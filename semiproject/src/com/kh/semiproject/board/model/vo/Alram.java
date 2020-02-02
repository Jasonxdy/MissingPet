package com.kh.semiproject.board.model.vo;

public class Alram {
	private String alramLocationTell;
	private String alramBreedTell;
	private String alramLocation;
	private String alramBreed;
	private String alramEmail;
	private int alramBoardType;
	
	public Alram() {}

	public Alram(String alramLocationTell, String alramBreedTell, String alramLocation, String alramBreed,
			String alramEmail, int alramBoardType) {
		super();
		this.alramLocationTell = alramLocationTell;
		this.alramBreedTell = alramBreedTell;
		this.alramLocation = alramLocation;
		this.alramBreed = alramBreed;
		this.alramEmail = alramEmail;
		this.alramBoardType = alramBoardType;
	}

	public String getAlramLocationTell() {
		return alramLocationTell;
	}

	public void setAlramLocationTell(String alramLocationTell) {
		this.alramLocationTell = alramLocationTell;
	}

	public String getAlramBreedTell() {
		return alramBreedTell;
	}

	public void setAlramBreedTell(String alramBreedTell) {
		this.alramBreedTell = alramBreedTell;
	}

	public String getAlramLocation() {
		return alramLocation;
	}

	public void setAlramLocation(String alramLocation) {
		this.alramLocation = alramLocation;
	}

	public String getAlramBreed() {
		return alramBreed;
	}

	public void setAlramBreed(String alramBreed) {
		this.alramBreed = alramBreed;
	}

	public String getAlramEmail() {
		return alramEmail;
	}

	public void setAlramEmail(String alramEmail) {
		this.alramEmail = alramEmail;
	}

	public int getAlramBoardType() {
		return alramBoardType;
	}

	public void setAlramBoardType(int alramBoardType) {
		this.alramBoardType = alramBoardType;
	}

	@Override
	public String toString() {
		return "Alram [alramLocationTell=" + alramLocationTell + ", alramBreedTell=" + alramBreedTell
				+ ", alramLocation=" + alramLocation + ", alramBreed=" + alramBreed + ", alramEmail=" + alramEmail
				+ ", alramBoardType=" + alramBoardType + "]";
	}

	
}
