package com.creatinnos.onlinetestsystem.daocustomization;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class ExecuteQuery {

	static Logger log = Logger.getLogger(JdbcTemplate.class.getName());
	
	public boolean executeInsert(String query) {
		int result = 0;
		try {
			CreateConnection.getConnection().update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		if(result!=0)
		{
			return true;
		}
		return false;
	}

	public static int executeUpdate(String query) {
		int result = 0;
		try {
			CreateConnection.getConnection().update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return result;
	}

	public static int executeDelete(String query) {
		int result = 0;
		try {
			CreateConnection.getConnection().update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return result;
	}
}
