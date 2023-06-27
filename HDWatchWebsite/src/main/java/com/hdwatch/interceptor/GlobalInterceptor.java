package com.hdwatch.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hdwatch.service.CategoriesService;


@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoriesService categoriesService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getHeader("X-Requested-With") == null) {
			request.setAttribute("cates", categoriesService.findAll());
		}
	}
}
