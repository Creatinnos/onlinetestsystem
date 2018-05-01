package com.creatinnos.onlinetestsystem.model;

import java.util.ArrayList;
import java.util.List;

public class ExamInfos extends Token{

	private List<ExamInfo> examInfos = new ArrayList<ExamInfo>();
	
	public List<ExamInfo> getExamInfos() {
		return examInfos;
	}

	public void setExamInfos(List<ExamInfo> examInfos) {
		this.examInfos = examInfos;
	}

	public void addExamInfos(ExamInfo examInfo) {
		if (this.examInfos == null) {
			this.examInfos = new ArrayList<>();
		}
		this.examInfos.add(examInfo);
	}

}
