package com.gxnu.domain;

import lombok.Data;

import javax.swing.*;

@Data
public class Audit {
    private Integer auditID;
    private String belong;
    private Integer status;
    private Integer rulesID;
    private String file;
    private Double score;
    private Integer isDelete;
}
