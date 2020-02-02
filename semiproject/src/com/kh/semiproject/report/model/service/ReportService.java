package com.kh.semiproject.report.model.service;
import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.report.model.dao.ReportDao;
import com.kh.semiproject.report.model.vo.Report;

public class ReportService {
	

	/** 관리자페이지 신고 목록 조회용
	 * @return reportBoardList
	 * @throws Exception
	 */
	public List<Report> ReportBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Report> reportBoardList = new ReportDao().reportBoardList(conn);
						
		return reportBoardList;
	}

	
	
	
	/** 관리자페이지 신고 게시판 검색용
	 * @param searchKey
	 * @param searchValue
	 * @return searchReport
	 * @throws Exception
	 */
	public List<Report> searchReport(String searchKey, String searchValue) throws Exception {

		
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "searchReportTitle" : condition = " REPORT_TITLE LIKE " + searchValue; break;
		case "searchReportContent" : condition = " REPORT_CONTENT LIKE " + searchValue; break;

		}
				
		List<Report> searchReport = new ReportDao().searchReport(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		
		return searchReport;
	}

}
