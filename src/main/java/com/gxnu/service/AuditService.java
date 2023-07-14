package com.gxnu.service;

import com.gxnu.domain.Audit;
import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Score;

import javax.servlet.http.HttpServletRequest;

public interface AuditService {
    BaseResponse add(Audit audit, HttpServletRequest request);
    BaseResponse delete(Integer auditID,HttpServletRequest request);
    BaseResponse fix(Integer auditID,Audit newAudit,HttpServletRequest request);
    BaseResponse find(Integer id,HttpServletRequest request);
    BaseResponse find(String belong,HttpServletRequest request);
    BaseResponse findAuditStatus(HttpServletRequest request);
    BaseResponse listAll(String account ,HttpServletRequest request);
    BaseResponse listByUser(String account, HttpServletRequest request);
    BaseResponse roolBack(Integer auditID, HttpServletRequest request);

    BaseResponse rate(Integer auditID, Integer score,HttpServletRequest request);

}
