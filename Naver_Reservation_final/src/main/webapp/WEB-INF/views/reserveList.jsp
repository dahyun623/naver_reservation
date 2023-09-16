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
<link href="./css/reservation.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">

<script type="text/javascript" src="./js/jquery-3.7.0.js"></script>

<title>네이버 예약</title>
</head>

<body>

	<div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="http://www.naver.com/" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="#" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="#" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>

		<div class="ct">
			<div class="wrap_review_list">

				<div class="top_title">
					<a href="./detail.do?productId=${param.productId}" class="btn_back"
						title="이전 화면으로 이동"> <i class="fn fn-backward1"></i>
					</a>
					<h2>
						<span class="title"></span>
					</h2>
				</div>

				<div class="section_review_list">
					<div class="review_box">
						<h3 class="title_h3">예약</h3>
						<div class="short_review_area">
							<ul class="list_short_review">
								<li class="list_item" data-id="1">
									<div>
										<div class="reserv_area">
											<div class="review_area no_img"></div>
											<h4 class="preview_txt_tit">${dto.reservationName}</h4>
											<p class="reserv">
												<c:if test="${not empty dto.reservationContent}">
													<c:set var="changedContent"
														value="${dto.reservationContent.replace('<img', '<img style=\"width: 374px; height: auto; \"')}" />
						        						${changedContent}
												</c:if>
											</p>
										</div>
										<button type="button" class="bk_btn">
											<i class="fn fn-nbooking-calender2"></i> <span>예약하기</span>
										</button>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<hr>

	</div>

	<%@include file="./footer.jsp"%>

</body>
<script type="myTemplate" id="reservList">
  {{#each reserv}}
    <li class="list_item" data-id="{{shopNo}}">
        <div>
 		    <div class="reserv_area" >
				<div class="review_area no_img" ></div>
                <h4 class="preview_txt_tit">{{reservationName}}</h4>
                <p class="reserv">
                    {{{reservationContent}}}
                </p>
            </div>
				<button type="button" class="bk_btn">
	                 <i class="fn fn-nbooking-calender2"></i> 
	                 <span>예약하기</span>
	            </button>
        </div>
    </li>
{{/each}}
 </script>

<script type="text/javascript">
	$(function() {
		$(".bk_btn")
				.on(
						"click",
						function() {
							var productId = "${dto.productId}";
							var shopNo = "${dto.shopNo}";
							window.location.href = `./reserve.do?productId=${dto.productId}&shopNo=${dto.shopNo}`;
						});
	});
</script>
</html>