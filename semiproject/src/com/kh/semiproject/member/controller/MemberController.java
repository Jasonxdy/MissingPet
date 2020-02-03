package com.kh.semiproject.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.proImgRenamePolicy;
import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.wrapper.EncryptWrapper;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/member").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		MemberService memberService = new MemberService();
		//MemberService boardService = new MemberService();
		
		if(command.equals("/loginPage")) {
			path = "/WEB-INF/views/member/login.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		else if(command.equals("/login")) {
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			System.out.println("로그인 비밀번호: " + memberPwd);
			try {
				
			Member member = new Member(memberId, memberPwd);
			
			Member loginMember = new MemberService().loginMember(member);
			
			System.out.println("loginMember : " + loginMember);
			
			HttpSession session = request.getSession();
			
			if(loginMember != null) {
				session.setMaxInactiveInterval(600);
				
				session.setAttribute("loginMember", loginMember);
				String save = request.getParameter("save");
				
				Cookie cookie = new Cookie("saveId", memberId);
				
				if(save != null) {
					// 쿠키가 유지될 수 있는 유효기간 설정
					cookie.setMaxAge(60*60*24*7);
				}else {
					cookie.setMaxAge(0);
				}
				
				
				cookie.setPath("/");
				
				response.addCookie(cookie);
				
			} else {
				session.setAttribute("msg", "로그인 정보가 유효하지 않습니다.");
			}
			
			//response.sendRedirect(request.getHeader("referer"));
			
			response.sendRedirect(request.getContextPath());
			
			} catch(Exception e) {
				ExceptionForward.errorPage(request, response, "로그인", e);
			}
		}
		// 로그 아웃
		else if(command.equals("/logout")){
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath());
			System.out.println("로그아웃");
		}
		
		// 회원가입 페이지로 이동
		else if(command.equals("/signUpPage")) {
			path = "/WEB-INF/views/member/signUp.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		// 아이디 중복 체크
		else if(command.equals("/idDupCheck")) {
			String id = request.getParameter("id");
			
			try {
				int result = new MemberService().idDupCheck(id);
				
				/*request.setAttribute("result", result);
				request.setAttribute("id", id); // 같은 key 값으로 계속 유지시키기
				
				RequestDispatcher view = request.getRequestDispatcher("idDupForm.do");
				view.forward(request, response);*/
				
				PrintWriter out = response.getWriter(); //?
				if(result>0) out.append("no");
				else out.append("yes");
				System.out.println("아이디 중복체크");
				
			}catch(Exception e){
				ExceptionForward.errorPage(request, response, "아이디 중복 확인", e);
			}
		}
		
		else if(command.equals("/emailDupCheck")) {
			String email = request.getParameter("email");
			
			try {
				int result = new MemberService().emailDupCheck(email);
				
				/*request.setAttribute("result", result);
				request.setAttribute("id", id); // 같은 key 값으로 계속 유지시키기
				
				RequestDispatcher view = request.getRequestDispatcher("idDupForm.do");
				view.forward(request, response);*/
				
				PrintWriter out = response.getWriter(); //?
				if(result>0) out.append("no");
				else out.append("yes");
				System.out.println("아이디 중복체크");
				
			}catch(Exception e){
				ExceptionForward.errorPage(request, response, "아이디 중복 확인", e);
			}
		}
		
		// 회원 가입 submit
		else if(command.equals("/signUp")) {
			
	 		try {
	 			
	 			String root = request.getSession().getServletContext().getRealPath("/");
	 			String savePath = root + "resources/upProfileImage/";
	 			int maxSize = 1024 * 1024;
	 			
	 			
	 			MultipartRequest multiRequest = 
 						new MultipartRequest(request, savePath, maxSize,
 								"UTF-8", new proImgRenamePolicy());
	 			
	 			String memberProImg = "default.png";
	 			
 				Enumeration<String> files = multiRequest.getFileNames();
 				
 				if(multiRequest.getFilesystemName("profile-img") != null) {
 					memberProImg = multiRequest.getFilesystemName("profile-img");
 				}
 				
 				
 				String memberId = multiRequest.getParameter("id");
 				String memberPwd = multiRequest.getParameter("pwd1");
 				memberPwd = EncryptWrapper.getSha512(memberPwd);
 				String memberName = multiRequest.getParameter("name");
 				String phone1 = multiRequest.getParameter("phone1");
 				String phone2 = multiRequest.getParameter("phone2");
 				String phone3 = multiRequest.getParameter("phone3");
 				// 전화번호를 - 와 함께 합침
 		 		String memberPhone = phone1 + "-" + phone2+ "-" + phone3;
 		 		
 		 		String memberEmail = multiRequest.getParameter("email");
 		 		
 		 		// 전달받은 파라미터를 Member 객체에 저장
 		 		Member member = new Member(memberId, memberName, memberPwd, 
 		 				memberEmail, memberPhone, memberProImg);
 			
 		 		int result = new MemberService().signUp(member);
 		 		
 		 		if(result>0) {
	 				msg = "가입 성공!";
	 				System.out.println(member);
	 			}else {
	 				msg = "가입 실패!";
	 			}
 		 		request.getSession().setAttribute("msg", msg);
	 			response.sendRedirect(request.getContextPath());
	 			
	 		}catch(Exception e) {
				ExceptionForward.errorPage(request, response, "회원가입", e);
	 		}
		}
		
		else if(command.equals("/findPage")) {
			path = "/WEB-INF/views/member/find.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		else if(command.equals("/findId")) {
			String memberName = request.getParameter("findIdName");
			String memberEmail = request.getParameter("findIdEmail");
			Member member = new Member(memberName, memberEmail);
			System.out.println(memberName);
			System.out.println(memberEmail);
			System.out.println("아이디찾기 컨트롤러에서 member: " + member);
			try {
				String memberId = memberService.findId(member);
				if(memberId != null) {
					request.setAttribute("memberEmail", memberEmail);
					path = "/WEB-INF/views/member/login.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					msg = "이름과 이메일이 일치하는 아이디가 없습니다.";
					request.getSession().setAttribute("msg", msg);
		 			response.sendRedirect(request.getContextPath());
				}
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "아이디 찾기", e);
			}
		}
		
		else if(command.equals("/findPwd")) {
			String memberId = request.getParameter("findPwdId");
			String memberEmail = request.getParameter("findPwdEmail");
			Member member = new Member(memberId, memberEmail);
			System.out.println("비밀번호찾기 컨트롤러에서 member: " + member);
			try {
				int result = memberService.findPwd(member);
				if(result>0) {
					request.setAttribute("memberEmail", memberEmail);
					path = "/WEB-INF/views/member/login.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}else {
					msg = "아이디와 이메일이 일치하는 사용자가 없습니다.";
					request.getSession().setAttribute("msg", msg);
		 			response.sendRedirect(request.getContextPath());
				}
			}catch (Exception e) {
				ExceptionForward.errorPage(request, response, "아이디 찾기", e);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
