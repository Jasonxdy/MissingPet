package com.kh.semiproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// filterName: 필터의 이름 지정(필터 순서 지정 시 사용)
// urlPatterns: 필터가 적용될 url 패턴 지정(/*: 모든 url)

@WebFilter(filterName="encoding", urlPatterns = "/*")
public class CommonFilter implements Filter {
	// implements Filter; 꼭 상속 받아야함
	
	/* Filter는 request, response가 Servlet/JSP 또는 클라이언트에게 도달하기 전에 
	 * 필요한 전 / 후 처리 작업을 맡는 클래스 (FilterChain을 통해 여러 필터 사용 가능)
	 * 
	 * Filter 생명주기
	 * init() -> doFilter() -> destroy()
	 * 
	 * 
	 * Filter 등록 방법
	 * 1. web.xml에 등록
	 * 2. @webFilter 어노테이션 사용(Servlet 3.0 이상)
	 * 	-> tomcat 8.5v == Servlet 3.1
	 * */
    public CommonFilter() {    }

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 서블릿 컨테이너가 현재 요청/응답에 필터 적용이 필요하다 판단되면
		// 호출되는 메소드
		
		// 요청/응답 시 문자 인코딩을 모두 UTF-8로 변환하는 필터
		// 1. 요청 받은 값 변환
		request.setCharacterEncoding("UTF-8");
		
		// 2. 뷰 로 전달(응답)할 값 변환
		// response.setContentType("text/html; charset=UTF-8");
	     String uri = ((HttpServletRequest)request).getRequestURI();
         
	      if(!uri.substring(uri.lastIndexOf(".")+1).equals("css")) {
	         response.setContentType("text/html; charset=UTF-8");
	      }
	      
	      
		chain.doFilter(request, response);
		/* Filter 인터페이스의 doFilter
		 * -> 필요한 필터링 작업을 작성하는 곳
		 * 
		 * FilterChain의 doFilter()
		 *  -> 다음 필터로 넘어가거나, 
		 *     현재 필터가 마지막일 경우 
		 *     요청된 Servlet/JSP 또는 View를 호출
		 * */
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
