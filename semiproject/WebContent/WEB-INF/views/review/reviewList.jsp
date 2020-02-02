<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.review.model.vo.PageInfo"%>
<%@page import="com.kh.semiproject.review.model.vo.Review"%>
<%@page import="com.kh.semiproject.review.model.vo.Img"%>
<%@page import="java.util.List"%>
<%
	List<Review> rList = (List<Review>)request.getAttribute("rList");
	List<Img> iList = (List<Img>)request.getAttribute("iList");	

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
<title>만남 그 후</title>
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


              <div class="container">
                <div class="row">

                  <section class="content">
                    <h2 id="title-top"><a href="#">만남 그 후</a></h2>
                    <hr>
                    <div class="col-md-12 col-md-offset-2" id="panelwrap">
                      <div class="panel panel-default">
                        <div class="panel-body">



                          <div class="table-container">
                            <table class="table table-filter">
                              <tbody id="list-table">
                              <% if(rList.isEmpty()) { %>
                              	<tr>
                              		<td> <h2>존재하는 게시글이 없습니다.</h2>
                              	</tr>
                              <% } else { %>
                              	<% int rCount = 0; 
                              	   for(Review review : rList) { %>
                              	   
                                <tr>
                                  
                                  <%
                                    rCount++;
                                  	String src = request.getContextPath()+"/resources/uploadImages/noImage.png";
                                  	for(Img img : iList){
                                  		if(img.getBoardNo() == review.getBoardNo()){
                                  			src = request.getContextPath() + "/resources/uploadImages/" + img.getImgChangeName();
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
                                       
                                          <h4 class="title">
                                          	<%= review.getBoardTitle() %>
                                            <span class="pull-right pendiente"></span>
                                          </h4>
                                      
                                      	
                                          <% 
                                          	String content = review.getBoardContent();
                                          	int conLength = 0;
                                          	if(content.length()<45){
                                          		conLength = content.length();
                                          	} else conLength = 45;
                                          %>
                                          <div id="dummy<%=rCount %>" style="display: none;"><%= review.getBoardContent()%></div>
                                          <div class="summary<%=rCount%>"></div>
                                          
                                      </div>
                                    </div>
                                  </td>
                                  <td>
                                    <!-- <a href="#" class="star"> -->
                                      <div class="media-meta pull-right" id="table-r"> 작성자 : <%= review.getMemberId() %> | <%= review.getBoardModifyDate() %> | 조회 <%= review.getBoardCount() %></div>
                                    <!-- </a> -->
                                  </td>
                                  <td style="display: none;"><%= review.getBoardNo() %></td>
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


                    </div>


                    <!--하단-->
                    <div id="bottom-bar">
                    <form class="input-group" method="GET" action="searchList" id="searchForm">
                      <select class="custom-select" name="searchKey" style="font-size: 12px;">
                        <option value="title" selected>제목</option>
                        <option value="content">내용</option>
                        <option value="titcont">제목+내용</option>
                        <option value="writer">작성자</option>
                      </select>
                      <input id="text-tt" type="text" name="searchValue">
                      <button type="submit">검색</button>
                      </form>
                      <div id="num">
                        <ul class="pagination">
                          <% if(currentPage > 1) { %>
                         	 <li class="page-item">
                          	  	<a class="page-link" href="<%= request.getContextPath() %>/review/reviewList?currentPage=1">&laquo;</a>
                        	 </li>
	                		<!-- 이전으로(<) -->
	                         <li class="page-item">
	                   			<a class="page-link" href="<%= request.getContextPath() %>/review/reviewList?currentPage=<%= currentPage-1 %>">&lt;</a>
		                	 </li>
	                	   <% } %>
	                	   
	                	   <% for(int p = startPage; p<=endPage; p++) { %>
	                	   	<% if(p == currentPage) { %>
	                	   	
	                          <li class="page-item">
	                            <a class="page-link"><%= p %></a>
	                          </li>
	                        <% } else { %>
	                          <li class="page-item">
	                            <a class="page-link" href="<%= request.getContextPath() %>/review/reviewList?currentPage=<%= p %>"><%= p %></a>
	                          </li>
	                        <% } %>
	                      <% } %>
                          
                          <% if(currentPage < maxPage) { %>
                         	 <li class="page-item">
	                   	 		<a class="page-link" href="<%= request.getContextPath() %>/review/reviewList?currentPage=<%= currentPage+1 %>">&gt;</a>
	               			 </li>
                          <li class="page-item">
                            <a class="page-link" href="<%= request.getContextPath() %>/review/reviewList?currentPage=<%= maxPage %>">&raquo;</a>
                          </li>
                          <% } %>
                        </ul>
                      </div>
                      <% if(loginMember != null) { %>
                      <div id="bt-bt">
                        <button type="button" class="btn btn-primary"><a href="<%= request.getContextPath() %>/review/reviewInsert">글쓰기</a></button>
                      </div>
					 <% } %>
                    </div>


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
		/* console.log($("#dummy1").text());
		console.log($("#dummy1").text().length); */
		
		$("#list-table td").click(function(){
			var boardNo = $(this).parent().children().eq(4).text();
			var rNum = $(this).parent().children().eq(5).text();
			// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
			location.href="<%= request.getContextPath() %>/review/reviewDetail?no=" + boardNo + "&currentPage=" + <%= currentPage%>;
		
		}).mouseenter(function(){
			$(this).parent().css("cursor", "pointer");
		
		});
	})
</script>

</body>
</html>