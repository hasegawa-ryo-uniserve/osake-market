<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.entity.Notification, java.time.format.DateTimeFormatter" %>
<%
	List<Notification> notificationList = (List<Notification>)request.getAttribute("notificationList");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	Integer currentPage = (Integer)request.getAttribute("currentPage");
	Integer totalPage = (Integer)request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>通知一覧 - Osake Market</title>
		<meta name="description"
		      content="Osake Marketの通知一覧ページです。
		      			最新のお知らせやキャンペーン情報、
		      			重要なお知らせをご確認いただけます。">
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
		
		<h2>お知らせ</h2>
		<% if(notificationList == null || notificationList.size() == 0) { %>
			<p>通知はありません</p>
		<% } else { %>
			<% for(Notification notification : notificationList) { %>
				<div class="notification-wrapper">
					<div>
						<p>
							<a href="${pageContext.request.contextPath}/notification/detail?notificationId=<%= notification.getNotificationId() %>">
								<%= notification.getTitle() %>
							</a>
						</p>
						<p class="notification-list-date"><%= notification.getCreateDate().format(formatter) %></p>
					</div>
				</div>
			<% } %>
		<% } %>
		<div class="pagination">
			<% for(Integer i = 1; i <= totalPage; i++) { %>
		
				<% if(i == currentPage) { %>
					<strong><%= i %></strong>
				<% } else { %>
					<a href="${pageContext.request.contextPath}/notification/list?page=<%= i %>">
						<%= i %>
					</a>
				<% } %>
		
			<% } %>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>