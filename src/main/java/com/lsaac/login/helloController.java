package com.lsaac.login;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public  String hello(){
        return "hello";
    }
}
