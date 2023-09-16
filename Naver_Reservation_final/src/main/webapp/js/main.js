$(function(){
	getpromotion();
	getitems();
	getProducts("P000", 1);
	$(".more .btn").on("click", function(){
		more();
	});
});

function getpromotion(){
	$.ajax({
		url:"./promotion.do",
		type:"get",
		async:true,
		dataType:"json",
		success:function(resp){
			var event = $(".visual_img").eq(0);
			for(let i=0; i<resp.length; i++){
				var html = "<li class='item item-none' style='background-image: url("+resp[i].eventImg+");'>"
									 	+ "<a href='./detail.do?productId="+resp[i].productId+"'> <span class='img_btm_border'></span> <span class='img_right_border'></span> <span class='img_bg_gra'></span>"
									    	+ "<div class='event_txt'>"
									        	+ "<h4 class='event_txt_tit'>"+resp[i].title+"</h4>"
									            + "<p class='event_txt_adr'></p>"
									            +	"<p class='event_txt_dsc'>"+resp[i].content+"</p>"
								            + "</div>"
									    + "</a>"
									 + "</li>";
					event.append(html);
			}
			event.children().eq(0).removeClass("item-none");
/*			if(resp.length > 1){
				$(".prev_inn, .nxt_inn").css("display", "block");
				$(".prev_inn")
			}*/
		},
		error:function(){
			alert("잘못된 요청입니다.");
		}
	});
}

function getitems(){
	$.ajax({
		url:"./items.do",
		type:"get",
		async:true,
		dataType:"json",
		success:function(resp){
			var category = $("#category");
			for(let i=0; i<resp.length; i++){
				var active = (i==0)?"active":""
				var html = "<li class='item' data-category='"+i+"'>"
							+ "<a class='anchor "+active+"'> <span>"+resp[i].categoryName+"</span> </a>"
							+ "</li>"
				category.append(html);
				category.children().eq(i).children().eq(0).on("click", function(){
					getProducts(resp[i].PCategoryId, 1)

					$(".active").eq(0).removeClass("active");
					$(this).addClass("active");

					$(".pink").eq(0).text(resp[i].count+"개");       
				});
			}
			$(".pink").eq(0).text(resp[0].count+"개");       
		},
		error:function(){
			alert("잘못된 요청입니다.");
		}
	});
}

function getProducts(pCategoryId, start){	
	if(start == 1){
		$(".lst_event_box>.item").remove();			
	}
	$.ajax({
		url:"./productItem.do",
		type:"get",
		async:false,
		data:{"pCategoryId":pCategoryId,
					"start":start},
		dataType:"json",
		success:function(resp){
			if("P00"+$("#category .active").parent().attr('data-category')== pCategoryId && resp.length == 0){
				alert("게시물의 끝입니다.");
			}
			var lBox = $(".lst_event_box").eq(0);
			var rBox = $(".lst_event_box").eq(1);
			for(let i=0; i<resp.length; i++){
				var html = "<div class='item'>"
                            + "<div class='item_book'>"
                            	+ "<div class='item_preview'>"
                            		+ "<img alt='"+resp[i].productName+"' class='img_thumb' src='"+resp[i].fileInfo[0].saveFileName+"'>"
                            		+ "<span class='img_border'></span>"
                        		+"</div>"
                            	+ "<div class='event_txt'>"
                            		+ "<h4 class='event_txt_tit'><span>"+resp[i].productName+"</span></h4>"
                            		+ "<p class='event_txt_dsc'>"+resp[i].description+"</p>"
                        		+"</div>"
                    		+"</div>"
                		+"</div>";
                if(i%2==0){
					lBox.append(html);
				} else {
					rBox.append(html);
				}
				$(".lst_event_box").children().last().on("click", function(){
						$(location).attr("href", "./detail.do?productId="+resp[i].productId);
					});
			}  
		},
		error:function(){
			alert("잘못된 요청입니다.");
		}
	});
}

function more(){
	var pCategoryId = "P00"+$("#category .active").parent().attr('data-category');
	var start = $(".lst_event_box").children().length+1;
	getProducts(pCategoryId, start);
}