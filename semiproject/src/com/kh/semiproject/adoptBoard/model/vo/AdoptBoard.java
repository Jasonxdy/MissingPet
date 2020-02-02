package com.kh.semiproject.adoptBoard.model.vo;

public class AdoptBoard {
	private int boardNo;
	private String aBoardLocation;
	private int aBoardCost;
	private String aBoardPhone;
	private String aBoardMap;
	private String aBoardNeutral;
	private String aBoardVac;
	private String aBoardHealth;
	private String aBoardDone;
	private int animalCode;
	
	public AdoptBoard () {}
	
	
	
	public AdoptBoard(String aBoardLocation, int aBoardCost, String aBoardPhone, String aBoardMap, String aBoardNeutral,
			String aBoardVac, String aBoardHealth, String aBoardDone) {
		super();
		this.aBoardLocation = aBoardLocation;
		this.aBoardCost = aBoardCost;
		this.aBoardPhone = aBoardPhone;
		this.aBoardMap = aBoardMap;
		this.aBoardNeutral = aBoardNeutral;
		this.aBoardVac = aBoardVac;
		this.aBoardHealth = aBoardHealth;
		this.aBoardDone = aBoardDone;
	}



	public AdoptBoard(String aBoardLocation, int aBoardCost, String aBoardPhone, String aBoardMap, String aBoardNeutral,
			String aBoardVac, String aBoardHealth) {
		super();
		this.aBoardLocation = aBoardLocation;
		this.aBoardCost = aBoardCost;
		this.aBoardPhone = aBoardPhone;
		this.aBoardMap = aBoardMap;
		this.aBoardNeutral = aBoardNeutral;
		this.aBoardVac = aBoardVac;
		this.aBoardHealth = aBoardHealth;
	}



	public AdoptBoard(int boardNo, String aBoardLocation, int aBoardCost, String aBoardPhone, String aBoardMap,
			String aBoardNeutral, String aBoardVac, String aBoardHealth, String aBoardDone, int animalCode) {
		super();
		this.boardNo = boardNo;
		this.aBoardLocation = aBoardLocation;
		this.aBoardCost = aBoardCost;
		this.aBoardPhone = aBoardPhone;
		this.aBoardMap = aBoardMap;
		this.aBoardNeutral = aBoardNeutral;
		this.aBoardVac = aBoardVac;
		this.aBoardHealth = aBoardHealth;
		this.aBoardDone = aBoardDone;
		this.animalCode = animalCode;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getaBoardLocation() {
		return aBoardLocation;
	}

	public void setaBoardLocation(String aBoardLocation) {
		this.aBoardLocation = aBoardLocation;
	}

	public int getaBoardCost() {
		return aBoardCost;
	}

	public void setaBoardCost(int aBoardCost) {
		this.aBoardCost = aBoardCost;
	}

	public String getaBoardPhone() {
		return aBoardPhone;
	}

	public void setaBoardPhone(String aBoardPhone) {
		this.aBoardPhone = aBoardPhone;
	}

	public String getaBoardMap() {
		return aBoardMap;
	}

	public void setaBoardMap(String aBoardMap) {
		this.aBoardMap = aBoardMap;
	}

	public String getaBoardNeutral() {
		return aBoardNeutral;
	}

	public void setaBoardNeutral(String aBoardNeutral) {
		this.aBoardNeutral = aBoardNeutral;
	}

	public String getaBoardVac() {
		return aBoardVac;
	}

	public void setaBoardVac(String aBoardVac) {
		this.aBoardVac = aBoardVac;
	}

	public String getaBoardHealth() {
		return aBoardHealth;
	}

	public void setaBoardHealth(String aBoardHealth) {
		this.aBoardHealth = aBoardHealth;
	}

	public String getaBoardDone() {
		return aBoardDone;
	}

	public void setaBoardDone(String aBoardDone) {
		this.aBoardDone = aBoardDone;
	}

	public int getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(int animalCode) {
		this.animalCode = animalCode;
	}

	@Override
	public String toString() {
		return "AdoptBoard [boardNo=" + boardNo + ", aBoardLocation=" + aBoardLocation + ", aBoardCost=" + aBoardCost
				+ ", aBoardPhone=" + aBoardPhone + ", aBoardMap=" + aBoardMap + ", aBoardNeutral=" + aBoardNeutral
				+ ", aBoardVac=" + aBoardVac + ", aBoardHealth=" + aBoardHealth + ", aBoardDone=" + aBoardDone
				+ ", animalCode=" + animalCode + "]";
	}

}
