<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>U006</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header class="page-header wrapper">
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>
	<% if(errorMsg != null) { %>
	<p style="color: red; margin: 60px 100px;">
	<%= errorMsg %>
	</p>
	<% } %>
	<h1 class="login-title">ログイン</h1>
	<div class="login-form">
	<form action="login" method="post">
		<div class="login-input">
		<input type="text" name="mail" placeholder="メールアドレス" size="70">
		<input type="text" name="password" placeholder="パスワード" size="70">
		</div>
		<div class="login-submit">
		<input type="submit" value="ログイン">
		</div>
	</form>
	</div>
	<div class="regist-and-forget">
	<div class="new-regist">
	<p>アカウントをお持ちでない方<br>
	<a href="register/user">アカウントを新規作成する</a>
	</p>
	</div>
	<div class="forget-password">
	<p><a href="reset/password/mail">パスワードを忘れた方はこちら</a></p>
	</div>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</footer>
</body>
</html>