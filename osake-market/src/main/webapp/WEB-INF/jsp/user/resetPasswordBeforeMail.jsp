<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>パスワードリセット(メールアドレス入力) - Osake Market</title>
		<meta name="description"
				content="Osake Marketのパスワードリセットページです。
						登録済みのメールアドレスを入力すると、
						パスワード再設定用のメールを送信します。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/resetPassword.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
	
		<% if(errorMsg != null) { %>
			<p id="error-msg" style="color: red; margin: 60px 100px;">
				<%= errorMsg %>
			</p>
		<% } %>
		<div class="reset-password-send-mail">
			<h2>パスワードのリセット</h2>
			<p>
				登録されているメールアドレスを入力して下さい。<br>
				パスワードリセットのメールをお送りします。<br>
				携帯メールアドレス（docomo/au/softbankのキャリアメール）<br>の場合は
				「@example.jp」のドメインにて受信設定をお願いいたします。
			</p>
			<div class="reset-password-form">
				<form action="${pageContext.request.contextPath}/reset/password/mail" method="post">
					<input type="text" name="mail" placeholder="メールアドレス" size="70">
					<div class="reset-password-submit">
						<!-- <input type="submit" value="送信" class="black-btn"> -->
						<button type="submit" class="black-btn">
							送信
						</button>
					</div>
				</form>
				<a href="${pageContext.request.contextPath}/login">
					<button class="white-btn">
						戻る
					</button>
				</a>
			</div>
		</div>
	
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>