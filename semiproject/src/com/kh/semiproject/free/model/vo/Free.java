package com.kh.semiproject.free.model.vo;

public class Free {

	
	private int boardNo;
	private String freeCategory;
	
	public Free() {
		// TODO Auto-generated constructor stub
	}

	public Free(int boardNo, String freeCategory) {
		super();
		this.boardNo = boardNo;
		this.freeCategory = freeCategory;
	}
	
	

	
	
	
	public Free(String freeCategory) {
		super();
		this.freeCategory = freeCategory;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFreeCategory() {
		return freeCategory;
	}

	public void setFreeCategory(String freeCategory) {
		this.freeCategory = freeCategory;
	}

	@Override
	public String toString() {
		return "Free [boardNo=" + boardNo + ", freeCategory=" + freeCategory + "]";
	}
	
	
	
	
}
