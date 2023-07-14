package com.gxnu.controller;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Group;
import com.gxnu.domain.Score;
import com.gxnu.service.GroupService;
import com.gxnu.service.Impl.AuditServiceImpl;
import com.gxnu.service.Impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("group")
@ResponseBody
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;
    @RequestMapping("add")
    public BaseResponse add(@RequestParam("belong") String belong, @RequestParam("proportion") Double proportion, HttpServletRequest request){
        Group group=new Group();
        group.setBelong(belong);
        group.setProportion(proportion);
        BaseResponse response=groupService.add(group,request);
        return response;
    }
    @RequestMapping("delete")
    public BaseResponse delete(@RequestParam("groupID") Integer groupID,HttpServletRequest request){
        BaseResponse response=groupService.delete(groupID,request);
        return response;
    }
    @RequestMapping("fix")
    public BaseResponse fix(@RequestParam("groupID") Integer groupID,@RequestParam("proportion") Double proportion,HttpServletRequest request){
        BaseResponse response=groupService.fix(groupID,proportion,request);
        return response;
    }
    @RequestMapping("find")
    public BaseResponse find(@RequestParam("groupID") Integer groupID,HttpServletRequest request){
        BaseResponse response=groupService.find(groupID,request);
        return response;
    }
}
