package com.gxnu.service;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Group;

import javax.servlet.http.HttpServletRequest;

public interface GroupService {
    BaseResponse add(Group group, HttpServletRequest request);
    BaseResponse delete(Integer groupID,HttpServletRequest request);
    BaseResponse fix(Integer groupID,Double proportion,HttpServletRequest request);
    BaseResponse find(Integer groupID,HttpServletRequest request);
}
