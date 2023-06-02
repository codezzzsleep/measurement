package com.gxnu.domain;

import lombok.Data;

import javax.swing.*;

@Data
public class Rules {
    private Integer rulesID;
    private Integer groupID;
    private String content;
    private Integer addScore;
    private Double maxScore;
    private String belong;
}
