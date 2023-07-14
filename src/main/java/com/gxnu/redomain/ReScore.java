package com.gxnu.redomain;

import lombok.Data;

@Data
public class ReScore {
    private Integer scoreID;
    private String account;
    private Double ideologicalAndMoral;
    private Double courseLearning;
    private Double bodyArtIntegrated;
    private Double practiceAbility;
    private Double totalScore;
}
