<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会社概要 - Osake Market</title>
		<meta name="description"
		      content="Osake Marketの会社概要ページです。
		      			販売業者情報や所在地、連絡先などの企業情報をご確認いただけます。">
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
	
		<h2>会社概要</h2>
		<table>
			<tr>
				<th>販売業者</th>
				<td>〇〇合同会社</td>
			</tr>
			<tr>
				<th>運営責任者</th>
				<td>山田太郎</td>
			</tr>
			<tr>
				<th>住所</th>
				<td>〒000-0000<br>東京都新宿区○○</td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td>000-000-0000</td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td>mailto:osakemarket@example.com</td>
			</tr>
			<tr>
				<th>URL</th>
				<td>http://www.osake-market.co.jp/</td>
			</tr>
		</table>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>