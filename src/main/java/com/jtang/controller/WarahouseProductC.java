package com.jtang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jtang.model.InOut;
import com.jtang.model.Product;
import com.jtang.model.Storage;
import com.jtang.model.User;
import com.jtang.service.IProService;
import com.jtang.service.IStorageService;

public class WarahouseProductC extends ControllerParent implements Controller{
	
	private IProService proService;
	private IStorageService storageService;
	
	public IProService getProService() {
		return proService;
	}

	public IStorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(IStorageService storageService) {
		this.storageService = storageService;
	}

	public void setProService(IProService proService) {
		this.proService = proService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv = null;
		
		if(this.checkLogin(request) == 1)
		{
			String action = request.getParameter("action");
			
			//处理返回中文乱码
			response.setCharacterEncoding("utf-8");         
			response.setContentType("text/html; charset=utf-8"); 
			
			if(action == null)
			{
				//刷新页面
				mv = new ModelAndView("dashboard");
				String[] locations=request.getServletPath().replaceAll(".html", "").split("/");
				mv.addObject("locations", locations);
				mv.addObject("location_info", "仓库管理平台");
				mv.addObject("content_page", "/WEB-INF/view/product.vm");
				
				//获取当前仓库货物
				int storageId = (Integer) request.getSession().getAttribute("workStorage");
				mv.addObject("proList",getAllPro(storageId));
				
				//必须进行登陆验证，保证session
				//获取当前仓库信息
				String enterpriseId = ((User) request.getSession().getAttribute("user")).getEnterpriseId();
				mv.addObject("storage",getStorage(storageId,enterpriseId));
				
				mv = setStorageList(request, mv);
				mv = setWorkStorage(request, mv);
				
			}
			else if(action.equals("add"))
			{
				//添加商品
			}
			else if(action.equals("edit"))
			{
				//编辑商品
				int id =  Integer.parseInt(request.getParameter("id"));
				String name =  request.getParameter("name");
				String msg = request.getParameter("msg");
				int unit = Integer.parseInt(request.getParameter("unit"));
				int storageId = (Integer) request.getSession().getAttribute("workStorage");
				this.editPro(name, msg, unit, id,storageId);
				
			}
			
		}
		else 
		{
			//重定向到登录页面
			response.sendRedirect(request.getContextPath());
		}
		
		return mv;
	}
	
	private int editPro(String name,String msg,int unit,int id,int storageId)
	{
		return proService.updatePro(id, storageId, name, msg, unit);
	}
	
	private List<Product> getAllPro(int storageId)
	{
		return proService.getAllPro(storageId);
	}
	
	private Storage getStorage(int storageId,String enterpriseId)
	{
		List<Storage> data = storageService.getStorageListByIds(new String[]{storageId+""}, enterpriseId);
		if(data.size() > 0)
		{
			return data.get(0);
		}
		else 
			return null;
	}
	

}
