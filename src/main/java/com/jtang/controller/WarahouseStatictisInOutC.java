package com.jtang.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSON;
import com.jtang.model.InOut;
import com.jtang.service.IInOutService;

public class WarahouseStatictisInOutC implements Controller{
	
	private IInOutService inoutService;

	public IInOutService getInoutService() {
		return inoutService;
	}

	public void setInoutService(IInOutService inoutService) {
		this.inoutService = inoutService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		if(action == null){return null;}
		
		//处理返回中文乱码
		response.setCharacterEncoding("utf-8");         
		response.setContentType("text/html; charset=utf-8"); 
		
		PrintWriter  out = response.getWriter();
		int storageId = (Integer) request.getSession().getAttribute("workStorage");
		String json="";

		if(action.equals("statictis_in"))
		{
			//统计今日所有入库
			Map<String,Object> map =  new HashMap<String,Object>();
			map.put("all", getInStatisticToday(storageId));
			map.put("groupByTime", getInTodayGroupByTime(storageId));
			json = JSON.toJSONString(map,true);
			
		}
		else if(action.equals("search_in"))
		{
			//搜索某些入库
			int searchProId = Integer.parseInt(request.getParameter("SearchProId"));
			String searchPersonId = request.getParameter("searchPersonId");
			String searchTime = "";
			
			json = searchInToday(searchProId,searchPersonId,searchTime,storageId);
		}
		else if(action.equals("statictis_out"))
		{
			
		}
		else if(action.equals("search_out"))
		{
			
		}
		else if(action.equals("search_all"))
		{
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			int proId = Integer.parseInt(request.getParameter("proSearchId")) ;
			int personId = Integer.parseInt(request.getParameter("personSearchId"));
			int searchAction = Integer.parseInt(request.getParameter("searchAction"));
			List<InOut> map = searchAll(searchAction,startTime, endTime, proId, storageId, personId );
			json = JSON.toJSONString(map,true);
		}
		else if(action.equals("search_allforchart"))
		{
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			int proId = Integer.parseInt(request.getParameter("proSearchId")) ;
			int personId = Integer.parseInt(request.getParameter("personSearchId"));
			int searchAction = Integer.parseInt(request.getParameter("searchAction"));		
			Map<String,Object> list = searchGroupByTimeAndPro(searchAction, startTime, endTime, proId, storageId, personId) ;
			json = JSON.toJSONString(list,true);
		}
		

		out.write(json);
		out.flush();
		out.close();
		
		return null;
	}
	
	private Map<String,Object> searchGroupByTimeAndPro(int action,String startTime,String endTime,int proId,int storageId,int personId)
	{
		List<Map<String,Object>> list = null;
		Map<String,Object> map = new HashMap<String,Object>();

		if(action == 1)
			list = inoutService.queryInGroupByTimeAndPro(storageId, startTime, endTime, proId, personId);
		else
			list = inoutService.queryOutGroupByTimeAndPro(storageId, startTime, endTime, proId, personId);
		
		int length = list.size();
		String[] times = new String[length];
		for(int i = 0;i < length;i++)
		{
			times[i] = (String) list.get(i).get("time");
			String name = (String) list.get(i).get("name");
		    if(!map.containsKey(name))
		    {
		    	//新增项目
		    	int[] datas = new int[length];
		    	datas[i] = ((BigDecimal)list.get(i).get("count")).intValue();
		    	map.put(name, datas);
		    }
		    else {
		    	//已有项目添加数据
		    	((int [])map.get(name))[i] =  ((BigDecimal)list.get(i).get("count")).intValue();
		    }
		    
		    //添加时间数据
		    map.put("time", times);
		    
		}
		
		return map;
	}
	
	private List<InOut> searchAll(int action,String startTime,String endTime,int proId,int storageId,int personId)
	{
		List<InOut> list = null;
		if(action == 0)
			list = inoutService.queryOut(storageId, startTime, endTime, proId, personId);
		else if(action == 1)
			list = inoutService.queryIn(storageId, startTime, endTime, proId, personId);
		else
			list = inoutService.queryAll(storageId, startTime, endTime, proId, personId);
		return list;
	}
	
	private String searchInToday(int searchProId,String searchPersonId,String searchTime,int storageId)
	{
		List<InOut> list = inoutService.searchTodayIn(searchProId, searchPersonId, searchTime,storageId);
		return  JSON.toJSONString(list,true);
	}
	
	private Map<String,Object> getInStatisticToday(int storageId)
	{
		List<InOut> list = inoutService.queryTodayIn(storageId);
		int inCount = getInCountToday(storageId);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("inSum", inCount);
		return map;
	}
	
	private Map<String,Object> getInTodayGroupByTime(int storageId)
	{
		List<Map<String,Object>> map = inoutService.queryTodayInGroupByTime(storageId);
		int size = map.size();
		String[] times = new String[size];
		int[] counts = new int[size];
		for(int i = 0;i < size ;i++)
		{
			times[i] = (String) map.get(i).get("time");
			counts[i] = ((BigDecimal) map.get(i).get("count")).intValue();			
		}
		
		Map<String,Object> data =  new HashMap<String,Object>();
		data.put("times", times);
		data.put("counts", counts);
			
		return data;
	}
	
	private String getOutStatisticToday(int storageId)
	{
		List<InOut> list = inoutService.queryTodayOut(storageId);
		int outCount = getOutCountToday(storageId);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("outSum", outCount);
		return JSON.toJSONString(map,true);
	}
	
	private int getInCountToday(int storageId)
	{
		return inoutService.countInToday(storageId);
	}
	
	private int getOutCountToday(int storageId)
	{
		return inoutService.countOutToday(storageId);
	}
}
