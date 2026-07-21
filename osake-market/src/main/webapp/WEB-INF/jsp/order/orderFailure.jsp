<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>注文失敗 - Osake Market</title>
		<meta name="description"
		      content="ご注文の処理を完了できませんでした。
		      			時間をおいて再度お試しいただくか、カートの内容をご確認ください。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
<body>
<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div id="order-complete">
			<h2>
				ご注文に失敗しました
			</h2>
			<p>申し訳ございません。ご注文を正常に完了できませんでした。</p>
			<p>カートの内容や入力情報をご確認のうえ、もう一度お試しください。</p>
			<a href="${pageContext.request.contextPath}/cart">
				<button class="black-btn">
					カートへ戻る
				</button>
			</a>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
</body>
</html>