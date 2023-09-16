<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=7z7tfqma7w"></script>
    <link href="./css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="./css/detail.css">
    <script src="./js/jquery-3.7.0.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		$(".img_thumb").each(function(item){
			console.log($(this).attr("class"));
		});
		
		$('.list_item .has-image').each(function() {
	    	fetchImages($(this));
		});
		
	});

	document.addEventListener("DOMContentLoaded", function () {
        const swiper = new Swiper(".swiper-container", {
            loop: true,
            autoplay: {
                delay: 2000,
            },
            sourceMap: false
        });
    });
	$(document).on("click", ".info_tab_lst a", (e) => {
     	
	    	const idx = $(e.target).parent().index();
			console.log(idx);
		    $(".info_tab_lst a").removeClass("active");
		    $(".info_tab_lst a").eq(idx).addClass("active");
		    $(".detail_total_wrap > .detail").removeClass("hide");
		    $(".detail_total_wrap > .detail").addClass("hide");
		    $(".detail_total_wrap > .detail").eq(idx).removeClass("hide");	    
	    
	  });
	
	$(document).on("click",".section_store_details a", (e) =>{
		$(".section_store_details a").toggleClass("hide");
		$(".store_details p").toggleClass("hide");
	});
	
	function fetchImages($listItem) {
			  var image = $listItem.find('.imageVal:first').val();

	        $.ajax({
	            url: "./reviewMore.do",
	            async: true,
	            type: "POST",
	            data: {
	                image: image,
	                command: "thumbs"
	            },
	            dataType: "json",
	            success: function(resp){
	                var img = $('<img>', { 
	                    src: resp.imageUrl, 
	                    alt: '리뷰 이미지', 
	                    width: '90px', 
	                    height: '90px', 
	                    class: 'img_vertical_top'
	                });
	                
	                $listItem.find(".thumb").attr("href", resp.imageUrl).append(img);
	            }
	        });
//	    });
	}
	
	function go(){
		location.href="./reservList.do?productId="+$("#productId").val();
	}
</script>
<body>
    <div id="container">
        <div class="header fade">
            <header class="header_tit">
            	<input type="hidden" id="productId" value="${lists.get(0).productId}">
                <h1 class="logo">
                    <a href="./main.do" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./main.do" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
            </header>
        </div>
        <div class="ct main">
            <div>
                <div class="section_visual">
                    <header>
                        <h1 class="logo">
                            <a href="./main.do" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                            <a href="./main.do" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                        </h1>
                        <a href="./myReservation.do" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
                    </header>
			        <c:set var="listSize" value="${fn:length(lists)}"/>
                    <div class="swiper container_visual">
					    <ul class="swiper-wrapper visual_img detail_swipe">
					        <c:forEach begin="0" end="${listSize}" var="index">
					        	<li class="swiper-slide item slide-img">
			                   <div class="pagination">
			                       <div class="bg_pagination"></div>
			                       <div class="figure_pagination">
			                           <span class="num">${index+1}</span>
			                           <span class="num off">/ <span>${listSize+1}</span></span>
			                       </div>
			                   </div>
					                <img alt="" src="${lists[0].fileInfo[index].saveFileName}" style="width: 414px; height: 414px;">
						<span class="img_bg"></span>
					            <div class="visual_txt">
					                <div class="visual_txt_inn">
					                    <h2 class="visual_txt_tit"> <span>${lists[0].productName}</span> </h2>
					                    <p class="visual_txt_dsc"></p>
					                </div>
					            </div>
								</li>
					        </c:forEach>
					    </ul>
					    <div class="swiper-button-prev"></div>
					    <div class="swiper-button-next"></div>
					</div>
                    </div>
                    <div class="group_btn_goto"  style="display: none;">
                        <a class="btn_goto_home" title="홈페이지" href="#" target="siteUrl"> <i class="fn fn-home1"></i> </a>
                        <a class="btn_goto_tel" title="전화" href="#"> <i class="fn fn-call1"></i> </a>
						<a class="btn_goto_mail" title="이메일" href="#"> <i class="fn fn-mail1"></i> </a>
                        <a href="#" class="btn_goto_path" title="길찾기"> <i class="fn fn-path-find1"></i> </a>
                        <a href="#" class="fn fn-share1 naver-splugin btn_goto_share" title="공유하기"></a>
                    </div>
                </div>
                
                <div class="section_store_details">
				    <div class="store_details close3">
				        <p class="dsc">${lists[0].etc1}<br><span id="shortContentValue"></span></p>
				        <p class="dsc hide">${lists[0].etc1}<br><span>${lists[0].etc2}</span></p>
				    </div>
				    <a href="#dsc" class="bk_more _open">
				        <span class="bk_more_txt">펼쳐보기</span>
				        <i class="fn fn-down2"></i>
				    </a>
				    <a href="#dsc" class="bk_more _close hide">
				        <span class="bk_more_txt">접기</span>
				        <i class="fn fn-up2"></i>
				    </a>
				</div>
				
                <div class="section_event">
                    <div class="event_info_box">
                        <div class="event_info_tit">
                            <h4 class="in_tit"> <i class="spr_book ico_evt"></i> <span>이벤트 정보</span> </h4>
                        </div>
                        <div class="event_info">
                            <div class="in_dsc">[네이버예약 특별할인]<br></div>
                        </div>
                    </div>
                </div>
                <div class="section_btn"> <button type="button" class="bk_btn" onclick="go()"> <i class="fn fn-nbooking-calender2"></i> <span>예약하기</span> </button> </div>
                <!-- 리뷰 -->
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">방문자 리뷰</h3>
                        <div class="short_review_area">
                            <div class="grade_area">
                                <c:set var="scoreavg" value="${score.reviewavg*20}"></c:set>
                                <span class="graph_mask"> <em class="graph_value" style="width: ${scoreavg}%;"></em> </span>
                                <strong class="text_value"> <span>${score.reviewavg}</span> <em class="total">5.0</em> </strong>
                                <span class="join_count"><em class="green">${score.score}건</em> 등록</span>
                            </div>
                            <ul class="list_short_review">
							<c:forEach var="review" items="${reviews}" varStatus="vs" begin="0" end="2">
                            	<c:choose>
                            	<c:when test="${empty review.fileInfo[0].fileName}">
                            		<li class="list_item">
	                                    <div>
	                                        <div class="review_area no_img">
	                                            <p class="review">${review.content}</p>
	                                        </div>
	                                        <div class="info_area">
	                                            <div class="review_info"> <span class="grade">${review.score}.0</span> <span class="name">${review.emailId}</span> <span class="date">${review.regdate} ${review.gubun}</span> </div>
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
	                                            <div class="review_info"> <span class="grade">${review.score}.0</span> <span class="name">${review.emailId}</span> <span class="date">${review.regdate} ${review.gubun}</span> </div>
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
                    <a class="btn_review_more" href="./reviewMore.do?productId=${param.productId}"> <span>예매자 한줄평 더보기</span> <i class="fn fn-forward1"></i> </a>
                </div>
                <!-- 상세정보 + 오시는길 -->
                <div class="section_info_tab">
					<ul class="info_tab_lst">
				      <li class="item _detail">
				      	<a href="#detail_area" class="anchor active">상세정보</a>
				      </li>
				      <li class="item _path">
				      	<a href="#detail_location" class="anchor">오시는길</a>
				      </li>
				    </ul>
				  <div class="detail_total_wrap">
					<div class="detail">
                    <div class="detail_area_wrap" id="detail_area">
                        <div class="detail_area">
                        <div class="detail_info">
                                <h3 class="blind">상세정보</h3>
                                <ul class="detail_info_group">
                                    <li class="detail_info_lst">
                                        <strong class="in_tit">[소개]</strong>
                                        <p class="in_dsc">${lists[0].description}</p>
                                    </li>
                                    <li class="detail_info_lst"> <strong class="in_tit">[공지사항]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000"> </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    </div>
					<div class="detail hide">
                    <div class="detail_location" id="detail_location">
                        <div class="box_store_info no_topline">
                            <div id="map" style="width:100%;height:400px;"></div>
                            <a href="#" class="store_location" title="지도웹으로 연결">
                                <span class="img_border"></span>
                                <span class="btn_map"><i class="spr_book2 ico_mapview"></i></span>
                            </a>
                            <h3 class="store_name">${lists[0].productName}</h3>
                            <div class="store_info">
                                <div class="store_addr_wrap">
                                    <span class="fn fn-pin2"></span>
                                    <p class="store_addr store_addr_bold">${lists[0].address}</p>
                                </div>
                                <div class="lst_store_info_wrap">
                                    <ul class="lst_store_info">
                                        <li class="item"> <span class="item_lt"> <i class="fn fn-call2"></i> <span class="sr_only">전화번호</span> </span> <span class="item_rt"> <a href="tel:02-548-0597" class="store_tel">${lists[0].phone}</a></span> </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                   </div> 
                </div>
            </div>
        </div>
<%@ include file="./footer.jsp" %>
</body>
<script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
<script type="text/javascript">
const swiper = new Swiper('.swiper', {
	  autoplay: {
		  delay: 1000,
		  disableOnInteraction: false,
	  },

	  loop: true,

	  navigation: {
	    nextEl: '.swiper-button-next',
	    prevEl: '.swiper-button-prev',
	  },

	});

var shortContent = (${lists[0].etc2.length()} > 30) ? '${lists[0].etc2.substring(0, 29)}...' : '${lists[0].etc2}';
console.log(${lists[0].etc2.length()});
console.log(shortContent);
document.getElementById("shortContentValue").innerText = shortContent;

var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(${lists[0].lat}, ${lists[0].lng-0.006}),
    zoom: 14
});
var marker = new naver.maps.Marker({
    position: new naver.maps.LatLng(${lists[0].lat}, ${lists[0].lng}),
    map: map
});
</script>
</html>
