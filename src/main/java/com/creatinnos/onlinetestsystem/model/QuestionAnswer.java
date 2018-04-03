package com.creatinnos.onlinetestsystem.model;

import java.util.ArrayList;

public class QuestionAnswer
{
	private String questionId;
	private ArrayList<String> selectedChoice;

	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public ArrayList<String> getSelectedChoice() {
		return selectedChoice;
	}
	public void setSelectedChoice(ArrayList<String> selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

}