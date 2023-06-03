package com.gxnu.service;

import com.gxnu.domain.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User userLogin(String account, String password, HttpServletRequest request);
    long userRegister(String account,String password,String checkPassword);
    User getLoginUser(HttpServletRequest request);

}
