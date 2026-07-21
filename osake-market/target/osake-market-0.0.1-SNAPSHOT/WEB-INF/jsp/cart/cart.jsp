<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.entity.Product, model.entity.CartItem, java.text.NumberFormat" %>
<%
	List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
	NumberFormat nf = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>カート - Osake Market</title>
		<meta name="description"
				content="Osake Marketのショッピングカートです。選択した商品の内容や数量、
						合計金額を確認し、購入手続きへ進めます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<h2>カート</h2>
		<% if(cart == null || cart.size() == 0) { %>
			<p style="text-align: center; color: red;">カートに商品はありません</p>
		<% } else { %>
		<table border="1" class="cart-table">
			<thead>
				<tr>
					<th>商品カテゴリ</th>
					<th>商品名</th>
					<th>価格(税込)</th>
					<th>数量</th>
					<th>小計</th>
					<th>削除</th>
				</tr>
			</thead>
			<tbody>
				<% int subTotal = 0; %>
				<% for(CartItem item : cart) { %>
				<tr>
					<td data-label="商品カテゴリ"><%= item.getProduct().getCategoryName() %></td>
					<td data-label="商品名"><%= item.getProduct().getProductName() %></td>
					<td data-label="価格(税込)"><%= item.getProduct().getPrice() %></td>
					<td data-label="数量" class="quantity-cell">
						<form action="${pageContext.request.contextPath}/cart/update/quantity" method="post">
							<input type="hidden" name="productId" value="<%= item.getProduct().getProductId() %>">
							<input type="hidden" name="delta" value="-1">
							<button type="submit" class="qty-btn">-</button>
						</form>
							<span class="qty"><%= item.getQuantity() %></span>
						<form action="${pageContext.request.contextPath}/cart/update/quantity" method="post">
							<input type="hidden" name="productId" value="<%= item.getProduct().getProductId() %>">
							<input type="hidden" name="delta" value="1">
							<button type="submit" class="qty-btn">+</button>
						</form>
					</td>
					<td data-label="小計"><%= nf.format(item.getProduct().getPrice() * item.getQuantity()) %></td>
					<td data-label="削除" class="cart-item-delete">
						<form action="${pageContext.request.contextPath}/cart/remove" method="post">
							<input type="hidden" name="productId" value="<%= item.getProduct().getProductId() %>">
							<button type="submit">
								<img src="${pageContext.request.contextPath}/images/trash.png" class="trash">
							</button>
						</form>
					</td>
				</tr>
				<% subTotal += item.getProduct().getPrice() * item.getQuantity(); %>
				<% } %>
			</tbody>
		</table>
		<p class="cart-price">
			小計：￥<%= nf.format(subTotal) %><br>
			送料：￥500<br>
			合計：￥<%= nf.format(subTotal + 500) %>
		</p>
		<a href="${pageContext.request.contextPath}/order/input" class="cart-button-link">
			<button class="cart-button red-btn">
				購入手続きに進む
			</button>
		</a>
		<% } %>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>