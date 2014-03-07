package com.jtang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ErrorController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("content_page", "/WEB-INF/view/404.vm");
		mv.addObject("location", "Location");
		mv.addObject("location_info", "location_info");
		return mv;
	}

}
