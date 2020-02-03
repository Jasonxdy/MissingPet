package com.kh.semiproject.mypage.model.service;

import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.mypage.model.dao.MypageDAO;
import com.kh.semiproject.mypage.model.vo.Ask;
import com.kh.semiproject.mypage.model.vo.Board;
import com.kh.semiproject.mypage.model.vo.Reply;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class MypageService {

	
	
	/**
	 * 회원 정보 조회용 서비스
	 * @param memberId : String
	 * @return 
	 * @throws Exception
	 */
	public Member selectMember(String memberId) throws Exception {
		Connection conn = getConnection();
		return new MypageDAO().selectMember(conn, memberId);
	}
	
	/**
	 * 회원 정보 수정용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception{
		Connection conn = getConnection();
		
		int result = new MypageDAO().updateMember(conn, member);
		
		if(result > 0) 		commit(conn);
		else				rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	/**
	 * 세션 갱신용 Service
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member recreateSession(String memberId) throws Exception {
		Connection conn = getConnection();
		return new MypageDAO().recreateSession(conn, memberId);
	}

	
	
	
	/**
	 * 비밀번호 변경용 Service
	 * @param loginMember
	 * @param newPwd
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(Member loginMember, String newPwd) throws Exception {
		
		Connection conn = getConnection();
		MypageDAO mypageDAO = new MypageDAO();
		
		// 현재 비밀번호 일치 여부 확인
		// SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PWD=?
		int result = mypageDAO.checkPwd(conn, loginMember);
		
		if(result>0) { // 현재 비밀번호가 일치할 경우
			
			// 비밀번호 변경 진행
			loginMember.setMemberPwd(newPwd);
			result = mypageDAO.updatePwd(conn, loginMember);
			
			if(result >0) commit(conn);
			else		  rollback(conn);
			
			return result;
			
		} else { // 현재 비밀번호가 일치하지 않을 경우
			return -1;
		}
	}

	
	
	/**
	 * 내가 쓴 글 수 조회용 Service
	 * @param memberId 
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(String memberId) throws Exception {
		Connection conn = getConnection();

		int listCount = new MypageDAO().getListCount(conn, memberId);

		close(conn);
		return listCount;
	}
	
	
	
	
	
	
	

	/**
	 * 내가 작성한 글 목록 조회용 Service 
	 * @param currentPage
	 * @param limit
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(int currentPage, int limit, String memberId) throws Exception{
		
		Connection conn = getConnection();

		List<Board> bList = new MypageDAO().selectList(conn, currentPage, limit, memberId);

		close(conn);
		return bList;
	}

	
	
	
	
	
	
	/**
	 * 내가 작성한 댓글 수 조회용 Service
	 * @param memberId
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(String memberId) throws Exception {
		Connection conn = getConnection();

		int listCount = new MypageDAO().getReplyListCount(conn, memberId);

		close(conn);
		return listCount;
	}

	
	
	
	
	
	
	/**
	 * 내가 작성한 댓글 조회용 Sevice
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int currentPage, int limit, String memberId) throws Exception {
		Connection conn = getConnection();

		List<Reply> rList = new MypageDAO().selectReplyList(conn, currentPage, limit, memberId);

		close(conn);
		return rList;
	}

	
	
	
	
	/**
	 * 내가 작성한 1:1 문의 글 개수 조회용 Service
	 * @param memberId
	 * @return listCount
	 * @throws Exception
	 */
	public int getAskListCount(String memberId) throws Exception {
		Connection conn = getConnection();

		int listCount = new MypageDAO().getAskListCount(conn, memberId);
		
		close(conn);
		return listCount;
	}

	
	
	
	
	/**
	 * 내가 작성한 1:1 문의 글 목록 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return aList
	 * @throws Exception
	 */
	public List<Ask> selectAskList(int currentPage, int limit, String memberId) throws Exception {
		Connection conn = getConnection();

		List<Ask> aList = new MypageDAO().selectAskList(conn, currentPage, limit, memberId);
		
		for(Ask ask : aList) {
			
			if(ask.getAnswerContent() != null) {
				ask.setAnswerContent(ask.getAnswerContent().replace("<br>", "\r\n"));
			}
			
		}

		close(conn);
		return aList;
	}

	
	
	
	
	
	
	/**
	 * 알림 설정 조회용 Service
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectTell(String memberId) throws Exception {
		
		Connection conn = getConnection();
		return new MypageDAO().selectTell(conn, memberId);
		
	}

	
	
	
	/**
	 * 알림 설정 변경용 Service
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateTell(Member member) throws Exception {
		
		Connection conn = getConnection();
		int result = new MypageDAO().updateTell(conn, member);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		return result;
	}



}
