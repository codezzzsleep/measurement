package com.gxnu.util;

public class GroupContentUtils {
    String[] str = {"","ideologicalAndMoral","courseLearning","bodyArtIntegrated","practiceAbility"};
    String[] labels = {"","思想品德综合分","课程学习成绩综合分","体艺综合分","创新创业与实践能力综合分"};
    public String getGroupContent(Integer index){
        return str[index];
    }
    public String getGroupLabel(Integer index){
        return labels[index];
    }

}
