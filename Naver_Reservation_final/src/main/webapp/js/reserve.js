//업체 사진에 슬라이드 적용
document.addEventListener("DOMContentLoaded", function() {
	const swiper = new Swiper(".swiper-container", {
		loop: true,
		autoplay: {
			delay: 2000,
		},
		sourceMap: false
	});
});


//달력 변수
var currentDate = new Date();
var currentDay = currentDate.getDate();
var currentMonth = currentDate.getMonth()+1;

//로드 되자마자 현재 날짜로 달력 한 번 부르기
$(function(){
	var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1;
	ajaxCalendar(year, month);
})

//달력 부르기
function ajaxCalendar(year, month){

	if(month > 12){
		month = 1;
		year ++;
	}
	
	if(month<1){
		month=12;
		year--;
	}
	
	console.log(isTwo(month), typeof isTwo(month));
	$('.y').text(year);
	$('.m').text(isTwo(month));
	
	var culDate = new Date(year, month-1 , 1);
  	var dayOfWeek = culDate.getDay();
  	lastDay = new Date(year, month, 0).getDate();
 	beforeDay = new Date(year, month-1, 0).getDate();
 	
	console.log(dayOfWeek, lastDay);
	
	$.ajax({
		url:"./shopCalendar.do",
		type: "get",
		async:true,
		data: {
		      "productId": productId,
		      "shopNo": shopNo
		   	  },
		dataType:"JSON",
		success:function(resp){
			console.log(typeof resp,resp);
			 // 테이블의 tbody 요소를 선택
      var tbody = $("tbody");
      tbody.empty(); // 기존 내용 삭제
      var tr = $("<tr></tr>"); // 새로운 tr 요소 생성
		
      // 이전 달의 일자 추가
      for (var i = beforeDay - (dayOfWeek-1); i <= beforeDay; i++) {
        var td = $("<td style='color: #ccc; text-align: center; vertical-align:middle;'>" + i + "</td>");
        tr.append(td);
      }
      
      console.log(dayOfWeek);

      // 현재 달의 일자 추가
      for (var j = 1; j <= lastDay; j++) {
        var td = $("<td style='text-align: center; vertical-align:middle;'></td>");
        var a = null;
        var justTd = $("<td style='color: #ccc; text-align: center; vertical-align:middle;'>" + j + "</td>");
        
        if ((dayOfWeek + j) % 7 == 0){
			a = $("<a ' style='color:blue;' class='countView'>" + j + "</a>");
		}else if((dayOfWeek + j) % 7 == 1){
			a = $("<a ' style='color:red;' class='countView'>" + j + "</a>");
		}else{
			a = $("<a ' style='color:black;' class='countView'>" + j + "</a>");
		}
        
        if(Array.isArray(resp) && resp.includes((dayOfWeek -1 + j) % 7)){
			tr.append(justTd);
		}else if(month==currentMonth && currentDay>j){
			tr.append(justTd);
		}else{
			a.on("click", function() {
		    var clickedDay = parseInt($(this).text());
		    var clickedMonth = parseInt($('.m').text());
		    var clickedYear = parseInt($('.y').text());
		    timeChoice(clickedYear, clickedMonth, clickedDay);}) // 클릭 이벤트 리스너 추가
	        td.append(a);
	        tr.append(td);
		}

        if ((dayOfWeek + j) % 7 == 0) {
          tbody.append(tr);
          tr = $("<tr></tr>");
        }
        
      }
		
      // 다음 달의 일자 추가
      for (var k = 0; k < (7 - (dayOfWeek + lastDay) % 7) % 7; k++) {
        var td = $("<td style='color: #ccc; text-align: center; vertical-align:middle;;'>" + (k + 1) + "</td>");
        tr.append(td);
      }

      // 마지막으로 남은 tr을 tbody에 추가
      if (tr.children().length > 0) {
        tbody.append(tr);
      }
		},
		error:function(){
			alert("통신에 실패하였습니다");
		}
	});
}

//한자리 숫자 앞에 0 붙이기
var isTwo = function(n) {
    // n을 문자열로 변환하여 문자열의 길이를 구함
    var strN = n.toString();
    // 만약 문자열의 길이가 1이면 앞에 "0"을 추가한 문자열 반환
    return strN.length < 2 ? "0" + strN : strN;
}

// 이전달 버튼 이벤트
$(function(){
	$("#prevMonth").on("click", function(){
	var selectedYear = parseInt($('.y').text());
  	var selectedMonth = parseInt($('.m').text());
  	
  	//현재날짜
  	var currentDate = new Date();
 	var currentYear = currentDate.getFullYear();
 	var currentMonth = currentDate.getMonth() + 1;
 	
 	if (selectedYear == currentYear && selectedMonth == currentMonth) {
    // 원하는 조치를 취하거나 알림 메시지를 표시합니다.
    alert("지난 날짜 선택은 불가능합니다.");
  	}else{
		var month = selectedMonth-1;
		ajaxCalendar(selectedYear, month);
	}
	});
});

//다음달 조회
$(function(){
	$("#nextMonth").on("click", function(){
		var year = parseInt($('.y').text());
		var month = parseInt($('.m').text()) + 1;
		ajaxCalendar(year, month);
	});
});

//시간 선택 관련 기본 리스너(토글)
	$(function() {
    $(".arrowImgT").on("click", function() {
        // 현재 .qty 요소의 display 속성 값을 가져옴
        var tDisplay = $(".timeTable").css("display");

        // display 속성 값 기반으로 toggle 기능
        if (tDisplay === "none") {
            $(".timeTable").css("display", "block");
            $(".arrowImgT").css("background-position", "-130px -175px");
        } else {
            $(".timeTable").css("display", "none");
            $(".arrowImgT").css("background-position", "-120px -80px");
        }
    });
});

function timeChoice(clickedYear, clickedMonth, clickedDay) {
    $.ajax({
        url: "./productTime.do",
        type: "get",
        async: true,
        data: {
            "productId": productId,
            "shopNo": shopNo
        },
        dataType: "text",
        success: function (resp) {
			$("#selectedDay").css("color", "black");
			$("#selectedDay").find("span").text(clickedYear+". "+isTwo(clickedMonth)+". "+isTwo(clickedDay)+".");
            var timeTable = $(".timeTable");
            timeTable.empty(); // 기존 내용 비우기
			
            var availableTimeArray = resp.replace("[", "").replace("]", "").split(", ");
            availableTimeArray.sort(); // 시간대를 오름차순으로 정렬

            for (var i = 0; i < availableTimeArray.length; i++) {
			    var availableTime = availableTimeArray[i];
			    var optionElement = $("<button></button>").text(availableTime); // value 대신 text를 사용해야 합니다.
					optionElement.on("click", function() {
					    var selectTime = $(this).text();
					    $(".selected-Time").text(selectTime);
					    });
			    
			    timeTable.append(optionElement);
			}
			$(".arrowImgT").click();
        },
        error: function () {
            alert("통신에 실패하였습니다");
        }
    });
}
 //-120px -80px;
 //-130px -175px;
 
$(function() {
    $(".arrowImgP").on("click", function() {
        // 현재 .qty 요소의 display 속성 값을 가져옴
        var currentDisplay = $(".qty").css("display");

        // display 속성 값 기반으로 toggle 기능
        if (currentDisplay === "none") {
            $(".qty").css("display", "block");
            $(".arrowImgP").css("background-position", "-130px -175px");
        } else {
            $(".qty").css("display", "none");
            $(".arrowImgP").css("background-position", "-120px -80px");
        }
    });
});


//인원 선택 이벤트
$(function(){
	$("#minusButton").on("click", function(){
		var countValue = parseInt($("#count_control_input").val(), 10);

		if (countValue === 0) {
			alert("음수 선택 불가");
		} else {
			$("#count_control_input").val(countValue - 1);
		}
		
		if(countValue ==0){
			$(".selected-option").text("인원을 선택해주세요.");
		}else{
			$(".selected-option").text(countValue-1+"명");
		}
	});

	$("#plusButton").on("click", function(){
		var countValue = parseInt($("#count_control_input").val(), 10);

		var maxValue = 5;
		
		if (countValue >= maxValue) {
			alert("최대값을 초과하여 선택 불가");
		} else {
			$("#count_control_input").val(countValue + 1);
			$(".selected-option").text(countValue+1+"명");
		}
	});
});



//약관 보기 이벤트
$(function() {
	// "약관 보기" 버튼을 클릭했을 때 실행될 함수 등록
	var agreementList = document.querySelectorAll(".btn_agreement");
	agreementList.forEach((btn) => {
		btn.addEventListener("click", function(event) {
			// 클릭된 버튼의 부모 요소에 "open" 클래스를 추가 혹은 제거
			const parentElement = event.target.closest(".agreement");
			if (parentElement.classList.contains("open")) {
				parentElement.classList.remove("open");
			} else {
				parentElement.classList.add("open");
			}
		});
	});
}
)

//약관 동의 버튼 색상 이벤트
$(function() {
    $(".chk_txt_label").on("click", function() {
        // 버튼의 클래스 상태에 따라 추가/제거
        if ($("#agreeBtn").hasClass("disable")) {
            $("#agreeBtn").removeClass("disable");
        } else {
            $("#agreeBtn").addClass("disable");
            // 선택된 시간의 텍스트 출력
            console.log($("#count_control_input").val());
        }
    });
});

//예약-> Ajax로 Post 전달값 처리
function submit(){
	var dataToSend = {
	"shopNo":shopNo,
	"day":$("#selectedDay").find("span").text(),
	"time":$(".selected-Time").text(),
	"email":document.getElementById("email").value,
	"reservName":document.getElementById("name").value,
	"reservPhone":document.getElementById("tel").value,
	"personCount":$("#count_control_input").val(),
	"requestContent":document.getElementById("requestContent").value
	}
	
	$.ajax({
	  url: "./requestReservation.do",
	  type: 'POST',
	  data: dataToSend,
	  dataType: 'text',
	  success: function(resp) {
	    console.log(resp);
		 window.location.href =".//bookingLogin.do?resrv_email="+document.getElementById("email").value;
	  },
	  error: function(error) {
		alert("3단계 통신 에러")
	    console.error('submit Error', error.responseText);
	  }
	});
}

//예약하기 버튼 이벤트
$(function() {
	
    $(".bk_btn").on("click", function(){
	
	if($("#agreeBtn").hasClass("disable")){
		alert("약관에 동의해주세요")
	}else if(validateReservationForm()==true){
		console.log("유효성 검사 통과")
		addictChk();
	}
})
});


//중복 예약 체크
function addictChk(){
	
	var addictChkData={
		"shopNo":shopNo,
		"day":$("#selectedDay").find("span").text(),
		"time":$(".selected-Time").text(),
		"email":document.getElementById("email").value
	}
	
	$.ajax({
		url:"./productReservatinoList.do",
		type:"GET",
		data:addictChkData,
		dataType:'text',
		success:function(resp){
			if(resp=="addict"){
				alert("이미 같은 시간에 예약이 존재합니다")
			}else{
				overReserveChk();
			}
		},
		error: function(error) {
		alert("1단계 통신 에러")
	    console.error('addictChkData Error', error.responseText);
	  }
	});
}

//초과예약 체크
function overReserveChk(){
	var overReserveChkData={
		"shopNo":shopNo,
		"time":$(".selected-Time").text()
	}
	
	$.ajax({
		url:"./shop.do",
		type:"GET",
		data:overReserveChkData,
		dataType:'text',
		success:function(resp){
			if(resp=="over"){
				console.log(resp)
				alert("해당 시간에 너무 많은 예약이 존재합니다")
			}else{
				submit();
			}
		},
		error: function(error) {
		alert("2단계 통신 에러")
	    console.error('overReserveChk Error', error.responseText);
	  }
	})
}

//유효값
function validateReservationForm() {
	console.log("유효성 검사 실행")
 	var name = document.getElementById("name").value;
  	var email = document.getElementById("email").value;
  	var tel = document.getElementById("tel").value;
  	var day = $("#selectedDay").find("span").text();
  	var time = $(".selected-Time").text();
  	var pCnt = $("#count_control_input").val();
	var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	var telPattern = /^\d{2,3}-\d{3,4}-\d{4}$/;
	console.log("이름, email, phone"+name+email+tel);
	
	  // 이름 유효성 검사: 이름은 1자 이상이어야 함
	if (name.trim() === "") {
	    alert("이름을 입력해주세요.");
	    return false;
	}else if (!telPattern.test(tel)) {
		// 전화번호 유효성 검사: 숫자와 '-'만 포함된 10~11자리여야 함
	    alert("유효한 전화번호를 입력해주세요. (예: 010-1234-5678)");
	    return false;
	}else if (!emailPattern.test(email)) {
		// 이메일 유효성 검사: 정규표현식을 사용하여 이메일 형식인지 확인
	    alert("유효한 이메일 주소를 입력해주세요.");
	    return false;
	}else if(day=="선택해주세요"){
		alert("날짜를 선택해주세요");
		return false;
	}else if(time=="시간을 선택해주세요."){
		alert("시간을 선택해주세요");
		return false;
	}else if(pCnt<1){
		alert("예약 인원을 선택해주세요");
		return false;
	}else{
	// 모든 유효성 검사를 통과
	return true;
	}
}