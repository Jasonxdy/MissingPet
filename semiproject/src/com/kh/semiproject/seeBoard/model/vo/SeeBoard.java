package com.kh.semiproject.seeBoard.model.vo;

import java.sql.Date;

public class SeeBoard {
	private int boardNo;
	private String sBoardLocation;
	private String sBoardPhone;
	private Date sBoardDate;
	private String sBoardMap;
	private String sBoardLocationTell;
	private String sBoardBreedTell;
	private String sBoardCommentTell;
	private int animalCode;
	
	public SeeBoard() {}

	public SeeBoard(String sBoardLocation, String sBoardPhone, Date sBoardDate, String sBoardMap,
			String sBoardLocationTell, String sBoardBreedTell, String sBoardCommentTell) {
		super();
		this.sBoardLocation = sBoardLocation;
		this.sBoardPhone = sBoardPhone;
		this.sBoardDate = sBoardDate;
		this.sBoardMap = sBoardMap;
		this.sBoardLocationTell = sBoardLocationTell;
		this.sBoardBreedTell = sBoardBreedTell;
		this.sBoardCommentTell = sBoardCommentTell;
	}


	public SeeBoard(int boardNo, String sBoardLocation, String sBoardPhone, Date sBoardDate, String sBoardMap,
			String sBoardLocationTell, String sBoardBreedTell, String sBoardCommentTell, int animalCode) {
		super();
		this.boardNo = boardNo;
		this.sBoardLocation = sBoardLocation;
		this.sBoardPhone = sBoardPhone;
		this.sBoardDate = sBoardDate;
		this.sBoardMap = sBoardMap;
		this.sBoardLocationTell = sBoardLocationTell;
		this.sBoardBreedTell = sBoardBreedTell;
		this.sBoardCommentTell = sBoardCommentTell;
		this.animalCode = animalCode;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getsBoardLocation() {
		return sBoardLocation;
	}

	public void setsBoardLocation(String sBoardLocation) {
		this.sBoardLocation = sBoardLocation;
	}

	public String getsBoardPhone() {
		return sBoardPhone;
	}

	public void setsBoardPhone(String sBoardPhone) {
		this.sBoardPhone = sBoardPhone;
	}

	public Date getsBoardDate() {
		return sBoardDate;
	}

	public void setsBoardDate(Date sBoardDate) {
		this.sBoardDate = sBoardDate;
	}

	public String getsBoardMap() {
		return sBoardMap;
	}

	public void setsBoardMap(String sBoardMap) {
		this.sBoardMap = sBoardMap;
	}

	public String getsBoardLocationTell() {
		return sBoardLocationTell;
	}

	public void setsBoardLocationTell(String sBoardLocationTell) {
		this.sBoardLocationTell = sBoardLocationTell;
	}

	public String getsBoardBreedTell() {
		return sBoardBreedTell;
	}

	public void setsBoardBreedTell(String sBoardBreedTell) {
		this.sBoardBreedTell = sBoardBreedTell;
	}

	public String getsBoardCommentTell() {
		return sBoardCommentTell;
	}

	public void setsBoardCommentTell(String sBoardCommentTell) {
		this.sBoardCommentTell = sBoardCommentTell;
	}

	public int getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(int animalCode) {
		this.animalCode = animalCode;
	}

	@Override
	public String toString() {
		return "SeeBoard [boardNo=" + boardNo + ", sBoardLocation=" + sBoardLocation + ", sBoardPhone=" + sBoardPhone
				+ ", sBoardDate=" + sBoardDate + ", sBoardMap=" + sBoardMap + ", sBoardLocationTell="
				+ sBoardLocationTell + ", sBoardBreedTell=" + sBoardBreedTell + ", sBoardCommentTell="
				+ sBoardCommentTell + ", animalCode=" + animalCode + "]";
	}

	
}
