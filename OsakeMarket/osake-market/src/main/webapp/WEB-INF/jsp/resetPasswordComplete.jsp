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
<div class="reset-password-complete">
<h1>パスワード再設定成功</h1>
<p>
パスワードの変更が完了しました。<br>
引き続きサービスをご利用ください。
</p>
<button>
<a href="${pageContext.request.contextPath}/login">ログインする</a>
</button>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>