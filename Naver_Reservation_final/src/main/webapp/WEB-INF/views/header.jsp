<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="./css/style.css" rel="stylesheet">
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
</head>
<body>
	<div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./main.do" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <c:choose>
                	<c:when test="${empty reservationEmail}">
		                <a href="./bookingLoginForm.do" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>                	
                	</c:when>
                	<c:otherwise>
                		<a href="./myReservation.do" class="btn_my"> <span class="viewReservation" title="예약확인">${reservationEmail}</span> </a>
                	</c:otherwise>
                </c:choose>
            </header>
    </div>
    <hr>
</body>
</html>