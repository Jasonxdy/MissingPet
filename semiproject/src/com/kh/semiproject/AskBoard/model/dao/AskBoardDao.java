package com.kh.semiproject.AskBoard.model.dao;

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

import com.kh.semiproject.AskBoard.model.vo.Answer;
import com.kh.semiproject.AskBoard.model.vo.AskBoard;
import com.kh.semiproject.board.model.vo.Board;


public class AskBoardDao {
	
private Properties prop = null;
	
	
	public AskBoardDao() throws Exception {
		
		String fileName = AskBoardDao.class.getResource("/com/kh/semiproject/sql/ask/ask-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	
	
	
	
	/** 관리자 페이지 1:1 문의 
	 * @return AskBoardList
	 * @throws Exception
	 */
	public List<AskBoard> AskBoardList(Connection conn) throws Exception{
		Statement stmt = null;
		ResultSet rset = null;
		List<AskBoard> AskBoardList = null;
		
		String query = prop.getProperty("AskBoardList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			
			AskBoardList = new ArrayList<AskBoard>();
			AskBoard ask = null;
			
			while(rset.next()) {
				ask = new AskBoard(rset.getInt("ASK_NO"), 
								rset.getString("ASK_TITLE"), 
								rset.getString("ASK_CONTENT"), 
								rset.getDate("ASK_MODIFY_DT"), 
								rset.getString("ASK_STATUS"), 
								rset.getString("MEMBER_ID"),
								rset.getString("ANSWER_CONTENT"));

				AskBoardList.add(ask);
			}				
			
		} finally {
			close(rset);
			close(stmt);				
		}	
		
		System.out.println("DAO 조회 성공");		
		return AskBoardList;
	}





	



	
	/** 관리자페이지 1:1문의 답변 목록 조회용
	 * @param conn
	 * @return AnswerList
	 * @throws Exception
	 */
	public List<Answer> AnswerList(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Answer> AnswerList = null;
		
		String query = prop.getProperty("AnswerList");
		
		try {		
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			AnswerList = new ArrayList<Answer>();
			Answer answer = null;
			
			while(rset.next()) {
				answer = new Answer(rset.getInt("ANSWER_NO"),
									rset.getString("ANSWER_CONTENT"),
									rset.getDate("ANSWER_DATE"));
				
				AnswerList.add(answer);
			}		
		} finally {
			
			close(rset);
			close(stmt);			
		}
		
		return AnswerList;
	}

	
	
	
	
	
	
/** 관리자페이지 1:1 답변 등록용
 * @param conn
 * @param answerContent
 * @return result
 * @throws Exception
 */
public int insertAnswer(Connection conn, int answerAskNo, String answerContent) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAnswer");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, answerAskNo);
			pstmt.setString(2, answerContent);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		System.out.println(result + "개의 답변등록 완료 DAO");
		return result;
	}





public int answerYupdate(Connection conn) throws Exception{
	Statement stmt = null;
	int answerYupdate = 0;
	
	String query = prop.getProperty("answerYupdate");
	try {
		
		stmt = conn.createStatement();
		answerYupdate = stmt.executeUpdate(query);
		
		
		
	} finally {		
		close(stmt);		
	}		
	return answerYupdate;
}





/** 관리자페이지 1:1 게시판 검색용 
 * @param conn
 * @param condition
 * @return searchAsk
 * @throws Exception
 */

public List<AskBoard> searchAsk(Connection conn, String condition) throws Exception {
	
		Statement stmt = null;
		ResultSet rset = null;
		List<AskBoard> searchAsk = null;
		
		String query1 = prop.getProperty("searchAsk1");
		String query2 = prop.getProperty("searchAsk2");
		
		try{
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query1 + condition + query2);
			
			searchAsk = new ArrayList<AskBoard>();
			
			AskBoard ask = null;
			
			while(rset.next()) {
				ask = new AskBoard(rset.getInt("ASK_NO"), 
						rset.getString("ASK_TITLE"), 
						rset.getString("ASK_CONTENT"), 
						rset.getDate("ASK_MODIFY_DT"), 
						rset.getString("ASK_STATUS"), 
						rset.getString("MEMBER_ID"),
						rset.getString("ANSWER_CONTENT"));
	
				searchAsk.add(ask);
			}

		} finally {
			close(rset);
			close(stmt);
		}
		
		System.out.println("DAO 조회 성공");

		return searchAsk;

}


	
	
	
	
}
