<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
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
<div class="register-user-result">
<% if(loginUser != null) { %> 
<h1>会員登録ありがとうございます</h1>
<p>
ご入力いただいた内容で、会員登録が完了しました。
</p>
<button>
<a href="${pageContext.request.contextPath}/index.jsp">ホームに戻る</a>
</button>
<% } else { %>
<h1>会員登録に失敗しました</h1>
<p>
申し訳ありません。会員登録中に問題が発生しました。<br>
下のボタンからもう一度お試しください。
</p>
<button>
<a href="${pageContext.request.contextPath}/register/user">新規会員登録</a>
</button>
<% } %>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>