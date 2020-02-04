package com.kh.semiproject.member.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kh.semiproject.member.model.dao.MemberDao;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.wrapper.EncryptWrapper;

public class MemberService {

	/** 로그인용 Service
	 * @param member
	 * @return member
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {
		Connection conn = getConnection();
		
		Member loginMember = new MemberDao().loginMember(conn, member);
		
		return loginMember;
	}

	public int idDupCheck(String id) throws Exception {
		Connection conn = getConnection();
		
		
		return new MemberDao().idDupCheck(conn, id);
	}

	public int signUp(Member member) throws Exception {
		Connection conn = getConnection();
		
		int result = new MemberDao().signUp(conn, member);
		if(result>0) commit(conn);
		else rollback(conn);
		System.out.println("서비스");
		close(conn);
		return result;
	}

	
	
	
	
	
	
	
	/** 관리자페이지 회원목록 출력용 
	 * @return mList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked") 
	public List<Member> memberList() throws Exception{
		Connection conn = getConnection();
		List<Member> mList =  new MemberDao().memberList(conn);
		
		close(conn);
		return mList;
	}

	
	
	
	/** 관리자페이지 회원정보 수정용
	 * @param memberId
	 * @param newId
	 * @param newName
	 * @param newEmail
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(String newId, String newName, String newEmail) throws Exception {
		
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, newId, newName, newEmail);
		
		if(result > 0) commit(conn);
		else 		 rollback(conn);
	
		close(conn);
		return result;
	}
	
	
	

	/** 관리자페이지 회원 삭제용
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int deleteMember(String memberId) throws Exception {
		
		
		System.out.println("서비스 작업 성공"); 
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, memberId);
		
		if(result > 0) commit(conn);
		else 		 rollback(conn);
		close(conn);
		return result;
	}

	
	
	
	
	
	/** 관리자페이지 회원 검색용 Service
	 * @param searchKey
	 * @param searchValue
	 * @return list
	 * @throws Exception
	 */
	public List<Member> searchMember(String searchKey, String searchValue) throws Exception{
		
		
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "memberName" : condition = " MEM_NAME LIKE " + searchValue; break;
		case "memberEmail" : condition = " MEM_EMAIL LIKE " + searchValue; break;
		}
		
		List<Member> searchMList = new MemberDao().searchMember(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		return searchMList;
	}
	
	
	/////////////////////// 환조
	
	/** 게시글 회원 조회용 Service
	 * @param memberId
	 * @return member
	 * @throws Exception
	 */
	public Member selectMember(String memberId) throws Exception {
		Connection conn = getConnection();
		
		Member member = new MemberDao().selectMember(conn, memberId);
		
		close(conn);
		
		return member;
	}
	
	
	/** 회원가입 이메일 중복 체크
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public int emailDupCheck(String email) throws Exception {
		Connection conn = getConnection();
		MemberDao memberDao = new MemberDao();
		int result = memberDao.emailDupCheck(conn, email);
		close(conn);
		return result;
	}

	/** 아이디 찾기
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public String findId(Member member) throws Exception {
		Connection conn = getConnection();
		MemberDao memberDao = new MemberDao();
		String memberId = memberDao.findId(conn, member);
		String email = member.getMemberPwd();
		System.out.println("아이디찾기 아이디: " + memberId);
			try {
				if(memberId != null) {
					Properties props = System.getProperties();
					props.put("mail.smtp.auth"           , "true");
					props.put("mail.smtp.ssl.enable"     , "true");
					props.put("mail.smtp.ssl.trust"      , "smtp.gmail.com");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host"           , "smtp.gmail.com");
					props.put("mail.smtp.port"           , 465);
					
					Session session = Session.getInstance(props, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("missingpetkh@gmail.com", "ehdgus12");
							//return new PasswordAuthentication("발신gmail계정주소", "앱비밀번호");
						}
					});
					
					InternetAddress from = new InternetAddress("missingpetkh@gmail.com");
					//InternetAddress from = new InternetAddress("발신gmail계정주소", "표시할발신자명");
					
					Message message = new MimeMessage(session);
					message.setFrom(from);
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
					//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("수신메일계정주소"));
					
					message.setSubject("Missing Pet 아이디 찾기");
					message.setText("회원님의 아이디는 " + memberId + " 입니다.");
					Transport.send(message);
				}
			} finally {
				
			}

		close(conn);
		return memberId;
	}

	/** 비밀번호 찾기
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public int findPwd(Member member) throws Exception {
		Connection conn = getConnection();
		MemberDao memberDao = new MemberDao();
		int result = memberDao.findPwd(conn, member);
		String email = member.getMemberPwd();
		String encPwd = null;
		StringBuffer tempPwd = new StringBuffer();
		if(result>0) {
			
			Random rnd = new Random();
			for (int i = 0; i < 10; i++) {
			    int rIndex = rnd.nextInt(3);
			    switch (rIndex) {
			    case 0:
			        // a-z
			        tempPwd.append((char) ((int) (rnd.nextInt(26)) + 97));
			        break;
			    case 1:
			        // A-Z
			        tempPwd.append((char) ((int) (rnd.nextInt(26)) + 65));
			        break;
			    case 2:
			        // 0-9
			        tempPwd.append((rnd.nextInt(10)));
			        break;
			    }
			}
			
			if(tempPwd != null) {
				encPwd = tempPwd.toString();
				encPwd = EncryptWrapper.getSha512(encPwd);
				member.setMemberPwd(encPwd);
				result = memberDao.updatePwd(conn, member);
			}
			System.out.println("임시 비밀번호 생성" + tempPwd);
			System.out.println("임시 암호화 비밀번호 생성" + encPwd);
		}
			try {
				if(tempPwd != null) {
					Properties props = System.getProperties();
					props.put("mail.smtp.auth"           , "true");
					props.put("mail.smtp.ssl.enable"     , "true");
					props.put("mail.smtp.ssl.trust"      , "smtp.gmail.com");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host"           , "smtp.gmail.com");
					props.put("mail.smtp.port"           , 465);
					
					Session session = Session.getInstance(props, new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("missingpetkh@gmail.com", "ehdgus12");
							//return new PasswordAuthentication("발신gmail계정주소", "앱비밀번호");
						}
					});
					
					InternetAddress from = new InternetAddress("missingpetkh@gmail.com");
					//InternetAddress from = new InternetAddress("발신gmail계정주소", "표시할발신자명");
					
					Message message = new MimeMessage(session);
					message.setFrom(from);
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
					//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("수신메일계정주소"));
					
					message.setSubject("Missing Pet 비밀번호 찾기");
					message.setText("회원님의 임시 비밀번호는 " + tempPwd + " 입니다.");
					Transport.send(message);
				}
			} finally {
				
			}
			close(conn);
		return result;
	}
	

}
