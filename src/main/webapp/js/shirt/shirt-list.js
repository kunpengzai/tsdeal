var pageNum = 1;
var priceRangeId = 0;
var colorId = 0;
var brandId = 0;
var sourceId = 0;
var design = 0;
var sleeve = 0;
var otherId = 10000;//"其他"的ID
var minPrice, maxPrice;
var pcClient = true;

$(function() {
	pcClient = isPC();
	if (pcClient == true) {
		$(".s-header-right-2").show();
		$(".s-header-right-1").show();
	    $(".s-filter").show();
		$(".s-header-1").css("width", "1000px");
		$(".s-shirt-content").css("width", "1000px");
		$(".s-header-right-2").on('mouseover', function(){
			$(".qrcode-layer").css('marginLeft',(1000-240-5)+'px');
			$(".qrcode-layer-part").show();
		});
		$(".s-header-right-2").on('mouseout', function(){
			$(".qrcode-layer-part").hide();
		});
		$(".s-header-right-2").on('click', function(){
			$(".qrcode-layer").css('marginLeft',(1000-240-5)+'px');
			$(".qrcode-layer-part").show();
		});
	} else {
		$(".s-header-1").css("width", "520px");
		$(".s-header-1").css("padding", "0 10px 0 10px");
		$(".s-shirt-content").css("width", "540px");
	}

	//$('#s-container').masonry({
	//	itemSelector : '.s-item',
	//	columnWidth : 260
	//});

	//var $container = $('#s-container');
	//$container.imagesLoaded(function() {
	//	$container.masonry({
	//		itemSelector: '.s-item',
	//		columnWidth: 260,
	//		isAnimated: true
	//	});
	//});
	//var winWidth = $(window).width();
	//var gap = 0;
	//if (winWidth > 880) {
	//	gap = (1000 -880)/3;
	//} else {
	//	gap = winWidth - 440;
	//}
//	$(".s-list").css("-moz-column-gap", gap);
//	$(".s-list").css("-webkit-column-gap", gap);
//	$(".s-list").css("column-gap", gap);
	
	//$(".s-content").css("width", "1000px");
	
	getMoreShirt();
//	imgLoad(".s-list",".s-box");
	
	$(".get-more-btn").on('click', function(){
		getMoreShirt();
	});

	$(".s-header-left-1").on('click', function(){
		window.location.reload();
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

	$(".s-header-right-1").on('click', function(){
		$(".s-header-right-1").hide();
		$(".s-header-right-0").show();
		$('#s-search').focus();
		if (pcClient == true) {
			$("#s-search").animate({width: '190px'}, "slow");
		} else {
			$("#s-search").animate({width: '100px'}, "slow");
		}
	});

	$('#s-show-search').on('mouseenter',function(){
		$(this).attr('src','../img/search2.png');
	}).on('mouseleave',function(){
		$(this).attr('src','../img/search1.png');
	});

	$("#s-search-btn").on('click', function(){
		pageNum = 1;
		getMoreShirt();
	});

	$("#s-search").on('keydown',function(e){
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			pageNum = 1;
			getMoreShirt();
		}
	});
	$("#s-search").on('blur',function(){
		if($.trim($("#s-search").val()) == ''){
			$('#s-search').animate({width: '0px'}, "slow",function(){
				$('.s-header-right-0').hide();
				$('.s-header-right-1').show();
			});
		}
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
	$(".s-shirt-blank").hide();
	$(".s-shirt-not-data").hide();
	var pageSize = 20;
	var url = "get-more-shirt.htm?pageNum="+pageNum+"&pageSize="+pageSize;
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
	var keyword = $.trim($("#s-search").val());
	if (keyword.length > 0) {
		url += "&keyword="+encodeURI(encodeURI(keyword));
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
        		var shirtList = JSON.parse(data.shirtList);
				console.log(shirtList.length);
				if (shirtList.length == 0 && pageNum == 1) {
					$(".s-shirt-not-data").show();
				}
        		if (shirtList.length < pageSize) {
        			$(".get-more-btn").hide();
					$(".s-shirt-blank").show();
        		} else {
					$(".get-more-btn").show();
				}
				pageNum += 1;
        		$.each(shirtList, function(index, item) {
					var colorNames = item.colorNames.split(",");
					//var htm = '<div class="s-box">'
					var htm = '';
					if (pcClient == true) {
						htm = '<div class="s-item" onclick="openShirtUrl(\''+item.linkUrl+'\','+item.shirtId+');">';
					} else {
						htm = '<div class="s-item" style="margin: 30px 25px 0 25px;" onclick="openShirtUrl(\''+item.linkUrl+'\','+item.shirtId+');">';
					}
								//+ '<img src="'+(item.imgType==1?data.baseUrl:'')+item.shirtImg+'" width="220px"/>'
					htm += '<div class="shirtImg" style="background:url('+(item.imgType==1?data.baseUrl:'')+item.shirtImg+') no-repeat top center"></div>'
								+ '<div class="s-box-s-content">'
									+ '<div class="s-box-s-title">' + item.title + '</div>'
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
                    //console.log(htm);
                    $("#s-container").append($(htm));
					//图片自适应
					fitImg();
    			});
				//imgLoad(".s-list",".s-box");
        	}
	       	return;
       	}
    });
}

function openShirtUrl(url, shirtId) {
	clickPoint(shirtId);
	window.open(url);
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


function fitImg(){
	$('.shirtImg').each(function(i,e){
		var s = $(this).attr('style');
		index_s = s.indexOf("(");
		index_e = s.indexOf(")");
		var url = s.substring(index_s+1,index_e);
		var img = new Image();
		var imgWidth;
		var imgHeight;
		img.src = url;
		if (img.complete) {
			imgWidth = img.width;
			imgHeight = img.height;
			if (imgWidth*300 < imgHeight*220) {//imgWidth/imgHeight < 220/300
				$(e).removeClass('shirtImg');
				$(e).addClass('shirtImg2');
			}
		} else {
			img.onload = function () {
				imgWidth = img.width;
				imgHeight = img.height;
				if (imgWidth*300 < imgHeight*220) {//imgWidth/imgHeight < 220/300
					$(e).removeClass('shirtImg');
					$(e).addClass('shirtImg2');
				}
				img.onload = null;
			};
		}
	});
}

function isPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone",
		"SymbianOS", "Windows Phone",
		"iPad", "iPod"];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
}

function clickPoint(shirtId) {
	$.ajax({
		type: 'post',
		url: "click-point.htm?shirtId="+shirtId,
		async: false,
		dataType: 'json',
		success: function (data) {
		}
	});
}
