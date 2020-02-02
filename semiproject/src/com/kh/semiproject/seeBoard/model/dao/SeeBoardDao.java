package com.kh.semiproject.seeBoard.model.dao;

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
import com.kh.semiproject.findBoard.model.dao.FindBoardDao;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;

public class SeeBoardDao {
	
	private Properties prop = null;
	
	public SeeBoardDao() throws Exception{
		String fileName = FindBoardDao.class.getResource("/com/kh/semiproject/sql/board/board-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	/** 봤어요 게시판 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public ArrayList<SeeBoard> selectSeeList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SeeBoard> sList = null;
		
		String query = prop.getProperty("selectSeeList");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			sList = new ArrayList<SeeBoard>();
			SeeBoard seeBoard = null;
			
			while(rset.next()) {
				seeBoard = new SeeBoard();
				seeBoard.setBoardNo(rset.getInt("BOARD_NO"));
				seeBoard.setsBoardLocation(rset.getString("SEE_LOCATION"));
				seeBoard.setsBoardDate(rset.getDate("SEE_DATE"));
				seeBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				sList.add(seeBoard);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return sList;
	}

	/** 봤어요 게시글 조회용 Dao
	 * @param conn
	 * @param seeBoard
	 * @return seeBoard
	 * @throws Exception
	 */
	public int insertSeeBoard(Connection conn, SeeBoard seeBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertSeeBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, seeBoard.getBoardNo());
			pstmt.setString(2, seeBoard.getsBoardLocation());
			pstmt.setString(3, seeBoard.getsBoardPhone());
			pstmt.setDate(4, seeBoard.getsBoardDate());
			pstmt.setString(5, seeBoard.getsBoardMap());
			pstmt.setString(6, seeBoard.getsBoardLocationTell());
			pstmt.setString(7, seeBoard.getsBoardBreedTell());
			pstmt.setString(8, seeBoard.getsBoardCommentTell());
			pstmt.setInt(9, seeBoard.getAnimalCode());
		
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 봤어요 게시글 조회용 Dao
	 * @param conn
	 * @param boardNo
	 * @return seeBoard
	 * @throws Exception
	 */
	public SeeBoard selectSeeBoard(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		SeeBoard seeBoard = null;
		
		String query = prop.getProperty("selectSeeBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				seeBoard = new SeeBoard(rset.getInt(1),
									rset.getString(2), 
									rset.getString(3), 
									rset.getDate(4), 
									rset.getString(5),
									rset.getString(6),
									rset.getString(7),
									rset.getString(8),
									rset.getInt(9));
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return seeBoard;
	}

	/** 봤어요 게시판 수정용 Dao
	 * @param conn
	 * @param seeBoard
	 * @return result
	 * @throws Exception
	 */
	public int updateSeeBoard(Connection conn, SeeBoard seeBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateSeeBoard");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, seeBoard.getsBoardLocation());
			pstmt.setString(2, seeBoard.getsBoardPhone());
			pstmt.setDate(3, seeBoard.getsBoardDate());
			pstmt.setString(4, seeBoard.getsBoardMap());
			pstmt.setString(5, seeBoard.getsBoardLocationTell());
			pstmt.setString(6, seeBoard.getsBoardBreedTell());
			pstmt.setString(7, seeBoard.getsBoardCommentTell());
			pstmt.setInt(8, seeBoard.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/** 봤어요 동물 수정용 Dao
	 * @param conn
	 * @param animal
	 * @return result
	 * @throws Exception
	 */
	public int updateSeeAnimal(Connection conn, Animal animal, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateSeeAnimal");
		
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

	/** 봤어요 게시판 동물코드 삭제용 Dao
	 * @param conn
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int seeDeleteAnimal(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteSeeAnimal");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 봤어요 게시글 검색용 Dao
	 * @param conn
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @return sList
	 * @throws Exception
	 */
	public ArrayList<SeeBoard> searchSeeList(Connection conn, int startRow, int endRow, int boardType, String condition) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SeeBoard> sList = null;
		
		String query1 = prop.getProperty("searchSeeList1");
		String query2 = prop.getProperty("searchSeeList2");
		
		try {
			pstmt = conn.prepareStatement(query1+condition+query2);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			sList = new ArrayList<SeeBoard>();
			SeeBoard seeBoard = null;
			
			while(rset.next()) {
				seeBoard = new SeeBoard();
				
				seeBoard.setBoardNo(rset.getInt("BOARD_NO"));
				seeBoard.setsBoardLocation(rset.getString("SEE_LOCATION"));
				seeBoard.setsBoardDate(rset.getDate("SEE_DATE"));
				seeBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				sList.add(seeBoard);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return sList;
	}

	/** 봤어요 게시판 알림 대상 조회용 Dao
	 * @param conn
	 * @return alramList
	 * @throws Exception
	 */
	public List<Alram> selectSeeAlram(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		List<Alram> alramList = null;
		
		String query = prop.getProperty("selectSeeAlram");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			alramList = new ArrayList<Alram>();
			
			Alram alram = null;
			
			while(rset.next()) {
				alram = new Alram(rset.getString("FIND_LOCATION_TELL"),
									rset.getString("FIND_BREED_TELL"),
									rset.getString("FIND_LOCATION"),
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
