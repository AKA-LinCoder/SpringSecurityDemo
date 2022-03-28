package com.lsaac.login.controller;

import com.lsaac.login.domain.ResponseResult;
import com.lsaac.login.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lsaac.login.service.LoginService;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){


        //登录
        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public  ResponseResult logout(){
        return  loginService.logout();
    }
}
