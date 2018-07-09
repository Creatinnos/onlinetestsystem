package com.creatinnos.onlinetestsystem.Constant;

public class QueryConstants {

	public static String FETCH_ALL_EXAMINFO="SELECT * FROM "+TableConstants.EXAM;
	public static String FETCH_ALL_EVENTINFO="SELECT * FROM "+TableConstants.EVENT;
	
	//Candidate
	public static String FETCH_ALL_CANDIDATE="SELECT * FROM "+TableConstants.CANDIDATE;
	public static String SAVE_CANDIDATE ="insert into CANDIDATE(NAME,SURENAME,FATHERNAME,MOTHERNAME,DOB,ADDRESS,EMAIL,PHONENUMBER,USERNAME,PASSWORD,CREATEDDATE,LASTMODIFIEDDATE)"
	+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String UPDATE_CANDIDATE ="update "+TableConstants.CANDIDATE+" set NAME=?,SURENAME=?,FATHERNAME=?,"
			+ "MOTHERNAME=?,DOB=?,ADDRESS=?,EMAIL=?,PHONENUMBER=?,USERNAME=?,PASSWORD=?,LASTMODIFIEDDATE=? WHERE CANDIDATEID=?";

	//Users
	public static String FETCH_ALL_ORGANIZATIONUSER="SELECT * FROM "+TableConstants.ORGANIZATIONUSER;
	public static String SAVE_ORGANIZATIONUSER ="insert into "+TableConstants.ORGANIZATIONUSER+""
			+ "(NAME,ROLE,ORGANIZATIONID,USERNAME,PASSWORD,EMAIL,PHONENUMBER,CREATEDDATE,LASTMODIFIEDDATE)"
	+"VALUES(?,?,?,?,?,?,?,?,?)";
	public static String UPDATE_ORGANIZATIONUSER="update "+TableConstants.ORGANIZATIONUSER+" set NAME=?,ROLE=?,"
			+ "USERNAME=?,PASSWORD=?,EMAIL=?,PHONENUMBER=?,LASTMODIFIEDDATE=? WHERE USERID=?";

	//Category
	//public static String SAVE_CATEGORY="INSERT INTO "+TableConstants.CATEGORY+"(CATEGORYNAME,SUBCATEGORYNAME,SUBJECTNAME) VALUES(?,??)";
	public static String SAVE_CATEGORY="INSERT INTO "+TableConstants.CATEGORY+"(CATEGORYNAME,ORGANIZATIONID) VALUES(?,?)";
	public static String FETCH_CATEGORY="SELECT * FROM "+TableConstants.CATEGORY+" WHERE ORGANIZATIONID =";
	
	public static String SAVE_SUB_CATEGORY="INSERT INTO "+TableConstants.SUBCATEGORY+"(SUBCATEGORYNAME,CATEGORYID,ORGANIZATIONID) VALUES(?,?,?)";
	public static String FETCH_SUB_CATEGORY="SELECT * FROM "+TableConstants.SUBCATEGORY+" WHERE ORGANIZATIONID =";
	
	public static String SAVE_SUBJECT="INSERT INTO "+TableConstants.SUBJECT+"(SUBJECT,CATEGORYID,SUBCATEGORYID,ORGANIZATIONID) VALUES(?,?,?,?)";
	public static String FETCH_SUBJECT="SELECT * FROM "+TableConstants.SUBJECT+" WHERE ORGANIZATIONID =";
	
	//Category
	public static String FETCH_ALL_CATEGORY="SELECT * FROM "+TableConstants.CATEGORY;
	
	
}
