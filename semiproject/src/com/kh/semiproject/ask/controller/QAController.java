package com.kh.semiproject.ask.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semiproject.ask.model.service.QAService;
import com.kh.semiproject.ask.model.vo.Ask;
import com.kh.semiproject.ask.model.vo.QA;
import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.member.model.vo.Member;


@WebServlet("/ask/*")
public class QAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/ask").length());

		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		QAService qaService = new QAService();
		
		if(command.equals("/main")) {
			
			QA qa = null;
			
			try {
				List<QA> qaList = qaService.selectQA();
				request.setAttribute("qaList", qaList);
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "QA 조회", e);
				
			}
			
			path = "/WEB-INF/views/ask/main.jsp";
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}
		
		
		
		
		
		// 1:1 문의 등록용 Controller
		else if(command.equals("/insertAsk")) {
			
			String askTitle = request.getParameter("askTitle");
			String askContent = request.getParameter("askContent");
			String memberId = ((Member)request.getSession().getAttribute("loginMember")).getMemberId();
			
			Ask ask = new Ask();
			ask.setAskTitle(askTitle);
			ask.setAskContent(askContent);
			ask.setMemberId(memberId);
			
			try {
				
				int result = qaService.insertAsk(ask);
				
				if(result > 0) {
					msg = "1:1 문의 등록 성공. 마이페이지에서 확인해주세요";
				}
				else {
					msg = "1:1 문의 등록 실패";
				}
				
				request.getSession().setAttribute("msg", msg);
				
				response.sendRedirect(request.getHeader("referer"));
				
			} catch (Exception e) {
				ExceptionForward.errorPage(request, response, "1:1문의 작성", e);
			}
			
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
