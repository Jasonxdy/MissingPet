package com.kh.semiproject.review.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.semiproject.board.model.vo.Animal;
import com.kh.semiproject.board.model.vo.Attachment;
import com.kh.semiproject.board.model.vo.Board;
import com.kh.semiproject.board.model.vo.BoardHJ;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.proImgRenamePolicy;
import com.kh.semiproject.common.alert.model.vo.Alert;
import com.kh.semiproject.findBoard.model.vo.FindBoard;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.review.model.dao.ReviewDAO;
import com.kh.semiproject.review.model.service.ReviewService;
import com.kh.semiproject.review.model.vo.Comment;
import com.kh.semiproject.review.model.vo.Img;
import com.kh.semiproject.review.model.vo.PageInfo;
import com.kh.semiproject.review.model.vo.Report;
import com.kh.semiproject.review.model.vo.Review;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/review/*")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewService reviewService = new ReviewService();
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/review").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		ReviewService rService = new ReviewService();
		
		if(command.equals("/reviewList")) {
			try {
				int listCount = reviewService.getListCount();
				
				int limit = 5;
				int pagingBarSize = 5;
				
				int currentPage = 0;
				int maxPage = 0;
				int startPage = 0;
				int endPage = 0;
				
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				}else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				maxPage = (int)Math.ceil(((double)listCount / limit));
				
				startPage = (currentPage-1) / pagingBarSize * pagingBarSize + 1;
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize,
						currentPage, maxPage, startPage, endPage);
				List<Review> rList = reviewService.selectList(currentPage, limit);
				List<Img> iList = reviewService.selectImgList(currentPage, limit);
				
				path = "/WEB-INF/views/review/reviewList.jsp";
				request.setAttribute("rList", rList);
				request.setAttribute("pInf", pInf);
				request.setAttribute("iList", iList);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "만남 그 후 목록 조회", e);
			}
			
		}
		
		else if(command.equals("/reviewInsert")) {
			path = "/WEB-INF/views/review/reviewInsert.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		else if(command.equals("/insert")) {
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10*1024*1024;
					String root = request.getSession().getServletContext().getRealPath("/");
					String savePath = root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = 
							new MultipartRequest(request, savePath, maxSize,
									"UTF-8", new proImgRenamePolicy());
					
					ArrayList<String> saveFiles = new ArrayList<String>();
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					
					HashMap<String, String> names = new HashMap<String, String>();
					while(files.hasMoreElements()) {
						String name = files.nextElement();
						if(multiRequest.getFilesystemName(name)!=null) {
							saveFiles.add(multiRequest.getFilesystemName(name));
							originFiles.add(multiRequest.getOriginalFileName(name));
							names.put(multiRequest.getFilesystemName(name), name);
						}
					}
					
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardUrl = multiRequest.getParameter("reviewUrl");
					HttpSession session = request.getSession();
					Member member = (Member)session.getAttribute("loginMember");
					String memberId = member.getMemberId();
					
					Review review = new Review(boardTitle, boardContent, boardUrl, memberId);
					ArrayList<Img> iList = new ArrayList<Img>();
					
					for(int i=originFiles.size()-1; i>=0; i--) {
						Img file = new Img();
						file.setImgPath(savePath);
						file.setImgOriginName(originFiles.get(i));
						file.setImgChangeName(saveFiles.get(i));
						
						// name(img)에 따라 level값 부여
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setImgLevel(0);
						} else if(names.get(saveFiles.get(i)).equals("img2")) {
							file.setImgLevel(1);
						} else if(names.get(saveFiles.get(i)).equals("img3")) {
							file.setImgLevel(2);
						} else if(names.get(saveFiles.get(i)).equals("img4")) {
							file.setImgLevel(3);
						} else if(names.get(saveFiles.get(i)).equals("img5")) {
							file.setImgLevel(4);
						}
						
						iList.add(file);
					}
					int result = rService.insertReview(review, iList);
					
					if(result == 0) msg= "게시글 등록 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("reviewList");
				}
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "만남 그 후게시글 등록", e);
			}
		}
		
		else if(command.equals("/reviewDetail")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				Review review = reviewService.selectReview(boardNo);
				reviewService.increaseCount(boardNo);
				if(review != null) {
					List<Img> imgs = reviewService.selectImgs(boardNo);
					
					if(!imgs.isEmpty()) {
						request.setAttribute("imgs", imgs);
					}
					
					path = "/WEB-INF/views/review/reviewDetail.jsp";
					request.setAttribute("review", review);
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					request.getSession().setAttribute("msg", "게시글 상세 조회 실패");
					response.sendRedirect("reviewList");
				}
				
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "만남 그 후 상세 조회", e);
			}
		}
		
		else if(command.equals("/reviewDelete")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				HttpSession session = request.getSession();
				Member member = (Member)session.getAttribute("loginMember");
				String lgMemberId = member.getMemberId();
				String writer = request.getParameter("memid");
				if(lgMemberId.equals(writer) || member.getMemberGrade().equals("Y")) {
					int result = reviewService.reviewDelete(boardNo);
					if(result>0) {
						request.getSession().setAttribute("msg", "게시글 삭제 성공");
						response.sendRedirect("reviewList");
					} else {
						request.getSession().setAttribute("msg", "게시글 삭제 실패");
						response.sendRedirect("reviewList");
					}
				} else {
					request.getSession().setAttribute("msg", "작성한 글만 삭제할 수 있습니다.");
					response.sendRedirect("reviewList");
				}
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "만남 그 후 글 삭제", e);
			}
		}
		
		else if(command.equals("/reviewUpdate")) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			try {
				Review review = reviewService.selectReview(boardNo);
				
				if(review != null) {
					List<Img> imgs = reviewService.selectImgs(boardNo);
					if(!imgs.isEmpty()) {
						request.setAttribute("imgs", imgs);
					}
				}
				path = "/WEB-INF/views/review/reviewUpdate3.jsp";
				request.setAttribute("review", review);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "글 수정 페이지 이동", e);
			}	
			
		}
		
		else if(command.equals("/update")) {
			try {
				if(ServletFileUpload.isMultipartContent(request)) {
					int maxSize = 10*1024*1024;
					String root = request.getSession().getServletContext().getRealPath("/");
					String savePath = root + "resources/uploadImages/";
					
					MultipartRequest multiRequest = 
							new MultipartRequest(request, savePath, maxSize,
									"UTF-8", new proImgRenamePolicy());
					ArrayList<String> saveFiles = new ArrayList<String>();
					ArrayList<String> originFiles = new ArrayList<String>();
					
					Enumeration<String> files = multiRequest.getFileNames();
					
					// 저장된 위치값 name을 확인하기 위한 map 생성
					HashMap<String, String> names = new HashMap<String, String>();
					while(files.hasMoreElements()) {
						String name = files.nextElement();
						if(multiRequest.getFilesystemName(name)!=null) {
							saveFiles.add(multiRequest.getFilesystemName(name));
							originFiles.add(multiRequest.getOriginalFileName(name));
							names.put(multiRequest.getFilesystemName(name), name);
						}
					}
					int boardNo = Integer.parseInt(multiRequest.getParameter("no"));
					String boardTitle = multiRequest.getParameter("title");
					String boardContent = multiRequest.getParameter("content");
					String boardUrl = multiRequest.getParameter("reviewUrl");
					HttpSession session = request.getSession();
					Member member = (Member)session.getAttribute("loginMember");
					String memberId = member.getMemberId();
					
					Review review = new Review(boardTitle, boardContent, boardUrl, memberId);
					ArrayList<Img> iList = new ArrayList<Img>();
					
					for(int i=originFiles.size()-1; i>=0; i--) {
						Img file = new Img();
						file.setImgPath(savePath);
						file.setImgOriginName(originFiles.get(i));
						file.setImgChangeName(saveFiles.get(i));
						
						// 각 input 자리에 맞는 이미지에 level 설정
						if( (i == originFiles.size()-1) && multiRequest.getFilesystemName("img1") != null) {
							file.setImgLevel(0);
						} else if(names.get(saveFiles.get(i)).equals("img2")) {
							file.setImgLevel(1);
						} else if(names.get(saveFiles.get(i)).equals("img3")) {
							file.setImgLevel(2);
						} else if(names.get(saveFiles.get(i)).equals("img4")) {
							file.setImgLevel(3);
						} else if(names.get(saveFiles.get(i)).equals("img5")) {
							file.setImgLevel(4);
						}
						
						iList.add(file);
					}
					int result = rService.updateReview(boardNo, review, iList);
					
					if(result > 0) msg= "게시글 수정 성공";
					else msg = "게시글 수정 실패";
					
					request.getSession().setAttribute("msg", msg);
					response.sendRedirect("reviewList");
				}
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "Review 수정", e);
			}
			
		}
		
		else if(command.equals("/insertComment")) {
			String commentWriter = request.getParameter("writer"); // ajax 키값을 받아옴
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String commentContent = request.getParameter("content");
			
			// 댓글 알림용 게시글 작성자 얻어오기
			String boardWriter = request.getParameter("boardWriter");
			String alertContent = request.getParameter("alertContent");
			String alertURL = request.getParameter("alertURL");
			
			//Reply reply = new Reply(replyContent, boardId);
			Comment comment = new Comment(commentContent, boardNo);
			
			try {
				int result = reviewService.insertComment(comment, commentWriter);
				
				
				// 댓글 등록자와 글 작성자가 같지 않는 경우만 실행
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
		
		else if(command.equals("/report")) {
			String reportTitle = request.getParameter("reportTitle");
			String reportContent = request.getParameter("reportContent");
			String memberId = request.getParameter("reportMemberId");
			int boardNo = Integer.parseInt(request.getParameter("reportBoardNo"));
			
			Report report = new Report(reportTitle, reportContent, memberId, boardNo);
			try {
				int result = reviewService.insertReport(report);
				if(result>0) {
					msg="신고 완료되었습니다.";
				}else {
					msg="신고 과정에 오류가 발생했습니다.";
				}
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect(request.getHeader("referer"));
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "신고하기", e);
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
			case "writer" : condition = " MEM_ID LIKE " + searchValue; break;
			}
			try {
				int listCount = reviewService.getSearchListCount(condition);
				
				int limit = 5;
				int pagingBarSize = 5;
				
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
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				List<Review> rList = reviewService.searchReviewList(startRow, endRow, condition);
				List<Img> iList = reviewService.searchRImgList(startRow, endRow, condition);
				//
				path = "/WEB-INF/views/review/reviewSearchList.jsp";
				request.setAttribute("rList", rList);
				request.setAttribute("pInf", pInf);
				request.setAttribute("iList", iList);
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "게시판 검색", e);
			}
		}
		
		else if (command.equals("/deleteAlert")) {
			
			int alertNo = Integer.parseInt(request.getParameter("alertNo"));
			
			System.out.println("delteAlert 실행");
			
			try {
				
				int result = reviewService.deleteAlert(alertNo);
				
				response.getWriter().print(result);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "알림 삭제", e);
			}
		}
		
	}
	//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
