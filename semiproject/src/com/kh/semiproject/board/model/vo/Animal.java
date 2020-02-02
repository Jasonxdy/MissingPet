package com.kh.semiproject.board.model.vo;

public class Animal {
	private int animalCode;
	private String animalGender;
	private String animalType;
	private String animalBreed;
	private String animalStatus;
	
	public Animal () {}

	public Animal(int animalCode, String animalGender, String animalType, String animalBreed) {
		super();
		this.animalCode = animalCode;
		this.animalGender = animalGender;
		this.animalType = animalType;
		this.animalBreed = animalBreed;
	}

	public Animal(String animalGender, String animalType, String animalBreed) {
		super();
		this.animalGender = animalGender;
		this.animalType = animalType;
		this.animalBreed = animalBreed;
	}



	public Animal(int animalCode, String animalGender, String animalType, String animalBreed, String animalStatus) {
		super();
		this.animalCode = animalCode;
		this.animalGender = animalGender;
		this.animalType = animalType;
		this.animalBreed = animalBreed;
		this.animalStatus = animalStatus;
	}

	public int getAnimalCode() {
		return animalCode;
	}

	public void setAnimalCode(int animalCode) {
		this.animalCode = animalCode;
	}

	public String getAnimalGender() {
		return animalGender;
	}

	public void setAnimalGender(String animalGender) {
		this.animalGender = animalGender;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getAnimalBreed() {
		return animalBreed;
	}

	public void setAnimalBreed(String animalBreed) {
		this.animalBreed = animalBreed;
	}

	public String getAnimalStatus() {
		return animalStatus;
	}

	public void setAnimalStatus(String animalStatus) {
		this.animalStatus = animalStatus;
	}

	@Override
	public String toString() {
		return "Animal [animalCode=" + animalCode + ", animalGender=" + animalGender + ", animalType=" + animalType
				+ ", animalBreed=" + animalBreed + ", animalStatus=" + animalStatus + "]";
	}
	
	

		
}
