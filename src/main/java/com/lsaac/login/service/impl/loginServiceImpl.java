package com.lsaac.login.service.impl;

import com.lsaac.login.domain.LoginUser;
import com.lsaac.login.domain.ResponseResult;
import com.lsaac.login.domain.User;
import com.lsaac.login.util.JwtUtil;
import com.lsaac.login.util.RedisCache;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lsaac.login.service.LoginService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 实现类
@Service
public class  loginServiceImpl implements LoginService {


    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Autowired
    private  UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseResult logout() {
        //获取SceurityContextHolder的用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId);

        return new ResponseResult(200,"注销成功");
    }

    @Override
    public ResponseResult login(User user) {


        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        try {
            UserDetails userDetails = userDetailService.loadUserByUsername(authenticationToken.getName());
            Object principal = authenticationToken.getPrincipal();
        }catch (Exception e){
            return new ResponseResult(HttpStatus.UNAUTHORIZED.value(),e.getMessage());
        }

        Authentication authentication =  authenticationManager.authenticate(authenticationToken);

        //如果认证没通过，给出对应的提示

        if(Objects.isNull(authentication)){
            throw  new RuntimeException("登录失败");
        }
        //如果认证通过，使用userid生成一个jwt，jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis，userid作为key

        redisCache.setCacheObject("login:"+userid,loginUser);

        return new ResponseResult(200,"登录成功",map);
    }
}
