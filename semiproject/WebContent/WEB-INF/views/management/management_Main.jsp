<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title> 미씽펫 - 관리자 페이지 </title>
               <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css" type="text/css">
            <link rel="stylesheet" href="<%= request.getContextPath() %>/css/management.css" type="text/css">
            <link href="https://fonts.googleapis.com/css?family=Song+Myung|Noto+Sans+KR|Do+Hyeon|Yeon+Sung|Nanum+Myeongjo|Sunflower:300&display=swap" rel="stylesheet">
 </head>
<body>
            <div class="container-fluid row-md-2" style="height: 1000px;">
		
                <%@ include file="../common/sidebar.jsp"%>

 
                  
                  <div class="container-fluid row-md-10 mr-5 mt-5" style="width:80%;">
	                <div id="page-content-wrapper mb-5 mt-5">
	                  <div class="card bg-light shadow ml-5 mt-5 mr-5 mb-5">
	                    <div class="card-body">
                          <h5>&nbsp;&nbsp;&nbsp;관리자 페이지</h5>

                            <table class="table" id="manage_table" style="width:100%;">
    
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
                              <pre style="height: 800px;">
                              	<!--  내용 입력 가능 -->

                              	
                              </pre>
                          </div>
                          </div>
                        </div>
                        </div>
            
               
                        </div> <!-- footer 고정 div -->
                        

          
   						<%@ include file="../common/footer.jsp"%>


</body>
</html>