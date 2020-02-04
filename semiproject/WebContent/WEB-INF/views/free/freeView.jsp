<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@page import="com.kh.semiproject.board.model.vo.BoardEH"%>
<%@page import="com.kh.semiproject.free.model.vo.Free"%>
<%@page import="com.kh.semiproject.board.model.vo.Img"%>
<%@page import="java.util.List"%>

<% BoardEH board = (BoardEH)request.getAttribute("board");
Free free = (Free)request.getAttribute("free");
List<Img> iList = (List<Img>)request.getAttribute("iList");	


String currentPage = request.getParameter("currentPage");


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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/copy6.css">


    
</head>
<body>

	<div class="container-fluid">
    <section class="row">
    
    
     <%@ include file="../common/sidebar.jsp"%>
    
          
          <div id="demo" class="w-100">
    
    <!--contents 시작-->

            <div class="col-md-12 mt-4" id="con">
            <div class="container-fluid mt-5 pl-5 pr-5 pb-3">
              <div class="row card bg-light" id="con">
                  <div class="col-md-12">
                      <div class="container-fluid mt-3">
                          <div class="row">
                              <div class="col-md-12">
                                 <h2 id="title-top"><a href="<%= request.getContextPath() %>/free/list">자유게시판</a></h2>
                                  <hr>
                              </div>
                          </div>
                      </div>
                  </div>
      
                  <div class="col-md-12" >
                      <div class="card"  id="bodytitle">
                          <div class="card-body p-1 pl-2 pt-2">
                          	<span class="freett1">
                                  <%=free.getFreeCategory() %>
                              </span>
                               &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                              <span class="freett2">
                                  <%=board.getBoardTitle() %>
                              </span>
                              &nbsp;&nbsp;
                              <span class="float-right">
                              <%=board.getMemId() %>
                              &nbsp;&nbsp;
                              <%=board.getBoardCreateDate() %> 
                              &nbsp;&nbsp; 
                          	    조회 : <%=board.getBoardCount() %>
                          	  &nbsp;&nbsp;
                                 <% if( request.getSession().getAttribute("loginMember") != null ) { %>
                      			  <button data-toggle="modal" data-target="#exampleModal" type="button" class="btn btn-outline-secondary btn-sm ml-5">신고하기</button>
								 <% } %>
                             <%-- 모달 시작 --%>
							<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			                <div class="modal-dialog modal-dialog-centered" role="document">
			                  <div class="modal-content">
			                    <div class="modal-header">
			                      <h5 class="modal-title" id="exampleModalLabel">신고하기</h5>
			                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			                        <span aria-hidden="true">&times;</span>
			                      </button>
			                    </div>
		                        <form action="<%= request.getContextPath()%>/review/report" method="post">
			                    	<div class="modal-body">
			                            <div class="form-group">
			                              <label for="recipient-name" class="col-form-label">제목</label>
			                              <input type="text" class="form-control" id="reportTitle" name="reportTitle">
			                              <% if( request.getSession().getAttribute("loginMember") != null ) { %>
			                              <input type="text" name="reportMemberId" value="<%= loginMember.getMemberId() %>" hidden>
			                              <% } %>
			                              <input type="text" name="reportBoardNo" value="<%= board.getBoardNo() %>" hidden>
			                            </div>
			                            <div class="form-group">
			                              <label for="message-text" class="col-form-label">내용</label>
			                              <textarea class="form-control" id="reportContent" name="reportContent"></textarea>
			                            </div>
			                    	</div>
				                    <div class="modal-footer">
				                      <button type="submit" class="btn btn-primary" onclick="return reportVil()">작성</button>
				                      <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				                    </div>
		                    	</form>
			                  </div>
			                </div>
			              </div>
						<%-- 모달 끝 --%>
                                  
                                  
                                  
                                  
                                  
                                  
                              </span>
                          </div>
                      </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="float-right">
                      <% if(loginMember != null && (board.getMemId().equals(loginMember.getMemberId()))) {%>
                      <a href="updateForm?no=<%=board.getBoardNo() %>" class="btn btn-warning id="updateBtn">수정</a>
                        <button type="button" class="btn btn-primary m-3 mr-5" id="deleteBtn">삭제</button>
                        	<% } %>
                      </div>
                    </div>
                  </div>
      

                  <!-- 이곳에 내용 넣어주시면 됩니다 -->


                       		 
                       		  <div id="pic" class="col-12 p-4">
	                     <%=board.getBoardContent() %> 
	                       <br>
                        <br>
                        <% if(board.getBoardUrl() != null){ %>
                         <% } %>  	
                      	<% if(iList != null) { %>
	                      	<% for(Img img : iList) { 
	                      	%>
	                      		<img style="display:inline-block; max-width:250px; max-height:250px;"  alt="" src="<%= request.getContextPath()%>/resources/uploadImages/<%= img.getImgChangeName() %>">
	                      	<% } %>
	                   <% } %> 
	                    <br>
                         <br>
	                    <div id="urlbox" style="background-color:white; border:1px solid lightgray; width:250px; height:30px;">
                         <%=board.getBoardUrl() %> 
                         </div>
                      </div>
                      


                  <div class="row">
      
                      <div class="col-md-12 p-5">


              </div>
          </div> <!-- container 종료-->

                       		    <div id="comment-Wrapper" class="container">
                  
             				   </div>
               					   <% if( request.getSession().getAttribute("loginMember") != null ) { %>
             						   <div id="loadId" style="display:none;"><%= loginMember.getMemberId() %></div>
									<% } %>
				
                				  <div class="row">
                				  <div class="col-9 mr-3">
                 				   <textarea id="commContent" style="resize: none; width: 100%; height: 100px"></textarea>
                 				 </div>
                 				 <div class="col-2 ml-3">
                 				   <button id="addComment" type="button" class="btn btn-primary ">등록하기</button>
                				  </div>
              					  </div>



                                  <div class="col-md-12">
                                    <div class="text-center" id="listback">
                                      <a href="list?currentPage=<%= currentPage %>" class="btn btn-primary">목록으로</a>
                                    </div>
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
     
  // 댓글 등록 버튼 동작
		$("#addComment").on("click", function(){
		var writer; // null 발생 예방을 위해서 아래에서 로그인 검사 실시
		var boardNo = <%= board.getBoardNo() %>;
		var content = $("#commContent").val();
		
		// 댓글 등록 시 해당 글 작성자가 댓글 알림 설정했는지 확인
		var boardWriter = "<%= board.getMemId() %>";
		var alertContent = "<%= board.getBoardTitle() %>";
		var alertURL = "<%= request.getContextPath() %>" + "/review/reviewDetail?no=" + boardNo + "&currentPage=1";
		
		// 로그인 검사
		<% if(loginMember == null){ %>
			alert("로그인 후 이용해 주세요.");
			
		<% }else { %>
			writer = "<%= loginMember.getMemberId()%>";
			
			$.ajax({ // 서버와 비동기 통신?
				url: "insertComment", // url은 필수 속성!!
				type: "post",
				data: {writer: writer, 	// key는 ""가 포함된 문자열
					   content: content, boardNo: boardNo, boardWriter:boardWriter, alertContent : alertContent, alertURL:alertURL},
				success: function(result){ // result에 서버의 응답이 담겨서 넘어온다
					if(!content.trim().length == 0){
						if(result>0){
							$("#commContent").val("");
							selectcList();
						}else{
							alert("댓글 등록 실패");
						}
					}else {
						alert("댓글 내용을 입력해주세요");
					}
				},
				error: function(){
					console.log("ajax 통신 실패");
				}
			});
		<% } %>
	});
		
		
		// 댓글 출력 함수
		function selectcList(){
			
			var boardNo = <%= board.getBoardNo()%>; // 글번호만 가져옴
			
			$.ajax({
				url: "selectCommentList",
				type: "POST",
				dataType: "json",
				data: {boardNo: boardNo},
				success: function(cList){
					var $commentWrapper = $("#comment-Wrapper"); // 같은 아이디의 div 태그 안에 댓글이 채워짐
					var $loadId = $("#loadId").text(); // 로그인된 memberId 가저오는 변수
					
					$commentWrapper.html(""); // 기존 내용 삭제
					
					$.each(cList, function(i){
						var $div = $("<div>").prop("class", "row pt-2 ml-1 mt-2 mb-2").prop("id", "comment"); 
						var $divImgWriter1 = $("<div>").prop("class", "col-2"); //이미지 담는 div
						var $divImgWriter2 = $("<div>").prop("class", "col-2"); // 작성자+작성일 담는 div
						var $divContent = $("<div>").prop("class", "col-6"); // 댓글 본문 div
						var $divButton = $("<div>").prop("class", "col-2 text-center"); // 수정 삭제 담는 div
						var $uButton = $("<button>").prop("class", "btn btn-sm btn-outline-warning").on("click", function(){
							$(this).parent().parent()
							.html("<textarea id='commentModify' class='m-1 pb-1' style='resize: none; width: 80%; height: 80px'></textarea>")
							.append("<button id='CMB' class='btn btn-sm btn-primary m-1 pb-1'>등록</button>");
							var commModifyContent;
							var commentNo = cList[i].commentNo;
							$("#CMB").click(function(){
								commModifyContent = $("#commentModify").val();
								$.ajax({
									url: "commentUpdate",
									type: "POST",
									dataType: "json",
									data: {commentNo: commentNo, commModifyContent: commModifyContent},
									success: function(result){
										if(result>0){
											selectcList();
										}else{
											alert("댓글 수정 실패");
										}
									}
								})
							});
						}).text("수정");
						var $dButton = $("<button>").prop("class", "btn btn-sm mt-1 btn-outline-primary").on("click", function(){
							var commentNo = cList[i].commentNo;
							if(confirm("정말 삭제하시겠습니까?")){
									$.ajax({
										url: "commentDelete",
										type: "POST",
										dataType: "json",
										data: {commentNo: commentNo},
										success: function(result){
											if(result>0){
												selectcList();
											}
										}
									});
							}
						}).text("삭제");
						var $img = $("<img>").prop("id", "imgdiv1")
						.prop("src", "<%= request.getContextPath()%>/resources/upProfileImage/" + cList[i].memberProImg);
						var $cWriter = $("<span>").html(cList[i].memberId);    // 작성자 값 cList에서 호출
						var $cDate = $("<span>").html(cList[i].commentModifyDt);
						var $cContent = $("<p>").html(cList[i].commentContent);
						//var $br = $("<br>");
						var $hr = $("<hr>");
						
						// 수업시간에 배운 내용
						//$div.append($cWriter).append($cDate).append($cContent);
						
						// append안에 append 사용
						if($loadId!= null){
							if(cList[i].memberId == $loadId){
								$div.append($divImgWriter1.append($img)).append($divImgWriter2.append($cWriter).append("<br>").append($cDate))
								.append($divContent.append($cContent)).append($divButton.append($uButton).append("<br>").append($dButton));;
							}
							
							 else{
								$div.append($divImgWriter1.append($img)).append($divImgWriter2.append($cWriter).append("<br>").append($cDate))
								.append($divContent.append($cContent)).append($divButton);;
							} 
						} 
						
						 else { 
							$div.append($divImgWriter1.append($img)).append($divImgWriter2.append($cWriter).append("<br>").append($cDate))
							.append($divContent.append($cContent)).append($divButton);;
						} 
						// 최종적으로 Wrapper에 모두 담은 div 추가
						$commentWrapper.append($div);
						// 태그의 경우 append는 태그 안에 추가함
						//$commentWrapper.append($div).append($hr);
					});
				},
				error: function(){
					console.log("ajax 통신 실패");
				}
			});
		      }
		
		 $(function(){
			 selectcList();
	         
	     
	      });
		      
		 
		 
			$("#deleteBtn").on("click",function(){
				if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=<%=board.getBoardNo() %>";
			});
     
     
     </script>
     




</body>
</html>