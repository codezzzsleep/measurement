package com.gxnu.service.Impl;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Rules;
import com.gxnu.mapper.RulesMapper;
import com.gxnu.redomain.ReRules;
import com.gxnu.redomain.ReRulesList;
import com.gxnu.service.RulesService;
import com.gxnu.util.GroupContentUtils;
import com.gxnu.util.SafeDomainUtils;
import com.gxnu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.gxnu.util.ErrorCode.*;

@Service
public class RulesServiceImpl implements RulesService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public BaseResponse add(Rules rules, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RulesMapper mapper=sqlSession.getMapper(RulesMapper.class);
        Integer n=mapper.insertIntoRules(rules.getGroupID(),rules.getContent(),rules.getAddScore(),rules.getMaxScore(),rules.getBelong());

        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReRules reRules=safeDomainUtils.getSafeReRules(rules);
        BaseResponse response=new BaseResponse(0,reRules,"ok");
        return response;
    }

    @Override
    public BaseResponse delete(Integer rulesID, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RulesMapper mapper=sqlSession.getMapper(RulesMapper.class);
        Rules rules=mapper.selectRulesByRulesID(rulesID);
        Integer n= mapper.deleteRules(rulesID);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReRules reRules=safeDomainUtils.getSafeReRules(rules);
        BaseResponse response=new BaseResponse(SUCCESS);
        return response;
    }

    @Override
    public BaseResponse fix(Integer rulesID, Integer groupID,String content,Integer addScore,Double maxScore, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RulesMapper mapper=sqlSession.getMapper(RulesMapper.class);

        Integer n=mapper.updateRules(rulesID,groupID,content,addScore,maxScore);
        Rules rules=mapper.selectRulesByRulesID(rulesID);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReRules reRules=safeDomainUtils.getSafeReRules(rules);
        BaseResponse response=new BaseResponse(0,reRules,"ok");
        return response;
    }

    @Override
    public BaseResponse find(Integer rulesID, HttpServletRequest request) {
        if(rulesID < 0){
            return new BaseResponse<>(PARAMS_ERROR);
        }
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RulesMapper mapper=sqlSession.getMapper(RulesMapper.class);
        Rules rules = mapper.selectRulesByRulesID(rulesID);
        BaseResponse response = new BaseResponse(0,rules,"ok");
        return response;
    }

    @Override
    public BaseResponse list(HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RulesMapper mapper=sqlSession.getMapper(RulesMapper.class);
        List<ReRulesList> reRulesLists = new ArrayList<>();
        GroupContentUtils utils= new GroupContentUtils();
        SafeDomainUtils safeDomainUtils = new SafeDomainUtils();
        for (int i=1;i<5;i++){
            ReRulesList rulesList = new ReRulesList();
            rulesList.setValue(i);
            rulesList.setLabel(utils.getGroupLabel(i));
            List<Rules> list=mapper.selectRulesByGroupID(i);
            rulesList.setChildren(safeDomainUtils.rulesToChildren(list));
            reRulesLists.add(rulesList);
        }
        BaseResponse response = new BaseResponse(0,reRulesLists,"ok");
        return response;
    }
}
