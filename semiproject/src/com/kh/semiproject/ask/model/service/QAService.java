package com.kh.semiproject.ask.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.semiproject.ask.model.dao.QADAO;
import com.kh.semiproject.ask.model.vo.Ask;
import com.kh.semiproject.ask.model.vo.QA;
import static com.kh.semiproject.common.JDBCTemplate.*;

public class QAService {

	/**
	 * QA 목록 조회용 Service
	 * @return qaList
	 * @throws Exception
	 */
	public List<QA> selectQA() throws Exception {
		
		Connection conn = getConnection();
		return new QADAO().selectQA(conn);
	}

	
	



	
	
	
	/**
	 * 1:1 문의 등록용 Service
	 * @param ask
	 * @return result
	 * @throws Exception
	 */
	public int insertAsk(Ask ask) throws Exception {
		
		Connection conn = getConnection();
		
		// 등록될 1:1 문의 번호 얻어오기
		int askNo = new QADAO().selectNextNo(conn); 
		
		ask.setAskNo(askNo);
		
//		ask.setAskContent(
//				ask.getAskContent().replace("\r\n", "<br>")
//		);
		
		// 1:1 문의 글 생성
		int result = new QADAO().insertAsk(conn, ask);
		
		if(result > 0) { // 1:1 문의 글 삽입 성공시
			result = new QADAO().insertAnswer(conn, ask);
		}
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		return result;
	}

}
