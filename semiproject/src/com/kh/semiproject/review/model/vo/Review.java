package com.kh.semiproject.review.model.vo;

import java.sql.Date;

public class Review {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date boardCreateDate;
	private Date boardModifyDate;
	private int boardCount;
	private String boardUrl;
	private String boardStatus;
	private String memberId;
	private int boardCode;
	private int rNum;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(int boardNo, String boardTitle, String boardContent, Date boardCreateDate, Date boardModifyDate,
			int boardCount, String boardUrl, String boardStatus, String memberId, int boardCode, int rNum) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardCount = boardCount;
		this.boardUrl = boardUrl;
		this.boardStatus = boardStatus;
		this.memberId = memberId;
		this.boardCode = boardCode;
		this.rNum = rNum;
	}


	public Review(int boardNo, String boardTitle, String boardContent, Date boardModifyDate, int boardCount,
			String memberId, int rNum) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardModifyDate = boardModifyDate;
		this.boardCount = boardCount;
		this.memberId = memberId;
		this.rNum = rNum;
	}
	
	

	public Review(String boardTitle, String boardContent, String boardUrl, String memberId) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardUrl = boardUrl;
		this.memberId = memberId;
	}

	public Review(int boardNo, String boardTitle, String boardContent, Date boardModifyDate, int boardCount,
			String boardUrl, String memberId) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardModifyDate = boardModifyDate;
		this.boardCount = boardCount;
		this.boardUrl = boardUrl;
		this.memberId = memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(Date boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public Date getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Date boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardUrl() {
		return boardUrl;
	}

	public void setBoardUrl(String boardUrl) {
		this.boardUrl = boardUrl;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	@Override
	public String toString() {
		return "Review [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDate=" + boardCreateDate + ", boardModifyDate=" + boardModifyDate + ", boardCount="
				+ boardCount + ", boardUrl=" + boardUrl + ", boardStatus=" + boardStatus + ", memberId=" + memberId
				+ ", boardCode=" + boardCode + ", rNum=" + rNum + "]";
	}

	
	
	
	
}
