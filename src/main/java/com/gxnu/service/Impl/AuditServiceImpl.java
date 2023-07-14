package com.gxnu.service.Impl;

import com.gxnu.domain.Audit;
import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Score;
import com.gxnu.domain.User;
import com.gxnu.mapper.AuditMapper;
import com.gxnu.mapper.RulesMapper;
import com.gxnu.mapper.UserMapper;
import com.gxnu.redomain.ReAudit;
import com.gxnu.redomain.ReAuditRules;
import com.gxnu.service.AuditService;
import com.gxnu.util.ExtensionAuditUtils;
import com.gxnu.util.SafeDomainUtils;
import com.gxnu.util.SqlSessionFactoryUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.gxnu.util.ErrorCode.*;

@Service
public class AuditServiceImpl implements AuditService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    ExtensionAuditUtils extensionAuditUtils = new ExtensionAuditUtils();

    @Override
    public BaseResponse add(Audit audit, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AuditMapper mapper=sqlSession.getMapper(AuditMapper.class);
        Integer n=mapper.insertIntoAudit(audit.getBelong(),audit.getStatus(),audit.getRulesID(),audit.getFile());
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(PARAMS_ERROR);
            return response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReAudit reAudit=safeDomainUtils.getSafeAudit(audit);
        BaseResponse response=new BaseResponse(0,reAudit,"OK");
        return response;
    }

    @Override
    public BaseResponse delete(Integer auditID, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AuditMapper mapper=sqlSession.getMapper(AuditMapper.class);
        Audit audit=mapper.selectAuditByAuditID(auditID);
        Integer n=mapper.deleteAudit(auditID);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0))
        {
            BaseResponse response=new BaseResponse(PARAMS_ERROR);
            return response;
        }

        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReAudit reAudit=safeDomainUtils.getSafeAudit(audit);
        BaseResponse response=new BaseResponse(SUCCESS);

        return response;
    }

    @Override
    public BaseResponse fix(Integer auditID, Audit newAudit, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponse find(Integer id, HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponse find(String belong,HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper mapper = sqlSession.getMapper(AuditMapper.class);
        List<Audit> auditList=mapper.selectAuditByBelong(belong);
        if(auditList.isEmpty()){
            BaseResponse response = new BaseResponse(NOT_FOUND_ERROR);
            return response;
        }


        List<ReAuditRules> reAuditRulesList = extensionAuditUtils.getExtensionAudit(auditList);

        BaseResponse response=new BaseResponse(0,reAuditRulesList,"ok");
        sqlSession.close();
        return response;
    }

    @Override
    public BaseResponse findAuditStatus(HttpServletRequest request) {
        return null;
    }

    @Override
    public BaseResponse listAll(String account ,HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper mapper = sqlSession.getMapper(AuditMapper.class);
        List<Audit> auditList =  mapper.selectAuditByLike(account);
        List<ReAuditRules> reAuditRulesList = extensionAuditUtils.getExtensionAudit(auditList);
        BaseResponse response = new BaseResponse(0,reAuditRulesList,"ok");
        return response;
    }

    @Override
    public BaseResponse listByUser(String account, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper mapper = sqlSession.getMapper(AuditMapper.class);
        List<Audit> auditList =  mapper.selectAuditByLike(account);
        List<ReAuditRules> reAuditRulesList = extensionAuditUtils.getExtensionAudit(auditList);
        Iterator<ReAuditRules> iterator = reAuditRulesList.iterator();
        while (iterator.hasNext()) {
            ReAuditRules element = iterator.next();
            if (element.getStatus() != 0) {
                iterator.remove();
            }
        }
        BaseResponse response = new BaseResponse(0,reAuditRulesList,"ok");
        return response;
    }

    @Override
    public BaseResponse roolBack(Integer auditID, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper mapper = sqlSession.getMapper(AuditMapper.class);
        Integer n  = mapper.updateAuditStatus(auditID,2);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            return new BaseResponse<>(SYSTEM_ERROR);
        }

        return new BaseResponse(SUCCESS);
    }

    @Override
    public BaseResponse rate(Integer auditID, Integer score, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper mapper = sqlSession.getMapper(AuditMapper.class);
        Integer n = mapper.updateAuditRate(auditID,score);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            return new BaseResponse(SYSTEM_ERROR);
        }
        return new BaseResponse(SUCCESS);
    }


}
