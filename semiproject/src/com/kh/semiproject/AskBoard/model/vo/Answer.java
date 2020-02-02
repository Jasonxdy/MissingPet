package com.kh.semiproject.AskBoard.model.vo;

import java.sql.Date;

public class Answer {

	private int answerNo;
	private String answerContent;
	private Date answerDate;
	
	
	public Answer() {}


	public Answer(int answerNo, String answerContent, Date answerDate) {
		super();
		this.answerNo = answerNo;
		this.answerContent = answerContent;
		this.answerDate = answerDate;
	}


	public int getAnswerNo() {
		return answerNo;
	}


	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}


	public String getAnswerContent() {
		return answerContent;
	}


	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}


	public Date getAnswerDate() {
		return answerDate;
	}


	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}


	
	
	@Override
	public String toString() {
		return "Answer [answerNo=" + answerNo + ", answerContent=" + answerContent + ", answerDate=" + answerDate + "]";
	}
	
	
	
	
	
	
}
