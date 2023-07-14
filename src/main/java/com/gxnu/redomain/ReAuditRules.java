package com.gxnu.redomain;

import lombok.Data;

@Data
public class ReAuditRules {
    private Integer auditID;
    private String belong;
    private Integer status;
    private Integer rulesID;
    private String file;
    private Double score;
    private String rulesContent;
    private Double maxScore;
}
