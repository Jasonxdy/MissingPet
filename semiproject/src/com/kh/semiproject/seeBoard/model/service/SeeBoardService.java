package com.kh.semiproject.seeBoard.model.service;

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
import com.kh.semiproject.map.model.DAO.MapDAO;
import com.kh.semiproject.map.model.vo.Map;
import com.kh.semiproject.seeBoard.model.dao.SeeBoardDao;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;

public class SeeBoardService {

	/** 봤어요 게시판 목록 조회용 Service
	 * @param currentPage
	 * @param limit
	 * @param boardType
	 * @return result
	 * @throws Exception
	 */
	public List<SeeBoard> selectSeeList(int currentPage, int limit, int boardType) throws Exception {
		Connection conn = getConnection();
		//
		ArrayList<SeeBoard> sList = new SeeBoardDao().selectSeeList(conn, currentPage, limit, boardType);
		
		close(conn);
		return sList;
	}

	/** 봤어요 게시판 게시글 등록용 Service
	 * @param board
	 * @param seeBoard
	 * @param animal
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public static int insertSeeBoard(BoardHJ board, SeeBoard seeBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
		
		SeeBoardDao seeBoardDao = new SeeBoardDao();
		BoardDao boardDao = new BoardDao();
		
		int result = 0;
		
		int boardNo = boardDao.selectNextBoardNo(conn);
		int animalNo = boardDao.selectNextAnimalNo(conn);
		
		if(boardNo > 0 || animalNo > 0) {
			board.setBoardNo(boardNo);
			animal.setAnimalCode(animalNo);
			seeBoard.setBoardNo(boardNo);
			seeBoard.setAnimalCode(animalNo);
			map.setBoardNo(boardNo);
			
			result = boardDao.insertBoard(conn, board);
			if(result > 0) {
				result = 0;
				
				result = boardDao.insertAnimal(conn, animal);
				if(result > 0) {
					result = 0;
					
					result = seeBoardDao.insertSeeBoard(conn, seeBoard);
					if(result > 0) {
						result = 0;
						
						result = new MapDAO().insertMap(conn, map);
						if(result>0) {
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

	/** 봤어요 게시글 조회용 Service
	 * @param boardNo
	 * @return seeBoard
	 * @throws Exception
	 */
	public SeeBoard selectSeeBoard(int boardNo) throws Exception {
		Connection conn = getConnection();
		
		SeeBoard seeBoard = new SeeBoardDao().selectSeeBoard(conn, boardNo);
		
		close(conn);
		return seeBoard;
	}

	/** 봤어요 게시판 수정용  Service
	 * @param board
	 * @param seeBoard
	 * @param animal
	 * @param fList
	 * @return result
	 * @throws Exception
	 */
	public static int updateSeeBoard(BoardHJ board, SeeBoard seeBoard, Animal animal, ArrayList<Attachment> fList, Map map) throws Exception {
		Connection conn = getConnection();
				
		SeeBoardDao seeBoardDao = new SeeBoardDao();
		BoardDao boardDao = new BoardDao();
		
		board.setBoardContent(board.getBoardContent().replace("\r\n", "<br>"));
		
		int result = boardDao.updateBoard(conn, board);
		if(result>0) {
			result = 0;
			int boardNo = board.getBoardNo();
			
			seeBoard.setBoardNo(boardNo);
			result = seeBoardDao.updateSeeBoard(conn, seeBoard);
			if(result>0) {
				result = seeBoardDao.updateSeeAnimal(conn, animal, boardNo);
				if(result>0) {
					// 지도 업데이트
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

	/** 봤어요 게시글 삭제용 Service
	 * @param no
	 * @return result
	 * @throws Exception
	 */
	public int deleteSeeBoard(int no) throws Exception {
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, no);
		
		if(result>0) {
			result = 0;
			
			result = new BoardDao().deleteAttachment(conn, no);
			if(result>0) {
				result = 0;
				
				result = new SeeBoardDao().seeDeleteAnimal(conn, no);
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

	/** 봤어요 게시글 검색용 Service
	 * @param startRow
	 * @param endRow
	 * @param boardType
	 * @param condition
	 * @return seeList
	 * @throws Exception
	 */
	public List<SeeBoard> searchSeeList(int startRow, int endRow, int boardType, String condition) throws Exception {
		Connection conn = getConnection();
		
		ArrayList<SeeBoard> sList = new SeeBoardDao().searchSeeList(conn, startRow, endRow, boardType, condition);
		
		close(conn);
		return sList;
	}

	/** 봤어요 게시판 알림 발송용 Service
	 * @param boardTitle
	 * @param getsBoardLocation
	 * @param animalBreed
	 * @return result
	 * @throws Exception
	 */
	public int sendSeeAlram(String boardTitle, String getsBoardLocation, String animalBreed) throws Exception {
		Connection conn = getConnection();
		
		List<Alram> alramList = new SeeBoardDao().selectSeeAlram(conn);
		
		for(Alram alram : alramList) {
			String alramLocation[] = alram.getAlramLocation().split(",");
			String boardLocation[] = getsBoardLocation.split(",");
			
			if(alram.getAlramLocationTell().equals("Y") && alramLocation[1].equals(boardLocation[1])) {
				new BoardService().sendMail(boardTitle, alram.getAlramEmail());
			} else if(alram.getAlramBreedTell().equals("Y") && alram.getAlramBreed().equals(animalBreed)) {
				new BoardService().sendMail(boardTitle, alram.getAlramEmail());
			}
		}
				
		return 0;
	}

}
