package com.jtang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jtang.service.IProService;
import com.jtang.service.IUserService;

public class WarahouseInOutController extends ControllerParent implements Controller{
	
	private IProService proService;
	private IUserService userService;

	public IProService getProService() {
		return proService;
	}

	public void setProService(IProService proService) {
		this.proService = proService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv = null;
		
		//验证登陆
		if(this.checkLogin(request) == 1){
			String action = request.getParameter("action");
			String path=request.getServletPath();
			
			if(action == null)
			{
				mv = new ModelAndView("dashboard");
				String[] locations=request.getServletPath().replaceAll(".html", "").split("/");
				mv.addObject("locations", locations);
				mv.addObject("location_info", "仓库管理平台");
				int storageId = (Integer) request.getSession().getAttribute("workStorage");
				mv.addObject("allPros", proService.getAllPro(storageId));
				mv.addObject("allUsers",userService.getAllUser());
				
				if(path.endsWith("in"))
				{	
					mv.addObject("content_page", "/WEB-INF/view/in.vm");
				}
				else if(path.endsWith("out"))
				{	
					mv.addObject("content_page", "/WEB-INF/view/out.vm");
				}
				
				mv = setStorageList(request, mv);
				mv = setWorkStorage(request, mv);
			}
		}
		else
		{
			//重定向到登录页面
			response.sendRedirect(request.getContextPath());
		}

		return mv;
	}
	
	

}
