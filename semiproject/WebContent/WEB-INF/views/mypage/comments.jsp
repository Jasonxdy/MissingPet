<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.review.model.vo.PageInfo"%>
<%@page import="com.kh.semiproject.mypage.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.semiproject.mypage.model.vo.Reply"%>
    
<% 
	List<Reply> rList = (List<Reply>)request.getAttribute("rList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	
	
%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <script src="https://kit.fontawesome.com/ec93b1bab1.js" crossorigin="anonymous"></script>

  <!-- 구글 폰트 추가 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-comment.css">

</head>

<body>


  <div class="container-fluid">

    <div class="row">

        <%@ include file="/WEB-INF/views/common/sidebar.jsp"%>


      <!-- 마이페이지 내용 작성 -->
      <div class="container-fluid d-md-block" id="mypage-wrapper">

        <!--  마이페이지 Header start -->
       <%@ include file="/WEB-INF/views/mypage/mypageHeader.jsp"%>

        <div class="ml-4 row" id="mypage-content-wrapper">
          <h2 class="mt-3">작성한 댓글</h2>
          
          <% if(rList.isEmpty()) { %>
          <div class="row justify-content-center col-md-12 jumbotron pr-4" id="1to1-question-wrapper-none"
            style="background-color: white; padding-left: 0 !important; height: 500px; margin-left: -380px;">
              <p class="text-muted" style="line-height: 300px;">작성하신 댓글이 없습니다 :)</p>
          </div>
          <% } else { %>

          <div class="row">
            <div class="container-fluid mt-4 col-md-10" id="table-container">
              <table class="table table-hover table-striped" id="list-table">
                <thead>
                    <tr>
                      <th style="width:12%">게시판</th>
                      <th style="width:73%">제목</th>
                      <th style="width:15%">작성일</th>
                    </tr>
                  </thead>
                  <tbody>
                  <% for (Reply reply : rList) { %>
                  	<tr>
                  	<td>
                  		<% switch(reply.getBoardCode()) {
                  		case 1 : %> <span class="badge badge-pill badge-danger mt-3">찾아요</span> <% break;
                  		case 2 : %> <span class="badge badge-pill badge-warning mt-3">봤어요</span> <% break;
                  		case 3 : %> <span class="badge badge-pill badge-success mt-3">분양합니다</span> <% break;
                  		case 4 : %> <span class="badge badge-pill badge-info mt-3">만남 그 후</span> <% break;
                  		case 5 : %> <span class="badge badge-pill badge-dark mt-3">자유게시판</span> <% break;
                  		}
                  		%>
                   </td>
                   <td>
                   <%= reply.getBoardTitle() %>
                      <p class="text-muted" id="my-reply">
                        <span style="font-size: em; color: Tomato;">
                          <i class="far fa-comment-dots"></i>
                        </span>
                   <%= reply.getCommContent() %>
                      </p>
                    </td>
                   
                   <td class="pt-4">
                   		<span>
                   		<%= reply.getCommModifyDt() %>
                   		</span>
                   </td>
                   <td style="display:none;"><%= reply.getBoardNo() %></td>
                   </tr>
                  <% } %>
                  
                  </tbody>
                  
              </table>

              <hr>



            </div>
          </div>

          <!-- 게시글 목록 번호-->
          <div class="row pt-5">
            <div class="col-md-12">

              <ul class="pagination justify-content-center mr-5 pr-5" style="margin:20px 0">
               <% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/commentList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/mypage/commentList?currentPage=<%= currentPage-1 %>">&lt;</a>
	                </li>
	                <% } %>
	                
	                <!-- 10개의 페이지 목록 -->
	                <% for(int p = startPage; p <= endPage; p++){ %>
	                	<% if(p == currentPage) { %>
		                <li>
		                    <a class="page-link"><%= p %></a>
		                </li>
	                	<% } else{ %>
                		<li>
	                    	<a class="page-link" href="<%= request.getContextPath() %>/mypage/commentList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/commentList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/commentList?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
              </ul>

            </div>

          </div>



		<% } %>





          <!-- Footer start -->
          <script>
          
          
          
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				
				
				var boardType = $(this).parent().children().eq(0).children().eq(0).text();
				
				// 게시판 종류에 따른 분류
				switch (boardType) {
				case "찾아요" : 
					var boardNo = $(this).parent().children().eq(3).text();
					location.href="<%= request.getContextPath() %>/findBoard/detail?no="+boardNo+"&currentPage=1";
					break;
				case "봤어요" : 
					var boardNo = $(this).parent().children().eq(3).text();
					location.href="<%= request.getContextPath() %>/seeBoard/detail?no="+boardNo+"&currentPage=1";
					break;
				case "분양합니다" : 
					var boardNo = $(this).parent().children().eq(3).text();
					location.href="<%= request.getContextPath() %>/adoptBoard/detail?no="+boardNo+"&currentPage=1";
					break;
				case "만남 그 후" : 
					var boardNo = $(this).parent().children().eq(3).text();
					location.href="<%= request.getContextPath() %>/review/reviewDetail?no="+boardNo+"&currentPage=1";
					break;
				case "자유게시판" : 
					var boardNo = $(this).parent().children().eq(3).text();
					location.href="<%= request.getContextPath() %>/free/view?no="+boardNo+"&currentPage=1";
					break;
				}
				
				
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
	</script>

          <!-- footer -->
		<div>
			<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		</div>

        </div>
      </div>





     <!-- 알림버튼 -->
	 <%@ include file="/WEB-INF/views/common/header.jsp"%>
    </div>




  </div>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>

</body>

</html>