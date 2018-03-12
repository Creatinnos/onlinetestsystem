package com.creatinnos.onlinetestsystem.model;

import com.creatinnos.onlinetestsystem.daocustomization.Column;
import com.creatinnos.onlinetestsystem.daocustomization.ColumnType;
import com.creatinnos.onlinetestsystem.daocustomization.Table;

@Table(tableName="question")
public interface Question {

	public @Column(columnType = ColumnType.INT) String QUESTIONID = "questionId";
	public @Column(columnType = ColumnType.STRING) String QUESTION = "question";
	public @Column(columnType = ColumnType.STRING) String UPLOADDATE= "uploadDate";

	public void setQuestionId(String questionId);
	public String getQuestionId();

	public void setQuestion(String question);
	public String getQuestion();

	public void setUploadDate(String uploadDate);
	public String getuploadDate();

}
