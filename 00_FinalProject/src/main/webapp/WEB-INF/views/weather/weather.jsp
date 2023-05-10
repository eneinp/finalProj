<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="<%=request.getContextPath() %>"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<style type="text/css">
	#container{
		text-align: center;
		width: 800px;
		margin: 100px auto;
	}
	#content{
		margin: 50px auto;
	}
</style>

<div id="content">
	<input type="text" placeholder="지역 입력" id="loc">
	<input type="button" value="날씨 보기" onclick="moveWeather()">
</div>
	
<script type="text/javascript">
	function moveWeather() {
		let loc = $('#loc').val();
		location.href='${ctxPath }/detail.weather?loc='+loc;
	}
	
</script>