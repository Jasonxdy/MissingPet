package com.kh.semiproject.qaBoard.model.vo;

import java.sql.Date;

public class QnABoard {
	private int qaNo;
	private String qaTitle;
	private String qaContent;
	private Date qaCreateDt;
	private Date qaModifyDt;
	
	
	public QnABoard() {}


	public QnABoard(int qaNo, String qaTitle, String qaContent, Date qaCreateDt, Date qaModifyDt) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.qaCreateDt = qaCreateDt;
		this.qaModifyDt = qaModifyDt;
	}


	public QnABoard(int qaNo, String qaTitle, String qaContent, Date qaModifyDt) {
		super();
		this.qaNo = qaNo;
		this.qaTitle = qaTitle;
		this.qaContent = qaContent;
		this.qaModifyDt = qaModifyDt;
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


	@Override
	public String toString() {
		return "QnABoard [qaNo=" + qaNo + ", qaTitle=" + qaTitle + ", qaContent=" + qaContent + ", qaCreateDt=" + qaCreateDt
				+ ", qaModifyDt=" + qaModifyDt + "]";
	}
	
	
	
	
	
	
	
	
}
