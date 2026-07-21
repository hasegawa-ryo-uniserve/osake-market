<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ - Osake Market</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">

</head>

<script>
	function withdraw() {
		return window.confirm("本当に退会しますか");
	}
</script>

<body>
<header class="page-header wrapper">
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<h1 class="page-title">マイページ</h1>
<div class="mypage-menu">
	<a href="${pageContext.request.contextPath}/update/user/form" class="menu-card">
		<span>
			<img src="${pageContext.request.contextPath}/images/user2.png" class="user2">
		</span>
		<div>会員情報を<br>管理する</div>
	</a>
	<a href="${pageContext.request.contextPath}/order/list" class="menu-card">
		<span>
			<img src="${pageContext.request.contextPath}/images/list.png" class="list">
		</span>
		<div>注文履歴</div>
	</a>
	<a href="${pageContext.request.contextPath}/favorite/list" class="menu-card">
		<span>
			<img src="${pageContext.request.contextPath}/images/heart.png" class="heart2">
		</span>
	<div>お気に入り</div>
	</a>
	<a href="${pageContext.request.contextPath}/notification/list" class="menu-card">
		<span>
			<img src="${pageContext.request.contextPath}/images/bell.png" class="bell2">
		</span>
	<div>通知</div>
	</a>
</div>
<div class="withdrow-and-logout-wrapper">
	<form action="${pageContext.request.contextPath}/withdraw" method="post">
		<button type="submit" class="red-btn" onclick="return withdraw()">
			退会する
		</button>
	</form>
	<form action="${pageContext.request.contextPath}/logout" method="get">
		<button type="submit" class="black-btn">
			ログアウトする
		</button>
	</form>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>