<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-header.css">


</head>
<body>
	<div class="row ml-1 w-100" id="mypage-header" style= "z-index:1030">
		<h1 style="font-weight: bold;">마이페이지</h1>
		<!-- 마이페이지 Header nav -->
		<div class="row w-100 mt-2">
			<ul class="nav" id="mypage-nav">
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="main" id="mypage-profile">내정보</a>
					</h5>
				</li>
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="changePwd" id="mypage-chagePwd">비밀번호
							변경</a>
					</h5>
				</li>
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="writingList" id="mypage-writing">작성한
							글</a>
					</h5>
				</li>
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="commentList" id="mypage-comment">작성한
							댓글</a>
					</h5>
				</li>
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="askList"
							id="mypage-1to1-question">1:1 문의</a>
					</h5>
				</li>
				<li class="nav-item mr-4">
					<h5>
						<a class="nav-link" href="notification"
							id="mypage-notification">알림 설정</a>
					</h5>
				</li>
			</ul>
			<!-- 마이페이지 Header nav end -->

		</div>


	</div>

</body>
</html>