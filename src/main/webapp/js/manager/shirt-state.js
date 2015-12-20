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
        	//console.log(data);
        	if (data.flag == 0) {
        		if (data.shirtList == null) {
        			return;
        		}
        		var shirtList = JSON.parse(data.shirtList);
        		$.each(shirtList,function(index, item) {
	        		$("#shirt-state-data").append(
		        		'<tr>'
							+ '<td>'+item.id+'</td>'
							+ '<td>'+item.title+'</td>'
							+ '<td>'+item.sourceName+'</td>'
							+ '<td>'+item.brandName+'</td>'
							+ '<td>'+item.clickNum+'</td>'
							+ '<td>'+item.minPrice+'</td>'
							+ '<td>'+(item.isActive==0?'是':'否')+'</td>'
							+ '<td>'+(item.design==0?'纯色':'有图案')+'</td>'
							+ '<td>'+item.colorNames+'</td>'
							+ '<td>'+(item.sleeve==0?'长袖':'短袖')+'</td>'
							+ '<td>'
							+ '<span class="shirt-s-t-td-opt-1">查看</span> '
							+ '<span class="shirt-s-t-td-opt-2" onclick="showShirtEditLayer('+item.id+');">编辑</span> '
							+ '<span class="shirt-s-t-td-opt-3">删除</span>'
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

function showShirtEditLayer() {
	$(".shirt-edit-layer").show();
}

function hideShirtEditLayer() {
	$(".shirt-edit-layer").hide();
}