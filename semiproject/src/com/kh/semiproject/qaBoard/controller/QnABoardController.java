package com.kh.semiproject.qaBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/qaBoard/*")
public class QnABoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QnABoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/qaBoard").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		
		if(command.equals("/QnA_Main")) {
			
			path = "/WEB-INF/views/qaBoard/QnA_Main";
			
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
	
			
			}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
