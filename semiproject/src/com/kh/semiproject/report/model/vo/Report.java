package com.kh.semiproject.report.model.vo;

import java.sql.Date;

public class Report {

	 private int reportNo;
	   private String reportTitle;
	   private String reportContent;
	   private Date reportCreateDt;
	   private String memberId;
	   private int boardNo;
	   private int boardCode;
	   
	   public Report() {}

	   
	   
	   
	   public Report(int reportNo, String reportTitle, String reportContent, String memberId, int boardNo, int boardCode) {
		super();
		this.reportNo = reportNo;
		this.reportTitle = reportTitle;
		this.reportContent = reportContent;
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.boardCode = boardCode;
	}




	public Report(int reportNo, String reportTitle, String reportContent, Date reportCreateDt, String memberId,
	         int boardNo) {
	      super();
	      this.reportNo = reportNo;
	      this.reportTitle = reportTitle;
	      this.reportContent = reportContent;
	      this.reportCreateDt = reportCreateDt;
	      this.memberId = memberId;
	      this.boardNo = boardNo;
	   }

	   public Report(String reportTitle, String reportContent, String memberId, int boardNo) {
	      super();
	      this.reportTitle = reportTitle;
	      this.reportContent = reportContent;
	      this.memberId = memberId;
	      this.boardNo = boardNo;
	   }

	   public int getReportNo() {
	      return reportNo;
	   }

	   public void setReportNo(int reportNo) {
	      this.reportNo = reportNo;
	   }

	   public String getReportTitle() {
	      return reportTitle;
	   }

	   public void setReportTitle(String reportTitle) {
	      this.reportTitle = reportTitle;
	   }

	   public String getReportContent() {
	      return reportContent;
	   }

	   public void setReportContent(String reportContent) {
	      this.reportContent = reportContent;
	   }

	   public Date getReportCreateDt() {
	      return reportCreateDt;
	   }

	   public void setReportCreateDt(Date reportCreateDt) {
	      this.reportCreateDt = reportCreateDt;
	   }

	   public String getmemberId() {
	      return memberId;
	   }

	   public void setmemberId(String memberId) {
	      this.memberId = memberId;
	   }

	   public int getBoardNo() {
	      return boardNo;
	   }

	   public void setBoardNo(int boardNo) {
	      this.boardNo = boardNo;
	   }
	   
	   

	   public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	public int getBoardCode() {
		return boardCode;
	}




	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}




	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reportTitle=" + reportTitle + ", reportContent=" + reportContent
				+ ", reportCreateDt=" + reportCreateDt + ", memberId=" + memberId + ", boardNo=" + boardNo
				+ ", boardCode=" + boardCode + "]";
	}





	

	
}
