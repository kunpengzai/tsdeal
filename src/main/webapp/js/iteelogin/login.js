function clickSubmit() {
	if ($.trim($("#userNameId").val()) == "输入账号" 
    			|| $.trim($("#userNameId").val()) == "" ) {
		$(".itee-login-error-msg").text("请输入账号！");
		return;
	}
	if ($.trim($("#passwordId").val()) == "输入密码" 
    			|| $.trim($("#passwordId").val()) == "" ) {
		$(".itee-login-error-msg").text("请输入密码！");
		return;
	}
	$("#loginForm").submit();
}