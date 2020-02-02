package com.kh.semiproject.findBoard.model.vo;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class FindBoard {
	private int boardNo;
	private String fBoardLocation;
	private int fBoardReward;
	private String fBoardPhone;
	private Date fBoardDate;
	private String fBoardMap;
	private String fBoardLocationTell;
	private String fBoardBreedTell;
	private String fBoardCommentTell;
	private int animalCode;
	
	public FindBoard() {}
	
	public FindBoard(String fBoardLocation, int fBoardReward, String fBoardPhone, Date fBoardDate, String fBoardMap,
			String fBoardLocationTell, String fBoardBreedTell, String fBoardCommentTell) {
		super();
		this.fBoardLocation = fBoardLocation;
		this.fBoardReward = fBoardReward;
		this.fBoardPhone = fBoardPhone;
		this.fBoardDate = fBoardDate;
		this.fBoardMap = fBoardMap;
		this.fBoardLocationTell = fBoardLocationTell;
		this.fBoardBreedTell = fBoardBreedTell;
		this.fBoardCommentTell = fBoardCommentTell;
	}



	public FindBoard(int boardNo, String fBoardLocation, int fBoardReward, String fBoardPhone, Date fBoardDate,
			String fBoardMap, String fBoardLocationTell, String fBoardBreedTell, String fBoardCommentTell,
			int animalCode) {
		super();
		this.boardNo = boardNo;
		this.fBoardLocation = fBoardLocation;
		this.fBoardReward = fBoardReward;
		this.fBoardPhone = fBoardPhone;
		this.fBoardDate = fBoardDate;
		this.fBoardMap = fBoardMap;
		this.fBoardLocationTell = fBoardLocationTell;
		this.fBoardBreedTell = fBoardBreedTell;
		this.fBoardCommentTell = fBoardCommentTell;
		this.animalCode = animalCode;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getfBoardLocation() {
		return fBoardLocation;
	}

	public void setfBoardLocation(String fBoardLocation) {
		this.fBoardLocation = fBoardLocation;
	}

	public int getfBoardReward() {
		return fBoardReward;
	}

	public void setfBoardReward(int fBoardReward) {
		this.fBoardReward = fBoardReward;
	}

	public String getfBoardPhone() {
		return fBoardPhone;
	}

	public void setfBoardPhone(String fBoardPhone) {
		this.fBoardPhone = fBoardPhone;
	}

	public Date getfBoardDate() {
		return fBoardDate;
	}

	public void setfBoardDate(Date fBoardDate) {
		this.fBoardDate = fBoardDate;
	}

	public String getfBoardMap() {
		return fBoardMap;
	}

	public void setfBoardMap(String fBoardMap) {
		this.fBoardMap = fBoardMap;
	}

	public String getfBoardLocationTell() {
		return fBoardLocationTell;
	}

	public void setfBoardLocationTell(String fBoardLocationTell) {
		this.fBoardLocationTell = fBoardLocationTell;
	}

	public String getfBoardBreedTell() {
		return fBoardBreedTell;
	}

	public void setfBoardBreedTell(String fBoardBreedTell) {
		this.fBoardBreedTell = fBoardBreedTell;
	}

	public String getfBoardCommentTell() {
		return fBoardCommentTell;
	}

	public void setfBoardCommentTell(String fBoardCommentTell) {
		this.fBoardCommentTell = fBoardCommentTell;
	}

	public int getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(int animalCode) {
		this.animalCode = animalCode;
	}

	@Override
	public String toString() {
		return "FindBoard [boardNo=" + boardNo + ", fBoardLocation=" + fBoardLocation + ", fBoardReward=" + fBoardReward
				+ ", fBoardPhone=" + fBoardPhone + ", fBoardDate=" + fBoardDate + ", fBoardMap=" + fBoardMap
				+ ", fBoardLocationTell=" + fBoardLocationTell + ", fBoardBreedTell=" + fBoardBreedTell
				+ ", fBoardCommentTell=" + fBoardCommentTell + ", animalCode=" + animalCode + "]";
	}


}
