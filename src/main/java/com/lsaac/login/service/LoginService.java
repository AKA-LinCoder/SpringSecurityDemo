package com.lsaac.login.service;

import com.lsaac.login.domain.ResponseResult;
import com.lsaac.login.domain.User;

public interface LoginService {


    ResponseResult logout();

    ResponseResult login(User user);
}
