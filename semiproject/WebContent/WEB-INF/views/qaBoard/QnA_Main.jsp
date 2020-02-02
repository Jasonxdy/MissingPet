<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미씽펫 - QnA 게시판</title>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="row">
    	<%@ include file="../common/sidebar.jsp"%>
    	
    	<div class="container-fluid pl-5 pr-5 pb-3" style="min-height: 89.3vh;">
        <div class="row card bg-light">
            <div class="col-md-12">
                <div class="container-fluid mt-3">
                    <div class="row">
                        <div class="col-md-12">
                            <h3>Q & A</h3>
                            <hr>
                        </div>
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
            </div>




            <div class="text-center">
                <button type="button" class="btn btn-primary btn-lg m-3" data-toggle="modal" data-target="#exampleModal">1:1 문의</button>
            </div>

            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">1:1문의 작성</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                        
                        <form>
                            <div class="form-group">
                              <label for="recipient-name" class="col-form-label">제목</label>
                              <input type="text" class="form-control" id="recipient-name">
                            </div>
                            <div class="form-group">
                              <label for="message-text" class="col-form-label">내용</label>
                              <textarea class="form-control" id="message-text"></textarea>
                            </div>
                        </form>

                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                      <button type="button" class="btn btn-primary">작성</button>
                    </div>
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
    $(".aa").click(function(){
        if($(this).parent().next("div").children().css("display") == "none"){
            $(this).parent().next("div").children().slideDown();
        } else{
            $(this).parent().next("div").children().slideUp();
        }
    });
</script>
</body>
</html>