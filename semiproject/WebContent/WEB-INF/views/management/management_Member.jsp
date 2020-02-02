<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.util.*, java.sql.*, com.kh.semiproject.member.model.vo.Member"%>

<%
	List<Member> mList = (List<Member>) request.getAttribute("mList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>미씽펫 - 관리자 페이지</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/management.css" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Song+Myung|Noto+Sans+KR|Do+Hyeon|Yeon+Sung|Nanum+Myeongjo|Sunflower:300&display=swap"
	rel="stylesheet">

</head>
<body>

	<div class="container-fluid row-md-2" style="height: 1000px;">


		<%@ include file="../common/sidebar.jsp"%>


		<div class="container-fluid row-md-10 mr-5 mt-5" style="width: 80%;">
			<div id="page-content-wrapper  mb-5 mt-5">
				<div class="card bg-light shadow ml-5 mt-5 mr-5 mb-5">
					<div class="card-body">

						<h5>&nbsp;&nbsp;&nbsp;관리자 페이지</h5>


						<!-- Content -->
						<div class="row-md-12" id="manageheader" style="width: 100%; height: 850px; overflow: auto">
							<div class="row-md-10" style="height: 720px;">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">
											<a href="<%=request.getContextPath()%>/Management/management_Member">회원관리</a></th>
										<th scope="col">
											<a href="<%=request.getContextPath()%>/Management/management_Board">게시판관리</a></th>
										<th scope="col">
											<a href="<%=request.getContextPath()%>/Management/management_QnA">QnA등록</a></th>
										<th scope="col">
											<a href="<%=request.getContextPath()%>/Management/management_Ask">1:1문의</a></th>
										<th scope="col">
											<a href="<%=request.getContextPath()%>/Management/management_Report">신고관리</a></th>
									</tr>
								</thead>
							</table>

					<!-- 검색창-->

				
								<form class="col" method="POST" action="searchMember">
									<div class="form-row align-items-center">
										<div class="row-md-2 my-5">
											<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect"></label>
											<select name="searchKey" class="custom-select mr-sm-2"
												id="inlineFormCustomSelect">
												<option value="memberId" selected>ID</option>
												<option value="memberName">회원이름</option>
												<option value="memberEmail">이메일</option>
											</select>
										</div>

										<div class="col-md-9">
											<input name="searchValue" type="text" class=" form-control mr-sm-2" 
												placeholder="검색어를 입력하세요.">
										</div>
										<div>
											<button type="submit" class="btn btn-sm btn-outline-secondary">검색</button>
										</div>
									</div>
								</form>
						



							<table class="row-md-10 table" style="height: 15px;" >
								<thead>
									<tr>
										<th scope="col">&nbsp;&nbsp;</th>
										<th scope="col">&nbsp;&nbsp;ID</th>
										<th scope="col">&nbsp;이름</th>
										<th scope="col">&nbsp;&nbsp;이메일</th>
										<th scope="col"> <!-- 관리 기능 --> </th>
									</tr>

								</thead>

								<tbody>

								<% if (mList.isEmpty()) { %>
									<tr>
										<td colspan="5">존재하는 회원이 없습니다.</td>
									</tr>
								<% } else { %>
									
									<% for (int i = 0; i < mList.size(); i++) { %>
									<tr>
										<td> <!--공백 --> </td>
										<td><%=mList.get(i).getMemberId()%></td>
										<td><%=mList.get(i).getMemberName()%></td>
										<td><%=mList.get(i).getMemberEmail()%></td>
										<td>
										 	<!-- 회원 정보 수정 모달 -->
										 <a id="modal-<%=i%>" href="#modal-container-<%=i%>" role="button"
											class="btn btn-sm btn-outline-secondary" data-toggle="modal">수정</a>
										 
											<div class="modal fade" id="modal-container-<%=i%>"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="myModalLabel">회원 정보 수정</h5>
															<button type="button" class="close" data-dismiss="modal">
																<span aria-hidden="true">×</span>
															</button>
														</div>
														<div class="modal-body">
															<form class="form-signin" method="POST" action="updateMember">
																<% String newId = "newId" + i; %>
																아이디 <input type="text" class="form-control"
																	id="<%= newId %>" name="newId" 
																	value="<%=mList.get(i).getMemberId()%>" readonly> <br>
																<% String newName = "newName" + i; %>
																이름 <input type="text" class="form-control"
																	id="<%= newName %>" name="newName" placeholder="닉네임 입력">
																<br>
																<% String newEmail = "newEmail" + i; %>
																이메일 <input type="text" class="form-control"
																	id="<%= newEmail %>" name="newEmail"
																	placeholder="이메일 입력"> <br>
																<div class="modal-footer">
																	<button type="submit" class="btn btn-outline-secondary">수정하기</button>
																	<button type="reset" class="btn btn-outline-secondary"
																		data-dismiss="modal">Close</button>
																</div>
															</form>
														</div>
													</div>
												</div>
											</div>
											
											<!--  회원 삭제 모달  -->																	
										  <a id="modal--<%=i%>" href="#modal-container--<%=i%>" role="button"
											class="btn btn-sm btn-outline-secondary" data-toggle="modal">삭제</a>
											
											
											<div class="modal fade" id="modal-container--<%=i%>"
												role="dialog" aria-labelledby="myModalLabel"
												aria-hidden="true">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="myModalLabel">회원 삭제</h5>
															<button type="button" class="close" data-dismiss="modal">
																<span aria-hidden="true">×</span>
															</button>
														</div>
														<div class="modal-body">
															<form class="form-signin" method="POST" action="deleteMember">
															 
															선택한 회원 아이디 <input type="text" class="form-control"
															id="<%=mList.get(i).getMemberId()%>"
																name="deleteMemberId" value="<%=mList.get(i).getMemberId()%>" readonly> <br>
																
																<p> 정말로 이 회원을 삭제하시겠습니까? </p>
																
															<div class="modal-footer">
																<button type="submit" class="btn btn-outline-secondary">삭제하기</button>
																<button type="reset" class="btn btn-outline-secondary"
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


	<%@ include file="../common/footer.jsp"%>



</body>
</html>