package com.kh.semiproject.map.model.DAO;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.kh.semiproject.findBoard.model.dao.FindBoardDao;
import com.kh.semiproject.map.model.vo.Map;

public class MapDAO {
	private Properties prop = null;
	
	public MapDAO() throws FileNotFoundException, IOException {
		String fileName = FindBoardDao.class.getResource("/com/kh/semiproject/sql/map/map-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
	
	/** 글쓰기 insert용 map DAO
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertMap(Connection conn, Map map) throws Exception {
		//
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertMap");
		String query1 = prop.getProperty("insertNoMap");
		try {
			if(map.getMapLatitude() != 0) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, map.getBoardNo());
				pstmt.setDouble(2, map.getMapLatitude());
				pstmt.setDouble(3, map.getMapLongitude());
				pstmt.setString(4, map.getMapAddress());
				
				result = pstmt.executeUpdate();
			}else {
				pstmt = conn.prepareStatement(query1);
				pstmt.setInt(1, map.getBoardNo());
				result = pstmt.executeUpdate();
			}
			
		}finally {
			close(pstmt);
		}
		return result;
	}


	/** 글 상세보기용 map DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Map selectMap(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Map map = null;
		String query = prop.getProperty("selectMap");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				map = new Map(boardNo, 
						rset.getDouble("MAP_LATITUDE"), 
						rset.getDouble("MAP_LONGITUDE"), 
						rset.getString("MAP_ADDRESS"));
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return map;
	}


	public int updateMap(Connection conn, Map map) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateMap");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDouble(1, map.getMapLatitude());
			pstmt.setDouble(2, map.getMapLongitude());
			pstmt.setString(3, map.getMapAddress());
			pstmt.setInt(4, map.getBoardNo());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
}
