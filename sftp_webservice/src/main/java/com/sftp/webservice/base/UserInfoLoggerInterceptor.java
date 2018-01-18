package com.sftp.webservice.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInfoLoggerInterceptor extends HandlerInterceptorAdapter
{
	private static final Logger OUT = LoggerFactory.getLogger(UserInfoLoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		request.setAttribute("startTime", System.currentTimeMillis());
		String threadName = Thread.currentThread().getName();
		request.setAttribute("threadName", threadName);
		Thread.currentThread().setName(threadName + "[ " + request.getRemoteAddr() + " - " + request.getRemoteUser() + " ]");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		OUT.info("Request: {} Completed in {} ms", request.getRequestURI(), (System.currentTimeMillis() - (Long) request.getAttribute("startTime")));
		Thread.currentThread().setName(request.getAttribute("threadName").toString());
		super.postHandle(request, response, handler, modelAndView);
	}
}
