package com.jtang.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;

import jt.sensordata.main.StartDataCenter;

/**
 * Servlet implementation class Test
 */
public class SensorDataCenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SensorDataCenter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		/*ServletContext sc = config.getServletContext();
		WebApplicationContext ctx = (WebApplicationContext)sc.getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
		StartDataCenter sdc = ctx.getBean("startDataCenter", StartDataCenter.class);
		sdc.comNumber = config.getInitParameter("comNumber");
		sdc.startAll(null);*/
	}

}
