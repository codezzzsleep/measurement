package com.gxnu.mapper;

import com.gxnu.domain.User;

public interface UserMapper {
    public User selectUserByAccountAndPassword(String account,String password);
}
