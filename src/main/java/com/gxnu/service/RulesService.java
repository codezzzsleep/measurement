package com.gxnu.service;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Group;
import com.gxnu.domain.Rules;

import javax.servlet.http.HttpServletRequest;

public interface RulesService {
    BaseResponse add(Rules rules, HttpServletRequest request);
    BaseResponse delete(Integer rulesID,HttpServletRequest request);
    BaseResponse fix(Integer rulesID, Integer groupID,String content,Integer addScore,Double maxScore,HttpServletRequest request);
    BaseResponse find(Integer rulesID,HttpServletRequest request);

    BaseResponse list(HttpServletRequest request);

}
