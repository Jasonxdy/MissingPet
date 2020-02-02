package com.kh.semiproject.mypage.model.vo;

import java.sql.Date;

public class Ask {
	
	private int askNo;
	private String askTitle;
	private String askContent;
	private Date askCreateDt;
	private Date askModifyDt;
	private String answerContent;
	private String askStatus;
	private String memberId;
	private int answerNo;
	private Date answerDate;
	
	public Ask() {
		// TODO Auto-generated constructor stub
	}

	public Ask(int askNo, String askTitle, String askContent, Date askCreateDt, Date askModifyDt, String answerContent,
			String askStatus, String memberId, int answerNo, Date answerDate) {
		super();
		this.askNo = askNo;
		this.askTitle = askTitle;
		this.askContent = askContent;
		this.askCreateDt = askCreateDt;
		this.askModifyDt = askModifyDt;
		this.answerContent = answerContent;
		this.askStatus = askStatus;
		this.memberId = memberId;
		this.answerNo = answerNo;
		this.answerDate = answerDate;
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

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
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

	public int getAnswerNo() {
		return answerNo;
	}

	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	@Override
	public String toString() {
		return "Ask [askNo=" + askNo + ", askTitle=" + askTitle + ", askContent=" + askContent + ", askCreateDt="
				+ askCreateDt + ", askModifyDt=" + askModifyDt + ", answerContent=" + answerContent + ", askStatus="
				+ askStatus + ", memberId=" + memberId + ", answerNo=" + answerNo + ", answerDate=" + answerDate + "]";
	}
	
	
	

}
