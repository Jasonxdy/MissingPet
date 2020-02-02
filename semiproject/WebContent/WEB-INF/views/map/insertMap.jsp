<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<style>
	.map_wrap {position:relative;width:100%;height:350px;}
    /* .title {font-weight:bold;display:block;} */
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
	</style>
</head>
<body>
<!--  -->
	<div id="map" ></div>
	<input id="latitude" name="latitude" style="display:none;" value="0">
	<input id="longitude" name="longitude" style="display:none;" value="0">
	<input id="mapAddress" type="textarea" name="mapAddress" style="display:none;" value="null">

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a0ceac8009d92843117c54f1ed676c5&libraries=services"></script>
	<script>
	var mapAddress = document.getElementById("mapAddress");
	console.log(mapAddress.value);
	var latitude = document.getElementById("latitude");
	var longitude = document.getElementById("longitude");
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 1 // 지도의 확대 레벨
	    };
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
	
	// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
	searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	
	// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
	    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
	        if (status === kakao.maps.services.Status.OK) {
	            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
	            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
	            
	            var content = '<div class="bAddr">' + detailAddr + 
	                        '</div>';
				//var mapAddress = !!result[0].road_address ? '도로명주소 : ' + result[0].road_address.address_name + '<br>지번 주소 : ' + result[0].address.address_name + '' : '';
				var mapAddress = !!result[0].road_address ? '도로명주소 : ' + result[0].road_address.address_name + '<br>' : '';
				mapAddress += '지번 주소 : ' + result[0].address.address_name + '';
				 
	            // 마커를 클릭한 위치에 표시합니다 
	            marker.setPosition(mouseEvent.latLng);
	            marker.setMap(map);
	
	            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
	            infowindow.setContent(content);
	            
	            $("#mapAddress").val(mapAddress);
	            console.log(mapAddress);
	            var LatLng = marker.getPosition();
	            $("#latitude").val(LatLng.getLat()); 
	            $("#longitude").val(LatLng.getLng()); 
	            infowindow.open(map, marker);
	        }   
	    });
	});
	
	// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
	kakao.maps.event.addListener(map, 'idle', function() {
	    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
	});
	
	function searchAddrFromCoords(coords, callback) {
	    // 좌표로 행정동 주소 정보를 요청합니다
	    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
	}
	
	function searchDetailAddrFromCoords(coords, callback) {
	    // 좌표로 법정동 상세 주소 정보를 요청합니다
	    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
	}
	
	// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
	function displayCenterInfo(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        var infoDiv = document.getElementById('centerAddr');
	
	        for(var i = 0; i < result.length; i++) {
	            // 행정동의 region_type 값은 'H' 이므로
	            if (result[i].region_type === 'H') {
	                break;
	            }
	        }
	    }    
	}
	</script>
</body>
</html>