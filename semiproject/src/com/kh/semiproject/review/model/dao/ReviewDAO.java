package com.kh.semiproject.review.model.dao;

import static com.kh.semiproject.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.semiproject.review.model.vo.Comment;
import com.kh.semiproject.review.model.vo.Img;
import com.kh.semiproject.review.model.vo.Report;
import com.kh.semiproject.review.model.vo.Review;

public class ReviewDAO {
	private Properties prop = null;
	
	public ReviewDAO() throws Exception {
		
		String fileName = ReviewDAO.class.getResource("/com/kh/semiproject/sql/review/review-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}

	public int getListCount(Connection conn) throws Exception {
		int listCount = 0;
		
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public List<Review> selectList(Connection conn, int currentPage, int limit)
		throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Review> rList = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			int startRow = (currentPage - 1)*limit +1;
			int endRow = startRow + limit -1;
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Review>();
			Review review = null;
			
			while(rset.next()) {
				review = new Review(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getInt("BOARD_COUNT"),
						rset.getString("MEM_ID"),
						rset.getInt("RNUM"));
				rList.add(review);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}

	public int selectNextNo(Connection conn) throws Exception {
		Statement stmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		}finally {
			close(rset);
			close(stmt);
		}
		
		return boardNo;
	}

	public int insertReview(Connection conn, Review review) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, review.getBoardNo());
			pstmt.setString(2, review.getBoardTitle());
			pstmt.setString(3, review.getBoardContent());
			pstmt.setString(4, review.getBoardUrl());
			pstmt.setString(5, review.getMemberId());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertImg(Connection conn, Img img) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, img.getImgOriginName());
			pstmt.setString(2, img.getImgChangeName());
			pstmt.setString(3, img.getImgPath());
			pstmt.setInt(4, img.getImgLevel());
			pstmt.setInt(5, img.getBoardNo());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<Img> selectImgList(Connection conn, int currentPage, int limit) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Img> iList = null;
		String query = prop.getProperty("selectImgList");
		
		try {
			int startRow = (currentPage -1)* limit +1;
			int endRow = startRow + limit -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			iList = new ArrayList<Img>();
			Img img = null;
			
			while(rset.next()) {
				img = new Img();
				img.setImgNo(rset.getInt("IMG_NO"));
				img.setBoardNo(rset.getInt("BOARD_NO"));
				img.setImgChangeName(rset.getString("IMG_CHANGE_NAME"));
				
				iList.add(img);
			}
			
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return iList;
	}

	public Review selectReview(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Review review = null;
		String query = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				review = new Review(
						boardNo,
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getInt("BOARD_COUNT"),
						rset.getString("BOARD_URL"),
						rset.getString("MEM_ID"));
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return review;
	}

	public List<Img> selectImgs(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Img> imgs = null;
		String query = prop.getProperty("selectImgs");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			imgs = new ArrayList<Img>();
			Img img = null;
			while(rset.next()) {
				img = new Img(rset.getInt("IMG_NO"),
						rset.getString("IMG_ORIGIN_NAME"),
						rset.getString("IMG_CHANGE_NAME"),
						rset.getInt("IMG_LEVEL"));
				imgs.add(img);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return imgs;
	}

	public int reviewDelete(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int result = 0;
		
		String query = prop.getProperty("reviewDelete");
		String query2 = prop.getProperty("commentDelete");
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
			if(result>0) {
				pstmt2 = conn.prepareStatement(query2);
				pstmt2.setInt(1, boardNo);
				result += pstmt2.executeUpdate();
			}
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReview(Connection conn, Review review) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getBoardTitle());
			pstmt.setString(2, review.getBoardContent());
			pstmt.setString(3, review.getBoardUrl());
			pstmt.setInt(4, review.getBoardNo());
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	

	public void deleteImg(Connection conn, Img oldImg) throws Exception {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, oldImg.getImgChangeName());
			
			pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		
	}

	public int increaseCount(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int insertComment(Connection conn, Comment comment, String commentWriter) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setString(2, commentWriter);
			pstmt.setInt(3, comment.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Comment> selectCommentList(Connection conn, int boardNo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Comment> cList = new ArrayList<Comment>();
		
		String query = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			Comment comment = null;
			while(rset.next()) {
				comment = new Comment(
						rset.getInt("COMM_NO"),
						rset.getString("COMM_CONTENT"),
						rset.getString("COMM_MODIFY_DT"),
						rset.getString("MEM_ID"),
						rset.getString("MEM_PRO_IMG"));
				cList.add(comment);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return cList;
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

	public int insertReport(Connection conn, Report report) throws Exception {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertReport");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, report.getReportTitle());
			pstmt.setString(2, report.getReportContent());
			pstmt.setString(3, report.getmemberId());
			pstmt.setInt(4, report.getBoardNo());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public List<Review> searchReviewList(Connection conn, int startRow, int endRow, String condition) throws Exception {
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		List<Review> rList = null;
		
		String query1 = prop.getProperty("searchReviewList1");
		String query2 = prop.getProperty("searchReviewList2");
		
		try {
			pstmt = conn.prepareStatement(query1+condition+query2);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Review>();
			Review review = null;
			
			while(rset.next()) {
				review = new Review(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getDate("BOARD_MODIFY_DT"),
						rset.getInt("BOARD_COUNT"),
						rset.getString("MEM_ID"),
						rset.getInt("RNUM"));
				rList.add(review);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return rList;
	}

	public List<Img> searchRImgList(Connection conn, int startRow, int endRow, String condition) throws Exception {
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		List<Img> iList = null;
		
		String query1 = prop.getProperty("searchRImgList1");
		String query2 = prop.getProperty("searchRImgList2");
		
		try {
			pstmt = conn.prepareStatement(query1+condition+query2);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			iList = new ArrayList<Img>();
			Img img = null;
			
			while(rset.next()) {
				img = new Img(
						rset.getInt("IMG_NO"), 
						rset.getString("IMG_CHANGE_NAME"),
						rset.getInt("BOARD_NO"));
				iList.add(img);
			}
		}finally {
			close(rset);
			close(pstmt);
		}
		return iList;
	}

	public int getSearchListCount(Connection conn, String condition) throws Exception {
		Statement stmt = null; 
		int result = 0;
		String query1 = prop.getProperty("getSearchListCount1");
		//String query2 = prop.getProperty("getSearchListCount2");
		
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query1+condition);
		}finally {
			close(stmt);
		}
		return result;
	}
}








