package com.kh.semiproject.AskBoard.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.semiproject.AskBoard.model.dao.AskBoardDao;
import com.kh.semiproject.AskBoard.model.vo.Answer;
import com.kh.semiproject.AskBoard.model.vo.AskBoard;
import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.vo.Board;

public class AskBoardService {

	
	
	public List<AskBoard> AskBoardList() throws Exception {
		
		Connection conn = getConnection();
		List<AskBoard> AskBoardList = new AskBoardDao().AskBoardList(conn);
		
		close(conn);
		return AskBoardList;
	}
	
	



	/** 관리자페이지 1:1문의 답변 목록 조회용
	 * @return AnswerList
	 * @throws Exception
	 */
	public List<Answer> AnswerList() throws Exception {
		Connection conn = getConnection();
		List<Answer> AnswerList = new AskBoardDao().AnswerList(conn);
		
		return AnswerList;
	}


	

	/** 관리자페이지 1:1 문의 답변 등록용
	 * @param answerContent
	 * @return
	 * @throws Exception
	 */
	public int insertAnswer(int answerAskNo,String answerContent) throws Exception {
		
		Connection conn = getConnection();
		int result = new AskBoardDao().insertAnswer(conn, answerAskNo, answerContent);
		
		if(result > 0) {
			
			int answerYupdate = new AskBoardDao().answerYupdate(conn);
		}
		
		if(result > 0) commit(conn);
		else		rollback(conn);	
		
		close(conn);
		return result;
	}





	public List<AskBoard> searchAsk(String searchKey, String searchValue) throws Exception { 
		
			Connection conn = getConnection();
			String condition = null;
			
			searchValue = "'%' || '" + searchValue + "' || '%'";
			
			switch(searchKey) {
			case "SearchAskTitle" : condition = " ASK_TITLE LIKE " + searchValue; break;
			case "SearchAskContent" : condition = " ASK_CONTENT LIKE " + searchValue; break;
			}
			
			List<AskBoard> searchAsk = new AskBoardDao().searchAsk(conn, condition);
			
			System.out.println("서비스  성공");
			
			close(conn);

		return searchAsk;
	}


	
	
	
	
	
	
	
	
	
}
