package com.kh.semiproject.board.model.vo;

import java.sql.Timestamp;

public class M_Comment {
	
	private int commNo;
	private String commContent;
	private Timestamp commCreateDate;
	private Timestamp commModifyDate;
	private String commStatus;
	private String memId;
	private int boardNo;
	
	public M_Comment() {
		// TODO Auto-generated constructor stub
	}

	public M_Comment(int commNo, String commContent, Timestamp commCreateDate, Timestamp commModifyDate,
			String commStatus, String memId, int boardNo) {
		super();
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreateDate = commCreateDate;
		this.commModifyDate = commModifyDate;
		this.commStatus = commStatus;
		this.memId = memId;
		this.boardNo = boardNo;
	}
	
	

	public M_Comment(int commNo, String commContent, Timestamp commCreateDate, String memId, int boardNo) {
		super();
		this.commNo = commNo;
		this.commContent = commContent;
		this.commCreateDate = commCreateDate;
		this.memId = memId;
		this.boardNo = boardNo;
	}

	public M_Comment(String commContent, int boardNo) {
		super();
		this.commContent = commContent;
		this.boardNo = boardNo;
	}

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public Timestamp getCommCreateDate() {
		return commCreateDate;
	}

	public void setCommCreateDate(Timestamp commCreateDate) {
		this.commCreateDate = commCreateDate;
	}

	public Timestamp getCommModifyDate() {
		return commModifyDate;
	}

	public void setCommModifyDate(Timestamp commModifyDate) {
		this.commModifyDate = commModifyDate;
	}

	public String getCommStatus() {
		return commStatus;
	}

	public void setCommStatus(String commStatus) {
		this.commStatus = commStatus;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "M_Comment [commNo=" + commNo + ", commContent=" + commContent + ", commCreateDate=" + commCreateDate
				+ ", commModifyDate=" + commModifyDate + ", commStatus=" + commStatus + ", memId=" + memId
				+ ", boardNo=" + boardNo + "]";
	}
	
	
	
	
	
	
	

}
