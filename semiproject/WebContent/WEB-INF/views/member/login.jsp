<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	boolean save = false; // 아이디 저장 체크박스 값을 수정하기 위한 변수
	String saveId = ""; // 쿠키에 저장된 saveId라는 키가 가지고 있는 값을 저장할 변수
	
	Cookie[] cookies = request.getCookies(); // 전달받은 쿠기 저장
	
	// 서버 첫 시작시 request.getCookies()의 값이 null
	// -> if문으로 처리하지 않는 경우 페이지 로딩 시 NullPointerException이 발생
	if(cookies != null){
		for(Cookie c : cookies){
			// 쿠키 객체에서 name을 얻어와 그 값이 "saveId"와 같은지 비교
			// 		== key, 속성
			if(c.getName().equals("saveId")){
				saveId = c.getValue();
				save = true;
			}
		}
	}
	String memberEmail = (String)request.getAttribute("memberEmail");
	System.out.println(memberEmail);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script>
	<% if(memberEmail != null){ %>
	alert("회원님의 이메일(<%= memberEmail %>)로 요청을 보냈습니다.");
	<%	session.removeAttribute("memberEmail");
	} %>
</script>

<link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<div  class="col">

    <div>

      <form class="form-signin" method="POST" action="<%= request.getContextPath() %>/member/login" onsubmit="return loginValidate();">
        <div class="text-center mb-4">
	        <a href="<%= request.getContextPath() %>">
	          <img class="mb-4 rounded" src="${pageContext.request.contextPath}/img/MissPet.png"
	            alt="" width="170">
	        </a>
          <!-- <h1 class="h3 mb-3 font-weight-normal">Login</h1> -->
        </div>

        <div class="form-label-group">
          <input type="text" id="memberId" name="memberId" class="form-control" placeholder="아이디"
          value="<%= saveId %>" required autofocus>
          <label for="memberId">아이디</label>
        </div>

        <div class="form-label-group">
          <input type="password" id="memberPwd" name="memberPwd" class="form-control" placeholder="비밀번호" required>
          <label for="memberPwd">비밀번호</label>
        </div>

        <div class="checkbox mb-3">
          <label>
            <input type="checkbox" id="save" name="save" <%= save ? "checked" : "" %> value="remember-me"> 아이디 저장
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
      </form>
      <div id="find" class="text-center">
        <a href="<%= request.getContextPath() %>/member/findPage">
        <span class="">아이디 찾기</span>·
        <span class="mr-5 pr-2">비밀번호 찾기</span> 
        </a>
        <a href="signUpPage">
        <span class="ml-5">회원가입</span>
        </a>
      </div>

    	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
    
    </div>

  </div>
  <script>
		// 로그인 유효성 검사
		function loginValidate(){
									//trim() 문자열의 앞뒤 공백 제거
			if( $("#memberId").val().trim().length == 0 ){
				alert("아이디를 입력하세요.");
				$("#memberId").focus();
				
				return false;
			}
									
			if( $("#memberPwd").val().trim().length == 0 ){
				alert("비밀번호를 입력하세요.");
				$("#memberPwd").focus();
				
				return false;
			}
			return true;
		}
	</script>
</body>
</html>