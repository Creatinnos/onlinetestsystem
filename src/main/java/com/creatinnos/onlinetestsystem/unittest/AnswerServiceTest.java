package com.creatinnos.onlinetestsystem.unittest;

import java.util.ArrayList;
import java.util.List;

import com.creatinnos.onlinetestsystem.model.Answer;
import com.creatinnos.onlinetestsystem.model.QuestionAnswer;
import com.creatinnos.onlinetestsystem.utils.Utilities;

public class AnswerServiceTest {

  public static void main(String[] args) {
		Utilities utilities=new Utilities();
		  List<QuestionAnswer> questionAnswers= new ArrayList<QuestionAnswer>();
	      for(int i=0;i<10;i++)
	      {
	    	  QuestionAnswer questionAnswer=new QuestionAnswer();
	    	  questionAnswer.setQuestionId("question"+i);
	    	  ArrayList<String> selectedChoices=new ArrayList<>();
	    	  selectedChoices.add("choice"+i);
	    	  questionAnswer.setSelectedChoice(selectedChoices);
	    	  questionAnswers.add(questionAnswer);
	      }
	      Answer answer=new Answer("examId","candidateId1",questionAnswers);
	      List<Answer> answers=new ArrayList<>();
	      answers.add(answer);
		utilities.objectToJSON(answers, false);
	}
}