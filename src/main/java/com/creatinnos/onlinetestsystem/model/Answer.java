package com.creatinnos.onlinetestsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Answer {

	private String examId;
	private String candidateId;
	private List<QuestionAnswer> questionAnswer;

	public Answer() {
		// TODO Auto-generated constructor stub
	}
	public Answer( String examId, String candidateId,List<QuestionAnswer> questionAnswers) {
		this.examId=examId;
		this.candidateId=candidateId;
		this.questionAnswer=questionAnswers;
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public List<QuestionAnswer> getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(List<QuestionAnswer> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

}


