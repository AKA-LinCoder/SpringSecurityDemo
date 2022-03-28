package com.lsaac.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsaac.login.domain.LoginUser;
import com.lsaac.login.domain.User;
import com.lsaac.login.mapper.MenuMapper;
import com.lsaac.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw  new RuntimeException("用户名不存在");
        }
        //TODO 查询对应的权限信息

//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));

        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        // 把数据封装成UserDetails返回


        return new LoginUser(user,list);
    }
}
