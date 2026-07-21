<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Product, java.util.List, java.text.NumberFormat" %>
<%
	List<Product> favoriteList = (List<Product>)request.getAttribute("favoriteList");
	NumberFormat nf = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入り一覧 - Osake Market</title>
		<meta name="description"
				content="Osake Marketのお気に入り一覧ページです。
						登録したお気に入り商品をまとめて確認し、
						商品詳細の閲覧やお気に入りの解除ができます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/favorite.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<%
			Boolean deleteResult = (Boolean) session.getAttribute("deleteResult");
			if(deleteResult != null && deleteResult) { 
		%>
			<p style="color: green; font-weight: bold;  margin-left: 5%;">選択した商品を削除しました</p>
		<% 	
			} else if(deleteResult != null && !deleteResult) { %>
			<p style="color: green; font-weight: bold;  margin-left: 5%;">お気に入り削除に失敗しました</p>
		<%	
				session.removeAttribute("deleteResult"); // 一回だけ表示
			} 
		%>
		
		<h2>お気に入り一覧</h2>
		<% if(favoriteList == null || favoriteList.isEmpty()) { %>
			<p style="text-align: center; color: red;">該当する商品が見つかりませんでした。</p>
		<% } else { %>
			<div class="favorite-list-container">
				<% for(Product product : favoriteList) { %>
					<div class="favorite-list-wrapper">
						<a href="${pageContext.request.contextPath}/product/detail?productId=<%= product.getProductId() %>">
							<img src="${pageContext.request.contextPath}/images/<%= product.getImageFile() %>" class="product-image">
						</a>
						<div class="favorite-list-info">
							<%= product.getProductName() %><br>
							￥<%= nf.format(product.getPrice()) %>
						</div>
						<div class="favorite-delete-form">
							<form action="${pageContext.request.contextPath}/favorite/delete" method="post">
								<input type="hidden" name="productId" value="<%= product.getProductId() %>">
								<input type="hidden" name="fromPage" value="favoriteList">
								<button class="black-btn">
									お気に入り解除
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