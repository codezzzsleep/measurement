package com.gxnu.mapper;

import com.gxnu.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    Integer insertIntoGroup(@Param("belong") String belong, @Param("proportion") Double proportion);
    Integer deleteByGroupID(@Param("groupID") Integer groupID);

    Group selectByGroupID(@Param("groupID") Integer groupID);

    Integer updateProportion(@Param("groupID") Integer groupID,@Param("proportion") Double proportion);
    List<Group> selectAllGroup();


}
