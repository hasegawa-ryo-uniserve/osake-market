<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User)session.getAttribute("loginUser");
%>
<h1>
<a href="${pageContext.request.contextPath}/index.jsp">Osake Market</a>
</h1>
	<nav>
		<ul class="main-nav">
			<div class="nav-group left">
				<li>商品カテゴリ</li>
				<a href="${pageContext.request.contextPath}/product/list"><li class="productList">商品一覧</li></a>
			</div>

			<div class="nav-group right">
				<li class="notice">
					<img src="${pageContext.request.contextPath}/images/bell.jpg" class="bell">
					<span>通知</span>
				</li>
				<li class="favorite">
					<img src="${pageContext.request.contextPath}/images/heart.svg" class="heart">
					<span>お気に入り</span>
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
					<a href="mypage" class="user">
						<img src="${pageContext.request.contextPath}/images/user.svg" class="userImg">
						<span>マイページ</span>
					</a>
					<% } %>
				</li>
			</div>
		</ul>
	</nav>