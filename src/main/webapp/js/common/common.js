(function(window, undefined) {
    "use strict";
    var hash = (function() {
        var fromHash = function() {
            var params = window.location.hash ? window.location.hash.substr(1).split("&") : [],
                paramsObject = {};

            for(var i = 0; i < params.length; i++) {
                var a = params[i].split("=");
                paramsObject[a[0]] =  decodeURIComponent(a[1]);
            }
            return paramsObject;
        };

        var toHash = function(params) {
            var str = [];
            for(var p in params) {
                str.push(p + "=" + encodeURIComponent(params[p]));
            }
            window.location.hash = str.join("&");
        };

        return {
            get: function(param) {
                var params = fromHash();
                if (param) {
                    return params[param];
                } else {
                    return params;
                }
            },
            add: function(newParams) {
                var params = fromHash();
                for (var p in newParams) {
                    params[p] = newParams[p];
                }
                toHash(params);
            },
            remove: function(removeParams) {
                removeParams = (typeof(removeParams)=='string') ? [removeParams] : removeParams;
                var params = fromHash();
                for (var i = 0; i < removeParams.length; i++) {
                    delete params[removeParams[i]];
                }
                toHash(params);
            },
            clear: function() {
                toHash({});
            }
        };
    })();

    $.iteeHash = hash;
})(window);

/**
 * 分页方法
 * @param paginationEle 分页组件，如：$("#shirt-add-pagination")
 * @param totalCount 总页数
 * @param pageNum 当前页
 * @param pageSize 每页数据数
 * @param clickMethod. 执行点击的方法，需要自己实现，含两个参数，如：clickPage(pageNum, pageSize)
 */
function iteePagination(paginationEle, totalCount, pageNum, pageSize, clickMethod) {
	paginationEle.empty();
	var pageNumCount=Math.ceil(totalCount/pageSize);
	if (pageNumCount == 0) {
		paginationEle.hide();
		return;
	}
	if (pageNum == 1) {
		paginationEle.append('<span class="pagination-item-point">&lt;</span>');
		paginationEle.append('<span class="pagination-item-sel">1</span>');
	} else {
		paginationEle.append('<span class="pagination-item" onclick="'+clickMethod+'('+(pageNum-1)+','+pageSize+');">&lt;</span>');
		paginationEle.append('<span class="pagination-item" onclick="'+clickMethod+'(1,'+pageSize+');">1</span>');
	}
	var pageIndex = [1];
	if (pageNumCount > 2) {
		pageIndex.push(pageNumCount);
		for (var i= 2; i<pageNumCount; i++) {
			switch(true) {
	        case ((pageNum) == i):
	        	if ($.inArray(pageNum, pageIndex) != -1) break;
	        	pageIndex.push(pageNum);
	        	paginationEle.append('<span class="pagination-item-sel">'+pageNum+'</span>');
	            break;
	        case ((i-pageNum) == 3 || (i-pageNum) == -3):
	        	paginationEle.append('<span class="pagination-item-point">...</span>');
	        	break;
	        case ((i-pageNum) <= 2 && (i-pageNum) >= -2):
	        	if ($.inArray(i, pageIndex) != -1) break;
				pageIndex.push(i);
	        	paginationEle.append('<span class="pagination-item" onclick="'+clickMethod+'('+i+','+pageSize+');">'+i+'</span>');
	        	break;
	        default:
	        	break;
			}
		}
	}
	if (pageNum == pageNumCount) {
		if (pageNumCount > 1) {
			paginationEle.append('<span class="pagination-item-sel">'+pageNumCount+'</span>');
		}
		paginationEle.append('<span class="pagination-item-point">&gt;</span>');
	} else {
		if (pageNumCount > 1) {
			paginationEle.append('<span class="pagination-item" onclick="'+clickMethod+'('+pageNumCount+','+pageSize+');">'+pageNumCount+'</span>');
		}
		paginationEle.append('<span class="pagination-item" onclick="'+clickMethod+'('+(pageNum+1)+','+pageSize+');">&gt;</span>');
	}
}