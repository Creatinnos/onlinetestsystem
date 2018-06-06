package com.creatinnos.onlinetestsystem.model;

public class NewExam extends Token{

	private String category;
	private String subCategory;
	private String subject;
	private String organizationId;
	private String examName;
	private String examStartDate;
	private String examEndDate;
	private String examDuration;
	private String examStartTime;
	private String examPassMark;
	private boolean isNegativeMarkApplicable;
	private String instruction;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamStartDate() {
		return examStartDate;
	}
	public void setExamStartDate(String examStartDate) {
		this.examStartDate = examStartDate;
	}
	public String getExamEndDate() {
		return examEndDate;
	}
	public void setExamEndDate(String examEndDate) {
		this.examEndDate = examEndDate;
	}
	public String getExamDuration() {
		return examDuration;
	}
	public void setExamDuration(String examDuration) {
		this.examDuration = examDuration;
	}
	public String getExamStartTime() {
		return examStartTime;
	}
	public void setExamStartTime(String examStartTime) {
		this.examStartTime = examStartTime;
	}
	public String getExamPassMark() {
		return examPassMark;
	}
	public void setExamPassMark(String examPassMark) {
		this.examPassMark = examPassMark;
	}
	public boolean isNegativeMarkApplicable() {
		return isNegativeMarkApplicable;
	}
	public void setNegativeMarkApplicable(boolean isNegativeMarkApplicable) {
		this.isNegativeMarkApplicable = isNegativeMarkApplicable;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	
}
