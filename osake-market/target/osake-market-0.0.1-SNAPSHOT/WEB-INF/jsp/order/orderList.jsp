<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Order, java.util.List, java.text.NumberFormat, java.time.format.DateTimeFormatter" %>
<%
	List<Order> orderList = (List<Order>)request.getAttribute("orderList");
	NumberFormat nf = NumberFormat.getInstance();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	Integer currentPage = (Integer)request.getAttribute("currentPage");
	Integer totalPage = (Integer)request.getAttribute("totalPage");
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>注文履歴一覧 - Osake Market</title>
	<meta name="description"
	      content="Osake Marketの注文履歴一覧ページです。
	      			過去のご注文日時や合計金額を確認し、各注文の詳細をご覧いただけます。">
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
		
		<h2>注文履歴</h1>
		<% if(orderList == null || orderList.size() == 0) { %>
			<p>注文履歴はありません</p>
		<% } else { %>
			<% for(Order order : orderList) { %>
				<div class="order-wrapper">
					<div>
						<p>
							ご注文日：
							<span><%= order.getCreateDate().format(formatter) %></span>
						</p>
						<p>
							合計金額：
							<span>￥<%= nf.format(order.getTotalAmount()) %></span>
						</p>
						<% if(order.getCancel() == 1) { %>
							<p>
								<span style="color:red">キャンセル済み</span>
							</p>
						<% } %>
					</div>
					<form action="${pageContext.request.contextPath}/order/detail" method="get">
						<input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
						<button type="submit" class="black-btn">
							詳細を見る
						</button>
					</form>
				</div>
			<% } %>
		<% } %>
		<div class="pagination">
			<% for(Integer i = 1; i <= totalPage; i++) { %>
		
				<% if(i == currentPage) { %>
					<strong><%= i %></strong>
				<% } else { %>
					<a href="${pageContext.request.contextPath}/order/list?page=<%= i %>">
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