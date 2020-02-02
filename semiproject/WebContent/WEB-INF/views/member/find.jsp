<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 / 비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/find.css">
</head>
<body>
	<!-- Modal -->
    <%-- <div class="modal fade" id="findModal" tabindex="-1" role="dialog" aria-labelledby="findModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div id="findModalContent" class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="findModalLabel">아이디/비밀번호 찾기</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    회원님의 이메일(<%= memberEmail %>)로<br> 아이디를 전송했습니다.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-block" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div> --%>

    <div class="container">
        <div class="col">
            <div class="text-center">
             <a href="<%= request.getContextPath() %>">
                <img class="mb-4 rounded" src="../img/MissPet.png" alt="" width="170">
             </a>
                <!-- <h1 class="h3 mb-3 font-weight-normal">Login</h1> -->
            </div>
            <!-- 탭 -->
            <div id="find-tabs" class="col">

                <ul class="nav nav-tabs ">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#qwe">아이디 찾기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#asd">비밀번호 찾기</a>
                    </li>
                </ul>

            </div>
            <div class="tab-content">
                <div class="tab-pane fade show active" id="qwe">
                    <form method="post" class="form-signin" action="<%= request.getContextPath() %>/member/findId">
                        <div class="form-label-group">
                            <input type="text" id="inputName-Id" name="findIdName" class="form-control" placeholder="아이디" required
                                autofocus>
                            <label for="inputEmail">이름</label>
                        </div>

                        <div class="form-label-group">
                            <input type="email" id="inputEmail-Id" name="findIdEmail" class="form-control" placeholder="비밀번호" required>
                            <label for="inputPassword">이메일 주소</label>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">아이디 찾기</button>
                    </form>
                </div>
                <div class="tab-pane fade" id="asd">
                    <form method="post" class="form-signin" action="<%= request.getContextPath() %>/member/findPwd">
                        <div class="form-label-group">
                            <input type="text" id="inputId-Pwd" name="findPwdId" class="form-control" placeholder="아이디" required
                                autofocus>
                            <label for="inputEmail">아이디</label>
                        </div>

                        <div class="form-label-group">
                            <input type="email" id="inputEmail-Pwd" name="findPwdEmail" class="form-control" placeholder="비밀번호" required>
                            <label for="inputPassword">이메일 주소</label>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">비밀번호 찾기</button>
                    </form>
                </div>

            </div>


            <!-- 로그인 -->


			<%@ include file="/WEB-INF/views/common/footer.jsp"%>


            

        </div>
    </div>
</body>
</html>