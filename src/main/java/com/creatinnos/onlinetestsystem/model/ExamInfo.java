package com.creatinnos.onlinetestsystem.model;

public class ExamInfo {

	private Long examInfoId=null;
	private String examName = null;// : "RRB Practice Exam",
	private String examStartDate = null;// "15/05/2018",
	private String examEndDate = null; // "20/05/2018",
	private String examDuration = "";// "1 Hour",
	private Integer progress = 0;// 40;

	
	public Long getExamInfoId() {
		return examInfoId;
	}

	public void setExamInfoId(Long examInfoId) {
		this.examInfoId = examInfoId;
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

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}
}
