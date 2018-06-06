package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	static Logger log = Logger.getLogger(JdbcTemplate.class.getName());

	ExecuteQuery executeQuery = new ExecuteQuery();

	public boolean saveNewExam(final NewExam newExam) {

		final String newExamQuery = "insert into " + TableConstants.EXAM
				+ "(EXAMNAME,ORGANIZATIONID,EXAMSTARTDATE,EXAMENDDATE,EXAMSTARTTME,EXAMDURATION,PASSMARK,"
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
				ps.setString(3, newExam.getExamStartDate());
				ps.setString(4, newExam.getExamEndDate());
				ps.setString(5, newExam.getExamStartTime());
				ps.setString(6, newExam.getExamDuration());
				ps.setString(7, newExam.getExamPassMark());
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
		ArrayList<NewExam> list = new ArrayList<>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					NewExam newExam = new NewExam();
					for (String st : map.keySet()) {
						switch (st) {
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
							newExam.setExamDuration("" + map.get(st));
							break;
						case "PASSMARK":
							newExam.setExamPassMark("" + map.get(st));
							break;
						case "ISNEGATIVE":
							newExam.setNegativeMarkApplicable((boolean) map.get(st));
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
}
