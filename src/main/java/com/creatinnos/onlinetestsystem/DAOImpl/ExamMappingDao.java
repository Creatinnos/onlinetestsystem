package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.Constant.TableConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.daocustomization.ExecuteQuery;
import com.creatinnos.onlinetestsystem.model.NewExam;
import com.creatinnos.onlinetestsystem.model.Question;
import com.creatinnos.onlinetestsystem.model.QuestionList;

public class ExamMappingDao extends NamedParameterJdbcDaoSupport {
	static Logger log = Logger.getLogger(ExamMappingDao.class.getName());

	ExecuteQuery executeQuery = new ExecuteQuery();

	public boolean updateExamMapping(final NewExam newExam) {

		final String newExamQuery = "UPDATE " + TableConstants.EXAM
				+ " SET EXAMNAME=?,EXAMSTARTDATE=?,EXAMENDDATE=?,EXAMTIME=?,EXAMDURATION=?,PASSMARK=?,"
				+ "ISNEGATIVE=?,CATEGORY=?,SUBCATEGORY=?,SUBJECT=? WHERE EXAMID=?";
		
		log.info(newExamQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(newExamQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, newExam.getExamName());
				ps.setString(2, newExam.getExamStartDate());
				ps.setString(3, newExam.getExamEndDate());
				ps.setString(4, newExam.getExamTime());
				ps.setString(5, newExam.getExamDuration());
				ps.setDouble(6, newExam.getPassMark());
				ps.setBoolean(7, newExam.isNegativeMarkApplicable());
				ps.setString(8, newExam.getCategory());
				ps.setString(9, newExam.getSubCategory());
				ps.setString(10, newExam.getSubject());
				ps.setString(11, newExam.getExamId());
				return ps;
			}
		}, holder);

//		final int newExamId = holder.getKey().intValue();
		
		return true;
	}

	
	public boolean saveExamMapping(String examId, ArrayList<Integer>  questionIdList) {


		String query = "select * from "+ TableConstants.EXAMQUESTIONMAPPING +" where EXAMID="+examId;
		String fetchedRecord=executeFetch(query);
		String queryInsUpd= null;
		if(fetchedRecord==null)
		{
			queryInsUpd = "insert into " + TableConstants.EXAMQUESTIONMAPPING
					+ "(QUESTIONIDLIST,EXAMID) VALUES (?,?)";
		}
		else
		{
			String[]  questionIdListTemp=fetchedRecord.split(",");
			for(int i=0;i<questionIdListTemp.length;i++)
			{
				if (!questionIdListTemp[i].trim().equals("")) {
					questionIdList.add(Integer.parseInt(questionIdListTemp[i].trim()));
				}
			}
			queryInsUpd="UPDATE "+TableConstants.EXAMQUESTIONMAPPING+" set QUESTIONIDLIST=? WHERE EXAMID=?";
		}
		log.info(queryInsUpd);
		final String newExamQuery=queryInsUpd;
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(newExamQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, questionIdList.toString());
				ps.setString(2, examId);
				return ps;
			}
		}, holder);

//		final int newExamId = holder.getKey().intValue();
		
		return true;
	}

	private String executeFetch(String query) {
		log.info(query);
		String questionIdList=null;
		try {			
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					for (String st : map.keySet()) {
						switch (st) {
						case "QUESTIONIDLIST":
							String tempStr=((String) map.get(st));
							questionIdList = tempStr.trim().substring(1, tempStr.length()-1);
							break;
						}
					}
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return questionIdList;
	}

	public String fetchExamMapping(String examId) {
		String query = "select * from "+ TableConstants.EXAMQUESTIONMAPPING +" where EXAMID="+examId;
		return executeFetch(query);
	}
	public QuestionList fetchExamMappedQuestions(String examId) {
		String questions=fetchExamMapping(examId);
		String query = "select * from "+ TableConstants.QUESTION +" where QUESTIONID in ("+questions+")";
		QuestionDao dao=new QuestionDao();
		QuestionList questionList= dao.fetchQuestion(query);
		questionList.setExamId(examId);
		return questionList;
	}
	
	public void deleteExamMapping(String examQuestionMappingId) {
		String query = "DELETE FROM "+ TableConstants.EXAMQUESTIONMAPPING+" WHERE EXAMQUESTIONMAPPINGID =?";
		CreateConnection.getConnection().update(query,examQuestionMappingId);
	}
	
	public void deleteExamMappedQuestions(String examId,String questionId) {
		
		String query = "select * from "+ TableConstants.EXAMQUESTIONMAPPING +" where EXAMID="+examId;
		String questionIdList = executeFetch(query);
		String[] questionIdListArr =questionIdList.split(",");
		ArrayList<String> newList=new ArrayList<>(Arrays.asList(questionIdListArr));
		System.out.println(questionId+"=="+newList.toString());
		for(int i=0;i<newList.size();i++)
		{
			if(newList.get(i).trim().equals(questionId))
			{
				newList.remove(i);
			}
		}
		
		System.out.println(newList.toString());
		String updateQuery = "UPDATE "+ TableConstants.EXAMQUESTIONMAPPING +" SET QUESTIONIDLIST=? where EXAMID=?";
		CreateConnection.getConnection().update(updateQuery,newList.toString(),examId);
	}
	
}
