package com.kh.semiproject.mypage.model.vo;

import java.sql.Date;

/**
 * @author Administrator
 *
 */
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private Date boardModifyDate;
	private int boardCode;
	
	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int boardNo, String boardTitle, Date boardModifyDate, int boardCode) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardModifyDate = boardModifyDate;
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

	public Date getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Date boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardModifyDate=" + boardModifyDate
				+ ", boardCode=" + boardCode + "]";
	}
	
	
	
	
	
	
	
	
}
