package com.creatinnos.onlinetestsystem.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;

public class MyServletContextListener implements ServletContextListener {

	static Logger log = Logger.getLogger(BusinessObject.class.getName());
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized");
		System.out.println(CreateConnection.getConnection());
	}

}