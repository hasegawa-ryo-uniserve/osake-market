<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>パスワードリセット(新しいパスワードの入力) - Osake Market</title>
		<meta name="description"
					content="Osake Marketのパスワード再設定ページです。
							新しいパスワードを入力し、アカウントのパスワードを再設定できます。">
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
	
	<%
		String errorMsg = (String) request.getAttribute("errorMsg");
		if (errorMsg != null) {
	%>
			<p id="error-msg" style="color: green; font-weight: bold; margin-left: 5%;"><%= errorMsg %></p>
	<%
		}
	%>
	<div class="new-password">
		<h2>パスワードの再設定</h2>
		<p>新しいパスワードを入力してください</p>
		<div class="new-password-form">
			<form action="${pageContext.request.contextPath}/reset/password/update" method="post">
				<input type="text" name="newPassword" size="70" placeholder="新しいパスワード（半角英数字・記号のみ8~100文字）"><br>
				<input type="text" name="confirmPassword" size="70" placeholder="新しいパスワードの確認"><br>
				<input type="hidden" name="token" value="${token}">
				<div class="new-password-submit">
					<input type="submit" value="送信" class="black-btn">
				</div>
			</form>
		</div>
	</div>
	
	<footer>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</footer>
	</body>
</html>