package com.kh.semiproject.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date boardCreateDt;
	private Date boardModifyDt;
	private int boardCount;
	private String boardUrl;
	private String boardStatus;
	private String memberId;
	private int boardCode;
	
	
public Board() {}


	
	
	
	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDt, String memberId) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDt = boardCreateDt;
		this.memberId = memberId;
	}





	public Board(int boardNo, String boardTitle, String boardContent, Date boardCreateDt, Date boardModifyDt,
			int boardCount, String boardUrl, String boardStatus, String memberId, int boardCode) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDt = boardCreateDt;
		this.boardModifyDt = boardModifyDt;
		this.boardCount = boardCount;
		this.boardUrl = boardUrl;
		this.boardStatus = boardStatus;
		this.memberId = memberId;
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


	public Date getBoardCreateDt() {
		return boardCreateDt;
	}


	public void setBoardCreateDt(Date boardCreateDt) {
		this.boardCreateDt = boardCreateDt;
	}


	public Date getBoardModifyDt() {
		return boardModifyDt;
	}


	public void setBoardModifyDt(Date boardModifyDt) {
		this.boardModifyDt = boardModifyDt;
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


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDt=" + boardCreateDt + ", boardModifyDt=" + boardModifyDt + ", boardCount=" + boardCount
				+ ", boardUrl=" + boardUrl + ", boardStatus=" + boardStatus + ", memberId=" + memberId + ", boardCode="
				+ boardCode + "]";
	}
}
