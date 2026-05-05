<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="page-header wrapper">
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
</header>
<div class="top-img">
<div class="wrapper">
<p>
	日本の銘酒をあなたに<br>
	厳選されたクラフト酒をお届けします。
</p>
<a href="${pageContext.request.contextPath}/product/list">
<button>
	商品を探す
</button>
</a>
</div>
</div>
<h2 style="text-align: center; font-weight: normal; margin: 50px 0;">カテゴリから選ぶ</h2>
<div class="home-category-wrapper">
<a href="${pageContext.request.contextPath}/product/list?category=wine" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/wine.png" class="wine">
<div class="home-category-name-wrapper">
<span class="category-alpha">WINE</span>
<span class="category-ja">ワイン</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=sparklingWine" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/sparkling_wine.png" class="sparklingWine">
<div class="home-category-name-wrapper">
<span class="category-alpha">SPARKLING WINE</span>
<span class="category-ja">スパークリングワイン</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=whisky" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/whisky.png" class="whisky">
<div class="home-category-name-wrapper">
<span class="category-alpha">WHISKY</span>
<span class="category-ja">ウイスキー</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=brandy" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/brandy.png" class="brandy">
<div class="home-category-name-wrapper">
<span class="category-alpha">BRANDY</span>
<span class="category-ja">ブランデー</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=syouchu" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/syouchu.png" class="syouchu">
<div class="home-category-name-wrapper">
<span class="category-alpha">SHOCHU</span>
<span class="category-ja">焼酎</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=nihonsyu" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/nihonsyu.png" class="nihonsyu">
<div class="home-category-name-wrapper">
<span class="category-alpha">JAPANESE SAKE</span>
<span class="category-ja">日本酒</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=liqueur" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/liqueur.png" class="liqueur">
<div class="home-category-name-wrapper">
<span class="category-alpha">LIQUEUR</span>
<span class="category-ja">リキュール</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=beer" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/beer.png" class="beer">
<div class="home-category-name-wrapper">
<span class="category-alpha">BEER</span>
<span class="category-ja">ビール</span>
</div>
</div>
</a>
<a href="${pageContext.request.contextPath}/product/list?category=otsumami" class="home-category-link">
<div class="home-category-inner-wrapper">
<img src="${pageContext.request.contextPath}/images/otsumami.png" class="otsumami">
<div class="home-category-name-wrapper">
<span class="category-alpha">FOOD</span>
<span class="category-ja">おつまみ</span>
</div>
</div>
</a>
</div>

<footer>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>