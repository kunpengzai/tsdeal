<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="itee-header-part">
	<span class="itee-header-title">iTEE后台管理</span>
	<span class="itee-header-logout" onclick="showShirtlogoutLayer();">
		<img src="../img/setting.png" width="20px" height="20px"/>
	</span>
</div>

<div class="shirt-logout-layer">
	<div class="shirt-logout-part">
		<div class="shirt-logout-part-title">
			<span class="shirt-logout-part-title-left">退出登录</span>
			<span class="shirt-logout-part-title-right" onclick="hideShirtlogoutLayer();">
				<img src="../img/layer_close.png" width="16px" height="16px"/>
			</span>
		</div>
		<div class="shirt-logout-content">
			<span>确定退出ITEE后台管理吗？</span>
		</div>
		<a href="../iteelogin/logout.htm"><div class="red-btn shirt-logout-btn">退出</div></a>
	</div>
</div>