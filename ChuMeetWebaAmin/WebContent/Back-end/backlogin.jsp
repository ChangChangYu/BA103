<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8">

<title>ChuMeet_Back</title>

<!-- Google Fonts -->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/animate.css">
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/HTML/BackEnd/assets/css/style.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>

<body>



	<div class="container">
		<div class="top">
			<h1 id="title" class="hidden">
				<span id="logo"></span>
			</h1>
		</div>
		<from class="form-horizontal" method="post"
			ACTION="<%=request.getContextPath()%>/back-end/admin/adminLogin.do">
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>後端登入</h2>
			</div>
			<div class="form-group">
				<label for="username">帳號</label> <br />

				<placeholder ="帳號" name="adminName" value="${param.adminName}">
				<i class="fa fa-user"></i>
			</div>
			<br />
			<div class="form-group help">
				<label for="password">密碼</label>

				<placeholder ="密碼" name="adminPw" value="${param.adminPW}">
				<i class="fa fa-fa-lock"></i> <a href="admin_admin.html" class=""></a>
			</div>
			<div>

				<br /> <input type="password" id="password"> <br />
				<button type="submit">確認</button>


				<br />
			</div>
		</div>
		</from>

	</div>
</body>

<script>
	$(document).ready(function() {
		$('#logo').addClass('animated fadeInDown');
		$("input:text:visible:first").focus();
	});
	$('#username').focus(function() {
		$('label[for="username"]').addClass('selected');
	});
	$('#username').blur(function() {
		$('label[for="username"]').removeClass('selected');
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>