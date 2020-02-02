package com.kh.semiproject.mypage.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Reply {
	
	private int commNo; // 댓글 번호
	private int boardNo; // 댓글이 작성된 게시글 번호
	private int boardCode; // 댓글이 작성된 게시판 코드
	private String boardTitle; // 댓글이 작성된 게시글 제목
	private String commContent; // 댓글 내용
	private Date commModifyDt; // 댓글 작성 시간
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(int commNo, int boardNo, int boardCode, String boardTitle, String commContent, Date commModifyDt) {
		super();
		this.commNo = commNo;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
		this.boardTitle = boardTitle;
		this.commContent = commContent;
		this.commModifyDt = commModifyDt;
	}

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public Date getCommModifyDt() {
		return commModifyDt;
	}

	public void setCommModifyDt(Date commModifyDt) {
		this.commModifyDt = commModifyDt;
	}

	@Override
	public String toString() {
		return "Reply [commNo=" + commNo + ", boardNo=" + boardNo + ", boardCode=" + boardCode + ", boardTitle="
				+ boardTitle + ", commContent=" + commContent + ", commModifyDt=" + commModifyDt + "]";
	}
	
	
	
	
}