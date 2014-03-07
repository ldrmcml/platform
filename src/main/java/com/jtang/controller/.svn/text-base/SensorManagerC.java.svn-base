package com.jtang.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jtang.model.Sensor;
import com.jtang.model.Storage;
import com.jtang.service.ISensorService;
import com.jtang.service.IStorageService;



public class SensorManagerC extends ControllerParent implements Controller {

	public ISensorService sensorService;
	

	public ISensorService getSensorService() {
		return sensorService;
	}


	public void setSensorService(ISensorService sensorService) {
		this.sensorService = sensorService;
	}

	public IStorageService storageService;
	
	

	/**
	 * @return the storageService
	 */
	public IStorageService getStorageService() {
		return storageService;
	}


	/**
	 * @param storageService the storageService to set
	 */
	public void setStorageService(IStorageService storageService) {
		this.storageService = storageService;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("content_page", "/WEB-INF/view/sensorManager.vm");
		String[] locations=request.getServletPath().replaceAll(".html", "").split("/");
		mv.addObject("locations", locations);
		mv.addObject("location_info", "仓库管理平台");
		
		
		
		String storageId = getWorkStorageId(request);
		if(!storageId.equals("")){
			List<Sensor >  allSensors = sensorService.getSensorListByStorageId(Integer.parseInt(storageId));
			mv.addObject("allSensors",allSensors);
		}
		
		HttpSession hs = request.getSession();
		
		List<Storage> storageList = (List<Storage>) hs.getAttribute("storageList");
		
		mv.addObject("storageList",storageList);
		
		mv = setStorageList(request, mv);
		mv = setWorkStorage(request, mv);
		return mv;

	}

	

}
