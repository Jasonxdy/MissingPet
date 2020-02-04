<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.semiproject.board.model.vo.PageInfo, com.kh.semiproject.board.model.vo.Attachment"%>
<%@page import="com.kh.semiproject.board.model.vo.Animal"%>
<%@page import="com.kh.semiproject.seeBoard.model.vo.SeeBoard"%>
<%@page import="com.kh.semiproject.board.model.vo.BoardHJ"%>

<%
	List<BoardHJ> bList = (List<BoardHJ>)request.getAttribute("bList");
	List<Attachment> aList = (List<Attachment>)request.getAttribute("aList");
	List<SeeBoard> sList = (List<SeeBoard>)request.getAttribute("sList");
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
<title>봤어요 글목록</title>
</head>
<body>

<div class="container-fluid">
    <div class="row" id="row">
		<%@ include file="../common/sidebar.jsp"%>
		
		<div class="container-fluid mt-5 pl-5 pr-5 pb-3" style="margin-left:20rem">
              <div class="row card bg-light">
                  <div class="col-md-12">
                      <div class="container-fluid mt-3">
                          <div class="row">
                              <div class="col-md-12">
                                  <h2><b>봤어요</b>
                                  <img src="<%= request.getContextPath() %>/img/봤어요logo2.png" style="widht : 50px; height:50px; display:inline-block">
                                  </h2>
                                  <hr>
                              </div>
                          </div>
                      </div>
                  </div>
      
                  <div class="album p-3">
                    <div class="container-fluid">
    
                      <div class="row">
                      <% if(bList.isEmpty()){ %>
                      	<div class="col-md-12 mb-4">
                      		<p>존재하는 게시글이 없습니다.</p>
                      	</div>
                      <% }else{ %>
                      	<% for(BoardHJ board : bList){%>
                      	<div class="col-md-3 mb-4">
                            <div class="card shadow-sm h-100">   
                                <div class="card-body p-0 h-100" id="list-table">
                                	<div style="display:none;"><%= board.getBoardNo() %></div>
                                    <div class="" style="height: 13rem;">
                                        <a class="text-muted">
                                        	<%
                                        		String src = request.getContextPath()+"/resources/uploadImages/noImage.png";
                                        		for(Attachment file : aList ){
                                        			if(file.getBoardNo() == board.getBoardNo() && file.getFileLevel()==0 && file.getFileStatus().equals("N")){
                                        				src = request.getContextPath()+"/resources/uploadImages/"+file.getFileChangeName();
                                        			}
                                        		}
                                        	%>
                                            <img class="w-100 h-100" style="border-top-left-radius: 0.25em; border-top-right-radius: 0.25em;" src="<%= src %>">
                                        </a>
                                    </div>
    
                                    <div class="px-1">
                                        <tr>
                                        <%
                                        	for(SeeBoard seeBoard : sList){
                                        		if(seeBoard.getBoardNo() == board.getBoardNo()){
                                        			for(Animal animal : animalList){
                                        				if(animal.getAnimalCode() == seeBoard.getAnimalCode() && animal.getAnimalStatus().equals("N")){ %>
                                        				
                                        					
                                        			
                                            <td>
                                            <span><%= animal.getAnimalBreed() %></span>
                                            <% 
                                            	src = null;
                                            	if(animal.getAnimalGender().equals("M")){
                                            		src = request.getContextPath()+"/img/남자.png";
                                            	} else if(animal.getAnimalGender().equals("F")){
                                            		src = request.getContextPath()+"/img/여자.png";
                                            	} else {
                                            		src = request.getContextPath()+"/img/중성.png";
                                            	}
                                            %>
                                            
                                            <img
                                                src="<%= src %>"
                                                style="width: 1rem;height: 1rem;" alt="">
                                            </td>
                      								<% } %>
                      							<% } %>
                      	
                                            <td>
                                            <% String ctitle = board.getBoardTitle();
                                            	if(ctitle.length()>20){
                                            		ctitle = ctitle.substring(0, 19);
                                            	}
                                            %>
                                            <p class="mb-0" style="border-bottom: 1px solid rgba(0, 0, 0, 1);"><%= ctitle %></p>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td>
                                            <p class="mb-0"><%= seeBoard.getsBoardDate() %></p>
                                            </td>
                                            
                                            <%
                                            	String location[] = seeBoard.getsBoardLocation().split(",");
                                            %>
                                            
                                            <td>
                                            <p class="mb-0"><%= location[0]+" "+location[1] %></p>
                                            </td>
                                        </tr>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        
                        <% } %>
                        <% } %>
                        <% } %>
                      <% } %>
                      </div>
                    </div>
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
                        </form>
                    </div>
    
                    
                    <% if(loginMember != null) {%>
                    <div class="float-right m-2">
                        <button type="button" class="btn btn-primary" id="insertBtn" onclick="location.href = 'insertForm';">글쓰기</button>
                    </div>
	        		<% } %>
    
                </div>
    
    
                <div class="col-md-12">
                    <ul class="pagination justify-content-center">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/seeBoard/boardList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/seeBoard/boardList?currentPage=<%= currentPage-1 %>">&lt;</a>
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
	                    	<a class="page-link" href="<%= request.getContextPath() %>/seeBoard/boardList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/seeBoard/boardList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/seeBoard/boardList?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
	                
	            	</ul>
                </div>

      
      
              </div>
          </div>
		

		
		
		<%@ include file="../common/header.jsp"%>
		
	</div>
	<%@ include file="../common/footer.jsp"%>
</div>

<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table div").click(function(){
				/* var boardNo = $(this).parent().children().eq(0).text(); */
				var boardNo = $(this).parent().children().eq(0).text();
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				location.href="<%= request.getContextPath() %>/seeBoard/detail?no="+boardNo + "&currentPage="+<%= currentPage %>;
			});
			
			$("#list-table div").mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			});
			
		});
		
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
</body>
</html>