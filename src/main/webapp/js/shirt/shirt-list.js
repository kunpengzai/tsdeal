var pageNum = 1;
$(function() {
	var winWidth = $(window).width();
	var gap = 0;
	if (winWidth > 880) {
		gap = (1000 -880)/3;
	} else {
		gap = winWidth - 440;
	}
	$(".s-list").css("-moz-column-gap", gap);
	$(".s-list").css("-webkit-column-gap", gap);
	$(".s-list").css("column-gap", gap);
	getMoreShirt();
	
	$(".get-more-btn").on('click', function(){
		getMoreShirt();
	});
});

function getMoreShirt() {
	var url = "get-more-shirt.htm?pageNum="+pageNum+"&pageSize=8";
	$.ajax({
        type:'get',
        url:url,
        async:false,
        dataType:'json',
        success:function(data) {
        	if (data.flag == 0) {
        		pageNum += 1;
        		var baseUrl = data.baseUrl;
        		var shirtList = JSON.parse(data.shirtList);
        		console.log(shirtList);
        		if (shirtList.length == 0) {
        			$(".get-more-btn").hide();
        		}
        		$.each(shirtList, function(index, item) {
        			var htm = '<div class="s-l-item">'
        				+ '<img src="'+(item.imgType==1?baseUrl:'')+item.shirtImg+'" width="218px"/>'
        				+ '<div class="s-l-con">'+item.shirtId+'=='+item.title+'</div></div>';
    				$(".s-list").append(htm);
    		});
        	}
	       	return;
       	}
    });
}