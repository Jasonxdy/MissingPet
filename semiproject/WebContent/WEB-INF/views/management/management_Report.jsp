<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@page
	import="java.util.*, java.sql.*, com.kh.semiproject.report.model.vo.Report"%>
	
   <% 
   List<Report> reportBoardList = (List<Report>)request.getAttribute("reportBoardList");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <title> 미씽펫 - 관리자 페이지 </title>

            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css" type="text/css">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/management.css" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Song+Myung|Noto+Sans+KR|Do+Hyeon|Yeon+Sung|Nanum+Myeongjo|Sunflower:300&display=swap" rel="stylesheet">
 <style>
.ContentLength {
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100px;
  height: 20px;
}


.table tr th a {
	color : white;
	font-size: 20px;
	font-family: 'Sunflower', sans-serif;

}



h5{
	font-family: 'Sunflower', sans-serif;
}
</style>     
        </head>
        <body>
        <div class="container-fluid row-md-2" style="height: 1000px;">
				
                <%@ include file="../common/sidebar.jsp"%>

 
                  
                  <div class="container-fluid row-md-10 mr-5 mt-5" style="width: 80%;">
	                <div id="page-content-wrapper mb-5 mt-5">
	                  <div class="card bg-light shadow ml-5 mt-5 mr-5 mb-5">
	                    <div class="card-body">
                          <h5>&nbsp;&nbsp;&nbsp;관리자 페이지</h5>


                          <!-- Content -->
                          <div class="row-md-12" id="manageheader" style="width:100%; height:850px; overflow:auto">
                        <div class="row-md-10" style="height: 720px;">
                            <table  class="table">

                                <thead>
									<tr>
										<th scope="col"><a class="badge badge-pill badge-danger" href="<%=request.getContextPath()%>/Management/management_Member">회원관리</a></th>
										<th scope="col"><a class="badge badge-pill badge-warning" href="<%=request.getContextPath()%>/Management/management_Board">게시판관리</a></th>
										<th scope="col"><a class="badge badge-pill badge-success" href="<%=request.getContextPath()%>/Management/management_QnA">QnA등록</a></th>
										<th scope="col"><a class="badge badge-pill badge-info" href="<%=request.getContextPath()%>/Management/management_Ask">1:1문의</a></th>
										<th scope="col"><a class="badge badge-pill badge-primary" href="<%=request.getContextPath()%>/Management/management_Report">신고관리</a></th>
									</tr>
								</thead>
                            </table>
            
          				   <!-- 검색창-->
                                           
                            <form class="col" action="searchReport" style="border: 1px solid yellowgreen;">
                              <div class="ml-5 form-row align-items-center" >
                                <div class="col-auto my-5">
                                  <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
                                  <select name="searchKey" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                    <option value="searchReportTitle" selected>제목</option>
                                    <option value="searchReportContent">내용</option>
                                  </select>
                                </div>
                                <div class="col-md-9" >
                                  <input name="searchValue" type="text" class=" form-control mr-sm-2" placeholder="검색어를 입력하세요.">
                                </div>

                                <div class="col">
                                  <button type="submit">검색</button>
                                </div>
 								 </div>
                              </form>
                                 
            
                            <table class="row-md-10 table">

                              <thead>
                                <tr>
                                  <th scope="col"><!-- 신고글 번호 --></th>
                                  <th scope="col">&nbsp;&nbsp;제목</th>
                                  <th scope="col">&nbsp;내용</th>
                                  <th scope="col">신고자</th>
                                  <th scope="col"> <!-- 관리 탭 --> </th>
                                </tr>
                              </thead>
                              
                              <tbody>
                               <%	 String boardName = ""; 
                               if (reportBoardList.isEmpty()) { %>
									<tr>
										<td colspan="5">존재하는 신고글이 없습니다.</td>
									</tr>
								<% } else { %>
									
									<% for (int i = 0; i < reportBoardList.size(); i++) { %>                               
                                <tr>
                                  <td id="<%= reportBoardList.get(i).getBoardNo() %>"><%= reportBoardList.get(i).getReportNo() %></td>
                                  
                                  <td><%= reportBoardList.get(i).getReportTitle() %></td>
                                  <td><%= reportBoardList.get(i).getReportContent() %></td>
                                  <td><%= reportBoardList.get(i).getmemberId() %></td>
                                  <td>
                                  		<%
                                  		switch(reportBoardList.get(i).getBoardCode()) {
                                  		case 1 : boardName = "/findBoard/detail"; break;
                                  		case 2 : boardName = "/seeBoard/detail"; break;
                                  		case 3 : boardName = "/adoptBoard/detail"; break;
                                  		case 4 : boardName = "/review/reviewDetail"; break;
                                  		case 5 : boardName = "/free/view"; break;
                                  		}
                                  		%>
                                        <button id="moveBoard" class="btn btn-sm btn-secondary" value="<%=boardName%>">해당 글 이동</button>
                                  </td>
                                </tr>
                                	<% } %>
								<% } %>
                              </tbody>
                            </table>
                        
                      	</div>
                      
                      
                     
                          </div>
                  		</div>
                 	  </div>
                    </div>     
                </div>              
               </div>
                

			<%@include file="../common/footer.jsp" %>
			 
			
	<script>
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$(document).on("click", "#moveBoard", function(){
				var boardNo = $(this).parent().parent().children().eq(0).prop("id");
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				var boardName = $(this).val();
				
				location.href="<%= request.getContextPath()%>" + boardName + "?no=" +boardNo + "&currentPage=1";
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	</script>
    </body>
</html>