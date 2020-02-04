<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아주세요 글쓰기</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/insert.css" type="text/css"/>
</head>
<body>

<div class="container-fluid">
    <div class="row" id="row">
    	<%@ include file="../common/sidebar.jsp"%>
    	
    	<div class="col-md-10 mt-4" id="con" style="margin-left:20rem">

        <div id="page-content-wrapper">
          <div class="card bg-light shadow mb-5 ml-4 mt-5 ">
            <div class="card-body">


              <!-- 이 안에다가 작성하세요!!!!!!!! -->


              <div class="container">
                <div class="row">

                  <section class="content">
                  
                    <h2 id="title-top"><a href="#">찾아주세요 글쓰기</a></h2>
                    <hr>
                    <form action="<%=request.getContextPath()%>/findBoard/insert" method="post" 
				  			enctype="multipart/form-data" role="form" onsubmit="return validate();">
                    <div class="col-md-8 col-md-offset-2" id="panelwrap">



                        <table id="tb">
                          <tr>
                            <th width="130px"><label>등록인</label></th>
                            <td><input type="text" size="14" value="<%= loginMember.getMemberName() %>" name="writer" disabled></td>
                          </tr>

                          <tr>
                            <th width="130px"><label>제목</label></th>
                            <td><input type="text" size="60" name="title" id="title" maxlength="100"></td>
                          </tr>
                          
                          <%
                          	String phone[] = loginMember.getMemberPhone().split("-");                 
                          %>

                          <tr>
                            <th><label>휴대폰번호</label></th>
                            <td colspan="2">
                            	<input type="number" value="<%= phone[0] %>" name="phone1" id="phone1" maxlength="3">&nbsp;-
                              	<input type="number" value="<%= phone[1] %>" name="phone2" id="phone2" maxlength="4">&nbsp;-
                              	<input type="number" value="<%= phone[2] %>" name="phone3" id="phone3" maxlength="4">
                            </td>
                          </tr>

                          <tr>
                            <th><label>E-mail</label></th>
                            <td><input type="email" size="24" value="<%= loginMember.getMemberEmail() %>" name="email" disabled></td>
                          </tr>

                          <tr>
                            <th><label>실종일</label></th>
                            <td><input type="date" name="missingDate" id="missingDate"></td>
                          </tr>

                          <tr>
                            <th><label>실종장소</label></th>
                            <td>
                              <select name="place1" id="place1"></select>
                              &nbsp;
							  <select name="place2" id="place2"></select>
							  <br><br>
							  <input type="text" size="40" name="place3" id="place3" maxlength="80">
                            </td>

                          </tr>
                          <tr>
                            <th><label>동물종류</label></th>
                            <td>
                              <select name="breed1" id="breed1"></select>
                              &nbsp;
							  <select name="breed3" id="breed3"></select>
                              &nbsp;&nbsp;&nbsp;
                              <label> 기타품종 : </label>
                              &nbsp;
                              <input type="text" size="20" name="breed4" id="breed4" style="display:none;">
                              <input type="hidden" name="breed2" id="breed2">
                            </td>
                          </tr>

                          <tr>
                            <th>성별</th>
                            <td>
                              <input type="radio" value="M" name="gender" id="gender">
                              <label for="남">수컷</label>
                              &nbsp;&nbsp;
                              <input type="radio" value="F" name="gender" id="gender">
                              <label for="여">암컷</label>
                              &nbsp;&nbsp;
                              <input type="radio" value="N" name="gender" id="gender">
                              <label for="중">중성화</label>
                            </td>
                          </tr>

                          <tr>
                            <th>사례금</th>
                            <td>
                              <input type="number" name="reward" id="reward" size="1" value="0"> 만원
                            </td>
                          </tr>



                        </table>
                        <br>
                        <textarea cols="85" rows="8" name="content" id="content"></textarea>



                    </div>

                    <!--오른쪽 배너-->
                    <div class="col-md-4" id="right-ban">
                      <label><b>알림 설정</b></label>
                      <br>
                      <input type="hidden" name="locationTell" value="N">
                      <input type="checkbox" name="locationTell" value="Y"> 장소&nbsp;
                      <input type="hidden" name="breedTell" value="N">
                      <input type="checkbox" name="breedTell" value="Y"> 품종&nbsp;
                      <input type="hidden" name="commentTell" value="N">
                      <input type="checkbox" name="commentTell" value="Y"> 댓글&nbsp;
                      <br>
                      <br>
                      <label><b>영상 첨부</b></label>
                      <br>
                      <input type="text" size="30" name="videoURL" id="videoURL" value="https://">
                      <br><br>
                      <!-- <label><b>사진 첨부</b></label>

                      <input type="file" name="img1"> -->
                      
                      <label class="input-group-addon mr-3 insert-label"><b>썸네일</b></label>
						<div class="boardImg" id="titleImgArea">
						<img id="titleImg" width="50" height="50">
						</div>
						
						<label class="input-group-addon mr-3 insert-label"><b>이미지 등록</b></label>
						<div class="form-inline mb-2">
							<div class="mr-2 boardImg" id="contentImgArea1">
								<img id="contentImg1" width="50" height="50">
							</div>
		
							<div class="mr-2 boardImg" id="contentImgArea2">
								<img id="contentImg2" width="50" height="50">
							</div>
		
							<div class="mr-2 boardImg" id="contentImgArea3">
								<img id="contentImg3" width="50" height="50">
							</div>
							
							<div class="mr-2 boardImg" id="contentImgArea4">
								<img id="contentImg4" width="50" height="50">
							</div>
						</div>
						
						<div id="fileArea">
					
						<%-- multiple 속성 : input 요소 하나에 둘 이상의 값을 입력할 수 있음을 명시 --%>
						<input type="file" id="img1" name="img1" onchange="LoadImg(this,1)">
						<input type="file" id="img2" name="img2" onchange="LoadImg(this,2)">
						<input type="file" id="img3" name="img3" onchange="LoadImg(this,3)">
						<input type="file" id="img4" name="img4" onchange="LoadImg(this,4)">
						<input type="file" id="img5" name="img5" onchange="LoadImg(this,5)">
						</div>
						
                      <br><br>
                      <label><b>지도표시</b></label>
                      <input type="hidden" size="10" name="spot" id="spot" value="no">
                      <div id="map">
                      	<%@ include file="/WEB-INF/views/map/insertMap.jsp"%>
                      </div>
                      <br>
                      <div id="bottom-bt">
                        <button type="submit" class="btn btn-primary m-2">등록하기</button>
                        <a href="boardList" class="btn btn-primary m-2">취소</a>
                        
                        
                      </div>

                    </div>
                    


                    <!--하단-->


					</form>
                  </section>

                </div>
              </div> <!-- container 종료-->

              <!-- 이 안에다가 작성하세요!!!!!!!! -->
            </div>
          </div>
        </div>



      </div>
    
    
    
    
    
    
    
    <%@ include file="../common/header.jsp"%>
		
	</div>
	<%@ include file="../common/footer.jsp"%>
</div>













</body>

<script>
$(document).ready(function() {
	
 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
   var area3 = ["대덕구","동구","서구","유성구","중구"];
   var area4 = ["광산구","남구","동구", "북구","서구"];
   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
   var area6 = ["남구","동구","북구","중구","울주군"];
   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
   var area16 = ["서귀포시","제주시","남제주군","북제주군"];
   
   var breed0 = ["종류 선택","개","고양이","기타"];
   var breed1 = ["골든리트리버","그레이 하운드","그레이트 덴","그레이트 피레니즈","꼬통 드 뚤레아","네오폴리탄 마스티프","노르포크 테리어","노리치 테리어","뉴펀들랜드","닥스훈트","달마시안","댄디 딘몬트 테리어","도고 아르젠티노","도베르만","도사","동경견","라브라도 리트리버","라바 압소","라이카","래빗 닥스훈트","랫 테리어","레이크랜드 테리어","로디지안 리즈백","로트바일러","마리노이즈","마스티프","말라뮤트","말티즈","맨체스터테리어","미니어쳐 닥스훈트","미니어쳐 불 테리어","미니어쳐 슈나우저","미너어쳐 푸들","미텔 스피츠","믹스견","바센지","바셋 하운드","버니즈 마운틴 독",
			"베들링턴 테리어","벨기에 그로넨달","벨기에 쉽독","벨기에 테뷰런","벨지안 셰퍼드 독","보더 콜리","보르조이","보스턴 테리어","복서","볼로네즈","부비에 데 플랑드르","불 테리어","불독","브뤼셀그리펀","브리타니 스파니엘","블랙 테리어","비글","비숑 프리제","비어디드 콜리","비즐라","빠삐용","사모예드","살루키","삽살개","샤페이","세인트 버나드","센트럴 아시안 오브차카","셔틀랜드 쉽독","셰퍼드","슈나우져","스코트쉬 테리어","스코티쉬 디어하운드","스탠다드 푸들","스테포드셔불테리어","스피츠","시바","시베리안 허스키","시베리안라이카","시잉프랑세즈","시츄","시코쿠",
			"실리햄 테리어","실키테리어","아나톨리안 셰퍼드","아메리칸 불독","아메리칸 스태퍼드셔 테리어","아메리칸 아키다","아메리칸 에스키모","아메리칸 코카 스파니엘","아메리칸 핏불 테리어","아메리칸 불리","아이리쉬 레드 앤 화이트 세터","아이리쉬 세터","아이리쉬 울프 하운드","아이리쉬 소프트코튼휘튼테리어","아키다","아프간 하운드","알라스칸 말라뮤트","에어델 테리어","오브차카","오스트랄리안 셰퍼드 독","오스트랄리안 캐틀 독","올드 잉글리쉬 불독","올드 잉글리쉬 쉽독","와이마라너","와이어 폭스 테리어","요크셔 테리어","울프독","웨스트 시베리언 라이카","웨스트하이랜드화이트테리어","웰시 코기 카디건",
			"웰시 코기 펨브로크","잉글리쉬 세터","제페니즈칭","진도견","차우차우","치와와","콜리","푸들","풍산견","프렌치 불독","휘펫"];
var breed2 = ["노르웨이 숲","데본 렉스","러시안 블루","레그돌 라가머핀","맹크스","먼치킨","메인쿤","발리네즈","버만","벵갈","봄베이","브리티시 쇼트헤어","사바나캣","샤트룩스","샴","셀커크 렉스","샴","셀커크 렉스","소말리","스노우 슈","스코티시폴드","스핑크스","시베리안 포레스트","싱가퓨라","아메리칸 쇼트헤어","아비니시안","재패니즈밥테일","터키시 앙고라","통키니즈","페르시안 친칠라","하바나 브라운","하일랜드 폴드","한국 고양이"];
   var breed3 = ["기타"];

 
 // 시/도 선택 박스 초기화

 $("#place1").each(function() {
  $selsido = $(this);
  $.each(eval(area0), function() {
   $selsido.append("<option value='"+this+"'>"+this+"</option>");
  });
  $selsido.next().append("<option value=''>구/군 선택</option>");
 });

 // 시/도 선택시 구/군 설정

 $("#place1").change(function() {
  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
  var $gugun = $(this).next(); // 선택영역 군구 객체
  $("option",$gugun).remove(); // 구군 초기화

  if(area == "area0")
   $gugun.append("<option value=''>구/군 선택</option>");
  else {
   $.each(eval(area), function() {
    $gugun.append("<option value='"+this+"'>"+this+"</option>");
   });
  }
 });
 
 // 품종 선택
 
 	$("#breed1").each(function() {
	  $selanimal = $(this);
	  $.each(eval(breed0), function() {
	   $selanimal.append("<option value='"+this+"'>"+this+"</option>");
	  });
	  $selanimal.next().append("<option value=''>품종 선택</option>");
	 });
 
	 $("#breed1").change(function() {
	  var breed = "breed"+$("option",$(this)).index($("option:selected",$(this)));
	  var $jong = $(this).next(); 
	  $("option",$jong).remove(); 

	  if(breed == "breed0")
	   $jong.append("<option value=''>품종 선택</option>");
	  else {
	   $.each(eval(breed), function() {
	    $jong.append("<option value='"+this+"'>"+this+"</option>");
	   });
	  }
	  
	  if($("#breed1").val() == "기타"){
			$("#breed4").css("display","inline-block");
		} else{
			$("#breed4").css("display","none");
		}
	});
	 
	$("#phone1").on("input",function(){
			if ($(this).val().length > $(this).prop("maxLength")){
	            $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
			}
	});
	$("#phone2").on("input",function(){
		if ($(this).val().length > $(this).prop("maxLength")){
            $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
		}
	});
	$("#phone3").on("input",function(){
		if ($(this).val().length > $(this).prop("maxLength")){
            $(this).val($(this).val().slice(0, $(this).prop("maxLength")));
		}
	});
	
});

function validate() {
	if ($("#title").val().trim().length == 0) {
		alert("제목을 입력해 주세요.");
		$("#title").focus();
		return false;
	}
	
	if ($("#phone1").val().trim().length == 0 || $("#phone2").val().trim().length == 0 || $("#phone3").val().trim().length == 0) {
		alert("핸드폰 번호를 입력해 주세요.");
		$("#phone1").focus();
		return false;
	}
	
	if($("#missingDate").val().length==0){
		alert("실종일을 입력해주세요.");
		return false;
	}
	
	if ($("#place1").val() == "시/도 선택") {
		alert("실종 장소를 입력해 주세요.");
		$("#place1").focus();
		return false;
	}
	
	if ($("#place3").val().trim().length == 0) {
		alert("상세주소 입력해 주세요.");
		$("#place3").focus();
		return false;
	}
	
	if ($("#breed1").val() == "종류 선택") {
		alert("동물종류를 입력해 주세요.");
		$("#breed1").focus();
		return false;
	} else if($("#breed1").val() == "기타" && $("#breed4").val().trim().length == 0){
		alert("상세 종류를 입력해 주세요.");
		$("#breed4").focus();
		return false;
	}
	
	 if($("#breed1").val()=="기타"){
		$("#breed2").val($("#breed4").val());
	} else{
		$("#breed2").val($("#breed3").val());
	}
	 
	if($(':radio[name="gender"]:checked').length < 1){
			alert("성별을 입력해 주세요.");
			return false;
	}
	
	if($("#reward").val().trim().length == 0){
		alert("사례금을 입력해 주세요.");
		$("#reward").focus();
		return false;
	}
	
	if($("#content").val().trim().length == 0){
		alert("내용을 입력해 주세요.");
		$("#content").focus();
		return false;
	}
	
	if ($("#videoURL").val().trim().length == 0) {
		alert("URL을 입력해 주세요.");
		$("#videoURL").focus();
		return false;
	}
	
	if ($("#spot").val().trim().length == 0) {
		alert("장소을 입력해 주세요.");
		$("#spot").focus();
		return false;
	}
	
}

$(function () {
	// 파일 선택 버튼이 있는 영역을 보이지 않게함
	$("#fileArea").hide();
	
	// 이미지 영역 클릭 시 파일 첨부창 띄우기
	$("#titleImgArea").click(function(){
		$("#img1").click();
	});
	
	$("#contentImgArea1").click(function(){
		$("#img2").click();
	});
	
	$("#contentImgArea2").click(function(){
		$("#img3").click();
	});
	
	$("#contentImgArea3").click(function(){
		$("#img4").click();
	});
	
	$("#contentImgArea4").click(function(){
		$("#img5").click();
	});
});

function LoadImg(value, num) {
	// 파일 업로드 시 업로드된 파일의 경로는 files 라는 배열이 생성되며 저장됨
	if(value.files && value.files[0]){
		// -> 파일이 선택이 된 경우
		var reader = new FileReader();
		
		reader.onload = function(e){
			switch(num){
			case 1 : $("#titleImg").prop("src", e.target.result); break;
			case 2 : $("#contentImg1").prop("src", e.target.result); break;
			case 3 : $("#contentImg2").prop("src", e.target.result); break;
			case 4 : $("#contentImg3").prop("src", e.target.result); break;
			case 5 : $("#contentImg4").prop("src", e.target.result); break;
			}
		}
		// file에서 내용(Content)을 읽어옴 + base64 인코딩의 경로를 반환
		reader.readAsDataURL(value.files[0]);
	}
}

</script>

</html>