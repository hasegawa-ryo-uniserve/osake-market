<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>パスワードリセット(メール送信済み) - Osake Market</title>
		<meta name="description"
				content="Osake Marketのパスワードリセットメール送信完了ページです。
						登録されたメールアドレス宛に、パスワード再設定用のメールを送信しました。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/resetPassword.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
	<div class="reset-password-after-mail">
		<h2>パスワードのリセット</h2>
		<p>
			パスワードリセットメールを送信しました。
		</p>
	</div>
	<footer>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</footer>
	</body>
</html>