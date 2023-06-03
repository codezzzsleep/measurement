package com.gxnu.service.Impl;

import com.gxnu.domain.User;
import com.gxnu.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserServiceImpl implements UserService {
    @Override
    public User userLogin(String account, String password, HttpServletRequest request) {
        return null;
    }

    @Override
    public long userRegister(String account, String password, String checkPassword) {
        return 0;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }
}
