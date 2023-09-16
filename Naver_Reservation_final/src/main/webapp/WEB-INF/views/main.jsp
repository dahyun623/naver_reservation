<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<%@ include file="./header.jsp"%>
<link rel="stylesheet" href="./css/main.css" >
<script type="text/javascript" src="./js/main.js"></script>
</head>
<body>
    <div id="container">
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div>
                            <div class="container_visual">
                                <!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
                                <ul class="visual_img">
   
                                </ul>
                            </div>
                        </div>
                        <div class="prev_e" >
                            <div class="prev_inn" style="display: none;">
                                <div class="btn_pre_e"></div>
                            </div>
                        </div>
                        <div class="nxt_e" >
                            <div class="nxt_inn" style="display: none;">
                                <div class="btn_nxt_e" ></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul id="category" class="event_tab_lst tab_lst_min"></ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예약 가능한 상품이 <span class="pink"></span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <div class="lst_event_box">
                    </div>
                    <div class="lst_event_box">
                    </div>
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<%@include file="./footer.jsp" %>
</html>
