package com.fnd.banma.service;

import com.fnd.banma.mapper.UserMapper;
import com.fnd.banma.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> query(){
        return userMapper.query();
    }

}
