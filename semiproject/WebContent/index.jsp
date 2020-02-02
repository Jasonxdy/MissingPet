<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
    
<div class="container-fluid">

    <section class="row">
    
      <!-- 사이드바 -->
      <%@ include file="WEB-INF/views/common/sidebar.jsp"%>

      <div id="demo" class="w-100">
        <!-- 컨텐츠 시작 -->
        <div id="main-carousel" class="carousel slide" data-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img class=" d-block w-100" style="background-size:contain"
                src="http://cdn.ppomppu.co.kr/zboard/data3/2013/0108/1357646773_Animal__Samoyed__Husky__Wallpaper_%281920X1080%29.jpg"
                alt="First slide">
            </div>
            <div class="carousel-item">
              <img class=" d-block w-100" style="background-size:contain"
                src="http://cdn.ppomppu.co.kr/zboard/data3/2013/0108/1357646773_Animal__Samoyed__Husky__Wallpaper_%281920X1080%29.jpg"
                alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class=" d-block w-100" style="background-size:contain"
                src="http://cdn.ppomppu.co.kr/zboard/data3/2013/0108/1357646773_Animal__Samoyed__Husky__Wallpaper_%281920X1080%29.jpg"
                alt="Third slide">
            </div> 
            <ul class="carousel-indicators">
              <li data-target="#main-carousel" data-slide-to="0" class="active"></li>
              <li data-target="#main-carousel" data-slide-to="1"></li>
              <li data-target="#main-carousel" data-slide-to="2"></li>
            </ul> 
          </div>
        </div>
        <!-- 컨텐츠 끝 -->
		<%@ include file="WEB-INF/views/common/footer.jsp"%>
      </div>


      <!-- 웹페이지 알림 -->
      <%@ include file="WEB-INF/views/common/header.jsp"%>
      

    </section>
</div>

</body>
</html>