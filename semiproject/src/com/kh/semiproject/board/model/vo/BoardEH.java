package com.kh.semiproject.board.model.vo;

import java.sql.Date;

public class BoardEH {
	
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date boardCreateDate;
	private Date boardModifyDate;
	private int boardCount;
	private String boardUrl;
	private String MemId;
	private int boardCode;

	public BoardEH() {
		// TODO Auto-generated constructor stub
	}

	public BoardEH(int boardNo, String boardTitle, String boardContent, Date boardCreateDate, Date boardModifyDate,
			int boardCount, String boardUrl, String memId, int boardCode) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardCount = boardCount;
		this.boardUrl = boardUrl;
		MemId = memId;
		this.boardCode = boardCode;
	}
	
	
	



	public BoardEH(int boardNo, String boardTitle, String boardContent, String boardUrl) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardUrl = boardUrl;
	}

	public BoardEH(int boardNo, String boardTitle, String boardContent, Date boardCreateDate, int boardCount,
			String boardUrl, String memId) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardCount = boardCount;
		this.boardUrl = boardUrl;
		MemId = memId;
	}

	public BoardEH(String boardTitle, String boardContent, String boardUrl) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardUrl = boardUrl;
	}

	public BoardEH(int boardNo, String boardTitle, Date boardCreateDate, int boardCount, String memId) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardCreateDate = boardCreateDate;
		this.boardCount = boardCount;
		MemId = memId;
	}
	
	
	

	public BoardEH(int boardNo, String boardTitle, String boardContent, Date boardCreateDate, int boardCount,
			String memId) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardCount = boardCount;
		MemId = memId;
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

	public String getMemId() {
		return MemId;
	}

	public void setMemId(String memId) {
		MemId = memId;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDate=" + boardCreateDate + ", boardModifyDate=" + boardModifyDate + ", boardCount="
				+ boardCount + ", boardUrl=" + boardUrl + ", MemId=" + MemId + ", boardCode=" + boardCode + "]";
	}
	
	
	
	
	
	
}