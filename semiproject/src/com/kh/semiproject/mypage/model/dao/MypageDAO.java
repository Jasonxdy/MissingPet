package com.kh.semiproject.mypage.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.kh.semiproject.common.JDBCTemplate.*;

import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.mypage.model.vo.Ask;
import com.kh.semiproject.mypage.model.vo.Board;
import com.kh.semiproject.mypage.model.vo.Reply;

public class MypageDAO {

	private Properties prop = null;
	
	public MypageDAO() throws Exception {
		String fileName = MypageDAO.class.getResource("/com/kh/semiproject/sql/mypage/mypage-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	
	/**
	 * 회원 정보 조회용 DAO
	 * @param conn : Connection
	 * @param memberId : String
	 * @return selectMebmer : Member
	 * @throws Exception
	 */
	public Member selectMember(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member selectMember = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				selectMember = 	new Member(memberId, 
						rset.getString("MEM_NAME"), 
						rset.getString("MEM_EMAIL"), 
						rset.getString("MEM_PHONE"), 
						rset.getString("MEM_PRO_IMG"));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return selectMember;
	}
	
	
	
	
	
	
	/**
	 * 회원 정보 수정용 Controller
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, Member member) throws Exception {
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMember");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPhone());
			pstmt.setString(2, member.getMemberProImg());
			pstmt.setString(3, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 세션 갱신용 DAO
	 * @param conn
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member recreateSession(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member loginMember = null;
		
		String query = prop.getProperty("recreateSession");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberName = rset.getString("MEM_NAME");
				String memberEmail = rset.getString("MEM_EMAIL");
				String memberPhone = rset.getString("MEM_PHONE");
				String memberWebTell = rset.getString("MEM_WEB_TELL");;
				String memberCommentTell = rset.getString("MEM_COMMENT_TELL");
				String memberAskTell = rset.getString("MEM_ASK_TELL");;
				String memberRTTell = rset.getString("MEM_REALTIME_TELL");
				String memberEmailCertify = rset.getString("MEM_EMAIL_CERTIFY");
				String memberStatus = rset.getString("MEM_STATUS");
				String memberGrade = rset.getString("MEM_GRADE");
				Date memberSignUpDT = rset.getDate("MEM_SIGNUP_DT");
				String memberProImg = rset.getString("MEM_PRO_IMG");
				
				loginMember = new Member(memberId, memberName, memberEmail,
						memberPhone, memberWebTell, memberCommentTell, memberAskTell,
						memberRTTell, memberEmailCertify, memberStatus, memberGrade,
						memberSignUpDT, memberProImg);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginMember;
	}


	/**
	 * 비밀번호 일치 여부 체크용 DAO
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int checkPwd(Connection conn, Member loginMember) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberId());
			pstmt.setString(2, loginMember.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	

	/**
	 * 비밀번호 변경용 DAO
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(Connection conn, Member loginMember) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberPwd());
			pstmt.setString(2, loginMember.getMemberId());
			
			result = pstmt.executeUpdate();
		
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	

	/**
	 * 내가 쓴 글 수 조회용 DAO
	 * @param conn
	 * @param memberId 
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String memberId) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getListCount");
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}


	
	
	
	
	
	

	
	/**
	 * 내가 쓴 글 목록 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectList(Connection conn, int currentPage, int limit, String memberId) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Board> bList = null;
		
		String query = prop.getProperty("selectList");

		
		try {
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			Board board = null;
			
			while(rset.next()) {
				board = new Board(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getInt("BOARD_CODE"));
				
				bList.add(board);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return bList;
	}


	
	
	
	
	
	/**
	 * 내가 쓴 댓글 수 조회용 DAO
	 * @param conn
	 * @param memberId
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getReplyListCount");
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	
	
	
	

	/**
	 * 내가 작성한 댓글 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, int currentPage, int limit, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> rList = null;
		
		String query = prop.getProperty("selectReplyList");

		
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Reply>();
			Reply reply = null;
			
			while(rset.next()) {
				reply = new Reply(rset.getInt("COMM_NO"),
									rset.getInt("BOARD_NO"),
									rset.getInt("BOARD_CODE"),
									rset.getString("BOARD_TITLE"),
									rset.getString("COMM_CONTENT"),
									rset.getDate("COMM_MODIFY_DT"));
				
				rList.add(reply);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}


	
	
	
	
	/**
	 * 1:1 문의 글 개수 확인용 DAO
	 * @param conn
	 * @param memberId
	 * @return listCount
	 * @throws Exception
	 */
	public int getAskListCount(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("get1to1ListCount");
		int listCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}


	
	
	/**
	 * 1:1 문의 글 목록 조회용 DAO
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param memberId
	 * @return aList
	 * @throws Exception
	 */
	public List<Ask> selectAskList(Connection conn, int currentPage, int limit, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Ask> aList = null;
		
		String query = prop.getProperty("selectAskList");

		
		try {
			int startRow = (currentPage - 1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			aList = new ArrayList<Ask>();
			Ask ask = null;
			
			while(rset.next()) {
				ask = new Ask(rset.getInt("ASK_NO"),
						rset.getString("ASK_TITLE"),
						rset.getString("ASK_CONTENT"),
						rset.getDate("ASK_CREATE_DT"),
						rset.getDate("ASK_MODIFY_DT"),
						rset.getString("ANSWER_CONTENT"),
						rset.getString("ASK_STATUS"),
						memberId,
						rset.getInt("ANSWER_NO"),
						rset.getDate("ANSWER_DATE"));
				
				aList.add(ask);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return aList;
	}


	
	
	
	
	/** 
	 * 알림 설정 조회용 DAO
	 * @param conn 
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectTell(Connection conn, String memberId) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTell");
		
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				String memberWebTell = rset.getString("MEM_WEB_TELL");
				String memberCommentTell = rset.getString("MEM_COMMENT_TELL");
				String memberAskTell = rset.getString("MEM_ASK_TELL");
				String memberRTTell = rset.getString("MEM_REALTIME_TELL");
				
				member = new Member(memberId, null, null, null, memberWebTell, memberCommentTell, memberAskTell, memberRTTell, null, null, null, null, null);
			}
			
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return member;
	}


	
	
	/**
	 * 알림 설정 변경용 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateTell(Connection conn, Member member) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateTell");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberWebTell());
			pstmt.setString(2, member.getmemberCommentTell());
			pstmt.setString(3, member.getMemberAskTell());
			pstmt.setString(4, member.getMemberRTTell());
			pstmt.setString(5, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}



}
