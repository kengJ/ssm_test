package com.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component 
public class LogInterceptor {//
	@Pointcut("execution(* com.controller.*Controller.*(..))")
	public void test(){}
	
	@Before("test()") 
    public void beforMethod(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String methodName = joinPoint.getSignature().getName();
//        List<Object> args = Arrays.asList(joinPoint.getArgs());
		String remoteAddr = "";
        remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (remoteAddr == null || "".equals(remoteAddr)) {
            remoteAddr = request.getRemoteAddr();
        }
        System.out.println("访问ip:"+remoteAddr);
    }
	
}
