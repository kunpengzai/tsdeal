<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head>
  
  	<meta charset="utf-8"/>
	<script>
	    var phoneWidth = parseInt(window.screen.width);
	    var phoneScale = phoneWidth / 540;
	    document.write('<meta name="viewport" content="width=540, initial-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', user-scalable=no target-densitydpi=device-dpi">');
	</script>
	<meta name="format-detection" content="telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="shortcut icon" href="/img/favicon.ico">
    <title>Miles麦斯 - 找到最好的T恤!</title>
   	<link rel="stylesheet" href="<%=path%>/css/shirt/shirt-list.css?t=20151206010"/>
  </head>
  <body>
 	<!-- header part start -->
 	<div class="s-header-top"></div>
  	<div class="s-header-part">
		<div class="s-header-1">
		  	<%--<span class="s-header-left-1">itee</span>
		  	<span class="s-header-left-2">最好的T恤推荐平台</span>--%>
			<span class="s-header-left"><img src="../img/logo.png"></span>
			<span class="s-header-right-2">关注我们</span>
			<span class="s-header-right-1"><img id="s-show-search" src="../img/search1.png"></span>
			<span class="s-header-right-0"><input type="text" id="s-search" placeholder="搜索" /><img id="s-search-btn" src="../img/search2.png"></span>
		</div>
		<div class="s-header-border"></div>
  	</div>
	<div class="qrcode-layer-part">
		<div class="qrcode-layer">
			<div class="qrcode-border-img">
				<img src="../img/triangle.png">
			</div>
			<div class="qrcode-content">
				<img src="../img/qrcode.png" width="144px" height="144px">
			</div>
		</div>
	</div>
  	<!-- header part end -->
  	<div class="s-filter">
	  	<div class="s-interval-1"></div>
	  	<div class="s-filter-part">
		  	<div class="s-filter-title">
		  		<span class="s-filter-title-1">
		  			<span class="s-filter-title-tab tab-1"></span>售价
		  		</span>
		  		<span class="s-filter-title-2">
		  			<span class="s-filter-title-tab tab-2"></span>颜色
		  		</span>
		  		<span class="s-filter-title-3">
		  			<span class="s-filter-title-tab tab-3"></span>品牌
		  		</span>
		  	</div>
		  	<div class="s-filter-value">
		  		<span class="s-filter-value-1">
					<span class="s-filter-price-cls" data-value="0">全部</span>
					<c:forEach var="item" items="${priceRangeList}" varStatus="status">
						<span class="s-filter-price-cls" data-value="${item.id}" data-minPrice="${item.minPrice}" data-maxPrice="${item.maxPrice}">${item.minPrice}<c:choose><c:when test="${item.maxPrice != null}">~${item.maxPrice}</c:when><c:otherwise>以上</c:otherwise></c:choose></span>
					</c:forEach>
		  		</span>
		  		<span class="s-filter-value-2">
					<span class="s-filter-color-cls" data-value="0">全部</span>
					<c:forEach var="item" items="${colorList}" varStatus="status">
						<span class="s-filter-color-cls" data-value="${item.id}">${item.name}</span>
					</c:forEach>
					<span class="s-filter-color-cls" data-value="10000">其他</span>
				</span>
		  		<span class="s-filter-value-3">
					<span class="s-filter-brand-cls" data-value="0">全部</span>
					<c:forEach var="item" items="${brandList}" varStatus="status">
						<span class="s-filter-brand-cls" data-value="${item.id}">${item.name}</span>
					</c:forEach>
					<span class="s-filter-brand-cls" data-value="10000">其他</span>
				</span>
		  	</div>
			<div class="s-filter-title">
		  		<span class="s-filter-title-1">
		  			<span class="s-filter-title-tab tab-4"></span>来源
		  		</span>
		  		<span class="s-filter-title-2">
		  			<span class="s-filter-title-tab tab-5"></span>图案
		  		</span>
		  		<span class="s-filter-title-3">
		  			<span class="s-filter-title-tab tab-6"></span>袖长
		  		</span>
			</div>
			<div class="s-filter-value">
		  		<span class="s-filter-value-1">
					<span class="s-filter-source-cls" data-value="0">全部</span>
					<c:forEach var="item" items="${sourceList}" varStatus="status">
						<span class="s-filter-source-cls" data-value="${item.id}">${item.name}</span>
					</c:forEach>
					<span class="s-filter-source-cls" data-value="10000">其他</span>
		  		</span>
		  		<span class="s-filter-value-2">
					<span class="s-filter-design-cls" data-value="0">全部</span>
					<span class="s-filter-design-cls" data-value="1">纯色</span>
					<span class="s-filter-design-cls" data-value="3">印花</span>
					<span class="s-filter-design-cls" data-value="4">条纹</span>
					<span class="s-filter-design-cls" data-value="2">图案</span>
				</span>
		  		<span class="s-filter-value-3">
					<span class="s-filter-sleeve-cls" data-value="0">全部</span>
					<span class="s-filter-sleeve-cls" data-value="1">长袖</span>
					<span class="s-filter-sleeve-cls" data-value="2">短袖</span>
				</span>
			</div>
	  	</div>
		<div class="s-filter-border"></div>

	  	<%--<div class="s-list">--%>
	  		<%--<div class="s-box">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
	  		</div>
			<div class="s-box">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>
			<div class="s-box">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>--%>
	  		<%--<div class="s-box">
	  			<div class="s-box-img">
	  				<img src="../img/shirt_4_8408_20151227161800.jpg" width="218px"/>
	  			</div>
	  		</div>
	  		<div class="s-box">
	  			<div class="s-box-img">
	  				<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  			</div>
	  		</div>
	  		<div class="s-box">
	  			<div class="s-box-img">
	  				<img src="../img/shirt_19_3209_20151229155136.jpg" width="218px"/>
	  			</div>
	  		</div>--%>
	  		<!-- <div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  			<div class="s-l-con">
	  				复仇者联盟
	  			</div>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_4_8408_20151227161800.jpg" width="218px"/>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_17_47_20151229154924.jpg" width="218px"/>
	  		</div>
	  		<div class="s-l-item">
	  			<img src="../img/shirt_19_3209_20151229155136.jpg" width="218px"/>
	  		</div> -->
	  	<%--</div>--%>
  	</div>
	<div id="s-container" class="s-shirt-content">
		<%--<li class="s-item"><img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_17_47_20151229154924.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_19_3209_20151229155136.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_17_47_20151229154924.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_17_47_20151229154924.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_17_47_20151229154924.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_19_3209_20151229155136.jpg" width="220px"/></div>
		<div class="s-item"><img src="../img/shirt_17_47_20151229154924.jpg" width="220px"/></div>--%>
			<%--<div class="s-item">
                <img src="../tsdImg/shirt_72_6208_20160109152946.jpg" width="220px"/>
                <div class="s-box-s-content">
                    <div class="s-box-s-title">72EVISU 2015秋冬新品 男式短袖T恤 专柜价790 AU15HMTS4500</div>
                    <div class="s-box-s-interval"></div>
                    <div class="s-box-s-price">
                        <span class="rmb">¥</span><span class="price">790</span><span class="brand">Evisu</span>
                    </div>
                    <div class="s-box-s-source">
                        <span class="source">天猫</span>
                        <span class="color"><span class="s-box-s-c" style="background-color: #C9C9C9"></span></span>
                    </div>
                    <div class="s-box-s-border"></div>
                </div>
            </div>
            <div class="s-item"><img src="../tsdImg/shirt_71_4946_20160109152739.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">71EVISU 2015秋冬新品 男式短袖T恤 专柜价650 AU15HMTS3900
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">650</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FF0000"></span><span class="s-box-s-c" style="background-color: #00A2E6"></span><span class="s-box-s-c" style="background-color: #FFEF00"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_70_8267_20160109152637.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">70EVISU 2015秋冬新品 男式短袖T恤 专柜价590 AU15QMTS1200
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">590</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_69_1142_20160109152557.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">69EVISU 2015秋冬新品 男式短袖T恤 专柜价790 AU15HMTS4100
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">790</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_68_2638_20160109152520.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">68EVISU 2015新品 男士T恤 专柜价1090 S15WHMTS1000
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">763</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_67_6094_20160109152434.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">67EVISU 2015新品 男式短袖T恤 专柜价990 S15WHUTS4400
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">693</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_66_1673_20160109152350.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">66EVISU 2015秋冬新品 男式短袖T恤 专柜价590 AU15HMTS2500
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">590</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span><span class="s-box-s-c" style="background-color: #00A2E6"></span><span class="s-box-s-c" style="background-color: #FF0000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_64_4336_20160109152121.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">64EVISU 男式短袖T恤 专柜价650 A14EGMTS7400E
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">455</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_63_2880_20160109152041.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">63EVISU 男式短袖T恤 专柜价850 A14WHMTS240AE
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">595</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_62_8166_20160109151918.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">62EVISU 男式短袖T恤 专柜价650 A14EGMTS7200E
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">455</span><span class="brand">Evisu</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_61_8501_20151229163035.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">61Levi's李维斯夏季男士Logo印花纯棉白色短袖T恤21945-0059
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_60_7716_20151229163000.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">60Levi's李维斯夏季男士Logo印花纯棉白色圆领短袖T恤21945-0043
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_59_2596_20151229162915.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">59Levi's李维斯夏季男士Logo印花纯棉白色短袖T恤21945-0045
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_58_9877_20151229162825.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">58Levi's李维斯男士Logo印花纯棉黑色圆领短袖T恤21945-0042
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_57_2261_20151229162731.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">57Levi's李维斯夏季男士Logo印花纯棉黑色圆领短袖T恤21945-0046
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #000000"></span></span></div><div class="s-box-s-border"></div></div></div>
            <div class="s-item"><img src="../tsdImg/shirt_56_581_20151229162645.jpg" width="220px"/><div class="s-box-s-content"><div class="s-box-s-title">56Levi's李维斯男士LOGO纯棉圆领短袖T恤17785-0077
            </div><div class="s-box-s-interval"></div><div class="s-box-s-price"><span class="rmb">¥</span><span class="price">199</span><span class="brand">LifeCycle</span></div><div class="s-box-s-source"><span class="source">天猫</span><span class="color"><span class="s-box-s-c" style="background-color: #FFFFFF"></span></span></div><div class="s-box-s-border"></div></div></div>
--%>
			<%--<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
	  		</div>
			<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>
			<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>
			<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>
			<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>
			<div class="s-item">
				<img src="../img/shirt_4_8408_20151227161800.jpg" width="220px"/>
				<div class="s-box-s-content">
					<div class="s-box-s-title">8-bit复仇者联T恤特色男士衬衫复仇者联T恤特色男士衬衫</div>
					<div class="s-box-s-interval"></div>
					<div class="s-box-s-price">
						<span class="rmb">¥</span><span class="price">298</span>
						<span class="brand">色男士衬衫</span>
					</div>
					<div class="s-box-s-source">
						<span class="source">淘宝淘宝</span>
						<span class="color">
							<span class="s-box-s-c"></span>
							<span class="s-box-s-c"></span>
						</span>
					</div>
					<div class="s-box-s-border"></div>
				</div>
			</div>--%>
	</div>
	<div class="s-shirt-not-data">没有查到相关T恤，再换个试试呗~~~</div>
	<div class="get-more-btn">
		<span>加载更多</span>
	</div>
  	<div class="s-shirt-blank"></div>

  </body>
  <%@include file="../layout/commonjs.jsp" %>
  <script src="<%=path%>/js/shirt/shirt-list.js?t=20151206010"></script>
</html>