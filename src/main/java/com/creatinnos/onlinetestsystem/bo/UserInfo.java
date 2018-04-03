package com.creatinnos.onlinetestsystem.bo;

import com.creatinnos.onlinetestsystem.daocustomization.Column;
import com.creatinnos.onlinetestsystem.daocustomization.ColumnType;
import com.creatinnos.onlinetestsystem.daocustomization.Table;

@Table(tableName="user")
public interface UserInfo {

	public @Column(columnName ="userId", columnType = ColumnType.INT,isPrimaryKey=true) String USERID = "userId";
	public @Column(columnName ="username",columnType = ColumnType.STRING) String USERNAME = "username";
	public @Column(columnName ="password",columnType = ColumnType.STRING) String PASSWORD= "password";
	public @Column(columnName ="email",columnType = ColumnType.STRING) String EMAIL= "email";
	public @Column(columnName ="phoneNumber",columnType = ColumnType.STRING) String PHONENUMBER= "phoneNumber";
	public @Column(columnName ="companyName",columnType = ColumnType.STRING) String COMPANYNAME= "companyName";
	public @Column(columnName ="createdDate",columnType = ColumnType.STRING) String CREATEDDATE= "createdDate";
	public @Column(columnName ="lastModifiedDate",columnType = ColumnType.STRING) String LASTMODIFIEDDATE= "lastModifiedDate";
	
	public String getUserId();
	public void setUserId(String userId) ;
	
	public String getUsername();
	public void setUsername(String username);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getEmail();
	public void setEmail(String email);
	
	public String getPhoneNumber();
	public void setPhoneNumber(String phoneNumber);
	
	public String getCompanyName();
	public void setCompanyName(String companyName);
	
	public String getCreatedDate();
	public void setCreatedDate(String createdDate);
	
	public String getLastModifiedDate();
	public void setLastModifiedDate(String lastModifiedDate);

}
