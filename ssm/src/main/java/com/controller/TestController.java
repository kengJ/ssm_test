package com.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.User;
import com.service.IUserService;

@Controller
@RequestMapping(value="/Test")
public class TestController {

	@Resource
    private IUserService userService;
	
	@RequestMapping(value="Login.do")
	@ResponseBody
	public User Login(String UserName,String Password){
		User User = userService.getUserByUserNameAndPassword(UserName, Password);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 3);
		User.setLastation(calendar.getTime());
		return User;
	}
	
}
