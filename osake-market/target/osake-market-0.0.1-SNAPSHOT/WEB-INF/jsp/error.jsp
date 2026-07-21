<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
	<meta charset="UTF-8">
	<head>
		<meta charset="UTF-8">
		<title>エラー - Osake Market</title>
		<meta name="description"
		      content="ページの表示中にエラーが発生しました。お手数ですが、
		      			時間をおいて再度お試しいただくか、トップページからご利用ください。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/others.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<main class="error-wrapper">
			<h2>エラーが発生しました</h2>
			<p class="error-message"><%= errorMsg %></p>
			<br>
			<p>
				ご不便をおかけし申し訳ございません。<br>
				時間をおいて再度お試しいただくか、トップページからご利用ください。
			</p>
			<p class="back-link">
				<a href="${pageContext.request.contextPath}/">トップページへ戻る</a>
			</p>
		</main>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>