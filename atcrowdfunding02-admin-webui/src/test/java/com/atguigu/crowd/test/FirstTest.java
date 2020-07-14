package com.atguigu.crowd.test;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SocketUtils;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;

//Spring给 junit 提供的运行类，可以在此注入IOC容器中的组件
@RunWith(SpringJUnit4ClassRunner.class)
//加载 Spring 配置文件的注解
@ContextConfiguration(locations="classpath:spring-persist-mybatis.xml")
public class FirstTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AdminMapper adminMapper;
	
	
	
	//测试数据源的连接
	@Test
	public void connectionTest() throws SQLException{
		Connection connection = dataSource.getConnection();

	}
	
	//测试Spring整合MyBatis，实现 注入AdminMapper接口，并可以调用其代理对象的方法
	@Test
	public void sqlSessionTest(){
		Admin admin = new Admin(null, "mason123", "123", "mason", "mason@at.com", null);
		int count = adminMapper.insert(admin);
		Logger logger = LoggerFactory.getLogger(getClass());
		logger.debug("*************debug、测试对象count**********"+count);
	}
}
