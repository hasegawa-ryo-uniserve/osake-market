<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Notification, java.time.format.DateTimeFormatter" %>
<%
	Notification notification = (Notification)request.getAttribute("notification");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>通知詳細 - Osake Market</title>
		<meta name="description"
	      content="Osake Marketの通知詳細ページです。お知らせのタイトル、
	      			配信日時、詳細内容をご確認いただけます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notification.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<% if(notification == null) { %>
			<p>通知はありません</p>
		<% } else { %>
			<h2><%= notification.getTitle() %></h2>
			<p class="notification-date"><%= notification.getCreateDate().format(formatter) %></p>
			<p class="notification-content"><%= notification.getMessage() %></p>
			
		<% } %>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>