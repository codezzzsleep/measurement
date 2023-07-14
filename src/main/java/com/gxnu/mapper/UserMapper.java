package com.gxnu.mapper;

import com.gxnu.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
     User selectUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
     User selectUserByAccount(@Param("account") String account);
    Integer insertIntoUser(@Param("account") String account,@Param("password") String password,@Param("college") String college,@Param("theClass") String theClass);
    List<User> selectUserAll();

    Integer updateUserPassword(@Param("userID") Integer userID,@Param("password") String password);

    User  selectUserByUserIDAndPassword(@Param("userID") Integer userID,@Param("password") String password);
    Integer updateUserStatusByAccount(@Param("account") String account);

    Integer updateUserIsAdmin(@Param("account") String account);

    Integer updateAdminToAdmin(@Param("account") String account);

    Integer updateAdminToUser(@Param("account") String account);




}
