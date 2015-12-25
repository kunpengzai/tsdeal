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
			<form role="form" enctype="multipart/form-data" id="addShirtForm" action="add-shirt.htm" method="post">
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
					<select class="shirt-add-source-sel select" id="shirt-add-source" name="sourceId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${sourceList}" var="source" varStatus="status">
	  						<option value="${source.id}">${source.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-design">
					<span class="shirt-add-item-design">图案</span>
					<select class="shirt-add-design-sel select" id="shirt-add-design" name="design">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">纯色</option>
  						<option value="2">有图案</option>
	  				</select>
					<span class="shirt-add-item-brand">品牌</span>
					<select class="shirt-add-brand-sel select" id="shirt-add-brandId" name="brandId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${brandList}" var="brand" varStatus="status">
	  						<option value="${brand.id}">${brand.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-color">
					<span class="shirt-add-item-color-label">颜色</span>
					<input type="hidden" id="shirt-add-shirt-color" name="colorIds" />
					<span class="shirt-add-item-color">
						<c:forEach items="${colorList}" var="color" varStatus="status">
	  						<span id="shirt-add-color-${color.id}" onclick="selColorInShirt('add',${color.id});">${color.name}</span>
	  					</c:forEach>
					</span>
				</div>
				<div class="shirt-add-s-sleeve">
					<span class="shirt-add-item-sleeve">袖长</span>
					<select class="shirt-add-sleeve-sel select" id="shirt-add-sleeve" name="sleeve">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">长袖</option>
  						<option value="2">短袖</option>
	  				</select>
	  				<span class="shirt-add-item-active">链接状态</span>
	  				<select class="shirt-add-active-sel select" id="shirt-add-isActive" name="isActive">
	  					<option value="1" selected="selected">有效</option>
	  					<option value="2">无效</option>
	  				</select>
				</div>
				<div class="shirt-add-s-imgType">
					<span class="shirt-add-item-imgType">图片类型</span>
					<!-- <input type="hidden" id="shirt-add-shirt-img" name="shirtImg" class='input' /> -->
					<select class="shirt-add-imgType-sel select" id="shirt-add-imgType" name="imgType" onchange="selectImgType('add');">
  						<option value="1" selected="selected">上传图片</option>
  						<option value="2">图片链接</option>
	  				</select>
				</div>
				<div class="shirt-add-s-uploadfile" id="shirt-add-uploadfile-id">
					<span class="shirt-add-item-uploadfile">上传图片</span>
					<span class="blue-btn shirt-add-sel-btn">选择文件</span>
					<span class="shirt-add-sel-filename" id="shirt-add-upload-filename">请选择</span>
					<div class="shirt-add-item-uf-input">
						<input type="file" id="shirt-add-upload-file" name="imageFile" style="opacity:0;" onchange="setUploadFile('add');" />
					</div>
				</div>
				<div class="shirt-add-s-imgUrl" id="shirt-add-imgUrl-id" style="display:none;">
					<span class="shirt-add-item-imgUrl">图片链接</span>
					<input type="text" id="shirt-add-img-url" name="shirtImg" class='input' />
				</div>
				<div class="shirt-add-s-btn">
					<span class="shirt-add-error-msg"></span>
					<span class="blue-btn shirt-add-btn" onclick="addShirt();">确定</span>
				</div>
			</div>
			</form>
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
			<form role="form" enctype="multipart/form-data" id="editShirtForm" action="edit-shirt.htm" method="post">
			<div class="shirt-add-content">
				<input type="hidden" id="shirt-edit-id" name="shirtId" value=""/>
				<div class="shirt-add-s-url">
					<span>商品链接</span>
					<input type="text" id="shirt-edit-link-url" name="linkUrl" class='input' />
				</div>
				<div class="shirt-add-s-title">
					<span>标题</span>
					<textarea id="shirt-edit-title" name="title" class='textarea'></textarea>
				</div>
				<div class="shirt-add-s-price">
					<span class="shirt-add-item-price">售价</span>
					<input type="text" id="shirt-edit-min-price" name="minPrice" class='input' /> 元
					<input type="hidden" id="shirt-edit-max-price" name="maxPrice" />
					<span class="shirt-add-item-source">来源</span>
					<select class="shirt-add-source-sel select" id="shirt-edit-source" name="sourceId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${sourceList}" var="source" varStatus="status">
	  						<option value="${source.id}">${source.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-design">
					<span class="shirt-add-item-design">图案</span>
					<select class="shirt-add-design-sel select" id="shirt-edit-design" name="design">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">纯色</option>
  						<option value="2">有图案</option>
	  				</select>
					<span class="shirt-add-item-brand">品牌</span>
					<select class="shirt-add-brand-sel select" id="shirt-edit-brandId" name="brandId">
	  					<option value="0" selected="selected">请选择</option>
	  					<c:forEach items="${brandList}" var="brand" varStatus="status">
	  						<option value="${brand.id}">${brand.name}</option>
	  					</c:forEach>
	  				</select>
				</div>
				<div class="shirt-add-s-color">
					<span class="shirt-add-item-color-label">颜色</span>
					<input type="hidden" id="shirt-edit-shirt-color" name="colorIds" />
					<span class="shirt-add-item-color">
						<c:forEach items="${colorList}" var="color" varStatus="status">
	  						<span id="shirt-edit-color-${color.id}" onclick="selColorInShirt('edit',${color.id});">${color.name}</span>
	  					</c:forEach>
					</span>
				</div>
				<div class="shirt-add-s-sleeve">
					<span class="shirt-add-item-sleeve">袖长</span>
					<select class="shirt-add-sleeve-sel select" id="shirt-edit-sleeve" name="sleeve">
	  					<option value="0" selected="selected">请选择</option>
  						<option value="1">长袖</option>
  						<option value="2">短袖</option>
	  				</select>
	  				<span class="shirt-add-item-active">链接状态</span>
	  				<select class="shirt-add-active-sel select" id="shirt-edit-isActive" name="isActive">
	  					<option value="1" selected="selected">有效</option>
	  					<option value="2">无效</option>
	  				</select>
				</div>
				<div class="shirt-add-s-imgType">
					<span class="shirt-add-item-imgType">图片类型</span>
					<!-- <input type="hidden" id="shirt-edit-shirt-img" name="shirtImg" class='input' /> -->
					<select class="shirt-add-imgType-sel select" id="shirt-edit-imgType" name="imgType" onchange="selectImgType('edit');">
  						<option value="1" selected="selected">上传图片</option>
  						<option value="2">图片链接</option>
	  				</select>
				</div>
				<div class="shirt-add-s-uploadfile" id="shirt-edit-uploadfile-id">
					<span class="shirt-add-item-uploadfile">上传图片</span>
					<span class="blue-btn shirt-add-sel-btn">选择文件</span>
					<span class="shirt-add-sel-filename" id="shirt-edit-upload-filename">请选择</span>
					<div class="shirt-add-item-uf-input">
						<input type="file" id="shirt-edit-upload-file" name="imageFile" style="opacity:0;" onchange="setUploadFile('edit');" />
					</div>
				</div>
				<div class="shirt-add-s-imgUrl" id="shirt-edit-imgUrl-id" style="display:none;">
					<span class="shirt-add-item-imgUrl">图片链接</span>
					<input type="text" id="shirt-edit-img-url" name="shirtImg" class='input' />
				</div>
				<div class="shirt-add-s-btn">
					<span class="shirt-add-error-msg"></span>
					<span class="blue-btn shirt-add-btn" onclick="editShirt();">确定</span>
				</div>
			</div>
			</form>
		</div>
	</div>
	
	<div class="shirt-del-layer">
		<div class="shirt-del-part">
			<div class="shirt-del-part-title">
				<span class="shirt-del-part-title-left">删除商品</span>
				<span class="shirt-del-part-title-right" onclick="hideShirtDeleteLayer();">
					<img src="../img/layer_close.png" width="16px" height="16px"/>
				</span>
			</div>
			<div class="shirt-del-content">● 请注意，该商品删除后，在后台管理列表页面将不会再显示该商品，该操作为不可逆操作。</div>
			<div class="shirt-del-s-btn">
				<input type="hidden" id="shirt-del-id" value=""/>
				<span class="red-btn shirt-del-btn" onclick="deleteShirt();">确定</span>
			</div>
		</div>
	</div>
  </body>
<script src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/js/jquery.datetimepicker.v.2.3.8.js"></script>
<script src="<%=path%>/js/manager/shirt-state.js?t=20151206000"></script>
</html>