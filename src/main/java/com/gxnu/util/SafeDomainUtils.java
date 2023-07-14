package com.gxnu.util;

import com.gxnu.domain.Audit;
import com.gxnu.domain.Group;
import com.gxnu.domain.Rules;
import com.gxnu.domain.User;
import com.gxnu.redomain.*;

import java.util.ArrayList;
import java.util.List;

public class SafeDomainUtils {
    public ReUser getSafeUser(User user){
        ReUser reUser=new ReUser();
        reUser.setUserID(user.getUserID());
        reUser.setCollege(user.getCollege());
        reUser.setAccount(user.getAccount());
        reUser.setCounselor(user.getCounselor());
        reUser.setIsAdmin(user.getIsAdmin());
        reUser.setTheClass(user.getTheClass());

        return reUser;
    }
    public List<ReUser> getSafeUser(List<User> userList){
        List<ReUser> reUserList = new ArrayList<>();
        for(User user:userList){
            ReUser reUser=new ReUser();
            reUser.setUserID(user.getUserID());
            reUser.setCollege(user.getCollege());
            reUser.setAccount(user.getAccount());
            reUser.setCounselor(user.getCounselor());
            reUser.setIsAdmin(user.getIsAdmin());
            reUser.setTheClass(user.getTheClass());
            reUserList.add(reUser);

        }
        return reUserList;
    }

    public ReAudit getSafeAudit(Audit audit){
        ReAudit reAudit=new ReAudit();
        reAudit.setAuditID(audit.getAuditID());
        reAudit.setFile(audit.getFile());
        reAudit.setBelong(audit.getBelong());
        reAudit.setStatus(audit.getStatus());
        reAudit.setScore(audit.getScore());
        reAudit.setRulesID(audit.getRulesID());
        return reAudit;
    }

    public List<ReAudit> getSafeAudit(List<Audit> auditList){
        List<ReAudit> reAuditList = new ArrayList<>();
        for (Audit audit:auditList) {
            ReAudit reAudit=new ReAudit();
            reAudit.setAuditID(audit.getAuditID());
            reAudit.setFile(audit.getFile());
            reAudit.setBelong(audit.getBelong());
            reAudit.setStatus(audit.getStatus());
            reAudit.setRulesID(audit.getRulesID());
            reAudit.setScore(audit.getScore());
            reAuditList.add(reAudit);
        }

       return reAuditList;
    }

    public ReGroup getSafeReGroup(Group group){
        ReGroup reGroup=new ReGroup();
        reGroup.setGroupID(group.getGroupID());
        reGroup.setBelong(group.getBelong());
        reGroup.setProportion(group.getProportion());
        reGroup.setGroupName(group.getGroupName());
        return reGroup;
    }

    public ReRules getSafeReRules(Rules rules){
        ReRules reRules=new ReRules();
        reRules.setRulesID(rules.getRulesID());
        reRules.setContent(rules.getContent());
        reRules.setAddScore(rules.getAddScore());
        reRules.setMaxScore(rules.getMaxScore());
        reRules.setBelong(rules.getBelong());
        return reRules;
    }

    public List<Children> rulesToChildren(List<Rules> rulesList){
        List<Children> childrenList = new ArrayList<>();
        for (Rules rules: rulesList) {
            Children temp = new Children();
            temp.setLabel(rules.getContent());
            temp.setValue(rules.getRulesID());
            childrenList.add(temp);
        }
        return childrenList;
    }






}
