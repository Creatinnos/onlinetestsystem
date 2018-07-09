package com.creatinnos.onlinetestsystem.rest;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.ExamMappingDao;
import com.creatinnos.onlinetestsystem.DAOImpl.QuestionDao;
import com.creatinnos.onlinetestsystem.model.QuestionList;

@Controller
public class QuestionService {


	@ResponseBody
	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST )
	public Boolean saveQuestions(@RequestBody QuestionList questions) {
		boolean saved=false;
		if(questions!=null)
		{
		QuestionDao questionDao=new QuestionDao();
		ArrayList<Integer> questionIdList =  questionDao.saveQuestion(questions);
		ExamMappingDao dao=new ExamMappingDao();
		saved = dao.saveExamMapping(questions.getExamId(), questionIdList);
		}
		
		return saved;	
	}

	
	@ResponseBody
	@RequestMapping(value = "/fetchQuestion", method = RequestMethod.GET )
	public QuestionList produceQuestions() {
		QuestionDao questionDao=new QuestionDao();
		QuestionList questions=questionDao.fetchQuestion();
		return questions;
	}
	
	@ResponseBody
	@RequestMapping(value = "/fetchExamMappedQuestion", method = RequestMethod.GET )
	public QuestionList fetchExamMappedQuestion(@RequestParam(required = true, value = "examId") String examId) {
		ExamMappingDao examMappingDao=new ExamMappingDao();
		QuestionList questions=examMappingDao.fetchExamMappedQuestions(examId);
		return questions;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST )
	public void updateQuestion(@RequestBody QuestionList questions) {
		QuestionDao questionDao=new QuestionDao();
		questionDao.updateQuestion(questions);
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST )
	public boolean deleteQuestion(@RequestParam(required = true, value = "questionId") String questionId) {
		QuestionDao questionDao=new QuestionDao();
		questionDao.deleteQuestion(questionId);
		return true;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteExamMappedQuestion", method = RequestMethod.GET )
	public boolean deleteExamMappedQuestion(@RequestParam(required = true, value = "questionId") String questionId,
			@RequestParam(required = true, value = "examId") String examId) {
		ExamMappingDao examMappingDao=new ExamMappingDao();
		examMappingDao.deleteExamMappedQuestions(examId,questionId);
		return true;
	}
	

}