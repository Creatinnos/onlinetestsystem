package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;

public class QuestionDAO {

	
	/*public Customer findByCustomerId(int custId){
		 
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
	 
		Customer customer = (Customer)getJdbcTemplate().queryForObject(
				sql, new Object[] { custId }, new CustomerRowMapper());
			
		return customer;
	}*/
	
	public List<Question> findAll(){
		
		return BusinessObject.findAll(Question.class);
		
	}
}
