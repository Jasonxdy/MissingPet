package com.kh.semiproject.seeBoard.controller;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.board.model.vo.PageInfo;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.MyFileRenamePolicy;
import com.kh.semiproject.common.alert.model.vo.Alert;
import com.kh.semiproject.findBoard.model.service.FindBoardService;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.map.model.service.MapService;
import com.kh.semiproject.map.model.vo.Map;
import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.review.model.service.ReviewService;
import com.kh.semiproject.review.model.vo.Comment;
import com.kh.semiproject.seeBoard.model.service.SeeBoardService;
import com.kh.semiproject.seeBoard.model.vo.SeeBoard;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/seeBoard/*")
public class seeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public seeBoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/seeBoard").length());
		//
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		BoardService boardService = new BoardService();
		SeeBoardService seeBoardService = new SeeBoardService();
		MemberService memberService = new MemberService();
		
		if(command.equals("/boardList")) {
			try {
				int boardType = 2;
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
				
				List<Animal> animalList = boardService.selectAnimalList(currentPage, limit, boardType);
				
				List<SeeBoard> sList = seeBoardService.selectSeeList(currentPage, limit, boardType);
				
				path = "/WEB-INF/views/seeBoard/seeBoardList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				request.setAttribute("animalList", animalList);
				request.setAttribute("sList", sList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "봤어요 게시판 조회", e);
			}
		}
		
		else if(command.equals("/insertForm")) {
			path = "/WEB-INF/views/seeBoard/seeBoardInsert.jsp";
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
					int boardCode = 2;
					
					BoardHJ board = new BoardHJ(boardTitle, boardContent, boardURL, boardWriter, boardCode);
					
					String sBoardLocation = multiRequest.getParameter("place1")
											+ "," + multiRequest.getParameter("place2")
											+ "," + multiRequest.getParameter("place3");
					String sBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					String sBoardDate = multiRequest.getParameter("missingDate");
					
					String temp[] = sBoardDate.split("-");
					
					Date date = new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]));
					
					String sBoardMap = multiRequest.getParameter("spot");
					
					String sBoardLocationTell = multiRequest.getParameter("locationTell");
					String sBoardBreedTell = multiRequest.getParameter("breedTell");
					String sBoardCommentTell = multiRequest.getParameter("commentTell");
					
					SeeBoard seeBoard = new SeeBoard(sBoardLocation, sBoardPhone, date, sBoardMap, sBoardLocationTell, sBoardBreedTell, sBoardCommentTell);

					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					// map 정보 가저오기
					Double mapLatitude = Double.parseDouble(multiRequest.getParameter("latitude"));
					Double mapLongitude = Double.parseDouble(multiRequest.getParameter("longitude"));
					String mapAddress = multiRequest.getParameter("mapAddress");
					Map map = new Map(mapLatitude, mapLongitude, mapAddress);
					
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
					
					int result = SeeBoardService.insertSeeBoard(board,seeBoard,animal,fList,map);
					
					if(result>0) {
						result = new SeeBoardService().sendSeeAlram(board.getBoardTitle(), seeBoard.getsBoardLocation(), animal.getAnimalBreed());
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
					
					SeeBoard seeBoard = seeBoardService.selectSeeBoard(boardNo);
					
					int animalCode = seeBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					Map map = new MapService().selectMap(boardNo);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					
					request.setAttribute("seeBoard", seeBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					request.setAttribute("map", map);
					
					path = "/WEB-INF/views/seeBoard/seeBoardDetail.jsp";
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
					
					SeeBoard seeBoard = seeBoardService.selectSeeBoard(no);
					
					int animalCode = seeBoard.getAnimalCode();
					
					Animal animal = boardService.selectAnimal(animalCode);
					
					String memberId = board.getBoardWriter();
					
					Member member = memberService.selectMember(memberId);
					
					Map map = new MapService().selectMap(no);
					
					if(!files.isEmpty()) {
						request.setAttribute("files", files);
					}
					request.setAttribute("seeBoard", seeBoard);
					request.setAttribute("animal", animal);
					request.setAttribute("board", board);
					request.setAttribute("member", member);
					request.setAttribute("map", map);
					
					path = "/WEB-INF/views/seeBoard/seeBoardUpdate.jsp";
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
					
					String sBoardLocation = multiRequest.getParameter("place1")
							+ "," + multiRequest.getParameter("place2")
							+ "," + multiRequest.getParameter("place3");
					String sBoardPhone = multiRequest.getParameter("phone1")
										+ "-" + multiRequest.getParameter("phone2")
										+ "-" + multiRequest.getParameter("phone3");
					String sBoardDate = multiRequest.getParameter("missingDate");
					
					String temp[] = sBoardDate.split("-");
					
					Date date = new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]));
					
					String sBoardMap = multiRequest.getParameter("spot");
					
					String sBoardLocationTell = multiRequest.getParameter("locationTell");
					String sBoardBreedTell = multiRequest.getParameter("breedTell");
					String sBoardCommentTell = multiRequest.getParameter("commentTell");
					
					SeeBoard seeBoard = new SeeBoard(sBoardLocation, sBoardPhone, date, sBoardMap, sBoardLocationTell, sBoardBreedTell, sBoardCommentTell);
					
					String animalGender = multiRequest.getParameter("gender");
					String animalType = multiRequest.getParameter("breed1");
					String animalBreed = multiRequest.getParameter("breed2");
					
					Animal animal = new Animal(animalGender, animalType, animalBreed);
					
					ArrayList<Attachment> fList = new ArrayList<Attachment>();
					
					// map 정보 가저오기
					Double mapLatitude = Double.parseDouble(multiRequest.getParameter("latitude"));
					Double mapLongitude = Double.parseDouble(multiRequest.getParameter("longitude"));
					String mapAddress = multiRequest.getParameter("mapAddress");
					Map map = new Map(mapLatitude, mapLongitude, mapAddress);
					
					
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
					
					int result = SeeBoardService.updateSeeBoard(board,seeBoard,animal,fList,map);
					
					if(result>0) {
						msg = "게시글 수정 성공";
						result = new SeeBoardService().sendSeeAlram(board.getBoardTitle(), seeBoard.getsBoardLocation(), animal.getAnimalBreed());
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
				int result = seeBoardService.deleteSeeBoard(no);
				
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

			String condition = null;
			
			searchValue = "'%' || '" + searchValue + "' || '%' ";
			
			switch(searchKey) {
			case "title": condition =  " BOARD_TITLE LIKE " + searchValue; break;
			case "content": condition =  " BOARD_CONTENT LIKE " + searchValue; break;
			case "titcont": condition =  " (BOARD_CONTENT LIKE" + searchValue + " OR BOARD_TITLE LIKE " + searchValue +")"; break;
			case "writer" : condition = " MEM_NAME LIKE " + searchValue; break;
			}
			try {
				int boardType = 2;
				int listCount = boardService.getSearchListCount(condition, boardType);
				
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
				
				int startRow = (currentPage -1) * limit + 1;
				int endRow = startRow + limit -1;
				
				maxPage = (int)Math.ceil( ( (double)listCount / limit ) );
				startPage = (currentPage -1) / pagingBarSize * pagingBarSize +1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInfo = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<BoardHJ> bList = boardService.searchBoardList(startRow, endRow, boardType, condition);
				
				List<Attachment> aList = boardService.searchAList(startRow, endRow, boardType, condition);
				 
				List<Animal> animalList = boardService.searchAnimalList(startRow, endRow, boardType, condition);
				
				List<SeeBoard> sList = seeBoardService.searchSeeList(startRow, endRow, boardType, condition);
				
				
				
				path = "/WEB-INF/views/seeBoard/seeBoardSearchList.jsp";
				request.setAttribute("pInf", pInfo);
				request.setAttribute("bList", bList);
				request.setAttribute("aList", aList);
				request.setAttribute("animalList", animalList);
				request.setAttribute("sList", sList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 검색", e);
			}
		}
		
		else if(command.equals("/insertComment")) {
			ReviewService reviewService = new ReviewService();
			String commentWriter = request.getParameter("writer"); // ajax 키값을 받아옴
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String commentContent = request.getParameter("content");
			
			// 댓글 알림용 게시글 작성자 얻어오기
			String boardWriter = request.getParameter("boardWriter");
			String alertContent = request.getParameter("alertContent");
			String alertURL = request.getParameter("alertURL");
			
			String email = request.getParameter("email");
			String title = request.getParameter("title");
			String commentTell = request.getParameter("commentTell");
			
			//Reply reply = new Reply(replyContent, boardId);
			Comment comment = new Comment(commentContent, boardNo);
			
			try {
				int result = reviewService.insertComment(comment, commentWriter);
				
				
				// 댓글 등록 시 게시글 작성자가 알림 설정한 경우 알림 board에 값 추가
				if(result > 0) {
					//int checkNo = reviewService.checkTell(boardWriter);
					if(!boardWriter.equals(commentWriter) && commentTell.equals("Y")) {
						new BoardService().sendCommentAlram(commentWriter, commentContent, title, email);
					}
				}
				
				
				if(!commentWriter.equals(boardWriter)) {
					// 댓글 등록 시 게시글 작성자가 알림 설정한 경우 ALERT 테이블에 값 추가
					if(result > 0) {
						String[] tell = reviewService.checkTell(boardWriter);
						
						if(tell[0].equals("Y") && tell[1].equals("Y")) { // 글 작성자가 댓글 알림을 켜둔 경우
							
							// 알림 테이블에 알림 정보 등록
							Alert alert = new Alert(boardWriter, alertContent, alertURL);
							
							result = reviewService.insertTell(alert);
						}
					}
				}
				
				response.getWriter().print(result);
				// getWriter(): response에 문자열을 포함시키는 객체
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "댓글 등록", e);
			}
			
		}
		
		else if(command.equals("/selectCommentList")) {
			ReviewService reviewService = new ReviewService();
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			try {
				List<Comment> cList = reviewService.selectCommentList(boardNo);
				
				response.setCharacterEncoding("UTF-8");
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // Gson은 GsonBuilder의 부모
				// Gson으로 만들고 결과는 json 형태로만 반환
				
				gson.toJson(cList, response.getWriter());
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "댓글 조회", e);
			}
			
		} 
		
		else if(command.equals("/commentUpdate")) {
			ReviewService reviewService = new ReviewService();
			int commentNo = Integer.parseInt(request.getParameter("commentNo")); // ajax 키값을 받아옴
			String commentContent = request.getParameter("commModifyContent");
			//Reply reply = new Reply(replyContent, boardId);
			Comment comment = new Comment(commentNo, commentContent);
			
			try {
				int result = reviewService.updateComment(comment);
				
				// getWriter(): response에 문자열을 포함시키는 객체
				response.getWriter().print(result);
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "댓글 수정", e);
			}
			
		}
		
		else if(command.equals("/commentDelete")) {
			ReviewService reviewService = new ReviewService();
			int commentNo = Integer.parseInt(request.getParameter("commentNo")); // ajax 키값을 받아옴
			//Reply reply = new Reply(replyContent, boardId);
			
			try {
				int result = reviewService.deleteComment(commentNo);
				
				// getWriter(): response에 문자열을 포함시키는 객체
				response.getWriter().print(result);
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "댓글 수정", e);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
