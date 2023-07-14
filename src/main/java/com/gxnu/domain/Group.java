package com.gxnu.domain;

import lombok.Data;

@Data
public class Group {
    private Integer groupID;
    private String belong;
    private Double proportion;
    private Integer isDelete;
    private String groupName;
}
