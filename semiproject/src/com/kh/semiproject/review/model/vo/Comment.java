package com.kh.semiproject.review.model.vo;

public class Comment {
	private int commentNo;
	private String commentContent;
	private String commentCreatDt;
	private String commentModifyDt;
	private String memberId;
	private int boardNo;
	private String memberProImg;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentNo, String commentContent, String commentCreatDt, String commentModifyDt, String memberId,
			int boardNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentCreatDt = commentCreatDt;
		this.commentModifyDt = commentModifyDt;
		this.memberId = memberId;
		this.boardNo = boardNo;
	}

	public Comment(String commentContent, int boardNo) {
		super();
		this.commentContent = commentContent;
		this.boardNo = boardNo;
	}

	public Comment(int commentNo, String commentContent) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
	}

	public Comment(int commentNo, String commentContent, String commentModifyDt, String memberId, String memberProImg) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentModifyDt = commentModifyDt;
		this.memberId = memberId;
		this.memberProImg = memberProImg;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentCreatDt() {
		return commentCreatDt;
	}

	public void setCommentCreatDt(String commentCreatDt) {
		this.commentCreatDt = commentCreatDt;
	}

	public String getCommentModifyDt() {
		return commentModifyDt;
	}

	public void setCommentModifyDt(String commentModifyDt) {
		this.commentModifyDt = commentModifyDt;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	
	public String getMemberProImg() {
		return memberProImg;
	}

	public void setMemberProImg(String memberProImg) {
		this.memberProImg = memberProImg;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentCreatDt="
				+ commentCreatDt + ", commentModifyDt=" + commentModifyDt + ", memberId=" + memberId + ", boardNo="
				+ boardNo + ", memberProImg=" + memberProImg + "]";
	}

	
	
	
	
}
