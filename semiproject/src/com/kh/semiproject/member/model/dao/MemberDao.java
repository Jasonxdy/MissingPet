package com.kh.semiproject.member.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = null;
	
	public MemberDao() throws Exception {
		
		String fileName = MemberDao.class.getResource("/com/kh/semiproject/sql/member/member-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	public Member loginMember(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member loginMember = null;
		
		String query = prop.getProperty("loginMember");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				String memberId = rset.getString("MEM_ID");
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
	
	public int idDupCheck(Connection conn, String id) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("idDupCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int signUp(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("signUp");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberPwd());
			pstmt.setString(4, member.getMemberEmail());
			pstmt.setString(5, member.getMemberPhone());
			pstmt.setString(6, member.getMemberProImg());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	/** 관리자페이지 회원목록 출력
	 * @param conn
	 * @return mList
	 */
	@SuppressWarnings("unchecked") 
	public List<Member> memberList(Connection conn) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = null;
		
		String query = prop.getProperty("memberList");
		
		try {
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			mList = new ArrayList<Member>();
			
			Member member = null;
			
			while(rset.next()) {
				// 아이디, 이름, 이메일 조회 
				member = new Member(rset.getString("MEM_ID"),
									rset.getString("MEM_NAME"),
									rset.getString("MEM_EMAIL"));
				
			 mList.add(member);
				
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		return mList;
	}
	
	
	
	

	/** 관리자페이지 회원 정보 수정용 
	 * @param conn
	 * @param memberId
	 * @param newId
	 * @param newName
	 * @param newEmail
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Connection conn, String newId, String newName, String newEmail) throws Exception{
	
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateMember");
	
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newName);
			pstmt.setString(2, newEmail);
			
			pstmt.setString(3, newId);
			
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	/** 관리자페이지 회원 삭제용
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(Connection conn, String memberId) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
	
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);

			result = pstmt.executeUpdate();
			
			System.out.println(result); 
			
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	
	/** 관리자페이지 회원 검색용
	 * @param conn
	 * @param condition
	 * @return searchMList
	 * @throws Exception
	 */
	
	public List<Member> searchMember(Connection conn, String condition) throws Exception {
		
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> searchMList = null;
		
		String query1 = prop.getProperty("searchMember1");
		String query2 = prop.getProperty("searchMember2");
		
		try{
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query1 + condition + query2);
			
			searchMList = new ArrayList<Member>();
			
			Member member = null;
			
			while(rset.next()) {
				member = new Member(rset.getString("MEM_ID"),
						rset.getString("MEM_NAME"),
						rset.getString("MEM_EMAIL"));
	
				searchMList.add(member);
			}

		} finally {
			close(rset);
			close(stmt);
		}
		System.out.println("DAO 조회 성공");
		return searchMList;
	}

	
	//////////////// 환조
	
	/** 게시글 회원 조회용 Dao
	 * @param conn
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(Connection conn, String memberId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(memberId,
								rset.getString("MEM_NAME"),
								rset.getString("MEM_EMAIL"),
								rset.getString("MEM_PHONE"), 
								rset.getString("MEM_COMMENT_TELL"), 
								rset.getString("MEM_REALTIME_TELL")); 
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(member);
		return member;
	}
	
	
	/** 회원가입시 이메일 중복 확인
	 * @param conn
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public int emailDupCheck(Connection conn, String email) throws Exception {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("emailDupCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	/** 아이디 찾기
	 * @param conn
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public String findId(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String memberId = null;
		String query = prop.getProperty("findId");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			System.out.println("dao에서 이름: " + member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			System.out.println("dao에서 이메일: " + member.getMemberPwd());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				memberId = rset.getString("MEM_ID");
			}
			System.out.println("dao결과 ID:" + memberId);
		}finally {
			close(rset);
			close(pstmt);
		}
		return memberId;
	}

	/** 비밀번호 찾기
	 * @param conn
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int findPwd(Connection conn, Member member) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = prop.getProperty("findPwd");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			System.out.println("dao에서 아이디: " + member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			System.out.println("dao에서 이메일: " + member.getMemberPwd());
			result = pstmt.executeUpdate();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	/** 새로 발급받은 비밀번호 업데이트
	 * @param conn
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(Connection conn ,Member member) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updatePwd");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPwd());
			pstmt.setString(2, member.getMemberId());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
}

