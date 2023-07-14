package com.gxnu.mapper;

import com.gxnu.domain.Rules;
import com.gxnu.redomain.ReRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RulesMapper {
    Integer insertIntoRules(@Param("groupID") Integer groupID,@Param("content") String content,@Param("addScore") int addScore,@Param("maxScore") Double maxScore,@Param("belong") String belong);
    Integer deleteRules(@Param("rulesID") Integer rulesID);
    List<Rules> selectRulesByBelong(@Param("belong") String belong);

    Integer updateRules(@Param("rulesID") Integer rulesID,@Param("groupID") Integer groupID,@Param("content") String content,@Param("addScore") int addScore,@Param("maxScore") Double maxScore);
    Rules selectRulesByRulesID(@Param("rulesID") Integer rulesID);



    List<Rules> selectRulesByGroupID(@Param("groupID") Integer groupID);


}
