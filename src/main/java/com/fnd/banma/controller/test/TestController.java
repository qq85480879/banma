package com.fnd.banma.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping()
    public String test(Model model){

        model.addAttribute("message","用户名已存在");

        return "/test/test";
    }

    @RequestMapping("/toRegist")
    public String toRegist(Model model){

        model.addAttribute("message","用户名已存在");

        return "frontdesk/regist/regist";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "frontdesk/login/login";
    }

}
