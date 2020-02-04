<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.board.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.semiproject.board.model.vo.Animal"%>
<%@page import="com.kh.semiproject.seeBoard.model.vo.SeeBoard"%>
<%@page import="com.kh.semiproject.member.model.vo.Member"%>  
<%@page import="com.kh.semiproject.board.model.vo.BoardHJ"%>
<%@page import="com.kh.semiproject.map.model.vo.Map"%>

<%
	BoardHJ board = (BoardHJ)request.getAttribute("board");
	SeeBoard seeBoard = (SeeBoard)request.getAttribute("seeBoard");
	Animal animal = (Animal)request.getAttribute("animal");
	Member member = (Member)request.getAttribute("member");
	ArrayList<Attachment> files = (ArrayList<Attachment>)request.getAttribute("files");
	Map map = (Map)request.getAttribute("map");
		
	String currentPage = request.getParameter("currentPage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봤어요 글읽기</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/copy4.css" type="text/css"/>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="row">
		<%@ include file="../common/sidebar.jsp"%>

<!--  -->
			<div class="container-fluid pl-5 pr-5 pb-3"
				style="min-height: 89.3vh; margin-left:20rem;">
				<div class="row card bg-light">
					<div class="col-md-12">
						<div class="container-fluid mt-3">
							<div class="row">
								<div class="col-md-12">
									<h3>봤어요(글읽기)</h3>
									<hr>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="card">
							<div class="card-body p-1 pl-2 pt-2">
								<span> <%= board.getBoardTitle() %> </span> &nbsp;&nbsp; <span
									class="float-right"> <%= board.getBoardModifyDate() %> &nbsp;&nbsp;&nbsp;&nbsp;
									조회 : <%= board.getBoardCount() %> &nbsp;&nbsp;
									<% if( request.getSession().getAttribute("loginMember") != null ) { %>
                        <button data-toggle="modal" data-target="#exampleModal" type="button" class="btn btn-secondary btn-sm ml-5">신고하기</button>
						<% } %>
								</span>
							</div>
						</div>
					</div>
					
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

					<div class="row">
						<div class="col-md-12">
							<div class="float-right">
								<% if(loginMember != null) {
									if((board.getBoardWriter().equals(loginMember.getMemberId())) || loginMember.getMemberGrade().equals("Y") ){%>
						
								<a href="updateForm?no=<%=board.getBoardNo() %>" class="btn btn-primary m-3">수정</a>
								<button type="button" class="btn btn-primary m-3" id="deleteBtn">삭제</button>
								<% } %>
								<% } %>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-md-8">
							<% if(files != null){ %>
							<div id="adoptCarousel" class="carousel slide p-2"
								data-interval="false" data-ride="carousel" style="height:35rem;">
								<!-- 부족한 영역 배경색 채움 ,data-interval="false" 슬라이드 정지 -->
								<ol class="carousel-indicators" style="margin-bottom: 1%;">
									<li data-target="#adoptCarousel" data-slide-to="0"
										class="active"></li>
									<% for(int i = 1 ; i< files.size() ; i++){ %>
									<li data-target="#adoptCarousel" data-slide-to="<%= i %>"></li>
									<% } %>
								</ol>
								<div class="carousel-inner" style="height:110%">
									<% 
										String src = request.getContextPath()+"/resources/uploadImages/noImage.png";
										for(Attachment file : files){
											src = request.getContextPath()+"/resources/uploadImages/"+file.getFileChangeName();
											if(file.getFileLevel()==0){
												%>
												<div class="carousel-item active h-100">
													<img src="<%= src %>" class="d-block w-100 h-100" alt="...">
												</div>
											<% }else {%>
												<div class="carousel-item h-100">
												<img src="<%= src %>" class="d-block w-100 h-100" alt="...">
												</div>
												
											<% } %>
										<% } %>
								</div>
								
								<a class="carousel-control-prev" href="#adoptCarousel"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> 
								<a class="carousel-control-next" href="#adoptCarousel"
									role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span> <span
									class="sr-only">Next</span>
								</a>
							</div>
							<% } else { %>
							<div id="adoptCarousel" class="carousel slide p-2"
								data-interval="false" data-ride="carousel" style="height:35rem;">
								<!-- 부족한 영역 배경색 채움 ,data-interval="false" 슬라이드 정지 -->
								<ol class="carousel-indicators" style="margin-bottom: 1%;">
									<li data-target="#adoptCarousel" data-slide-to="0"
										class="active"></li>
									<!-- <li data-target="#adoptCarousel" data-slide-to="1"></li> -->
								</ol>
								<div class="carousel-inner" style="height:110%">
												<div class="carousel-item active h-100">
													<img src="<%=request.getContextPath() %>/resources/uploadImages/noImage.png" class="d-block w-100 h-100" alt="...">
												</div>
								</div>
								
								<a class="carousel-control-prev" href="#adoptCarousel"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> 
								<a class="carousel-control-next" href="#adoptCarousel"
									role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span> <span
									class="sr-only">Next</span>
								</a>
							</div>
							
							<% } %>
								

						</div>




						<div class="col-md-4" id="right-tab">
							<table>
								<tr>
									<th width="100px;">등록인</th>
									<td><%= member.getMemberName() %></td>
								</tr>
								<tr>
									<th>발견일</th>
									<td><%= seeBoard.getsBoardDate() %></td>
								</tr>
								<tr>
									<th>연락처</th>
									<td><%= seeBoard.getsBoardPhone() %></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><%= member.getMemberPwd() %></td>
								</tr>
								<tr>
									<th>발견장소</th>
									<td><%= seeBoard.getsBoardLocation() %></td>
								</tr>
								<tr>
									<th>동물 종류</th>
									<td><%= animal.getAnimalType() %></td>
								</tr>
								<tr>
									<th>품종</th>
									<td><%= animal.getAnimalBreed() %></td>
								</tr>
								<tr>
								<tr>
								<%
									String gender = null;
									switch(animal.getAnimalGender()){
									case "M": gender = "수컷"; break;
									case "F": gender = "암컷"; break;
									case "N": gender = "중성화"; break;
									}
								%>
									<th>성별</th>
									<td><%= gender %></td>
								</tr>
							</table>
							<% if(request.getAttribute("map") != null) { %>
							<input id="latitude" name="latitude" style="display:none;" value="<%=map.getMapLatitude()%>">
							<input id="longitude" name="longitude" style="display:none;" value="<%=map.getMapLongitude()%>">
							<input id="mapAddress" type="textarea" name="mapAddress" value="<%=map.getMapAddress() %>" style="display:none;">
							<% } %>
							<div id="map">
								<%@ include file="/WEB-INF/views/map/detailMap.jsp"%>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12 p-5">
							<div class="card mb-2">
								<div class="card-body">
									<div><%= board.getBoardContent() %></div>
								</div>
							</div>
						</div>
					</div>






					<div class="row">

						<div class="col-md-12 p-5">

							<div id="comment-Wrapper" class="container">
								
							</div>
							<% if( request.getSession().getAttribute("loginMember") != null ) { %>
			                <div id="loadId" style="display:none;"><%= loginMember.getMemberId() %></div>
							<% } %>
							
							<div class="row">
								<div class="col-8 mr-5">
									<textarea id="commContent" style="resize: none; width: 100%;"></textarea>
								</div>
								<div class="col-2 ml-5">
									<button id="addComment" type="button" class="btn btn-primary btn-lg">댓글작성</button>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="text-center">
									<a href="boardList?currentPage=<%= currentPage %>" class="btn btn-primary m-3">목록으로</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../common/footer.jsp"%>
			</div>

			<%@ include file="../common/header.jsp"%>
	</div>
	
</div>
<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=<%=board.getBoardNo() %>";
		});
		
		$("#addComment").on("click", function(){
			var writer; // null 발생 예방을 위해서 아래에서 로그인 검사 실시
			var boardNo = <%= board.getBoardNo() %>;
			var content = $("#commContent").val();
			var email = "<%= member.getMemberPwd() %>";
			var title = "<%= board.getBoardTitle() %>";
			var commentTell = "<%= seeBoard.getsBoardCommentTell() %>"
			
				// 댓글 등록 시 해당 글 작성자가 댓글 알림 설정했는지 확인
				var boardWriter = "<%= board.getBoardWriter() %>";
				var alertContent = "<%= board.getBoardTitle() %>";
				var alertURL = "<%= request.getContextPath() %>" + "/seeBoard/detail?no=" + boardNo + "&currentPage=1";
			
			// 로그인 검사
			<% if(loginMember == null){ %>
				alert("로그인 후 이용해 주세요.");
				
			<% }else { %>
				writer = "<%= loginMember.getMemberId()%>";
				
				$.ajax({ // 서버와 비동기 통신?
					url: "insertComment", // url은 필수 속성!!
					type: "post",
					data: {writer: writer, 	// key는 ""가 포함된 문자열
						   content: content, boardNo: boardNo, boardWriter:boardWriter, email: email, title: title, commentTell: commentTell
						   , alertContent : alertContent, alertURL:alertURL},
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
		
		function selectcList(){
			var boardNo = <%= board.getBoardNo() %>;
			
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
						var $div = $("<div>").prop("class", "row ml-1 mt-2 mb-2").prop("id", "comment"); 
						var $divImgWriter1 = $("<div>").prop("class", "col-2 mt-2"); //이미지 담는 div
						var $divImgWriter2 = $("<div>").prop("class", "col-2 mt-2"); // 작성자+작성일 담는 div
						var $divContent = $("<div>").prop("class", "col-6 mt-2"); // 댓글 본문 div
						var $divButton = $("<div>").prop("class", "col-2 mt-2 text-center"); // 수정 삭제 담는 div
						var $uButton = $("<button>").prop("class", "btn btn-sm btn-primary").on("click", function(){
							$(this).parent().parent()
							.html("<textarea id='commentModify' class='m-2 pb-1' style='resize: none; width: 80%; height: 70px'></textarea>")
							.append("<button id='CMB' class='btn btn-sm btn-primary m-2 pb-1'>등록</button>");
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
						var $dButton = $("<button>").prop("class", "btn btn-sm mt-1 btn-primary").on("click", function(){
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
						if($loadId != null){
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
</script>

</body>
</html>