package com.kh.semiproject.common.alert.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.common.alert.model.vo.Alert;
import com.kh.semiproject.mypage.model.dao.MypageDAO;

import static com.kh.semiproject.common.JDBCTemplate.*;

public class AlertDAO {
	
private Properties prop = null;
	
	public AlertDAO() throws Exception {
		String fileName = AlertDAO.class.getResource("/com/kh/semiproject/sql/alert/alert-query.properties").getPath();
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	
	
	/**
	 * 알림 정보 조회용 DAO
	 * @param conn
	 * @param memberId
	 * @return alertList
	 * @throws Exception
	 */
	public List<Alert> selectAlert(Connection conn, String memberId) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAlert");
		Alert alert = null;
		List<Alert> alertList = new ArrayList<Alert>();
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				alert = new Alert(rset.getInt("ALERT_NO"),
						memberId,
						rset.getString("ALERT_STATUS"),
						rset.getString("ALERT_CONTENT"),
						rset.getString("ALERT_URL"),
						rset.getString("ALERT_TYPE"));
				
				alertList.add(alert);
				
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		return alertList;
	}



	
	/**
	 * 웹페이지 알림설정 확인용
	 * @param conn
	 * @param memberId
	 * @return memberWebTell
	 * @throws Exception
	 */
	public String []selectMemberWebTell(Connection conn, String memberId) throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberWebTell");
		
		String memberWebTell[] = new String[3];
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memberWebTell[0] = rset.getString("MEM_WEB_TELL");
				memberWebTell[1] = rset.getString("MEM_COMMENT_TELL");
				memberWebTell[2] = rset.getString("MEM_ASK_TELL");
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return memberWebTell;
	}

}
