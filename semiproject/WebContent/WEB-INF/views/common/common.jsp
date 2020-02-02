<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
    <div class="row" id="row">

	<%@ include file="sidebar.jsp"%>

    <%@ include file="header.jsp"%>

    
    
    
    
    
    
    
    
    <div class="container-fluid mt-5 pl-5 pr-5 pb-3">
        <div class="row card bg-light">
          <div class="col-md-12">
            <div class="container-fluid mt-3">
              <div class="row">
                <div class="col-md-12">
                  <h3>게시판 제목</h3>
                  <hr>
                </div>
              </div>
            </div>
          </div>

          <div class="col-md-12">
            <div class="card">
              <div class="card-body p-1 pl-2 pt-2">
                <span>
                  저희집 집나간 바둑이 찾아주세요
                </span>
                &nbsp;&nbsp;
                <span class="float-right">
                  2020-01-03 &nbsp;&nbsp;&nbsp;&nbsp; 조회 : 122 &nbsp;&nbsp;
                  <button class="flot-right" type="button">신고하기</button>
                </span>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-md-12">
              <div class="float-right">
                <button type="button" class="btn btn-primary m-3">수정</button>
                <button type="button" class="btn btn-primary m-3">삭제</button>
                <button type="button" class="btn btn-primary m-3">이전 글</button>
                <button type="button" class="btn btn-primary m-3">다음 글</button>
              </div>
            </div>
          </div>



          <!-- 이곳에 내용 넣어주시면 됩니다 -->











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
                    <span id="n-name">리트리버덕후</span><br>
                    <span>2020-01-20</span>
                  </div>
                  <div class="col-8">
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
                    <span id="n-name">리트리버덕후</span><br>
                    <span>2020-01-20</span>
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
                  <button type="button" class="btn btn-primary m-3">목록으로</button>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <%@ include file="footer.jsp"%>

</div>
</body>
</html>