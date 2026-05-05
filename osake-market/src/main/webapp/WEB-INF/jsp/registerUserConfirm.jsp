<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Gender" %>
<%
	User registerUser = (User)request.getAttribute("registerUser");
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
<div class="register-user-confirm">
<h1>確認画面</h1>
<p>
以下の内容で送信してもよろしいでしょうか？<br>
よろしければ、一番下の「会員登録をする」を押してください。
</p>
<table border="1" class="register-user-confirm-table">
<tr>
<th>姓</th>
<td><%= registerUser.getSei() %></td>
</tr>
<tr>
<th>名</th>
<td><%= registerUser.getMei() %></td>
</tr>
<tr>
<th>生年月日</th>
<td><%= registerUser.getBirthday() %></td>
</tr>
<tr>
<th>性別</th>
<% if(registerUser.getGender() == Gender.MALE) { %>
<td>男性</td>
<% } else if(registerUser.getGender() == Gender.FEMALE) { %>
<td>女性</td>
<% } else { %>
<td>その他</td>
<% } %>
</tr>
<tr>
<th>郵便番号</th>
<td><%= registerUser.getPostalCode() %></td>
</tr>
<tr>
<th>都道府県</th>
<td><%= registerUser.getPrefecture() %></td>
</tr>
<tr>
<th>住所</th>
<td><%= registerUser.getAddress() %></td>
</tr>
<tr>
<th>建物名・部屋番号（任意）</th>
<td><%= registerUser.getBuilding() %></td>
</tr>
<tr>
<th>電話番号</th>
<td><%= registerUser.getPhoneNumber() %></td>
</tr>
<tr>
<th>メールアドレス</th>
<td><%= registerUser.getMail() %></td>
</tr>
<tr>
<th>パスワード</th>
<td><%= registerUser.getPassword() %></td>
</tr>
</table>

<form action="${pageContext.request.contextPath}/register/user/done" method="post">
<input type="hidden" name="sei" value="${registerUser.sei}">
<input type="hidden" name="mei" value="${registerUser.mei}">
<input type="hidden" name="birthday" value="<%= registerUser.getBirthday().toString() %>">
<input type="hidden" name="gender" value="<%= registerUser.getGender().getValue() %>">
<input type="hidden" name="postalCode" value="${registerUser.postalCode}">
<input type="hidden" name="prefecture" value="${registerUser.prefecture}">
<input type="hidden" name="address" value="${registerUser.address}">
<input type="hidden" name="building" value="${registerUser.building}">
<input type="hidden" name="phoneNumber" value="${registerUser.phoneNumber}">
<input type="hidden" name="mail" value="${registerUser.mail}">
<input type="hidden" name="password" value="${registerUser.password}">

<div class="resister-user-confirm-submit">
<input type="submit" value="会員登録する">
</div>
<button>
<a href="${pageContext.request.contextPath}/register/user">戻る</a>
</button>
</form>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>