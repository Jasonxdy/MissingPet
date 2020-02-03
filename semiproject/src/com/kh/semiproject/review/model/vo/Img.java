package com.kh.semiproject.review.model.vo;

import java.sql.Date;

public class Img {
	private int imgNo;
	private String imgOriginName;
	private String imgChangeName;
	private String imgPath;
	private Date imgCreateDt;
	private String imgStatus;
	private int imgLevel;
	private int boardNo;
	
	public Img() {
	}

	public Img(int imgNo, String imgOriginName, String imgChangeName, String imgPath, Date imgCreateDt,
			String imgStatus, int imgLevel, int boardNo) {
		super();
		this.imgNo = imgNo;
		this.imgOriginName = imgOriginName;
		this.imgChangeName = imgChangeName;
		this.imgPath = imgPath;
		this.imgCreateDt = imgCreateDt;
		this.imgStatus = imgStatus;
		this.imgLevel = imgLevel;
		this.boardNo = boardNo;
	}
	
	

	public Img(int imgNo, String imgOriginName, String imgChangeName, int imgLevel) {
		super();
		this.imgNo = imgNo;
		this.imgOriginName = imgOriginName;
		this.imgChangeName = imgChangeName;
		this.imgLevel = imgLevel;
	}
	
	

	public Img(int imgNo, String imgChangeName, int boardNo) {
		super();
		this.imgNo = imgNo;
		this.imgChangeName = imgChangeName;
		this.boardNo = boardNo;
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public String getImgOriginName() {
		return imgOriginName;
	}

	public void setImgOriginName(String imgOriginName) {
		this.imgOriginName = imgOriginName;
	}

	public String getImgChangeName() {
		return imgChangeName;
	}

	public void setImgChangeName(String imgChangeName) {
		this.imgChangeName = imgChangeName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Date getImgCreateDt() {
		return imgCreateDt;
	}

	public void setImgCreateDt(Date imgCreateDt) {
		this.imgCreateDt = imgCreateDt;
	}

	public String getImgStatus() {
		return imgStatus;
	}

	public void setImgStatus(String imgStatus) {
		this.imgStatus = imgStatus;
	}

	public int getImgLevel() {
		return imgLevel;
	}

	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Img [imgNo=" + imgNo + ", imgOriginName=" + imgOriginName + ", imgChangeName=" + imgChangeName
				+ ", imgPath=" + imgPath + ", imgCreateDt=" + imgCreateDt + ", imgStatus=" + imgStatus + ", imgLevel="
				+ imgLevel + ", BoardNo=" + boardNo + "]";
	}
	
	
}
