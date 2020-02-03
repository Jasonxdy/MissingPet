<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.util.*, java.sql.*, com.kh.semiproject.qaBoard.model.vo.QnABoard"%>

<%
	List<QnABoard> qaBoardList = (List<QnABoard>) request.getAttribute("qaBoardList");
%>


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>미씽펫 - 관리자 QnA 페이지</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/management.css"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Song+Myung|Noto+Sans+KR|Do+Hyeon|Yeon+Sung|Nanum+Myeongjo|Sunflower:300&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="<%= request.getContextPath() %>/daumeditor/css/editor.css" type="text/css" charset="utf-8"/>
<script src="<%= request.getContextPath() %>/daumeditor/js/editor_loader.js" type="text/javascript" charset="utf-8"></script>
<style>

.ContentLength {
  display: inline-block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100px;
  height: 20px;
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
                        <div class="row-md-10" style="height: 720px;">
							<table class="table">

								<thead>
									<tr>
										<th scope="col"><a
											href="<%=request.getContextPath()%>/Management/management_Member">회원관리</a></th>
										<th scope="col"><a
											href="<%=request.getContextPath()%>/Management/management_Board">게시판관리</a></th>
										<th scope="col"><a
											href="<%=request.getContextPath()%>/Management/management_QnA">QnA등록</a></th>
										<th scope="col"><a
											href="<%=request.getContextPath()%>/Management/management_Ask">1:1문의</a></th>
										<th scope="col"><a
											href="<%=request.getContextPath()%>/Management/management_Report">신고
												관리</a></th>
									</tr>
								</thead>
							</table>
						<!-- 검색창-->
							
								<form class="col" method="POST" action="searchQnA">
									<div class="form-row align-items-center">
										<div class="row-md-2 my-5">
											<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect"></label>
											<select name="searchKey" class="custom-select mr-sm-2"
												id="inlineFormCustomSelect">
												<option value="SearchQnATitle" selected>제목</option>
												<option value="SearchQnAContent">내용</option>
												<option value="SearchQnAModifyDt">등록일</option>
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
										<th scope="col">글번호</th>
										<th scope="col">&nbsp;&nbsp;제목</th>
										<th scope="col">&nbsp;내용</th>
										<th scope="col">&nbsp;&nbsp;등록일</th>
										<th scope="col">
										<!--  글 등록  -->
														<div style="display:inline-block" style="width: 100px;">
											<a id="modal-QnAwrite" href="#modal-container-QnAwrite"
												role="button" class="btn btn-sm btn-outline-primary"
												data-toggle="modal">Q&A 글 등록</a>

											<div class="modal fade" id="modal-container-QnAwrite"
												role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content" id="modal-renew">
														<div class="modal-header">
															<h5 class="modal-title" id="myModalLabel">QnA 등록</h5>
															<button type="button" class="close" data-dismiss="modal">
																<span aria-hidden="true">×</span>
															</button>
														</div>
														
														<div class="modal-body">
																<form method="POST" action="<%= request.getContextPath() %>/Management/insertQnA">
																	<div>
																		<div>
																			 <input type="text" name="QnATitle" placeholder="제목 입력" style="width:100%">
																			 <textArea name="QnAContent" cols="106" rows="20" style="resize:none">등록할 내용을 작성해주세요.</textArea>
																		</div>
																	</div>
																<div class="modal-footer">
																	<button class="btn btn-outline-primary" type="submit">등록</button>
																	<button type="button" class="btn btn-outline-secondary"
																data-dismiss="modal">Close</button>
																</div>
																</form>
															</div>
														</div>
													</div>
												</div>
											</div> <!--  글 등록 div END -->

										</th>
									</tr>
								</thead>

								<tbody>
								  <% if (qaBoardList.isEmpty()) { %>
									<tr>
										<td colspan="5">존재하는 QnA게시글이 없습니다.</td>
									</tr>
								<% } else { %>
									
									<% for (int i = 0; i < qaBoardList.size(); i++) { %> 
									<tr>
										<td><%= qaBoardList.get(i).getQaNo() %></td>
									<td><span class="ContentLength"><%= qaBoardList.get(i).getQaTitle() %></span></td>
									<td><span class="ContentLength"><%= qaBoardList.get(i).getQaContent() %> </span></td>
										<td><%= qaBoardList.get(i).getQaModifyDt() %></td>
										<td>
										
											<div>
												<!-- QnA 수정 모달 -->
												<a id="modal-120930-<%= i %>" href="#modal-container-120930-<%= i %>"
													role="button" class="btn btn-sm btn-outline-secondary"
													data-toggle="modal">수정</a> 

												<div class="modal fade" id="modal-container-120930-<%= i %>"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content" id="modal-renew">
															<div class="modal-header">
																<h5 class="modal-title" id="myModalLabel">QnA 수정</h5>
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">×</span>
																</button>
															</div>
															<div class="modal-body">
																<form method="POST" action="<%= request.getContextPath() %>/Management/updateQnA">
																	<div>
																		<div>
																			현재 선택한 글 번호는 <input name="UpdateQnANo" style="width:4%" value="<%= qaBoardList.get(i).getQaNo() %>" readonly> 번 입니다.<br>
																			<input type="text" name="UpdateQnATitle" style="width:100%" value="<%= qaBoardList.get(i).getQaTitle() %>">
																			 <textArea name="UpdateQnAContent" cols="106" rows="20" style="resize:none"><%= qaBoardList.get(i).getQaContent() %></textArea>
																		</div>
																	</div>
															<div class="modal-footer">
																<button class="btn btn-primary " type="submit">수정</button>
																<button type="button" class="btn btn-secondary"
																	data-dismiss="modal">Close</button>
															</div>
															</form>
															</div>

														</div>
													</div>
												</div>
										
										
										
											<!--  QnA 삭제 모달  -->																	
										  <a id="modal---<%=i%>" href="#modal-container---<%=i%>" role="button"
											class="btn btn-sm btn-outline-secondary" data-toggle="modal">삭제</a>
											
											
											<div class="modal fade" id="modal-container---<%=i%>"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="myModalLabel">QnA 글 삭제</h5>
															<button type="button" class="close" data-dismiss="modal">
																<span aria-hidden="true">×</span>
															</button>
														</div>
														<div class="modal-body">
															<form class="form-signin" method="POST" action="deleteQnA">
															 <div>
																		<div style="width:100; overflow:hidden text-overflow:ellipsis">
																			현재 선택한 글 번호는 <input name="DeleteQnANo" style="width:5%" value="<%= qaBoardList.get(i).getQaNo() %>" readonly> 번 입니다.<br>
																			<input type="text" name="DeleteQnATitle" style="width:100%" value="<%= qaBoardList.get(i).getQaTitle() %>" readonly>
																			 <textArea name="DeleteQnAContent" cols="63" rows="20" style="resize:none " readonly><%= qaBoardList.get(i).getQaContent() %></textArea>
																		</div>
																	</div>
															
																
															<div class="modal-footer">
															해당 글을 정말 삭제하시겠습니까? &nbsp;&nbsp;
																<button type="submit" class="btn btn-outline-secondary">삭제하기</button>
																<button type="reset" class="btn btn-outline-secondary"
																	data-dismiss="modal">Close</button>
															</div>
														  </form>
														</div>
													</div>
												</div>
											</div>
												</div>
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
	
				
	<%@ include file="../common/footer.jsp"%>




</body>
</html>