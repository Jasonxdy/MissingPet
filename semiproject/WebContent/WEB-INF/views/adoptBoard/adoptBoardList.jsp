<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.semiproject.board.model.vo.PageInfo, com.kh.semiproject.board.model.vo.Attachment"%>
<%@page import="com.kh.semiproject.board.model.vo.Animal"%>
<%@page import="com.kh.semiproject.adoptBoard.model.vo.AdoptBoard"%>   
<%@page import="com.kh.semiproject.board.model.vo.BoardHJ"%> 
    
<%
	List<BoardHJ> bList = (List<BoardHJ>)request.getAttribute("bList");
	List<Attachment> aList = (List<Attachment>)request.getAttribute("aList");
	List<AdoptBoard> adoptList = (List<AdoptBoard>)request.getAttribute("adoptList");
	List<Animal> animalList = (List<Animal>)request.getAttribute("animalList");
	
	
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
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/ReviewList.css" type="text/css"/>
</head>
<body>

<div class="container-fluid">

    <section class="row">
    
      <!-- 사이드바 -->
      <%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

      <div id="demo" class="w-100">
        <!-- 컨텐츠 시작 -->
        <div class="col-md-12 mt-4" id="con">

        <div id="page-content-wrapper">
          <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
            <div class="card-body">

              <!-- 이 안에다가 작성하세요!!!!!!!! -->


              <div class="container" style="margin-left:20rem;">
                <div class="row">

                  <section class="content">
                    <h2 id="title-top"><a href="#">분양합니다</a></h2>
                    <hr>
                    <div class="col-md-12 col-md-offset-2" id="panelwrap">
                      <div class="panel panel-default">
                        <div class="panel-body">



                          <div class="table-container">
                            <table class="table table-filter">
                              <tbody id="list-table">
                              <% if(bList.isEmpty()) { %>
                              	<tr>
                              		<td> <h2>존재하는 게시글이 없습니다.</h2>
                              	</tr>
                              <% } else {
                            	for(BoardHJ board : bList){
                              	 int rCount = 0; %>
                              	   
                              	   
                                <tr>
                                  
                                  <%
                                    rCount++;
                                  	String src = request.getContextPath()+"/resources/uploadImages/noImage.png";
                                  	for(Attachment file : aList){
                                  		if(file.getBoardNo() == board.getBoardNo()){
                                  			src = request.getContextPath() + "/resources/uploadImages/" + file.getFileChangeName();
                                  		}
                                  	}
                                  %>
                                 	 <td class="d-none d-lg-block" style="width:150px">
                                      <img 
                                        src="<%= src %>"
                                        alt="img1" width="150px" height="100px">
                                  	 </td>
                                    <td>
                                    </td>
                                  <td class="pl-4">
                                    <div class="media">
                                     <a href="#" class="pull-left">
                                     </a>
                                      <div class="media-body">
                                      	<% for(AdoptBoard adoptBoard : adoptList) { 
                                      			if(adoptBoard.getBoardNo() == board.getBoardNo()) {%>
                                       	<div> <%= adoptBoard.getaBoardDone()   %> </div>
                                       	<div> <%= adoptBoard.getaBoardLocation() %></div>
                                       	<% }
                                      		}%>
                                          <h4 class="title">
                                          	<%= board.getBoardTitle() %>
                                            <span class="pull-right pendiente"></span>
                                          </h4>
                                      
                                      	
                                          <% 
                                          	String content = board.getBoardContent();
                                          	int conLength = 0;
                                          	if(content.length()<45){
                                          		conLength = content.length();
                                          	} else conLength = 45;
                                          %>
                                          <div id="dummy<%=rCount %>" style="display: none;"><%= board.getBoardContent()%></div>
                                          <div class="summary<%=rCount%>"></div>
                                          
                                      </div>
                                    </div>
                                  </td>
                                  <td>
                                    <!-- <a href="#" class="star"> -->
                                      <div class="media-meta pull-right" id="table-r"> 작성자 : <%= board.getBoardWriter() %> | <%= board.getBoardModifyDate() %> | 조회 <%= board.getBoardCount() %></div>
                                    <!-- </a> -->
                                  </td>
                                  <td style="display: none;"><%= board.getBoardNo() %></td>
                                </tr>
							<% } %>
							<% } %>
                              </tbody>
                            </table>
                          </div>

                        </div>
                        <!--pannel종료-->
                      </div>
                      <!--pannel-default종료-->

                      <!--오른쪽 배너-->
                      <div id="right-ban" class="">
                        <a href="#">
                          <img src="<%= request.getContextPath() %>/img/banner.PNG" alt="배너" width="190px" height="550px">
                        </a>
                      </div>
                      
                      <div class="col-md-12">
                    <div class="float-left m-2">
                        <form class="input-group" method="GET" action="searchList" id="searchForm">
                            <select class="form-control label" name="searchKey">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="titcont">제목+내용</option>
                                <option value="writer">작성자</option>
                            </select>
                            <input type="text" name="searchValue">
                            <button class="btn btn-primary">검색</button>
                            <input type="hidden" name="doneCheck1" value="N">
                            <input type="checkbox" name="doneCheck1" id="doneCheck1" value="Y" checked>분양중
                            <input type="hidden" name="doneCheck2" value="N">
                            <input type="checkbox" name="doneCheck2" id="doneCheck2" value="Y" checked>분양완료
                        </form>
                    </div>
                    
                    <script>
	                    $(function(){
		            		var searchKey = "<%= searchKey %>";
		            		var searchValue = "<%= searchValue %>";
		            		
		            		if(searchKey != "null" && searchValue != "null"){
		            			// 검색한 경우
		            			
		            			$.each( $("select[name=searchKey] > option") , function(index, item){
		            					// $(item) : 현재 접근 요소
		            				if( $(item).val() == searchKey ){
		            					$(item).prop("seleted", "true");
		            				}
		            			});
		            			$("input[name=searchValue]").val(searchValue);
		            			
		            		} 
		            	});
                    </script>
    
                    
                    <% if(loginMember != null) {%>
                    <div class="float-right m-2">
                        <button type="button" class="btn btn-primary" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
                    </div>
	        		<% } %>
    
                </div>
    

                    </div>
                    <!--하단-->
                    <!-- 검색 페이지 글쓰기 하단  시작 -->
                <div class="col-md-12">
                    <ul class="pagination justify-content-center">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/adoptBoard/boardList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/adoptBoard/boardList?currentPage=<%= currentPage-1 %>">&lt;</a>
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
	                    	<a class="page-link" href="<%= request.getContextPath() %>/adoptBoard/boardList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/adoptBoard/boardList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/adoptBoard/boardList?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            	</ul>
                </div>
                <!-- 검색 페이지 글쓰기 하단  종료 -->
                    


                  </section>
				
                </div>
                
              </div> <!-- container 종료-->

              <!-- 이 안에다가 작성하세요!!!!!!!! -->
            </div>
            
          </div>
          
          
        </div>
        



      </div> <!-- #con 종료-->
        <!-- 컨텐츠 끝 -->
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
      </div>


      <!-- 웹페이지 알림 -->
      <%@ include file="/WEB-INF/views/common/header.jsp"%>
      

    </section>
</div>
<script>
	$(function(){
		$(".summary1").text($("#dummy1").text().slice(0, 45));
		$(".summary2").text($("#dummy2").text().slice(0, 45));
		$(".summary3").text($("#dummy3").text().slice(0, 45));
		$(".summary4").text($("#dummy4").text().slice(0, 45));
		$(".summary5").text($("#dummy5").text().slice(0, 45));
		if($("#dummy1").text().length>45){
			$(".summary1").text($(".summary1").text() + "...");
		}
		if($("#dummy2").text().length>45){
			$(".summary2").text($(".summary2").text() + "...");
		}
		if($("#dummy3").text().length>45){
			$(".summary3").text($(".summary3").text() + "...");
		}
		if($("#dummy4").text().length>45){
			$(".summary4").text($(".summary4").text() + "...");
		}
		if($("#dummy5").text().length>45){
			$(".summary5").text($(".summary5").text() + "...");
		}
		
		$("#list-table td").click(function(){
			var boardNo = $(this).parent().children().eq(4).text();
			var rNum = $(this).parent().children().eq(5).text();
			// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
			location.href="<%= request.getContextPath() %>/adoptBoard/detail?no="+boardNo + "&currentPage="+<%= currentPage %>;
		
		}).mouseenter(function(){
			$(this).parent().css("cursor", "pointer");
		
		});
	})
</script>

</body>
</html>