<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../layout/taglib.jsp" %>
<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="../layout/htmlHeader.jsp"%>
    <title>iTEE管理后台</title>
    <link rel="stylesheet" href="<%=path%>/css/jquery.datetimepicker.css">
   	<link rel="stylesheet" href="<%=path%>/css/manager/shirt-state.css?t=20151206000"/>
  </head>
  <body>
  	<div class="shirt-state">
  		<div class="shirt-state-title">iTEE后台管理</div>
  		<div class="shirt-state-search">
  			<span class="search-brand">品牌
  				<select class="search-brand-sel select">
  					<option value="0" selected="selected">请选择</option>
  					<c:forEach items="${brandList}" var="brand" varStatus="status">
  						<option value="${brand.id}">${brand.name}</option>
  					</c:forEach>
  				</select>
  			</span>
  			<span class="search-source">来源
  				<select class="search-source-sel select">
  					<option value="0" selected="selected">请选择</option>
  					<c:forEach items="${sourceList}" var="source" varStatus="status">
  						<option value="${source.id}">${source.name}</option>
  					</c:forEach>
  				</select>
  			</span>
  			<span class="search-active">链接状态
  				<select class="search-active-sel select">
  					<option value="0" selected="selected">请选择</option>
  					<option value="1">有效</option>
  					<option value="2">无效</option>
  				</select>
  			</span>
  			<span class="search-beginDate">创建时间
				<input type="text" id="search-beginDate-picker" class='timepicker input' readonly	value="开始时间" />
  			</span>
  			<span class="search-endDate">-
  				<input type="text" id="search-endDate-picker" class='timepicker input' readonly value="结束时间" />
  			</span>
  			<span class="search-s-shirt blue-btn" onclick="searchShirt();">搜索</span>
  			<span class="search-add-shirt blue-btn" onclick="showShirtAddLayer();">添加新商品</span>
  		</div>
  		<div class="shirt-state-info">
  			<table class="shirt-state-table">
  				<thead>
  				<tr>
	  				<th class="shirt-s-t-th-1">编号</th>
	  				<th class="shirt-s-t-th-2">标题</th>
	  				<th class="shirt-s-t-th-3">来源</th>
	  				<th class="shirt-s-t-th-4">品牌</th>
	  				<th class="shirt-s-t-th-5">点击次数</th>
	  				<th class="shirt-s-t-th-6">售价</th>
	  				<th class="shirt-s-t-th-7">是否有效</th>
	  				<th class="shirt-s-t-th-8">图案</th>
	  				<th class="shirt-s-t-th-9">颜色</th>
	  				<th class="shirt-s-t-th-10">袖长</th>
	  				<th class="shirt-s-t-th-11">操作</th>
  				</tr>
  				</thead>
  				<tbody id="shirt-state-data">
                </tbody>
  				<!-- <tr>
  					<td>1</td>
  					<td>标题</td>
	  				<td>天猫</td>
	  				<td>无印良品</td>
	  				<td>1234567</td>
	  				<td>6000</td>
	  				<td>是</td>
	  				<td>有图案</td>
	  				<td>黑色/白色/黑色</td>
	  				<td>长袖</td>
	  				<td>
	  					<span class="shirt-s-t-td-opt-1">查看</span>
	  					<span class="shirt-s-t-td-opt-2">编辑</span>
	  					<span class="shirt-s-t-td-opt-3">删除</span>
	  				</td>
  				</tr> -->
  			</table>
  		</div>
  	</div>
  	
  	<!-- shirt add layer -->
  	<div class="shirt-add-layer">
		<div class="shirt-add-part">
			<div class="shirt-add-part-title">
				<span class="shirt-add-part-title-left">添加商品</span>
				<span class="shirt-add-part-title-right" onclick="hideShirtAddLayer();">
					<img src="../img/layer_close.png" width="16px" height="16px"/>
				</span>
			</div>
			<div class="shirt-add-content">
				<div class="shirt-add-s-url">
					<span>商品链接</span>
					<input type="text" id="shirt-add-link-url" name="linkUrl" class='input' />
				</div>
				<div class="shirt-add-s-title">
					<span>标题</span>
					<textarea id="shirt-add-title" name="title" class='textarea'></textarea>
				</div>
				<div class="shirt-add-s-price">
					<span class="shirt-add-item-price">售价</span>
					<input type="text" id="shirt-add-min-price" name="minPrice" class='input' /> 元
					<input type="hidden" id="shirt-add-max-price" name="maxPrice" />
					<span class="shirt-add-item-source">来源</span>
					<select class="shirt-add-source-sel select" name="sourceId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${sourceList}" var="source" varStatus="status">
	  						<option value="${source.id}">${source.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-design">
					<span class="shirt-add-item-design">图案</span>
					<select class="shirt-add-design-sel select" name="design">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">纯色</option>
  						<option value="2">有图案</option>
	  				</select>
					<span class="shirt-add-item-brand">品牌</span>
					<select class="shirt-add-brand-sel select" name="brandId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${brandList}" var="brand" varStatus="status">
	  						<option value="${brand.id}">${brand.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-color">
					<span class="shirt-add-item-color-label">颜色</span>
					<span class="shirt-add-item-color">
						<c:forEach items="${colorList}" var="color" varStatus="status">
	  						<span colorId="${color.id}">${color.name}</span>
	  					</c:forEach>
					</span>
				</div>
				<div class="shirt-add-s-sleeve">
					<span class="shirt-add-item-sleeve">袖长</span>
					<select class="shirt-add-sleeve-sel select" name="sleeve">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">长袖</option>
  						<option value="2">短袖</option>
	  				</select>
				</div>
				<div class="shirt-add-s-imgType">
					<span class="shirt-add-item-imgType">图片类型</span>
					<input type="hidden" id="shirt-add-shirt-img" name="shirtImg" class='input' />
					<select class="shirt-add-imgType-sel select" name="imgType"  onchange="selectImgType();">
  						<option value="1" selected="selected">上传图片</option>
  						<option value="2">图片链接</option>
	  				</select>
				</div>
				<div class="shirt-add-s-uploadfile">
					<span class="shirt-add-item-uploadfile">上传图片</span>
					<input type="file" id="shirt-add-upload-file" class='input' />
				</div>
				<div class="shirt-add-s-imgUrl" style="display:none;">
					<span class="shirt-add-item-imgUrl">图片链接</span>
					<input type="text" id="shirt-add-img-url" class='input' />
				</div>
				<div class="shirt-add-s-btn">
					<span class="blue-btn" onclick="addShirt();">确定</span>
				</div>
			</div>
			
		</div>
	</div>
	
	<!-- shirt edit layer-->
	<div class="shirt-edit-layer">
		<div class="shirt-edit-part">
			<div class="shirt-edit-part-title">
				<span class="shirt-edit-part-title-left">编辑商品</span>
				<span class="shirt-edit-part-title-right" onclick="hideShirtEditLayer();">
					<img src="../img/layer_close.png" width="16px" height="16px"/>
				</span>
			</div>
		</div>
	</div>
	
  </body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/jquery.datetimepicker.v.2.3.8.js"></script>
<script src="<%=path%>/js/manager/shirt-state.js?t=20151206000"></script>
</html>