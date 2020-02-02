package com.kh.semiproject.findBoard.model.service;

import static com.kh.semiproject.common.JDBCTemplate.close;
import static com.kh.semiproject.common.JDBCTemplate.commit;
import static com.kh.semiproject.common.JDBCTemplate.getConnection;
import static com.kh.semiproject.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semiproject.board.model.dao.BoardDao;
import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Alram;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.findBoard.model.dao.FindBoardDao;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.map.model.DAO.MapDAO;
import com.kh.semiproject.map.model.vo.Map;

public class FindBoardService {

	/** 찾아요 게시판 게시글 등록용 Service
	 * @param board
	 * @param findBoard
	 * @param animal
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public static int insertFindBoard(BoardHJ board, FindBoard findBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
		
		FindBoardDao findBoardDao = new FindBoardDao();
		BoardDao boardDao = new BoardDao();
		
		int result = 0;
		
		int boardNo = boardDao.selectNextBoardNo(conn);
		int animalNo = boardDao.selectNextAnimalNo(conn);
		
		if(boardNo > 0 || animalNo > 0) {
			board.setBoardNo(boardNo);
			animal.setAnimalCode(animalNo);
			findBoard.setBoardNo(boardNo);
			findBoard.setAnimalCode(animalNo);
			map.setBoardNo(boardNo);
			
			result = boardDao.insertBoard(conn, board);
			if(result > 0) {
				result = 0;
				
				result = boardDao.insertAnimal(conn, animal);
				if(result > 0) {
					result = 0;
					
					result = findBoardDao.insertFindBoard(conn, findBoard);
					
					// 맵 정보 insert
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

	/** 찾아주세요 목록 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return fList
	 * @throws Exception
	 */
	public List<FindBoard> selectFindList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<FindBoard> fList = new FindBoardDao().selectFindList(conn, currentPage, limit, boardType);
		
		close(conn);
		return fList;
	}

	/** 찾아주세요 게시글 조회용 Service
	 * @param boardNo
	 * @return findBoard
	 * @throws Exception
	 */
	public FindBoard selectFindBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
				
		FindBoard findBoard = new FindBoardDao().selectFindBoard(conn, boardNo);
		
		close(conn);
		
		return findBoard;
	}

	/** 찾아주세요 게시글 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteFindBoard(int no) throws Exception {
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, no);
		
		if(result>0) {
			result = 0;
			
			result = new BoardDao().deleteAttachment(conn, no);
			if(result>0) {
				result = 0;
				
				result = new FindBoardDao().deleteFindAnimal(conn, no);
				if(result>0) {
					commit(conn);
				}
			}
		} else {
			rollback(conn);
		}
			
		close(conn);
		return result;
	}

	/** 찾아주세요 게시판 수정용 Service
	 * @param board
	 * @param findBoard
	 * @param animal
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public static int updateFindBoard(BoardHJ board, FindBoard findBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
		
		FindBoardDao findBoardDao = new FindBoardDao();
		BoardDao boardDao = new BoardDao();
		
		board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>"));
		
		int result = boardDao.updateBoard(conn, board);
		if(result>0) {
			result = 0;
			int boardNo = board.getBoardNo();
			
			findBoard.setBoardNo(boardNo);
			result = findBoardDao.updateFindBoard(conn, findBoard);
			if(result>0) {
				result = findBoardDao.updateFindAnimal(conn, animal, boardNo);
				if(result>0) {
					// 지도 업데이트
					result=0;
					map.setBoardNo(boardNo);
					result = new MapDAO().updateMap(conn, map);
					if(result > 0) {
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

	/** 찾아주세요 게시글 검색용 Service
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @return findList
	 * @throws Exception
	 */
	public List<FindBoard> searchFindList(int startRow, int endRow, int boardType, String condition) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<FindBoard> fList = new FindBoardDao().searchFindList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		return fList;
	}

	/** 찾아주세요 게시판 알림 발송용 Service
	 * @param boardTitle
	 * @return result
	 * @throws Exception
	 */
	public int sendFindAlram(String boardTitle, String findBoardLocation, String animalBreed) throws Exception {
		Connection conn = getConnection();
		
		List<Alram> alramList = new FindBoardDao().selectFindAlram(conn);
		System.out.println(alramList);
		
		for(Alram alram : alramList) {
			String alramLocation[] = alram.getAlramLocation().split(",");
			String boardLocation[] = findBoardLocation.split(",");
			
			if(alram.getAlramLocationTell().equals("Y") && alramLocation[1].equals(boardLocation[1])) {
				new BoardService().sendMail(boardTitle, alram.getAlramEmail());
			} else if(alram.getAlramBreedTell().equals("Y") && alram.getAlramBreed().equals(animalBreed)) {
				new BoardService().sendMail(boardTitle, alram.getAlramEmail());
			}
		}
				
		return 0;
	}
	
}
