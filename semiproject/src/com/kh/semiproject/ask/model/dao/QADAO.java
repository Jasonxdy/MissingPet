package com.kh.semiproject.ask.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.ask.model.vo.Ask;
import com.kh.semiproject.ask.model.vo.QA;
import com.kh.semiproject.mypage.model.dao.MypageDAO;
import static com.kh.semiproject.common.JDBCTemplate.*;

public class QADAO {

	private Properties prop = null;

	public QADAO() throws Exception {
		String fileName = QADAO.class.getResource("/com/kh/semiproject/sql/ask/qa-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
	
	/**
	 * QA 목록 조회용 DAO
	 * @param conn
	 * @return qaList
	 * @throws Exception
	 */
	public List<QA> selectQA(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<QA> qaList = new ArrayList<QA>();
		QA qa = null;
		
		String query = prop.getProperty("selectQA");
		
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				qa = new QA(rset.getInt("QA_NO"),
						rset.getString("QA_TITLE"),
						rset.getString("QA_CONTENT"), 
						rset.getDate("QA_CREATE_DT"), 
						rset.getDate("QA_MODIFY_DT"), 
						rset.getString("QA_STATUS"));
				
				qaList.add(qa);
			}
			
		} finally {
			
			close(rset);
			close(stmt);
		}
		
		return qaList;
	}


	
	
	
	
	
	/**
	 * 1:1 문의 등록용 DAO
	 * @param conn
	 * @param ask
	 * @return result
	 * @throws Exception
	 */
	public int insertAsk(Connection conn, Ask ask) throws Exception {
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAsk");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ask.getAskNo());
			pstmt.setString(2, ask.getAskTitle());
			pstmt.setString(3, ask.getAskContent());
			pstmt.setString(4, ask.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	
	
	/**
	 * 1:1 문의 등록 글 번호 조회 DAO
	 * @param conn
	 * @return askNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		int askNo = 0;
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				askNo = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return askNo;
	}


	
	
	
	
	/**
	 * 1:1 문의 답변 등록용 DAO
	 * @param conn
	 * @param ask
	 * @return result
	 * @throws Exception
	 */
	public int insertAnswer(Connection conn, Ask ask) throws Exception {
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAnswer");
		int result = 0;
		
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, ask.getAskNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
