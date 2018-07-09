package com.creatinnos.onlinetestsystem.model;

import java.util.List;

public class NewExam extends Token{
	private String examId;
	private String category;
	private String subCategory;
	private String subject;
	private String organizationId;
	private String examName;
	private String examStartDate;
	private String examEndDate;
	private String examDuration;
	private String examTime;
	private Double passMark;
	private boolean isNegativeMarkApplicable;
	private List<String> candidates;
	private String candidatesType;
	private String instruction;
	private String progress;
	
	
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	
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
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public Double getPassMark() {
		return passMark;
	}
	public void setPassMark(Double passMark) {
		this.passMark = passMark;
	}
	public boolean isNegativeMarkApplicable() {
		return isNegativeMarkApplicable;
	}
	public void setNegativeMarkApplicable(boolean isNegativeMarkApplicable) {
		this.isNegativeMarkApplicable = isNegativeMarkApplicable;
	}
	public List<String> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<String> candidates) {
		this.candidates = candidates;
	}
	public String getCandidatesType() {
		return candidatesType;
	}
	public void setCandidatesType(String candidatesType) {
		this.candidatesType = candidatesType;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	@Override
	public String toString() {
		return "NewExam [examId=" + examId + ", category=" + category
				+ ", subCategory=" + subCategory + ", subject=" + subject + ", organizationId=" + organizationId
				+ ", examName=" + examName + ", examStartDate=" + examStartDate + ", examEndDate=" + examEndDate
				+ ", examDuration=" + examDuration+ ", examTime="
				+ examTime + ", passMark=" + passMark + ", isNegativeMarkApplicable=" + isNegativeMarkApplicable
				+ ", candidates=" + candidates + ", candidatesType=" + candidatesType + ", instruction=" + instruction
				+ ", progress=" + progress + "]";
	}
		
	
}
