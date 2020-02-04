<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List, com.kh.semiproject.board.model.vo.PageInfo, com.kh.semiproject.board.model.vo.Attachment"%>
<%@page import="com.kh.semiproject.board.model.vo.Animal"%>
<%@page import="com.kh.semiproject.findBoard.model.vo.FindBoard"%>
<%@page import="com.kh.semiproject.board.model.vo.BoardHJ"%>

<%
	List<BoardHJ> bList = (List<BoardHJ>)request.getAttribute("bList");
	List<Attachment> aList = (List<Attachment>)request.getAttribute("aList");
	List<FindBoard> fList = (List<FindBoard>)request.getAttribute("fList");
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
                                  <h3>찾아주세요</h3>
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
                                        	for(FindBoard findBoard : fList){
                                        		if(findBoard.getBoardNo() == board.getBoardNo()){
                                        			for(Animal animal : animalList){
                                        				if(animal.getAnimalCode() == findBoard.getAnimalCode() && animal.getAnimalStatus().equals("N")){ %>
                                        				
                                        					
                                        			
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
                                            <p class="mb-0" style="border-bottom: 1px solid rgba(0, 0, 0, 1);"><%= board.getBoardTitle() %></p>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td>
                                            <p class="mb-0"><%= findBoard.getfBoardDate() %></p>
                                            </td>
                                            
                                            <%
                                            	String location[] = findBoard.getfBoardLocation().split(",");
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
    					
                        <!-- <div class="col-md-3 mb-4">
                            <div class="card shadow-sm h-100">   
                                <div class="card-body p-0 h-100">
                                    <div class="" style="height: 60;">
                                        <a class="text-muted">
                                            <img class="w-100 h-100" style="border-top-left-radius: 0.25em; border-top-right-radius: 0.25em;" src="http://cdn.ppomppu.co.kr/zboard/data3/2013/0108/1357646773_Animal__Samoyed__Husky__Wallpaper_%281920X1080%29.jpg">
                                        </a>
                                    </div>
    
                                    <div class="px-1">
                                        <tr>
                                            <td>
                                            <span>믹스견</span>
                                            <img
                                                src="http://cdn.ppomppu.co.kr/zboard/data3/2013/0108/1357646773_Animal__Samoyed__Husky__Wallpaper_%281920X1080%29.jpg"
                                                style="width: 1rem;height: 1rem;" alt="">
                                            </td>
                                            <td>
                                            <p class="mb-0" style="border-bottom: 1px solid rgba(0, 0, 0, 1);">글제목</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            <p class="mb-0">2019-12-09</p>
                                            </td>
                                            <td>
                                            <p class="mb-0">서울 을지로입구역</p>
                                            </td>
                                        </tr>
                                    </div>
                                </div>
                            </div>
                        </div>  -->                      
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
                        <!-- <ul class="pagination justify-content-center">
                            <li class="page-item">
                            <a class="page-link" href="#">Previous</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">1</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">2</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">3</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">4</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">5</a>
                            </li>
                            <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                            </li>
                        </ul> -->
                    <ul class="pagination justify-content-center">
	            	<% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/findBoard/boardList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/findBoard/boardList?currentPage=<%= currentPage-1 %>">&lt;</a>
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
	                    	<a class="page-link" href="<%= request.getContextPath() %>/findBoard/boardList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/findBoard/boardList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/findBoard/boardList?currentPage=<%= maxPage %>">&gt;&gt;</a>
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
				location.href="<%= request.getContextPath() %>/findBoard/detail?no="+boardNo + "&currentPage="+<%= currentPage %>;
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