package com.ydgk.crowd.mapper;

import com.ydgk.crowd.entity.Auth;
import com.ydgk.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> getAuthIdByRoleId(Integer roleId);

    void deleteOldAuth(Integer roleId);

    void insertRoleAssignAuth(@Param("roleId") Integer roleId,@Param("authIds") List<Integer> authIds);

    List<Auth> selectAuthListByAdminId(Integer id);
}