package com.kh.semiproject.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semiproject.board.model.service.BoardService;
import com.kh.semiproject.board.model.vo.Board;

import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.member.model.service.MemberService;
import com.kh.semiproject.member.model.vo.Member;


@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath+"/ ").length());
		
		String msg = null;
		String path = null;
		RequestDispatcher view = null;
		
		

	} // doGet End
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
