package com.kh.semiproject.review.model.service;


import static com.kh.semiproject.common.JDBCTemplate.close;
import static com.kh.semiproject.common.JDBCTemplate.commit;
import static com.kh.semiproject.common.JDBCTemplate.getConnection;
import static com.kh.semiproject.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semiproject.review.model.dao.ReviewDAO;
import com.kh.semiproject.review.model.vo.Comment;
import com.kh.semiproject.review.model.vo.Img;
import com.kh.semiproject.review.model.vo.Report;
import com.kh.semiproject.review.model.vo.Review;

public class ReviewService {

	public int getListCount() throws Exception {
		Connection conn = getConnection();
		
		int listCount = new ReviewDAO().getListCount(conn);
		close(conn);
		return listCount;
	}

	public List<Review> selectList(int currentPage, int limit) throws Exception {
		Connection conn = getConnection();
		
		List<Review> rList = new ReviewDAO().selectList(conn, currentPage, limit);
		
		close(conn);
		return rList;
	}

	public int insertReview(Review review, ArrayList<Img> iList) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		int result = 0;
		int boardNo = reviewDAO.selectNextNo(conn);
		
		if(boardNo>0) {
			review.setBoardNo(boardNo);
			result = reviewDAO.insertReview(conn, review);
			if(result>0) {
				if(!iList.isEmpty()) {
					result = 0;
					for(Img img : iList) {
						img.setBoardNo(boardNo);
						
						result = reviewDAO.insertImg(conn, img);
						
						if(result == 0) break;
					}
				}
			}
		}
		
		if(result>0)commit(conn);
		else {
			for(Img img : iList) {
				String path = img.getImgPath();
				String saveFile = img.getImgChangeName();
				
				File failedFile = new File(path + saveFile);
				
				failedFile.delete();
			}
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public List<Img> selectImgList(int currentPage, int limit) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<Img> iList = new ReviewDAO().selectImgList(conn, currentPage, limit);
		
		close(conn);
		
		return iList;
	}

	public Review selectReview(int boardNo) throws Exception {
		Connection conn = getConnection();
		ReviewDAO rDAO = new ReviewDAO();
		Review review = rDAO.selectReview(conn, boardNo);
		
		close(conn);
		return review;
	}

	public List<Img> selectImgs(int boardNo) throws Exception {
		Connection conn = getConnection();
		List<Img> imgs = new ReviewDAO().selectImgs(conn, boardNo);
		close(conn);
		return imgs;
	}

	public int reviewDelete(int boardNo) throws Exception {
		Connection conn = getConnection();
		int result = new ReviewDAO().reviewDelete(conn, boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateReview(int boardNo, Review review, ArrayList<Img> iList) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		List<Img> oldList = new ArrayList<Img>();
		int result = 0;
		
		oldList = reviewDAO.selectImgs(conn, boardNo);
		
		review.setBoardNo(boardNo);
		result = reviewDAO.updateReview(conn, review);
		if(result>0) {
			if(!iList.isEmpty()) {
				result = 0;
				for(Img img : iList) {
					img.setBoardNo(boardNo);
					
					result = reviewDAO.insertImg(conn, img);
					
					if(result == 0) break;
					// 업데이트 할 이미지가 있고 그 이미지의 레벨과 같은 오래된 이미지가 있으면
					// 오래된 이미지의 상태값 변경
					for(Img oldImg : oldList) {
						if(oldImg.getImgLevel() == img.getImgLevel()) {
							reviewDAO.deleteImg(conn, oldImg);
						}
					}
				}
			}
		}
		
		if(result>0)commit(conn);
		else {
			for(Img img : iList) {
				String path = img.getImgPath();
				String saveFile = img.getImgChangeName();
				
				File failedFile = new File(path + saveFile);
				
				failedFile.delete();
			}
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

	public void increaseCount(int boardNo) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		int result = reviewDAO.increaseCount(conn, boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
	}

	public int insertComment(Comment comment, String commentWriter) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		int result = reviewDAO.insertComment(conn, comment, commentWriter);  
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Comment> selectCommentList(int boardNo) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		
		List<Comment> cList = reviewDAO.selectCommentList(conn, boardNo);
		
		close(conn);
		return cList;
	}

	public int updateComment(Comment comment) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		int result = reviewDAO.updateComment(conn, comment);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteComment(int commentNo) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		int result = reviewDAO.deleteComment(conn, commentNo);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int insertReport(Report report) throws Exception {
		Connection conn = getConnection();
		ReviewDAO reviewDAO = new ReviewDAO();
		int result = reviewDAO.insertReport(conn, report);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	/** Review 검색 리스트 Service
	 * @param startRow
	 * @param endRow
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Review> searchReviewList(int startRow, int endRow, String condition) throws Exception {
		Connection conn = getConnection();
		
		List<Review> rList = new ReviewDAO().searchReviewList(conn, startRow, endRow, condition);
		
		close(conn);
		
		return rList;
	}

	/** Img 검색 리스트 Service
	 * @param startRow
	 * @param endRow
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Img> searchRImgList(int startRow, int endRow, String condition) throws Exception {
		Connection conn = getConnection();
		
		List<Img> iList = new ReviewDAO().searchRImgList(conn, startRow, endRow, condition);
		
		close(conn);
		return iList;
	}
}









