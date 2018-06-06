package com.creatinnos.onlinetestsystem.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {

	private String id;
	private String type;
	private String question;
	private List<HashMap<String, Object>> choice;
	/*private String choice2;
	private String choice3;
	private String choice4;
	private String choice5;
	private String choice6;
	private String choice7;
	private String choice8;
	private String choice9;
	private String choice10;*/
	private String uploadDate;
	
	public Question()
	{
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<HashMap<String, Object>> getChoice() {
		return choice;
	}

	public void setChoice(List<HashMap<String, Object>> choices) {
		this.choice = choices;
	}

/*	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public String getChoice5() {
		return choice5;
	}

	public void setChoice5(String choice5) {
		this.choice5 = choice5;
	}

	public String getChoice6() {
		return choice6;
	}

	public void setChoice6(String choice6) {
		this.choice6 = choice6;
	}

	public String getChoice7() {
		return choice7;
	}

	public void setChoice7(String choice7) {
		this.choice7 = choice7;
	}

	public String getChoice8() {
		return choice8;
	}

	public void setChoice8(String choice8) {
		this.choice8 = choice8;
	}

	public String getChoice9() {
		return choice9;
	}

	public void setChoice9(String choice9) {
		this.choice9 = choice9;
	}

	public String getChoice10() {
		return choice10;
	}

	public void setChoice10(String choice10) {
		this.choice10 = choice10;
	}
*/
	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	@Override
	public String toString() {
		return "Question2 [type=" + type + ", question=" + question + ", choice1=" + choice +"]";
	}


}