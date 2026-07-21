<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>特定商取引法 - Osake Market</title>
		<meta name="description"
		      content="Osake Marketの特定商取引法に基づく表記ページです。
		      			販売業者情報や支払方法、送料、返品条件などをご確認いただけます。">
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
		
		<h2>特定商取引法に基づく表記</h2>
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
				<th>販売価格</th>
				<td>各商品ページに表示された価格（税込）</td>
			</tr>
			<tr>
				<th>商品代金以外の必要料金</th>
				<td>・送料<br>
					・銀行振込手数料（銀行振込の場合）
				</td>
			</tr>
			<tr>
				<th>支払方法</th>
				<td>・クレジットカード<br>
					・銀行振込
				</td>
			</tr>
			<tr>
				<th>支払時期</th>
				<td>・クレジットカード：注文時決済<br>
					・銀行振込：注文後7日以内
				</td>
			</tr>
			<tr>
				<th>商品の引渡時期</th>
				<td>ご入金確認後、3営業日以内に発送いたします。</td>
			</tr>
			<tr>
				<th>返品・交換について</th>
				<td>商品の欠陥または当社の不備による場合を除き、返品・交換はお受けできません。</td>
			</tr>
		</table>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>