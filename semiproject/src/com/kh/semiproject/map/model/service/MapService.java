package com.kh.semiproject.map.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.semiproject.map.model.DAO.MapDAO;
import com.kh.semiproject.map.model.vo.Map;

public class MapService {

	/** 글 상세보기용 map Service
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Map selectMap(int boardNo) throws Exception {
		//
		Connection conn = getConnection();
		MapDAO mapDAO = new MapDAO();
		Map map = mapDAO.selectMap(conn, boardNo);
		
		close(conn);
		return map;
	}

}
