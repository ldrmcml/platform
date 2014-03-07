package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.support.RequestContextUtils;

public class FController implements Controller {
	private Fjs fjs;
	
	public void setFjs(Fjs fjs) {
		this.fjs = fjs;
	}


	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		WebApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
        ModelAndView mv = new ModelAndView("hello", "name", this.fjs.getName());
        
        org.springframework.web.servlet.DispatcherServlet aa = new org.springframework.web.servlet.DispatcherServlet();
        
        String bb = new String("aaa");
        
        return mv;
	}
}
