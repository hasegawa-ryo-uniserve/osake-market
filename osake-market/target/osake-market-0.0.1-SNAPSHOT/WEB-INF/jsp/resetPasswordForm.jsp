<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header class="page-header wrapper">
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<div class="new-password">
<h1>パスワードの再設定</h1>
<p>新しいパスワードを入力してください</p>
<div class="new-password-form">
<form action="${pageContext.request.contextPath}/reset/password/update" method="post">
<input type="text" name="newPassword" size="70" placeholder="新しいパスワード（半角英数字・記号のみ8~100文字）"><br>
<input type="text" name="confirmPassword" size="70" placeholder="新しいパスワードの確認"><br>
<input type="hidden" name="token" value="${token}">
<div class="new-password-submit">
<input type="submit" value="送信">
</div>
</form>
</div>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>