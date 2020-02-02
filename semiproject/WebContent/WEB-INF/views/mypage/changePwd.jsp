<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<%
	Member member = (Member)request.getSession().getAttribute("loginMember");
%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-changePwd.css">


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- 구글 폰트 추가 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

</head>

<body>


  <!-- ---
        layout: examples
        title: Dashboard Template
        extra_css: "dashboard.css"
        extra_js:
          - "https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"
          - "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"
          - "dashboard.js"
        --- -->

  <!-- <div class="navbar navbar-white fixed-top bg-white flex-md-nowrap p-0 shadow bg-transparent">
            <ul class="navbar-nav px-3">
              <li class="nav-item text-nowrap">
                <a class="nav-link" href="#">
                    <img src="img/alert_icon.png" width="5%" height="10%">
                </a>
              </li>
            </ul>
          </div>
         -->






	<div class="container-fluid">

		<div class="row">
			<!-- 사이드바 -->
			<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>


			<!-- 마이페이지 내용 작성 -->
			<div class="container-fluid d-md-block" id="mypage-wrapper">

				<!--  마이페이지 Header -->
				<%@ include file="/WEB-INF/views/mypage/mypageHeader.jsp"%>

				<div class="ml-1 row" id="mypage-content-wrapper">
					<h2 class="mt-3">비밀번호 변경</h2>

					<!-- 비밀번호 변경 form -->
					<form class="row ml-1" id="profile-update" method="post" role="form"
					action="updatePwd" onsubmit="return validate();">

						<div class="row mt-2 pl-3">
							<div class="row pt-4">
								<div class="col-md-2 pt-2">
									<h5>아이디</h5>
								</div>
								<h5 class="pt-2">
									<strong> <%= member.getMemberId() %> </strong>
								</h5>

							</div>
							<div class="row pt-5">
								<div class="col-md-2 pt-2 pb-3">
									<h5>현재 비밀번호</h5>
								</div>
								<input type="password" class="form-control" id="currentPwd"
									name="currentPwd">
								<div class="col-md-6 offset-md-3"
									style="margin-left: 266px; padding-left: 0;">
								</div>
							</div>

							<div class="row pt-5">
								<div class="col-md-2 pt-2">
									<h5>새 비밀번호</h5>
								</div>
								<input type="password" class="form-control" id="newPwd1"
									name="newPwd1">
								<div class="col-md-6 offset-md-3"
									style="margin-left: 266px; padding-left: 0;">
									<span id="checkPwd1">&nbsp;</span>
								</div>
							</div>

							<!-- 비밀번호 확인-->
							<div class="row pt-5">
								<div class="col-md-2 pt-2">
									<h5>새 비밀번호 확인</h5>
								</div>
								<input type="password" class="form-control" id="newPwd2"
									name="newPwd2">
								<div class="col-md-6 offset-md-3"
									style="margin-left: 266px; padding-left: 0;">
									<span id="checkPwd2">&nbsp;</span>
								</div>
							</div>
						</div>


						<div class="row mt-5 mb-5 text-center mt-1">
							<div class="col-md-9" style="padding-left: 200px;">
								<button
									class="btn btn-outline-primary btn-lg btn-block center-block"
									type="submit">변경하기</button>
							</div>
						</div>
					</form>

					<!-- 프로필 수정 form end -->

					<div>
					<%@ include file="/WEB-INF/views/common/footer.jsp"%>
					</div>
				</div>
			</div>


			<script>
			
			var signUpCheck = { 
					"newPwd1":false,
					"newPwd2":false,
					};
			
			$(document).ready(function(){
				var $pwd1 = $("#newPwd1");
				var $pwd2 = $("#newPwd2");
				
				// 비밀번호  유효성 검사
				//영어 대,소문자 + 숫자, 총 6~12글자
				$pwd1.on("input", function(){
					var regExp = /^[A-Za-z0-9]{6,12}$/;
					
					if(!regExp.test($pwd1.val())){
						$("#checkPwd1").text("비밀번호 형식이 유효하지 않습니다.")
						.css("color", "red");
						signUpCheck.newPwd1 = false;
					} else {
						$("#checkPwd1").text("유효한 형식의 비밀번호입니다.")
						.css("color","green");
						signUpCheck.newPwd1 = true;
					}
				});		
				
				// 비밀번호 일치 여부
				$pwd2.on("input", function(){
					
					if($pwd1.val().trim() != $pwd2.val().trim()){
						$("#checkPwd2").text("비밀번호가 일치하지 않습니다")
						.css("color", "red");
						signUpCheck.newPwd2 = false;
					} else {
						$("#checkPwd2").text("비밀번호가 일치합니다")
						.css("color","green");
						signUpCheck.newPwd2 = true;
					}
				});		
				
			});
	        
	   	 	
			// submit 동작
			function validate(){
				for(var key in signUpCheck){
					if(!signUpCheck[key]){
						
						alert("입력값이 잘못되었습니다. 다시 입력해주세요");
						var id = "#"+key;
						$(id).val("");
						$(id).focus();
						return false;
					}
				}
			}
			</script>




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