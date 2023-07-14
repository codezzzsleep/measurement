package com.gxnu.domain;

import lombok.Data;

@Data
public class User {
    private Integer userID;
    private String account;
    private String password;
    private Integer isDelete;
    private Integer isAdmin;
    private String college;
    private String theClass;
    private String counselor;

}
