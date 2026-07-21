<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.List, model.entity.Product, model.entity.CartItem, java.text.NumberFormat" %>
<%
	List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
	NumberFormat nf = NumberFormat.getInstance();
	String paymentMethod = (String)request.getAttribute("paymentMethod");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文情報入力 - Osake Market</title>
		<meta name="description"
				content="Osake Marketの注文情報入力ページです。
						お支払い方法を選択し、ご注文内容や合計金額を確認して
						購入手続きを進められます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
		
		<!-- JavaScript -->
		<script>
			document.addEventListener("DOMContentLoaded", () => {
			
			    const paymentMethod = document.getElementById("paymentMethod");
			    const creditCardArea = document.getElementById("creditCardArea");
			    const bankArea = document.getElementById("bankArea");
			
			    function updatePaymentArea() {
			
			        creditCardArea.style.display = "none";
			        bankArea.style.display = "none";
			
			        if (paymentMethod.value === "CREDIT_CARD") {
			            creditCardArea.style.display = "block";
			        }
			
			        if (paymentMethod.value === "BANK") {
			            bankArea.style.display = "block";
			        }
			    }
			
			    paymentMethod.addEventListener("change", updatePaymentArea);
			
			    // 画面表示時にも実行
			    updatePaymentArea();
			});
		</script>
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<h2>注文情報入力</h2>
		<div class="order-input-wrapper">
			<h3 class="paymentMethodInputTitle">お支払情報入力</h2>
			<form action="${pageContext.request.contextPath}/order/confirm" method="post" class="paymentMethodInput">
				支払方法<br>
				<select id="paymentMethod" name="paymentMethod" class="paymentMethod">
					<option value="">選択してください</option>
					<option value="CREDIT_CARD" <%= "CREDIT_CARD".equals(paymentMethod) ? "selected" : "" %>>クレジットカード</option>
					<option value="BANK" <%= "BANK".equals(paymentMethod) ? "selected" : "" %>>銀行振り込み</option>
				</select>
				
				<br><br>
				
				<div id="creditCardArea" style="display:none;">
				<script
					type="text/javascript"
					src="https://checkout.pay.jp/"
					class="payjp-button"
					data-key="pk_test_acfdce0fc7cbbd4f792b7bd4"
					data-partial="true"
					data-token-name="card"   
				></script>
				</div>
				
				<div id="bankArea" style="display:none;">
					振込先口座<br>
					三菱UFJ銀行 渋谷支店 普通 1234567
				</div>
				
				<br>
				<h3 class="order-content">注文内容</h2>
				<% int subTotal = 0; %>
				<% int total = 0; %>
				<% for(CartItem item : cart) { %>
				<%= item.getProduct().getProductName() %>&nbsp;×&nbsp;
				<%= item.getQuantity() %>&nbsp;&nbsp;＝&nbsp;
				<% subTotal = item.getProduct().getPrice() * item.getQuantity(); %>
				<% total += subTotal; %>
				￥<%= nf.format(subTotal) %><br>
				<% } %><hr>
				送料：￥500<br>
				合計：￥<%= nf.format(total + 500) %>
				<br><br>
				
				<button type="submit" class="order-input-submit red-btn">確認画面に進む</button>
			</form>
			<a href="${pageContext.request.contextPath}/cart">
				<button type="submit" class="order-input-return white-btn">カートに戻る</button>
			</a>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>