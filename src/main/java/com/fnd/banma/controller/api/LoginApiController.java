package com.fnd.banma.controller.api;

import com.fnd.banma.controller.h5.LoginH5Controller;
import com.fnd.banma.pojo.User;
import com.fnd.banma.service.LoginService;
import com.fnd.banma.vo.SysResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/login")
public class LoginApiController {

    Logger logger = LoggerFactory.getLogger(LoginH5Controller.class);

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public SysResult<User> toLogin(User user, HttpSession session) {

        try {

            User user2 = loginService.findUserForUP(user);

            if (user2 != null) {
                /*用户成功登录 将用户存入session*/
                session.setAttribute("user",user);

                /*将用户登录次数加一*/
                Integer loginTimes = user2.getLoginTimes();
                if(loginTimes != null){
                    user2.setLoginTimes(++loginTimes);
                } else {
                    user2.setLoginTimes(1);
                }

                loginService.updataUser(user2);

                logger.info("登录成功:" + user2.getUserId());
                return SysResult.genSuccessResult(user);
            } else {
                return SysResult.genDefeated("用户名或密码错误");
            }

        } catch (Exception e) {
            logger.error("登录异常", e);
            return SysResult.genErrorResult("登录异常");
        }

    }
}
