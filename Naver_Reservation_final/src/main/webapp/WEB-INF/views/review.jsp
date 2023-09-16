<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="./css/style.css" rel="stylesheet">
</head>

<body>
<input type="hidden" id="productId" value="${param.productId}">
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
		<%@ include file="./header.jsp" %>
		<script type="text/javascript" src="./js/reviewMore.js"></script>
<!-- 			<header class="header_tit"> -->
<!-- 				<h1 class="logo"> -->
<!-- 					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a> -->
<!-- 					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a> -->
<!-- 				</h1> -->
<!-- 				  <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a> -->
<!-- 			</header> -->
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                        <a href="javascript:history.back(-1)" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="./detail.do?productId=${param.productId}">${score.productName}</a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">사용자 리뷰</h3>
                        <div class="short_review_area">
                            <div class="grade_area"> <span class="graph_mask"> <em class="graph_value" style="width: ${score.reviewavg / 5.0 * 100}%;"></em> </span> <strong class="text_value"> <span>${score.reviewavg}</span> <em class="total">5.0</em> </strong> <span class="join_count"><em class="green">${score.score}건</em> 등록</span>                                </div>
                            <ul class="list_short_review">
                            	<c:forEach var="review" items="${lists}" varStatus="vs">
                            	<c:choose>
                            	<c:when test="${empty review.fileInfo[0].fileName}">
                            		<li class="list_item">
	                                    <div>
	                                        <div class="review_area no_img">
	                                            <p class="review">${review.content}</p>
	                                        </div>
	                                        <div class="info_area">
	                                            <div class="review_info"> <span class="grade">${review.score}.0</span> <span class="name">${review.emailId}</span> <span class="date">${review.regdate}  ${review.gubun}</span> </div>
	                                        </div>
	                                    </div>
	                                </li>
                            	</c:when>
                            	<c:otherwise>
                            		<li class="list_item">
	                                    <div>
	                                        <div class="review_area has-image">
	                                            <div class="thumb_area">
	                                            <c:forEach var="fileInfo" items="${review.fileInfo}" varStatus="vs">
								                    <input type="hidden" class="imageVal" value="${fileInfo.saveFileName}">
								                </c:forEach>
	                                                <a href="#" class="thumb" title="이미지 크게 보기">
	                                                </a> 
	                                                <span class="img_count">${fn:length(review.fileInfo)}</span>                     
	                                                </div>
	                                           <p class="review">${review.content}</p>
	                                        </div>
	                                        <div class="info_area">
	                                            <div class="review_info"> <span class="grade">${review.score}.0</span> <span class="name">${review.emailId}</span> <span class="date">${review.regdate}  ${review.gubun}</span> </div>
	                                        </div>
	                                    </div>
                                	</li>
                            	</c:otherwise>
                            </c:choose>
                            </c:forEach>
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                    <a class="btn_review_more" href="#"><span>방문자 리뷰 더보기</span> <i class="fn fn-down2"></i> </a>
                </div>
            </div>
        </div>
        <hr> </div>
		<footer>
	        <div class="gototop">
	            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
	        </div>
	        <div id="footer" class="footer">
	            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
	            <span class="copyright">© NAVER Corp.</span>
	        </div>
	    </footer>
</body>

</html>
