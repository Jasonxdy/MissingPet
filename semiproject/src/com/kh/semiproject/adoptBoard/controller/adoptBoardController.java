package com.kh.semiproject.adoptBoard.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.semiproject.adoptBoard.model.service.AdoptBoardService;
import com.kh.semiproject.adoptBoard.model.vo.AdoptBoard;
import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.MyFileRenamePolicy;
import com.kh.semiproject.findBoard.model.service.FindBoardService;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.map.model.service.MapService;
import com.kh.semiproject.map.model.vo.Map;
import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.seeBoard.model.service.SeeBoardService;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/adoptBoard/*")
public class adoptBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adoptBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/adoptBoard").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		BoardService boardService = new BoardService();
		AdoptBoardService adoptBoardService = new AdoptBoardService();
		MemberService memberService = new MemberService();
		
		if(command.equals("/boardList")) {
			try {
				int boardType = 3;
				int listCount = boardService.getListCount(boardType);
				
				int limit = 8;
				int pagingBarSize = 10;
				
				int currentPage = 0;	
				int maxPage = 0;		
				int startPage = 0;		
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize +1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInfo = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<BoardHJ> bList = boardService.selectFList(currentPage, limit, boardType);
				
				List<Attachment> aList = boardService.selectAList(currentPage, limit, boardType);
				
				//List<Animal> animalList = boardService.selectAnimalList(currentPage, limit, boardType);
				
				List<AdoptBoard> adoptList = adoptBoardService.selectAdoptList(currentPage, limit, boardType);
				
				path = "/WEB-INF/views/adoptBoard/adoptBoardList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				//request.setAttribute("animalList", animalList);
				request.setAttribute("adoptList", adoptList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "분양합니다 게시판 조회", e);
			}
		}
		
		else if(command.equals("/insertForm")) {
			path = "/WEB-INF/views/adoptBoard/adoptBoardInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		else if(command.equals("/insert")) {
			
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10 * 1024 * 1024;
					
					String root = request.getSession().getServletContext().getRealPath("/");
				
					String savePath =  root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
					ArrayList<String> saveFiles = new ArrayList<String>();
					
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					while(files.hasMoreElements()) {
						
						// 업로드된 파일은 역순으로 전달됨
						String name = files.nextElement();
						
						if(multiRequest.getFilesystemName(name) != null) {
							// getFilesystemName(key) : rename된 파일명 얻어오기
							saveFiles.add(multiRequest.getFilesystemName(name));
							
							originFiles.add(multiRequest.getOriginalFileName(name));
						}
					}
					
					Member loginMember = (Member)request.getSession().getAttribute("loginMember");
					
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardURL = multiRequest.getParameter("videoURL");
					String boardWriter = loginMember.getMemberId();
					int boardCode = 3;
					
					BoardHJ board = new BoardHJ(boardTitle, boardContent, boardURL, boardWriter, boardCode);
					
					String aBoardLocation = multiRequest.getParameter("place1")
											+ "," + multiRequest.getParameter("place2")
											+ "," + multiRequest.getParameter("place3");
					
					int aBoardCost = Integer.parseInt(multiRequest.getParameter("cost"));
					
					String aBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					//String aBoardDate = multiRequest.getParameter("missingDate");
					
					//String temp[] = aBoardDate.split("-");
					
					//Date date = new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]));
					
					String aBoardMap = multiRequest.getParameter("spot");
					
					/*
					 * String fBoardLocationTell = multiRequest.getParameter("locationTell"); String
					 * fBoardBreedTell = multiRequest.getParameter("breedTell"); String
					 * fBoardCommentTell = multiRequest.getParameter("commentTell");
					 */
					String aBoardNeutral = multiRequest.getParameter("neutral");
					String aBoardVac = multiRequest.getParameter("vac");
					String aBoardHealth = multiRequest.getParameter("health");
 					
					AdoptBoard adoptBoard = new AdoptBoard(aBoardLocation, aBoardCost, aBoardPhone, aBoardMap, aBoardNeutral, aBoardVac, aBoardHealth);
					
					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					for(int i = originFiles.size()-1 ; i >=0 ; i--) {
						Attachment file = new Attachment();
						file.setFilePath(savePath);
						file.setFileOriginName(originFiles.get(i));
						file.setFileChangeName(saveFiles.get(i));
						
						// 썸네일 이미지는 fileLevel 0으로 나머지 이미지에는 fileLevel 1 부여
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setFileLevel(0);
						} else {
							file.setFileLevel(1);
						}
						
						fList.add(file);
					}
					
					int result = AdoptBoardService.insertAdoptBoard(board,adoptBoard,animal,fList);
					
					if(result>0) {
						msg = "게시글 등록 성공";
						
					}
					else		 msg = "게시글 등록 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("boardList");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		else if(command.contentEquals("/detail")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				BoardHJ board = boardService.selectBoard(boardNo);
				
				if(board != null) {
					List<Attachment> files = boardService.selectFiles(boardNo);
					
					AdoptBoard adoptBoard = adoptBoardService.selectAdoptBoard(boardNo);
					
					int animalCode = adoptBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					//Map map = new MapService().selectMap(boardNo);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					
					request.setAttribute("adoptBoard", adoptBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					//request.setAttribute("map", map);
					
					path = "/WEB-INF/views/adoptBoard/adoptBoardDetail.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "게시글 상세 조회 실패");
					response.sendRedirect("list");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		else if(command.equals("/updateForm")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				BoardHJ board = boardService.updateForm(no);
				
				if(board != null) {
					List<Attachment> files = boardService.selectFiles(no);
					
					AdoptBoard adoptBoard = adoptBoardService.selectAdoptBoard(no);
					
					int animalCode = adoptBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					Map map = new MapService().selectMap(no);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					request.setAttribute("adoptBoard", adoptBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					request.setAttribute("map", map);
					
					path = "/WEB-INF/views/adoptBoard/adoptBoardUpdate.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				} else {
					request.getSession().setAttribute("msg", "게시판 수정 화면 출력");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 등록", e);
			}
		}
		
		else if(command.contentEquals("/update")) {
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10 * 1024 * 1024;
					
					String root = request.getSession().getServletContext().getRealPath("/");
				
					String savePath =  root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
				
					ArrayList<String> saveFiles = new ArrayList<String>();
					
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					while(files.hasMoreElements()) {
						
						// 업로드된 파일은 역순으로 전달됨
						String name = files.nextElement();
						
						if(multiRequest.getFilesystemName(name) != null) {
							// getFilesystemName(key) : rename된 파일명 얻어오기
							saveFiles.add(multiRequest.getFilesystemName(name));
							
							originFiles.add(multiRequest.getOriginalFileName(name));
						}
					}
					
					int boardNo = Integer.parseInt(request.getParameter("no"));
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardURL = multiRequest.getParameter("videoURL");
					
					BoardHJ board = new BoardHJ(boardNo, boardTitle, boardContent, boardURL);
					
					String aBoardLocation = multiRequest.getParameter("place1")
							+ "," + multiRequest.getParameter("place2")
							+ "," + multiRequest.getParameter("place3");
					
					int aBoardCost = Integer.parseInt(multiRequest.getParameter("cost"));
					
					String aBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					
					String aBoardMap = multiRequest.getParameter("spot");
					
					String aBoardNeutral = multiRequest.getParameter("neutral");
					String aBoardVac = multiRequest.getParameter("vac");
					String aBoardHealth = multiRequest.getParameter("health");
					String aBoardDone = multiRequest.getParameter("done");
 					
					AdoptBoard adoptBoard = new AdoptBoard(aBoardLocation, aBoardCost, aBoardPhone, aBoardMap, aBoardNeutral, aBoardVac, aBoardHealth, aBoardDone);
					
					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					/*
					 * // map 정보 가저오기 Double mapLatitude =
					 * Double.parseDouble(multiRequest.getParameter("latitude")); Double
					 * mapLongitude = Double.parseDouble(multiRequest.getParameter("longitude"));
					 * String mapAddress = multiRequest.getParameter("mapAddress"); Map map = new
					 * Map(mapLatitude, mapLongitude, mapAddress);
					 */
					
					
					for(int i = originFiles.size()-1 ; i >=0 ; i--) {
						Attachment file = new Attachment();
						file.setFilePath(savePath);
						file.setFileOriginName(originFiles.get(i));
						file.setFileChangeName(saveFiles.get(i));
						
						// 썸네일 이미지는 fileLevel 0으로 나머지 이미지에는 fileLevel 1 부여
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setFileLevel(0);
						} else {
							file.setFileLevel(1);
						}
						
						fList.add(file);
					}
					
					int result = AdoptBoardService.updateAdoptBoard(board,adoptBoard,animal,fList);
					
					if(result>0) {
						msg = "게시글 수정 성공";
					}
					else		 msg = "게시글 수정 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("boardList");
				}
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 수정", e);
			}
		}
		
		else if(command.equals("/delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			try {
				int result = adoptBoardService.deleteAdoptBoard(no);
				
				if(result>0) {
					msg = "게시글이 삭제되었습니다.";
					path = "boardList";
				}
				else {
					msg = "게시글 삭제 실패";
					path = "detail?no="+no;
				}
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(path);
				
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시글 수정", e);
			}
		}
		
		else if(command.contentEquals("/searchList")) {
			String searchKey = request.getParameter("searchKey");
			String searchValue = request.getParameter("searchValue");
			String doneCheck1 = request.getParameter("doneCheck1");
			String doneCheck2 = request.getParameter("doneCheck2");
			
			System.out.println(doneCheck1);
			System.out.println(doneCheck2);

			String condition = null;
			
			searchValue = "'%' || '" + searchValue + "' || '%' ";
			
			switch(searchKey) {
			case "title": condition =  " BOARD_TITLE LIKE " + searchValue; break;
			case "content": condition =  " BOARD_CONTENT LIKE " + searchValue; break;
			case "titcont": condition =  " (BOARD_CONTENT LIKE" + searchValue + " OR BOARD_TITLE LIKE " + searchValue +")"; break;
			case "writer" : condition = " MEM_NAME LIKE " + searchValue; break;
			}
			try {
				int boardType = 3;
				int listCount = adoptBoardService.getAdoptSearchListCount(condition, boardType, doneCheck1, doneCheck2);
				
				int limit = 1;
				int pagingBarSize = 10;
				
				int currentPage = 0;	
				int maxPage = 0;		
				int startPage = 0;		
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize +1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInfo = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<BoardHJ> bList = adoptBoardService.searchAdoptBList(startRow, endRow, boardType, condition, doneCheck1, doneCheck2);
				
				List<Attachment> aList = boardService.searchAList(startRow, endRow, boardType, condition);
				
				List<Animal> animalList = boardService.searchAnimalList(startRow, endRow, boardType, condition);
				
				List<AdoptBoard> adoptList = adoptBoardService.searchAdoptList(startRow, endRow, boardType, condition);
				
				System.out.println(pInfo);
				System.out.println(bList);
				System.out.println(aList);
				
				path = "/WEB-INF/views/adoptBoard/adoptBoardSearchList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				request.setAttribute("animalList", animalList);
				request.setAttribute("adoptList", adoptList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 검색", e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
