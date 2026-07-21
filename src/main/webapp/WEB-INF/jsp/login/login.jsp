<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン - Osake Market</title>
		<meta name="description"
				content="Osake Marketのログインページです。
						ログインすると、お気に入り商品の登録やカートの利用、
						ご注文手続きなどの便利な機能をご利用いただけます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<% if(errorMsg != null) { %>
			<p id="error-msg" style="color: red; margin: 60px 100px;">
				<%= errorMsg %>
			</p>
		<% } %>
		<h2>ログイン</h2>
		<div class="login-form">
			<form action="login" method="post">
				<div class="login-input">
					<input type="text" name="mail" placeholder="メールアドレス" size="70">
					<input type="password" name="password" placeholder="パスワード" size="70">
				</div>
				<div class="login-submit">
					<input type="submit" value="ログイン" class="black-btn">
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