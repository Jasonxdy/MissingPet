package com.kh.semiproject.AskBoard.model.vo;

import java.sql.Date;

public class AskBoard {
	private int askNo;
	private String askTitle;
	private String askContent;
	private Date askCreateDt;
	private Date askModifyDt;
	private String askStatus;
	private String memberId;
	private String answerContent;	
	
	public AskBoard() {}

	
	
	
	
	
	public AskBoard(int askNo, String askTitle, String askContent, Date askModifyDt, String askStatus,
			String memberId,String answerContent) {
		super();
		this.askNo = askNo;
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askModifyDt = askModifyDt;
		this.askStatus = askStatus;
		this.memberId = memberId;
		this.answerContent = answerContent;
	}






	public AskBoard(int askNo, String askTitle, String askContent, Date askCreateDt, Date askModifyDt, String askStatus,
			String memberId) {
		super();
		this.askNo = askNo;
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askCreateDt = askCreateDt;
		this.askModifyDt = askModifyDt;
		this.askStatus = askStatus;
		this.memberId = memberId;
	}
	
	

	public String getAnswerContent() {
		return answerContent;
	}






	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}






	public int getAskNo() {
		return askNo;
	}

	public void setAskNo(int askNo) {
		this.askNo = askNo;
	}

	public String getAskTitle() {
		return askTitle;
	}

	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}

	public String getAskContent() {
		return askContent;
	}

	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}

	public Date getAskCreateDt() {
		return askCreateDt;
	}

	public void setAskCreateDt(Date askCreateDt) {
		this.askCreateDt = askCreateDt;
	}

	public Date getAskModifyDt() {
		return askModifyDt;
	}

	public void setAskModifyDt(Date askModifyDt) {
		this.askModifyDt = askModifyDt;
	}

	public String getAskStatus() {
		return askStatus;
	}

	public void setAskStatus(String askStatus) {
		this.askStatus = askStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}






	@Override
	public String toString() {
		return "AskBoard [askNo=" + askNo + ", askTitle=" + askTitle + ", askContent=" + askContent + ", askCreateDt="
				+ askCreateDt + ", askModifyDt=" + askModifyDt + ", askStatus=" + askStatus + ", memberId=" + memberId
				+ ", answerContent=" + answerContent + "]";
	}

	
	
	

	
	
	
	
	
	
	
}
