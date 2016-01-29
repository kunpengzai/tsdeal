var pageNum = 1;
var priceRangeId = 0;
var colorId = 0;
var brandId = 0;
var sourceId = 0;
var design = 0;
var sleeve = 0;
var otherId = 10000;//"其他"的ID
var minPrice, maxPrice;

$(function() {
	$('#s-container').masonry({
		itemSelector : '.s-item',
		columnWidth : 260
	});

	//var $container = $('#s-container');
	//$container.imagesLoaded(function() {
	//	$container.masonry({
	//		itemSelector: '.s-item',
	//		columnWidth: 260,
	//		isAnimated: true
	//	});
	//});
	var winWidth = $(window).width();
	//var gap = 0;
	//if (winWidth > 880) {
	//	gap = (1000 -880)/3;
	//} else {
	//	gap = winWidth - 440;
	//}
//	$(".s-list").css("-moz-column-gap", gap);
//	$(".s-list").css("-webkit-column-gap", gap);
//	$(".s-list").css("column-gap", gap);
	
	$(".s-content").css("width", "1000px");
	
	getMoreShirt();
//	imgLoad(".s-list",".s-box");
	
	$(".get-more-btn").on('click', function(){
		getMoreShirt();
	});

	$(".s-filter-price-cls").on('click', function(){
		$(".s-filter-price-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		priceRangeId = $(this).attr("data-value");
		minPrice = $(this).attr("data-minPrice");
		maxPrice = $(this).attr("data-maxPrice");
		pageNum = 1;
		getMoreShirt();
	});
	$(".s-filter-color-cls").on('click', function(){
		$(".s-filter-color-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		colorId = $(this).attr("data-value");
		pageNum = 1;
		getMoreShirt();
	});
	$(".s-filter-brand-cls").on('click', function(){
		$(".s-filter-brand-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		brandId = $(this).attr("data-value");
		pageNum = 1;
		getMoreShirt();
	});
	$(".s-filter-source-cls").on('click', function(){
		$(".s-filter-source-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		sourceId = $(this).attr("data-value");
		pageNum = 1;
		getMoreShirt();
	});
	$(".s-filter-design-cls").on('click', function(){
		$(".s-filter-design-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		design = $(this).attr("data-value");
		pageNum = 1;
		getMoreShirt();
	});
	$(".s-filter-sleeve-cls").on('click', function(){
		$(".s-filter-sleeve-cls").css('border-bottom', 'hidden');
		$(this).css('border-bottom', '2px solid #000000');
		sleeve = $(this).attr("data-value");
		pageNum = 1;
		getMoreShirt();
	});
});


function imgLoad(parent, content) {
	var contents = $(parent).children(content);
	//console.log(contents);
	var imgWidth = contents[0].offsetWidth;
	console.log($(contents[0]).height());
	console.log("====="+contents[0].offsetHeight);
	var cols = Math.floor(1000/imgWidth);
	//console.log(cols);
	var highArr = [];
	for (var i=0; i<contents.length; i++) {
		if (i<cols) {
			highArr[i] = contents[i].offsetHeight;
			console.log(highArr[i]);
		} else {
			var index = i % cols;
			var left = index * (40 + 220);
			//console.log("...."+Math.floor(i/cols));
			var top = highArr[index] + 30 * Math.floor(i/cols);
			$(contents[i]).css("top", top + "px");
			$(contents[i]).css("left", left + "px");
			$(contents[i]).css("position", "absolute");
			//contents[i].style.top = top + "px";
			//contents[i].style.left = left + "px";
			//contents[i].style.position = "absolute";
			highArr[index] = highArr[index] + contents[i].offsetHeight;
		}
	}
	//$(".get-more-btn").css('position', 'relative');
	//console.log(Math.max.apply(null,highArr));
	//$(".get-more-btn").css('top', Math.max.apply(null,highArr));
}


function imgLoad1 (parent, content) {
	var gparent = $(parent);
	//var contents = getContents(gparent,content);
	var contents = $(parent).children(content);
	console.log(contents);
	var imgWidth = contents[0].offsetWidth;
	console.log("----"+imgWidth);
	//var cols = Math.floor($(window).width()/imgWidth);
	var cols = Math.floor(1000/imgWidth);
	console.log(cols);
	//$(parent).addClass("width:"+imgWidth*cols+"px;margin:0 auto");
	//gparent.style.cssText = "width:"+imgWidth*cols+"px;margin:0 auto";
	console.log("width:"+imgWidth*cols+"px,margin 0 auto");
	console.log("显示的列数是"+cols);
	var highArr = [];
	console.log("=="+contents.length);
	for (var i = 0; i < contents.length; i++) {
		if(i<cols){
			highArr[i] = contents[i].offsetHeight;
			console.log(highArr[i]);
		}else{
			console.log(highArr);
			var minHigh = Math.min.apply(null,highArr);
			console.log(minHigh);
			var minLocation  = -1;
			for (var j = 0; j < highArr.length; j++) {
				if(highArr[j] == minHigh){
					minLocation = j;
					break;
				}
			};
			contents.css("top", (minHigh+30)+"px");
			contents.css("left", (minLocation * imgWidth +40*(minLocation)) + "px");
			contents.css("position", "absolute");
			//contents[i].style.top = minHigh+30+"px";
			//contents[i].style.left = minLocation * imgWidth +40*(minLocation) + "px";
			//contents[i].style.position = "absolute";
			highArr[minLocation] = highArr[minLocation] + contents[i].offsetHeight;

		}
	};
	console.log(Math.max.apply(null,highArr));
	$(".get-more-btn").show();
}

function getMoreShirt() {
	var url = "get-more-shirt.htm?pageNum="+pageNum+"&pageSize=16";
	if (colorId != 0) {
		url += "&colorId="+colorId;
	}
	if (brandId != 0) {
		url += "&brandId="+brandId;
	}
	if (sourceId != 0) {
		url += "&sourceId="+sourceId;
	}
	if (design != 0) {
		url += "&design="+design;
	}
	if (sleeve != 0) {
		url += "&sleeve="+sleeve;
	}
	if (priceRangeId != 0) {
		url += "&minPrice="+minPrice+"&maxPrice="+maxPrice;
	}
	$.ajax({
        type:'get',
        url:url,
        async:false,
        dataType:'json',
        success:function(data) {
        	if (data.flag == 0) {
				if (pageNum == 1) {
					//$(".s-list").empty();
					$("#s-container").empty();
				}
        		pageNum += 1;
        		var shirtList = JSON.parse(data.shirtList);
        		console.log(shirtList);
        		if (shirtList.length == 0) {
        			$(".get-more-btn").hide();
        		}
        		$.each(shirtList, function(index, item) {
					var colorNames = item.colorNames.split(",");
					//var htm = '<div class="s-box">'
					var htm = '<div class="s-item">'
								+ '<img src="'+(item.imgType==1?data.baseUrl:'')+item.shirtImg+'" width="220px"/>'
								+ '<div class="s-box-s-content">'
									+ '<div class="s-box-s-title">' + item.shirtId + item.title + '</div>'
									+ '<div class="s-box-s-interval"></div>'
									+ '<div class="s-box-s-price">'
										+ '<span class="rmb">¥</span><span class="price">'+item.minPrice+'</span>'
										+ '<span class="brand">' + item.brandName + '</span>'
									+ '</div>'
									+ '<div class="s-box-s-source">'
										+ '<span class="source">' + item.sourceName + '</span>'
										+ '<span class="color">';
											for (var i=0; i<colorNames.length; i++) {
												htm += '<span class="s-box-s-c" style="background-color: '+colorNames[i]+'"></span>';
											}
							htm += '</span>'
									+ '</div>'
									+ '<div class="s-box-s-border"></div>'
									+ '</div>'
								+ '</div>';
    				//$(".s-list").append(htm);
					console.log(htm)
                    $("#s-container").append($(htm));
    			});
				//imgLoad(".s-list",".s-box");
        	}
	       	return;
       	}
    });
}

/*
function imgLoad (parent,content) {
	var gparent = document.getElementById(parent);
	var contents = getContents(gparent,content);
	var imgWidth = contents[0].offsetWidth;
	var cols = Math.floor(document.documentElement.clientWidth/imgWidth);
	gparent.style.cssText = "width:"+imgWidth*cols+"px;margin:0 auto";
	console.log("width:"+imgWidth*cols+"px,margin 0 auto");
	console.log("显示的列数是"+cols);
	var highArr = [];
	console.log("=="+contents.length);
	for (var i = 0; i < contents.length; i++) {
		if(i<cols){
			highArr[i] = contents[i].offsetHeight;
			console.log(highArr[i]);
		}else{
			var minHigh = Math.min.apply(null,highArr);
			var minLocation  = -1;
			for (var j = 0; j < highArr.length; j++) {
				if(highArr[j] == minHigh){
					minLocation = j;
					break;
				}
			};
			contents[i].style.top = minHigh+"px";
			contents[i].style.left = minLocation * imgWidth + "px";
			contents[i].style.position = "absolute";
			highArr[minLocation] = highArr[minLocation] + contents[i].offsetHeight;

		}
	};
}

function getContents(parent,content){
	var childs = parent.getElementsByTagName("*");
	console.log(childs);
	var contents = [];
	for (var i = 0; i < childs.length; i++) {
		//console.log(childs[i].className);
		if(childs[i].className == content){
			contents.push(childs[i]);
		}
	};
	return contents;
}

		*/