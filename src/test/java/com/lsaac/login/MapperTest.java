package com.lsaac.login;

import com.lsaac.login.domain.User;
import com.lsaac.login.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public  void  TestBCrypt(){
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

//    @Autowired
//    private UserMapper userMapper;
//    @Test
//    public  void testUserMapper(){
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
//    }
}
