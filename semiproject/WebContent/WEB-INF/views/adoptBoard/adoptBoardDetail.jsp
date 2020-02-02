<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.semiproject.board.model.vo.Attachment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.semiproject.board.model.vo.Animal"%>
<%@page import="com.kh.semiproject.adoptBoard.model.vo.AdoptBoard"%>
<%@page import="com.kh.semiproject.member.model.vo.Member"%>  
<%@page import="com.kh.semiproject.board.model.vo.BoardHJ"%>
<%@page import="com.kh.semiproject.map.model.vo.Map"%>   

<%
	BoardHJ board = (BoardHJ)request.getAttribute("board");
	AdoptBoard adoptBoard = (AdoptBoard)request.getAttribute("adoptBoard");
	Animal animal = (Animal)request.getAttribute("animal");
	Member member = (Member)request.getAttribute("member");
	ArrayList<Attachment> files = (ArrayList<Attachment>)request.getAttribute("files");
	Map map = (Map)request.getAttribute("map");
		
	String currentPage = request.getParameter("currentPage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>


.container{margin-bottom: 20px;}

@media (min-width: 1200px){
    .container{
      max-width:1500px;
    }
    
    
    }

/*    --------------------------------------------------
	:: General
	-------------------------------------------------- */
    
    .content { min-width: 100%;}
    
    .content h1 {
        text-align: center;
    }
    .content .content-footer p {
        color: #6d6d6d;
        font-size: 12px;
        text-align: center;
    }
    .content .content-footer p a {
        color: inherit;
        font-weight: bold;
    }
    
    /*	--------------------------------------------------
        :: Table Filter
        -------------------------------------------------- */
  

    #tb tr {
        border-bottom: 1px solid lightgray;
    }

    #tb tr:last-child {
        border-bottom: none;
    }

    #tb tr th{
        padding-top: 15px;
        padding-bottom: 15px;
        color: cornflowerblue;
       
    }

    #tb tr td{
        padding-top: 15px;
        padding-bottom: 15px;
        width : 500px
    }

    #map {

        width : 320px;
        height: 250px;
        background-color: lightpink;
        margin-top:20px;
    }



    
    b {
        color:cornflowerblue;
    }


    #yes{ background-color: #5cb85c;
        border-radius: 3mm; 
        width:100px;
        margin-top: 5px;
        margin-left: 25px; 
        text-align: center;
        color:white;
   }

   #bt-rig{
    float: right;
    margin-right: 50px;
    margin-top:5px;
    width : 100px;
    height: 35px;
    text-align: center;
   }

   #title{
      border-radius: 10px;
      border:1px solid lightgray;
      box-shadow: 2px 2px 3px rgba(135,139,133,0.4);
      height: 45px;
      padding-left:20px;
      margin-bottom : 20px;
   }
   
   #title span{
       line-height: 40px;
   }


   #title-top{

    margin-bottom: 30px;
   }

   #title-top a{ 
        color: black;
        text-decoration: none;
        font-weight: bold;
       }

    #panelwrap{
    width: 70%;
    height: 80%;
        float:left;}
    
  

    #right-tab{
        width: 30%;
        float:left;
        margin-top: 10px;
    
    }

    #right-tab table{
        width : 320px;
    }

    #right-tab tr {
        border-top : 1px solid ivory;
        background-color: white;
    }

    #right-tab tr th{
        background-color:wheat;
        color : gray;
        text-align: center;
    }

    #right-tab tr td{
        border-left : 1px solid  ivory;
        padding : 5px 5px 5px 20px;
    }


    #table-r{
        padding : 5px 5px 5px 5px;
    }

.custom-select{
    width : 80px;
    display: inline-block;
}

#text-tt{
    width: 150px;
    display: inline-block;
    margin-left: 10px; 
    height: 35px;
 
} 
#num {
    margin-top:20px;
    display: inline-block;
    margin-left: 200px;
}

#check {
    display: inline-block;
    margin-left: 80px;
}

#bt-bt{ 
     display: inline-block;
    margin-left: 30px;
}

#text{
    padding-top: 30px;
    width: 600px;
    height: 200px;
    border: 1px solid lightgray;
}

#imgdiv1{
    display: block;
    float:left;
    margin-right: 30px;
    margin-left: 10px;

}

#imgdiv2{
    display: block;
    float:left;
    margin-right: 30px;
    margin-left: 10px;
    margin-top: 20px;

}

    
#pic {
    margin-top: 30px;
    margin-bottom: 10px;
    height: 480px;
    width: 600px;
}


#textarea{
    display: block;
    float:left;
    margin-right: 30px;
    margin-left: 30px;
    margin-top: 0px;
    width : 80%;
}



#bottom-bt{
    margin-top:8px;
    display: block;
    float:left;
   

}

#comment-Wrapper{
    background-color: lightgray;
    
}

#comment{
    min-height: 90px;
    border: 1px solid white;
    border-radius: 20px;
}



#com{
    display: block;
    float:left;
    height: 70px;
    padding : 10px;
    width : 800px;
    border: 1px solid lightgray;
    border-radius: 20px;
    margin-bottom: 20px;
}
</style>
</head>
<body>
<div class="container-fluid">
    <div class="row" id="row">
		<%@ include file="../common/sidebar.jsp"%>

<!--  -->
			<div class="container-fluid pl-5 pr-5 pb-3"
				style="min-height: 89.3vh; margin-left:20rem;">
				<div class="row card bg-light">
					<div class="col-md-12">
						<div class="container-fluid mt-3">
							<div class="row">
								<div class="col-md-12">
									<h3>분양합니다(글읽기)</h3>
									<hr>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="card">
							<div class="card-body p-1 pl-2 pt-2">
								<span> <%= board.getBoardTitle() %> </span> &nbsp;&nbsp; <span
									class="float-right"> <%= board.getBoardModifyDate() %> &nbsp;&nbsp;&nbsp;&nbsp;
									조회 : <%= board.getBoardCount() %> &nbsp;&nbsp;
									<button class="flot-right" type="button">신고하기</button>
								</span>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="float-right">
								<% if(loginMember != null && (board.getBoardWriter().equals(loginMember.getMemberId()))){ %>
						
								<a href="updateForm?no=<%=board.getBoardNo() %>" class="btn btn-primary m-3">수정</a>
								<button type="button" class="btn btn-primary m-3" id="deleteBtn">삭제</button>
								<% } %>
								<button type="button" class="btn btn-primary m-3">이전 글</button>
								<button type="button" class="btn btn-primary m-3">다음 글</button>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-md-8">
							<% if(files != null){ %>
							<div id="adoptCarousel" class="carousel slide p-2"
								data-interval="false" data-ride="carousel" style="height:35rem;">
								<!-- 부족한 영역 배경색 채움 ,data-interval="false" 슬라이드 정지 -->
								<ol class="carousel-indicators" style="margin-bottom: 1%;">
									<li data-target="#adoptCarousel" data-slide-to="0"
										class="active"></li>
									<% for(int i = 1 ; i< files.size() ; i++){ %>
									<li data-target="#adoptCarousel" data-slide-to="<%= i %>"></li>
									<% } %>
								</ol>
								<div class="carousel-inner" style="height:110%">
									<% 
										String src = request.getContextPath()+"/resources/uploadImages/noimage.png";
										for(Attachment file : files){
											src = request.getContextPath()+"/resources/uploadImages/"+file.getFileChangeName();
											if(file.getFileLevel()==0){
												%>
												<div class="carousel-item active h-100">
													<img src="<%= src %>" class="d-block w-100 h-100" alt="...">
												</div>
											<% }else {%>
												<div class="carousel-item h-100">
												<img src="<%= src %>" class="d-block w-100 h-100" alt="...">
												</div>
												
											<% } %>
										<% } %>
								</div>
								
								<a class="carousel-control-prev" href="#adoptCarousel"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> 
								<a class="carousel-control-next" href="#adoptCarousel"
									role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span> <span
									class="sr-only">Next</span>
								</a>
							</div>
							<% } else { %>
							<div id="adoptCarousel" class="carousel slide p-2"
								data-interval="false" data-ride="carousel" style="height:35rem;">
								<!-- 부족한 영역 배경색 채움 ,data-interval="false" 슬라이드 정지 -->
								<ol class="carousel-indicators" style="margin-bottom: 1%;">
									<li data-target="#adoptCarousel" data-slide-to="0"
										class="active"></li>
									<!-- <li data-target="#adoptCarousel" data-slide-to="1"></li> -->
								</ol>
								<div class="carousel-inner" style="height:110%">
												<div class="carousel-item active h-100">
													<img src="<%=request.getContextPath() %>/resources/uploadImages/noimage.png" class="d-block w-100 h-100" alt="...">
												</div>
								</div>
								
								<a class="carousel-control-prev" href="#adoptCarousel"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> 
								<a class="carousel-control-next" href="#adoptCarousel"
									role="button" data-slide="next"> <span
									class="carousel-control-next-icon" aria-hidden="true"></span> <span
									class="sr-only">Next</span>
								</a>
							</div>
							
							<% } %>
								

						</div>




						<div class="col-md-4" id="right-tab">
							<table>
								<tr>
									<th width="100px;">등록인</th>
									<td><%= member.getMemberName() %></td>
								</tr>
								<tr>
									<th>연락처</th>
									<td><%= adoptBoard.getaBoardPhone() %></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td><%= member.getMemberEmail() %></td>
								</tr>
								<tr>
									<th>분양장소</th>
									<td><%= adoptBoard.getaBoardLocation() %></td>
								</tr>
								<tr>
									<th>동물 종류</th>
									<td><%= animal.getAnimalType() %></td>
								</tr>
								<tr>
									<th>품종</th>
									<td><%= animal.getAnimalBreed() %></td>
								</tr>
								<tr>
								<tr>
								<%
									String gender = null;
									switch(animal.getAnimalGender()){
									case "M": gender = "수컷"; break;
									case "F": gender = "암컷"; break;
									case "N": gender = "중성화"; break;
									}
								%>
									<th>성별</th>
									<td><%= gender %></td>
								</tr>
								<tr>
									<th>중성화 여부</th>
									<td><%= adoptBoard.getaBoardNeutral() %></td>
								</tr>
								<tr>
									<th>예방접종 여부</th>
									<td><%= adoptBoard.getaBoardNeutral() %></td>
								</tr>
								<tr>
									<th>건강상태</th>
									<td><%= adoptBoard.getaBoardHealth() %></td>
								</tr>
								<tr>
									<th>분양금</th>
									<td><%= adoptBoard.getaBoardCost() %>만원</td>
								</tr>
							</table>
							<%-- <% if(request.getAttribute("map") != null) { %>
							<input id="latitude" name="latitude" style="display:none;" value="<%=map.getMapLatitude()%>">
							<input id="longitude" name="longitude" style="display:none;" value="<%=map.getMapLongitude()%>">
							<input id="mapAddress" type="textarea" name="mapAddress" value="<%=map.getMapAddress() %>" style="display:none;">
							<% } %>
							<div id="map">
								<%@ include file="/WEB-INF/views/map/detailMap.jsp"%>
							</div> --%>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12 p-5">
							<div class="card mb-2">
								<div class="card-body">
									<div><%= board.getBoardContent() %></div>
								</div>
							</div>
						</div>
					</div>






					<div class="row">

						<div class="col-md-12 p-5">

							<div id="comment-Wrapper" class="container">
								<div id="comment" class="row ml-1 mt-2 mb-2">
									<div id="imgdiv1" class="col-auto">
										<img
											src="https://postfiles.pstatic.net/MjAxNzEyMjlfNDQg/MDAxNTE0NTExNDEyNDU0.96lwkH4peyvDrTpX7wG2Zv5a7Gmy8YlxhwRvVMjKBpwg.NF6iNTCZ1o5q6pETOTjHpwhg85y1_Dt1sx50E8sxinwg.PNG.lovetotalk/12%EA%B7%80%EC%97%AC%EC%9A%B4_%EA%B0%95%EC%95%84%EC%A7%80_%EC%82%AC%EC%A7%84_%281%29-001.png?type=w1"
											width="70px" height="70px">
									</div>
									<div id="comment-Writer" class="col-auto">
										<span id="n-name">리트리버덕후</span><br> <span>2020-01-20</span>
									</div>
									<div class="col-md-8">
										<span>안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</span>

									</div>
									<div>
										<button type="button" class="btn btn-primary m-3">수정</button>
										<button type="button" class="btn btn-primary m-3">삭제</button>
									</div>


									&nbsp;&nbsp;&nbsp;&nbsp;

								</div>

								<div id="comment" class="row ml-1 mt-2 mb-2">
									<div id="imgdiv1" class="col-auto">
										<img
											src="https://postfiles.pstatic.net/MjAxNzEyMjlfNDQg/MDAxNTE0NTExNDEyNDU0.96lwkH4peyvDrTpX7wG2Zv5a7Gmy8YlxhwRvVMjKBpwg.NF6iNTCZ1o5q6pETOTjHpwhg85y1_Dt1sx50E8sxinwg.PNG.lovetotalk/12%EA%B7%80%EC%97%AC%EC%9A%B4_%EA%B0%95%EC%95%84%EC%A7%80_%EC%82%AC%EC%A7%84_%281%29-001.png?type=w1"
											width="70px" height="70px">
									</div>
									<div id="comment-Writer" class="col-auto">
										<span id="n-name">리트리버덕후</span><br> <span>2020-01-20</span>
									</div>
									<div class="col-8">
										<span>안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요</span>
									</div>
									<div>
										<button type="button" class="btn btn-primary m-3">수정</button>
										<button type="button" class="btn btn-primary m-3">삭제</button>
									</div>
								</div>

								<div class="row">
									<div class="col-8 mr-5">
										<textarea style="resize: none; width: 100%;">d</textarea>
									</div>
									<div class="col-2 ml-5">
										<button type="button" class="btn btn-primary btn-lg">댓글작성</button>
									</div>
								</div>
							</div>

							<div class="col-md-12">
								<div class="text-center">
									<a href="boardList?currentPage=<%= currentPage %>" class="btn btn-primary m-3">목록으로</a>
								</div>
							</div>
						</div>
					</div>


				</div>

			</div>


			<%@ include file="../common/header.jsp"%>
	</div>
	<%@ include file="../common/footer.jsp"%>
</div>
<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=<%=board.getBoardNo() %>";
		});
</script>

</body>
</html>