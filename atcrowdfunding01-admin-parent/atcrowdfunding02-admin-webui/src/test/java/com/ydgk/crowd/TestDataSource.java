package com.ydgk.crowd;

import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-28 16:09
 */
// Spring 整合 Junit 提供的运行器类
@RunWith(SpringJUnit4ClassRunner.class)
// 加载 Spring 配置文件的注解
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestDataSource {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    //1、 获取日志打印器
    Logger logger = LoggerFactory.getLogger(TestDataSource.class);

    @Test
    public void testAdminUpdate(){
        Integer integer = adminService.updateAdmin(
                new Admin(1, "zhangsan", "123",
                        "张三", "zhangsan@aliyun.com", null));
        logger.info("数据库影响行数为："+integer+" 行");
    }

    /*
    使用日志：
       日志门面： SLF4J
       日志实现： logback

       需要对日志进行统一，统一项目中使用日志的技术。
     */
    @Test
    public void testLogging(){
        // 自己打印日志

        //2、 通过logger打印日志
        //  日志输出级别，可以指定日志输出的级别，指定之后，在指定级别以下的日志将不会打印
        //   默认输出日志几倍是debug
        logger.trace("追踪日志...");// 表示打印的日志是一个追踪日志
        logger.debug("调试日志...");// 表示打印的是调试日志
        logger.info("信息日志...");// 表示答应的日志是一个信息日志
        logger.warn("警告日志...");// 表示打印的日志是警告日志
        logger.error("错误日志...");// 表示打印的日志是错误日志
    }

    @Test
    public void testMethod() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

//    @Test
//    public void testMethod1(){
//        System.out.println(adminService.getAdminList());
//    }

}

