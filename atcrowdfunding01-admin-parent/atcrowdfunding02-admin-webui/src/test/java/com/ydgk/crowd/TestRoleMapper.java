package com.ydgk.crowd;

import com.ydgk.crowd.entity.Role;
import com.ydgk.crowd.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author kfstart
 * @descrption
 * @create 2020-07-03 9:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestRoleMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testMethod(){
        //向数据库中插入多条记录
//        for(int i = 0; i < 400; i++){
//            roleMapper.insert(new Role(null,"roleName"+i));
//        }
    }

}
