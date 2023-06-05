<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="<%=request.getContextPath() %>"/>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4sqz6l4y8y&submodules=geocoder"></script>
<div id="drive_wrap">
	<div id="map" style="height: 400px;"></div>
	
	<div id="drive_main_wrap">
		<div class="inp_drive">
			<input type="text" id="start_drive" placeholder="출발지 :" value="">
			<input type="text" id="drive" placeholder="도착지 :">
			<input type="button" id="search" value="드라이브 경로 추천" onclick="openwindows()">
			<span>※ 미 입력시 기본 (서울특별시 중구 남대문로1가 18) </span>
			<p id="addr"></p>
		</div>
	</div>
	
	<hr>
	
	<div id="drive_road_view">
			</div>
</div>