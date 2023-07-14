package com.gxnu.service;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    BaseResponse userLogin(String account, String password, HttpServletRequest request);
    BaseResponse userRegister(String checkPassword,User user,HttpServletRequest request);
    BaseResponse getLoginUser(HttpServletRequest request);
    BaseResponse userLogout(HttpServletRequest request);

    BaseResponse changePassword(Integer userID,String oldPassword,String newPassword,String checkPassword,HttpServletRequest request);
    BaseResponse findAll(HttpServletRequest request);
    BaseResponse fixIsAdmin(String account,HttpServletRequest request);
    BaseResponse findUser(String account,HttpServletRequest request);

    BaseResponse findUserIsAdmin(HttpServletRequest request);
    BaseResponse findUserAdmin(HttpServletRequest request);
    BaseResponse deleteUserAdmin(String account,HttpServletRequest request);



    BaseResponse fixAdminToAdmin(String account,HttpServletRequest request);

    BaseResponse fixAdminToUser(String account,HttpServletRequest request);



}
