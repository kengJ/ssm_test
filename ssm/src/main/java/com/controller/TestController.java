package com.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public User Login(HttpSession session,String UserName,String Password,@RequestParam(value="IsLogin", defaultValue="1",required=false) String IsLogin){
		Map<String,User> UserList = session.getAttribute("UserList") != null ? (Map<String,User>) session.getAttribute("UserList") : new HashMap<>();
		if(UserList.get(UserName) != null){
			//判断日期和密码是否正确
			User User = UserList.get(UserName);
			boolean Check = User.getPassword().equals(Password)&&User.getLastation().compareTo(new Date())>1;
			if(IsLogin.equals("1")){
				Check = User.getPassword().equals(Password);
			}
			if(Check){
				User = UpdateUserLastation(User);
				session.setAttribute(UserName, User);
				return User;
			}else{
				return null;
			}
		}
		User User = userService.getUserByUserNameAndPassword(UserName, Password);
		if(User != null){//账号密码正确
			//检查是否过期
			Date Lastation = User.getLastation();
			if(Lastation.compareTo(new Date())<1&&!IsLogin.equals("1")){
				return null;
			}		
			UserList.put(UserName, UpdateUserLastation(User));
			session.setAttribute("UserList", UserList);
		}
		return User;
	}
	
	public User UpdateUserLastation(User User){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, 3);
		User.setLastation(calendar.getTime());
		return User;
	}
}
