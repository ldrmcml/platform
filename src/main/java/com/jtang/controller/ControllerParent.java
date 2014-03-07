/**
 * 
 */
package com.jtang.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.jtang.model.Storage;
import com.jtang.model.User;

/**
 * @author Administartor
 *
 */
public class ControllerParent {

	/**
	 * 将当前登录用户的有权限的仓库列表保存到试图当中供显示
	 * @param request
	 * @param mv
	 * @return
	 */
	protected ModelAndView setStorageList(HttpServletRequest request,ModelAndView mv){
		if(request.getSession().getAttribute("storageList")!=null){
			mv.addObject("storageList",request.getSession().getAttribute("storageList"));
		}
		return mv;
	}
	
	protected ModelAndView setWorkStorage(HttpServletRequest request,ModelAndView mv){
		if(request.getSession().getAttribute("workStorage")!=null){
			mv.addObject("workStorage",request.getSession().getAttribute("workStorage"));
		}
		return mv;
	}
	
	protected String getWorkStorageId(HttpServletRequest request){
		HttpSession hs = request.getSession();
		
		Object storageId = hs.getAttribute("workStorage");
		
		return storageId==null?"":storageId.toString();
		
	}
	
     /**
	 * 登陆验证
	 * @param request
	 * @return
	 * 	    0  未登录
	 *      1  已登录
	 */
	protected int checkLogin(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");
		if(user == null)
			return 0;
		else 
			return 1;
	}
	protected HashMap<String,Float> getMaxMinTemp(HttpServletRequest request){
		if(request.getSession().getAttribute("storageList")==null || request.getSession().getAttribute("workStorage")==null){
			return null;
		}
		HashMap<String,Float>  maxMin = new HashMap<String,Float> ();
		int workStorageId = (Integer) request.getSession().getAttribute("workStorage");
		List<Storage> storageList = (List<Storage>) request.getSession().getAttribute("storageList");
		
		for(int i=0;i<storageList.size();i++){
			if(workStorageId == storageList.get(i).getId()){
				maxMin.put("max", storageList.get(i).getMaxTemp());
				maxMin.put("min", storageList.get(i).getMinTemp());
				return maxMin;
			}
		}
		return null;
	}
	
}
