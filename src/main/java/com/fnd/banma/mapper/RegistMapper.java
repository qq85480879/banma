package com.fnd.banma.mapper;

import com.fnd.banma.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistMapper {

    void regist(User user);

    User checkName(String username);
}
