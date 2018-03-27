package com.creatinnos.onlinetestsystem.rest;

import java.util.ArrayList;
import java.util.Date;

import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.model.ChoiceType;

public class Question {

	private String type;
	private String question;
	private String answer;
	private ArrayList<String> correctChoice;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String choice5;
	private String choice6;
	private String choice7;
	private String choice8;
	private String choice9;
	private String choice10;

	public Question()
	{
		
	}
			
	public Question(String type, String question, String choice1, String choice2, String choice3, String choice4,
			String choice5, String choice6, String choice7, String choice8, String choice9, String choice10) {
		super();
		this.type = type;
		this.question = question;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
		this.choice5 = choice5;
		this.choice6 = choice6;
		this.choice7 = choice7;
		this.choice8 = choice8;
		this.choice9 = choice9;
		this.choice10 = choice10;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
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

	public ArrayList<String> getCorrectChoice() {
		return correctChoice;
	}

	public void setCorrectChoice(ArrayList<String> correctChoice) {
		this.correctChoice = correctChoice;
	}

	@Override
	public String toString() {
		return "Question2 [type=" + type + ", question=" + question + ", choice1=" + choice1 + ", choice2=" + choice2
				+ ", choice3=" + choice3 + ", choice4=" + choice4 + ", correctChoice="+correctChoice+"]";
	}

	public com.creatinnos.onlinetestsystem.bo.Question getBO()
	{
		com.creatinnos.onlinetestsystem.bo.Question question=BusinessObject.create(com.creatinnos.onlinetestsystem.bo.Question.class);

		question.setQuestion(this.question);
		question.setAnswer(correctChoice.toString());
		if(type!=null)
		{
			switch(type)
			{
			case "checkbox":
				question.setChoiceType(ChoiceType.CHECKBOX);
				break;
			case "radio":
				question.setChoiceType(ChoiceType.RADIO);		
				break;	
			}
		}
		
		ArrayList<String> arr=new ArrayList<>();
		if(choice1!=null && !choice1.equals(""))
			arr.add(choice1);
		if(choice2!=null && !choice2.equals(""))
			arr.add(choice2);
		if(choice3!=null && !choice3.equals(""))
			arr.add(choice3);
		if(choice4!=null && !choice4.equals(""))
			arr.add(choice4);
		if(choice5!=null && !choice5.equals(""))
			arr.add(choice5);
		if(choice6!=null && !choice6.equals(""))
			arr.add(choice6);
		if(choice7!=null && !choice7.equals(""))
			arr.add(choice7);
		if(choice8!=null && !choice8.equals(""))
			arr.add(choice8);
		if(choice9!=null && !choice9.equals(""))
			arr.add(choice9);
		if(choice10!=null && !choice10.equals(""))
			arr.add(choice10);

		question.setChoices(arr);
		question.setUploadDate(""+(new Date()));
		return question;
	}

}