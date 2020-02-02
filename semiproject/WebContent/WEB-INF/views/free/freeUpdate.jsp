<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@page import="java.util.ArrayList"%>
    <%@page import="com.kh.semiproject.board.model.vo.BoardEH"%>
      <%@page import="com.kh.semiproject.free.model.vo.Free"%>
    <%@page import="com.kh.semiproject.board.model.vo.Img"%>
    
 <%
	
 	BoardEH board = (BoardEH)request.getAttribute("board");
 	Free free = (Free)request.getAttribute("free");
 	Member member = (Member)request.getAttribute("member");
	ArrayList<Img> iList = (ArrayList<Img>)request.getAttribute("iList");
 %> 
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  
<link rel="stylesheet" href="<%= request.getContextPath() %>/daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<script src="<%= request.getContextPath() %>/daumeditor/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
 
  
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/copy5.css">

    
    <!--  -->
    





    
</head>
<body>


<div class="container-fluid">
    <section class="row">
    
    
     <%@ include file="../common/sidebar.jsp"%>
    
          
          <div id="demo" class="w-100">
    
    <!--contents 시작-->

            <div class="col-md-12 mt-4" id="con">
          
          
            <div id="page-content-wrapper" >
              <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
                <div class="card-body">
                  

                    <!-- 이 안에다가 작성하세요!!!!!!!! -->

            
            <div class="container">
              <div class="row">
            
                <section class="content">
                  <h2 id="title-top"><a href="#">자유게시판 수정</a></h2>
                  <hr>
                  <div class="col-md-12 col-md-offset-2" id="panelwrap" >


				<form id="tx_editor_form" action="update?no=<%= board.getBoardNo()%>"
				method="post" enctype="multipart/form-data" role="form" onsubmit="retrun validate();" >
                   
                    <div id="title22">
                     <div class="mb-2">
					<label class="input-group-addon mr-3 insert-label">카테고리</label> 
					<select	class="custom-select" id="category" name="category" style="width: 150px;">
						<option value="잡담">잡담</option>
						<option value="질문">질문</option>
						<option value="정보">정보</option>
					</select>
				</div>
                     
		            <div class="input-group">
		              <label class="input-group-addon mr-3" id="title-tt">제목</label>
		              <input type="text" class="form-control" id="title" name="title"
		               size="60" value="<%= board.getBoardTitle()%>">
		            </div>
		            
                	 <div class="input-group">
		              <label class="input-group-addon mr-3">작성자</label>
		              <h5 class="my-0" id="writer"><%=board.getMemId()%></h5>
		            </div>
		            
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성일</label>
		              <h5 class="my-0" id="today"><%=board.getBoardCreateDate() %></h5>
		            </div>
		            
                    </div>
                   
                 	<div class="form-group">
		           <%@ include file="/daumeditor/reviewUpdateEditor.jsp"%>
		          </div>
		          <textarea id="content" style="display:none;"><%=board.getBoardContent() %></textarea>
	                  
	 					
                 
                   <div id="bt-lable">
                    <label><b>영상 첨부</b></label>
                    &nbsp;&nbsp;
                    <input type="text" id="url" name="url" size="30" value="<%=board.getBoardUrl()%>">
                    &nbsp;&nbsp;&nbsp;
                    
                     <div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label"><b>썸네일</b></label>
						<div class="boardImg" id="titleImgArea">
						<%-- <% String thSrc = "";
							if(iList != null && iList.get(0) != null) {
								thSrc = request.getContextPath() + "/resources/uploadImages/" + iList.get(0).getImgChangeName();
							}%> --%>
						<img id="titleImg" width="100" height="100">
						<%-- src = "<%=thSrc %>" --%>
						</div>
					</div>
					
					
					<div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
						
					
						<div class="mr-2 boardImg" id="contentImgArea1">
							<img id="contentImg1" width="50" height="50" >
						</div>
						
						<div class="mr-2 boardImg" id="contentImgArea2">
							<img id="contentImg2" width="50" height="50" >
						</div>

						<div class="mr-2 boardImg" id="contentImgArea3">
							<img id="contentImg3" width="50" height="50" >
						</div>
						
						<script>
						<% if(iList != null){ %>
                        <%  for(int i=0; i<iList.size(); i++) { %>
                        	<% if(i==0) { 
                                    String src1 = request.getContextPath()+"/resources/uploadImages/"+iList.get(i).getImgChangeName(); %>
									$("#titleImg").prop("src","<%=src1%>");
							<% } else if(i==1) {
                                    String src2 = request.getContextPath()+"/resources/uploadImages/"+iList.get(i).getImgChangeName(); %>
                                    $("#contentImg1").prop("src","<%=src2%>");
							<% } else if(i==2) {
                                    String src3 = request.getContextPath()+"/resources/uploadImages/"+iList.get(i).getImgChangeName(); %>
                                    $("#contentImg2").prop("src","<%=src3%>");
                       		<% } else if(i==3) {
                                    String src4 = request.getContextPath()+"/resources/uploadImages/"+iList.get(i).getImgChangeName(); %>
                                    $("#contentImg3").prop("src","<%=src4%>");
                        	<% } else if(i==4) {
                                    String src5 = request.getContextPath()+"/resources/uploadImages/"+iList.get(i).getImgChangeName(); %>
                                    $("#contentImg4").prop("src","<%=src5%>");
								<% } %>
							<% } %>
						<% } %>
						</script>
						
						
					
					
					</div>
					
					
					<div id="fileArea">
					<input type="file" id="img1" 
						name="img1" onchange="LoadImg(this,1)"> 
					<input type="file" id="img2" 
						name="img2" onchange="LoadImg(this,2)"> 
					<input type="file" id="img3" 
						name="img3" onchange="LoadImg(this,3)"> 
					<input type="file" id="img4" 
						name="img4" onchange="LoadImg(this,4)">
				</div>
					
					
					
                   </div>
                   
                   
                   <div id="top-bt">
                      <button type="submit" class="btn btn-primary m-2" onclick="saveContent()">등록하기</button>
                        <a href="write?no=<%= board.getBoardNo() %>" class="btn btn-primary">취소</a>
                   </div>
                   
                   
                  <%   for(int i=0 ; i<5 ; i++){ 
           			 		if(i < iList.size()){
          			 %>
		               			<input type="hidden" name="beforeImg" value="<%= iList.get(i).getImgChangeName()%>">
	              		 <% }else{%>
		               			<input type="hidden" name="beforeImg" value="">
            
          		  <%      } 
           			 } %>
                   
                   
                   
                   
                   
                   </form>



                  </div>

                  <!--오른쪽 배너-->
                 

                  <!--하단-->
                   
                 


                </section>
                
              </div>
            </div> <!-- container 종료-->






            <!-- 이 안에다가 작성하세요!!!!!!!! -->
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
     
     
 	$(function(){
		loadContent();
	});
    
  	
  	$(function(){
  		$("#category > option[value='<%= free.getFreeCategory() %>']").attr("selected","selected");
  	});
     
  
    function validate(){
        
        $.each( $("input[type=file]"), function(index, item){
           
           if($(item).val() == ""){
              $($("input[name=beforeImg]").get(index)).val("");
           }
        });
     
    }
     
		// 오늘 날짜 출력 
		var today = new Date();

		var str = today.getFullYear() + "-" + today.getMonth() + 1 + "-"
				+ today.getDate();
		$("#today").html(str);

	
		
		
		// 이미지 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
	    $(function () {
			// 파일 선택 버튼이 있는 영역을 보이지 않게함
	    	$("#fileArea").hide();
			
			// 이미지 영역 클릭 시 파일 첨부 창 띄우기
			$("#titleImgArea").click(function(){
				// 숨겨놓은 파일버튼 누르게함
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
			
	    });
		
	 // 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
	    function LoadImg(value, num) {
	    	if(value.files && value.files[0])
	    	var reader = new FileReader();
	    	reader.onload = function(e){
				switch(num){
				case 1 : $("#titleImg").prop("src",e.target.result); break;
				case 2 : $("#contentImg1").prop("src",e.target.result); break;
				case 3 : $("#contentImg2").prop("src",e.target.result); break;
				case 4 : $("#contentImg3").prop("src",e.target.result); break;
				}
	    	}
	    		reader.readAsDataURL(value.files[0]);
	    }
	 
		

		
     
     </script>
     
     



</body>
</html>