<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String errorMsg = (String)request.getAttribute("errorMsg");
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
<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
<div class="reset-password-send-mail">
<h1>パスワードのリセット</h1>
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
<input type="submit" value="送信">
</div>
</form>
<button>
<a href="${pageContext.request.contextPath}/index.jsp">戻る</a>
</button>
</div>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>