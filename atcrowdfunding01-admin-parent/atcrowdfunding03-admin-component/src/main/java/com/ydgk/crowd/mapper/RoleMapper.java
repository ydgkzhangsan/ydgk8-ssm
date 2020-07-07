package com.ydgk.crowd.mapper;

import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectRoleByKeyWord(@Param("keyWord") String keyWord);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    void deleteByIds(@Param("roleIds") List<Integer> roleIds);

    List<Role> selectAssignedList(Integer adminId);

    List<Role> selectUnassignedList(Integer adminId);

    void deleteInnerAdminRoleByAdminId(Integer adminId);

    void insertInnerAdminRole(@Param("adminId") Integer adminId,@Param("assignedRoleIds") Integer[] assignedRoleIds);
}