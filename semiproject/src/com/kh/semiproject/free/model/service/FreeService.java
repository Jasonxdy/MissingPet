package com.kh.semiproject.free.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semiproject.board.model.vo.BoardEH;
import com.kh.semiproject.board.model.vo.Img;
import com.kh.semiproject.board.model.vo.M_Comment;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.free.model.dao.FreeDao;
import com.kh.semiproject.free.model.vo.Free;
import com.kh.semiproject.review.model.vo.Comment;

public class FreeService {
	
	
	public static int getListCount() throws Exception{
		
		Connection conn = getConnection();
		
		int listCount = new FreeDao().getListCount(conn);
		
		close(conn);
		return listCount;
	}
	

	public  List<BoardEH> selectList(int currentPage, int limit) throws Exception{
		
		Connection conn = getConnection();
		
		List<BoardEH> blist = new FreeDao().selectList(conn,currentPage,limit);
		
		close(conn);
		
		return blist;
	}
	
	

	

	
	/** 상세조회용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public static BoardEH selectFree(int no) throws Exception {

		Connection conn = getConnection();
		
		FreeDao freeDao = new FreeDao();
		BoardEH board = freeDao.selectFree(conn,no);		
		
		if(board != null) {
			int result = freeDao.increaseCount(conn,no);
			
			if(result>0) {
				commit(conn); 
				// 반환되는 notice는 조회수 증가가 되어있지 않으므로
				// 리턴시 조회수를 +1 증가시켜줌
				board.setBoardCount(board.getBoardCount()+1);
			}else{
				rollback(conn);
				board = null; // 조회수 증가 실패 시 조회되지 않게 막음
				}
		}
		
		close(conn);
		
		return board;
	}

	public static List<BoardEH> searchFree(String searchKey, String searchValue) throws Exception{
		
		Connection conn = getConnection();

		String condition = null;

		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		
		switch(searchKey) {
		
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "titcont" : condition = " (BOARD_TITLE LIKE " + searchValue 
									+ " OR BOARD_CONTENT LIKE" + searchValue + ") "; break;
		}
		
		
		List<BoardEH> blist = new FreeDao().searchFree(conn,condition);
		// serchkey, searchvalue 다 condition 안에있음
		
		System.out.println("검색 서비스  성공");

		close(conn);
		return blist;
	}
	
	

	public static PageInfo searchPinf(String searchKey, String searchValue) throws Exception{

		Connection conn = getConnection();

		
		String condition = null;

		searchValue = "'%' || '" + searchValue + "' || '%'";
		
		switch(searchKey) {
		
		case "title" : condition = " BOARD_TITLE LIKE " + searchValue; break;
		case "content" : condition = " BOARD_CONTENT LIKE " + searchValue; break;
		case "titcont" : condition = " (BOARD_TITLE LIKE " + searchValue 
									+ " OR BOARD_CONTENT LIKE" + searchValue + ") "; break;
		}
		
		PageInfo pInf = new FreeDao().searchPinf(conn, condition);
		
		System.out.println("검색 서비스  성공");
		
		close(conn);
		return pInf;
	}



	public List<Free> selectfList(int currentPage, int limit) throws Exception {

		Connection conn = getConnection();
		
		List<Free> flist = new FreeDao().selectfList(conn, currentPage, limit);
		
		close(conn);
		return flist;
	}


	public static Free selectFree2(int no) throws Exception{
		Connection conn = getConnection();
		Free free = new FreeDao().selectFree2(conn,no);

		close(conn);
		return free;
	}


	public int insertComm(Comment comm, String commentWriter)throws Exception {
		
		Connection conn = getConnection();
		
		int result = new FreeDao().insertComm(conn,comm,commentWriter);

		if(result>0) commit(conn);
		else		 rollback(conn);
		
		close(conn);
		return result;
	}


	public List<Comment> selectCommList(int boardNo)throws Exception{
		
		Connection conn = getConnection();

		List<Comment> comm = new FreeDao().selectCommList(conn,boardNo);
		
		close(conn);
		return comm;
	}


	public static int deleteFree(int no)throws Exception {
		
		Connection conn = getConnection();
		
		int result = new FreeDao().deleteFree(conn,no);
		
		if(result>0) commit(conn);
		else		rollback(conn);
		
		close(conn);
		
		return result;
	}


	

	/** 게시글 등록용 Service
	 * @param board
	 * @param boardWriter
	 * @param fList
	 * @param freeCategory 
	 * @return result
	 * @throws Exception
	 */
	public static int insertFree(BoardEH board, String memberId, ArrayList<Img> fList, Free free) throws Exception {
		
		
		Connection conn = getConnection();
		
		FreeDao freeDao = new FreeDao();
		
		
		int result = 0;
		
		
		int boardNo = freeDao.selectNextNo(conn);
		
		
		if(boardNo > 0) { // 게시글 번호를  얻어옴
			
			// 2) 게시글(Board)를 DB에 먼저 삽입
			board.setBoardNo(boardNo);
			free.setBoardNo(board.getBoardNo());
			
			result = freeDao.insertFree(conn, board, memberId);
			
			if(result > 0) { // 게시글 삽입 성공 시
				result = 0; // 트랜젝션 처리를 위해 재활용
				
				result = freeDao.insertFreeCategory(conn, free);
				if(result > 0) { // 게시글 삽입 성공 시
					result = 0; // 트랜젝션 처리를 위해 재활용
				
				// 3) fList의 데이터를 하나씩 DB에 삽입
				for(Img file : fList) {
					// 현재 게시글 번호 추가
					file.setBoardNo(boardNo);
					
					result = freeDao.insertImg(conn,file);
					
					// DB 데이터가 삽입되지 않았을 때
					if(result == 0) break;
						
				}
			}
			
		}
	
		}
		
		// 4) 트랜잭션 처리
				if(result > 0) commit(conn);
				else{
					
					// 5) DB 삽입 실패시 
					// 서버에 저장된 파일을 삭제
					for(Img file : fList) {
						String path = file.getImgPath();
						String saveFile = file.getImgChangeName();
						
						File failedFile = new File(path + saveFile);
						// -> 파일이라는 객체가 매개변수로 지정된 경로의 파일을 취급 할 수 있음
						
						failedFile.delete();
					}
					
					rollback(conn);
				}
				
				close(conn);
				return result;
			}


	
	/** 이미지 확인용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public static List<Img> selectImg(int no) throws Exception {
		
		Connection conn = getConnection();
		
		List<Img> img = new FreeDao().selectImg(conn,no);

		close(conn);

		return img;
	}

	
	/** 공지사항 수정 화면용 Service
	 * @param no
	 * @return notice
	 * @throws Exception
	 */
	public static BoardEH updateForm(int no) throws Exception {
		
		Connection conn = getConnection();

		
		BoardEH board  = new FreeDao().selectFree(conn,no);
		
		board.setBoardContent(board.getBoardContent().replace("<br>", "\r\n"));

		
		close(conn);
		
		return board;
	}


	public static int updateFree(BoardEH board, ArrayList<Img> fList, Free free, String[] beforeImg) throws Exception {



		Connection conn = getConnection();
		
		FreeDao freeDao = new FreeDao();
		
		board.setBoardContent(board.getBoardContent().replace("<br>", "\r\n"));

		
		int result = freeDao.updateBoard(conn,board);
		if(result>0) {
			result = 0;
		int boardNo = board.getBoardNo();
			
			
		free.setBoardNo(boardNo);
		result = freeDao.updateFree(conn, free);
		
		if(result>0 && fList != null) {
			
			for(String beforePath : beforeImg) {
	               if(!beforePath.equals("")) 
	                  System.out.println("삭제 결과 : " + freeDao.deleteBeforeImage(conn, beforePath) );
	               
	               for(Img img : fList) {
						String path = img.getImgPath();
						//String saveFile = img.getImgChangeName();
						
						File failedFile = new File(path + beforePath);
						
						failedFile.delete();
					}
			}
			
			result=0;
			
			for(Img img : fList) {
				img.setBoardNo(boardNo);
				
				result = freeDao.insertImg(conn, img);
				if(result ==0) {
					break;
				}
			}
		}
			
			int count = freeDao.countImg(conn, boardNo);
			if(count>4) {
				int over = count-4;
				result = freeDao.deleteImg(conn, boardNo, over);
			}
			}
			
			if(result >0) {
				commit(conn);
			} else {
				for(Img img : fList) {
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


	public int updateComment(Comment comment) throws Exception {

		Connection conn = getConnection();
		FreeDao freeDao = new FreeDao();
		int result = freeDao.updateComment(conn,comment);
		
		if(result>0) commit(conn);
		else        rollback(conn);
		
		close(conn);
		return result;
	}


	public int deleteComment(int commentNo) throws Exception {

		Connection conn = getConnection();
		FreeDao freeDao = new FreeDao();
		
		int result = freeDao.deleteComment(conn,commentNo);
		
		if(result>0) commit(conn);
		else        rollback(conn);
		
		close(conn);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	

}
