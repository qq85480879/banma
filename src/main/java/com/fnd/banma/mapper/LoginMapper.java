package com.fnd.banma.mapper;

import com.fnd.banma.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
    User findUserForUP(User user);

    void updataUser(User user);
}
