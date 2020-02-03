package com.kh.semiproject.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semiproject.common.ExceptionForward;
import com.kh.semiproject.common.alert.model.service.AlertService;
import com.kh.semiproject.common.alert.model.vo.Alert;
import com.kh.semiproject.member.model.vo.Member;
import com.kh.semiproject.common.ExceptionForward;

@WebFilter(urlPatterns = "/*")
public class AlertFilter implements Filter {

    public AlertFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		
		Member member = (Member) ((HttpServletRequest)request).getSession().getAttribute("loginMember");

		List<Alert> alertList = null;

		AlertService alertService = new AlertService();

		if (member != null) { // 로그인이 되어있는 경우
			try {
				if (member.getMemberWebTell().equals("Y")) {
					alertList = alertService.selectAlert(member.getMemberId());
				}

				((HttpServletRequest)request).getSession().setAttribute("alertList", alertList);

			} catch (Exception e) {
				ExceptionForward.errorPage((HttpServletRequest)request, (HttpServletResponse)response, "웹페이지 알림 조회", e);
			}
		}
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		
		if (!uri.substring(uri.lastIndexOf(".") + 1).equals("css")) {
			response.setContentType("text/html; charset=UTF-8");
		}
		
		chain.doFilter(request, response);
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
