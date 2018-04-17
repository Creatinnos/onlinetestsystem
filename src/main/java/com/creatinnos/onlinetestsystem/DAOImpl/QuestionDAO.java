package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.daocustomization.ExecuteQuery;
import com.creatinnos.onlinetestsystem.model.Question;

public class QuestionDao extends NamedParameterJdbcDaoSupport{
	static Logger log = Logger.getLogger(JdbcTemplate.class.getName());

	ExecuteQuery executeQuery =new ExecuteQuery();

	public boolean saveQuestion(final Question question)
	{

		String choice="";

		for(int i=0;i<question.getChoice().size();i++)
		{
			choice=choice+"\""+question.getChoice().get(i)+"\",";
		}
		if(choice.length()>0)
		{
			choice=choice.substring(0, choice.length()-1);
		}
		final String questionQuery="insert into question (QUESTION, CHOICETYPE, UPLOADDATE) VALUES (?,?,?)";
		log.info(questionQuery);

		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(questionQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, question.getQuestion());
				ps.setString(2, question.getType());
				ps.setString(3, question.getUploadDate()!=null ? question.getUploadDate() : (new Date()).toString());
				return ps;
			}
		}, holder);

		final int questionId = holder.getKey().intValue();

		final String choiceQuery="insert into choices(QUESTIONID,CHOICE, ISANSWER) VALUES (?,?,?)";
		log.info(questionQuery);

		final List<Map<String, Object>> maps=question.getChoice();
		for(int i=0;i<maps.size();i++)
		{
			final int j=i;
			holder = new GeneratedKeyHolder();
			CreateConnection.getConnection().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(choiceQuery, Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, questionId);
					ps.setString(2, (String) maps.get(j).get("choice"));
					ps.setBoolean(3, (boolean) maps.get(j).get("answer"));
					return ps;
				}
			}, holder);
		}
		return true;
	}


	private List<Question> executeFetch(String query) {
		List<Question>  list=new ArrayList<>();
		try {
			List<Map<String, Object>> maps=CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if(maps!=null && maps.size()>0)
			{
				for(int i=0;i<maps.size();i++)
				{
					Map<String, Object> map=maps.get(i);
					Question question=new Question();
					int questionId=0;
					for(String st:map.keySet())
					{
						switch(st)
						{
						case "QUESTIONID":
							questionId=Integer.parseInt(map.get(st)+"");
							question.setId(""+map.get(st));
							break;
						case "QUESTION":
							question.setQuestion(""+map.get(st));
							break;
						case "CHOICETYPE":
							question.setType(""+map.get(st));
							break;
						case "UPLOADDATE":
							question.setUploadDate(""+map.get(st));
							break;
						}
					}
					
					String choiceQuery="select CHOICE,ISANSWER from choices where questionid="+questionId;
					List<Map<String, Object>> choices=CreateConnection.getConnection().queryForList(choiceQuery);
					log.info(choiceQuery+"-->");
					log.info(choices);
					question.setChoice(choices);
					list.add(question);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return list;
	}

	public List<Question> fetchQuestion() {
		String query="select * from question";
		return executeFetch(query);
	}
}
