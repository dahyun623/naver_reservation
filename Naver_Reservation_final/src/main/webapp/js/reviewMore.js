
$(function() {
	
	$('.list_item .has-image').each(function() {
    	fetchImages($(this));
	});
	
    var pageNum = 2;  // 페이지 번호
	var total = Number($("em.green").text().replace('건', ''));
	
    $('.btn_review_more').on('click', function(e) {
		e.preventDefault();
		
		  // 페이지 번호 증가
        var start = (pageNum - 1) * 5 + 1;  // 시작 번호
        var end = pageNum * 5;  // 끝 번호
        console.log("pageNum:", pageNum);
		console.log("start:",start);
		console.log("end:", end);
		console.log("productId: ",productId);
		
        $.ajax({
            url: "./reviewMore.do",
            async: true,
            type: "POST",
            data: {
				productId: $("#productId").val(),
                start: start,
                end: end,
                command: "more"
            },
            dataType: "json",
            success: function(reviews) {
				console.log("resp:",reviews);
				
                reviews.forEach(function(review) {
                    var listItem;
                    if (review.fileInfo.length === 0) {
                        listItem = $('<li>').addClass('list_item').html(
                            '<div>' +
                                '<div class="review_area no_img">' +
                                    '<p class="review">' + review.content + '</p>' +
                                '</div>' +
                                '<div class="info_area">' +
                                    '<div class="review_info"> <span class="grade">' + review.score + '.0</span> <span class="name">' + review.emailId + '</span> <span class="date">' + review.regdate + ' 방문</span> </div>' +
                                '</div>' +
                            '</div>'
                        );
                    } else {
						var imageInputs = review.fileInfo.map(function(fileInfo) {
				            return '<input type="hidden" class="imageVal" value="' + fileInfo.saveFileName + '">';
				        }).join('');
				        
                        listItem = $('<li>').addClass('list_item').html(
                            '<div>' +
                                '<div class="review_area has-image">' +
                                    '<div class="thumb_area">' +
                                        imageInputs +
                                        '<a href="#" class="thumb" title="이미지 크게 보기">' +
                                        '</a> <span class="img_count">'+review.fileInfo.length+'</span>' +              
                                    '</div>' +
                                    '<p class="review">' + review.content + '</p>' +
                                '</div>' +
                                '<div class="info_area">' +
                                    '<div class="review_info"> <span class="grade">' + review.score + '.0</span> <span class="name">' + review.emailId + '</span> <span class="date">' + review.regdate + ' 방문</span> </div>' +
                                '</div>' +
                            '</div>'
                        );
                    }

                    $('.list_short_review').append(listItem);
                    
                    fetchImages(listItem);
                });
                pageNum++;
                
                if ((pageNum - 1) * 5 >= total) {
                    $(".btn_review_more").hide();
                }
				
            }
        });
        
    });
});

function fetchImages($listItem) {
//    $listItem.find('.imageVal').each(function() {
//        var image = $(this).val();
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
//    });
}

