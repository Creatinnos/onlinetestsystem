package com.creatinnos.onlinetestsystem.daocustomization;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class CreateConnection {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BusinessObject.class.getName());

	private static JdbcTemplate con;

	public void setCon(JdbcTemplate con) {
		CreateConnection.con = con;
	}

	private CreateConnection() {
	}

	public static JdbcTemplate getConnection() {
		return con;
	}

}
