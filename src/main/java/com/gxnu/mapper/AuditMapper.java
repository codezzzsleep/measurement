package com.gxnu.mapper;

import com.gxnu.domain.Audit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditMapper {
    Integer insertIntoAudit(@Param("belong") String belong,@Param("status") Integer status,@Param("rulesID") Integer rulesID,@Param("file") String file);
    Audit selectAuditByFile(@Param("file") String file);
    Integer deleteAudit(@Param("auditID") Integer auditID);
    List<Audit> selectAuditByBelong(@Param("belong") String belong);

    Audit selectAuditByAuditID(@Param("auditID") Integer auditID);

    List<Audit> selectAuditByLike(@Param("account") String account);

    Integer updateAuditStatus(@Param("auditID") Integer auditId, @Param("status") Integer status);

    Integer updateAuditRate(@Param("auditID") Integer auditId, @Param("score") Integer score);
}
