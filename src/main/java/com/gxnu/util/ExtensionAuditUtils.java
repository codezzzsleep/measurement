package com.gxnu.util;

import com.gxnu.domain.Audit;
import com.gxnu.mapper.RulesMapper;
import com.gxnu.redomain.ReAuditRules;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ExtensionAuditUtils {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    public List<ReAuditRules> getExtensionAudit(List<Audit> auditList){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RulesMapper rulesMapper = sqlSession.getMapper(RulesMapper.class);
        List<ReAuditRules> reAuditRulesList = new ArrayList<>();
        for (Audit audit : auditList) {
            ReAuditRules reAuditRules = new ReAuditRules();
            reAuditRules.setRulesContent(rulesMapper.selectRulesByRulesID(audit.getRulesID()).getContent());
            reAuditRules.setMaxScore(rulesMapper.selectRulesByRulesID(audit.getRulesID()).getMaxScore());
            reAuditRules.setRulesID(audit.getRulesID());
            reAuditRules.setAuditID(audit.getAuditID());
            reAuditRules.setScore(audit.getScore());
            reAuditRules.setBelong(audit.getBelong());
            reAuditRules.setFile(audit.getFile());
            reAuditRules.setStatus(audit.getStatus());
            reAuditRulesList.add(reAuditRules);
        }

        return reAuditRulesList;
    }
}
