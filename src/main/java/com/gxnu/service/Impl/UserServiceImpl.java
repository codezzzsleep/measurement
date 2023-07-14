package com.gxnu.service.Impl;

import com.gxnu.domain.BaseResponse;
import com.gxnu.domain.User;
import com.gxnu.mapper.AuditMapper;
import com.gxnu.mapper.UserMapper;
import com.gxnu.redomain.ReUser;
import com.gxnu.service.UserService;
import com.gxnu.util.ErrorCode;
import com.gxnu.util.SafeDomainUtils;
import com.gxnu.util.SqlSessionFactoryUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static com.gxnu.util.ErrorCode.*;


@Service
public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    private static final String SALT = "web";
    @Override
    public BaseResponse userLogin(String account, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(account, password)) {
            BaseResponse response = new BaseResponse(ErrorCode.PARAMS_ERROR);
            return response;
        }

        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 查询用户是否存在
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectUserByAccountAndPassword(account,encryptPassword);
        sqlSession.close();
        // 用户不存在
        if (user == null) {
            BaseResponse response = new BaseResponse(ErrorCode.LOGIN_ERROR);
            return response;
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute("userLoginState", user);
        SafeDomainUtils safeDomainUtils =new SafeDomainUtils();
        ReUser reUser= safeDomainUtils.getSafeUser(user);
        BaseResponse response = new BaseResponse(0,reUser,"ok");
        return response;
    }

    @Override
    public BaseResponse userRegister(String checkPassword,User user, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User exit = mapper.selectUserByAccount(user.getAccount());
        if (exit!=null){
            BaseResponse response = new BaseResponse(ErrorCode.REGISTER_ERROT);
            return response;
        }
        if(StringUtils.isAnyBlank(user.getAccount(),user.getPassword(),checkPassword)){
            BaseResponse response = new BaseResponse(ErrorCode.PARAMS_ERROR);
            return response;
        }
        if (!StringUtils.equals(user.getPassword(),checkPassword)){
            BaseResponse response = new BaseResponse(ErrorCode.PARAMS_ERROR);
            return response;
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user.getPassword()).getBytes());
        Integer number = mapper.insertIntoUser(user.getAccount(), encryptPassword, user.getCollege(), user.getTheClass());
        sqlSession.commit();
        sqlSession.close();
        if (number==0){
            BaseResponse response = new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        BaseResponse response = new BaseResponse(0,"注册成功，请登录！");
        return response;
    }


    @Override
    public BaseResponse getLoginUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLoginState");
        if (user == null){
            BaseResponse baseResponse = new BaseResponse(ErrorCode.NOT_LOGIN_ERROR);
            return baseResponse;
        }
        SafeDomainUtils safeDomainUtils = new SafeDomainUtils();
        ReUser reUser = safeDomainUtils.getSafeUser(user);
        Integer code = 200;
        if (user.getIsAdmin() > 0){
            code = 300;
        }
        BaseResponse baseResponse = new BaseResponse(code,reUser);
        return baseResponse;
    }

    @Override
    public BaseResponse userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("userLoginState");
        BaseResponse response = new BaseResponse(SUCCESS);
        return response;
    }

    @Override
    public BaseResponse changePassword(Integer userID,String oldPassword,String newPassword, String checkPassword,HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + oldPassword).getBytes());
        User user=mapper.selectUserByUserIDAndPassword(userID,encryptPassword);
        if(user==null){
            BaseResponse response=new BaseResponse(PASSWORD_ERROT);
            return response;
        }
        if(!newPassword.equals(checkPassword)){
            BaseResponse response=new BaseResponse(PARAMS_ERROR);
            return response;
        }

        encryptPassword = DigestUtils.md5DigestAsHex((SALT + newPassword).getBytes());
        Integer n=mapper.updateUserPassword(userID,encryptPassword);
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        sqlSession.commit();
        sqlSession.close();
        SafeDomainUtils safeDomainUtils =new SafeDomainUtils();
        ReUser reUser= safeDomainUtils.getSafeUser(user);
        BaseResponse response = new BaseResponse(SUCCESS);
        return response;
    }

    @Override
    public BaseResponse findAll(HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.selectUserAll();
        sqlSession.close();
        List<ReUser> reUserList=new ArrayList<>();
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        reUserList=safeDomainUtils.getSafeUser(userList);
        List<String> states=new ArrayList<>();
        for(ReUser reUser:reUserList){

           states.add(reUser.getAccount());

        }
        BaseResponse response=new BaseResponse(0,states,"ok");
        return response;
    }

    @Override
    public BaseResponse fixIsAdmin(String account, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer n=mapper.updateUserStatusByAccount(account);
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return response;
        }
        sqlSession.commit();
        sqlSession.close();
        BaseResponse response=new BaseResponse(SUCCESS);
        return response;


    }

    @Override
    public BaseResponse findUser(String account, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectUserByAccount(account);
        sqlSession.close();
        if(user==null){
            BaseResponse response=new BaseResponse(PARAMS_ERROR);
            return  response;
        }
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReUser reUser=safeDomainUtils.getSafeUser(user);
        BaseResponse response=new BaseResponse(0,reUser,"ok");
        return response;
    }

    @Override
    public BaseResponse findUserIsAdmin(HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList=mapper.selectUserAll();
        sqlSession.close();
        if(userList==null){
            BaseResponse response=new BaseResponse(NOT_FOUND_ERROR);
            return  response;
        }
        List<ReUser> reUserList=new ArrayList();
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        for(User user:userList){
            if(user.getIsAdmin()==1){

                reUserList.add(safeDomainUtils.getSafeUser(user));
            }
        }
        BaseResponse response=new BaseResponse(0,reUserList,"ok");
        return  response;

    }

    @Override
    public BaseResponse findUserAdmin(HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList=mapper.selectUserAll();
        sqlSession.close();
        if(userList==null){
            BaseResponse response=new BaseResponse(NOT_FOUND_ERROR);
            return  response;
        }
        List<ReUser> reUserList=new ArrayList();
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        for(User user:userList){
            if(user.getIsAdmin()==2){

                reUserList.add(safeDomainUtils.getSafeUser(user));
            }
        }
        BaseResponse response=new BaseResponse(0,reUserList,"ok");
        return  response;
    }



    @Override
    public BaseResponse fixAdminToAdmin(String account, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectUserByAccount(account);
        if(user.getIsAdmin()!=0){
            BaseResponse response=new BaseResponse(40005,"已是管理员");
            return response;
        }
        Integer n=mapper.updateAdminToAdmin(account);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return  response;
        }
        BaseResponse response=new BaseResponse(SUCCESS);
        return response;


    }

    @Override
    public BaseResponse fixAdminToUser(String account, HttpServletRequest request) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectUserByAccount(account);
        if(user.getIsAdmin()!=0){
            BaseResponse response=new BaseResponse(40005,"已是管理员");
            return response;
        }
        Integer n=mapper.updateAdminToUser(account);
        sqlSession.commit();
        sqlSession.close();
        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return  response;
        }
        BaseResponse response=new BaseResponse(SUCCESS);
        return response;

    }

    @Override
    public BaseResponse deleteUserAdmin(String account, HttpServletRequest request) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Integer n=mapper.updateUserIsAdmin(account);

        if(n.equals(0)){
            BaseResponse response=new BaseResponse(SYSTEM_ERROR);
            return  response;
        }
        User user=mapper.selectUserByAccount(account);
        sqlSession.commit();
        sqlSession.close();
        SafeDomainUtils safeDomainUtils=new SafeDomainUtils();
        ReUser reUser=safeDomainUtils.getSafeUser(user);
        BaseResponse response=new BaseResponse(0,reUser,"ok");
        return response;
    }


}
