<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.ask.model.vo.QA"%>
<%@page import="java.util.List"%>

<%

	List<QA> qaList = (List<QA>)request.getAttribute("qaList");

%>

    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q & A</title>


</head>
<body>

<div class="container-fluid">
    <div class="row" id="row">
    	<%@ include file="../common/sidebar.jsp"%>
    	
    	<div class="container-fluid pl-5 pr-5 pb-3" style="min-height: 89.3vh; margin-left:300px">
        <div class="row card bg-light">

			 <div class="col-md-12">
                <div class="container-fluid mt-3">
                    <div class="container">
                            <h3>Q & A</h3>
                            <hr>
                    </div>
                </div>
            </div>
            
            <div id="qaListArea">
				<!-- 	<div class="container">
                <div class="row card">
                    <div class="col-md-12 card-body p-2 aa" style="cursor: pointer;">
                        <p class="mb-0">Q. 질문</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 bg-white" id="bb" style="display: none;">
                        <p class="mb-0">내용</p>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row card">
                    <div class="col-md-12 card-body p-2 aa" style="cursor: pointer;">
                        <p class="mb-0">Q. 질문</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 bg-white" id="bb" style="display: none;">
                        <p class="mb-0">내용</p>
                    </div>
                </div>
            </div> -->
            </div>


            <div class="text-center">
                <button type="button" class="btn btn-primary btn-lg m-3" data-toggle="modal" data-target="#exampleModal" onclick="loginValidate();">1:1 문의</button>
            </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">1:1 문의 작성</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                        
                        <form action="insertAsk" onsubmit="return askValidate();" id="insertAsk" method="POST">
                            <div class="form-group">
                              <label for="recipient-name" class="col-form-label">제목</label>
                              <input type="text" class="form-control" id="askTitle" name="askTitle">
                            </div>
                            <div class="form-group">
                              <label for="message-text" class="col-form-label">내용</label>
                              <textarea class="form-control" id="askContent" name="askContent"></textarea>
                            </div>

                    </div>
                    <div class="modal-footer">
                      <button class="btn btn-secondary" data-dismiss="modal">취소</button>
                      <button type="submit" class="btn btn-primary">작성</button>
                    </div>
                        </form>
                  </div>
                </div>
              </div>

        </div>
    </div>
    	
    	
    	
    	
    	<%@ include file="../common/header.jsp"%>
	</div>
	<%@ include file="../common/footer.jsp"%>
</div>
<script>
    
    	/* 1:1 문의 로그인 체크 */
    	function loginValidate(){
    	<% if(loginMember == null) { %>
    		location.href = "<%= request.getContextPath() %>/member/loginPage" 
			<% } %>
    	}
    	
    	/* 1:1 문의 미작성여부 체크 */
    	function askValidate() {
    		
			if($('#askTitle').val().trim().length == 0) {
    			alert("1:1 문의의 제목을 입력해주세요.");
				$("#askTitle").focus();
				return false;
			}
			
			else if($('#askContent').val().trim().length == 0) {
    			alert("1:1 문의의 내용을 입력해주세요.");
				$("#askContent").focus();
				
				return false;
			}
			return true;
    		
    	}
    
    
    
    /* QA 목록 출력 함수 */
    
   $(document).ready(function(){
	   
	   var $qaListArea = $("#qaListArea");
	   
	   <% for (QA qa : qaList) { %>
	   
	   var $container = $("<div>").prop("class", "container");
	   var $questionRow = $('<div class="row card">');
	   var $questionCol = $('<div class="col-md-12 card-body p-2 clickShow" style="cursor: pointer;">');
	   var $question = $('<p class="mb-0">').html("<strong>Q. " + "<%= qa.getQaTitle()%></strong>");
	   var $answerRow = $('<div class="row">');
	   var $answerCol = $('<div class="col-md-12 bg-white" id="bb" style="display: none;">');
	   var $answer = $('<p class="mb-0">').html("<%= qa.getQaContent()%>");
	   
	   $container.append($questionRow.append($questionCol.append($question)));
	   $container.append($answerRow.append($answerCol.append($answer)));
	   $qaListArea.append($container);
	   
	   <% } %>
	   
	   
	   $(".clickShow").click(function(){
			
			console.log("click 실행")
			
		    if($(this).parent().next("div").children().css("display") == "none"){
		        $(this).parent().next("div").children().slideDown();
		    } else {
		        $(this).parent().next("div").children().slideUp();
		    }
		});
	   
	   
    	
    });
    
    
    	
    
</script>
</body>
</html>