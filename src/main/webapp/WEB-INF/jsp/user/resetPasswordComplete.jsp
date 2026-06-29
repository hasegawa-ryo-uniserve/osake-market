<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>パスワードリセット(パスワードの再設定完了) - Osake Market</title>
	<meta name="description"
			content="Osake Marketのパスワード再設定完了ページです。
					パスワードの変更が完了しました。
					新しいパスワードでログインしてサービスをご利用いただけます。">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/resetPassword.css">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div class="reset-password-complete">
			<h1>パスワード再設定成功</h1>
			<p>
				パスワードの変更が完了しました。<br>
				引き続きサービスをご利用ください。
			</p>
			<a href="${pageContext.request.contextPath}/login">
				<button class="black-btn">
					ログインする
				</button>
			</a>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>