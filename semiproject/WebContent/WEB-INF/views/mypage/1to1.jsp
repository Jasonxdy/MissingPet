<%@page import="com.kh.semiproject.review.model.vo.PageInfo"%>
<%@page import="com.kh.semiproject.mypage.model.vo.Ask"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	List<Ask> aList = (List<Ask>)request.getAttribute("aList");
	PageInfo pInf = (PageInfo)request.getAttribute("pInf");
	
	int listCount = pInf.getListCount();
	int currentPage = pInf.getCurrentPage();
	int maxPage = pInf.getMaxPage();
	int startPage = pInf.getStartPage();
	int endPage = pInf.getEndPage();
	
%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    

  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- 구글 폰트 추가 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">
  
  <!-- css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-1to1-question.css">

</head>

<body>






  <div class="container-fluid">

    <div class="row">

      <%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

      <!-- 마이페이지 내용 작성 -->
      <div class="container-fluid d-md-block" id="mypage-wrapper">

        <!--  마이페이지 Header start -->
        <%@ include file="/WEB-INF/views/mypage/mypageHeader.jsp"%>


        <!-- Content Wrapper -->
        <div class="ml-1 row" id="mypage-content-wrapper">
          <h2 class="mt-3">1:1 문의</h2>
			
			<% if(aList.isEmpty()) { %>
          <div class="row justify-content-center col-md-12 jumbotron pr-4" id="1to1-question-wrapper-none"
            style="background-color: white; padding-left: 0 !important; height: 500px; margin-left: -320px;">
              <p class="text-muted" style="line-height: 300px;">작성하신 1:1문의가 없습니다 :)</p>
          </div>
          <% } else { %>

            <div class="row">
              <div class="container-fluid mt-4 col-md-10"  id="table-container">
                  <table class="table table-hover table-striped" id="list-table">
                    <thead>
                      <tr>
                        <th>제목 </th>
                        <th>상태</th>
                        <th>작성일</th>
                      </tr>
                    </thead>
                    <tbody>
                    
                     <% for (Ask ask : aList) { %>
                  	<tr>
                  	<td style="display:none;">
                  		<%= ask.getAskNo() %>
                  	</td>
                  	<td style="display:none;">
                  		<%= ask.getAnswerContent() %>
                  	</td>
                  	<td style="display:none;">
                  		<%= ask.getAnswerDate() %>
                  	</td>
                  	<td>
                  		<%= ask.getAskTitle() %>
                   </td>
                   <td>
                          <% if (ask.getAskStatus().equals("N")) { %>
                          <span class="badge badge-pill badge-dark">답변 대기중</span>
                          <% } else { %>
                          <span class="badge badge-pill badge-success">답변 완료</span>
                          <% } %>
                   </td>
                   <td>
                          <span>
                            <%= ask.getAskModifyDt() %>
                          </span>
                   </td>
                   
                  <% } %>
                  
                    </tbody>
                  </table>

                <hr>



              </div>
            </div>

          <!-- 게시글 목록 번호 -->
          <div class="row pt-5">
            <div class="col-md-12">
  
              <ul class="pagination justify-content-center mr-5 pr-5" style="margin:20px 0">
               <% if(currentPage > 1) { %>
	                <li>
	                	<!-- 맨 처음으로(<<) -->
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/askList?currentPage=1">&lt;&lt;</a>
	                </li>
	                
	                <li>
	                	<!-- 이전으로(<) -->
                   		<a class="page-link" href="<%= request.getContextPath() %>/mypage/askList?currentPage=<%= currentPage-1 %>">&lt;</a>
	                </li>
	                <% } %>
	                
	                <!-- 10개의 페이지 목록 -->
	                <% for(int p = startPage; p <= endPage; p++){ %>
	                	<% if(p == currentPage) { %>
		                <li>
		                    <a class="page-link"><%= p %></a>
		                </li>
	                	<% } else{ %>
                		<li>
	                    	<a class="page-link" href="<%= request.getContextPath() %>/mypage/askList?currentPage=<%= p %>"><%= p %></a>
	                	</li>
	                	<% } %>
					<%} %>
	                
	                <!-- 다음 페이지로(>) -->
	                <% if(currentPage < maxPage){ %>
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/askList?currentPage=<%= currentPage+1 %>">&gt;</a>
	                </li>
	                
	                <!-- 맨 끝으로(>>) -->
	                <li>
	                    <a class="page-link" href="<%= request.getContextPath() %>/mypage/askList?currentPage=<%= maxPage %>">&gt;&gt;</a>
	                </li>
	                <% }%>
              </ul>
  
            </div>
  
          </div>

        </div>


  <% } %>

					
					
				<!-- 1:1 문의 조회용 modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					role="dialog" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">문의 결과</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<p id="answerCreateDate"></p>
								<p class="text-muted" id="answerContent"></p>
							</div>
							<div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
        </div>
							</form>
						</div>
					</div>
				</div>



				<script>
          
		// 게시글 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				
				// 답변 미완료인 경우 "답변이 완료되지 않았습니다" 출력
				var askStatus = $(this).parent().children().eq(4).children().eq(0).text();
				
				if(askStatus == "답변 대기중") {
					alert("답변이 아직 작성되지 않았습니다.");
				} else {
					
					var answerDate = $(this).parent().children().eq(2).text();
					var answerContent = $(this).parent().children().eq(1).text();
					
					
					
					$("#answerCreateDate").html("답변 작성일 : " + answerDate);
					$("#answerContent").html(answerContent);
					
					
					$("#exampleModal").modal();
					
					
					// var askNo = $(this).parent().children().eq(0).text();
					// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
					<%-- // location.href="<%= request.getContextPath() %>/board/detail?no="+ boardNo + "&currentPage=" + <%= currentPage %>; --%>
					
				}
				
				
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
	</script>

          <!-- footer -->
		<span>
			<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		</span>

      </div>
    </div>





    <!-- 알림버튼 -->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
  </div>




  </div>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>

</body>

</html>