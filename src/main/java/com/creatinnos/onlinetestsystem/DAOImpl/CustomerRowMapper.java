package com.creatinnos.onlinetestsystem.DAOImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;

public class CustomerRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question = BusinessObject.create(Question.class);
		return question;
	}
	
}