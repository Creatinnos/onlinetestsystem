package com.creatinnos.onlinetestsystem.unittest;

import java.util.ArrayList;
import java.util.List;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.daocustomization.BusinessObject;
import com.creatinnos.onlinetestsystem.model.ChoiceType;

public class DummyDatas {

	public static Question getDummyQuestion()
	{
		Question questions= BusinessObject.create(Question.class);
		questions.setQuestion("Question1");
		questions.setChoiceType(ChoiceType.CHECKBOX);
		ArrayList<String> choices=new ArrayList<>();
		choices.add("choice1");
		choices.add("choice2");
		choices.add("choice3");
		choices.add("choice4");
		questions.setChoices(choices);
		return questions; 
	}

	public static Question getDummyQuestion(String question)
	{
		Question questions=BusinessObject.create(Question.class);
		questions.setQuestion(question);
		questions.setChoiceType(ChoiceType.RADIO);
		ArrayList<String> choices=new ArrayList<>();
		choices.add("choice1");
		choices.add("choice2");
		choices.add("choice3");
		choices.add("choice4");
		questions.setChoices(choices);
		return questions; 
	}
	
	public static Question getDummyQuestion(String question,ChoiceType choiceType)
	{
		Question questions=BusinessObject.create(Question.class);
		questions.setQuestion(question);
		questions.setChoiceType(choiceType);
		ArrayList<String> choices=new ArrayList<>();
		choices.add("choice1");
		choices.add("choice2");
		choices.add("choice3");
		choices.add("choice4");
		questions.setChoices(choices);
		return questions; 
	}
	
	public static List<Question> getDummyQuestionList()
	{
		List<Question> questionsList=new ArrayList<>();
		
		for(int i=1;i<=10;i++)
		{
			if(i%3==0)
			{
				questionsList.add(getDummyQuestion("Question"+i,ChoiceType.CHECKBOX));
			}
			else if(i%4==0)
			{
				questionsList.add(getDummyQuestion("Question"+i,ChoiceType.TEXTAREA));
			}else
			{
				questionsList.add(getDummyQuestion("Question"+i,ChoiceType.RADIO));
				
			}
		}
		return questionsList; 
	}
}
