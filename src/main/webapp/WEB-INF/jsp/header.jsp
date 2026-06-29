<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.User" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>
<h1>
<a href="${pageContext.request.contextPath}/">Osake Market</a>
</h1>
  	<input type="checkbox" id="menu-toggle" hidden>
  
	  <label class="menu-icon" for="menu-toggle">
	    <span></span>
	    <span></span>
	    <span></span>
	  </label>
  
  		<div class="overlay"></div>
	<nav  class="menu">
		<ul class="main-nav">
				<%-- <li class="nav-group left">
					<div class="category-menu">
						<span class="category-title">商品カテゴリ</span>
						<span class="triangle">▼</span>
					<ul class="category-dropdown">
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=wine&productName=&sort=new">ワイン</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=sparklingWine&productName=&sort=new">スパークリングワイン</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=whisky&productName=&sort=new">ウイスキー</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=brandy&productName=&sort=new">ブランデー</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=shochu&productName=&sort=new">焼酎</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=japaneseSake&productName=&sort=new">日本酒</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=liqueur&productName=&sort=new">リキュール</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=beer&productName=&sort=new">ビール</a></li>
						<li><a href="${pageContext.request.contextPath}/product/list?categoryName=food&productName=&sort=new">おつまみ</a></li>
					</ul>
					</div>
				<a href="${pageContext.request.contextPath}/product/list">商品一覧</a>
				</li> --%>
				
				<li class="nav-group left">
				    <input type="checkbox" id="category-toggle" hidden>
				    
				    <label class="category-menu" for="category-toggle">
				        <span class="category-title">商品カテゴリ</span>
				        <span class="triangle">▼</span>
				    </label>
				    
				    <ul class="category-dropdown">
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=wine&productName=&sort=new">ワイン</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=sparklingWine&productName=&sort=new">スパークリングワイン</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=whisky&productName=&sort=new">ウイスキー</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=brandy&productName=&sort=new">ブランデー</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=shochu&productName=&sort=new">焼酎</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=japaneseSake&productName=&sort=new">日本酒</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=liqueur&productName=&sort=new">リキュール</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=beer&productName=&sort=new">ビール</a></li>
				        <li><a href="${pageContext.request.contextPath}/product/list?categoryName=food&productName=&sort=new">おつまみ</a></li>
			        </ul>
				    
				    <span class="nav-product-list">
				    <a href="${pageContext.request.contextPath}/product/list">
				    商品一覧
				    </a>
				    </span>
				</li>
				
			<li class="nav-group right">
			<ul class="icon-nav">
				<li class="notification">
					<a href="${pageContext.request.contextPath}/notification/list" class="notification">
						<img src="${pageContext.request.contextPath}/images/bell.jpg" class="bell">
						<span>通知</span>
					</a>
				</li>
				<li class="favorite">
					<a href="${pageContext.request.contextPath}/favorite/list" class="favorite">
						<img src="${pageContext.request.contextPath}/images/heart.svg" class="heart">
						<span>お気に入り</span>
					</a>
				</li>
				<li class="cart">
					<a href="${pageContext.request.contextPath}/cart" class="cart">
						<img src="${pageContext.request.contextPath}/images/cart.png" class="cartImg">
						<span>カート</span>
					</a>
				</li>
				<li class="user">
					<% if (loginUser == null) { %>
					<a href="${pageContext.request.contextPath}/login" class="user">
						<img src="${pageContext.request.contextPath}/images/user.svg" class="userImg">
						<span>ログイン</span>
					</a>
					<% } else { %>
					<a href="${pageContext.request.contextPath}/mypage" class="user">
						<img src="${pageContext.request.contextPath}/images/user.svg" class="userImg">
						<span>マイページ</span>
					</a>
					<% } %>
				</li>
			</ul>
			</li>
		</ul>
	</nav>