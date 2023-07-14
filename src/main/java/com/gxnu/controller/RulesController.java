package com.gxnu.controller;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Rules;
import com.gxnu.domain.Score;
import com.gxnu.service.Impl.RulesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("rules")
@ResponseBody
public class RulesController {

    @Autowired
    RulesServiceImpl rulesService;
    @RequestMapping("add")
    public BaseResponse add(@RequestParam("groupID") Integer groupID,@RequestParam("content") String content,@RequestParam("addScore") Integer addScore,@RequestParam("maxScore") Double maxScore,@RequestParam("belong") String belong, HttpServletRequest request){
        Rules rules=new Rules();
        rules.setGroupID(groupID);
        rules.setContent(content);
        rules.setAddScore(addScore);
        rules.setMaxScore(maxScore);
        rules.setBelong(belong);
        BaseResponse response=rulesService.add(rules,request);
        return response;
    }
    @RequestMapping("delete")
    public BaseResponse delete(@RequestParam("rulesID") Integer rulesID,HttpServletRequest request){
        BaseResponse response=rulesService.delete(rulesID,request);
        return response;
    }
    @RequestMapping("fix")
    public BaseResponse fix(@RequestParam("rulesID") Integer rulesID,@RequestParam("groupID") Integer groupID,@RequestParam("content") String content,@RequestParam("addScore") Integer addScore,@RequestParam("maxScore") Double maxScore, HttpServletRequest request){
        Rules rules=new Rules();
        rules.setRulesID(rulesID);
        rules.setGroupID(groupID);
        rules.setContent(content);
        rules.setAddScore(addScore);
        rules.setMaxScore(maxScore);
        BaseResponse response=rulesService.fix(rulesID,groupID,content,addScore,maxScore,request);
        return response;
    }
    @RequestMapping("find")
    public BaseResponse find(@RequestParam("rulesID") Integer rulesID,HttpServletRequest request){
        BaseResponse response = rulesService.find(rulesID,request);
        return response;
    }
    @RequestMapping("list")
    public BaseResponse find(HttpServletRequest request){
        BaseResponse response=rulesService.list(request);
        return response;
    }
}
