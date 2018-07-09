package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.Constant.QueryConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

public class UserDao {

	static Logger log = Logger.getLogger(UserDao.class.getName());

	public List<OrganizationUsers> fetchAllUsers() {
		return executeFetch(QueryConstants.FETCH_ALL_ORGANIZATIONUSER);
	}
	public List<OrganizationUsers> fetchUsers(String organizationId) {
		return executeFetch(QueryConstants.FETCH_ALL_ORGANIZATIONUSER +  " where organizationId='"+organizationId+"'");
	}

	public OrganizationUsers saveUser(final OrganizationUsers users)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				
				PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_ORGANIZATIONUSER, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, users.getName());
				ps.setString(2, users.getRole());
				ps.setString(3, users.getOrganizationId());
				ps.setString(4, users.getUserName());
				ps.setString(5, users.getPassword());
				ps.setString(6, users.getEmail());
				ps.setString(7, users.getPhone());
				ps.setString(8, dateFormat.format(calendar.getTime()));
				ps.setString(9, dateFormat.format(calendar.getTime()));
				return ps;
			}
		}, holder);
		final int userId = holder.getKey().intValue();
		System.out.println(users);
		users.setUserId(userId+"");
		return users;
	}
	
	public boolean updateUsers(List<OrganizationUsers> users)
	{
		boolean allSuccess=false;
		if(users!=null)
		{
			for(int i=0;i<users.size();i++)
			{
				if(updateUser(users.get(i)))
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
	public boolean updateUser(final OrganizationUsers users)
	{
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				PreparedStatement ps = connection.prepareStatement(QueryConstants.UPDATE_ORGANIZATIONUSER, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, users.getName());
				ps.setString(2, users.getRole());
				ps.setString(3, users.getUserName());
				ps.setString(4, users.getPassword());
				ps.setString(5, users.getEmail());
				ps.setString(6, users.getPhone());
				ps.setString(7, dateFormat.format(calendar.getTime()));
				ps.setString(8, users.getUserId());
				return ps;
			}
		}, holder);
		return true;
	}
	
	private List<OrganizationUsers> executeFetch(String query) {
		List<OrganizationUsers> userList = new ArrayList<OrganizationUsers>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					OrganizationUsers users = new OrganizationUsers();
					for (String st : map.keySet()) {
						switch (st) {
						case "USERID":
							users.setUserId("" + map.get(st));
							break;
						case "NAME":
							users.setName("" + map.get(st));
							break;
						case "ROLE":
							users.setRole("" + map.get(st));
							break;
						case "USERNAME":
							users.setUserName("" + map.get(st));
							break;
						case "PASSWORD":
							users.setPassword("" + map.get(st));
							break;
						case "EMAIL":
							users.setEmail("" + map.get(st));
							break;
						case "ORGANIZATIONID":
							users.setOrganizationId("" + map.get(st));
							break;
						case "PHONENUMBER":
							users.setPhone("" + map.get(st));
							break;
						case "CREATEDATE":
							users.setCreatedDate("" + map.get(st));
							break;
						case "MODIFIEDDATE":
							users.setModifiedDate("" + map.get(st));
							break;
						}
					}
					System.out.println(users);
					userList.add(users);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return userList;
	}

}
