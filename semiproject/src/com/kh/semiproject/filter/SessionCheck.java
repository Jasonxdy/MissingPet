package com.kh.semiproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*@WebFilter(filterName="sessionRunout", urlPatterns = {"/review/*",
		"/adoptBoard/*", "/ask/*", "/Board/*", "/findBoard/*",
		"/free/*", "/Management/*", "/mypage/*", "/qaBoard/*",
		"/seeBoard/*"})*/
@WebFilter(filterName="sessionRunout", urlPatterns = {"/mypage/*",
		"/Management/*",
		"/findBoard/insertForm", "/findBoard/insert",
		"/findBoard/updateForm", "/findBoard/update",
		"/findBoard/delete",
		"/free/writeForm", "/free/write", "/free/delete",
		"/free/updateForm", "/free/update",
		"/review/reviewInsert", "/review/insert", "/review/reviewDelete",
		"/review/reviewUpdate", "/review/update", "/review/report",
		"/seeBoard/insertForm", "/seeBoard/insert", "/seeBoard/delete",
		"/seeBoard/updateForm", "/seeBoard/update"
		})
public class SessionCheck implements Filter {
	public SessionCheck() {
		// TODO Auto-generated constructor stub
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession(false);
		if(session.getAttribute("loginMember") == null) {
			String path = "/WEB-INF/views/common/sessionRunout.jsp";
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
			//((HttpServletResponse) response).sendRedirect("/WEB-INF/views/common/sessionRunout.jsp");
		} else {
		chain.doFilter(request, response);
		}
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}