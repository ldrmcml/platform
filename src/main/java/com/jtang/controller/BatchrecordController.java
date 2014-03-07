package com.jtang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BatchrecordController implements Controller {

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				ModelAndView mv = null;
				
				if(request.getMethod().equalsIgnoreCase("get")){
					String action=request.getParameter("action");
					System.out.println(action);
					if(action == null)
					{
						//简单获取页面
						mv = new ModelAndView("dashboard");
						mv.addObject("content_page", "/WEB-INF/view/batchrecord.vm");
						String[] locations=request.getServletPath().replaceAll(".html", "").split("/");
						mv.addObject("locations", locations);
						mv.addObject("location_info", "溯源管理平台");
					}
					else	;
				}
				
				return mv;
	}

}
