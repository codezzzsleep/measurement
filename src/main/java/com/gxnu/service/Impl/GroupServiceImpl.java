package com.gxnu.service.Impl;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.Group;
import com.gxnu.mapper.GroupMapper;
import com.gxnu.redomain.ReGroup;
import com.gxnu.service.GroupService;
import com.gxnu.util.SafeDomainUtils;
import com.gxnu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.gxnu.util.ErrorCode.*;

@Service
public class GroupServiceImpl implements GroupService {

    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public BaseResponse add(Group group, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        GroupMapper mapper=sqlSession.getMapper(GroupMapper.class);
        List<Group> listGroup=mapper.selectAllGroup();
        double sum=0;
        for(int i=0;i<listGroup.size();i++){
            if(listGroup.get(i).getGroupID().equals(group.getGroupID()))
                continue;
            sum=sum+listGroup.get(i).getProportion();
        }

        if(group.getProportion()>1-sum||group.getProportion()<0){
            BaseResponse response=new BaseResponse(PROPORTION_ERROT);
            return response;
        }
        Integer n=mapper.insertIntoGroup(group.getBelong(),group.getProportion());
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReGroup reGroup=safeDomainUtils.getSafeReGroup(group);
        BaseResponse response=new BaseResponse(0,reGroup,"ok");

        return response;
    }

    @Override
    public BaseResponse delete(Integer groupID, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        GroupMapper mapper=sqlSession.getMapper(GroupMapper.class);
        Group group=mapper.selectByGroupID(groupID);
        Integer n=mapper.deleteByGroupID(groupID);

        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }

        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReGroup reGroup=safeDomainUtils.getSafeReGroup(group);
        BaseResponse response=new BaseResponse(SUCCESS);
        return response;
    }

    @Override
    public BaseResponse fix(Integer groupID, Double proportion, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        GroupMapper mapper=sqlSession.getMapper(GroupMapper.class);


        List<Group> listGroup=mapper.selectAllGroup();
        double sum=0;
        for(int i=0;i<listGroup.size();i++){
            if(listGroup.get(i).getGroupID().equals(groupID))
                continue;
            sum=sum+listGroup.get(i).getProportion();
        }

        if(proportion>1-sum||proportion<0){
            BaseResponse response=new BaseResponse(PROPORTION_ERROT);
            return response;
        }
        Integer n=mapper.updateProportion(groupID,proportion);
        Group group=mapper.selectByGroupID(groupID);
        sqlSession.commit();
        sqlSession.close();

        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReGroup reGroup=safeDomainUtils.getSafeReGroup(group);
        BaseResponse response=new BaseResponse(0,reGroup,"ok");
        return response;
    }

    @Override
    public BaseResponse find(Integer groupID, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        GroupMapper mapper=sqlSession.getMapper(GroupMapper.class);
        Group group=mapper.selectByGroupID(groupID);
        sqlSession.close();
        if(group==null){
            BaseResponse response=new BaseResponse(NOT_FOUND_ERROR);
            return  response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReGroup reGroup=safeDomainUtils.getSafeReGroup(group);
        BaseResponse response=new BaseResponse(0,reGroup,"ok");
        return response;

    }
}
