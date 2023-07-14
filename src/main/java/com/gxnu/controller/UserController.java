package com.gxnu.controller;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.User;
import com.gxnu.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("login")
    public BaseResponse  userLogin(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest request){
        BaseResponse response = userService.userLogin(account,password,request);
        return response;
    }
    @RequestMapping("register")
    public BaseResponse userRegister(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("college") String college, @RequestParam("theClass") String theClass, @RequestParam("checkPassword") String checkPassword ,HttpServletRequest request){
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setCollege(college);
        user.setTheClass(theClass);
        BaseResponse response = userService.userRegister(checkPassword,user,request);

        return response;
    }
    @RequestMapping("delete")
    public BaseResponse userDelete(Integer id){
        return null;
    }
    @RequestMapping("fix")
    public BaseResponse userFix(Integer id,User newUser){
        return null;
    }
    @RequestMapping("current")
    public BaseResponse getUser(HttpServletRequest request){
        BaseResponse response =  userService.getLoginUser(request);
        return response;
    }
    @RequestMapping("logout")
    public BaseResponse logout(HttpServletRequest request){
        BaseResponse response = userService.userLogout(request);
        return response;
    }

    @RequestMapping("changPassword")
    public BaseResponse changPassword(@RequestParam("userID") Integer userID,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,@RequestParam("checkPassword") String checkPassword,HttpServletRequest request){
        BaseResponse response=userService.changePassword(userID,oldPassword,newPassword,checkPassword,request);
        return response;
    }
    @RequestMapping("findAll")
    public BaseResponse findAll(HttpServletRequest request){
        BaseResponse response=userService.findAll(request);
        return response;
    }

    @RequestMapping("fixIsAdmin")
    public BaseResponse fixIsAdmin(@RequestParam("account") String account,HttpServletRequest request){
        BaseResponse response=userService.fixIsAdmin(account,request);
        return response;
    }

    @RequestMapping("findUser")
    public BaseResponse findUser(@RequestParam("account") String account,HttpServletRequest request){
        BaseResponse response=userService.findUser(account,request);
        return response;
    }

    @RequestMapping("findUserIsAdmin")
    public BaseResponse findUserIsAdmin(HttpServletRequest request){
        BaseResponse response=userService.findUserIsAdmin(request);
        return response;
    }
    @RequestMapping("findUserAdmin")
    public BaseResponse findUserAdmin(HttpServletRequest request){
        BaseResponse response=userService.findUserAdmin(request);
        return response;
    }
    @RequestMapping("deleteUserAdmin")
    public BaseResponse deleteUserAdmin(@RequestParam("account") String account,HttpServletRequest request){
        BaseResponse response=userService.deleteUserAdmin(account,request);
        return response;
    }

    @RequestMapping("fixAdminToAdmin")
    public BaseResponse fixAdminToAdmin(@RequestParam("account") String account,HttpServletRequest request){
        BaseResponse response=userService.fixAdminToAdmin(account,request);
        return response;
    }

    @RequestMapping("fixAdminToUser")
    public BaseResponse fixAdminToUser(@RequestParam("account") String account,HttpServletRequest request){
        BaseResponse response=userService.fixAdminToUser(account,request);
        return response;
    }

}
