<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.User"%>
<%
	// セッションスコープに保存されたユーザー情報を取得
	User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Osake Market</title>
		<meta name="description"
			content="ワイン、スパークリングワイン、ウイスキー、ブランデー、
					焼酎、日本酒、リキュール、ビールなど豊富なお酒を販売。
					オンラインで簡単にご購入いただけます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div class="top-img">
			<div class="wrapper">
				<p>
					日本の銘酒をあなたに<br> 厳選されたクラフト酒をお届けします
				</p>
				<a href="${pageContext.request.contextPath}/product/list">
					<button class="red-btn">商品を探す</button>
				</a>
			</div>
		</div>
		<h2>カテゴリから選ぶ</h2>
		<div class="home-category-wrapper">
			<a href="${pageContext.request.contextPath}/product/list?categoryName=wine&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/wine.png"
						class="wine">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">WINE</span> <span class="category-ja">ワイン</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=sparklingWine&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img
						src="${pageContext.request.contextPath}/images/sparkling_wine.png"
						class="sparklingWine">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">SPARKLING WINE</span> <span
							class="category-ja">スパークリングワイン</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=whisky&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/whisky.png"
						class="whisky">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">WHISKY</span> <span
							class="category-ja">ウイスキー</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=brandy&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/brandy.png"
						class="brandy">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">BRANDY</span> <span
							class="category-ja">ブランデー</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=shochu&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/syouchu.png"
						class="shochu">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">SHOCHU</span> <span
							class="category-ja">焼酎</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=japaneseSake&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/nihonsyu.png"
						class="japaneseSake">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">JAPANESE SAKE</span> <span
							class="category-ja">日本酒</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=liqueur&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/liqueur.png"
						class="liqueur">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">LIQUEUR</span> <span
							class="category-ja">リキュール</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=beer&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/beer.png"
						class="beer">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">BEER</span> <span class="category-ja">ビール</span>
					</div>
				</div>
			</a>
			<a href="${pageContext.request.contextPath}/product/list?categoryName=food&productName=&sort=new"
				class="home-category-link">
				<div class="home-category-inner-wrapper">
					<img src="${pageContext.request.contextPath}/images/otsumami.png"
						class="otsumami">
					<div class="home-category-name-wrapper">
						<span class="category-alpha">FOOD</span> <span class="category-ja">おつまみ</span>
					</div>
				</div>
			</a>
		</div>
	
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>