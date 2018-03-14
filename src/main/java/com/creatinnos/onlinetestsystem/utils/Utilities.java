package com.creatinnos.onlinetestsystem.utils;

import java.io.IOException;
import java.util.List;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.unittest.DummyDatas;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {

	public static void objectToJSON(List<?> data,boolean prettyString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString ="";
			if(!prettyString)
			{
				jsonInString = mapper.writeValueAsString(data);
				System.out.println(jsonInString);
			}
			else
			{
				jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
				System.out.println(jsonInString);
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		objectToJSON(DummyDatas.getDummyQuestionList(),true);
	}

	public static String questionBoToString(Question question)
	{
		String str="{\n";
		if(question!=null)
		{
			str=str+" QuestionId : "+question.getQuestionId()+",\n";
			str=str+" Question : "+question.getQuestion()+",\n";
			str=str+" answer : "+question.getAnswer()+",\n";
			str=str+" choice type : "+question.getChoiceType()+",\n";
			str=str+" choice : "+question.getChoices()+",\n";
			str=str+" upload date : "+question.getUploadDate()+",\n";
		}
		str=str+"}\n ";
		return str;
	}
}
