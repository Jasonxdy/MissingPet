<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.review.model.vo.Review"%>
<%@page import="com.kh.semiproject.review.model.vo.Img"%>
<%@page import="java.util.List"%>
<%
	Review review = (Review)request.getAttribute("review");
	List<Img> imgs = (List<Img>)request.getAttribute("imgs");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>만남 그 후 글읽기</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/ReviewDetail.css" type="text/css"/>
</head>
<body>
<div class="container-fluid">
    <section class="row">
    
      <!-- 사이드바 -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>
		
		<div id="demo" class="w-100">
		
	     <div class="col-md-10 mt-4" id="con">

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
                      
                      <div id="title" style="background-color: lightgray;">
                        
                        <p><span style="background-color: beige; height:100%; display:inline-block;">제목: &nbsp;&nbsp;</span><%= review.getBoardTitle() %></p>
                      </div>
                      <div>
                        글번호: <span id="no"><%= review.getBoardNo() %></span> &nbsp;&nbsp;&nbsp;&nbsp;
                        글쓴이 : <lable for="memid"><%= review.getMemberId() %></lable><input id="memid" name="memid" type="text" value="<%= review.getMemberId() %>" style="display:none;" readonly> &nbsp;&nbsp;&nbsp;&nbsp;
                        날짜 : <%= review.getBoardModifyDate() %>&nbsp;&nbsp; &nbsp;&nbsp;
                        조회 : <%= review.getBoardCount() %> &nbsp;&nbsp;&nbsp;&nbsp;
                        <% if( request.getSession().getAttribute("loginMember") != null ) { %>
                        <button data-toggle="modal" data-target="#exampleModal" type="button" class="btn btn-secondary btn-sm ml-5">신고하기</button>
						<% } %>
                        <button id="list" type="button" class="btn btn-secondary btn-sm float-right">목록</button>
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
			                              <input type="text" name="reportBoardNo" value="<%= review.getBoardNo() %>" hidden>
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

                      </div>

                      <div id="pic" class="col-12 p-4">
                      	<% if(imgs != null) { %>
	                      	<% for(Img img : imgs) { %>
	                      		<img style="display: block; max-width:400px;"  alt="" src="<%= request.getContextPath()%>/resources/uploadImages/<%= img.getImgChangeName() %>">
	                      	<% } %>
	                   <% } %>   	
	                      	<%= review.getBoardContent() %>
                      </div>
                      <div class="row">
                        <div class="col-8"></div>
                        <div class="col-4 text-center" >
                        <% if( request.getSession().getAttribute("loginMember") != null ) { %>
	                        <%if(loginMember.getMemberId().equals(review.getMemberId()) || loginMember.getMemberGrade().equals("Y")) { %>
	                          <button id="update" type="submit" class="btn btn-primary">글수정</button>
	                          <button id="delete" type="submit" class="btn btn-primary">글삭제</button>
	                        <% } %>
                        <% } %>
                        <!--  request.getAttribute("loginMember") != null -->
                        <!-- loginMember.getMemberId().equals(review.getMemberId()) || loginMember.getMemberGrade().equals("Y" -->
                        </div>
                      </div>

                    </div>

                  </section>

                </div>
              </div> <!-- container 종료-->

                <div id="comment-Wrapper" class="container">
                  
                  
                </div>
                <% if( request.getSession().getAttribute("loginMember") != null ) { %>
                <div id="loadId" style="display:none;"><%= loginMember.getMemberId() %></div>
				<% } %>
				
                <div class="row">
                  <div class="col-8 mr-3">
                    <textarea id="commContent" style="resize: none; width: 100%; height: 100px"></textarea>
                  </div>
                  <div class="col-2 ml-3 mt-2">
                    <button id="addComment" type="button" class="btn btn-primary ">등록하기</button>
                  </div>
                </div>
             
              <!-- 이 안에다가 작성하세요!!!!!!!! -->
            </div>
          </div>
        </div>
      </div> <!-- #con 종료-->


		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		</div>
        <!-- 웹페이지 알림 -->
		<%@ include file="/WEB-INF/views/common/header.jsp"%>
    </section>
</div>

<script>
	$("#list").click(function(){
		location.href="<%= request.getContextPath()%>/review/reviewList"
	});
	$("#delete").click(function(){
		var boardNo = $("#no").text();
		var writer = $("#memid").val();
		if(window.confirm("정말 삭제하시겠습니까?")){
			location.href="<%= request.getContextPath()%>/review/reviewDelete?no=" + boardNo + "&memid=" + writer;
		}
	});
	$("#update").click(function(){
		var boardNo = $("#no").text();
		location.href="<%= request.getContextPath()%>/review/reviewUpdate?no=" + boardNo;
	});
	
	
	
	$("#addComment").on("click", function(){
		var writer; // null 발생 예방을 위해서 아래에서 로그인 검사 실시
		var boardNo = <%= review.getBoardNo() %>;
		var content = $("#commContent").val();
		
		// 댓글 등록 시 해당 글 작성자가 댓글 알림 설정했는지 확인
		var boardWriter = "<%= review.getMemberId() %>";
		
		// 로그인 검사
		<% if(loginMember == null){ %>
			alert("로그인 후 이용해 주세요.");
			
		<% }else { %>
			writer = "<%= loginMember.getMemberId()%>";
			
			$.ajax({ // 서버와 비동기 통신?
				url: "insertComment", // url은 필수 속성!!
				type: "post",
				data: {writer: writer, 	// key는 ""가 포함된 문자열
					   content: content, boardNo: boardNo, boardWriter:boardWriter},
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
		var boardNo = <%= review.getBoardNo() %>;
		
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
	
	function reportVil(){
		if($("#reportTitle").val().trim().length == 0){
			alert("제목을 입력해주세요");
			$("#reportTitle").focus();
			return false;
		}
		if($("#reportContent").val().trim().length == 0){
			alert("내용을 입력해주세요");
			$("#reportContent").focus();
			return false;
		}
	}
	
</script>



</body>
</html>