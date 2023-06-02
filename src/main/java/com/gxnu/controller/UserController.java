package com.gxnu.controller;

import com.gxnu.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("login")
    @ResponseBody
    public String  userLogin(User user){
        return "user login";
    }
    @RequestMapping("register")
    @ResponseBody
    public String userRegister(User user){
        return "user register";
    }
}
