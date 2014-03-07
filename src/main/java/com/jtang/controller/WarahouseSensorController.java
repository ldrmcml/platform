package com.jtang.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSON;
import com.jtang.service.IRFIDService;

public class WarahouseSensorController extends ControllerParent implements Controller{
	
	private IRFIDService rfidService;
	public IRFIDService getRfidService() {
		return rfidService;
	}

	public void setRfidService(IRFIDService rfidService) {
		this.rfidService = rfidService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv=null;
		//处理返回中文乱码
		response.setCharacterEncoding("utf-8");         
		response.setContentType("text/html; charset=utf-8"); 
		
		//验证登陆
		if(this.checkLogin(request) == 1){
			if(request.getMethod().equalsIgnoreCase("get")){
				String action=request.getParameter("action");
				System.out.println(action);
				if(action==null){
					//单纯获取页面
					mv = new ModelAndView("dashboard");
					mv.addObject("content_page", "/WEB-INF/view/frid_sensor.vm");
					String[] locations=request.getServletPath().replaceAll(".html", "").split("/");
					mv.addObject("locations", locations);
					mv.addObject("location_info", "仓库管理平台");
					mv = setStorageList(request, mv);
					mv = setWorkStorage(request, mv);
				} else if(action.equals("scanReader")) {
					
					//扫描RFID
					HashMap<String,Object> data = rfidService.queryActiveTags(0);
					String dataJson=JSON.toJSONString(data, true);
					//System.out.println(dataJson);
					PrintWriter out = response.getWriter();
					out.print(dataJson);
					out.flush();
					out.close();
					
				} else if(action.equals("getstate")) {
					//get state
					byte[] info=rfidService.getReaderMode();
					for (int i=0;i<12;i++)
						System.out.print(info[i]);
				}
				else	;
				
			}
			else if (request.getMethod().equalsIgnoreCase("post")){
				String action=request.getParameter("action");
				if(action.equalsIgnoreCase("open")){
					PrintWriter out = response.getWriter();
					out.print(openReader());
					out.flush();
					out.close();
				}
				else if(action.equalsIgnoreCase("set"))
					setReader(request,response);
				else if(action.equalsIgnoreCase("close"))
				{
					PrintWriter out = response.getWriter();
					out.print(closeReader());
					out.flush();
					out.close();
				}	
			}
		}
		else
		{
			//重定向到登录页面
			response.sendRedirect(request.getContextPath());
		}
		
		
		return mv;	
	}
	
	private String openReader(){
		//打开串口
		Map<String, Object> map=rfidService.openPort();
		
		int res = (Integer)map.get("res");
		if(res == 0)
			map.put("msg", "打开成功");
		else if(res == 48 )
			map.put("msg", "通讯错误");
		else 
			map.put("msg", "错误码："+res);
		return JSON.toJSONString(map,true);
	
	}
	
	private int  closeReader(){
		//打开串口
		return rfidService.closePort();
	}
	
	private void setReader(HttpServletRequest request,HttpServletResponse response){
		
		//判断串口是否已经打开
		
		//get sensor set
		//int portAdr=request.getParameter("portAdr")==null?0:Integer.parseInt(request.getParameter("portAdr")); 
		int soundSet=request.getParameter("soundSet")==null?0:Integer.parseInt(request.getParameter("soundSet"));
		int readMode=request.getParameter("readMode")==null?0:Integer.parseInt(request.getParameter("readMode"));
		int readArea=request.getParameter("readArea")==null?0:Integer.parseInt(request.getParameter("readArea"));
		
		rfidService.setReadMode(readMode);
		rfidService.setSoundMode(soundSet);
		rfidService.setMemArea(readArea);
		
		
	}

}
