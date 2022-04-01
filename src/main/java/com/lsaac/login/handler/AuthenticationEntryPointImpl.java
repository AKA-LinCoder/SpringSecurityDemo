package com.lsaac.login.handler;

import com.alibaba.fastjson.JSON;
import com.lsaac.login.domain.ResponseResult;
import com.lsaac.login.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//认证失败处理器
@Component
public class AuthenticationEntryPointImpl  implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        ///未认证错误
//        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"当前用户认证失败，请重新登录");
//        String json = JSON.toJSONString(result);
//        //处理异常
//        WebUtils.renderString(response,json);
//    }
@Override
public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    ///未认证错误
    ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"当前用户认证失败，请重新登录");
    String json = JSON.toJSONString(result);
    //处理异常
    WebUtils.renderString(response,json);
}
}
