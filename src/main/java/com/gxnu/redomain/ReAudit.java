package com.gxnu.redomain;

import lombok.Data;

@Data
public class ReAudit {
    private Integer auditID;
    private String belong;
    private Integer status;
    private Integer rulesID;
    private String file;
    private Double score;

}
