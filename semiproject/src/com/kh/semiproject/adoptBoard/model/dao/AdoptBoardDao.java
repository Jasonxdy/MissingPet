package com.kh.semiproject.adoptBoard.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.semiproject.adoptBoard.model.vo.AdoptBoard;

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

}
