package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;

public class DbValueMapping {

	public static List<OrganizationUsers> mapOrganizationUsers(List<Map<String, Object>> list)
	{
		
		List<OrganizationUsers> organizationUsers=new ArrayList<>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
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
				organizationUsers.add(users);
			}
		}
		
		
		return organizationUsers;
	}
}
