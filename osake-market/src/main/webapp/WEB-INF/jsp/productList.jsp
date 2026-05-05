<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product, java.util.List, java.text.NumberFormat" %>
<%
	List<Product> productList = (List<Product>)request.getAttribute("productList");
	NumberFormat nf = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header class="page-header wrapper">
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<div class="product-list-container">
<% for(Product product : productList) { %>
<div class="product-list-wrapper">
<img src="${pageContext.request.contextPath}/images/<%= product.getImageFile() %>" class="product-image">
<div class="product-list-info">
<%= product.getProductName() %><br>
￥<%= nf.format(product.getPrice()) %>
</div>
<div class="heart-and-button-wrapper">
<img src="${pageContext.request.contextPath}/images/heart.svg" class="product-list-heart-image">
<button>
カートに追加する
</button>
</div>
</div>
<% } %>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>