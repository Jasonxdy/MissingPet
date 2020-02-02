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
<title>만남 그 후 글쓰기</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/ReviewInsert.css" type="text/css" charset="utf-8"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<script src="<%= request.getContextPath() %>/daumeditor/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>

<div class="container-fluid">

    <section class="row">
    
      <!-- 사이드바 -->
      <%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

      <div id="demo" class="">
        <!-- 컨텐츠 시작 -->
        <div class="col-md-12 mt-4" id="con">

        <div id="page-content-wrapper">
          <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
            <div class="card-body">
              <div class="container">
                <div class="row">

                  <section class="content">
                  <form name="tx_editor_form" id="tx_editor_form" enctype="multipart/form-data"
                  action="<%=request.getContextPath()%>/review/update" method="post" accept-charset="utf-8">
                    <h2 id="title-top"><a href="#">만남 그 후 글쓰기</a></h2>
                    <hr>
                    <div class="col-md-12 col-md-offset-2" id="panelwrap">

                      <div>
                        
                        <label for="title"> 제목 :</label>
                        &nbsp;
                        <input name="title" id="title" type="text" size="90" value="<%= review.getBoardTitle()%>">
                      </div>
                      <div id="pic">
						
						<%@ include file="/daumeditor/reviewUpdateEditor.jsp"%>
						
                      </div>
                      <textarea id="content" style="display:none;"><%= review.getBoardContent() %></textarea>
                      <input style="display:none;" name="no" value="<%=review.getBoardNo()%>">
                      <div class="row">
                        <label><b>영상 첨부</b></label>
                        &nbsp;&nbsp;
                        <input name="reviewUrl" type="text" size="30" value="<%= review.getBoardUrl() %>" style="height: 1.5rem !important;">
                        &nbsp;&nbsp;&nbsp;
                       
                        
                        <div class="form-inline mb-2">
							<label class="input-group-addon mr-3 insert-label">썸네일</label>
							<div class="boardImg" id="titleImgArea">
								<img id="titleImg" width="50" height="50">
							</div>
						</div>

						<div class="form-inline mb-2">
							<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
							<div class="mr-2 boardImg" id="contentImgArea1">
								<img id="contentImg1" width="50" height="50">
							</div>
							<div class="mr-2 boardImg" id="contentImgArea2">
								<img id="contentImg2" width="50" height="50">
							</div>
							<div class="mr-2 boardImg" id="contentImgArea3">
								<img id="contentImg3" width="50" height="50">
							</div>
							<div class="mr-2 boardImg" id="contentImgArea4">
								<img id="contentImg4" width="50" height="50">
							</div>
						</div>
                         <div id="fileArea">
							<!-- multiple 속성
								- input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시
							 -->
							<input type="file" id="img1" accept="image/*"
								name="img1" onchange="LoadImg(this,1)"> 
							<input type="file" id="img2" accept="image/*"
								name="img2" onchange="LoadImg(this,2)">
							<input type="file" id="img3" accept="image/*"
								name="img3" onchange="LoadImg(this,3)">
							<input type="file" id="img4" accept="image/*"
								name="img4" onchange="LoadImg(this,4)">
								<input type="file" id="img5" accept="image/*"
								name="img5" onchange="LoadImg(this,5)">
						</div>
                      </div>
                      
                      <div id="top-bt">
                        <button type="submit" class="btn btn-primary mr-5" onclick='return saveContent();'>작성완료</button>
                        <button type="button" class="btn btn-primary ml-5">취소</button>
                      </div>
                      
                    </div>
                    </form>
                  </section>
                </div>
              </div> <!-- container 종료-->
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
		loadContent();
	});
//이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
	$(function () {
		// 파일 선택 버튼이 있는 영역을 보이지 않게 함
		$("#fileArea").hide();
		
		// 이미지 영역 클릭 시 파일 첨부창 띄우기
		$("#titleImgArea").click(function(){
			$("#img1").click();
		});
		$("#contentImgArea1").click(function(){
			$("#img2").click();
		});
		$("#contentImgArea2").click(function(){
			$("#img3").click();
		});
		$("#contentImgArea3").click(function(){
			$("#img4").click();
		});
		$("#contentImgArea4").click(function(){
			$("#img5").click();
		});
		
	});
	
	// 아이디값으로 input 자리에 imgLevel로 배치
	$(function () {
		<% if(request.getAttribute("imgs") != null) { %>
			<% int i = 0; %>
			<% for(Img img : imgs) { %>
			<% 
			// level에 맞는 위치에 이미지 호출
				String src = request.getContextPath() + "/resources/uploadImages/" + img.getImgChangeName(); %>
				<% if(img.getImgLevel() == 0){ %>
					$("#titleImg").prop("src", "<%= src %>");
				<% }else { %>
					$("#contentImg"+<%=img.getImgLevel()%>).prop("src", "<%= src %>");
				<% }  %>
			<% } i++; %>
		<% } %>
	});
	
	
	// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	function LoadImg(value, num) {
		// 파일 업로드 시 업로드된 파일의 경로는 
		// 해당 요소에 files라는 배열이 생성되며 저장됨 
		if(value.files && value.files[0]) { // value.files가 있냐
			// -> 파일이 선택이 된 경우, 경로만 접근
			var reader = new FileReader(); // 파일 자체를 브라우저로 호출
			
			reader.onload = function(e){ // .onload 파일을 다 읽으면 실행하라, 실행이 다 되면 result를 반환, result에 파일 경로가 저장되어 있음
				switch(num){
				case 1: $("#titleImg").prop("src", e.target.result); break;
				case 2: $("#contentImg1").prop("src", e.target.result); break;
				case 3: $("#contentImg2").prop("src", e.target.result); break;
				case 4: $("#contentImg3").prop("src", e.target.result); break;
				case 5: $("#contentImg4").prop("src", e.target.result); break;
				}
			} 
			// file에서 내용(Content)을 읽어옴
			// + base64 인코딩된 경로를 반환
			reader.readAsDataURL(value.files[0]);
			// 실제 내용을 읽어옴
		}
	}
	
	
</script>
</body>
</html>











