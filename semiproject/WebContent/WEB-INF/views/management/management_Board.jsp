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
          <style>
          
.table tr th a {
	color : white;
	font-size: 20px;
	font-family: 'Sunflower', sans-serif;

}

#boardList{
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
                            
                         <table id="boardList">
                            <thead>
                              <tr>
                               <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board_Find">찾아주세요 게시판 </a> &nbsp;|&nbsp;</th>
                                <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board_See">봤어요 게시판 </a> &nbsp;|&nbsp; </th>
                                <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board_Adopt">분양합니다 게시판 </a> &nbsp;|&nbsp;</th>
                                <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board_Meeting">만남 그 후 게시판 </a> &nbsp;|&nbsp; </th>
                                <th scope="col"><a href="<%= request.getContextPath() %>/Management/management_Board_Free">자유게시판 </a></th>
                              </tr>
                            </thead>
             			</table>

						
							<pre style="height: 900px">
							<!--  내용 입력 가능 -->
							</pre>
				
                           
                          </div>
                        


                        
                     </div>
                     
                    </div>
                     
                    </div>
       
                </div>              
                

          </div>
        
        <%@ include file="../common/footer.jsp" %>


</body>
</html>