package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//return HandlerInterceptor.super.preHandle(request, response, handler);
		//response.setCharacterEncoding("utf-8");
		String token=request.getHeader("accessToken");
		System.out.println("coming");
		if(null!=token) {
			boolean result=JwtUtil.verify(token);
			if(result) {
				return true;
			}
		}
		response.getWriter().write("token error");
		return false;
	}
}
