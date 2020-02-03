<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page
	import="java.util.*, java.sql.*, com.kh.semiproject.AskBoard.model.vo.AskBoard, com.kh.semiproject.AskBoard.model.vo.Answer"%>

<%
	List<AskBoard> AskBoardList = (List<AskBoard>) request.getAttribute("AskBoardList");

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
				
                <%@ include file="../common/sidebar.jsp" %>

                  <!-- Content -->
                  
                  <div class="container-fluid row-md-10 mr-5 mt-5" style="width: 80%;">
	                <div id="page-content-wrapper mb-5">
	                  <div class="card bg-light shadow ml-5 mr-5">
	                    <div class="card-body">
                          <h5>&nbsp;&nbsp;&nbsp;관리자 페이지</h5>


                                 <!-- Content -->
                       <div class="row-md-12" id="manageheader" style="width:100%; height:850px; overflow:auto">
                        <div class="row-md-10" style="height: 720px;">
                        <table class="table">

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
							
								<form class="col" method="POST" action="searchAsk" style="border: 1px solid yellowgreen;">
									<div class="ml-5 form-row align-items-center">
										<div class="row-md-2 my-5">
											<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect"></label>
											<select name="searchKey" class="custom-select mr-sm-2"
												id="inlineFormCustomSelect">
												<option value="SearchAskTitle" selected>제목</option>
												<option value="SearchAskContent">내용</option>
											</select>
										</div>

										<div class="col-md-9" >
											<input name="searchValue" type="text" class=" form-control mr-sm-2" 
												placeholder="검색어를 입력하세요.">
										</div>
										<div>
											<button type="submit" class="btn btn-sm btn-outline-secondary">검색</button>
										</div>
									</div>
								</form>
                           
                              
                              
                            <table class="row-md-10 table">

                              <thead>
                                <tr>
                                <th scope="col"><!--  문의 번호 --></th>
                                <th scope="col">&nbsp;&nbsp;문의자</th>
                                  <th scope="col">&nbsp;&nbsp;제목</th>
                                  <th scope="col">&nbsp;내용</th>
                                  <th scope="col">&nbsp;&nbsp;등록일</th>
                                  <th scope="col">답장 여부</th>
                                  <th scope="col"> <!--  답장 창 --> </th>
                                </tr>
                              </thead>
                              
                              <tbody>
                              <% if (AskBoardList.isEmpty()) { %>
									<tr>
										<td colspan="5">존재하는 1:1 문의글이 없습니다.</td>
									</tr>
								<% } else { %>
									
									<% for (int i = 0; i < AskBoardList.size(); i++) { %>
                              	<tr>
                              		<td><%= AskBoardList.get(i).getAskNo() %></td>	
                              		<td><%= AskBoardList.get(i).getMemberId() %></td>
	                              	<td>
		                              	<span class="ContentLength">
		                              		<%= AskBoardList.get(i).getAskTitle() %>
		                              	</span>	
	                              	</td>	
	                              	<td>
		                              	<span class="ContentLength">
		                              		<%= AskBoardList.get(i).getAskContent() %>
		                              	</span>	
	                              	</td>
	                              	<td><%= AskBoardList.get(i).getAskModifyDt() %></td>	
	                              	<td><%= AskBoardList.get(i).getAskStatus() %></td>	
	                              	<td> <!-- 1:1 문의 답장 모달   -->
	                              		
							<a id="modal-<%= i %>" href="#modal-container-<%= i %>" role="button"
									class="btn btn-sm btn-outline-info reply" data-toggle="modal">답장하기</a>
										 
									<div class="modal fade" id="modal-container-<%= i %>" role="dialog"
                                        aria-labelledby="myModalLabel" aria-hidden="true" >
                                        <div class="modal-dialog" role="document" >
                                          <div class="modal-content" id="modal-renew">
                                             <form class="form-signin" method="POST" action="answer" >
                                            <div class="modal-header">
                                              <h5 class="modal-title" id="myModalLabel">1:1 문의 답변창 | <input type="text" style="width:28%" name="recievedAskMemberId" value="<%= AskBoardList.get(i).getMemberId() %>" readonly >님의 문의입니다.</h5>
                                              <button type="button" class="close" data-dismiss="modal">
                                                <span aria-hidden="true">×</span>
                                              </button>
                                            </div>
                                           <div class="modal-body" >
                                           
                                           
                                                <!-- 사용자가 남긴 질문 -->
                                                <div id="recievedQuestion" style="width:100% ">
                                                
                                                    <div>
                                                 	<p class="badge badge-pill badge-info" style="width:15%"> 문의 제목 </p>
                                                    <p style="width:70% height:10%" name="recievedAskTitle"><%= AskBoardList.get(i).getAskTitle() %></p>
                                                    </div>
                                              
                                               
                                               
                                                    <div> <!--  recievedAskContent 시작  -->
              										<p class="badge badge-pill badge-info" style="width:15%"> 문의 내용 </p>
                                                     <p style="height:30%" name="recievedAskContent"><%= AskBoardList.get(i).getAskContent() %></p>
                                                	</div> <!--  recievedAskContent  -->
                                                	
                                              </div> <!--  recievedQuestion /div -->
                                               
                                               
                                               
											<div> <!--  answerContent 시작 -->
													
													<%
														String answerContent = "답변을 입력해 주세요.";
														if( AskBoardList.get(i).getAnswerContent() != null){
															answerContent =  AskBoardList.get(i).getAnswerContent();
														}
													%>
													<p class="badge badge-pill badge-info" style="width:30%"> 아래 빈 칸에 답변을 입력해주세요. </p>
                                                    <textArea name="answerContent" cols="106" rows="9" style="resize:none"><%= answerContent %></textArea>
                                                </div> <!--  answerContent 끝  -->
                                                
                                                <input type="text" value= "<%= AskBoardList.get(i).getMemberId() %>" style="display:none" name="checkMemberId" id="checkMemberId">
                                             
                                                </div>
								
								            <div class="modal-footer">
								            <input type="text" style="width:4%" name="answerAskNo" value="<%= AskBoardList.get(i).getAskNo() %>">번 문의글에 답변을 등록하시겠습니까?
                                              <button class="btn btn-outline-danger " type="submit">등록하기</button>
                                              <button type="button" class="btn btn-outline-primary"
                                                data-dismiss="modal">Close</button>
											</div>
											
                                        	</form>      
                                            </div>															
										</div>
									</div>
								</div>
							
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
                             
                          
                <%@ include file="../common/footer.jsp" %>
                <!--   <script>
                  	$(".reply").on("click",function(){
                  		
                  		var askNo = $(this).parent().parent().children().eq(0).text();
                  		console.log(askNo);
                  		
                  		
                  		$.ajax({
                  			
                  			url: "management_Ask",
                  			data : {askNo:askNo},
                  			type : "GET",
                  			success : function(result){
                  				
                  			},
                  			error : function(){
                  				console.log("AJAX 통신 실패");
                  			}
                  		});
                  		
                  	});
                  
                  
                  
                  </script> -->
   




    </body>
</html>