package com.kh.semiproject.qaBoard.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;

import java.util.List;

import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.qaBoard.model.dao.QnABoardDao;
import com.kh.semiproject.qaBoard.model.vo.QnABoard;

public class QnABoardService {
	
	
	
	/** 관리자페이지 Q&A 목록 조회용
	 * @return qaBoardList
	 * @throws Exception
	 */
	public List<QnABoard> qaBoardList() throws Exception {
		
		Connection conn = getConnection();
		
		List<QnABoard> qaBoardList = new QnABoardDao().qaBoardList(conn);
		
		close(conn);
		System.out.println("서비스 조회 성공");
		return qaBoardList;
	}



	
	
	/** 관리자페이지 Q&A 글 등록용 
	 * @param qnAContent 
	 * @param qnATitle 
	 * @return result
	 * @throws Exception
	 */
	public int insertQnA(String QnATitle, String QnAContent) throws Exception {
		
		Connection conn = getConnection();
		
		QnAContent.replace("\r\n", "<br>");
		
		int result = new QnABoardDao().insertQnA(conn, QnATitle, QnAContent);
		
	
		
		if(result > 0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		System.out.println("서비스 완료");
		return result;
	}


	
	
	/** 관리자페이지 Q&A 글 수정용 
	 * @param UpdateQnANo 
	 * @param updateQnAContent 
	 * @param updateQnATitle 
	 * @return result
	 * @throws Exception
	 */
	public int updateQnA(int UpdateQnANo, String updateQnAContent, String updateQnATitle) throws Exception{
		Connection conn = getConnection();
		
		updateQnAContent.replace("\r\n", "<br>");
		
		int result = new QnABoardDao().updateQnA(conn, UpdateQnANo, updateQnATitle, updateQnAContent);
		
		if(result > 0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		System.out.println("서비스 완료");
		return result;
	}




	/** 관리자페이지 Q&A 글 삭제용 
	 * @param deleteQnANo 
	 * @param deleteQnATitle 
	 * @param deleteQnAContent 
	 * @return result
	 * @throws Exception
	 */
	public int deleteQnA(int deleteQnANo) throws Exception{
		Connection conn = getConnection();
		
		int result = new QnABoardDao().deleteQnA(conn, deleteQnANo);
		
		if(result > 0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		System.out.println("서비스 완료");
		return result;
	}





	/** 관리자페이지 QnA 검색용
	 * @param searchKey
	 * @param searchValue
	 * @return searchQnA
	 * @throws Exception
	 */
	public List<QnABoard> searchQnA(String searchKey, String searchValue) throws Exception {
		
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "SearchQnATitle" : condition = " QA_TITLE LIKE " + searchValue; break;
		case "SearchQnAContent" : condition = " QA_CONTENT LIKE " + searchValue; break;
		case "SearchQnAModifyDt" : condition = " QA_MODIFY_DT LIKE " + searchValue; break;
		}
		
		List<QnABoard> searchQnA = new QnABoardDao().searchQnA(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		
		
		
		
		return searchQnA;
	}
	
	
}
