package com.kh.semiproject.qaBoard.model.dao;
import static com.kh.semiproject.common.JDBCTemplate.*;
import static com.kh.semiproject.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.member.model.dao.MemberDao;
import com.kh.semiproject.qaBoard.model.vo.QnABoard;

public class QnABoardDao {
	
	private Properties prop = null;
	
	
	public QnABoardDao() throws Exception {
		
		String fileName = QnABoardDao.class.getResource("/com/kh/semiproject/sql/qa/qa-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
	
	
	/** 관리자페이지 Q&A게시판 목록조회용
	 * @param conn
	 * @return qaBoardList
	 * @throws Exception
	 */
	public List<QnABoard> qaBoardList(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<QnABoard> qaBoardList = null;
		
		String query = prop.getProperty("qaBoardList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			qaBoardList = new ArrayList<QnABoard>();
			QnABoard qa = null;
			
			while(rset.next()) {
				qa = new QnABoard(rset.getInt("QA_NO"),
						rset.getString("QA_TITLE"),
						rset.getString("QA_CONTENT"),
						rset.getDate("QA_MODIFY_DT"));
				
				qaBoardList.add(qa);					
			}				
			
		} finally {
			close(rset);
			close(stmt);				
		}	
		
		System.out.println("DAO 조회 성공");
		return qaBoardList;
	}



	
	

	/** 관리자 페이지 QnA 글 등록용
	 * @param conn
	 * @param QnATitle
	 * @param QnAContent
	 * @return result
	 * @throws Exception
	 */
	public int insertQnA(Connection conn, String QnATitle, String QnAContent) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQnA");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, QnATitle);
			pstmt.setString(2, QnAContent);
			
			result = pstmt.executeUpdate();
		
		} finally {
			close(pstmt);
		}
		System.out.println("DAO QnA 등록 성공");
		return result;
	}




	/** 관리자 페이지 QnA 글 수정용
	 * @param conn
	 * @param UpdateQnANo 
	 * @param updateQnAContent
	 * @param updateQnATitle
	 * @return result
	 * @throws Exception
	 */
	public int updateQnA(Connection conn, int UpdateQnANo, String updateQnAContent, String updateQnATitle) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateQnA");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, updateQnATitle);
			pstmt.setString(2, updateQnAContent);
			pstmt.setInt(3, UpdateQnANo);
			
			result = pstmt.executeUpdate();
		
		} finally {
			close(pstmt);
		}
		System.out.println("DAO QnA 수정 성공");
		return result;
	}



	
	
	
	/** 관리자 페이지 QnA 글 삭제용
	 * @param conn
	 * @param deleteQnANo
	 * @return result
	 * @throws Exception
	 */
	public int deleteQnA(Connection conn, int deleteQnANo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteQnA");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, deleteQnANo);
			
			result = pstmt.executeUpdate();
		
		} finally {
			close(pstmt);
		}
		System.out.println("DAO QnA 수정 성공");
		return result;
	}



	
	
	/** 관리자 페이지 QnA 검색용
	 * @param conn
	 * @param condition
	 * @return searchQnA
	 * @throws Exception
	 */
	public List<QnABoard> searchQnA(Connection conn, String condition) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		List<QnABoard> searchQnA = null;
		
		String query1 = prop.getProperty("searchQnA1");
		String query2 = prop.getProperty("searchQnA2");
		
		try{
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query1 + condition + query2);
			
			searchQnA = new ArrayList<QnABoard>();
			
			QnABoard QnAboard = null;
			
				while(rset.next()) {
					QnAboard = new QnABoard(rset.getInt("QA_NO"),
							rset.getString("QA_TITLE"),
							rset.getString("QA_CONTENT"),
							rset.getDate("QA_MODIFY_DT"));
				
					searchQnA.add(QnAboard);
				}				
	

		} finally {
			close(rset);
			close(stmt);
		}
		System.out.println("DAO 조회 성공");
		
		return searchQnA;
	}
}
