package com.gxnu.redomain;

import lombok.Data;

import java.util.List;
@Data
public class ReRulesList {
    private Integer value;
    private String label;
    private List<Children> children;

}
