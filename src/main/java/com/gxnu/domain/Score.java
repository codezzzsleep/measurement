package com.gxnu.domain;

import lombok.Data;

@Data
public class Score {
    private Integer scoreID;
    private String account;
    private Double ideologicalAndMoral;
    private Double courseLearning;
    private Double bodyArtIntegrated;
    private Double practiceAbility;
}
