package com.kh.semiproject.board.model.vo;

import java.sql.Date;

public class BoardHJ {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private int boardCount;
	private Date boardModifyDate;
	private String boardURL;
	private String boardWriter;
	private int boardCode;
	
	public BoardHJ() {}
	
	

	public BoardHJ(int boardNo, String boardTitle, String boardContent) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}

	

	public BoardHJ(int boardNo, String boardTitle, String boardContent, int boardCount, Date boardModifyDate,
			String boardWriter) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardModifyDate = boardModifyDate;
		this.boardWriter = boardWriter;
	}



	public BoardHJ(String boardTitle, String boardContent, String boardURL, String boardWriter, int boardCode) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardURL = boardURL;
		this.boardWriter = boardWriter;
		this.boardCode = boardCode;
	}



	public BoardHJ(int boardNo, String boardTitle, int boardCount, Date boardModifyDate, String boardWriter,
			int boardCode) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCount = boardCount;
		this.boardModifyDate = boardModifyDate;
		this.boardWriter = boardWriter;
		this.boardCode = boardCode;
	}

	

	public BoardHJ(int boardNo, String boardTitle) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
	}



	public BoardHJ(int boardNo, String boardTitle, String boardContent, String boardURL) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardURL = boardURL;
	}



	public BoardHJ(int boardNo, String boardTitle, String boardContent, int boardCount, Date boardModifyDate,
			String boardURL, String boardWriter, int boardCode) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCount = boardCount;
		this.boardModifyDate = boardModifyDate;
		this.boardURL = boardURL;
		this.boardWriter = boardWriter;
		this.boardCode = boardCode;
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

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public Date getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Date boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public String getBoardURL() {
		return boardURL;
	}

	public void setBoardURL(String boardURL) {
		this.boardURL = boardURL;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	@Override
	public String toString() {
		return "BoardHJ [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCount=" + boardCount + ", boardModifyDate=" + boardModifyDate + ", boardURL=" + boardURL
				+ ", boardWriter=" + boardWriter + ", boardCode=" + boardCode + "]";
	}
	
	
}
