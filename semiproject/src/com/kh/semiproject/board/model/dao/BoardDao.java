package com.kh.semiproject.board.model.dao;
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

import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.member.model.dao.MemberDao;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.qaBoard.model.vo.QnABoard;


public class BoardDao {
		private Properties prop = null;
		
		
		public BoardDao() throws Exception {
			
			String fileName = MemberDao.class.getResource("/com/kh/semiproject/sql/board/board-query.properties").getPath();
			
			prop = new Properties();
			prop.load(new FileReader(fileName));
		}

		
		

		/** 관리자페이지 찾아요 게시판 목록 조회용 
		 * @return FindBoardList
		 * @throws Exception
		 */
		public List<Board> FindBoardList(Connection conn) throws Exception {
		
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> FindBoardList = null;
			
			String query = prop.getProperty("FindBoardList");
			
			try {
			
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				FindBoardList = new ArrayList<Board>();
				Board board = null;
			
				while(rset.next()) {
					board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_CREATE_DT"),
						rset.getString("MEM_ID"));
										
					
					FindBoardList.add(board);
				}
								
			} finally {
				close(rset);
				close(stmt);
			}
			
			System.out.println("DAO 조회 완료");
			
			return FindBoardList;
		}



		
		

		/** 관리자페이지 봤어요 게시판 목록 조회용 
		 * @return SeeBoardList
		 * @throws Exception
		 */
		public List<Board> SeeBoardList(Connection conn) throws Exception {
		
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> SeeBoardList = null;
			
			String query = prop.getProperty("SeeBoardList");
			
			try {
			
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				SeeBoardList = new ArrayList<Board>();
				Board board = null;
			
				while(rset.next()) {
					board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_CREATE_DT"),
						rset.getString("MEM_ID"));
										
					SeeBoardList.add(board);
				}
								
			} finally {
				close(rset);
				close(stmt);
			}
			
			System.out.println("DAO 조회 완료");
			return SeeBoardList;
		}



		/** 관리자페이지 분양합니다 게시판 목록 조회용 
		 * @return AdoptBoardList
		 * @throws Exception
		 */
		public List<Board> AdoptBoardList(Connection conn) throws Exception {
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> AdoptBoardList = null;
			
			String query = prop.getProperty("AdoptBoardList");
			
			try {
			
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				AdoptBoardList = new ArrayList<Board>();
				Board board = null;
			
				while(rset.next()) {
					board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_CREATE_DT"),
						rset.getString("MEM_ID"));
										
					AdoptBoardList.add(board);
				}
								
			} finally {
				close(rset);
				close(stmt);
			}
			
			System.out.println("DAO 조회 완료");
			return AdoptBoardList;
		}
		
		
		
		
		
		/** 관리자페이지 만남 그 후 게시판 목록 조회용 
		 * @return ReviewBoardList
		 * @throws Exception
		 */
		public List<Board> ReviewBoardList(Connection conn) throws Exception {
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> ReviewBoardList = null;
			
			String query = prop.getProperty("ReviewBoardList");
			
			try {
			
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				ReviewBoardList = new ArrayList<Board>();
				Board board = null;
			
				while(rset.next()) {
					board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_CREATE_DT"),
						rset.getString("MEM_ID"));
										
					ReviewBoardList.add(board);
				}
								
			} finally {
				close(rset);
				close(stmt);
			}
			
			System.out.println("DAO 조회 완료");
			return ReviewBoardList;
		}
		
		
		
		
		
		/** 관리자페이지 자유게시판 목록 조회용
		 * @return FreeBoardList
		 * @throws Exception
		 */
		public List<Board> FreeBoardList(Connection conn) throws Exception {
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> FreeBoardList = null;
			
			String query = prop.getProperty("FreeBoardList");
			
			try {
			
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				FreeBoardList = new ArrayList<Board>();
				Board board = null;
			
				while(rset.next()) {
					board = new Board(
						rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_CREATE_DT"),
						rset.getString("MEM_ID"));
										
					FreeBoardList.add(board);
				}
								
			} finally {
				close(rset);
				close(stmt);
			}
			
			System.out.println("DAO 조회 완료");
			return FreeBoardList;
		}



		
		

		/** 관리자페이지 찾아요 게시판 검색용 
		 * @param conn
		 * @param condition
		 * @return searchFindBoard
		 * @throws Exception
		 */
		public List<Board> searchFindBoard(Connection conn, String condition) throws Exception{
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> searchFindBoard = null;
			
			String query1 = prop.getProperty("searchFindBoard1");
			String query2 = prop.getProperty("searchFindBoard2");
			
			try{
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query1 + condition + query2);
				
				searchFindBoard = new ArrayList<Board>();
				
				Board board = null;
				
				while(rset.next()) {
					board = new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getDate("BOARD_CREATE_DT"),
							rset.getString("MEM_ID"));
		
					searchFindBoard.add(board);
				}

			} finally {
				close(rset);
				close(stmt);
			}
			System.out.println("DAO 조회 성공");
			return searchFindBoard;
		}
		
		
		
		/** 관리자페이지 봤어요 게시판 검색용 
		 * @param conn
		 * @param condition
		 * @return searchSeeBoard
		 * @throws Exception
		 */
		public List<Board> searchSeeBoard(Connection conn, String condition) throws Exception{
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> searchSeeBoard = null;
			
			String query1 = prop.getProperty("searchSeeBoard1");
			String query2 = prop.getProperty("searchSeeBoard2");
			
			try{
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query1 + condition + query2);
				
				searchSeeBoard = new ArrayList<Board>();
				
				Board board = null;
				
				while(rset.next()) {
					board = new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getDate("BOARD_CREATE_DT"),
							rset.getString("MEM_ID"));
		
					searchSeeBoard.add(board);
				}

			} finally {
				close(rset);
				close(stmt);
			}
			System.out.println("DAO 조회 성공");
			return searchSeeBoard;
		}
		
		
		/** 관리자페이지 분양합니다 게시판 검색용 
		 * @param conn
		 * @param condition
		 * @return searchAdoptBoard
		 * @throws Exception
		 */
		public List<Board> searchAdoptBoard(Connection conn, String condition) throws Exception{
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> searchAdoptBoard = null;
			
			String query1 = prop.getProperty("searchAdoptBoard1");
			String query2 = prop.getProperty("searchAdoptBoard2");
			
			try{
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query1 + condition + query2);
				
				searchAdoptBoard = new ArrayList<Board>();
				
				Board board = null;
				
				while(rset.next()) {
					board = new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getDate("BOARD_CREATE_DT"),
							rset.getString("MEM_ID"));
		
					searchAdoptBoard.add(board);
				}

			} finally {
				close(rset);
				close(stmt);
			}
			System.out.println("DAO 조회 성공");
			return searchAdoptBoard;
		}
		
		/** 관리자페이지 만남 그 후 게시판 검색용 
		 * @param conn
		 * @param condition
		 * @return searchReviewBoard
		 * @throws Exception
		 */
		public List<Board> searchReviewBoard(Connection conn, String condition) throws Exception{
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> searchReviewBoard = null;
			
			String query1 = prop.getProperty("searchReviewBoard1");
			String query2 = prop.getProperty("searchReviewBoard2");
			
			try{
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query1 + condition + query2);
				
				searchReviewBoard = new ArrayList<Board>();
				
				Board board = null;
				
				while(rset.next()) {
					board = new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getDate("BOARD_CREATE_DT"),
							rset.getString("MEM_ID"));
		
					searchReviewBoard.add(board);
				}

			} finally {
				close(rset);
				close(stmt);
			}
			System.out.println("DAO 조회 성공");
			return searchReviewBoard;
		}
		
		
		/** 관리자페이지 자유게시판 검색용 
		 * @param conn
		 * @param condition
		 * @return searchFreeBoard
		 * @throws Exception
		 */
		public List<Board> searchFreeBoard(Connection conn, String condition) throws Exception{
			Statement stmt = null;
			ResultSet rset = null;
			List<Board> searchFreeBoard = null;
			
			String query1 = prop.getProperty("searchFreeBoard1");
			String query2 = prop.getProperty("searchFreeBoard2");
			
			try{
				stmt = conn.createStatement();
				
				rset = stmt.executeQuery(query1 + condition + query2);
				
				searchFreeBoard = new ArrayList<Board>();
				
				Board board = null;
				
				while(rset.next()) {
					board = new Board(
							rset.getInt("BOARD_NO"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getDate("BOARD_CREATE_DT"),
							rset.getString("MEM_ID"));
		
					searchFreeBoard.add(board);
				}

			} finally {
				close(rset);
				close(stmt);
			}
			System.out.println("DAO 검색 성공");
			return searchFreeBoard;
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
		 * @param updateQnATitle
		 * @param updateQnAContent
		 * @return result
		 * @throws Exception
		 */
		public int updateQnA(Connection conn, String updateQnATitle, String updateQnAContent) throws Exception{
			
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("updateQnA");
			
			try {
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, updateQnATitle);
				pstmt.setString(2, updateQnAContent);
				
				result = pstmt.executeUpdate();
			
			} finally {
				close(pstmt);
			}
			System.out.println("DAO QnA 수정 성공");
			return result;
		}
		
		/////////////////////////////////
		//환조

		/** 다음 게시글 번호 반환용 Dao
		 * @param conn
		 * @return boardNo
		 * @throws Exception
		 */
		public int selectNextBoardNo(Connection conn) throws Exception {
			Statement stmt = null;
			ResultSet rset = null;
			int boardNo = 0;
			
			String query = prop.getProperty("selectNextBoardNo");
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					boardNo = rset.getInt(1);
				}
			} finally {
				close(rset);
				close(stmt);
			}
			
			return boardNo;
		}
		
		/** 다음 동물 번호 반환용 Dao
		 * @param conn
		 * @return animalNo
		 * @throws Exception
		 */
		public int selectNextAnimalNo(Connection conn) throws Exception {
			Statement stmt = null;
			ResultSet rset = null;
			int animalNo = 0;
			
			String query = prop.getProperty("selectNextAnimalNo");
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					animalNo = rset.getInt(1);
				}
			} finally {
				close(rset);
				close(stmt);
			}
			
			return animalNo;
		}
		
		/** 게시글 등록용  Dao
		 * @param conn
		 * @param board
		 * @return result
		 * @throws Exception
		 */
		public int insertBoard(Connection conn, BoardHJ board) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertBoard");
			System.out.println(board);
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, board.getBoardNo());
				pstmt.setString(2, board.getBoardTitle());
				pstmt.setString(3, board.getBoardContent());
				pstmt.setString(4, board.getBoardURL());
				pstmt.setString(5, board.getBoardWriter());
				pstmt.setInt(6, board.getBoardCode());
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		/** 동물 코드 삽입용 Dao
		 * @param conn
		 * @param animal
		 * @return result
		 * @throws Exception
		 */
		public int insertAnimal(Connection conn, Animal animal) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			
			String query = prop.getProperty("insertAnimal");
			System.out.println(animal);
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, animal.getAnimalCode());
				pstmt.setString(2, animal.getAnimalGender());
				pstmt.setString(3, animal.getAnimalType());
				pstmt.setString(4, animal.getAnimalBreed());
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		/** 게시글 파일(이미지) 정보 삽입용 Dao
		 * @param conn
		 * @param file
		 * @return result
		 * @throws Exception
		 */
		public int insertAttachment(Connection conn, Attachment file) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, file.getFileOriginName());
				pstmt.setString(2, file.getFileChangeName());
				pstmt.setString(3, file.getFilePath());
				pstmt.setInt(4, file.getFileLevel());
				pstmt.setInt(5, file.getBoardNo());
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		/** 전체 게시글 수 조회용 Dao
		 * @param conn
		 * @return listCount
		 * @throws Exception
		 */
		public int getListCount(Connection conn,int boardType) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int listCount = 0;
			String query = prop.getProperty("getListCount");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, boardType);
				
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
		
		/** 게시판 목록 조회용 Dao
		 * @param conn
		 * @param currentPage
		 * @param limit
		 * @return bList
		 * @throws Exception
		 */
		public List<BoardHJ> selectFList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<BoardHJ> bList = null;
			
			String query = prop.getProperty("selectFList");
			
			try {
				// 쿼리문 실행 시 between 조건에 사용될 값
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				pstmt=conn.prepareStatement(query);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				bList = new ArrayList<BoardHJ>();
				BoardHJ board = null;
				
				while(rset.next()) {
					board = new BoardHJ(rset.getInt("BOARD_NO"),
									rset.getString("BOARD_TITLE"),
									rset.getString("BOARD_CONTENT"),
									rset.getInt("BOARD_COUNT"),
									rset.getDate("BOARD_MODIFY_DT"),
									rset.getString("MEM_ID")
									);
					bList.add(board);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return bList;
		}
		
		/** 썸네일 이미지 목록 조회 Dao
		 * @param conn
		 * @param currentPage
		 * @param limit
		 * @return fList
		 * @throws Exception
		 */
		public List<Attachment> selectAList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Attachment> aList = null;
			
			String query = prop.getProperty("selectAList");
			
			try {
				// 쿼리문 실행 시 between 조건에 사용될 값
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				pstmt=conn.prepareStatement(query);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				aList = new ArrayList<Attachment>();
				Attachment file = null;
				
				while(rset.next()) {
					file = new Attachment();
					file.setFileNo(rset.getInt("IMG_NO"));
					file.setBoardNo(rset.getInt("BOARD_NO"));
					file.setFileChangeName(rset.getString("IMG_CHANGE_NAME"));
					file.setFileStatus(rset.getString("IMG_STATUS"));
					
					aList.add(file);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return aList;
		}
		
		/** 목록 조회용 동물 정보 반환 Dao
		 * @param conn
		 * @param currentPage
		 * @param limit
		 * @param boardType
		 * @return animalList
		 * @throws Exception
		 */
		public List<Animal> selectAnimalList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Animal> animalList = null;
			String query = null;
			
			if(boardType==1) {
			query = prop.getProperty("selectFindAnimalList");
			} else if(boardType==2) {
				query = prop.getProperty("selectSeeAnimalList");
			} else if(boardType==3) {
				query = prop.getProperty("selectAdoptAnimalList");
			}
			
			try {
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				pstmt=conn.prepareStatement(query);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				animalList = new ArrayList<Animal>();
				Animal animal = null;
				
				while(rset.next()) {
					animal = new Animal();
					animal.setAnimalCode(rset.getInt("ANIMAL_CODE"));
					animal.setAnimalGender(rset.getString("ANIMAL_GENDER"));
					animal.setAnimalType(rset.getString("ANIMAL_TYPE"));
					animal.setAnimalBreed(rset.getString("ANIMAL_BREED"));
					animal.setAnimalStatus(rset.getString("ANIMAL_STATUS"));
					
					animalList.add(animal);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return animalList;
		}
		
		/** 게시글 상세 조회용 Dao
		 * @param conn
		 * @param boardNo
		 * @return board
		 * @throws Exception
		 */
		public BoardHJ selectboard(Connection conn, int boardNo) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			BoardHJ board = null;
			
			String query = prop.getProperty("selectBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					board = new BoardHJ(boardNo, rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getInt("BOARD_COUNT"),
							rset.getDate("BOARD_MODIFY_DT"),
							rset.getString("BOARD_URL"),
							rset.getString("MEM_ID"),
							rset.getInt("BOARD_CODE"));
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return board;
		}
		
		/** 게시글 이미지 파일 조회용 Dao
		 * @param conn
		 * @param boardNo
		 * @return files
		 * @throws Exception
		 */
		public List<Attachment> selectFiles(Connection conn, int boardNo) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Attachment> files = null;
			
			String query = prop.getProperty("selectFiles");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				files = new ArrayList<Attachment>();
				Attachment file = null;
				
				while(rset.next()) {
					file = new Attachment(rset.getInt("IMG_NO"),
											rset.getInt("BOARD_NO"), 
											rset.getString("IMG_ORIGIN_NAME"),
											rset.getString("IMG_CHANGE_NAME"),
											rset.getString("IMG_PATH"),
											rset.getDate("IMG_CREATE_DT"),
											rset.getInt("IMG_LEVEL"),
											rset.getString("IMG_STATUS"));
					
					files.add(file);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return files;
		}

		/** 게시글 동물 조회용 Dao
		 * @param conn
		 * @param animalCode
		 * @return animal
		 * @throws Exception
		 */
		public Animal selectAnimal(Connection conn, int animalCode) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Animal animal = null;
			
			String query = prop.getProperty("selectAnimal");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, animalCode);
				rset= pstmt.executeQuery();
				
				if(rset.next()) {
					animal = new Animal(animalCode,
							rset.getString("ANIMAL_GENDER"),
							rset.getString("ANIMAL_TYPE"),
							rset.getString("ANIMAL_BREED"));
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return animal;
		}

		/** 게시글 삭제용 Dao
		 * @param conn
		 * @param no
		 * @return result
		 * @throws Exception
		 */
		public int deleteBoard(Connection conn, int no) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 사진 삭제용 Dao
		 * @param conn
		 * @param no
		 * @return result
		 * @throws Exception
		 */
		public int deleteAttachment(Connection conn, int no) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		
		/** 게시판 수정용 Dao
		 * @param conn
		 * @param board
		 * @return result
		 * @throws Exception
		 */
		public int updateBoard(Connection conn, BoardHJ board) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("updateBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, board.getBoardTitle());
				pstmt.setString(2, board.getBoardContent());
				pstmt.setString(3, board.getBoardURL());
				pstmt.setInt(4, board.getBoardNo());
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		
		/** 썸네일 삭제용 Dao
		 * @param conn
		 * @param boardNo
		 * @return result
		 * @throws Exception
		 */
		public int deleteThumbnail(Connection conn, int boardNo) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteThumbnail");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, boardNo);
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 이미지 파일 카운트용 Dao
		 * @param conn
		 * @param boardNo
		 * @return count
		 * @throws Exception
		 */
		public int countAttachment(Connection conn, int boardNo) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int count = 0;
			String query = prop.getProperty("countAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					count = rset.getInt(1);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return count;
		}

		/** 일반 이미지 삭제용 Dao
		 * @param conn
		 * @param boardNo
		 * @return result
		 * @throws Exception
		 */
		public int deleteImg(Connection conn, int boardNo, int over) throws Exception {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteImg");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, boardNo);
				pstmt.setInt(2, over);
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 검색 게시글 수 조회용 Dao
		 * @param conn
		 * @param boardType
		 * @return searchListCount
		 * @throws Exception
		 */
		public int getSearchListCount(Connection conn, String condition, int boardType) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int searchListCount = 0;
			String query = prop.getProperty("getSearchListCount");
			
			try {
				pstmt = conn.prepareStatement(query + condition);
				
				pstmt.setInt(1, boardType);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					searchListCount = rset.getInt(1); 
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			return searchListCount;
		}

		/** 게시글 검색 조회용 Service
		 * @param conn
		 * @param currentPage
		 * @param limit
		 * @param boardType
		 * @param condition
		 * @return bList
		 * @throws Exception
		 */
		public List<BoardHJ> searchBoardList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<BoardHJ> bList = null;
			
			String query1 = prop.getProperty("searchBoardList1");
			String query2 = prop.getProperty("searchBoardList2");
			
			try {
				pstmt = conn.prepareStatement(query1+condition+query2);
				
				System.out.println(query1+condition+query2);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				bList = new ArrayList<BoardHJ>();
				BoardHJ board = null;
				
				while(rset.next()) {
					board = new BoardHJ(rset.getInt("BOARD_NO"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("BOARD_CONTENT"),
									rset.getInt("BOARD_COUNT"),
									rset.getDate("BOARD_MODIFY_DT"), 
									rset.getString("MEM_ID"), 
									rset.getInt("BOARD_CODE"));
					
					System.out.println(rset.getInt("BOARD_NO"));
					
					bList.add(board);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			return bList;
		}

		/** 검색 게시글 동물 조회용 Dao
		 * @param conn
		 * @param currentPage
		 * @param limit
		 * @param boardType
		 * @param condition
		 * @return aList
		 * @throws Exception
		 */
		public List<Attachment> searchAList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Attachment> aList = null;
			
			String query1 = prop.getProperty("searchAList1");
			String query2 = prop.getProperty("searchAList2");
			
			System.out.println(query1+condition+query2);
			
			try {
				pstmt = conn.prepareStatement(query1+condition+query2);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				aList = new ArrayList<Attachment>();
				Attachment file = null;
				
				while(rset.next()) {
					file = new Attachment();
					file.setFileNo(rset.getInt("IMG_NO"));
					file.setBoardNo(rset.getInt("BOARD_NO"));
					file.setFileChangeName(rset.getString("IMG_CHANGE_NAME"));
					file.setFileStatus(rset.getString("IMG_STATUS"));
					
					aList.add(file);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return aList;
		}

		/** 검색 게시글 동물 조회용 Dao
		 * @param conn
		 * @param startRow
		 * @param endRow
		 * @param boardType
		 * @param condition
		 * @return animalList
		 * @throws Exception
		 */
		public List<Animal> searchAnimalList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception{
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Animal> animalList = null;
			String query1 = null;
			String query2 = null;
			
			if(boardType==1) {
				query1 = prop.getProperty("searchFindAnimalList1");
				query2 = prop.getProperty("searchFindAnimalList2");
			} else if(boardType==2) {
				query1 = prop.getProperty("searchSeeAnimalList1");
				query2 = prop.getProperty("searchSeeAnimalList2");
			} else if(boardType==3) {
				query1 = prop.getProperty("searchAdoptAnimalList1");
				query2 = prop.getProperty("searchAdoptAnimalList2");
			}
			
			try {
				pstmt = conn.prepareStatement(query1+condition+query2);
				
				pstmt.setInt(1, boardType);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				animalList = new ArrayList<Animal>();
				Animal animal = null;
				
				while(rset.next()) {
					animal = new Animal();
					
					animal.setAnimalCode(rset.getInt("ANIMAL_CODE"));
					animal.setAnimalGender(rset.getString("ANIMAL_GENDER"));
					animal.setAnimalType(rset.getString("ANIMAL_TYPE"));
					animal.setAnimalBreed(rset.getString("ANIMAL_BREED"));
					animal.setAnimalStatus(rset.getString("ANIMAL_STATUS"));
					
					animalList.add(animal);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return animalList;
		}


		
		
		
		
	
} // class END

