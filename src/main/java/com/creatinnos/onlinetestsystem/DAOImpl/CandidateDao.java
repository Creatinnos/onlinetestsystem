package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.Constant.QueryConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.model.candidate.Candidates;

public class CandidateDao {

	static Logger log = Logger.getLogger(CandidateDao.class.getName());

	public List<Candidates> fetchAllCandidates() {
		return executeFetch(QueryConstants.FETCH_ALL_CANDIDATE);
	}

	public Candidates saveCandidates(final Candidates candidate)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_CANDIDATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, candidate.getName());
				ps.setString(2, candidate.getSureName());
				ps.setString(3, candidate.getFatherName());
				ps.setString(4, candidate.getMotherName());
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(5, candidate.getDob());
				ps.setString(6, candidate.getAddress());
				ps.setString(7, candidate.getEmail());
				ps.setString(8, candidate.getPhone());
				ps.setString(9, candidate.getUserName());
				ps.setString(10, candidate.getPassword());
				ps.setString(11, new Date().toString());
				ps.setString(12, new Date().toString());
				return ps;
			}
		}, holder);
		final int candidateId = holder.getKey().intValue();
		candidate.setCandidateId(candidateId+"");
		return candidate;
	}
	
	public boolean updateCandidates(List<Candidates> candidates)
	{
		boolean allSuccess=false;
		if(candidates!=null)
		{
			for(int i=0;i<candidates.size();i++)
			{
				if(updateCandidates(candidates.get(i)))
				{
					allSuccess=true;
				}
				else
				{
					allSuccess=false;
				}
			}
		}
		return allSuccess;
	}
	public boolean updateCandidates(final Candidates candidate)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.UPDATE_CANDIDATE, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, candidate.getName());
				ps.setString(2, candidate.getSureName());
				ps.setString(3, candidate.getFatherName());
				ps.setString(4, candidate.getMotherName());
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				ps.setString(5, dateFormat.format(calendar.getTime()));
				ps.setString(6, candidate.getAddress());
				ps.setString(7, candidate.getEmail());
				ps.setString(8, candidate.getPhone());
				ps.setString(9, candidate.getUserName());
				ps.setString(10, candidate.getPassword());
				ps.setString(11, new Date().toString());
				ps.setString(12, candidate.getCandidateId());
				return ps;
			}
		}, holder);
		return true;
	}
	
	private List<Candidates> executeFetch(String query) {
		List<Candidates> candidates = new ArrayList<Candidates>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					Candidates candidate = new Candidates();
					for (String st : map.keySet()) {
						switch (st) {
						case "CANDIDATEID":
							candidate.setCandidateId("" + map.get(st));
							break;
						case "NAME":
							candidate.setName("" + map.get(st));
							break;
						case "SURENAME":
							candidate.setSureName("" + map.get(st));
							break;
						case "FATHERNAME":
							candidate.setFatherName("" + map.get(st));
							break;
						case "MOTHERNAME":
							candidate.setMotherName("" + map.get(st));
							break;
						case "DOB":
							candidate.setDob("" + map.get(st));
							break;
						case "ADDRESS":
							candidate.setAddress("" + map.get(st));
							break;
						case "EMAIL":
							candidate.setEmail("" + map.get(st));
							break;
						case "PHONENUMBER":
							candidate.setPhone("" + map.get(st));
							break;
						case "USERRNAME":
							candidate.setUserName("" + map.get(st));
							break;
						/*case "PASSWORD":
							candidate.setPassword("" + map.get(st));
							break;*/
						case "CREATEDATE":
							candidate.setCreatedDate("" + map.get(st));
							break;
						case "MODIFIEDDATE":
							candidate.setModifiedDate("" + map.get(st));
							break;
						}
					}

					candidates.add(candidate);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return candidates;
	}

}
