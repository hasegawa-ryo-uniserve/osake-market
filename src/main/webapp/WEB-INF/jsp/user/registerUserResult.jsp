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
		<title>新規会員登録(結果) - Osake Market</title>
		<meta name="description"
				content="Osake Marketの新規会員登録結果ページです。
						会員登録の完了状況をご確認いただけます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerUser.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div class="register-user-result">
			<% if(loginUser != null) { %> 
				<h2>会員登録ありがとうございます</h2>
				<p>
					ご入力いただいた内容で、会員登録が完了しました。
				</p>
				<a href="${pageContext.request.contextPath}/">
					<button class="black-btn">
						ホームに戻る
					</button>
				</a>
			<% } else { %>
				<h2>会員登録に失敗しました</h2>
				<p>
					申し訳ありません。会員登録中に問題が発生しました。<br>
					下のボタンからもう一度お試しください。
				</p>
				<a href="${pageContext.request.contextPath}/register/user">
					<button class="black-btn">
						新規会員登録
					</button>
				</a>
			<% } %>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>