/**
 * 특정 값을 받아와 이메일의 형식인지 확인 후 submit
 */
$(function(){
	$(".login_btn").click(function(event){
		console.log("로그인 버튼 클릭 테스트");
		$login_input =  $(".login_input").val();
		console.log("작성 한 값 : "+$login_input);
		$regex = /^[\w\.-]+@[a-zA-Z\d\.-]+\.[a-zA-Z]{2,}$/;
		if($login_input==""){
			alert("값을 입력해주세요");
			event.preventDefault();
		}else if(!$regex.test($login_input)){
			alert("이메일 형식이 잘못되었습니다. Ex) example@email.com ");
			$(".login_input").focus();
			$(".login_input").val("");
			event.preventDefault();
		}
	});
});

