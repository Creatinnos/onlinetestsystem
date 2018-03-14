package com.creatinnos.onlinetestsystem.bo;

import java.util.List;

import com.creatinnos.onlinetestsystem.daocustomization.Column;
import com.creatinnos.onlinetestsystem.daocustomization.ColumnType;
import com.creatinnos.onlinetestsystem.daocustomization.Table;
import com.creatinnos.onlinetestsystem.model.ChoiceType;

@Table(tableName="question")
public interface Question {

	public @Column(columnName ="questionId", columnType = ColumnType.INT,isPrimaryKey=true) String QUESTIONID = "questionId";
	public @Column(columnName ="question",columnType = ColumnType.STRING) String QUESTION = "question";
	public @Column(columnName ="choiceType",columnType = ColumnType.ENUM , enumValue=ChoiceType.class) String CHOICETYPE= "choiceType";
	public @Column(columnName ="choice",columnType = ColumnType.LIST , value=List.class) String CHOICES= "choices";
	public @Column(columnName ="answer",columnType = ColumnType.STRING) String ANSWER = "answer";
	public @Column(columnName ="uploadDate",columnType = ColumnType.STRING) String UPLOADDATE= "uploadDate";
	
	public void setQuestionId(String questionId);
	public String getQuestionId();

	public void setQuestion(String question);
	public String getQuestion();

	public void setAnswer(String answer);
	public String getAnswer();
	
	public void setChoiceType(ChoiceType choiceType);
	public ChoiceType getChoiceType();
	
	public void setChoices(List<String> choices);
	public List<String> getChoices();
	public void addChoices(String choice);
	
	
	public void setUploadDate(String uploadDate);
	public String getUploadDate();

}
