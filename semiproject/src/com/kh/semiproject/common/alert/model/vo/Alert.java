package com.kh.semiproject.common.alert.model.vo;

public class Alert {
	
	private int alertNo;
	private String memberId;
	private String alertStatus;
	private String alertContent;
	private String alertURL;
	private String alertType;
	
	public Alert() {
		// TODO Auto-generated constructor stub
	}

	
	
	public Alert(String memberId, String alertContent, String alertURL) {
		super();
		this.memberId = memberId;
		this.alertContent = alertContent;
		this.alertURL = alertURL;
	}



	public Alert(int alertNo, String memberId, String alertStatus, String alertContent, String alertURL,
			String alertType) {
		super();
		this.alertNo = alertNo;
		this.memberId = memberId;
		this.alertStatus = alertStatus;
		this.alertContent = alertContent;
		this.alertURL = alertURL;
		this.alertType = alertType;
	}

	public int getAlertNo() {
		return alertNo;
	}

	public void setAlertNo(int alertNo) {
		this.alertNo = alertNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

	public String getAlertContent() {
		return alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	public String getAlertURL() {
		return alertURL;
	}

	public void setAlertURL(String alertURL) {
		this.alertURL = alertURL;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	@Override
	public String toString() {
		return "Alert [alertNo=" + alertNo + ", memberId=" + memberId + ", alertStatus=" + alertStatus
				+ ", alertContent=" + alertContent + ", alertURL=" + alertURL + ", alertType=" + alertType + "]";
	}
	
	
	

}
