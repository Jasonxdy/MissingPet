package com.kh.semiproject.adoptBoard.model.service;

import static com.kh.semiproject.common.JDBCTemplate.*;

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
import com.kh.semiproject.findBoard.model.dao.FindBoardDao;
import com.kh.semiproject.findBoard.model.vo.FindBoard;

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
	public static int insertAdoptBoard(BoardHJ board, AdoptBoard adoptBoard, Animal animal, ArrayList<Attachment> fList) throws Exception {
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
			
			result = boardDao.insertBoard(conn, board);
			if(result > 0) {
				result = 0;
				
				result = boardDao.insertAnimal(conn, animal);
				if(result > 0) {
					result = 0;
					
					result = adoptBoardDao.insertAdoptBoard(conn, adoptBoard);
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
		
		return result;
	}

}
