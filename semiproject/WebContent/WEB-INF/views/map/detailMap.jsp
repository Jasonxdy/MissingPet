<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<style>
	.map_wrap {position:relative;width:100%;height:350px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
	</style>
</head>
<body>
	<div id="map" ></div>
	<!--  -->

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a0ceac8009d92843117c54f1ed676c5&libraries=services"></script>
	<script>
	var mapAddress = document.getElementById("mapAddress").value;
	var latitude = document.getElementById("latitude").value;
	var longitude = document.getElementById("longitude").value;
	var dMapAddress;
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
	        level: 1 // 지도의 확대 레벨
	    };
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	
	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	
	
	var geocoder = new kakao.maps.services.Geocoder();

	var coord = new kakao.maps.LatLng(latitude, longitude);
	
	var callback = function(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	    	var dMapAddress = !!result[0].road_address ? '도로명주소 : ' + result[0].road_address.address_name + '\n지번 주소 : ' + result[0].address.address_name + '' : '';
	    	var iwContent = '<div class="bAddr" style="padding:5px;">' + mapAddress + '<br><a href="https://map.kakao.com/link/map/' + dMapAddress + ',' + latitude + ',' + longitude + '" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,' + latitude + ',' + longitude + '" style="color:blue" target="_blank">길찾기</a></div>', 
	    	iwPosition = new kakao.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다

	    	var infowindow = new kakao.maps.InfoWindow({
	    	    position : iwPosition, 
	    	    content : iwContent 
	    	});
	    	infowindow.open(map, marker);
	    }
	};
	
	geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
	
	
	// 인포윈도우를 생성합니다
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	
		
	</script>
</body>
</html>