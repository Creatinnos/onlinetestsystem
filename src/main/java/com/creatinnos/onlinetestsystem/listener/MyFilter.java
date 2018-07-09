package com.creatinnos.onlinetestsystem.listener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;  
  
public class MyFilter implements Filter{  
  
	static Logger log = Logger.getLogger(MyFilter.class.getName());
	
public void init(FilterConfig arg0) throws ServletException {}  
      
public void doFilter(ServletRequest req, ServletResponse resp,  
    FilterChain chain) throws IOException, ServletException {
	
	Map<String, String[]> s=((HttpServletRequest) req).getParameterMap();
	for (String str:s.keySet())
	{
		log.info(str +" --"+ s.get(str));
	}
	
	//SecurityUtil.getInstance().addToken(securityModal);
	
    chain.doFilter(req, resp);//sends request to next resource  
    }  
    public void destroy() {}  
}  