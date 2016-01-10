<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head>
  
  	<meta charset="utf-8"/>
	<!-- <script> 
	    var phoneWidth = parseInt(window.screen.width);
	    var phoneScale = phoneWidth / 640;
	    document.write('<meta name="viewport" content="width=640, initial-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', user-scalable=no target-densitydpi=device-dpi">');
	</script>
	<meta name="format-detection" content="telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" /> -->
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="shortcut icon" href="/img/favicon.ico">
	<link rel="stylesheet" href="<%=path%>/css/common/common.css?t=20151206000"/>
	
    <title>itee 最好的T恤推荐平台</title>
   	<link rel="stylesheet" href="<%=path%>/css/shirt/shirt-list.css?t=20151206000"/>
  </head>
  <body>
 	<!-- header part start -->
 	<div class="s-header-top"></div>	
  	<div class="s-header-part">
		<div class="s-header-1">
		  	<span>itee</span>
		  	<span>最好的T恤推荐平台</span>
		  	<span>search</span>
		  	<span>关注我们</span>
		  </div>
  	</div>
  	<!-- header part end -->
  	<div class="s-content">
	  	<div class="s-interval-1"></div>
	  	<div class="s-filter-part">
		  	<div class="s-filter-title">
		  		<span class="s-filter-title-1">
		  			<span class="s-filter-title-tab tab-blue"></span>售价
		  		</span>
		  		<span class="s-filter-title-2">
		  			<span class="s-filter-title-tab tab-red"></span>颜色
		  		</span>
		  		<span class="s-filter-title-3">
		  			<span class="s-filter-title-tab tab-green"></span>品牌
		  		</span>
		  	</div>
		  	<div class="s-filter-value">
		  		<span class="s-filter-value-1">
	  				<span>全部</span>
	  				<span>0~100</span>
	  				<span>100~150</span>
	  				<span>150~200</span>
	  				<span>200~300</span>
	  				<span>300以上</span>
		  		</span>
		  		<span class="s-filter-value-2">售价</span>
		  		<span class="s-filter-value-3">售价</span>
		  	</div>
	  	</div>
	  	<div class="s-list">
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  			<div class="s-l-con">
	  				复仇者联盟
	  			</div>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_18_9422_20151229155038.jpg" width="218px"/>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_19_3209_20151229155136.jpg" width="218px"/>
	  		</div> -->
	  		
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  			<div class="s-l-con">
	  				复仇者联盟
	  			</div>
	  		</div> -->
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_18_9422_20151229155038.jpg" width="218px"/>
	  		</div> -->
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  		</div> -->
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_19_3209_20151229155136.jpg" width="218px"/>
	  		</div> -->
	  	</div>
	  	<div class="get-more-btn">
	  		<span>加载更多</span>
	  	</div>
  	</div>
  </body>
  <%@include file="../layout/commonjs.jsp" %>
  <script src="<%=path%>/js/shirt/shirt-list.js?t=20151206000"></script>
</html>