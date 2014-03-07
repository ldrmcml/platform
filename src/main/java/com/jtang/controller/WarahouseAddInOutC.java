package com.jtang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.jtang.service.IInOutService;
import com.jtang.service.IProService;
import com.jtang.service.IStorageService;

public class WarahouseAddInOutC implements Controller{
	
	public IInOutService getInoutService() {
		return inoutService;
	}

	public void setInoutService(IInOutService inoutService) {
		this.inoutService = inoutService;
	}

	private IInOutService inoutService;
	private IProService proService;
	private IStorageService storageService;

	public IStorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(IStorageService storageService) {
		this.storageService = storageService;
	}

	public IProService getProService() {
		return proService;
	}

	public void setProService(IProService proService) {
		this.proService = proService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		addInOutOpe(request,response);
		return null;
	}

	private void addInOutOpe(HttpServletRequest request,HttpServletResponse response)
	{
		String data = request.getParameter("in_out");
		//System.out.println(data);
		Map<String, Object> inOutDatas = JSON.parseObject(data, new TypeReference<Map<String, Object>>() {});
		int action=(Integer)inOutDatas.get("inOutAction");
		String personId=(String) inOutDatas.get("personId");
		int proId=Integer.parseInt((String) inOutDatas.get("proId"));
		int cardNum = (Integer) inOutDatas.get("cardNum");
		
		//数据库中获取商品单位
		int rfidcount = proService.getPro(proId).getUnit();
		//仓库id
		int storageId = (Integer) request.getSession().getAttribute("workStorage");
		
		inoutService.addInOuts(cardNum, action, personId, rfidcount,
				(JSONArray) inOutDatas.get("cards"), (JSONArray) inOutDatas.get("cardsTime"),proId,storageId);
		
		if(action == 0)
			proService.minusProCount(proId, rfidcount * cardNum);
		else if(action == 1)
		{
			//更新商品信息
			proService.addProCount(proId, rfidcount * cardNum);
			//更新仓库库存信息
			storageService.addStorageUse(storageId, rfidcount * cardNum);
		}
	
		return;
	}
	
}
