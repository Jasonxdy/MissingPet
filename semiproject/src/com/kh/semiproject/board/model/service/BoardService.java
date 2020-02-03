package com.kh.semiproject.board.model.service;
import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.member.model.dao.MemberDao;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.qaBoard.model.vo.QnABoard;

public class BoardService {

	
	/** 관리자페이지 찾아요 게시판 목록 조회
	 * @return FindBoardList
	 * @throws Exception
	 */
	public List<Board> FindBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Board> FindBoardList = new BoardDao().FindBoardList(conn);
		System.out.println("서비스 성공");
		
		return FindBoardList;
	}

	
	
	/** 관리자페이지 봤어요 게시판 목록 조회
	 * @return SeeBoardList
	 * @throws Exception
	 */
	public List<Board> SeeBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Board> SeeBoardList = new BoardDao().SeeBoardList(conn);
		System.out.println("서비스 성공");
		return SeeBoardList;
	}


	
	/** 관리자페이지 분양합니다 게시판 목록 조회
	 * @return AdoptBoardList
	 * @throws Exception
	 */
	public List<Board> AdoptBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Board> AdoptBoardList = new BoardDao().AdoptBoardList(conn);
		System.out.println("서비스 성공");
		return AdoptBoardList;
	}

	
	/** 관리자페이지 만남 그 후 게시판 목록 조회
	 * @return ReviewBoardList
	 * @throws Exception
	 */
	public List<Board> ReviewBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Board> ReviewBoardList = new BoardDao().ReviewBoardList(conn);
		System.out.println("서비스 성공");
		return ReviewBoardList;
	}

	
	
	/** 관리자페이지 자유게시판 목록 조회
	 * @return FreeBoardList
	 * @throws Exception
	 */
	public List<Board> FreeBoardList() throws Exception{
		
		Connection conn = getConnection();
		List<Board> FreeBoardList = new BoardDao().FreeBoardList(conn);
		System.out.println("서비스 성공");
		return FreeBoardList;
	}



	
	
	
	
	
	
	/** 관리자페이지 찾아요 게시판 검색용  
	 * @param searchKey
	 * @param searchValue
	 * @return searchFindBoard
	 */
	public List<Board> searchFindBoard(String searchKey, String searchValue) throws Exception{
	
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "createDt" : condition = " BOARD_CREATE_DT LIKE " + searchValue; break;
		}
		
		List<Board> searchFindBoard = new BoardDao().searchFindBoard(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		return searchFindBoard;

	}

	
	/** 관리자페이지 봤어요 게시판 검색용  
	 * @param searchKey
	 * @param searchValue
	 * @return searchSeeBoard
	 */
	public List<Board> searchSeeBoard(String searchKey, String searchValue) throws Exception{
	
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "createDt" : condition = " BOARD_CREATE_DT LIKE " + searchValue; break;
		}
				
		List<Board> searchSeeBoard = new BoardDao().searchSeeBoard(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		return searchSeeBoard;

	}
	
	
	/** 관리자페이지 분양했어요 게시판 검색용  
	 * @param searchKey
	 * @param searchValue
	 * @return searchAdoptBoard
	 */
	public List<Board> searchAdoptBoard(String searchKey, String searchValue) throws Exception{
	
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "createDt" : condition = " BOARD_CREATE_DT LIKE " + searchValue; break;
		}
				
		List<Board> searchAdoptBoard = new BoardDao().searchAdoptBoard(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		return searchAdoptBoard;

	}
	
	/** 관리자페이지 만남 그 후 게시판 검색용  
	 * @param searchKey
	 * @param searchValue
	 * @return searchReviewBoard
	 */
	public List<Board> searchReviewBoard(String searchKey, String searchValue) throws Exception{
	
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "createDt" : condition = " BOARD_CREATE_DT LIKE " + searchValue; break;
		}
				
		List<Board> searchReviewBoard = new BoardDao().searchReviewBoard(conn, condition);
		
		System.out.println("서비스  성공");
		
		close(conn);
		return searchReviewBoard;

	}
	
	
	/** 관리자페이지 자유게시판 검색용  
	 * @param searchKey
	 * @param searchValue
	 * @return searchFreeBoard
	 */
	public List<Board> searchFreeBoard(String searchKey, String searchValue) throws Exception{
	
		Connection conn = getConnection();
		String condition = null;
		
		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "memberId" : condition = " MEM_ID LIKE " + searchValue; break;
		case "createDt" : condition = " BOARD_CREATE_DT LIKE " + searchValue; break;
		}
				
		List<Board> searchFreeBoard = new BoardDao().searchFreeBoard(conn, condition);
		
		System.out.println("검색 서비스  성공");
		
		close(conn);
		return searchFreeBoard;

	}
	
	/////////////////// 환조
	
	/** 전체 게시글 수 조회 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(int boardType) throws Exception{
		Connection conn = getConnection();
		
		int listCount = new BoardDao().getListCount(conn, boardType);
		
		close(conn);
		
		return listCount;
	}
	
	/** 게시판 목록 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @return bList
	 * @throws Exception
	 */
	public List<BoardHJ> selectFList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		
		List<BoardHJ> bList = new BoardDao().selectFList(conn, currentPage, limit, boardType);
		
		close(conn);
		
		return bList;
	}

	/** 썸네일 이미지 목록 조회 Service
	 * @param currentPage
	 * @param limit
	 * @return aList
	 * @throws Exception
	 */
	public List<Attachment> selectAList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		
		List<Attachment> aList = new BoardDao().selectAList(conn, currentPage, limit, boardType);
		
		close(conn);
		return aList;
	}

	/** 목록 조회용 동물 정보 반환 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return animalList
	 * @throws Exception
	 */
	public List<Animal> selectAnimalList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		
		List<Animal> animalList = new BoardDao().selectAnimalList(conn, currentPage, limit, boardType);
		
		close(conn);
		return animalList;
	}

	/** 게시글 상세 조회용 Service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public BoardHJ selectBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		BoardDao boardDao = new BoardDao();
		
		BoardHJ board = boardDao.selectboard(conn, boardNo);
		
		System.out.println(board);
		
		if(board != null) {
			int result = boardDao.increaseCount(conn, boardNo);
			
			if(result>0) {
				commit(conn);
				
				board.setBoardCount(board.getBoardCount()+1);
			} else {
				rollback(conn);
				board=null;
			}
		}
		
		
		
		close(conn);
		
		return board;
	}
	
	/** 게시글 이미지 파일 조회용 Service
	 * @param boardNo
	 * @return files
	 * @throws Exception
	 */
	public List<Attachment> selectFiles(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		List<Attachment> files = new BoardDao().selectFiles(conn, boardNo);
		
		close(conn);
		
		return files;
	}

	/** 게시글 동물 조회용 Serivce
	 * @param animalCode
	 * @return animal
	 * @throws Exception
	 */
	public Animal selectAnimal(int animalCode) throws Exception {
		Connection conn = getConnection();
		
		Animal animal = new BoardDao().selectAnimal(conn, animalCode);
		
		close(conn);
		
		return animal;
	}

	/** 게시판 수정화면용 Service
	 * @param no
	 * @return board
	 * @throws Exception
	 */
	public BoardHJ updateForm(int no) throws Exception {
		Connection conn = getConnection();
		
		BoardHJ board = new BoardDao().selectboard(conn, no);
		
		board.setBoardContent(board.getBoardContent().replace("<br>", "\r\n"));
		
		return board;
	}

	/** 검색 게시글 수 조회 Service
	 * @param condition
	 * @param boardType
	 * @return searchListCount
	 * @throws Exception
	 */
	public int getSearchListCount(String condition, int boardType) throws Exception {
		Connection conn = getConnection();
		
		int searchListCount = new BoardDao().getSearchListCount(conn, condition, boardType);
		
		close(conn);
		
		return searchListCount;
	}

	/** 검색 게시글 조회 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @param condition
	 * @return bList
	 * @throws Exception
	 */
	public List<BoardHJ> searchBoardList(int startRow, int endRow, int boardType, String condition) throws Exception{
		Connection conn = getConnection();
		
		List<BoardHJ> bList = new BoardDao().searchBoardList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		
		return bList;
	}

	/** 검색 게시글 파일 조회 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @param condition
	 * @return aList
	 * @throws Exception
	 */
	public List<Attachment> searchAList(int startRow, int endRow, int boardType, String condition) throws Exception{
		Connection conn = getConnection();
		
		List<Attachment> aList = new BoardDao().searchAList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		return aList;
	}

	/** 검색 게시글 동물 조회 Service
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @return animalList
	 * @throws Exception
	 */
	public List<Animal> searchAnimalList(int startRow, int endRow, int boardType, String condition) throws Exception {
		Connection conn = getConnection();
		
		List<Animal> animalList = new BoardDao().searchAnimalList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		return animalList;
	}
	
	public void sendMail(String boardTitle, String email) throws Exception{
		try {
			System.out.println("send메일 시작");
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
			
			message.setSubject(boardTitle + " 글이 등록되었습니다.");
			message.setText("메일본문내용");
			
			Transport.send(message);
		} finally {
			
		}
	}

	/** 댓글 이메일 알림용 Service
	 * @param commentWriter
	 * @param commentContent
	 * @param email
	 * @throws Exception
	 */
	public void sendCommentAlram(String commentWriter, String commentContent, String title, String email) throws Exception {
		String boardTitle = null;
		
		boardTitle = commentWriter+"님이 "+title +"글에 댓글을 달았습니다. 내용 : commentContent";
		
		new BoardService().sendMail(boardTitle, email);
	}



	

	
	
	
} // class End
