package com.kh.semiproject.findBoard.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.board.model.vo.Alram;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.findBoard.model.vo.FindBoard;

public class FindBoardDao {
	
	private Properties prop = null;
	
	public FindBoardDao() throws Exception{
		String fileName = FindBoardDao.class.getResource("/com/kh/semiproject/sql/board/board-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 찾아요 게시판 삽입용 Dao
	 * @param conn
	 * @param findBoard
	 * @return result
	 * @throws Exception
	 */
	public int insertFindBoard(Connection conn, FindBoard findBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFindBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, findBoard.getBoardNo());
			pstmt.setString(2, findBoard.getfBoardLocation());
			pstmt.setInt(3, findBoard.getfBoardReward());
			pstmt.setString(4, findBoard.getfBoardPhone());
			pstmt.setDate(5, findBoard.getfBoardDate());
			pstmt.setString(6, findBoard.getfBoardMap());
			pstmt.setString(7, findBoard.getfBoardLocationTell());
			pstmt.setString(8, findBoard.getfBoardBreedTell());
			pstmt.setString(9, findBoard.getfBoardCommentTell());
			pstmt.setInt(10, findBoard.getAnimalCode());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 찾아주세요 게시판 목록 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return fList
	 * @throws Exception
	 */
	public ArrayList<FindBoard> selectFindList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FindBoard> fList = null;
		
		String query = prop.getProperty("selectFindList");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<FindBoard>();
			FindBoard findBoard = null;
			
			while(rset.next()) {
				findBoard = new FindBoard();
				findBoard.setBoardNo(rset.getInt("BOARD_NO"));
				findBoard.setfBoardLocation(rset.getString("FIND_LOCATION"));
				findBoard.setfBoardDate(rset.getDate("FIND_DATE"));
				findBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				fList.add(findBoard);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}

	
	/** 찾아주세요 게시글 조회용 Dao
	 * @param conn
	 * @param boardNo
	 * @return findBoard
	 * @throws Exception
	 */
	public FindBoard selectFindBoard(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FindBoard findBoard = null;
		
		String query = prop.getProperty("selectFindBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findBoard = new FindBoard(rset.getInt(1),
									rset.getString(2), 
									rset.getInt(3),
									rset.getString(4), 
									rset.getDate(5), 
									rset.getString(6),
									rset.getString(7),
									rset.getString(8),
									rset.getString(9),
									rset.getInt(10));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findBoard;
	}

	/** 찾아주세요 게시판 수정용 Dao
	 * @param conn
	 * @param findBoard
	 * @return result
	 * @throws Exception
	 */
	public int updateFindBoard(Connection conn, FindBoard findBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateFindBoard");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, findBoard.getfBoardLocation());
			pstmt.setInt(2, findBoard.getfBoardReward());
			pstmt.setString(3, findBoard.getfBoardPhone());
			pstmt.setDate(4, findBoard.getfBoardDate());
			pstmt.setString(5, findBoard.getfBoardMap());
			pstmt.setString(6, findBoard.getfBoardLocationTell());
			pstmt.setString(7, findBoard.getfBoardBreedTell());
			pstmt.setString(8, findBoard.getfBoardCommentTell());
			pstmt.setInt(9, findBoard.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}
	
	/** 찾아주세요 동물 수정용 Dao
	 * @param conn
	 * @param animal
	 * @return result
	 * @throws Exception
	 */
	public int updateFindAnimal(Connection conn, Animal animal, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateFindAnimal");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, animal.getAnimalGender());
			pstmt.setString(2, animal.getAnimalType());
			pstmt.setString(3, animal.getAnimalBreed());
			pstmt.setInt(4, boardNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 찾아주세요 동물코드 삭제용 Dao
	 * @param conn
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteFindAnimal(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteFindAnimal");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 찾아주세요 게시글 검색용 Dao
	 * @param conn
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @return fList
	 * @throws Exception
	 */
	public ArrayList<FindBoard> searchFindList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FindBoard> fList = null;
		
		String query1 = prop.getProperty("searchFindList1");
		String query2 = prop.getProperty("searchFindList2");
		
		try {
			pstmt = conn.prepareStatement(query1+condition+query2);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			fList = new ArrayList<FindBoard>();
			FindBoard findBoard = null;
			
			while(rset.next()) {
				findBoard = new FindBoard();
				
				findBoard.setBoardNo(rset.getInt("BOARD_NO"));
				findBoard.setfBoardLocation(rset.getString("FIND_LOCATION"));
				findBoard.setfBoardDate(rset.getDate("FIND_DATE"));
				findBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				fList.add(findBoard);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return fList;
	}

	/** 찾아주세요 게시판 알림 대상 조회용 Dao
	 * @param conn
	 * @return alramList
	 * @throws Exception
	 */
	public List<Alram> selectFindAlram(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Alram> alramList = null;
		
		String query = prop.getProperty("selectFindAlram");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			alramList = new ArrayList<Alram>();
			
			Alram alram = null;
			
			while(rset.next()) {
				alram = new Alram(rset.getString("SEE_LOCATION_TELL"),
									rset.getString("SEE_BREED_TELL"),
									rset.getString("SEE_LOCATION"),
									rset.getString("ANIMAL_BREED"),
									rset.getString("MEM_EMAIL"),
									rset.getInt("BOARD_CODE"));
				
				alramList.add(alram);
			}
		} finally {
			close(rset);
			close(stmt);
		}
		return alramList;
	}

	

}
