package com.gxnu.controller;

import com.gxnu.domain.Audit;
import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Score;
import com.gxnu.domain.User;
import com.gxnu.service.AuditService;
import com.gxnu.service.Impl.AuditServiceImpl;
import com.gxnu.util.ErrorCode;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("audit")
@ResponseBody
public class AuditController {
    @Autowired
    private AuditServiceImpl auditService;
    @Autowired
    MultipartResolver multipartResolver;
    @RequestMapping("add")
    public BaseResponse add(@RequestParam("belong") String belong, @RequestParam("status") Integer status, @RequestParam("rulesID") Integer rulesID, @RequestParam("file") String file, HttpServletRequest request){
        Audit audit=new Audit();
        audit.setBelong(belong);
        audit.setStatus(status);
        audit.setRulesID(rulesID);
        audit.setFile(file);
        BaseResponse response=auditService.add(audit,request);
        return response;
    }
    @RequestMapping("delete")
    public BaseResponse delete(@RequestParam("auditID") Integer auditID,HttpServletRequest request){
        BaseResponse response=auditService.delete(auditID,request);
        return response;
    }
    @RequestMapping("fix")
    public BaseResponse fix(Integer auditID,Audit newAudit){
        return null;
    }

    @RequestMapping("find")
    public BaseResponse find(@RequestParam("belong") String belong,HttpServletRequest request){
        BaseResponse response=auditService.find(belong, request);
        return response;
    }
    @RequestMapping("listByUser")
    public BaseResponse listByUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userLoginState");
        if(user.getAccount().length()<9){
            return new BaseResponse<>(ErrorCode.PARAMS_ERROR);
        }
        String account = user.getAccount().substring(0,9);
        BaseResponse response = auditService.listByUser(account,request);
        return response;
    }
    @RequestMapping("listByPost")
    public BaseResponse listByUser(@RequestParam("account") String account,HttpServletRequest httpServletRequest){
        String tmp = account.substring(0,9);
        BaseResponse response = auditService.listAll(account,httpServletRequest);
        return response;
    }


    @RequestMapping("listAll")
    public BaseResponse list( HttpServletRequest  request){
        User user = (User) request.getSession().getAttribute("userLoginState");
        BaseResponse response = auditService.listAll(user.getAccount(),request);
        return response;
    }
    @RequestMapping("roolBack")
    public BaseResponse roolBack(@RequestParam("auditID") Integer auditID,HttpServletRequest request){
        BaseResponse response = auditService.roolBack(auditID,request);
        return response;
    }
    @RequestMapping("rate")
    public BaseResponse rate(@RequestParam("auditID") Integer auditID,@RequestParam("score") Integer score,HttpServletRequest request){
        BaseResponse response = auditService.rate(auditID,score,request);
        return response;
    }
//    @RequestMapping("upload")
//    public String  uploadFile(HttpServletRequest request ) throws IOException {
//
//        User user =(User)  request.getSession().getAttribute("userLoginState");
//        if(user == null) return "error";
//        String belong = user.getAccount();
////        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(request);
//        MultipartFile multipartFile = multipartRequest.getFile("file");
//        String pathName = "../webapps/measurement_war/img/"+belong;
//        File fileDir = new File(pathName);
//        if(!fileDir.exists()) {
//
//            //如果没有目录应该创建目录
//            fileDir.mkdirs();
//        }
//        //获取图片名称
//        String imgName = multipartFile.getOriginalFilename();
//        Long time = System.currentTimeMillis();
//        String path = pathName+'/'+ time+imgName;
//        //文件实现上传
//        multipartFile.transferTo(new File(path));
//
//        return "../img/"+belong+time+imgName;
//    }
}
