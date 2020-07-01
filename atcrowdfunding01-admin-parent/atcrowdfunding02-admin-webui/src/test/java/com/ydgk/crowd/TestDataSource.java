package com.ydgk.crowd;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydgk.crowd.entity.Admin;
import com.ydgk.crowd.mapper.AdminMapper;
import com.ydgk.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
//    @Autowired
//    private SqlSessionFactoryBean sqlSessionFactoryBean;
    @Autowired
    private AdminMapper adminMapper;
    //1、 获取日志打印器
    Logger logger = LoggerFactory.getLogger(TestDataSource.class);

    @Test
    public void testPageHelper(){
        // 在查询之前调用这个方法，以后的查询会自带分页  pageNum: 页码， pageSize : 每一页多少条
        PageHelper.startPage(2,10);
        List<Admin> admins = adminMapper.selectByExample(null);

        // PageInfo   可以将查询到的集合封装到PageInfo中，这样pageInfo就保存了很多的分页信息
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        System.out.println("下一页页码为："+adminPageInfo.getNextPage());
        System.out.println("上一页页码为："+adminPageInfo.getPrePage());
        System.out.println("是否有上一页："+adminPageInfo.isHasPreviousPage());
        System.out.println("是否有下一页："+adminPageInfo.isHasNextPage());
        System.out.println("当前页是多少页："+adminPageInfo.getPageNum());
        System.out.println("总共的记录条数为："+adminPageInfo.getTotal());
        System.out.println("每页显示条数："+adminPageInfo.getSize());
        List<Admin> list = adminPageInfo.getList();
        for(Admin admin : list){
            System.out.println(admin);
        }
        // 用来返回导航页面显示的页码数
        int[] navigatepageNums = adminPageInfo.getNavigatepageNums();
        for(int i : navigatepageNums){
            System.out.print(i + ",");
        }
        System.out.println();

    }

    // 向数据库中插入多条记录，方便完成管理员维护功能
//    @Test
//    public void insertMoreAdmin() throws SQLException {
//        Connection connection = dataSource.getConnection();
//        String sql = "INSERT INTO t_admin (login_acct,user_pswd,user_name,email) VALUES (?,?,?,?)";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        for(int i = 0 ; i < 1000; i++){
//            statement.setObject(1,"logAcct"+i);
//            statement.setObject(2,"E10ADC3949BA59ABBE56E057F20F883E");
//            statement.setObject(3,"userName"+i);
//            statement.setObject(4,"userName"+i+"@aliyun.com");
//            statement.executeUpdate();
//        }
//        statement.close();
//        connection.close();
//    }

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

