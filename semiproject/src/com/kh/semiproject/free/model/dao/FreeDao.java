package com.kh.semiproject.free.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardEH;
import com.kh.semiproject.board.model.vo.Img;
import com.kh.semiproject.board.model.vo.M_Comment;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.free.model.vo.Free;
import com.kh.semiproject.review.model.vo.Comment;

public class FreeDao {
	

	private Properties prop = null;
	
	public FreeDao() throws Exception{
		
		String fileName
		= FreeDao.class
		.getResource("/com/kh/semiproject/sql/free/free-query.properties")
		.getPath();
		
		prop = new Properties();
		
		prop.load(new FileReader(fileName));
		
	}
	
	/** 전체 게시글 수 조회용 Dao
	 * @param conn
	 * @param boardType 
	 * @return listCount
	 * @throws Exception
	 */
	
	public int getListCount(Connection conn, int boardType) throws Exception {
		
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
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
	
	
	/** 자게 목록 조회용 Dao
	 * @param conn
	 * @param limit 
	 * @param currentPage 
	 * @param boardType 
	 * @return list
	 * @throws Exception
	 */

	public List<BoardEH> selectList(Connection conn, int currentPage, int limit, int boardType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardEH> blist = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			
			
			int startRow = (currentPage-1)*limit+1;
			int endRow = startRow + limit -1;

			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			blist = new ArrayList<BoardEH>();
			
			BoardEH board = null;
			
			
			while(rset.next()) {
				
				board = new BoardEH(rset.getInt("BOARD_NO"), 
						rset.getString("BOARD_TITLE"), 
						rset.getDate("BOARD_CREATE_DT"), 
						rset.getInt("BOARD_COUNT"), 
						rset.getString("MEM_ID"));
				
				blist.add(board);
				
			}
			
		}finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		return blist;
	}
	
	
	
	

	/** 자게 목록 조회 (카테고리)
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param boardType 
	 * @return
	 * @throws Exception
	 */
	public List<Free> selectfList(Connection conn, int currentPage, int limit, int boardType) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<Free> flist = null;
		
		String query = prop.getProperty("selectfList");
		
		try {
			
			// 쿼리문 실행 시 between 조건에 사용될 값
			int startRow = (currentPage-1)*limit+1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			flist = new ArrayList<Free>();
			
			Free free = null;
			
			while(rset.next()) {
				
				free = new Free(
						rset.getInt("BOARD_NO"),
						rset.getString("FREE_CATEGORY"));
				// free.setBoardNo(rset.getInt("BOARD_NO"));
				
				flist.add(free);
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return flist;
	}



	/** 다음 게시글 번호 반환용 Dao
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */

	public int selectNextNo(Connection conn) throws Exception{
		
		Statement stmt = null;
		ResultSet rset = null;
		int no = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt=conn.createStatement();
			rset = stmt.executeQuery(query);		
			
			if(rset.next()) {
				no = rset.getInt(1);
			}
			
		}finally {
			close(rset);
			close(stmt);
		}
		
		return no;
	}


	/** 게시글 상세 조회용 Dao
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */

	public BoardEH selectFree(Connection conn, int no) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardEH board = null;
		
		String query = prop.getProperty("selectFree");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				board = new BoardEH(no, 
						rset.getString("BOARD_TITLE"), 
						rset.getString("BOARD_CONTENT"), 
						rset.getDate("BOARD_CREATE_DT"), 
						rset.getInt("BOARD_COUNT"),
						rset.getString("BOARD_URL"),
						rset.getString("MEM_ID"));
			}
			
			
		}finally {
			close(pstmt);
			close(rset);
		}
		return board;
		
	}


	public int increaseCount(Connection conn, int no) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("increaseCount");
		
		try{
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	


	public Free selectFree2(Connection conn, int no)throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Free free = null;
		
		String query = prop.getProperty("selectFree2");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {

				free = new Free(rset.getString("FREE_CATEGORY"));
				
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return free;
	}


	
	/** 댓글 등록용 Dao
	 * @param conn
	 * @param reply
	 * @param replyWriter
	 * @return result
	 * @throws Exception
	 */
	public int insertComm(Connection conn, Comment comm, String commentWriter) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertComm");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comm.getCommentContent());
			pstmt.setString(2, commentWriter);
			pstmt.setInt(3, comm.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}



	public List<Comment> selectCommList(Connection conn, int boardNo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Comment> mlist = null;
		 
		String query = prop.getProperty("selectCommList");
		
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			mlist =  new ArrayList<Comment>();
			
			Comment mcomm = null;
			
			while(rset.next()) {
				
				mcomm = new Comment(
						rset.getInt("COMM_NO"),
						rset.getString("COMM_CONTENT"),
						rset.getString("COMM_MODIFY_DT"),
						rset.getString("MEM_ID"),
						rset.getString("MEM_PRO_IMG"));
				
				mlist.add(mcomm);
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		return mlist;
	}

	
	public int updateComment(Connection conn, Comment comment) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteComment(Connection conn, int commentNo) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	


	public int deleteFree(Connection conn, int no) throws Exception{
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteFree");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	
	/** 게시글 등록용 Dao
	 * @param conn
	 * @param board
	 * @param boardWriter
	 * @return result 
	 * @throws SQLException 
	 * @throws Exception
	 */
	
	public int insertFree(Connection conn, BoardEH board, String memberId) throws SQLException {
		

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setString(4, board.getBoardUrl());
			pstmt.setString(5, memberId);
			pstmt.setInt(6, 5);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
				
		return result;
	}
	
	
	/** 게시글 파일(이미지) 정보 삽입용 Dao
	 * @param conn
	 * @param file
	 * @return result
	 * @throws SQLException 
	 * @throws Exception
	 */
	public int insertImg(Connection conn, Img file) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertImg");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, file.getImgOriginName());
			pstmt.setString(2, file.getImgChangeName());
			pstmt.setString(3, file.getImgPath());
			pstmt.setInt(4, file.getImgLevel());
			pstmt.setInt(5, file.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
			}finally {
				close(pstmt);
			}
			
			return result;
	}
	
	

	public int insertFreeCategory(Connection conn, Free free) throws Exception {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFreeCategory");
		
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, free.getBoardNo());
			pstmt.setString(2, free.getFreeCategory());
			result = pstmt.executeUpdate();
			
			
			}finally {
				close(pstmt);
			}
			
			return result;
	}

	public List<Img> selectImg(Connection conn, int no)  throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Img> ilist = null;
		
		String query = prop.getProperty("selectImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			ilist = new ArrayList<Img>();
			Img img = null;
			
			while(rset.next()) {
				img = new Img(rset.getInt("IMG_NO"),
										rset.getString("IMG_ORIGIN_NAME"),
										rset.getString("IMG_CHANGE_NAME"),
										rset.getString("IMG_PATH"),
										rset.getDate("IMG_CREATE_DT"),
										rset.getString("IMG_STATUS"),
										rset.getInt("IMG_LEVEL"),
										rset.getInt("BOARD_NO"));
				
				ilist.add(img);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ilist;
	}
	
	
	
	
	/** 게시판 수정용 Dao
	 * @param conn
	 * @param board
	 * @return result
	 * @throws Exception 
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, BoardEH board) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getBoardUrl());
			pstmt.setInt(4, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	/** 카테고리 수정용 Dao
	 * @param conn
	 * @param animal
	 * @return result
	 * @throws Exception 
	 * @throws Exception
	 */
	public int updateFree(Connection conn, Free free) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateFree");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, free.getFreeCategory());
			pstmt.setInt(2, free.getBoardNo());
			
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
	 * @throws Exception
	 */
	public int countImg(Connection conn, int boardNo) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		String query = prop.getProperty("countImg");
		
		
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
	public int deleteImg(Connection conn, int boardNo, int over) throws Exception{
		
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

	public int deleteBeforeImage(Connection conn, String beforePath) throws Exception{
		
		   PreparedStatement pstmt = null;
		      int result = 0;
		      String query = prop.getProperty("deleteBeforeImage");
		      
		      try {
		         pstmt = conn.prepareStatement(query);
		         pstmt.setString(1, beforePath);
		         
		         result = pstmt.executeUpdate();
		      }finally {
		         close(pstmt);
		      }
		      return result;
	}

	
	/** 검색 게시글 수 조회용 Dao
	 * @param conn
	 * @param boardType
	 * @return searchListCount
	 * @throws Exception 
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

	
	
	/** 검색용 dao
	 * @param conn
	 * @param condition 
	 * @param condition
	 * @return
	 * @throws Exception
	 */

	public List<BoardEH> searchList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception{
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BoardEH> blist = null;
		
		String query1 = prop.getProperty("searchBoard1");
		String query2 = prop.getProperty("searchBoard2");
		
		
		try {
			System.out.println(query1+condition+query2);
			pstmt = conn.prepareStatement(query1+condition+query2);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			blist = new ArrayList<BoardEH>();
			BoardEH board = null;
			
			while(rset.next()) {
				board = new BoardEH(rset.getInt("BOARD_NO"), 
						rset.getString("BOARD_TITLE"), 
						rset.getDate("BOARD_CREATE_DT"), 
						rset.getInt("BOARD_COUNT"), 
						rset.getString("MEM_ID"));
				
				blist.add(board);
			}
			
			
		}finally {
			
			close(rset);
			close(pstmt);
		}
		
		return blist;
	}



	public List<Free> searchfList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Free> fList = null;
		

		String query1 = prop.getProperty("searchfList1");
		String query2 = prop.getProperty("searchfList2");
		
		try {
			
			System.out.println(query1+condition+query2);
			pstmt = conn.prepareStatement(query1+condition+query2);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<Free>();
			Free free = null;
			
			while(rset.next()) {
				free = new Free();
				free.setBoardNo(rset.getInt("BOARD_NO"));
				free.setFreeCategory(rset.getString("FREE_CATEGORY"));
				
				fList.add(free);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
	





}
