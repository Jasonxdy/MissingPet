package com.kh.semiproject.report.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.report.model.vo.Report;


public class ReportDao {
	private Properties prop = null;
	
	
	public ReportDao() throws Exception {
		
		String fileName = ReportDao.class.getResource("/com/kh/semiproject/sql/report/report-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	
	

	/** 관리자페이지 신고목록 조회용
	 * @param conn
	 * @return reportBoardList
	 * @throws Exception
	 */
	public List<Report> reportBoardList(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Report> reportBoardList = null;
		
		String query = prop.getProperty("reportBoardList");
		
		try {
		
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			reportBoardList = new ArrayList<Report>();
			Report report = null;
		
			while(rset.next()) {
				
				report = new Report(rset.getInt("REPORT_NO"),
									rset.getString("REPORT_TITLE"),
									rset.getString("REPORT_CONTENT"),
									rset.getString("MEM_ID"));
					
				
				reportBoardList.add(report);
			}
							
		} finally {
			close(rset);
			close(stmt);
		}
		
		System.out.println("DAO 조회 완료");
		
		return reportBoardList;
	}




	
	
	/** 관리자페이지 신고게시판 검색용
	 * @param conn
	 * @param condition
	 * @return searchReport
	 * @throws Exception
	 */
	public List<Report> searchReport(Connection conn, String condition) throws Exception {
	
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Report> searchReport = null;
		
		String query1 = prop.getProperty("searchReport1");
		String query2 = prop.getProperty("searchReport2");
		
		try{
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query1 + condition + query2);
			
			searchReport = new ArrayList<Report>();
			
			Report report = null;
			
			while(rset.next()) {
				report = new Report(rset.getInt("REPORT_NO"),
						rset.getString("REPORT_TITLE"),
						rset.getString("REPORT_CONTENT"),
						rset.getString("MEM_ID"));
		
	
				searchReport.add(report);
			}

		} finally {
			close(rset);
			close(stmt);
		}
		
		System.out.println("DAO 검색 성공");
		return searchReport;
	}
	
	
	
	
	
}
