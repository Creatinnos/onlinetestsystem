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

import com.creatinnos.onlinetestsystem.Constant.TableConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.daocustomization.ExecuteQuery;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

public class LoginDao {
	static Logger log = Logger.getLogger(LoginDao.class.getName());

	ExecuteQuery executeQuery = new ExecuteQuery();

	public List<OrganizationUsers> findOrganization(String username, String password) {
		String findUser = "select * from "+TableConstants.ORGANIZATIONUSER+" where username='" + username + "' and password='" + password + "';";
		return executeFetch(findUser);
	}

	public List<OrganizationUsers> findCandidate(String username, String password) {
		String findUser = "select * from CANDIDATE where username='" + username + "' and password='" + password + "';";
		return executeFetch(findUser);
	}

	public List<OrganizationUsers> registerAdmin(final OrganizationUsers users) {
		final String query = "insert into organization (ORGANIZATIONNAME ,CREATEDDATE ,LASTMODIFIEDDATE) " + "VALUES ("
				+ "?,?,?)";

		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, users.getOrganizationName());
				ps.setString(2, dateFormat.format(calendar.getTime()));
				ps.setString(3, dateFormat.format(calendar.getTime()));
				return ps;
			}
		}, holder);
		
		final int organizationId = holder.getKey().intValue();
		System.out.println(users);
		String query1 = "insert into "+TableConstants.ORGANIZATIONUSER+"(ROLE,NAME,USERNAME,PASSWORD,EMAIL ,ORGANIZATIONID ,PHONENUMBER ,CREATEDDATE ,LASTMODIFIEDDATE,CREATEDUSERID) "
				+ "VALUES (" + "'Q1U1C1'," + "'" + users.getName() + "'," + "'" + users.getUserName() + "'," + "'"
				+ users.getPassword() + "'," + "'" + users.getEmail() + "'," + "'" + organizationId + "'," + "'"
				+ users.getPhone() + "'," + "'" + (new Date()) + "'," + "'" + (new Date()) + "'," + "'0'" + ")";
		executeQuery.executeInsert(query1);
		users.setOrganizationId(organizationId+"");
		List<OrganizationUsers> list=new ArrayList<>();
		list.add(users);
		return list;
	}

	public boolean registerUser(OrganizationUsers users) {
		String query = "insert into "+TableConstants.ORGANIZATIONUSER+"(ROLE,USERNAME,PASSWORD,EMAIL ,ORGANIZATIONID ,PHONENUMBER ,CREATEDDATE ,LASTMODIFIEDDATE,CREATEDUSERID) "
				+ "VALUES (" + "'" + users.getRole() + "'," + "'" + users.getUserName() + "'," + "'"
				+ users.getPassword() + "'," + "'" + users.getEmail() + "'," + "'" + users.getOrganizationId() + "',"
				+ "'" + users.getPhone() + "'," + "'" + (new Date()) + "'," + "'" + (new Date()) + "'" + "'"
				+ users.getCreatedUser() + "'," + ")";
		return executeQuery.executeInsert(query);
	}

	private static List<OrganizationUsers> executeFetch(String query) {
		log.info(query);
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			if (maps != null && maps.size() > 0) {
				return DbValueMapping.mapOrganizationUsers(maps);
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return new ArrayList<>();
	}
}
