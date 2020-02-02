package com.kh.semiproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import com.kh.semiproject.wrapper.EncryptWrapper;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;


// 암호화 필터
@WebFilter(urlPatterns = {"/member/login", 
		"/member/signUp", "/member/updatePwd",
		"/member/deleteMember", "/mypage/updatePwd"
})
public class EncryptFilter implements Filter {

    public EncryptFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest h_request = (HttpServletRequest)request;
		
		// Encrypt Wrapper
		// 요청 객체 자체를 새로운 것으로 변환시켜 받는다
		
		// Encrypt Wrapper 객체 생성
		EncryptWrapper encWrapper = new EncryptWrapper(h_request);
		
		// pass the request along the filter chain
		// request를 encWrapper로 대체
		chain.doFilter(encWrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
