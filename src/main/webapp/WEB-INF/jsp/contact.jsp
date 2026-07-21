<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お問い合わせ - Osake Market</title>
		<meta name="description"
		      content="Osake Marketのお問い合わせページです。
		      			商品や注文、配送などに関するご質問やご意見をフォームから送信できます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/others.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<% if(msg != null) { %>
			<p id="send-success-msg" style="color: green; font-weight: bold; margin-left: 5%;"><%= msg %></p>
		<% } %>
			<h2 class="page-title">お問い合わせ</h2>
			<form action="${pageContext.request.contextPath}/contact" method="post">
				<div class="form-group">
					<span>名前</span><br>
					<input type="text" name="name" placeholder="山田 太郎" required>
				</div>
				<div class="form-group">
					<span>メールアドレス</span><br>
					<input type="email" name="mail" placeholder="メールアドレスを入力して下さい" required>
				</div>
				<div class="form-group">
					<span>お問い合わせ種別</span><br>
					<select name="category" required>
						<option value="">選択してください</option>
						<option value="product">商品について</option>
					    <option value="order">注文について</option>
					    <option value="delivery">配送について</option>
					    <option value="payment">お支払いについて</option>
					    <option value="return">返品・交換について</option>
					    <option value="account">会員登録・ログインについて</option>
					    <option value="request">ご要望・ご意見</option>
					    <option value="other">その他</option>
					</select>
				</div>
				<div class="form-group">
					<span>お問い合わせ内容</span><br>
					<textarea name="content" cols="100" rows="15" placeholder="お問い合わせ内容をご記入してください。" required></textarea>
				</div>
				<button type="submit" class="contact-btn black-btn">
					送信
				</button>
			</form>
	
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>