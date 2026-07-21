<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "model.entity.User, java.time.format.DateTimeFormatter,
					model.entity.Product, model.entity.CartItem, java.text.NumberFormat,
					java.util.List" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String paymentMethod = (String)request.getAttribute("paymentMethod");
	List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
	NumberFormat nf = NumberFormat.getInstance();
	String cardToken = (String)request.getAttribute("cardToken");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文内容確認 - Osake Market</title>
		<meta name="description"
			content="Osake Marketの注文内容確認ページです。
					お客様情報やお支払い方法、ご注文内容、
					合計金額を確認し、注文を確定できます。">
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
		
		<h2>注文内容確認</h2>
		<div class="order-confirm-wrapper">
			<h3>お客様情報</h3>
			<%-- <p>
				お名前：<%= loginUser.getSei() %>&nbsp;<%= loginUser.getMei() %><br>
				生年月日：<%= loginUser.getBirthday().format(fmt) %><br>
				性別：
				<%
					switch(loginUser.getGender()) {
						case MALE:
							out.print("男性");
							break;
						case FEMALE:
							out.print("女性");
							break;
						case OTHER:
							out.print("その他");
							break;
					}
				%>
				<br>
				郵便番号：
				<%= loginUser.getPostalCode().substring(0, 3) %>
				-
				<%= loginUser.getPostalCode().substring(3) %><br>
				都道府県：<%= loginUser.getPrefecture() %><br>
				住所：<%= loginUser.getAddress() %><br>
				建物名・部屋番号：<%= loginUser.getBuilding()%><br>
				電話番号：
				<%= loginUser.getPhoneNumber().substring(0, 3) %>-
				<%= loginUser.getPhoneNumber().substring(3, 7) %>-
				<%= loginUser.getPhoneNumber().substring(7) %>
				<br>
				メールアドレス：<%= loginUser.getMail() %>
			</p>
			
			<h3>支払情報</h3>
			支払方法：
			<%
				switch(paymentMethod) {
					case "CREDIT_CARD":
						out.print("クレジットカード");
						break;
					case "BANK":
						out.print("銀行振り込み");
						break;
				}
			%>
			
			<h3>注文内容</h3>
			<% int subTotal = 0; %>
			<% int total = 0; %>
			<% for(CartItem item : cart) { %>
			<%= item.getProduct().getProductName() %>&nbsp;×&nbsp;
			<%= item.getQuantity() %>&nbsp;&nbsp;＝&nbsp;
			<% subTotal = item.getProduct().getPrice() * item.getQuantity(); %>
			<% total += subTotal; %>
			￥<%= nf.format(subTotal) %><br>
			<% } %>
			送料：￥500<br>
			合計：￥<%= nf.format(total + 500) %> --%>
			<table class="order-confirm-table info-table">
		<tr>
			<th>お名前</th>
			<td><%= loginUser.getSei() %> <%= loginUser.getMei() %></td>
		</tr>
		<tr>
			<th>生年月日</th>
			<td><%= loginUser.getBirthday().format(fmt) %></td>
		</tr>
		<tr>
			<th>性別</th>
			<td>
				<%
					switch(loginUser.getGender()) {
						case MALE: out.print("男性"); break;
						case FEMALE: out.print("女性"); break;
						case OTHER: out.print("その他"); break;
					}
				%>
			</td>
		</tr>
		<tr>
			<th>郵便番号</th>
			<td>
				<%= loginUser.getPostalCode().substring(0,3) %>-
				<%= loginUser.getPostalCode().substring(3) %>
			</td>
		</tr>
		<tr>
			<th>都道府県</th>
			<td><%= loginUser.getPrefecture() %></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><%= loginUser.getAddress() %></td>
		</tr>
		<tr>
			<th>建物名・部屋番号</th>
			<td><%= loginUser.getBuilding() %></td>
		</tr>
		<tr>
			<th>携帯電話番号</th>
			<td>
				<%= loginUser.getPhoneNumber().substring(0,3) %>-
				<%= loginUser.getPhoneNumber().substring(3,7) %>-
				<%= loginUser.getPhoneNumber().substring(7) %>
			</td>
		</tr>
		<tr>
			<th>メールアドレス</th>
			<td><%= loginUser.getMail() %></td>
		</tr>
	</table>

	<h3>支払情報</h3>
	<table class="order-confirm-table info-table">
		<tr>
			<th>支払方法</th>
			<td>
				<%
					switch(paymentMethod) {
						case "CREDIT_CARD":
							out.print("クレジットカード");
							break;
						case "BANK":
							out.print("銀行振り込み");
							break;
					}
				%>
			</td>
		</tr>
	</table>

	<h3>注文内容</h3>
	<table class="order-confirm-table item-table">
		<tr>
			<th>商品名</th>
			<th>数量</th>
			<th>金額</th>
		</tr>

		<%
			int subTotal = 0;
			int total = 0;
			for(CartItem item : cart){
				subTotal = item.getProduct().getPrice() * item.getQuantity();
				total += subTotal;
		%>
		<tr>
			<td><%= item.getProduct().getProductName() %></td>
			<td><%= item.getQuantity() %></td>
			<td>￥<%= nf.format(subTotal) %></td>
		</tr>
		<% } %>

		<tr>
			<th colspan="2">送料</th>
			<td>￥500</td>
		</tr>
		<tr>
			<th colspan="2">合計</th>
			<td>￥<%= nf.format(total + 500) %></td>
		</tr>
	</table>
		</div>
		<br><br>
		
		<div class="order-confirm-submit">
			<form action="${pageContext.request.contextPath}/order/input" method="post">
				<input type="hidden" name="paymentMethod" value="<%= paymentMethod %>">
				<button class="modify-btn white-btn">
					修正する
				</button>
			</form>
			
			<form action="${pageContext.request.contextPath}/order/done" method="post">
				<input type="hidden" name="paymentMethod" value="<%= paymentMethod %>">
				<input type="hidden" name="cardToken" value="<%= cardToken %>">
				<button type="submit" class="confirm-btn red-btn">
					注文を確定する
				</button>
			</form>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>