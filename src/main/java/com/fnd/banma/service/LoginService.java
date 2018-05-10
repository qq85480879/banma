package com.fnd.banma.service;

import com.fnd.banma.mapper.LoginMapper;
import com.fnd.banma.pojo.User;
import com.fnd.banma.test.SHA;
import com.fnd.banma.vo.SystemTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    public User findUserForUP(User user) {

        String password = user.getPassword();
        password = SHA.getResule(password);
        user.setPassword(password);

        return loginMapper.findUserForUP(user);
    }

    public void updataUser(User user) {

        user.setUpdateTime(SystemTime.getSystemTime());

        loginMapper.updataUser(user);
    }
}
