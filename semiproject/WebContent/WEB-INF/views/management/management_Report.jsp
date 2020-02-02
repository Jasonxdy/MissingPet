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
                                  <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Member">회원관리</a></th>
                                  <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board">게시판관리</a></th>
                                  <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_QnA">QnA등록</a></th>
                                  <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Ask">1:1문의</a></th>
                                  <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Report">신고 관리</a></th>
                                </tr>
                              </thead>
                            </table>
            
                            <table class="row-md-10 table" style="height: 15px;">

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
                               <% if (reportBoardList.isEmpty()) { %>
									<tr>
										<td colspan="5">존재하는 신고글이 없습니다.</td>
									</tr>
								<% } else { %>
									
									<% for (int i = 0; i < reportBoardList.size(); i++) { %>                               
                                <tr>
                                  <td><%= reportBoardList.get(i).getReportNo() %></td>
                                  <td><%= reportBoardList.get(i).getReportTitle() %></td>
                                  <td><%= reportBoardList.get(i).getReportContent() %></td>
                                  <td><%= reportBoardList.get(i).getmemberId() %></td>
                                  <td>
                                        <button onclick="">해당 글 이동</button>
                                  </td>
                                </tr>
                                	<% } %>
								<% } %>
                              </tbody>
                            </table>
                        
                      	</div>
                      
                      
                      <!-- 검색창-->
                                       
                          <div id="searchTab">
                            <form class="col mt-3 " action="searchReport">
                              <div class="form-row align-items-center" >
                                <div class="col-auto my-5">
                                  <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
                                  <select name="searchKey" class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                    <option value="searchReportTitle" selected>제목</option>
                                    <option value="searchReportContent">내용</option>
                                  </select>
                                </div>
                                <div class="col" >
                                  <input name="searchValue" type="text" class=" form-control mr-sm-2" placeholder="검색어를 입력하세요.">
                                </div>

                                <div class="col">
                                  <button type="submit">검색</button>
                                </div>
 								 </div>
                              </form>
                                  
                            </div>
                          </div>
                  		</div>
                 	  </div>
                    </div>
                    
                </div>              
               </div>
                

			<%@include file="../common/footer.jsp" %>
			
    </body>
</html>