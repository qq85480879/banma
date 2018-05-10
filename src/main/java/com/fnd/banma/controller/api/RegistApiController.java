package com.fnd.banma.controller.api;

import com.fnd.banma.pojo.User;
import com.fnd.banma.service.RegistService;
import com.fnd.banma.vo.SysResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/regist")
public class RegistApiController {

    Logger logger = LoggerFactory.getLogger(RegistApiController.class);

    @Autowired
    private RegistService registService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public SysResult<User> toRegist(User user) {

        registService.regist(user);
        try {
            logger.info("注册成功:" + user.toString());
            return SysResult.genSuccessResult(user);
        } catch (Exception e) {
            logger.error("注册异常" + user.toString(), e);
            return SysResult.genErrorResult("注册失败");
        }

    }


    /*检查用户名是否存在*/
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public boolean checkName(String username, Model model) {
        try {
            User user = registService.checkName(username);
            if (user != null) {
                model.addAttribute("msg", "用户名已存在");
                return false;
            } else {
                model.addAttribute("msg", "用户名可用");
                return true;
            }
        } catch (Exception e) {
            logger.error("查询用户名异常" + username, e);
            return false;
        }
    }


}
