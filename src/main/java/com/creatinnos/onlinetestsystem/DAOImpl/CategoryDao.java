package com.creatinnos.onlinetestsystem.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.creatinnos.onlinetestsystem.Constant.QueryConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.model.Category;
import com.creatinnos.onlinetestsystem.model.CategoryList;
import com.creatinnos.onlinetestsystem.model.SubCategory;
import com.creatinnos.onlinetestsystem.model.Subject;

public class CategoryDao {

	static Logger log = Logger.getLogger(CategoryDao.class.getName());

	public CategoryList fetchAllCategory(String organizationId) {
		return executeFetch(organizationId);

	}

	public Category saveCategory(final Category category) {
		String query="SELECT * FROM CATEGORY WHERE CATEGORYNAME='"+category.getCategoryName()+"' AND "
			 	+" ORGANIZATIONID='"+category.getOrganizationId()+"'";
			 
		List<Map<String, Object>> maps=checkExistingData(query);
		if(maps!=null && maps.size()>0)
		{
			return frameCategory(maps.get(0));
		}
		KeyHolder holder = new GeneratedKeyHolder();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_CATEGORY,
						Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, category.getCategoryName());
					ps.setString(2, category.getOrganizationId());
				return ps;
			}
		}, holder);
		category.setCategoryId(holder.getKey().toString());
		return category;
	}

	public SubCategory saveSubCategory(final SubCategory subCategory) {
		 String query="SELECT * FROM SUBCATEGORY WHERE CATEGORYID='"+subCategory.getCategoryId()+"' AND "
				 	+" SUBCATEGORYNAME='"+subCategory.getSubCategoryName()+"' AND ORGANIZATIONID='"+subCategory.getOrganizationId()+"'";
				 
		List<Map<String, Object>> maps=checkExistingData(query);
		if(maps!=null && maps.size()>0)
		{
			return frameSubCategory(maps.get(0));
		}
		KeyHolder holder = new GeneratedKeyHolder();
		
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_SUB_CATEGORY,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, subCategory.getSubCategoryName());
				ps.setString(2, subCategory.getCategoryId());
				ps.setString(3, subCategory.getOrganizationId());
				return ps;
			}
		}, holder);
		subCategory.setSubCategoryId(holder.getKey().toString());
		return subCategory;
	}
	public Subject saveSubject(final Subject subject) {
		String query="SELECT * FROM SUBJECT WHERE CATEGORYID='"+subject.getCategoryId()+"' AND "
			 	+" SUBCATEGORYID='"+subject.getSubCategoryId()+"' AND SUBJECT='"+subject.getSubject()+"'"
			 			+ "AND ORGANIZATIONID='"+subject.getOrganizationId()+"'";
			 
		List<Map<String, Object>> maps=checkExistingData(query);
		if(maps!=null && maps.size()>0)
		{
			return frameSubject(maps.get(0));
		}
		KeyHolder holder = new GeneratedKeyHolder();
		final String subjectName = subject.getSubject();
		CreateConnection.getConnection().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_SUBJECT,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, subjectName);
				ps.setString(2, subject.getCategoryId());
				ps.setString(3, subject.getSubCategoryId());
				ps.setString(4, subject.getOrganizationId());
				return ps;
			}
		}, holder);
		subject.setSubjectId(holder.getKey().toString());
		return subject;
	}

	/*
	public Category saveCategoryTemp(final Category category) {
		KeyHolder holder = new GeneratedKeyHolder();
		final String categoryName = category.getCategoryName();
		List<SubCategory> subCategorys = category.getSubCategory();
		for (int i = 0; i < subCategorys.size(); i++) {
			holder = new GeneratedKeyHolder();
			SubCategory subCategory = subCategorys.get(i);
			final String subCategoryName = subCategory.getSubCategoryName();
			final List<String> subjects = subCategory.getSubjects();
			for (int j = 0; j < subjects.size(); j++) {
				final String subjectName = subjects.get(j);
				CreateConnection.getConnection().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(QueryConstants.SAVE_CATEGORY,
								Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, categoryName);
						ps.setString(2, subCategoryName);
						ps.setString(3, subjectName);
						return ps;
					}
				}, holder);
			}
		}
		return category;
	}*/
	
	
	private CategoryList executeFetch(String organizationId) {
		CategoryList categoryList=new CategoryList();
		categoryList.setCategory(executeFetchCategory(QueryConstants.FETCH_CATEGORY+"'"+organizationId+"'"));
		categoryList.setSubCategory(executeFetchSubCategory(QueryConstants.FETCH_SUB_CATEGORY+"'"+organizationId+"'"));
		categoryList.setSubject(executeFetchSubject(QueryConstants.FETCH_SUBJECT+"'"+organizationId+"'"));
		return categoryList;
	}
	private List<Category> executeFetchCategory(String query) {
		List<Category> categories = new ArrayList<Category>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					categories.add(frameCategory(maps.get(i)));
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return categories;
	}
	private List<SubCategory> executeFetchSubCategory(String query) {
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					subCategories.add(frameSubCategory(maps.get(i)));
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return subCategories;
	}
	private List<Subject> executeFetchSubject(String query) {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					subjects.add(frameSubject(maps.get(i)));
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return subjects;
	}
	
	private Category frameCategory(Map<String, Object> map)
	{
		Category category=new Category();
		for (String st : map.keySet()) {
			switch (st) {
			case "CATEGORYID":
				category.setCategoryId("" + map.get(st));
				break;
			case "CATEGORYNAME":
				category.setCategoryName("" + map.get(st));
				break;
			}
		}
		return category;
	}
	
	private SubCategory frameSubCategory(Map<String, Object> map)
	{
		SubCategory subCategory=new SubCategory();
		for (String st : map.keySet()) {
			switch (st) {
			case "CATEGORYID":
				subCategory.setCategoryId("" + map.get(st));
				break;
			case "SUBCATEGORYID":
				subCategory.setSubCategoryId("" + map.get(st));
				break;
			case "SUBCATEGORYNAME":
				subCategory.setSubCategoryName("" + map.get(st));
				break;
			}
		}
		return (subCategory);	
	}
	
	private Subject frameSubject(Map<String, Object> map)
	{
		Subject subject=new Subject();
		for (String st : map.keySet()) {
			switch (st) {
			case "CATEGORYID":
				subject.setCategoryId("" + map.get(st));
				break;
			case "SUBCATEGORYID":
				subject.setSubCategoryId("" + map.get(st));
				break;
			case "SUBJECT":
				subject.setSubject("" + map.get(st));
				break;
			case "SUBJECTID":
				subject.setSubjectId("" + map.get(st));
				break;
			}
		}
		return subject;
	
	}
	private List<Map<String, Object>> checkExistingData(String query) {
		log.info("checkExistingData -- > "+query);
		return CreateConnection.getConnection().queryForList(query);
	}
	
}
