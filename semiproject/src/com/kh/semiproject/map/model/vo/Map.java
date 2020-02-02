package com.kh.semiproject.map.model.vo;

public class Map {
	private int boardNo;
	private double mapLatitude;
	private double mapLongitude;
	private String mapAddress;
	
	public Map() {
		// TODO Auto-generated constructor stub
	}

	public Map(int boardNo, double mapLatitude, double mapLongitude, String mapAddress) {
		super();
		this.boardNo = boardNo;
		this.mapLatitude = mapLatitude;
		this.mapLongitude = mapLongitude;
		this.mapAddress = mapAddress;
	}
	
	public Map(double mapLatitude, double mapLongitude, String mapAddress) {
		super();
		this.mapLatitude = mapLatitude;
		this.mapLongitude = mapLongitude;
		this.mapAddress = mapAddress;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public double getMapLatitude() {
		return mapLatitude;
	}

	public void setMapLatitude(double mapLatitude) {
		this.mapLatitude = mapLatitude;
	}

	public double getMapLongitude() {
		return mapLongitude;
	}

	public void setMapLongitude(double mapLongitude) {
		this.mapLongitude = mapLongitude;
	}

	public String getMapAddress() {
		return mapAddress;
	}

	public void setMapAddress(String mapAddress) {
		this.mapAddress = mapAddress;
	}

	@Override
	public String toString() {
		return "map [boardNo=" + boardNo + ", mapLatitude=" + mapLatitude + ", mapLongitude=" + mapLongitude
				+ ", mapAddress=" + mapAddress + "]";
	}
	
	
}
