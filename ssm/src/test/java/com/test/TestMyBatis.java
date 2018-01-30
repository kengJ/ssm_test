package com.test;

import javax.annotation.Resource;
import org.junit.Test;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import com.model.User;
import com.service.IUserService;
	
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService = null;
	
	@Test
	public void test() {
		// TODO Auto-generated method stub
		User user = userService.getUserById(1);
		logger.info(user);
		logger.info("user转为json");
		logger.info(JSON.toJSONString(user));
	}
}
