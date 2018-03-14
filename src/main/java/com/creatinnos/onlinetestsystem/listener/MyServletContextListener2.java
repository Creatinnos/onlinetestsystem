package com.creatinnos.onlinetestsystem.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener2 implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("MyServletContextListener2contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("MyServletContextListener2contextInitialized");
	}

}