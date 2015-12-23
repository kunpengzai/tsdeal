$(function() {
	$("#search-beginDate-picker").datetimepicker({
         lang:'ch',
         value:'',
         closeOnDateSelect:true,
         timepicker:false,
         format:'Y-m-d',
         formatDate:'Y-m-d'
     });
     $("#search-endDate-picker").datetimepicker({
         lang:'ch',
         value:'',
         closeOnDateSelect:true,
         timepicker:false,
         format:'Y-m-d',
         formatDate:'Y-m-d'
     });
	 getShirtList();
});

function getShirtList() {
	var selBrandId = $(".search-brand-sel").val();
	var selSourceId = $(".search-source-sel").val();
	var selActiveId = $(".search-active-sel").val();
	var beginDate = $("#search-beginDate-picker").val();
	var endDate = $("#search-endDate-picker").val();
	var url = "shirt-list.htm?pageNum=1&pageSize=20";
	if (parseInt(selBrandId) > 0) {
		url += "&brandId="+selBrandId
	}
	if (parseInt(selSourceId) > 0) {
		url += "&sourceId="+selSourceId
	}
	if (parseInt(selActiveId) > 0) {
		url += "&isActive="+selActiveId
	}
	if (beginDate != "开始时间") {
		url += "&beginDate="+beginDate
	}
	if (endDate != "结束时间") {
		url += "&endDate="+endDate
	}
   	$.ajax({
        type:'get',
        url:url,
        async:false,
        dataType:'json',
        success:function(data){
        	if (data.flag == 0) {
        		if (data.shirtList == null) {
        			return;
        		}
        		var shirtList = JSON.parse(data.shirtList);
        		$.each(shirtList,function(index, item) {
	        		$("#shirt-state-data").append(
		        		'<tr>'
							+ '<td>'+item.shirtId+'</td>'
							+ '<td>'+item.title+'</td>'
							+ '<td>'+item.sourceName+'</td>'
							+ '<td>'+item.brandName+'</td>'
							+ '<td>'+item.clickNum+'</td>'
							+ '<td>'+item.minPrice+'</td>'
							+ '<td>'+(item.isActive==1?'是':'否')+'</td>'
							+ '<td>'+(item.design==1?'纯色':'有图案')+'</td>'
							+ '<td>'+item.colorNames+'</td>'
							+ '<td>'+(item.sleeve==1?'长袖':'短袖')+'</td>'
							+ '<td>'
							+ '<span class="shirt-s-t-td-opt-1" onclick="showShirtDetail(\''+item.linkUrl+'\');">查看</span> '
							+ '<span class="shirt-s-t-td-opt-2" onclick="showShirtEditLayer('+item.shirtId+');">编辑</span> '
							+ '<span class="shirt-s-t-td-opt-3" onclick="showShirtDeleteLayer('+item.shirtId+');">删除</span>'
							+ '</td>'
						+ '</tr>'
					);
        		});
        	}
	       	return;
       	}
    });
}

function searchShirt() {
	$("#shirt-state-data").empty();
	getShirtList();
}

function showShirtAddLayer() {
	$(".shirt-add-layer").show();
}

function hideShirtAddLayer() {
	$(".shirt-add-layer").hide();
}

function showShirtDetail(linkUrl) {
	window.open(linkUrl); 
}

function showShirtEditLayer() {
	$(".shirt-edit-layer").show();
}

function hideShirtEditLayer() {
	$(".shirt-edit-layer").hide();
}

function showShirtDeleteLayer(shirtId) {
	$("#shirt-del-id").val(shirtId);
	$(".shirt-del-layer").show();
}

function hideShirtDeleteLayer() {
	$(".shirt-del-layer").hide();
}

function selectImgType() {
	var imgType = $(".shirt-add-imgType-sel").val();
	$(".shirt-add-s-uploadfile").hide();
	$(".shirt-add-s-imgUrl").hide();
	if (imgType == 1) {
		$(".shirt-add-s-uploadfile").show();
	} else if (imgType == 2) {
		$(".shirt-add-s-imgUrl").show();
	}
}

function selColorInAddShirt(colorId) {
	var colorIds = $("#shirt-add-shirt-color").val();
	if ($("#shirt-add-color-"+colorId).css("border-color")=="rgb(204, 204, 204)") {
		$("#shirt-add-color-"+colorId).css("border-color", "#2483CB");
		$("#shirt-add-color-"+colorId).css("background-color", "#2483CB");
		$("#shirt-add-color-"+colorId).css("color", "#FFFFFF");
		colorIds += colorId + ",";
		$("#shirt-add-shirt-color").val(colorIds);
	} else {
		$("#shirt-add-color-"+colorId).css("border-color", "#CCCCCC");
		$("#shirt-add-color-"+colorId).css("background-color", "#FFFFFF");
		$("#shirt-add-color-"+colorId).css("color", "#000000");
		colorIds = colorIds.replace(colorId + ",", "");;
		$("#shirt-add-shirt-color").val(colorIds);
	}
}

function addShirt() {
	var linkUrl = $("#shirt-add-link-url").val();
	if ($.trim(linkUrl) == "") {
		$(".shirt-add-error-msg").text("商品链接不能为空。");
		return;
	}
	var title = $("#shirt-add-title").val();
	if ($.trim(title) == "") {
		$(".shirt-add-error-msg").text("标题不能为空。");
		return;
	}
	var price = $("#shirt-add-min-price").val();
	if ($.trim(price) == "") {
		$(".shirt-add-error-msg").text("价格不能为空。");
		return;
	}
	$("#shirt-add-max-price").val(price);
	
	var sourceId = $("#shirt-add-source").val();
	if (sourceId == 0) {
		$(".shirt-add-error-msg").text("请选择来源。");
		return;
	}
	
	var design = $("#shirt-add-design").val();
	if (design == 0) {
		$(".shirt-add-error-msg").text("请选择图案。");
		return;
	}
	
	var brandId = $("#shirt-add-brandId").val();
	if (brandId == 0) {
		$(".shirt-add-error-msg").text("请选择品牌。");
		return;
	}
	
	var colorIds = $("#shirt-add-shirt-color").val();
	if ($.trim(colorIds) == "") {
		$(".shirt-add-error-msg").text("请选择颜色。");
		return;
	}
	$("#shirt-add-shirt-color").val(colorIds.substring(0, colorIds.length-1));
	
	var sleeve = $("#shirt-add-sleeve").val();
	if (sleeve == 0) {
		$(".shirt-add-error-msg").text("请选择袖长。");
		return;
	}
	
	$("#addShirtForm").submit();
}

function deleteShirt() {
	var shirtId = $("#shirt-del-id").val();
	$.ajax({
        type:'post',
        url:"delete-shirt.htm?shirtId="+shirtId,
        async:false,
        dataType:'json',
        success:function(data){
        	if (data.flag == 0) {
        		window.location.reload();
    		}
        }
	});
}