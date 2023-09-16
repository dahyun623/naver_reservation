var ratingValue = 0;
var currentInputIndex = 0;
var maxInputIndex = 2; 
var lastImgIdx = 0;

$(function(){
	// 별점 체크하기
	$('.rating_rdo').on('click', function() {
        // 해당 별점의 value를 가져옴
        ratingValue = $(this).val();
        console.log(ratingValue);

        // 먼저 모든 별점 인풋의 checked 클래스를 제거함
        $('.rating_rdo').removeClass('checked');

        // 선택한 별점 이하의 모든 별점 인풋에 checked 클래스를 추가함
        $('.rating_rdo').each(function() {
            if ($(this).val() <= ratingValue) {
                $(this).addClass('checked');
            } 
        });
        $(".star_rank").text(ratingValue);
    });
	
	// 리뷰 내용 입력 시 textarea에 focus 보내기
	$(".review_write_info").click(function(){
		$(this).hide();
		$(".review_textarea").focus();
	});
	// 글자수 제한하기
	$(".review_textarea").on('input', function () {
        var text_length = $(this).val().length;
        if(text_length > 400) {
            alert('400자까지만 작성 가능합니다.');
            $(this).val($(this).val().substring(0, 400));
        } else {
            $(".guide_review span:first-child").text(text_length);
        }
    });
    
    // 사진 추가 버튼 누를 때 정규화
	var fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
	var maxSize = 5 * 1024 * 1024;
    
    $("#addFileBtn").click(function(e){
        e.preventDefault();
        
        if ($(".file").eq(currentInputIndex).val() !== '') {
            // 사진 첨부되었을 때만 인덱스를 증가시킴
            currentInputIndex = currentInputIndex + 1;
        } 
        
        if (currentInputIndex > maxInputIndex) {
            alert("파일은 3개까지만 가능합니다.");
            return; 
        }
        // 현재 인덱스의 파일 인풋을 트리거
        $(".file").eq(currentInputIndex).click();
    });
	
	// 사진 파일 업로드 시 정규화, 썸네일 띄우기
    $(".file").change(function(){
		if(!$(this).val().match(fileForm)){
				alert("이미지만 업로드 가능합니다");
				$(this).val('');
				return;
		} else if ($(this).size > maxSize){
			alert("파일 사이즈는 5MB까지만 가능합니다.");	
			$(this).val('');
			return;
		} else {
			img_preview();
		}
        console.log("파일 첨부 currentInputIndex:", currentInputIndex);
        updateFileIndex();
    });

	// 리뷰 등록 버튼 클릭시
	$("#setReview").click(function(){
	   if("score", $('input[name^="rating"]:checked').val() == null){
			alert("별점을 등록해주세요");
		} else if($(".review_textarea").val().length < 5){
			alert("5글자 이상 내용을 입력해주세요")
		} else {
			reviewFileWrite();
		}
		
	});
	
});

// 썸네일 등록 ajax
function img_preview(){
	var file = $("input:file");
	var formData = new FormData();
	formData.append("image", file[currentInputIndex].files[0]);
	formData.append("imageIndex", currentInputIndex);
	
	$.ajax({
		url: "./reviewWrite.do",
		enctype: "multipart/form-data",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function(suc){
			$(".lst_thumb").append(suc);
			lastImgIdx++;
		},
		error: function(){
			alert("잘못된 요청");
		}
	});
}

// 썸네일 삭제시
function removeItem(id){
	var fileInputId = id.replace('item', 'file');
	$("#" + fileInputId).val('');
	$("#"+id).remove();
	updateFileIndex();
	
	// 2번째 이상 첨부해서 삭제할 때
	while(lastImgIdx > 0){
		lastImgIdx--;
		currentInputIndex--;
	}
	// 첫번째 첨부 삭제시
		lastImgIdx = 0;
		currentInputIndex = 0;
}

// 최종 리뷰 등록 ajax
function reviewFileWrite(){
    var formData = new FormData();
    
    formData.append("reservno", $("#reservno").val());
    formData.append("productId", $("#productId").val());
    formData.append("emailId", $("#emailId").val());
    formData.append("content", $(".review_textarea").val());
    formData.append("score", ratingValue);
    
    $(".file").each(function(i, obj) {
	    if (obj.files[0]) {
	        formData.append("filename"+i, obj.files[0]);
	    }
    });
	
    $.ajax({
        url: "./reviewWriteForm.do",
        enctype: "multipart/form-data",
        type: "POST",
        processData: false, // 이 옵션을 false로 설정해야 FormData가 정상적으로 작동합니다.
        contentType: false, // 이 옵션을 false로 설정해야 FormData가 정상적으로 작동합니다.
        data: formData,
        success: function(){
            alert("리뷰 입력이 완료되었습니다");
            window.location.href = './myReservation.do';
        },
        error: function(){
            alert("잘못된 요청");
        }
    });
}

function updateFileIndex() {
    var fileCount = 0;
    $(".file").each(function(i, obj) {
        if (obj.files[0]) {
            fileCount++;
        }
    });
    currentInputIndex = fileCount - 1;
    lastImgIdx = fileCount;
}