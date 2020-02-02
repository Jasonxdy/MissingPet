package com.kh.semiproject.adoptBoard.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semiproject.adoptBoard.model.vo.AdoptBoard;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;

public class AdoptBoardDao {
	
	private Properties prop = null;

	public AdoptBoardDao() throws Exception{
		String fileName = AdoptBoardDao.class.getResource("/com/kh/semiproject/sql/board/board-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
	/** 분양합니다 게시판 목록 조회용 Dao
	 * @param conn
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return adopt List
	 * @throws Exception
	 */
	public ArrayList<AdoptBoard> selectFindList(Connection conn, int currentPage, int limit, int boardType) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdoptBoard> adoptList = null;
		
		String query = prop.getProperty("selectAdoptList");
		
		try {
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + limit -1;
			
			pstmt=conn.prepareStatement(query);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			adoptList = new ArrayList<AdoptBoard>();
			AdoptBoard adoptBoard = null;
			
			while(rset.next()) {
				adoptBoard = new AdoptBoard();
				adoptBoard.setBoardNo(rset.getInt("BOARD_NO"));
				adoptBoard.setaBoardLocation(rset.getString("ADOPT_LOCATION"));
				adoptBoard.setaBoardDone(rset.getString("ADOPT_DONE"));
				adoptBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				adoptList.add(adoptBoard);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return adoptList;
	}

	/** 분양합니다 게시판 삽입용 Dao
	 * @param conn
	 * @param adoptBoard
	 * @return result
	 * @throws Exception
	 */
	public int insertAdoptBoard(Connection conn, AdoptBoard adoptBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAdoptBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, adoptBoard.getBoardNo());
			pstmt.setString(2, adoptBoard.getaBoardLocation());
			pstmt.setInt(3, adoptBoard.getaBoardCost());
			pstmt.setString(4, adoptBoard.getaBoardPhone());
			pstmt.setString(5, adoptBoard.getaBoardMap());
			pstmt.setString(6, adoptBoard.getaBoardNeutral());
			pstmt.setString(7, adoptBoard.getaBoardVac());
			pstmt.setString(8, adoptBoard.getaBoardHealth());
			pstmt.setInt(9, adoptBoard.getAnimalCode());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 입양합니다 게시글 조회용 dao
	 * @param conn
	 * @param boardNo
	 * @return adoptBoard
	 * @throws Exception
	 */
	public AdoptBoard selectAdoptBoard(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AdoptBoard adoptBoard = null;
		
		String query = prop.getProperty("selectAdoptBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				adoptBoard = new AdoptBoard(rset.getInt(1),
									rset.getString(2), 
									rset.getInt(3), 
									rset.getString(4), 
									rset.getString(5),
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
		
		return adoptBoard;
	}

	/** 분양합니다 동물 수정용 Dao
	 * @param conn
	 * @param animal
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateAdoptAnimal(Connection conn, Animal animal, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAdoptAnimal");
		
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

	/** 분양합니다 게시판 수정용 Dao
	 * @param conn
	 * @param adoptBoard
	 * @return
	 * @throws Exception
	 */
	public int updateAdoptBoard(Connection conn, AdoptBoard adoptBoard) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateAdoptBoard");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, adoptBoard.getaBoardLocation()); 
			pstmt.setInt(2,adoptBoard.getaBoardCost()); 
			pstmt.setString(3, adoptBoard.getaBoardPhone());
			pstmt.setString(4, adoptBoard.getaBoardMap());
			pstmt.setString(5, adoptBoard.getaBoardNeutral());
			pstmt.setString(6, adoptBoard.getaBoardVac());
			pstmt.setString(7, adoptBoard.getaBoardHealth());
			pstmt.setString(8, adoptBoard.getaBoardDone());
			pstmt.setInt(9, adoptBoard.getBoardNo());
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 입양합니다 게시판 동물코드 삭제용 Dao
	 * @param conn
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int adoptDeleteAnimal(Connection conn, int no) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("deleteAdoptAnimal");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/** 분양합니다 게시글 검색용 Dao
	 * @param conn
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @param condition2
	 * @return adoptList
	 * @throws Exception
	 */
	public ArrayList<AdoptBoard> searchFindList(Connection conn, int startRow, int endRow, int boardType, String condition, String condition2) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdoptBoard> adoptList = null;
		
		String query1 = prop.getProperty("searchAdoptList1");
		String query2 = prop.getProperty("searchAdoptList2");
		
		try {
			pstmt = conn.prepareStatement(query1+condition+condition2+query2);
			
			pstmt.setInt(1, boardType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			adoptList = new ArrayList<AdoptBoard>();
			AdoptBoard adoptBoard = null;
			
			while(rset.next()) {
				adoptBoard = new AdoptBoard();
				
				//findBoard.setBoardNo(rset.getInt("BOARD_NO"));
				//findBoard.setfBoardLocation(rset.getString("FIND_LOCATION"));
				//findBoard.setfBoardDate(rset.getDate("FIND_DATE"));
				//findBoard.setAnimalCode(rset.getInt("ANIMAL_CODE"));
				
				adoptList.add(adoptBoard);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		return adoptList;
	}

}
