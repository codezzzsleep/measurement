package com.gxnu.service.Impl;

import com.gxnu.domain.*;
import com.gxnu.mapper.AuditMapper;
import com.gxnu.mapper.GroupMapper;
import com.gxnu.mapper.RulesMapper;
import com.gxnu.mapper.ScoreMapper;
import com.gxnu.service.ScoreService;
import com.gxnu.util.GroupContentUtils;
import com.gxnu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    GroupContentUtils groupContentUtils = new GroupContentUtils();
    @Override
    public BaseResponse calculate(String userAccount) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
        RulesMapper rulesMapper = sqlSession.getMapper(RulesMapper.class);
        ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);
        GroupMapper groupMapper = sqlSession.getMapper(GroupMapper.class);
//        查询该用户所有的综测审核信息
        List<Audit> auditList = auditMapper.selectAuditByBelong(userAccount);
        Score scoreDoMain = scoreMapper.selectScore(userAccount);
//        判断该用户在score表中是否已经存在
//        如果存在则把单项的分值制空
        if(scoreDoMain == null){
            scoreMapper.insertIntoScoreAccount(userAccount);
            sqlSession.commit();
        }
        scoreMapper.updateScore(userAccount,0.0,0.0,0.0,0.0,0.0);

        sqlSession.commit();
//        定义运行时产生的分数数组, 下标从1开始
        double[] runScore ={0.0,0.0,0.0,0.0,0.0,0.0};
        double[] proportion = {0.0,0.0,0.0,0.0,0.0};
        for (Audit audit :auditList) {
            Rules rules = rulesMapper.selectRulesByRulesID(audit.getRulesID());
            Group group = groupMapper.selectByGroupID(rules.getGroupID());

            double score = audit.getScore();
//            对加分减分项进行判断，并进行值修复
            if(rules.getAddScore() == 1){
                score = -score;
            }
            runScore[group.getGroupID()]+= score;
            proportion[group.getGroupID()] = group.getProportion();
        }
//        对分值大于100 或 小于 0 的进行规格化处理
        for(double item :runScore){
            if(item>100){
                item = 100;
            } else if (item < 0) {
                item = 0;
            }
        }
        for(int i=1;i<5;i++){
            runScore[5]+=runScore[i]*proportion[i];
        }
        scoreMapper.updateScore(userAccount,runScore[1],runScore[2],runScore[3],runScore[4],runScore[5]);
        Score result = scoreMapper.selectScore(userAccount);
        sqlSession.commit();
        sqlSession.close();
        BaseResponse response = new BaseResponse(0,result,"ok");
        return response;
    }
}
