<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Order, java.util.List, model.entity.OrderItem, java.time.format.DateTimeFormatter" %>
<%
	Order order = (Order)request.getAttribute("order");
	List<OrderItem> orderItemList = order.getOrderItemList();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	String msg = (String)session.getAttribute("msg");
	session.removeAttribute("msg");	
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>注文履歴詳細 - Osake Market</title>
	<meta name="description"
	      content="Osake Marketの注文履歴詳細ページです。ご注文商品の詳細や注文日時、
	      			お支払い方法、注文ステータスをご確認いただけます。">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	
	<!-- JavaScript -->
	<script>
		function cancel() {
			return window.confirm("注文の取り消しを実行してよろしいですか？");
		}
	</script>
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<% if(msg != null) { %>
			<script>
				alert("<%= msg %>");
			</script>
		<% } %>
		<% for(OrderItem orderItem : orderItemList) { %>
		<div class="order-detail-wrapper">
			<img src="${pageContext.request.contextPath}/images/<%= orderItem.getProduct().getImageFile() %>" class="product-detail-image">
			<p>
				ご注文日時：<%= orderItem.getCreateDate().format(formatter) %><br>
				商品名：<%= orderItem.getProduct().getProductName() %><br>
				商品番号：<%= orderItem.getProduct().getProductId() %><br>
				商品代金：<%= orderItem.getProduct().getPrice() %><br>
				支払方法：
							<%
								switch(order.getPaymentMethod()) {
									case CREDIT_CARD:
										out.print("クレジットカード");
										break;
									case BANK:
										out.print("銀行振り込み");
										break;
								}
							%><br>
				注文ステータス：
							<%
								switch(order.getStatus()) {
									case 0:
										out.print("決済前");
										break;
									case 1:
										out.print("決済完了済み");
										break;
									case 2:
										out.print("発送依頼済み　※注文のキャンセルは不可です");
										break;
									case 3:
										out.print("発送済み　※注文のキャンセルは不可です");
										break;
								}
							%><br>
				<% if(order.getCancel() == 1) { %>
				<span style="color:red">キャンセル済み</span>
				<% } %>
			</p>
		</div>
		<hr>
		<% } %>
		<div class="return-and-cancel">
			<a href="${pageContext.request.contextPath}/order/list">
			<button class="return white-btn">戻る</button>
			</a>
			
			<% if(order.getCancel() == 0) { %>
				<form action="${pageContext.request.contextPath}/order/cancel" method="post">
					<input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
					<button type="submit" onclick="return cancel()" class="order-cancel red-btn">
						注文を取り消す
					</button>
				</form>
			<% } %>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>