package com.fnd.banma.controller.h5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/h5/head")
public class HeadH5Controller {

    @RequestMapping("/login")
    public String toLogin(){
        return "frontdesk/login/login";
    }

    @RequestMapping("/regist")
    public String toRegist(){
        return "frontdesk/regist/regist";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "frontdesk/index/index";
    }

    @RequestMapping("/prodlist")
    public String toProd(){
        return "frontdesk/prodlist/prod_list";
    }

    @RequestMapping("/cart")
    public String toCart(){
        return "frontdesk/cart/cart";
    }

    @RequestMapping("/orderadd")
    public String toOrderAdd(){
        return "frontdesk/orderadd/orderadd";
    }

    @RequestMapping("/orderlist")
    public String toOrderList(){
        return "frontdesk/orderlist/orderlist";
    }

    @RequestMapping("/toLogout")
    public String toLogout(HttpSession httpSession) {
        //将user对象从session域中删除
        httpSession.removeAttribute("user");
        return "frontdesk/index/index";
    }

}
