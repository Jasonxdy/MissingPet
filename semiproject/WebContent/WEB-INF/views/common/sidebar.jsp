<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.member.model.vo.Member"%>
<%
	String msg = (String)session.getAttribute("msg");
	Member loginMember = (Member)session.getAttribute("loginMember");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script>
		// 로그인 실패 메세지 출력 후 session에 남아있는 "msg" 속성 제거 
		<% if(msg != null){ %>
			alert("<%= msg %>");
		<%	session.removeAttribute("msg");
		 } %>
</script>
</head>
<body>
    
<div class="d-md-block bg-white sidebar" id="sidebarAll" style="z-index: 1030;">

        <div class="sidebar-sticky" id="sidebarAll" style="height: 800px;">

          <table class="table-responsive">

            <ul class="nav flex-column">
              <li class="align-items-center text-center">
                <a id="logo" href="<%= request.getContextPath() %>">
                  <img src="<%= request.getContextPath() %>/img/logo7.png" width=150px>
                </a>
              </li>
			<% if(loginMember == null){ %>
              <li class="nav-item align-items-center">
              <a href="<%= request.getContextPath() %>/member/loginPage">
                <button type="button" class="btn btn-outline-primary btn-sm px-5 mt-5 p-3" id="loginBtn">로그인</button>
              </a>
              </li>
			<% } else {%>
				<li>
              	<div class="justif-content-end align-items-center text-center">
              		<div>
						<img src="<%= request.getContextPath() %>/resources/upProfileImage/<%= loginMember.getMemberProImg() %>"
						style="width:50px; border-radius: 100%;">
					</div>
				<%= loginMember.getMemberName() %>님 환영합니다. 
				</div>
				<a href="<%= request.getContextPath() %>/member/logout">
                <button type="button" class="btn btn-outline-secondary btn-sm px-3 mt-5 p-2" id="loginBtn">로그아웃</button>
              </a>
              </li>
              <% } %>
              
              <li class="nav-item mt-5">
                <p>&nbsp;</p>
              </li>

              <li class="nav-item mt-3">
                <p>&nbsp;</p>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link active text-center mt-4 mb-1" href="<%=request.getContextPath() %>/findBoard/boardList" id="findhover">
                  찾아주세요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="<%=request.getContextPath() %>/seeBoard/boardList" id="seehover">
                  봤어요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="<%=request.getContextPath() %>/adoptBoard/boardList" id="adopthover">
                  입양했어요
                </a>
              </li>

              <li class="nav-item mb-1">
                <a class="nav-link  text-center" href="<%= request.getContextPath() %>/review/reviewList" id="reviewhover">
                  만남 그 후
                </a>
              </li>

              <li class="nav-item mb-5">
                <a class="nav-link  text-center" href="<%= request.getContextPath() %>/free/list" id="freehover">
                  자유게시판
                </a>
              </li>

              <li class="nav-item">
                <a class="nav-link  text-center" href="<%= request.getContextPath() %>/ask/main" id="askhover">
                  Q&A
                </a>
              </li>
              <%-- 마이페이지는 로그인 시에만 보이게 변경? or 로그아웃 버튼 옆에? --%>
              <li class="nav-item">
              
              
                <% if(loginMember == null) { %>
                <a class="nav-link  text-center" 
                href="<%= request.getContextPath() %>/member/loginPage" id="mypagehover">
                  마이페이지
                </a>
	              <% } else { %>
                <a class="nav-link  text-center" 
                href="<%= request.getContextPath() %>/mypage/main" id="mypagehover">
                  마이페이지
                </a>
               <% } %>
                  <!-- 로그인 전 화면인데 마이페이지 구현? -->
              </li>











		<%-- 로그인 된 계정이 관리자 등급인 경우에만 관리자페이지 버튼 표출 --%>
	        <% if(loginMember != null && loginMember.getMemberId().equals("admin")){ %>
		       	<li class="nav-item">
	                <a class="nav-link text-center" 
	                href="<%=request.getContextPath()%>/Management/management_Main">
	               		  관리자페이지
	                </a>
	            </li>
	        <% } %>        
              

            </ul>

          </table>
        </div>
      </div>
	


</body>
</html>