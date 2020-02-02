<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<%
	Member member = (Member)request.getSession().getAttribute("loginMember");
	String[] phone = member.getMemberPhone().split("-");
%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-main.css">


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

        <div class="ml-1 row" id="mypage-content-wrapper" >
          <h2 class="mt-3">프로필 수정</h2>

          <!-- 프로필 수정 form -->
          <form class="row ml-1" id="profile-update" action="updateMember" onsubmit="return validate();"
          enctype = "multipart/form-data" role="form" method="POST">

            <div class="row mt-2">
              <div class="col-md-10">
                <div class="row file-field">
                  <div class="mb-4 col-md-2">
                 		 <%
							String src = request.getContextPath() + "/resources/upProfileImage/" + member.getMemberProImg();
						%>
                    <img src="<%= src %>"
                      class="rounded-circle z-depth-1-half avatar-pic" alt="example placeholder avatar"
                      id="imagePreview">
                  </div>
                  <div class="d-flex justify-content-center col-md-2" id="profile-image-input">
                    <div class="btn btn-mdb-color btn-rounded float-left">
                      <input type="file" id="imageUpload" name="imageUpload" style="width: 300px">
                    </div>
                  </div>
                </div>
              </div>

              <!-- 이미지 업로드 방법 정리
                https://victorydntmd.tistory.com/175
             -->


              <!-- 이미지 업로드 jQuery -->
              <script type="text/javascript">
                function readURL(input) {
                  if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                      $('#imagePreview').prop('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                  }  
                }

                $("#imageUpload").change(function () {
                  readURL(this);
                });
                
              </script>
            </div>


            <!-- 아이디 -->
            <div class="row pt-3">
              <div class="col-md-2 pt-2">
                <h5>아이디</h5>
              </div>
              <input type="text" class="form-control" id="userId" name="userId" disabled value="<%= member.getMemberId() %>">
            </div>

            <!-- 이름 -->
            <div class="row pt-5">
              <div class="col-md-2 pt-2">
                <h5>이름</h5>
              </div>
              <input type="text" class="form-control" id="userName" name="userName" disabled value="<%= member.getMemberName() %>">
            </div>

            <!-- 이메일 및 이메일 인증 -->
            <div class="row pt-5">
              <div class="col-md-2 pt-2">
                <h5>이메일</h5>
              </div>
              <div class="col-md-10" id="profile-email" style="padding-left:0px;">
                <h5>
                  <b><%= member.getMemberEmail() %></b>
                </h5>
                <p>인증된 이메일입니다. <a href="#" style="color: purple;">이메일 변경하기</a></p>
              </div>


            </div>

            <!-- 전화번호 입력 -->
            <div class="row pt-5">
              <div class="col-md-2 pt-2">
                <h5>전화번호</h5>
              </div>
              <div class="col-md-10" id="phone-input" style="padding-left:0px;">
                <!-- 전화번호 -->
                <div class="row mb-3 form-row">

                  <!-- 전화번호1 -->
                  <select class="custom-select" id="phone1" name="phone1">
                    <option>010</option>
                    <option>011</option>
                    <option>016</option>
                    <option>017</option>
                    <option>019</option>
                  </select>
                  &nbsp;&nbsp;

                  <script>
                    /* jQuery의 for문 */
                    $.each($("#phone1>option"), function (index, item) {
                      /* index : 0부터 시작 , item : 현재 선택된 요소를 의미 */

                      if ($(item).text() == '<%= phone[0] %>') {
                        $(item).prop("selected", true);
                      }

                    });

                  </script>




                  <!-- maxlength 안먹는 문제 수정 필요!! -->


                  <!-- 전화번호2 -->
                  <input type="number" class="form-control phone" id="phone2" name="phone2" maxlength="4"
                    value="<%= phone[1]%>">

                  &nbsp;&nbsp;

                  <!-- 전화번호3 -->
                  <input type="number" class="form-control phone" id="phone3" name="phone3" maxlength="4"
                    value="<%= phone[2]%>">
                    <br>
                </div>
                   <div class="col-md-6 offset-md-3" style="margin-left: 0; padding-left: 0;">
                		<span id="checkPhone">&nbsp;</span>
          		  </div>
              </div>


            </div>
            <div class="row">
              <div>
                <hr class="mb-4">
              </div>
            </div>

            <div class="row mt-4 mb-5 text-center" >
              <div class="col-md-9" style="padding-left: 200px;">
                <button class="btn btn-outline-primary btn-lg btn-block center-block" type="submit">변경하기</button>
              </div>
            </div>
          </form>

          <!-- 프로필 수정 form end -->

				<!-- footer -->
				<div>
					<%@ include file="/WEB-INF/views/common/footer.jsp"%>
				</div>
			</div>
      </div>
      
	
	<script>
	 // 각 유효성 검사 결과를 저장할 객체
    var signUpCheck = { 
			"phone":true,
			};
    
	 	// 실시간 입력 형식 검사
	// 정규표현식
	$(document).ready(function(){
		
		var $phone2 = $("#phone2");
		var $phone3 = $("#phone3");
		
		
		// 전화번호 관련
	 	$(".phone").on("input",function(){
	 		
			// 전화번호 input 태그에 4글자 이상 입력하지 못하게 하는 이벤트
            if ($(this).val().length > $(this).prop("maxLength")){
                $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
            }
            
			// 전화번호 유효성 검사
            var regExp1 =  /^\d{3,4}$/; // 숫자 3~4 글자
            var regExp2 =  /^\d{4,4}$/; // 숫자 4 글자
            
            if(!regExp1.test($phone2.val()) || !regExp2.test($phone3.val())){
            	$("#checkPhone").text("전화번호가 유효하지 않습니다.")
				.css("color", "red");
            	signUpCheck.phone = false;
            } else {
            	$("#checkPhone").text("유효한 전화번호 형식입니다.")
				.css("color", "green");
            	signUpCheck.phone = true;
            	
            }
            
			
        });
		
          	
		
	});
    
	 	
	// submit 동작
	function validate(){
		
		for(var key in signUpCheck){
			if(!signUpCheck[key]){
				alert("전화번호를 확인해주세요.");
				return false;
			}
		}
	}
	
	
	
    </script>




		<!-- 알림 버튼 -->
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