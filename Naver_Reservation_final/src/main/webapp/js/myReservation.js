$none = "<div class='err'>"
	+ "<i class='spr_book ico_info_nolist'></i>"
	+ "<h1 class='tit'>예약 리스트가 없습니다</h1>"
	+ "</div>";

//load 됐을 시 ajax를 실행해서 값들을 불러오고 그 값을 뿌려준다.
$(function() {
	ajax();
});
function ajax(){
	console.log("ajax실행");
	$.ajax({
		url: "./myReservationStatus.do",
		type: "get",
		dataType: "json",
		success: function(data) {
			console.log(data);
			summary_board(data);
		},
		error: function() {
			console.log("error");
		}
	})
}
function summary_board(data) {
	var num;
	$(".figure").eq("0").text(data["U"]);
	$(".figure").eq("1").text(data["R"]);
	$(".figure").eq("2").text(data["C"]);
	$(".figure").eq("3").text(data["A"]);
	for (let i = 0; i < $(".figure").length; i++) {
		num += $(".figure").eq(i).text();
		console.log(num);
	} if (num == 0) {
		printnone(data);
	} else {
		reservelist();
	}
}
//예약이 없는 경우
function printnone(data) {
	$(".wrap_mylist").html("");
	$(".wrap_mylist").after($none);
}

//예약이 있는 경우
function reservelist() {
	console.log("reservelist실행");
	$.ajax({
		url: "./myReservationList.do",
		type: "get",
		dataType: "json",
		success: function(lists) {
			//			var count = Object.keys(data["U"]).length
			//			console.log(count);
			//			console.log(data["U"][0]["reservNo"])
			console.log(Object.keys(lists)[0]);
			printlist(lists);
		},
		error: function() {
			console.log("error");
		}
	});
}

function printlist(lists) {
	$(".card_item").remove();
	for (let i = 0; i < Object.keys(lists).length; i++) {
		var keys = Object.keys(lists)[i];
		console.log(keys);
		var keylen = Object.keys(lists[keys]).length;
		console.log(keylen);
		for (let j = 0; j < keylen; j++) {
			console.log(lists[keys][j]);
			makecard(lists[keys][j]);
		}
	}
}
function makecard(list) {
	switch (list["status"]) {
		case "U":
		console.log(list)
			$(".card").eq(0).append("<article class='card_item'>"
										+ "<a href='#' class='link_booking_details'>"
										+ "<div class='card_body'>"
										+ "<div class='left'></div>"
										+ "<div class='middle'>"
										+ "<div class='card_detail'>"
										+ "<em class='booking_number'>No."+list["reservationDate"]+"</em>"
										+ "<h4 class='tit'>"+list["productDTO"][0]["productName"]+"</h4>"
										+ "<ul class='detail'>"
										+ "<li class='item'><span class='item_tit'>일정</span><em class='item_dsc'>"+list["day"]+" / "+list["personCount"]+"명 </em></li>"
										+ "<li class='item'><span class='item_tit'>내역</span><em class='item_dsc'>"+list["requestContent"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>장소</span><em class='item_dsc'>"+list["productDTO"][0]["address"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>업체</span><em class='item_dsc'>"+list["productDTO"][0]["productName"]+"</em></li>"
										+ "</ul>"
										+ "<div class='price_summary'><span class='price_tit'>결제 예정금액</span><em class='price_amount'><span>000,000,000</span><span class='unit'>원</span></em></div>"
										+ "<div class='booking_cancel'><button class='btn' onclick='cancle($(this))'><span>취소</span></button></div>"
										+ "</div>"
										+ "</div>"
										+ "<div class='right'></div>"
										+ "</div>"
										+ "<div class='card_footer'>"
										+ "<div class='left'></div>"
										+ "<div class='middle'></div>"
										+ "<div class='right'></div>"
										+ "</div>"
										+ "</a>"
										+ "<a href='#' class='fn fn-share1 naver-splugin btn_goto_share' title='공유하기'></a>"
										+ "</article>");
			break;
		case "R":
			console.log(list)
			$(".card").eq(1).append("<article class='card_item'>"
										+ "<a href='#' class='link_booking_details'>"
										+ "<div class='card_body'>"
										+ "<div class='left'></div>"
										+ "<div class='middle'>"
										+ "<div class='card_detail'>"
										+ "<em class='booking_number'>No."+list["reservationDate"]+"</em>"
										+ "<h4 class='tit'>"+list["productDTO"][0]["productName"]+"</h4>"
										+ "<ul class='detail'>"
										+ "<li class='item'><span class='item_tit'>일정</span><em class='item_dsc'>"+list["day"]+" / "+list["personCount"]+"명 </em></li>"
										+ "<li class='item'><span class='item_tit'>내역</span><em class='item_dsc'>"+list["requestContent"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>장소</span><em class='item_dsc'>"+list["productDTO"][0]["address"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>업체</span><em class='item_dsc'>"+list["productDTO"][0]["productName"]+"</em></li>"
										+ "</ul>"
										+ "<div class='price_summary'><span class='price_tit'>결제 예정금액</span><em class='price_amount'><span>000,000,000</span><span class='unit'>원</span></em></div>"
										+ "<div class='booking_cancel'><button class='btn' onclick='cancle($(this))'><span>취소</span></button></div>"
										+ "</div>"
										+ "</div>"
										+ "<div class='right'></div>"
										+ "</div>"
										+ "<div class='card_footer'>"
										+ "<div class='left'></div>"
										+ "<div class='middle'></div>"
										+ "<div class='right'></div>"
										+ "</div>"
										+ "</a>"
										+ "<a href='#' class='fn fn-share1 naver-splugin btn_goto_share' title='공유하기'></a>"
										+ "</article>");
			break;
		case "C":
			console.log(list)
			if(list["reviewStatus"]=="N"){
				$(".card").eq(2).append("<article class='card_item'>"
										+"<a href='#' class='link_booking_details'>"
										+"<div class='card_body'>"
										+"<div class='left'></div>"
										+"<div class='middle'>"
										+"<div class='card_detail'>"
										+ "<em class='booking_number'>No."+list["reservationDate"]+"</em>"
										+ "<h4 class='tit'>"+list["productDTO"][0]["productName"]+"</h4>"
										+ "<ul class='detail'>"
										+ "<li class='item'><span class='item_tit'>일정</span><em class='item_dsc'>"+list["day"]+" / "+list["personCount"]+"명 </em></li>"
										+ "<li class='item'><span class='item_tit'>내역</span><em class='item_dsc'>"+list["requestContent"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>장소</span><em class='item_dsc'>"+list["productDTO"][0]["address"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>업체</span><em class='item_dsc'>"+list["productDTO"][0]["productName"]+"</em></li>"
										+ "</ul>"
										+"<div class='booking_cancel'>"
										+"<a href='./reviewWrite.do?reservno="+list["reservNo"]+"&productId="+list["productDTO"][0]["productId"]+"'><button class='btn'><span>예매자 리뷰 남기기</span></button></a>"
										+"</div>"
										+"</div>"
										+"</div>"
										+"<div class='right'></div>"
										+"</div>"
										+"<div class='card_footer'>"
										+"<div class='left'></div>"
										+"<div class='middle'></div>"
										+"<div class='right'></div>"
										+"</div>"
										+"</a>"
										+"</article>");
			}else{
				$(".card").eq(2).append("<article class='card_item'>"
										+"<a href='#' class='link_booking_details'>"
										+"<div class='card_body'>"
										+"<div class='left'></div>"
										+"<div class='middle'>"
										+"<div class='card_detail'>"
										+ "<em class='booking_number'>No."+list["reservationDate"]+"</em>"
										+ "<h4 class='tit'>"+list["productDTO"][0]["productName"]+"</h4>"
										+ "<ul class='detail'>"
										+ "<li class='item'><span class='item_tit'>일정</span><em class='item_dsc'>"+list["day"]+" / "+list["personCount"]+"명 </em></li>"
										+ "<li class='item'><span class='item_tit'>내역</span><em class='item_dsc'>"+list["requestContent"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>장소</span><em class='item_dsc'>"+list["productDTO"][0]["address"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>업체</span><em class='item_dsc'>"+list["productDTO"][0]["productName"]+"</em></li>"
										+ "</ul>"
										+"<div class='booking_cancel'>"
										+"<a href='#'><button class='btn'><span>리뷰 완료</span></button></a>"
										+"</div>"
										+"</div>"
										+"</div>"
										+"<div class='right'></div>"
										+"</div>"
										+"<div class='card_footer'>"
										+"<div class='left'></div>"
										+"<div class='middle'></div>"
										+"<div class='right'></div>"
										+"</div>"
										+"</a>"
										+"</article>");
			}
			
			break;
		case "A":
			console.log(list)
			$(".card").eq(3).append("<article class='card_item'>"
										+"<a href='#' class='link_booking_details'>"
										+"<div class='card_body'>"
										+"<div class='left'></div>"
										+"<div class='middle'>"
										+"<div class='card_detail'>"
										+ "<em class='booking_number'>No."+list["reservationDate"]+"</em>"
										+ "<h4 class='tit'>"+list["productDTO"][0]["productName"]+"</h4>"
										+"<ul class='detail'>"
										+ "<li class='item'><span class='item_tit'>일정</span><em class='item_dsc'>"+list["day"]+" / "+list["personCount"]+"명 </em></li>"
										+ "<li class='item'><span class='item_tit'>내역</span><em class='item_dsc'>"+list["requestContent"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>장소</span><em class='item_dsc'>"+list["productDTO"][0]["address"]+"</em></li>"
										+ "<li class='item'><span class='item_tit'>업체</span><em class='item_dsc'>"+list["productDTO"][0]["productName"]+"</em></li>"
										+"</ul>"
										+"<div class='price_summary'>"
										+"<span class='price_tit'>결제 예정금액</span>"
										+"<em class='price_amount'>"
										+"<span>000,000,000</span>"
										+"<span class='unit'>원</span>"
										+"</em>"
										+"</div>"
										+"</div>"
										+"</div>"
										+"<div class='right'></div>"
										+"</div>"
										+"<div class='card_footer'>"
										+"<div class='left'></div>"
										+"<div class='middle'></div>"
										+"<div class='right'></div>"
										+"</div>"
										+"</a>"
									+"</article>");
			break;
	}
}


//취소 버튼 클릭시
function cancle($data){
	console.log($data.parent());
	$parent = $data.parent()
	$no = $parent.siblings(".booking_number").text();
	$num = $parent.siblings(".detail").find("em").eq(0).text();
	$title = $parent.siblings(".tit").text();
	console.log($num, $title);
	$(".pop_tit").children("span").text($title);
	$(".pop_tit").children(".sm").text($num);
	console.log($(".pop_tit"));
	$(".popup_booking_wrapper").attr("style","display:block");
}

//취소에서 닫기버튼
function closePopup(){
console.log("%%%%%");
$(".popup_booking_wrapper").attr("style","display:none");
$(".pop_tit").children("span").text(" ");
	$(".pop_tit").children(".sm").text(" ");
}

function cofirm(){
	console.log($no);
	$.ajax({
		url: "./myReservationCancel.do",
		type: "post",
		data:	{"reservationDate" : $no },
		success: function() {
			console.log("succ");
			ajax();
			$(".popup_booking_wrapper").attr("style","display:none");
		},
		error: function() {
			console.log("error");
		}
	});
}
