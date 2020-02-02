package com.kh.semiproject.ask.model.vo;

import java.sql.Date;

public class QA {
	
	private int qaNo;
	private String qaTitle;
	private String qaContent;
	private Date qaCreateDt;
	private Date qaModifyDt;
	private String qaStatus;
	
	public QA() {
	}
	
	public QA(int qaNo, String qaTitle, String qaContent, Date qaCreateDt, Date qaModifyDt, String qaStatus) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.qaCreateDt = qaCreateDt;
		this.qaModifyDt = qaModifyDt;
		this.qaStatus = qaStatus;
	}

	public int getQaNo() {
		return qaNo;
	}

	public void setQaNo(int qaNo) {
		this.qaNo = qaNo;
	}

	public String getQaTitle() {
		return qaTitle;
	}

	public void setQaTitle(String qaTitle) {
		this.qaTitle = qaTitle;
	}

	public String getQaContent() {
		return qaContent;
	}

	public void setQaContent(String qaContent) {
		this.qaContent = qaContent;
	}

	public Date getQaCreateDt() {
		return qaCreateDt;
	}

	public void setQaCreateDt(Date qaCreateDt) {
		this.qaCreateDt = qaCreateDt;
	}

	public Date getQaModifyDt() {
		return qaModifyDt;
	}

	public void setQaModifyDt(Date qaModifyDt) {
		this.qaModifyDt = qaModifyDt;
	}

	public String getQaStatus() {
		return qaStatus;
	}

	public void setQaStatus(String qaStatus) {
		this.qaStatus = qaStatus;
	}

	@Override
	public String toString() {
		return "QA [qaNo=" + qaNo + ", qaTitle=" + qaTitle + ", qaContent=" + qaContent + ", qaCreateDt=" + qaCreateDt
				+ ", qaModifyDt=" + qaModifyDt + ", qaStatus=" + qaStatus + "]";
	}
	
	
	
}
