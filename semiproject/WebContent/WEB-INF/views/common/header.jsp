<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.common.alert.model.vo.Alert"%>
    
<%
	
	List<Alert> alertList = (List<Alert>)request.getSession().getAttribute("alertList");
	String[] memberWebTell = (String[])request.getSession().getAttribute("memberWebTell");
	
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR&display=swap" rel="stylesheet">

</head>
<body>



	<% if(alertList == null) { %>
		<button class="btn btn-secondary dropdown-toggle btn-sm bg-transparent fixed-top" type="button"
	        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	        <img src="<%= request.getContextPath() %>/img/alertOff.png" width="50" style ="z-index:1030">
	      </button>
	      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" id="dropdown-menu" style ="z-index:1030">
	
	        <div class="dropdown-item  text-center" style="font-family: 'Noto Sans KR', sans-serif;">
	          알림 내역이 없습니다 :)
	        </div>
	        </div>
	<% } else if (alertList.isEmpty()){ %>
		<button class="btn btn-secondary dropdown-toggle btn-sm bg-transparent fixed-top" type="button"
	        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	        <img src="<%= request.getContextPath() %>/img/alertOff.png" width="50" style ="z-index:1030">
	      </button>
	      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" id="dropdown-menu" style ="z-index:1030">
	
	        <div class="dropdown-item  text-center" style="font-family: 'Noto Sans KR', sans-serif;">
	          <h6 class="alertOFF mt-2" style="font-family: 'Noto Sans KR', sans-serif;"> 알림 설정
	            <a href="<%= request.getContextPath() %>/mypage/notification">
	              <!-- 톱니바퀴 이미지 누를 시 알람설정 페이지로 이동 -->
	              <img src="<%= request.getContextPath() %>/img/mainview/img/alarmoff.png" width="30" height="30" style="padding-bottom: 3px;">
	            </a>
	          </h6>
	            알림 내역이 없습니다 :)
	        </div>
	        </div>
	
	<% } else { %>
		<button class="btn btn-secondary btn-sm bg-transparent fixed-top" type="button"
	        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	        <img src="<%= request.getContextPath() %>/img/alertOn.png" width="50" style ="z-index:1030">
	      </button>
	      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton" id="dropdown-menu" style ="z-index:1030">
	
	        <div class="dropdown-item  text-center">
	          <h6 class="alertOFF mt-2" style="font-family: 'Noto Sans KR', sans-serif;"> 알림 설정
	            <a href="<%= request.getContextPath() %>/mypage/notification">
	              <!-- 톱니바퀴 이미지 누를 시 알람설정 페이지로 이동 -->
	              <img src="<%= request.getContextPath() %>/img/mainview/img/alarmoff.png" width="30" height="30" style="padding-bottom: 3px;">
	            </a>
	          </h6>
	          
	          
	           <% for(Alert alert : alertList) {%>
					<% if(alert.getAlertType().equals("B") && memberWebTell[1].equals("Y")) { %>
			<div class="container mt-3" style="font-family: 'Noto Sans KR', sans-serif;">
				<div class="media border p-3">
					<div class="media-body">
					<% String alertContent = alert.getAlertContent();
                      if(alertContent.length()>12){
                   	   alertContent = alertContent.substring(0, 11) + "...";
                      } %>
						<a style="text-decoration: none; color: orange; cursor:pointer;" href="<%= alert.getAlertURL() %>">
						<b><%= alertContent %></b></a> 게시글에 댓글이 달렸습니다.
					</div>
				</div>
			</div>
					<% } else if (alert.getAlertType().equals("A") && memberWebTell[2].equals("Y")){ %>
			<div class="container mt-3" style="font-family: 'Noto Sans KR', sans-serif;">
				<div class="media border p-3">
					<div class="media-body">
						<a href="<%= request.getContextPath() %>/mypage/askList" style="text-decoration: none; color: orange;" onclick="deleteAlert();">
						<b>작성하신 1:1 문의에 답변이 작성되었습니다.</b></a>
					</div>
				</div>
			</div>
					<% } %>

			<% } %>
	          
	        </div>
	        </div>
	        
	        
	        <script>
	        	<%-- function deleteAlert(alertUrl) {
	        		var alertNo = $(this).next().text();
	        		var deleteNo = "<%= request.getContextPath()%>" + "/review/deleteAlert";
	        		
	        		console.log(deleteNo);
	        		console.log(alertUrl);
	        		
	        		$.ajax({
	    	        	url : "/review/deleteAlert",
	    	        	type : "GET",
	    	        	data : {
	    	        		alertNo : alertNo
	    	        	},
	    	        	
	    	        	success : function(result) {
	    	        		if(result > 0) {
	    	        			location.href = alertUrl;
	    	        		}
	    	        	},
	    	        	
	    	        	error : function (){
	    	        		
	    	        		console.log("ajax 통신 실패");
	    	        	}
	            	
	            	});
	        		
	        		return false;
	        		
	        		
	        	} --%>
	        	
	        
	        </script>
	
	
	<% } %>



        <!-- 게시글 이름 누를 시 해당 게시글로 이동 -->

		<!-- <div class="container mt-3">
			<div class="media border p-3">
				<div class="media-body">
					<a href="#" style="text-decoration:none; color: orange;"><b>&lt;강아지를 잃어버렸습니다&gt;</b></a>게시글에 댓글이 달렸습니다.
				</div>
			</div>
		</div>

		<h6 class="dropdown-header"> 댓글 알림</h6>
        <a href="#"><b>&lt;강아지를 잃어버렸습니다&gt;</b></a>게시글에 댓글이 달렸습니다.<br>
        <a href="#"><b>&lt;강아지를 잃어버렸습니다&gt;</b></a>게시글에 댓글이 달렸습니다.<br>


        게시판에 해당 필터링 적용한 목록으로 이동?
        <h6 class="dropdown-header"> 실시간 알림</h6>
        <mark>찾아주세요</mark><a href="#"><b>&lt;종로구&gt;</b></a>지역에 새로운 게시글이 있습니다.<br>
        <mark>봤어요</mark><a href="#"><b>&lt;종로구&gt;</b></a>지역에 새로운 게시글이 있습니다.<br>
        <mark>봤어요</mark><a href="#"><b>&lt;말티즈&gt;</b></a>품종의 새로운 게시글이 있습니다.<br>


        <h6 class="dropdown-header"> 1:1문의 답변 알림</h6>
        <a href="#"><b>&lt;건의사항&gt;</b></a>문의사항에 답변이 달렸습니다.<br>
        <a href="#"><b>&lt;홈페이지 알람기능 문의&gt;</b></a>문의사항에 답변이 달렸습니다.<br>
        <a href="#"><b>&lt;미씽펫&gt;</b></a>문의사항에 답변이 달렸습니다.<br>
      </div> -->
      

</body>
</html>