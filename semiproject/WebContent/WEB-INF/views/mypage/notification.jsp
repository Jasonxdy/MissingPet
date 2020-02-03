<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Member member = (Member)request.getAttribute("member");
%>  
    
 
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title> 미씽펫 방문을 환영합니다! </title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="mypage-notification.css">


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

  <!-- 구글 폰트 추가 -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

  <!-- 토글 스위치 CDN -->
  <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
  <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
  
  <!-- css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage-notification.css">
	
</head>

<body>


  <div class="container-fluid">

    <div class="row">

       <%@ include file="/WEB-INF/views/common/sidebar.jsp"%>



      <!-- 마이페이지 내용 작성 -->
      <div class="container-fluid d-md-block" id="mypage-wrapper">

        <!--  마이페이지 Header start -->
        <%@ include file="/WEB-INF/views/mypage/mypageHeader.jsp"%>

        <!-- 토글 스위치 출처 : https://bootsnipp.com/snippets/GaxR2 -->
        <div class="ml-1 row" id="mypage-content-wrapper">
          <h2 class="mt-3">알림 설정</h2>

          <div class="row ml-1">

            <!-- 알림 form -->
            <form action="#">
              
              <!-- 웹페이지 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-2 pl-4 mt-4 mb-2">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="webTell">
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h4 class="mt-2"><b>웹페이지 알림</b></h4>
                  <h6>내 활동에 대한 웹페이지 알림 수신</h6>
                </div>
              </div>


              <!-- 댓글 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-5 pl-4 mt-4">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="commentTell">
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" id= "explain" style="width: 900px;">
                  <h5 class="mt-2">댓글 알림</h5>
                  <h6>내가 작성한 글 또는 댓글의 답글에 대한 알림 수신</h6>
                </div>
              </div>
              
              <!-- 1:1 문의 알림 버튼 -->
              <div class="row">
                <div class="col-md-1 ml-5 pl-4 mt-4 mb-5 pb-4">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="askTell">
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" id= "explain" style="width: 900px;">
                  <h5 class="mt-2">1:1 문의 알림</h5>
                  <h6>내가 작성한 1:1 문의의 답변에 대한 알림 수신</h6>
                </div>
              </div>

              <!-- 이메일 알림 버튼 -->
              <div class="row">

                <div class="col-md-1 ml-2 pl-4 mt-4 mb-2">
                  <label class="switch">
                    <input type="checkbox" class="warning" id="rtTell">
                    <span class="slider round"></span>
                  </label>
                </div>
                
                <div class="col-md-8 ml-2" style="width: 900px;">
                  <h4 class="mt-2"><b>이메일 알림</b></h4>
                  <h6>이메일을 통한 실시간 알림 수신</h6>
                </div>
              </div>

            </form>
            <!-- 알림 form end -->


          </div>


          <!-- 웹페이지 알림 on/off시 전체 on/off script -->
          <script>

            /* 
            
            $("#webTell").change(function(){
              if(!$(this).prop("checked")){
                $("#commentTell").prop("checked", false);
                $("#askTell").prop("checked", false);
              }

            });
 */
            // $("webTell>input:checkbox").change(function(){
            //   console.log("자식눌렸당");
            // });
            
              // if($("#commentTell").prop("checked") || $("#askTell").prop("checked")) {
              //   $("#webTell").prop("checked", true);
              // }

          
          
          </script>



          <!-- Footer start -->
          <%@ include file="/WEB-INF/views/common/footer.jsp"%>

        </div>
      </div>





      <!-- 알림버튼 -->
	  <%@ include file="/WEB-INF/views/common/header.jsp"%>
    </div>

    <script>
    	
    
    	// ready 함수
      $(document).ready(function () {
    	  // 알림 설정 조회
    	  tellList();
    	  
    		// 전체 값 변경
          $("#webTell").change(function () {
            if(!$(this).prop("checked")) {
              $("#commentTell").prop("class", "dark").prop("disabled", true);
              $("#askTell").prop("class", "dark").prop("disabled", true);
              $("#explain>h5, #explain>h6").addClass("text-muted");
            } 
            else {
              $("#commentTell").prop("class", "warning").prop("disabled", false);
              $("#askTell").prop("class", "warning").prop("disabled", false);
              $("#explain>h5, #explain>h6").removeClass("text-muted");
            }
            tellCheck();
          });
      	
          $("#commentTell").change(function() {
            if($(this).prop("checked")) {
              $("#webTell").prop("checked", true);
            } else if (!$(this).prop("checked") && !$("#askTell").prop("checked")) {
              $("#webTell").prop("checked", false);
            }
            tellCheck();
          });

          $("#askTell").change(function() {
            if($(this).prop("checked")) {
              $("#webTell").prop("checked", true);
            } else if (!$(this).prop("checked") && !$("#commentTell").prop("checked")) {
              $("#webTell").prop("checked", false);
            }
            tellCheck();
          });
          
          $("#rtTell").change(function(){
        	  tellCheck();
          });
    	
      });
		      
   // 알림 설정 가져오기
      var tellList = function () {
	   
	   console.log("tellList 실행");
	   
      	
      	  // 실시간 알림 체크
      	if('<%= member.getMemberRTTell() %>' == 'Y') {
      		$("#rtTell").prop("checked", true);
      	} else {
      		$("#rtTell").prop("checked", false);
      	}
      	  
      	  // 웹페이지 알림 체크
      	if('<%= member.getMemberWebTell() %>' == 'Y') {
      		$("#webTell").prop("checked", true);
      	} else {
      		$("#webTell").prop("checked", false);
      	}
      	  
      	  // 댓글 알림 체크
      	if('<%= member.getmemberCommentTell() %>' == 'Y') {
      		$("#commentTell").prop("checked", true);
      	} else {
      		$("#commentTell").prop("checked", false);
      	}
      	  
      	  // 1:1 문의 알림 체크
      	if('<%= member.getMemberAskTell() %>' == 'Y') {
      		$("#askTell").prop("checked", true);
      	} else {
      		$("#askTell").prop("checked", false);
      	}
      	
	   // 웹페이지 알림 꺼져있는 경우 나머지 disable, dable check
	   if(!$("#webTell").prop("checked")) {
           $("#commentTell").prop("class", "dark").prop("disabled", true);
           $("#askTell").prop("class", "dark").prop("disabled", true);
           $("#explain>h5, #explain>h6").addClass("text-muted");
         } 
      	
      }
   
   
        
        
        // 태그 변경 시 값 ajax로 전달
        var tellCheck = function(){
        	
        	console.log("tellCheck 실행");
        	
	        var webTell = $("#webTell").prop("checked");
	        var commentTell = $("#commentTell").prop("checked");
	        var askTell = $("#askTell").prop("checked");
	        var rtTell = $("#rtTell").prop("checked");
	        
	        /*
	       	콘솔 체크용
	       	*/
	       /*  console.log("webTell : " + webTell);
	        console.log("commentTell : " + commentTell);
	        console.log("askTell : " + askTell);
	        console.log("rtTell : " + rtTell); 
	        console.log("-------------------"); */
	        
       		$.ajax({
	        	url : "updateTell",
	        	type : "GET",
	        	data : {
	        		webTell : webTell,
	        		commentTell : commentTell,
	        		askTell : askTell,
	        		rtTell : rtTell,
	        		memberId : '<%= loginMember.getMemberId() %>'
	        	},
	        	
	        	success : function(result) {
	        		if(result == 0) { 
	        			alert("알림 설정 수정 실패");
	        			location.href = "notification";
	        		}
	        	},
	        	
	        	error : function (){
	        		console.log("ajax 통신 실패");
	        	}
        	
        	});
        	
        }
      

    </script>






  </div>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>

</body>

</html>