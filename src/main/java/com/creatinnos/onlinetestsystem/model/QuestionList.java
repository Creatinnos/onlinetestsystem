package com.creatinnos.onlinetestsystem.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionList extends Token {

	private String examId;
	private List<Question> question;

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public void addQuestion(Question question2) {
		if(this.question==null)
		{
			this.question =new ArrayList<>();
		}
		this.question.add(question2);
		
	}
}
