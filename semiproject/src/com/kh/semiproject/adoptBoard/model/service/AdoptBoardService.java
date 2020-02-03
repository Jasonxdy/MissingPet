package com.kh.semiproject.adoptBoard.model.service;

import static com.kh.semiproject.common.JDBCTemplate.close;
import static com.kh.semiproject.common.JDBCTemplate.commit;
import static com.kh.semiproject.common.JDBCTemplate.getConnection;
import static com.kh.semiproject.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semiproject.adoptBoard.model.dao.AdoptBoardDao;
import com.kh.semiproject.adoptBoard.model.vo.AdoptBoard;
import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.map.model.DAO.MapDAO;
import com.kh.semiproject.map.model.vo.Map;

public class AdoptBoardService {

	/** 분양합니다 목록 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return adoptList
	 * @throws Exception
	 */
	public List<AdoptBoard> selectAdoptList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<AdoptBoard> adoptList = new AdoptBoardDao().selectFindList(conn, currentPage, limit, boardType);
		
		close(conn);
		return adoptList;
	}

	/** 분양합니다 게시판 게시글 등록용 Service
	 * @param board
	 * @param adoptBoard
	 * @param animal
	 * @param adoptList
	 * @return result
	 * @throws Exception
	 */
	public static int insertAdoptBoard(BoardHJ board, AdoptBoard adoptBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
		
		AdoptBoardDao adoptBoardDao = new AdoptBoardDao();
		BoardDao boardDao = new BoardDao();
		
		int result = 0;
		
		int boardNo = boardDao.selectNextBoardNo(conn);
		int animalNo = boardDao.selectNextAnimalNo(conn);
		
		if(boardNo > 0 || animalNo > 0) {
			board.setBoardNo(boardNo);
			animal.setAnimalCode(animalNo);
			adoptBoard.setBoardNo(boardNo);
			adoptBoard.setAnimalCode(animalNo);
			map.setBoardNo(boardNo);
			
			result = boardDao.insertBoard(conn, board);
			if(result > 0) {
				result = 0;
				
				result = boardDao.insertAnimal(conn, animal);
				if(result > 0) {
					result = 0;
					
					result = adoptBoardDao.insertAdoptBoard(conn, adoptBoard);
					if(result > 0) {
						result = 0;
						
						result = new MapDAO().insertMap(conn, map);
						if(result > 0) {
							if(!fList.isEmpty()) {
								result = 0;
								
								for(Attachment file : fList) {
									file.setBoardNo(boardNo);
									
									result = boardDao.insertAttachment(conn, file);
									if(result ==0) {
										break;
									}
								}
							}
							if(result >0) {
								commit(conn);
							} else {
								for(Attachment file : fList) {
									String path = file.getFilePath();
									String saveFile = file.getFileChangeName();
									
									File failedFile = new File(path + saveFile);
									
									failedFile.delete();
								}
								rollback(conn);
							}
							close(conn);
						}
					}
				} 
			}
		}
		
		return result;
	}

	/** 분양합니다 게시글  조회용 Service
	 * @param boardNo
	 * @return adoptBoard
	 * @throws Exception
	 */
	public AdoptBoard selectAdoptBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		AdoptBoard adoptBoard = new AdoptBoardDao().selectAdoptBoard(conn, boardNo);
		
		close(conn);
		return adoptBoard;
	}
	
	/** 분양합니다 게시판 수정용 Service
	 * @param board
	 * @param adoptBoard
	 * @param animal
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public static int updateAdoptBoard(BoardHJ board, AdoptBoard adoptBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
		
		AdoptBoardDao adoptBoardDao = new AdoptBoardDao();
		BoardDao boardDao = new BoardDao();
		
		board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>"));
		
		int result = boardDao.updateBoard(conn, board);
		if(result>0) {
			result = 0;
			int boardNo = board.getBoardNo();
			
			adoptBoard.setBoardNo(boardNo);
			result = adoptBoardDao.updateAdoptBoard(conn, adoptBoard);
			if(result>0) {
				result = adoptBoardDao.updateAdoptAnimal(conn, animal, boardNo);
				if(result>0) {
					// 지도 업데이트
					 result=0;
					map.setBoardNo(boardNo);
					result = new MapDAO().updateMap(conn, map);
					if(result>0) {
						if(!fList.isEmpty()) {
							result = 0;
							
							for(Attachment file : fList) {
								file.setBoardNo(boardNo);
								
								if(file.getFileLevel()==0) {
									
									result = boardDao.deleteThumbnail(conn, boardNo);
									
									if(result==0) {
										break;
									}
								}
								
								result = boardDao.insertAttachment(conn, file);
								
								if(result ==0) {
									break;
								}
							}
							int count = boardDao.countAttachment(conn, boardNo);
							
							if(count>4) {
								int over = count-4;
								result = boardDao.deleteImg(conn, boardNo, over);
							}
						}
						
						if(result >0) {
							commit(conn);
						} else {
							for(Attachment file : fList) {
								String path = file.getFilePath();
								String saveFile = file.getFileChangeName();
								
								File failedFile = new File(path + saveFile);
								
								failedFile.delete();
							}
							rollback(conn);
						}
						close(conn);
					}
				}
			}
		}
		return result;
	}

	/** 분양합니다 게시글 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteAdoptBoard(int no) throws Exception {
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, no);
		
		if(result>0) {
			result = 0;
			
			result = new BoardDao().deleteAttachment(conn, no);
			if(result>0) {
				result = 0;
				
				result = new AdoptBoardDao().adoptDeleteAnimal(conn, no);
				if(result>0) {
					result = 0;
					
					result = new BoardDao().deleteComment(conn, no);
					
					if(result > 0) {
						commit(conn);
					}
				} 
			}
		} else {
			rollback(conn);
		}
			
		close(conn);
		return result;
	}

	public List<AdoptBoard> searchAdoptList(int startRow, int endRow, int boardType, String condition) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<AdoptBoard> adoptList = new AdoptBoardDao().searchFindList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		return adoptList;
	}

	/** 분양합니다 게시판 BoardList 검색용 Service
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @param doneCheck2
	 * @return
	 * @throws Exception
	 */
	public List<BoardHJ> searchAdoptBList(int startRow, int endRow, int boardType, String condition, String doneCheck1, String doneCheck2) throws Exception {
		Connection conn = getConnection();
		
		String condition2 = null;
		
		if(doneCheck1.equals("Y") && doneCheck2.equals("Y")) {
			condition2 = " ";
		} else if(doneCheck1.equals("Y") && doneCheck2.equals("N")) {
			condition2 = " AND ADOPT_DONE='N'";
		} else if(doneCheck1.equals("N") && doneCheck2.equals("Y")) {
			condition2= " AND ADOPT_DONE='Y'";
		} else if(doneCheck1.equals("N") && doneCheck2.equals("N")) {
			condition2= " AND ADOPT_DONE='C'";
		}
		
		List<BoardHJ> bList = new AdoptBoardDao().searchAdoptBList(conn, startRow, endRow, boardType, condition, condition2);
		
		close(conn);
		
		return bList;
	}

	/** 분양합니다 검색 게시글 수 조회 Service
	 * @param condition
	 * @param boardType
	 * @param doneCheck1
	 * @param doneCheck2
	 * @return
	 * @throws Exception
	 */
	public int getAdoptSearchListCount(String condition, int boardType, String doneCheck1, String doneCheck2) throws Exception {
		Connection conn = getConnection();
		
				
		String condition2 = null;
		
		if(doneCheck1.equals("Y") && doneCheck2.equals("Y")) {
			condition2 = " ";
		} else if(doneCheck1.equals("Y") && doneCheck2.equals("N")) {
			condition2 = " AND ADOPT_DONE='N'";
		} else if(doneCheck1.equals("N") && doneCheck2.equals("Y")) {
			condition2= " AND ADOPT_DONE='Y'";
		} else if(doneCheck1.equals("N") && doneCheck2.equals("N")) {
			condition2= " AND ADOPT_DONE='C'";
		}

		int searchAdoptListCount = new AdoptBoardDao().getSearchAdoptListCount(conn, condition, boardType, condition2);
		
		close(conn);
		
		return searchAdoptListCount;
	}

}
