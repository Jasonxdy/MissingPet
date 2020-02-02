<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
	<style>
		/* number 태그 화살표 제거 */
		input[type="number"]::-webkit-outer-spin-button,
		input[type="number"]::-webkit-inner-spin-button {
		    -webkit-appearance: none;
		    margin: 0;
		}
		
		#signWrapper label{
		    font-size: 20px;
		  }
	</style>
</head>
<body>

	<div class="container-sm">
	
	    <div id="signWrapper" class="">
	        <div class="text-center mb-3 mt-2">
	            <img class="mb-4 rounded" src="../img/MissPet.png" alt="" width="170">
	            <!-- <h1 class="h3 mb-3 font-weight-normal">Login</h1> -->
	        </div>
	        <div id="" class="col-sm-8 offset-sm-2">
	
	            <form method="POST" action="signUp" class="needs-validation" name="signUpForm"
	                enctype="multipart/form-data" role="form" onsubmit="return validate();">
	
	                <!-- 아이디 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="id" class="">* 아이디</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="text" class="form-control" name="id" id="id" maxlength="12"
	                            placeholder="아이디를 입력하세요" autocomplete="off" required>
	                        <input type="hidden" name="idDup" id="idDup" value="false">
	                        <!-- required : 필수 입력 항목으로 지정 -->
	                        <!-- autocomplete="off" : input 태그 자동완성 기능을 끔 -->
	                    </div>
	                    <div class="col-md-3">
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkId">&nbsp;</span>
	                    </div>
	                </div>
	
	
	                <!-- 비밀번호 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="pwd1">* 비밀번호</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="password" class="form-control" id="pwd1" name="pwd1" maxlength="12"
	                            placeholder="비밀번호를 입력하세요" required>
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkPwd1">&nbsp;</span>
	                    </div>
	                </div>
	
	                <!-- 비밀번호 확인 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="pwd2">* 비밀번호 확인</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="password" class="form-control" id="pwd2" maxlength="12" placeholder="비밀번호 확인"
	                            required>
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkPwd2">&nbsp;</span>
	                    </div>
	                </div>
	
	                <!-- 이름 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="name">* 이름</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="text" class="form-control" id="name" name="name" required>
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkName">&nbsp;</span>
	                    </div>
	                </div>
	
	                <!-- 전화번호 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="phone1">* 전화번호</label>
	                    </div>
	                    <!-- 전화번호1 -->
	                    <div class="col-md-3">
	                        <select class="custom-select" id="phone1" name="phone1" required>
	                            <option>010</option>
	                            <option>011</option>
	                            <option>016</option>
	                            <option>017</option>
	                            <option>019</option>
	                        </select>
	                    </div>
	
	                    <!-- 전화번호2 -->
	                    <div class="col-md-3">
	                        <input type="number" class="form-control phone" id="phone2" maxlength="4" name="phone2"
	                            required>
	                    </div>
	
	                    <!-- 전화번호3 -->
	                    <div class="col-md-3">
	                        <input type="number" class="form-control phone" id="phone3" maxlength="4" name="phone3"
	                            required>
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkPhone">&nbsp;</span>
	                    </div>
	                </div>
	
	                <!-- 이메일 -->
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="email">* Email</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="email" class="form-control" id="email" name="email" autocomplete="off"
	                            required>
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkEmail">&nbsp;</span>
	                    </div>
	                </div>
	
	                <div class="row mb-3 form-row">
	                    <div class="col-md-3">
	                        <label for="profile-img">* 프로필 사진</label>
	                    </div>
	                    <div class="col-md-6">
	                        <input type="file" class="form-control" id="profile-img" name="profile-img">
	                    </div>
	
	                    <div class="col-md-6 offset-md-3">
	                        <span id="checkEmail">&nbsp;</span>
	                    </div>
	                </div>
	                
	
	                <hr class="mb-4">
	                <button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
	            </form>
	            <%@ include file="/WEB-INF/views/common/footer.jsp"%>
	        </div>
	    </div>
	</div>

	<script>
        // 각 유효성 검사 결과를 저장할 객체
       var signUpCheck = { 
       		"id":false,
       		"idDup":false,
			"pwd1":false,
			"pwd2":false,
			"name":false,
			"phone":false,
			"email":false
			};
        
   	 	// 실시간 입력 형식 검사
		// 정규표현식
		$(document).ready(function(){
			
			// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
			var $id = $("#id");
			var $pwd1 = $("#pwd1");
			var $pwd2 = $("#pwd2");
			var $name = $("#name");
			var $phone2 = $("#phone2");
			var $phone3 = $("#phone3");
			var $email = $("#email");
			
			
			
			// 아이디  유효성 검사
			// 첫글자는 영어 소문자, 나머지 글자는 영어 대,소문자 + 숫자, 총 6~12글자
			$id.on("input", function(){
				var regExp = /^[a-z][a-zA-z\d]{5,11}$/;
				if(!regExp.test($id.val())){
					$("#checkId").text("아이디 형식이 유효하지 않습니다.")
						.css("color", "red");
					signUpCheck.id = false;
				}else {
					signUpCheck.id = true;
					// 유효 X, 사용 X, 사용 O
					
					// Ajax를 이용한 실시간 아이디 중복검사
					$.ajax({
						url: "idDupCheck",
						data: {id: $id.val()},
						type: "GET",
						success: function(result){
							if(result == "yes"){
								$("#checkId").text("사용 가능한 아이디 입니다.")
								.css("color", "green");
								
								signUpCheck.idDup = true;
							}else{
								$("#checkId").text("사용할 수 없는 아이디 입니다.")
								.css("color", "red");
								signUpCheck.idDup = false;
							}
								
						},
						error: function(e){
							console.log("아이디 중복 검사 ajax 실패");
							console.log(e);
						}
						
					});
				}
			});

			
			
			
			// 비밀번호  유효성 검사
			//영어 대,소문자 + 숫자, 총 6~12글자
			$pwd1.on("input", function(){
				var regExp = /^[A-Za-z0-9]{6,12}$/;
				if(!regExp.test($pwd1.val())){
					$("#checkPwd1").text("비밀번호 형식이 유효하지 않습니다.")
						.css("color", "red");
					signUpCheck.pwd1 = false;
				}else {
					$("#checkPwd1").text("유효한 형식의 비밀번호 입니다.")
					.css("color", "green");
					signUpCheck.pwd1 = true;
				}
			});
			
			
			// 비밀번호 일치 여부
			$pwd2.on("input", function(){
				if($pwd1.val().trim() != $pwd2.val().trim()){
					$("#checkPwd2").text("비밀번호가 일치하지 않습니다.")
						.css("color", "red");
					signUpCheck.pwd2 = false;
				}else {
					$("#checkPwd2").text("비밀번호가 일치합니다.")
					.css("color", "green");
					signUpCheck.pwd2 = true;
				}
			});
			
			// 이름 유효성 검사
			// 한글 두 글자 이상
			$name.on("input", function(){
				var regExp = /^[가-힣]{2,}$/;
				if(!regExp.test($name.val())){
					signUpCheck.name = false;
				}else {
					signUpCheck.name = true;
				}
			});	
			
			
			// 전화번호 관련
		 	$(".phone").on("input",function(){
		 		
				// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
                if ($(this).val().length > $(this).prop("maxLength")){
                    $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
                }
                
				// 전화번호 유효성 검사
				
                var regExp2 =  /^\d{3,4}$/; // 숫자 3~4 글자
                var regExp3 =  /^\d{4,4}$/; // 숫자 4 글자
                
                if(!regExp2.test($phone2.val()) || !regExp3.test($phone3.val())){
                	$("#checkPhone").text("전화번호가 유효하지 않습니다.").css("color", "red");
                	signUpCheck.phone = false;
                } else{
                	$("#checkPhone").text("전화번호가 유효한 형식입니다.").css("color", "green");
                	signUpCheck.phone = true
                }
                
				
				
            });
			
			
			// 이메일 유효성 검사
			$email.on("input", function(){
			 var regExp =  /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
			 if(!regExp.test($email.val())){
				 $("#checkEmail").text("이메일이 유효하지 않습니다.").css("color", "red");
             	signUpCheck.email = false;
			 }else {
				 $("#checkEmail").text("이메일이 유효한 형식입니다.").css("color", "green");
	             	signUpCheck.email = true;
	             	
	             	$.ajax({
						url: "emailDupCheck",
						data: {email: $email.val()},
						type: "GET",
						success: function(result){
							if(result == "yes"){
								$("#checkEmail").text("사용 가능한 이메일 입니다.")
								.css("color", "green");
								signUpCheck.email = true;
							}else{
								$("#checkEmail").text("사용할 수 없는 이메일 입니다.")
								.css("color", "red");
								signUpCheck.email = false;
							}
								
						},
						error: function(e){
							console.log("이메일 중복 검사 ajax 실패");
							console.log(e);
						}
						
					});
			 }
			})
		});
        
   	 	
		// submit 동작
		function validate(){
			
			
			for(var key in signUpCheck){
				if(!signUpCheck[key]){
					alert("일부 입력값이 잘못되었습니다.");
					var id = "#"+key;
					$(id).focus();
					return false;
				}
			}
		}
		
		
		
        </script>


</body>
</html>