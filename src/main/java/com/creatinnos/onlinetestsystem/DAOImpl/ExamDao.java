package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.creatinnos.onlinetestsystem.model.NewExam;

public class ExamDao extends NamedParameterJdbcDaoSupport {
	static Logger log = Logger.getLogger(ExamDao.class.getName());

	ExecuteQuery executeQuery = new ExecuteQuery();

	public boolean updateExam(final NewExam newExam) {

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

	
	public boolean saveNewExam(final NewExam newExam) {

		final String newExamQuery = "insert into " + TableConstants.EXAM
				+ "(EXAMNAME,ORGANIZATIONID,EXAMSTARTDATE,EXAMENDDATE,EXAMTIME,EXAMDURATION,PASSMARK,"
				+ "ISNEGATIVE,CATEGORY,SUBCATEGORY,SUBJECT)" 
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		log.info(newExamQuery);
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(newExamQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, newExam.getExamName());
				ps.setString(2, newExam.getOrganizationId());
				
				SimpleDateFormat dateFormatDb=new SimpleDateFormat("yyyy-MM-dd");
				try {
					ps.setString(3, dateFormatDb.format(dateFormatDb.parse(newExam.getExamStartDate())));
					ps.setString(4, dateFormatDb.format(dateFormatDb.parse(newExam.getExamEndDate())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ps.setString(5, newExam.getExamTime());
				ps.setString(6, newExam.getExamDuration());
				ps.setDouble(7, newExam.getPassMark());
				ps.setBoolean(8, newExam.isNegativeMarkApplicable());
				ps.setString(9, newExam.getCategory());
				ps.setString(10, newExam.getSubCategory());
				ps.setString(11, newExam.getSubject());
				return ps;
			}
		}, holder);

//		final int newExamId = holder.getKey().intValue();
		
		return true;
	}

	private ArrayList<NewExam> executeFetch(String query) {
		log.info(query);
		ArrayList<NewExam> list = new ArrayList<>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					NewExam newExam = new NewExam();
					for (String st : map.keySet()) {
						switch (st) {
						case "EXAMID":
							newExam.setExamId("" + map.get(st));
							break;
						case "EXAMNAME":
							newExam.setExamName("" + map.get(st));
							break;
						case "ORGANIZATIONID":
							newExam.setOrganizationId("" + map.get(st));
							break;
						case "EXAMSTARTDATE":
							newExam.setExamStartDate("" + map.get(st));
							break;
						case "EXAMENDDATE":
							newExam.setExamEndDate("" + map.get(st));
							break;
						case "EXAMDURATION":
							newExam.setExamDuration(""+map.get(st));
							break;
						case "EXAMTIME":
							newExam.setExamTime(""+map.get(st));
							break;
						case "PASSMARK":
							newExam.setPassMark( Double.parseDouble(""+map.get(st)));
							break;
						case "ISNEGATIVE":
							if((map.get(st)+"").equals("1"))
							{
								newExam.setNegativeMarkApplicable(true);	
							}
							else
							{
								newExam.setNegativeMarkApplicable(false);
							}
							break;
						case "CATEGORY":
							newExam.setCategory("" + map.get(st));
							break;
						case "SUBCATEGORY":
							newExam.setSubCategory("" + map.get(st));
							break;
						case "SUBJECT":
							newExam.setSubject("" + map.get(st));
							break;
						}
					}
					list.add(newExam);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return list;
	}

	public ArrayList<NewExam> fetchAllExam() {
		String query = "select * from "+ TableConstants.EXAM;
		return executeFetch(query);
	}
	public ArrayList<NewExam> fetchExam(String examId) {
		String query = "select * from "+ TableConstants.EXAM +" where EXAMID="+examId;
		return executeFetch(query);
	}
	public ArrayList<NewExam> fetchOrganizationExam(String organizationId) {
		String query = "select * from "+ TableConstants.EXAM +" where ORGANIZATIONID="+organizationId+"";
		return executeFetch(query);
	}
}
