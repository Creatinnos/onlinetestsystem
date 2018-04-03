package com.creatinnos.onlinetestsystem.rest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;

@SuppressWarnings("serial")
public class CrunchifyServletExample extends HttpServlet
{
 
    public void init() throws ServletException
    {
          System.out.println("----------");
          System.out.println("---------- CrunchifyServletExample Initialized successfully ----------");
          System.out.println(CreateConnection.getConnection());
          System.out.println("----------");
    }
}