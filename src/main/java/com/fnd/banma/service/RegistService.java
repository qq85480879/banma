package com.fnd.banma.service;

import com.fnd.banma.mapper.RegistMapper;
import com.fnd.banma.pojo.User;
import com.fnd.banma.test.SHA;
import com.fnd.banma.vo.SystemTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RegistService {

    Logger logger = LoggerFactory.getLogger(RegistService.class);

    @Autowired
    private RegistMapper registMapper;

    public void regist(User user) {

        try {
            Date date = new Date();
            /*并发量小 采用时间戳毫秒级 例如：20180418113333+456 17位*/
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmsssss");

            String userId = simpleDateFormat.format(date);

            user.setUserId(userId);

            user.setCreatedTime(SystemTime.getSystemTime());

            user.setUpdateTime(SystemTime.getSystemTime());

            user.setPassword(SHA.getResule(user.getPassword()));

            registMapper.regist(user);

        } catch (Exception e) {
            logger.error("转换用户数据异常" + user.toString(), e);
        }

    }

    public User checkName(String username) {
        return registMapper.checkName(username);
    }
}
