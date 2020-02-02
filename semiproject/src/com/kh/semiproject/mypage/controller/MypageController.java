package com.kh.semiproject.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.proImgRenamePolicy;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.mypage.model.dao.MypageDAO;
import com.kh.semiproject.mypage.model.service.MypageService;
import com.kh.semiproject.mypage.model.vo.Ask;
import com.kh.semiproject.mypage.model.vo.Board;
import com.kh.semiproject.mypage.model.vo.Reply;
import com.kh.semiproject.review.model.vo.PageInfo;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/mypage/*")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MypageController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/mypage").length());

		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		MypageService mypageService = new MypageService();

		
		// 메인화면 이동용 Controller
		if (command.equals("/main")) {
			
			if(request.getSession().getAttribute("loginMember") == null) {
				path = "/WEB-INF/views/member/login.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			else {

				String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		    	
		    	try {
					
					Member loginMember = mypageService.recreateSession(memberId);
					
					HttpSession session = request.getSession();
	
					session.setAttribute("loginMember", loginMember);
					session.setMaxInactiveInterval(600);
		    		
	//				// DB에서 아이디가 일치하는 회원 정보 읽어오기
	//	    		Member selectMember = new MypageService().selectMember(memberId);
	//	    		
	//	    		request.setAttribute("member", selectMember);
	//	    		
		    		path = "/WEB-INF/views/mypage/main.jsp";
		    		view = request.getRequestDispatcher(path);
		    		view.forward(request, response);
		    		
				} catch (Exception e) {
					ExceptionForward.errorPage(request, response, "회원 정보 조회", e);
					
				}
			}

		}
		
		
		// 프로필 수정용 Controlller
		else if (command.equals("/updateMember")){
			
			Member loginMember = (Member)request.getSession().getAttribute("loginMember");
			
			
			try {
				String root = request.getSession().getServletContext().getRealPath("/");
	 			String savePath = root + "resources/upProfileImage/";
	 			int maxSize = 1024 * 1024;
	 			
	 			
	 			MultipartRequest multiRequest = 
 						new MultipartRequest(request, savePath, maxSize,
 								"UTF-8", new proImgRenamePolicy());
	 			
 				// 업로드 된 파일이 없는 경우
 				String memberProImg = ((Member)request.getSession().getAttribute("loginMember")).getMemberProImg();
 				
 				// 업로드 된 파일이 있는 경우
 				if(multiRequest.getFilesystemName("imageUpload") != null) {
 					memberProImg = multiRequest.getFilesystemName("imageUpload");
 				}

 				String phone1 = multiRequest.getParameter("phone1");
 				String phone2 = multiRequest.getParameter("phone2");
 				String phone3 = multiRequest.getParameter("phone3");
 				// 전화번호를 - 와 함께 합침
 		 		String memberPhone = phone1 + "-" + phone2+ "-" + phone3;
 		 		
 		 		// 전달받은 파라미터를 Member 객체에 저장
 		 		Member member = new Member(loginMember.getMemberId(), loginMember.getMemberName(), memberPhone, memberProImg);
 			
 		 		int result = mypageService.updateMember(member);
 		 		
 		 		if(result > 0) {
 		 			msg = "회원 정보 수정 성공";
 		 		} else {
 		 			msg = "회원 정보 수정 실패";
 		 		}
 		 		
 		 		request.getSession().setAttribute("msg", msg);
 				response.sendRedirect("main");
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "회원정보 수정", e);
			}
			

		}
		
		
		
		
		
		// 비밀번호 변경 페이지 조회용 Controller
		else if (command.equals("/changePwd")) {
			path = "/WEB-INF/views/mypage/changePwd.jsp";
    		view = request.getRequestDispatcher(path);
    		view.forward(request, response);
		}
		
		
		
		
		// 비밀번호 변경용 Controller
		else if (command.equals("/updatePwd")) {
			
			HttpSession session = request.getSession();
			Member loginMember = (Member)session.getAttribute("loginMember");
//			String memberId = loginMember.getMemberId();
			// 전달받은 파라미터를 효율적으로 서비스단으로 전달하기 위해 loginMember를 활용
			// -> loginMember에 id가 저정되어 있으므로 memberId가 불필요해짐.
			
			String currentPwd = request.getParameter("currentPwd");
			loginMember.setMemberPwd(currentPwd);
			// loginMember 객체에 currentPwd를 저장하여 서비스로 전달하는 매개변수 개수를 줄임.
			
			String newPwd = request.getParameter("newPwd1");
			
			
			// 3. 비즈니스 로직을 위한 서비스 호출 후 반환 값 저장
			try {
				int result = mypageService.updatePwd(loginMember, newPwd);
				
				if(result > 0) {
					msg = "비밀번호가 변경되었습니다.";
					path = "main";
					
				} else if (result == 0) {
					msg = "비밀번호 변경에 실패하였습니다.";
					path = "changePwd";
				} else {
					msg = "현재 비밀번호가 일치하지 않습니다";
					path = "changePwd";
				}
				
				session.setAttribute("msg", msg);
				response.sendRedirect(path);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "비밀번호 벽경", e);
			}
		}
		
		
		
		
		// 내가 작성한 글 조회용 Controller
		else if (command.equals("/writingList")) {
			try {
				
				// 현재 로그인된 loginMember의 아이디 얻어오기
				String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
				
				int listCount = mypageService.getListCount(memberId);
				int limit = 10; // 한 페이지에 보여질 게시글의 수
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수
				
				int currentPage = 0; // 현재 페이지 번호를 표시할 변수
				int maxPage = 0; // 전체 페이지의 수 (== 마지막 페이지)
				int startPage = 0; // 페이징바 시작 페이지 번호
				int endPage = 0; // 페이징바 끝 페이지 번호
				
				
				
				// currentPage - 현재 페이지 번호를 표시할 변수
				// 처음 게시판 목록으로 화면이 전환 되면  1페이지가 보여야 함
				if(request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
//					전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				
				// maxPage - 총 페이지 수 (== 마지막 페이지)
				// limit == 5일 경우
				// 게시글의 개수가 50개일 경우 필요 페이지 수 : 10 페이지
				// 게시글의 개수가 51개일 경우 필요 페이지 수 : 11 페이지
				maxPage = (int)Math.ceil( ((double)listCount / limit) );
				// Math.ceil(number) : 올림
				
				
				
				
				// startPage - 페이징바의 시작 페이지 번호
				// 페이징바에 수가 10개씩 보여질 경우 
				// 시작 번호는 1, 11, 21, 31, ... 
				
				startPage = (currentPage-1) / pagingBarSize * pagingBarSize  + 1;
				
				
				
				
				// endPage - 페이징바 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if(maxPage <= endPage) {
					endPage = maxPage;
				}
				
				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);
				
				
				
				List<Board> bList = mypageService.selectList(currentPage, limit, memberId);
				
				path = "/WEB-INF/views/mypage/writings.jsp";
				request.setAttribute("bList", bList);
				request.setAttribute("pInf", pInf);
				
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
			} catch (Exception e) {
				
				ExceptionForward.errorPage(request, response, "게시글 목록 조회", e);
				
			}
			
		}
		
		
		
		
		
		
		// 내가 작성한 댓글 조회용 Controller
		else if (command.equals("/commentList")) {
			try {

				// 현재 로그인된 loginMember의 아이디 얻어오기
				String memberId = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();

				int listCount = mypageService.getReplyListCount(memberId);
				int limit = 10; // 한 페이지에 보여질 게시글의 수
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수

				int currentPage = 0; // 현재 페이지 번호를 표시할 변수
				int maxPage = 0; // 전체 페이지의 수 (== 마지막 페이지)
				int startPage = 0; // 페이징바 시작 페이지 번호
				int endPage = 0; // 페이징바 끝 페이지 번호

				// currentPage - 현재 페이지 번호를 표시할 변수
				// 처음 게시판 목록으로 화면이 전환 되면 1페이지가 보여야 함
				if (request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
//					전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}

				// maxPage - 총 페이지 수 (== 마지막 페이지)
				// limit == 5일 경우
				// 게시글의 개수가 50개일 경우 필요 페이지 수 : 10 페이지
				// 게시글의 개수가 51개일 경우 필요 페이지 수 : 11 페이지
				maxPage = (int) Math.ceil(((double) listCount / limit));
				// Math.ceil(number) : 올림

				// startPage - 페이징바의 시작 페이지 번호
				// 페이징바에 수가 10개씩 보여질 경우
				// 시작 번호는 1, 11, 21, 31, ...

				startPage = (currentPage - 1) / pagingBarSize * pagingBarSize + 1;

				// endPage - 페이징바 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if (maxPage <= endPage) {
					endPage = maxPage;
				}

				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);

				List<Reply> rList = mypageService.selectReplyList(currentPage, limit, memberId);

				path = "/WEB-INF/views/mypage/comments.jsp";
				request.setAttribute("rList", rList);
				request.setAttribute("pInf", pInf);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "댓글 목록 조회", e);
			}
			
		}
		
		
		
		
		// 1:1 문의 Controller
		else if (command.equals("/askList")) {
			
			
			try {

				// 현재 로그인된 loginMember의 아이디 얻어오기
				String memberId = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();

				int listCount = mypageService.getAskListCount(memberId);
				int limit = 10; // 한 페이지에 보여질 게시글의 수
				int pagingBarSize = 5; // 보여질 페이징바의 페이지 개수

				int currentPage = 0; // 현재 페이지 번호를 표시할 변수
				int maxPage = 0; // 전체 페이지의 수 (== 마지막 페이지)
				int startPage = 0; // 페이징바 시작 페이지 번호
				int endPage = 0; // 페이징바 끝 페이지 번호

				// currentPage - 현재 페이지 번호를 표시할 변수
				// 처음 게시판 목록으로 화면이 전환 되면 1페이지가 보여야 함
				if (request.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
//					전달 받은 값이 있을 경우 해당 번호를 저장
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}

				// maxPage - 총 페이지 수 (== 마지막 페이지)
				// limit == 5일 경우
				// 게시글의 개수가 50개일 경우 필요 페이지 수 : 10 페이지
				// 게시글의 개수가 51개일 경우 필요 페이지 수 : 11 페이지
				maxPage = (int) Math.ceil(((double) listCount / limit));
				// Math.ceil(number) : 올림

				// startPage - 페이징바의 시작 페이지 번호
				// 페이징바에 수가 10개씩 보여질 경우
				// 시작 번호는 1, 11, 21, 31, ...

				startPage = (currentPage - 1) / pagingBarSize * pagingBarSize + 1;

				// endPage - 페이징바 끝페이지 번호
				endPage = startPage + pagingBarSize - 1;
				if (maxPage <= endPage) {
					endPage = maxPage;
				}

				PageInfo pInf = new PageInfo(listCount, limit, pagingBarSize, currentPage, maxPage, startPage, endPage);

				List<Ask> aList = mypageService.selectAskList(currentPage, limit, memberId);

				path = "/WEB-INF/views/mypage/1to1.jsp";
				request.setAttribute("aList", aList);
				request.setAttribute("pInf", pInf);

				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				

			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "1:1 문의 목록 조회", e);
			}
			
		}
		
		
		
		
		
		
		// 알림 설정 조회 Controller
		else if (command.equals("/notification")) {
			
			
			String memberId = ((Member) request.getSession().getAttribute("loginMember")).getMemberId(); 
			
			try {
				
				Member member = mypageService.selectTell(memberId);
				
				request.setAttribute("member", member);
				path = "/WEB-INF/views/mypage/notification.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
				
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "알림 설정 조회", e);
			}
		}
		
		
		
		// 알림 설정 수정용 Controller
		else if (command.equals("/updateTell")) {
			
			String memberId = request.getParameter("memberId");
			String webTell = (request.getParameter("webTell").equals("true"))? "Y" : "N";
			String commentTell = (request.getParameter("commentTell").equals("true"))? "Y" : "N";
			String askTell = (request.getParameter("askTell").equals("true"))? "Y" : "N";
			String rtTell = (request.getParameter("rtTell").equals("true"))? "Y" : "N";
			
			// 알림설정 전달할 객체 생성
			Member member = new Member(memberId, null, null, null, null, webTell, commentTell, askTell, rtTell,	null, null,	null, null,	null);
			
			int result = 0;
			
			try {
				// 알림 설정 DB 삽입 결과 조회
				result = mypageService.updateTell(member);
				response.getWriter().print(result);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "알림 수정", e);
			}
			
			
			
			
			
		}
			
		
		
		
		
		
		
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
