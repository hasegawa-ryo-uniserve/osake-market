<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Product" %>
<%
	List<Product> cart = (List<Product>)session.getAttribute("cart");
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
<p>カート画面です</p>
<% if(cart != null) { %>
<% for(Product product : cart) { %>
<%= product.getProductName() %>
<% } %>
<% } %>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>