<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@page import="java.util.List, com.kh.semiproject.board.model.vo.BoardEH, com.kh.semiproject.board.model.vo.PageInfo"%>
<%@page import="com.kh.semiproject.free.model.vo.Free"%>


<%
	
	List<BoardEH> blist = (List<BoardEH>)request.getAttribute("blist");
	List<Free> flist = (List<Free>)request.getAttribute("flist");
	
PageInfo pInf = (PageInfo)request.getAttribute("pInf");
String searchKey = request.getParameter("searchKey");
String searchValue = request.getParameter("searchValue");

int listCount = pInf.getListCount();
int currentPage = pInf.getCurrentPage();
int maxPage = pInf.getMaxPage();
int startPage = pInf.getStartPage();
int endPage = pInf.getEndPage();
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
  

  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
  
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/copy2.css">

    
    

</head>

<body>


	<div class="container-fluid">
    <section class="row">
    
    
     <%@ include file="../common/sidebar.jsp"%>
    
          
          <div id="demo" class="w-100">
    
    <!--contents 시작-->

            <div class="col-md-12 mt-4" id="con">
          
            <div id="page-content-wrapper" >
              <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
                <div class="card-body">

                    <!-- 이 안에다가 작성하세요!!!!!!!! -->

            
            <div class="container">
              <div class="row">
            
                <section class="content">
                  <h2 id="title-top"><a href="#">자유게시판</a></h2>
                  <hr>
                  <div class="col-md-12 col-md-offset-2" id="panelwrap" >
                    <div class="panel panel-default">
                      <div class="panel-body">

                        
                       
                        <table class="table table-hover" id="list-table">
                          <thead>
                            <tr>
                              <th scope="col">NO</th>
                              <th scope="col">말머리</th>
                              <th scope="col">제목</th>
                              <th scope="col">작성자</th>
                              <th scope="col">조회</th>
                              <th scope="col">작성일</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <td><b>104</b></td>
                              <td><b>공지</b></td>
                              <td><b>미씽펫 오픈했습니다</b></td>
                              <td><b>관리자</b></td>
                              <td><b>1000</b></td>
                              <td><b>2020-01-01</b></td>
                            </tr>
                          
                           <% if(blist.isEmpty() && flist.isEmpty()) {%>
                           <tr>
	                		<td colspan="6">존재하는 글이 없습니다.</td>
	                	   </tr>
	                	  <%}else{%>
	                		  <%for(BoardEH board : blist) {%>
                            <tr>
                             <td><%=board.getBoardNo()%></td>
                              <%for(Free free : flist) {
                              if(board.getBoardNo() == free.getBoardNo()) {%>
                             <td><%=free.getFreeCategory()%></td>
                             <%} %>
                             <%} %>
                             <td><%=board.getBoardTitle()%></td>
                             <td><%=board.getMemId()%></td>
                             <td><%=board.getBoardCount()%></td>
                             <td><%=board.getBoardCreateDate()%></td>
                            </tr>
                            	<%} %>
	                	<%} %>
                          </tbody>
                        </table>
                        



                      </div><!--pannel종료-->
                    </div><!--pannel-default종료-->

                    <!--오른쪽 배너-->
                    <div id="right-ban">
                      <a href="#">
                        <img src="<%= request.getContextPath() %>/img/배너.PNG" alt="배너" width="190px" height="550px">
                      </a>
                    </div>


                  </div>


                  <!--하단-->
                  <div id="bottom-bar">
                  <form method="GET" action="search" id="searchForm">
                  <select name="searchKey" class="custom-select" id="custom-select" >
                     <option value="title">글제목</option>
	                 <option value="content">내용</option>
	                 <option value="titcont">제목+내용</option>
                </select>
                <input name="searchValue" id="text-tt" type="text">
                <button type="submit">검색</button>
                </form>
                
                
                 <script>
					$(function(){
						var searchKey = "<%= searchKey %>";
						var searchValue = "<%= searchValue %>";
						
						if(searchKey != "null" && searchValue != "null"){
							$.each($("select[name=searchKey] > option"), function(index, item){
								if($(item).val() == searchKey){
									$(item).prop("selected","true");
								} 
							});
							
							$("input[name=searchValue]").val(searchValue);
						}
							
					});
				</script>
                
                
                
                
                 <!-- 페이징바 -->
	            <div style="clear: both;" id="pagebar">
	            <ul class="pagination">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/free/list?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/free/list?currentPage=<%= currentPage-1 %>">&lt;</a>
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
	                    	<a class="page-link" href="<%= request.getContextPath() %>/free/list?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/free/list?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/free/list?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            </ul>
	        </div>
                
              <div id="bt-bt">
               <% if(loginMember != null && loginMember.getMemberGrade().equals("N")) {%>
              <button type="button" class="btn btn-primary" onclick="location.href = 'writeForm';">글쓰기</button>
             <%} %>
             </div>

                </div>


                </section>
                
              </div>
            </div> <!-- container 종료-->

            <!-- 이 안에다가 작성하세요!!!!!!!! -->
           </div>
          </div>
          </div>


          
          </div> <!-- #con 종료-->

            <!--contents 종료-->
    
    
     <%@ include file="../common/footer.jsp"%>
    </div>
   
   
     
     
       <%@ include file="../common/header.jsp"%>
    
     
     
     
     
     </section>
     
     </div>  <!-- .container-fluid 종료 -->
     
     
     
     <script>
     
		// 상세조회 기능     
		$(function(){
			$("#list-table td").click(function(){
				var boardNo = $(this).parent().children().eq(0).text();
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				location.href="<%= request.getContextPath()%>/free/view?no=" + boardNo + "&currentPage=" + <%= currentPage%>;
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	
	</script>
     
     
     
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>
     


</body>
</html>