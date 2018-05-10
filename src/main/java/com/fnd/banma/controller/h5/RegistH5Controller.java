package com.fnd.banma.controller.h5;


import com.fnd.banma.controller.api.RegistApiController;
import com.fnd.banma.pojo.User;
import com.fnd.banma.service.RegistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/h5/regist")
public class RegistH5Controller {

    Logger logger = LoggerFactory.getLogger(RegistApiController.class);

    @Autowired
    private RegistService registService;




}
