function _change() {
	$("#vCode").attr("src", "./VerifyCodeServlet?" + new Date().getTime());
}