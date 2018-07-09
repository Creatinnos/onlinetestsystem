package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.Constant.TableConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.daocustomization.ExecuteQuery;
import com.creatinnos.onlinetestsystem.model.Question;
import com.creatinnos.onlinetestsystem.model.QuestionList;

import javafx.scene.control.Tab;

public class QuestionDao extends NamedParameterJdbcDaoSupport{
	static Logger log = Logger.getLogger(QuestionDao.class.getName());

	ExecuteQuery executeQuery =new ExecuteQuery();

	public ArrayList<Integer> saveQuestion(QuestionList questionss)
	{
		List<Question> questions=questionss.getQuestion(); 
		ArrayList<Integer> questionIdList = new ArrayList<Integer>();
		for(int i=0;i<questions.size();i++)
		{
			final Question question=questions.get(i);
			final String questionQuery="insert into "+TableConstants.QUESTION+" (QUESTION,CATEGORY,OPTIONTYPE,"+
					"OPTION1,OPTION2,OPTION3,OPTION4,CORRECTOPTION,ISNEGATIVEAPPLICABLE,UPLOADDATE) VALUES (?,?,?,?,?,?,?,?,?,?)";
			log.info(questionQuery);

			KeyHolder holder = new GeneratedKeyHolder();
			CreateConnection.getConnection().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(questionQuery, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, question.getQuestion());
					ps.setString(2, question.getCategory());
					ps.setString(3, question.getOptionType());
					ps.setString(4, question.getOption1());
					ps.setString(5, question.getOption2());
					ps.setString(6, question.getOption3());
					ps.setString(7, question.getOption4());
					ps.setString(8, question.getCorrectOption());
					//for now 
					ps.setString(9, "1");
					ps.setString(10, question.getUploadDate()!=null ? question.getUploadDate() : (new Date()).toString());
					return ps;
				}
			}, holder);
			
			final int questionId = holder.getKey().intValue();
			question.setId(questionId+"");
			questionIdList.add(Integer.parseInt(questionId+""));
		}
		return questionIdList;
	}


	private QuestionList executeFetch(String query) {
		log.info(query);
		QuestionList  list=new QuestionList();
		try {
			List<Map<String, Object>> maps=CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if(maps!=null && maps.size()>0)
			{
				for(int i=0;i<maps.size();i++)
				{
					Map<String, Object> map=maps.get(i);
					Question question=new Question();
					for(String st:map.keySet())
					{
						switch(st)
						{
						case "QUESTIONID":
							question.setId(""+map.get(st));
							break;
						case "QUESTION":
							question.setQuestion(""+map.get(st));
							break;
						case "CATEGORY":
							question.setCategory(""+map.get(st));
							break;
						case "OPTIONTYPE":
							question.setOptionType(""+map.get(st));
							break;
						case "OPTION1":
							question.setOption1(""+map.get(st));
							break;
						case "OPTION2":
							question.setOption2(""+map.get(st));
							break;
						case "OPTION3":
							question.setOption3(""+map.get(st));
							break;
						case "OPTION4":
							question.setOption4(""+map.get(st));
							break;
						case "CORRECTOPTION":
							question.setCorrectOption(""+map.get(st));
							break;
						/*case "ISNEGATIVEAPPLICABLE":
							question.setType(""+map.get(st));
							break;*/
						case "UPLOADDATE":
							question.setUploadDate(""+map.get(st));
							break;
						}
					}
					list.addQuestion(question);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return list;
	}

	public QuestionList fetchQuestion() {
		String query="select * from question";
		return executeFetch(query);
	}
	public QuestionList fetchQuestion(String query) {
		return executeFetch(query);
	}
	
	public ArrayList<Integer> updateQuestion(QuestionList questionss)
	{
		List<Question> questions=questionss.getQuestion(); 
		ArrayList<Integer> questionIdList = new ArrayList<Integer>();
		for(int i=0;i<questions.size();i++)
		{
			final Question question=questions.get(i);
			final String questionQuery="UPDATE "+TableConstants.QUESTION+" SET QUESTION=?, CATEGORY=?, OPTIONTYPE=?,"+
					"OPTION1=?,OPTION2=?,OPTION3=?,OPTION4=?,CORRECTOPTION=?,ISNEGATIVEAPPLICABLE=?,"
					+ "UPLOADDATE= ? WHERE QUESTIONID=?";
			log.info(questionQuery);

			KeyHolder holder = new GeneratedKeyHolder();
			CreateConnection.getConnection().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(questionQuery, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, question.getQuestion());
					ps.setString(2, question.getCategory());
					ps.setString(3, question.getOptionType());
					ps.setString(4, question.getOption1());
					ps.setString(5, question.getOption2());
					ps.setString(6, question.getOption3());
					ps.setString(7, question.getOption4());
					ps.setString(8, question.getCorrectOption());
					ps.setString(9, "1");
					ps.setString(10, question.getUploadDate()!=null ? question.getUploadDate() : (new Date()).toString());
					ps.setString(11, question.getId());
					return ps;
				}
			}, holder);
			
			final int questionId = holder.getKey().intValue();
			question.setId(questionId+"");
			questionIdList.add(Integer.parseInt(questionId+""));
		}
		return questionIdList;
	}
	
	private boolean deleteQuestion(String query, String questionId)
	{
		System.out.println(CreateConnection.getConnection().update(query,questionId));
		return true;
	}
	
	public boolean deleteQuestion(String questionId) {
		String sql="DELETE FROM "+TableConstants.QUESTION+" WHERE QUESTIONID=?";
		return deleteQuestion(sql,questionId);
	}
	
}
