<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Product, java.util.List, java.text.NumberFormat" %>
<%
	List<Product> productList = (List<Product>)request.getAttribute("productList");
	NumberFormat nf = NumberFormat.getInstance();
	
	// リクエストパラメータを取得
    String selectedCategory = request.getParameter("categoryName");
    String inputProductName = request.getParameter("productName");
    String selectedSort = request.getParameter("sort");
    
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品一覧 - Osake Market</title>
		<meta name="description"
			content="Osake Marketの商品一覧です。ワイン、日本酒、ウイスキー、
					焼酎、ビール、リキュールなど豊富なお酒を掲載。
					カテゴリや商品名で検索して、お好みの商品をお選びいただけます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
			
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<%
			String cartMessage = (String) session.getAttribute("cartMessage");
			if (cartMessage != null) {
		%>
				<p style="color: green; font-weight: bold; margin-left: 5%;"><%= cartMessage %></p>
		<%
		    	session.removeAttribute("cartMessage"); // 一回だけ表示
			}
		%>
		<%
			Boolean addResult = (Boolean) session.getAttribute("addResult");
			Boolean deleteResult = (Boolean) session.getAttribute("deleteResult");
			String favoriteMessage = (String) session.getAttribute("favoriteMessage");
			if ((addResult != null && addResult == true && favoriteMessage != null) || 
				(deleteResult != null && deleteResult == true && favoriteMessage != null)) {
		%>
				<p style="color: green; font-weight: bold;  margin-left: 5%;"><%= favoriteMessage %></p>
		<%
		    	session.removeAttribute("favoriteMessage"); // 一回だけ表示
		    	session.removeAttribute("addResult"); // 一回だけ表示
		    	session.removeAttribute("deleteResult"); // 一回だけ表示
			}
		%>
		<div class="product-list-filter-container">
			<form action="${pageContext.request.contextPath}/product/list" method="get">
				<select name="categoryName">
					<option value="" <%= (selectedCategory == null || selectedCategory.isEmpty()) ? "selected" : "" %>>すべてのカテゴリ</option>
					<option value="wine" <%= "wine".equals(selectedCategory) ? "selected" : "" %>>ワイン</option>
					<option value="sparklingWine" <%= "sparklingWine".equals(selectedCategory) ? "selected" : "" %>>スパークリングワイン</option>
					<option value="whisky" <%= "whisky".equals(selectedCategory) ? "selected" : "" %>>ウイスキー</option>
					<option value="brandy" <%= "brandy".equals(selectedCategory) ? "selected" : "" %>>ブランデー</option>
					<option value="shochu" <%= "shochu".equals(selectedCategory) ? "selected" : "" %>>焼酎</option>
					<option value="japaneseSake" <%= "japaneseSake".equals(selectedCategory) ? "selected" : "" %>>日本酒</option>
					<option value="liqueur" <%= "liqueur".equals(selectedCategory) ? "selected" : "" %>>リキュール</option>
					<option value="beer" <%= "beer".equals(selectedCategory) ? "selected" : "" %>>ビール</option>
					<option value="food" <%= "food".equals(selectedCategory) ? "selected" : "" %>>おつまみ</option>
				</select>
				<input type="text" name="productName" size="35" placeholder="商品名で検索" value="<%= (inputProductName != null) ? inputProductName : "" %>">
				<select name="sort">
					<option value="new" <%= "new".equals(selectedSort) ? "selected" : "" %>>追加の新しい順</option>
					<option value="old" <%= "old".equals(selectedSort) ? "selected" : "" %>>追加の古い順</option>
					<option value="price-desc" <%= "price-desc".equals(selectedSort) ? "selected" : "" %>>価格が高い順</option>
					<option value="price-asc" <%= "price-asc".equals(selectedSort) ? "selected" : "" %>>価格が安い順</option>
				</select>
				<input type="submit" value="検索" class="product-list-filter-form-submit black-btn">
			</form>
		</div>
		<% if(productList == null || productList.isEmpty()) { %>
				<p>該当する商品が見つかりませんでした。</p>
		<% } else { %>
			<div class="product-list-container">
				<% for(Product product : productList) { %>
					<div class="product-list-wrapper">
						<a href="${pageContext.request.contextPath}/product/detail?productId=<%= product.getProductId() %>">
							<img src="${pageContext.request.contextPath}/images/<%= product.getImageFile() %>" class="product-image">
						</a>
						<div class="product-list-info">
								<%= product.getProductName() %><br>
								￥<%= nf.format(product.getPrice()) %>
						</div>
						<div class="heart-and-button-wrapper">	
							<% if(product.isFavoriteFlag()) { %>
								<form action="${pageContext.request.contextPath}/favorite/delete" method="post">
									<input type="hidden" name="productId" value="<%= product.getProductId() %>">
									<input type="hidden" name="fromPage" value="list">
									<button type="submit" class="heart-btn">
										<span class="product-list-heart-colored">❤</span>
									</button>
								</form>
							<% } else { %>
							 	<form action="${pageContext.request.contextPath}/favorite/add" method="post">
							 		<input type="hidden" name="productId" value="<%= product.getProductId() %>">
							 		<input type="hidden" name="fromPage" value="list">
									<button type="submit" class="heart-btn">
										<span class="product-list-heart">♡</span>
									</button>
								</form>
							<% } %>
							<form action="${pageContext.request.contextPath}/cart/add" method="post">
								<input type="hidden" name="productId" value="<%= product.getProductId() %>">
								<input type="hidden" name="fromPage" value="list">
								<button class="cart-add-btn black-btn">
									カートに追加する
								</button>
							</form>
						</div>
					</div>
				<% } %>
			</div>
		<% } %>
			
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>