package com.gxnu.redomain;

import lombok.Data;

@Data
public class ReUser {
    private Integer userID;
    private String account;
    private Integer isAdmin;
    private String college;
    private String theClass;
    private String counselor;
}
