package com.gxnu.redomain;

import lombok.Data;

@Data
public class ReRules {
    private Integer rulesID;
    private Integer groupID;
    private String content;
    private Integer addScore;
    private Double maxScore;
    private String belong;
}
