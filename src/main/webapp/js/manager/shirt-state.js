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
     var urlHash=$.iteeHash.get();
     if (urlHash.brandId) {
    	 $(".search-brand-sel").val(urlHash.brandId);
     }
     if (urlHash.sourceId) {
    	 $(".search-source-sel").val(urlHash.sourceId);
     }
     if (urlHash.isActive) {
    	 $(".search-active-sel").val(urlHash.isActive);
     }
     if (urlHash.beginDate) {
    	 $("#search-beginDate-picker").val(urlHash.beginDate);
     }
     if (urlHash.endDate) {
    	 $("#search-endDate-picker").val(urlHash.endDate);
     }
	 getShirtList();
});

function getShirtList() {
	var selBrandId = $(".search-brand-sel").val();
	var selSourceId = $(".search-source-sel").val();
	var selActiveId = $(".search-active-sel").val();
	var beginDate = $("#search-beginDate-picker").val();
	var endDate = $("#search-endDate-picker").val();
	var urlHash=$.iteeHash.get();
	var pageNum = urlHash.pageNum||1;
	var pageSize = urlHash.pageSize||20;
	$("#edit-page-pageNum").val(pageNum);
	var url = "shirt-list.htm?pageNum="+pageNum+"&pageSize="+pageSize;
	$.iteeHash.add({"pageNum":pageNum});
	$.iteeHash.add({"pageSize":pageSize});
	if (parseInt(selBrandId) > 0) {
		url += "&brandId="+selBrandId;
		$.iteeHash.add({"brandId":selBrandId});
	}
	if (parseInt(selSourceId) > 0) {
		url += "&sourceId="+selSourceId;
		$.iteeHash.add({"sourceId":selSourceId});
	}
	if (parseInt(selActiveId) > 0) {
		url += "&isActive="+selActiveId;
		$.iteeHash.add({"isActive":selActiveId});
	}
	if (beginDate != "开始时间") {
		url += "&beginDate="+beginDate;
		$.iteeHash.add({"beginDate":beginDate});
	}
	if (endDate != "结束时间") {
		url += "&endDate="+endDate;
		$.iteeHash.add({"endDate":endDate});
	}
   	$.ajax({
        type:'get',
        url:url,
        async:false,
        dataType:'json',
        success:function(data){
        	if (data.flag == 0) {
				$("#shirt-state-data").empty();
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
							+ '<td>'+(item.isActive==1?'有效':'无效')+'</td>'
							+ '<td>'+(item.design==1?'纯色':'有图案')+'</td>'
							+ '<td>'+(item.colorNames!=null?item.colorNames:'-')+'</td>'
							+ '<td>'+(item.sleeve==1?'长袖':'短袖')+'</td>'
							+ '<td id="s-weight-'+item.shirtId+'">'+item.weight+'</td>'
							+ '<td id="s-status-'+item.shirtId+'">'+((item.weight>0 && item.isActive==1)?'显示':'不显示')+'</td>'
							+ '<td>'
							+ '<span class="blue-btn shirt-s-t-td-opt-0" id="s-satus-btn-'+item.shirtId+'" onclick="changeShirtStatus(\''+item.shirtId+'\');">'
							+ ((item.weight>0 && item.isActive==1)?'不显示':'显示')
							+ '</span>'
							+'</td>'
							+ '<td>'
							+ '<span class="blue-btn shirt-s-t-td-opt-1" onclick="showShirtBuyPage(\''+item.linkUrl+'\');">查看</span> '
							+ '<span class="blue-btn shirt-s-t-td-opt-2" onclick="showShirtEditLayer('+item.shirtId+');">编辑</span> '
							+ '<span class="red-btn shirt-s-t-td-opt-3" onclick="showShirtDeleteLayer('+item.shirtId+');">删除</span>'
							+ '</td>'
						+ '</tr>'
					);
        		});
        		var paginationEle = $("#shirt-add-pagination");
        		iteePagination(paginationEle, data.totalCount, data.pageNum, data.pageSize, 'clickPage');
        	}
	       	return;
       	}
    });
}

function changeShirtStatus(shirtId) {//status=1：显示, status=0：不显示
	var statusText = $("#s-status-"+shirtId).text();
	var status = 1;
	if (statusText == "显示") {
		status = 0;
	}
	$.ajax({
        type:'post',
        url:"change-shirt-status.htm?shirtId="+shirtId+"&status="+status,
        async:false,
        dataType:'json',
        success:function(data) {
        	if (data.flag == 0) {
        		$("#s-weight-"+shirtId).text(data.weight);
        		if (status == 1) {
        			$("#s-status-"+shirtId).text("显示");
        			$("#s-satus-btn-"+shirtId).text("不显示");
        		} else {
        			$("#s-status-"+shirtId).text("不显示");
        			$("#s-satus-btn-"+shirtId).text("显示");
        		}
        	}
        }
	});
}

function changeWeightScheduler() {
	var btnText = $(".change-weight-scheduler-btn").text();
	var status = 0;
	if (btnText == "开启") {
		status = 1;
	}
	$.ajax({
        type:'post',
        url:"change-weight-scheduler.htm?status="+status,
        async:false,
        dataType:'json',
        success:function(data) {
        	if (data.flag == 0) {
        		if (status == 1) {
        			$(".change-weight-scheduler-btn").text("关闭");
        		} else {
        			$(".change-weight-scheduler-btn").text("开启");
        		}
        	}
        }
	});
}

function clickPage(pageNum, pageSize) {
	$.iteeHash.add({"pageNum":pageNum});
	$.iteeHash.add({"pageSize":pageSize});
	getShirtList();
}

function searchShirt() {
	getShirtList();
}

function showShirtAddLayer() {
	resetLayer('add');
	$(".shirt-add-layer").show();
}

function hideShirtAddLayer() {
	$(".shirt-add-layer").hide();
}

function showShirtBuyPage(linkUrl) {
	window.open(linkUrl); 
}

function showShirtEditLayer(shirtId) {
	getShirtDetail(shirtId);
	$(".shirt-edit-layer").show();
}

function hideShirtEditLayer() {
	$(".shirt-edit-layer").hide();
	resetLayer('edit');
}

function showShirtDeleteLayer(shirtId) {
	$("#shirt-del-id").val(shirtId);
	$(".shirt-del-layer").show();
}

function hideShirtDeleteLayer() {
	$(".shirt-del-layer").hide();
}

function errorMsg(type, content) {
	$("#shirt-"+type+"-error").text(content);
	$("#shirt-"+type+"-error").css("opacity", 1);
	setTimeout(function() {
		$("#shirt-"+type+"-error").css("opacity", 0);
	},1000);
}

function selectImgType(type, imgType) {
	if (imgType == null) {
		imgType = $("#shirt-"+type+"-imgType").val();
	} else {
		$("#shirt-"+type+"-imgType").val(imgType);
	}
	$("#shirt-"+type+"-uploadfile-id").hide();
	$("#shirt-"+type+"-imgUrl-id").hide();
	if (imgType == 1) {
		$("#shirt-"+type+"-upload-filename").text("请选择");
		$("#shirt-"+type+"-uploadfile-id").show();
	} else if (imgType == 2) {
		$("#shirt-"+type+"-imgUrl-id").show();
	}
}

function selColorInShirt(type, colorId) {
	var colorIds = $("#shirt-"+type+"-shirt-color").val();
	if (colorIds.length > 0 && colorIds.lastIndexOf(",") != (colorIds.length-1)) {
		colorIds += ",";
	}
	if ($("#shirt-"+type+"-color-"+colorId).css("border-color")=="rgb(204, 204, 204)") {
		$("#shirt-"+type+"-color-"+colorId).css("border-color", "#2483CB");
		$("#shirt-"+type+"-color-"+colorId).css("background-color", "#2483CB");
		$("#shirt-"+type+"-color-"+colorId).css("color", "#FFFFFF");
		if (colorIds.indexOf(colorId + ",") == -1) {
			colorIds += colorId + ",";
		}
		$("#shirt-"+type+"-shirt-color").val(colorIds);
	} else {
		$("#shirt-"+type+"-color-"+colorId).css("border-color", "#CCCCCC");
		$("#shirt-"+type+"-color-"+colorId).css("background-color", "#FFFFFF");
		$("#shirt-"+type+"-color-"+colorId).css("color", "#000000");
		colorIds = colorIds.replace(colorId + ",", "");
		$("#shirt-"+type+"-shirt-color").val(colorIds);
	}
}

function setUploadFile(type) {
	var filename = $("#shirt-"+type+"-upload-file").val();
	var index = filename.lastIndexOf("\\") + 1;
	$("#shirt-"+type+"-upload-filename").text(filename.substring(index, filename.length));
}

function addShirt() {
	var linkUrl = $("#shirt-add-link-url").val();
	if ($.trim(linkUrl) == "") {
		errorMsg('add', "商品链接不能为空。");
		return;
	}
	var title = $("#shirt-add-title").val();
	if ($.trim(title) == "") {
		errorMsg('add', "标题不能为空。");
		return;
	}
	var price = $("#shirt-add-min-price").val();
	if ($.trim(price) == "") {
		errorMsg('add', "价格不能为空。");
		return;
	}
	$("#shirt-add-max-price").val(price);
	
	var sourceId = $("#shirt-add-source").val();
	if (sourceId == 0) {
		errorMsg('add', "请选择来源。");
		return;
	}
	
	var design = $("#shirt-add-design").val();
	if (design == 0) {
		errorMsg('add', "请选择图案。");
		return;
	}
	
	var brandId = $("#shirt-add-brandId").val();
	if (brandId == 0) {
		errorMsg('add', "请选择品牌。");
		return;
	}
	
	var colorIds = $("#shirt-add-shirt-color").val();
	if ($.trim(colorIds) == "") {
		errorMsg('add', "请选择颜色。");
		return;
	}
	
	var sleeve = $("#shirt-add-sleeve").val();
	if (sleeve == 0) {
		errorMsg('add', "请选择袖长。");
		return;
	}
	
	var imgType = $("#shirt-add-imgType").val();
	if (imgType == 1) {
		var filename = $("#shirt-add-upload-file").val();
		if ($.trim(filename) == "") {
			errorMsg('add', "请选择上传图片。");
			return;
		}
	} else if (imgType == 2) {
		var shirtImgUrl = $("#shirt-add-img-url").val();
		if ($.trim(shirtImgUrl) == "") {
			errorMsg('add', "请填写图片链接。");
			return;
		}
	}
	
	$("#shirt-add-shirt-color").val(colorIds.substring(0, colorIds.length-1));
	$("#addShirtForm").submit();
}

function editShirt() {
	var linkUrl = $("#shirt-edit-link-url").val();
	if ($.trim(linkUrl) == "") {
		errorMsg('edit', "商品链接不能为空。");
		return;
	}
	var title = $("#shirt-edit-title").val();
	if ($.trim(title) == "") {
		errorMsg('edit', "标题不能为空。");
		return;
	}
	var price = $("#shirt-edit-min-price").val();
	if ($.trim(price) == "") {
		errorMsg('edit', "价格不能为空。");
		return;
	}
	$("#shirt-edit-max-price").val(price);
	
	var sourceId = $("#shirt-edit-source").val();
	if (sourceId == 0) {
		errorMsg('edit', "请选择来源。");
		return;
	}
	
	var design = $("#shirt-edit-design").val();
	if (design == 0) {
		errorMsg('edit', "请选择图案。");
		return;
	}
	
	var brandId = $("#shirt-edit-brandId").val();
	if (brandId == 0) {
		errorMsg('edit', "请选择品牌。");
		return;
	}
	
	var colorIds = $("#shirt-edit-shirt-color").val();
	if ($.trim(colorIds) == "") {
		errorMsg('edit', "请选择颜色。");
		return;
	}
	
	var sleeve = $("#shirt-edit-sleeve").val();
	if (sleeve == 0) {
		errorMsg('edit', "请选择袖长。");
		return;
	}
	
	var weight = $("#shirt-edit-weight").val();
	if (weight == "") {
		errorMsg('edit', "请填写权值。");
		return;
	}
	
	var imgType = $("#shirt-edit-imgType").val();
	if (imgType == 1) {
		var filename = $("#shirt-edit-upload-file").val();
		var imgname = $("#shirt-edit-upload-filename").text();
		if (($.trim(imgname) == "请选择" || $.trim(imgname) == "") && $.trim(filename) == "") {
			errorMsg('edit', "请选择上传图片。");
			return;
		}
	} else if (imgType == 2) {
		var shirtImgUrl = $("#shirt-edit-img-url").val();
		if ($.trim(shirtImgUrl) == "") {
			errorMsg('edit', "请填写图片链接。");
			return;
		}
	}
	
	$("#shirt-edit-shirt-color").val(colorIds.substring(0, colorIds.length-1));
	$("#editShirtForm").submit();
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

function getShirtDetail(shirtId) {
	$("#shirt-edit-id").val(shirtId);
	$.ajax({
        type:'get',
        url:"shirt-detail.htm?shirtId="+shirtId,
        async:false,
        dataType:'json',
        success:function(data){
        	if (data.flag == 0) {
        		var shirtList = JSON.parse(data.shirtList);
        		$.each(shirtList,function(index, item) {
        			$("#shirt-edit-link-url").val(item.linkUrl);
					$("#shirt-edit-url").val(item.url);
        			$("#shirt-edit-title").val(item.title);
        			$("#shirt-edit-min-price").val(item.minPrice);
        			$("#shirt-edit-max-price").val(item.maxPrice);
        			if (item.sourceId != null) {
        				$("#shirt-edit-source").val(item.sourceId);
        			}
        			if (item.design != null) {
        				$("#shirt-edit-design").val(item.design);
        			}
        			if (item.brandId != null) {
        				$("#shirt-edit-brandId").val(item.brandId);
        			}
        			if (item.colorIds != null) {
        				$("#shirt-edit-shirt-color").val(item.colorIds);
        				var ids = item.colorIds.split(',');
        				for (var i=0; i<ids.length;i++) {
        					selColorInShirt('edit',ids[i]);
        				}
        			}
        			if (item.sleeve != null) {
        				$("#shirt-edit-sleeve").val(item.sleeve);
        			}
        			if (item.isActive != null) {
        				$("#shirt-edit-isActive").val(item.isActive);
        			}
        			$("#shirt-edit-weight").val(item.weight);
        			if (item.imgType != null) {
        				selectImgType('edit', item.imgType);
        				if (item.imgType == 1) {
        					if (item.shirtImg == null || item.shirtImg == "") {
        						$("#shirt-edit-upload-filename").text("请选择");
        					} else {
        						$("#shirt-edit-upload-filename").text(item.shirtImg);
        					}
        				} else if (item.imgType == 2) {
        					$("#shirt-edit-img-url").val(item.shirtImg);
        				}
        			}
        		});
    		}
        }
	});
}

function resetLayer(type) {
	$("#shirt-"+type+"-link-url").val("");
	$("#shirt-"+type+"-url").val("");
	$("#shirt-"+type+"-title").val("");
	$("#shirt-"+type+"-min-price").val("");
	$("#shirt-"+type+"-max-price").val("");
	$("#shirt-"+type+"-source").val(0);
	$("#shirt-"+type+"-design").val(0);
	$("#shirt-"+type+"-brandId").val(0);
	$("#shirt-"+type+"-shirt-color").val("");
	$(".shirt-add-item-color span").css("border-color", "#CCCCCC");
	$(".shirt-add-item-color span").css("background-color", "#FFFFFF");
	$(".shirt-add-item-color span").css("color", "#000000");
	$("#shirt-"+type+"-sleeve").val(0);
	$("#shirt-"+type+"-isActive").val(1);
	selectImgType(type, 1);
	$("#shirt-"+type+"-img-url").val("");
	$("#shirt-"+type+"-upload-filename").text("请选择");
}