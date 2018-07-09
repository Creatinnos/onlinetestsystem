package com.creatinnos.onlinetestsystem.model;

import java.util.List;

public class CategoryList extends Token {

	private List<Category> category;
	private List<SubCategory> subCategory;
	private List<Subject> subject;
	
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public List<SubCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}
	public List<Subject> getSubject() {
		return subject;
	}
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
	

}
