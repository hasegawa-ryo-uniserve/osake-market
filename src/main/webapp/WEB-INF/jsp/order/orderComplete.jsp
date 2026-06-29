<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.User" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文完了 - Osake Market</title>
		<meta name="description"
		      content="Osake Marketの注文完了ページです。
		      			ご注文ありがとうございます。注文確認メールを送信しましたので、
		      			ご注文内容をご確認ください。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div id="order-complete">
			<h2>
				ご注文ありがとうございました！
			</h2>
			<p>下記のメールアドレスに注文確認メールを送信しました。</p>
			<p><%= loginUser.getMail() %></p>
			<a href="${pageContext.request.contextPath}/">
				<button class="black-btn">
					ホームへ戻る
				</button>
			</a>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>