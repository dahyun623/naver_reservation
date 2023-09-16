<%@page import="com.min.edu.vo.ProductReservation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>

<link href="./css/reservation.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">

<script>
	var productId = "${param.productId}";
	var shopNo = "${param.shopNo}";
</script>
<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>
<script type="text/javascript" src="./js/reserve.js"></script>

<!-- 스와이퍼 라이브러리 추가 -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/swiper@10.0.4/swiper-bundle.min.js"></script>

<style type="text/css">
#calendar {
	margin: auto;
}

#calendar th {
	width: 80px;
}

#calendar td {
	width: 80px;
	height: 80px;
	text-align: left;
	vertical-align: top;
	position: relative;
}

.timeMenu, .personCnt {
	width: 380px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.personContainer {
	margin-bottom: 20px;
}

.menu-title {
	font-weight: bold;
}

.arrowImgP, .arrowImgT {
	display: inline-block;
	width: 32px;
	height: 32px;
	background-image: url("./img/spr_book.png");
	background-position: -120px -80px;
}

.timeContainer {
	width: 400px;
}

.timeTable {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.timeTable button {
	width: calc(30% - 10px);
	margin-bottom: 10px;
}
}
</style>


</head>

<body>
	<input type="hidden" id="productId" value="${param.productId}">
	<input type="hidden" id="shopNo" value="${param.shopNo}">

	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="./main.do" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="./main.do" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>

		<div class="ct">
			<div class="ct_wrap">

				<div class="top_title">
					<a href="./reservList.do?productId=${param.productId}"
						class="btn_back" title="이전 화면으로 이동"> <i
						class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title"></span>
					</h2>
				</div>


				<div class="group_visual">
					<div class="container_visual" style="width: 414px;">
						<ul class="visual_img">
							<li class="item" style="width: 414px;"><img alt=""
								class="img_thumb" src="${lists[0].fileInfo[0].saveFileName}">
								<span class="img_bg"></span>
								<div class="preview_txt">
									<em class="preview_txt_dsc">${lists.get(0).reservationName}</em>
								</div></li>
						</ul>
					</div>
				</div>

				<div class="section_store_details">
					<div class="store_details">
						<h3 class="in_tit">상세 설명</h3>
						<p class="dsc">
						<div style="width: 414px; text-align: center;">
							<c:if test="${not empty lists[0].reservationContent}">
								<c:set var="changedContent"
									value="${lists[0].reservationContent.replace('<img', '<img style=\"width: 400px; height: auto; \"')}" />
        							${changedContent}
								</c:if>
						</div>
					</div>
				</div>

				<div class="section_booking_ticket">
					<div class="ticket_body">

						<div class="calendarcontainer ">
							<table id="calendar" style="margin-top: 20px;">

								<caption
									style="width: auto; height: auto; margin: 0; padding: 0; overflow: visible; clip: auto; border: none; text-align: center; font-size: 17px; margin-bottom: 10px;">
									<a id="prevMonth"> ◀ </a> <span class="y"></span> . <span
										class="m"></span> <a id="nextMonth"> ▶ </a>
								</caption>

								<thead>
									<tr>
										<th style="color: red;">SUN</th>
										<th>MON</th>
										<th>TUE</th>
										<th>WED</th>
										<th>THU</th>
										<th>FRI</th>
										<th style="color: blue;">SAT</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
						</div>

						<strong style="color: #ccc;" id="selectedDay">날짜 : 
						<span>선택해주세요</span></strong> <br> <br>

						<div class="timeContainer">
							<div class="timeMenu">
								<div class="menu-title">시간</div>
								<div class="selected-Time">시간을 선택해주세요.</div>
								<a class="arrowImgT" title="화살표"></a>
							</div>

							<div class="timeTable" style="display: none;"></div>
						</div>

						<br>

						<div class="personContainer">
							<div class="personCnt">
								<div class="menu-title">인원</div>
								<div class="selected-option">인원을 선택해주세요.</div>
								<a class="arrowImgP" title="더하기"></a>
							</div>
							<div class="qty" style="display: none;">
								<div class="count_control">
									<div class="clearfix">
										<a id="minusButton"
											class="btn_plus_minus spr_book2 ico_minus3 disabled"
											title="빼기"></a> <input type="number" id="count_control_input"
											class="count_control_input disabled" value="0" readonly
											title="수량"> <a id="plusButton"
											class="btn_plus_minus spr_book2 ico_plus3" title="더하기"></a>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>


				<div class="section_booking_form">
					<div class="booking_form_wrap">
						<div class="form_wrap">
							<h3 class="out_tit">예매자 정보</h3>
							<div class="agreement_nessasary help_txt">
								<span class="spr_book ico_nessasary"></span> <span>필수입력</span>
							</div>


							<form class="form_horizontal">

								<div class="inline_form">
									<label class="label" for="name"> <span
										class="spr_book ico_nessasary">필수</span> <span>예매자</span>
									</label>
									<div class="inline_control">
										<input type="text" name="name" id="name" class="text"
											placeholder="네이버" maxlength="17">
									</div>
								</div>

								<div class="inline_form">
									<label class="label" for="tel"> <span
										class="spr_book ico_nessasary">필수</span> <span>연락처</span>
									</label>
									<div class="inline_control tel_wrap">
										<input type="tel" name="tel" id="tel" class="tel" value=""
											placeholder="휴대폰 입력 시 예매내역 문자발송">
										<div class="warning_msg">형식이 틀렸거나 너무 짧아요</div>
									</div>
								</div>

								<div class="inline_form">
									<label class="label" for="email"> <span
										class="spr_book ico_nessasary">필수</span> <span>이메일</span>
									</label>
									<div class="inline_control">
										<input type="email" name="email" id="email" class="email"
											value="" placeholder="crong@codesquad.kr" maxlength="50">
									</div>
								</div>

								<div class="inline_form last">
									<label class="label" for="message">예매내용</label>
									<div class="inline_control">
										<input type="text" name="requestContent" id="requestContent"
											class="requestContent" value="" placeholder="요청사항"
											maxlength="50">
									</div>
								</div>

							</form>

						</div>
					</div>
					<div class="section_booking_agreement">
						<div class="agreement all">
							<input type="checkbox" id="chk3" class="chk_agree"> <label
								for="chk3" class="label chk_txt_label"> <span>이용자
									약관 전체동의</span>
							</label>
							<div class="agreement_nessasary">
								<span>필수동의</span>
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i
								class="spr_book ico_arr_ipc2"></i> <span>개인정보 수집 및 이용 동의</span>
							</span> <a class="btn_agreement"> <span class="btn_text">보기</span> <i
								class="fn fn-down2"></i>
							</a>
							<div class="useragreement_details">
								&lt;개인정보 수집 및 이용 동의&gt;<br> <br> 1. 수집항목 : [필수] 이름,
								연락처, [선택] 이메일주소<br> <br> 2. 수집 및 이용목적 : 사업자회원과 예약이용자의
								원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 분쟁조정 해결을 위한 기록보존, 네이버 예약 이용 후
								리뷰작성에 따른 네이버페이 포인트 지급 및 관련 안내<br> <br> 3. 보관기간<br>
								- 회원탈퇴 등 개인정보 이용목적 달성 시까지 보관<br> - 단, 상법 및 ‘전자상거래 등에서의 소비자
								보호에 관한 법률’ 등 관련 법령에 의하여 일정 기간 보관이 필요한 경우에는 해당 기간 동안 보관함<br>
								<br> 4. 동의 거부권 등에 대한 고지: 정보주체는 개인정보의 수집 및 이용 동의를 거부할 권리가
								있으나, 이 경우 상품 및 서비스 예약이 제한될 수 있습니다.<br>
							</div>
						</div>
						<!-- [D] 약관 보기 클릭 시 agreement에 open 클래스 추가 -->
						<div class="agreement">
							<span class="chk_txt_span"> <i
								class="spr_book ico_arr_ipc2"></i> <span>개인정보 제3자 제공 동의</span>
							</span> <a class="btn_agreement"> <span class="btn_text">보기</span> <i
								class="fn fn-down2"></i>
							</a>
							<div class="useragreement_details custom_details_wrap">
								<div class="custom_details">
									&lt;개인정보 제3자 제공 동의&gt;<br> <br> 1. 개인정보를 제공받는 자 :
									미디어앤아트<br> <br> 2. 제공하는 개인정보 항목 : [필수] 네이버 아이디, 이름,
									연락처 [선택] 이메일 주소<br> <br> 3. 개인정보를 제공받는 자의 이용목적 :
									사업자회원과 예약이용자의 원활한 거래 진행, 고객상담, 불만처리 등 민원 처리, 서비스 이용에 따른 설문조사 및
									혜택 제공, 분쟁조정 해결을 위한 기록보존<br> <br> 4. 개인정보를 제공받는 자의
									개인정보 보유 및 이용기간 : 개인정보 이용목적 달성 시 까지 보관합니다.<br> <br> 5.
									동의 거부권 등에 대한 고지 : 정보주체는 개인정보 제공 동의를 거부할 권리가 있으나, 이 경우 상품 및 서비스
									예약이 제한될 수 있습니다.<br>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="box_bk_btn">
					<!-- [D] 약관 전체 동의가 되면 disable 제거 -->
					<div id="agreeBtn" class="bk_btn_wrap disable">
						<button type="button" class="bk_btn">
							<i class="spr_book ico_naver_s"></i> <span>예약하기</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="./footer.jsp"%>
</body>




</html>