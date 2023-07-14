package com.gxnu.controller;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Score;
import com.gxnu.service.Impl.ScoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("score")
@ResponseBody
public class ScoreController {
    @Autowired
    ScoreServiceImpl scoreService;
    @RequestMapping("calculate")
    public BaseResponse calculate(@RequestParam("account") String account, HttpServletRequest request){
        BaseResponse response=scoreService.calculate(account);
        return response;
    }
    @RequestMapping("add")
    public BaseResponse add(Score score){
        return null;
    }
    @RequestMapping("delete")
    public BaseResponse delete(Integer id){
        return null;
    }
    @RequestMapping("fix")
    public BaseResponse fix(Integer id,Score newScore){
        return null;
    }
    @RequestMapping("find")
    public BaseResponse find(Integer id){
        return null;
    }
}
