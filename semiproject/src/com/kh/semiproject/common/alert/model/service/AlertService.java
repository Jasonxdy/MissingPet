package com.kh.semiproject.common.alert.model.service;

import java.sql.Connection;
import java.util.List;

import static com.kh.semiproject.common.JDBCTemplate.*;

import com.kh.semiproject.common.alert.model.dao.AlertDAO;
import com.kh.semiproject.common.alert.model.vo.Alert;

public class AlertService {

	public List<Alert> selectAlert(String memberId) throws Exception {
		
		Connection conn = getConnection();
		return new AlertDAO().selectAlert(conn, memberId);
	}

	
	/**
	 * 로그인한 유저 알림 설정 가져오기
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public String selectMemberWebTell(String memberId) throws Exception {
		Connection conn = getConnection();
		return new AlertDAO().selectMemberWebTell(conn, memberId);
	}

}
