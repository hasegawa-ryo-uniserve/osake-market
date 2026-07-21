<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.User, model.Gender" %>
<%
	User inputUser = (User)request.getAttribute("inputUser");
	String selectedPrefecture = inputUser != null ? inputUser.getPrefecture() : "東京都";
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>新規会員登録(入力) - Osake Market</title>
	<meta name="description"
				content="Osake Marketの新規会員登録ページです。
						必要事項を入力して会員登録を行うと、お気に入り登録や
						スムーズなお買い物など便利な機能をご利用いただけます。">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerUser.css">
	<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<div class="register-user-form">
			<h2>新規会員登録</h2>
			<form action="${pageContext.request.contextPath}/register/user/confirm" method="post">
				<div class="sei-and-mei">
					<div class="form-group">
						<label>姓</label>
						<input type="text" name="sei" size="35" placeholder="姓" value="<%= inputUser != null ? inputUser.getSei() : "" %>" required>
					</div>
					
					<div class="form-group">
						<label>名</label>
						<input type="text" name="mei" size="35" placeholder="名" value="<%= inputUser != null ? inputUser.getMei() : "" %>" required>
					</div>
				</div>
				
				<div class="form-group">
					<label>生年月日</label>
					<input type="date" name="birthday" 
						value="<%= inputUser != null ? inputUser.getBirthday() : "" %>" required>
				</div>
				
				<div class="form-group">
					<label>性別</label>
					<div class="radio-group">
						<input type="radio" name="gender" id="male" value="1" <%= inputUser == null || inputUser.getGender() == Gender.MALE ? "checked" : "" %>><label class="gender-label" for="male">男性</label>
						<input type="radio" name="gender" id="female" value="2" <%= inputUser != null && inputUser.getGender() == Gender.FEMALE ? "checked" : "" %>><label class="gender-label" for="female">女性</label>
						<input type="radio" name="gender" id="other" value="3" <%= inputUser != null && inputUser.getGender() == Gender.OTHER ? "checked" : "" %>><label class="gender-label" for="other">その他</label>
					</div>
				</div>
				
				<div class="postalCode-and-prefecture">
					<div class="form-group">
						<label>郵便番号</label>
						<input type="text" name="postalCode" placeholder="例：1011001" value="<%= inputUser != null ? inputUser.getPostalCode() : "" %>" required>
					</div>
					<div class="form-group">
						<label>都道府県</label>
						<select name="prefecture">
							<option value="北海道" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>北海道</option>
							<option value="青森県" <%= "青森県".equals(selectedPrefecture) ? "selected" : "" %>>青森県</option>
							<option value="岩手県" <%= "岩手県".equals(selectedPrefecture) ? "selected" : "" %>>岩手県</option>
							<option value="宮城県" <%= "宮城県".equals(selectedPrefecture) ? "selected" : "" %>>宮城県</option>
							<option value="秋田県" <%= "秋田県".equals(selectedPrefecture) ? "selected" : "" %>>秋田県</option>
							<option value="山形県" <%= "山形県".equals(selectedPrefecture) ? "selected" : "" %>>山形県</option>
							<option value="福島県" <%= "福島県".equals(selectedPrefecture) ? "selected" : "" %>>福島県</option>
							<option value="茨城県" <%= "茨城県".equals(selectedPrefecture) ? "selected" : "" %>>茨城県</option>
							<option value="栃木県" <%= "栃木県".equals(selectedPrefecture) ? "selected" : "" %>>栃木県</option>
							<option value="群馬県" <%= "群馬県".equals(selectedPrefecture) ? "selected" : "" %>>群馬県</option>
							<option value="埼玉県" <%= "埼玉県".equals(selectedPrefecture) ? "selected" : "" %>>埼玉県</option>
							<option value="千葉県" <%= "千葉県".equals(selectedPrefecture) ? "selected" : "" %>>千葉県</option>
							<option value="東京都" <%= "東京都".equals(selectedPrefecture) ? "selected" : "" %>>東京都</option>
							<option value="神奈川県" <%= "神奈川県".equals(selectedPrefecture) ? "selected" : "" %>>神奈川県</option>
							<option value="新潟県" <%= "新潟県".equals(selectedPrefecture) ? "selected" : "" %>>新潟県</option>
							<option value="富山県" <%= "富山県".equals(selectedPrefecture) ? "selected" : "" %>>富山県</option>
							<option value="石川県" <%= "石川県".equals(selectedPrefecture) ? "selected" : "" %>>石川県</option>
							<option value="福井県" <%= "福井県".equals(selectedPrefecture) ? "selected" : "" %>>福井県</option>
							<option value="山梨県" <%= "山梨県".equals(selectedPrefecture) ? "selected" : "" %>>山梨県</option>
							<option value="長野県" <%= "長野県".equals(selectedPrefecture) ? "selected" : "" %>>長野県</option>
							<option value="岐阜県" <%= "岐阜県".equals(selectedPrefecture) ? "selected" : "" %>>岐阜県</option>
							<option value="静岡県" <%= "静岡県".equals(selectedPrefecture) ? "selected" : "" %>>静岡県</option>
							<option value="愛知県" <%= "愛知県".equals(selectedPrefecture) ? "selected" : "" %>>愛知県</option>
							<option value="三重県" <%= "三重県".equals(selectedPrefecture) ? "selected" : "" %>>三重県</option>
							<option value="滋賀県" <%= "滋賀県".equals(selectedPrefecture) ? "selected" : "" %>>滋賀県</option>
							<option value="京都府" <%= "京都府".equals(selectedPrefecture) ? "selected" : "" %>>京都府</option>
							<option value="大阪府" <%= "大阪府".equals(selectedPrefecture) ? "selected" : "" %>>大阪府</option>
							<option value="兵庫県" <%= "兵庫県".equals(selectedPrefecture) ? "selected" : "" %>>兵庫県</option>
							<option value="奈良県" <%= "奈良県".equals(selectedPrefecture) ? "selected" : "" %>>奈良県</option>
							<option value="和歌山県" <%= "和歌山県".equals(selectedPrefecture) ? "selected" : "" %>>和歌山県</option>
							<option value="鳥取県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>鳥取県</option>
							<option value="島根県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>島根県</option>
							<option value="岡山県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>岡山県</option>
							<option value="広島県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>広島県</option>
							<option value="山口県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>山口県</option>
							<option value="徳島県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>徳島県</option>
							<option value="香川県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>香川県</option>
							<option value="愛媛県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %> >愛媛県</option>
							<option value="高知県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>高知県</option>
							<option value="福岡県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>福岡県</option>
							<option value="佐賀県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>佐賀県</option>
							<option value="長崎県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>長崎県</option>
							<option value="熊本県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>熊本県</option>
							<option value="大分県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>大分県</option>
							<option value="宮崎県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>宮崎県</option>
							<option value="鹿児島県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>鹿児島県</option>
							<option value="沖縄県" <%= "北海道".equals(selectedPrefecture) ? "selected" : "" %>>沖縄県</option>
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label>住所</label>
					<input type="text" name="address" size="70" placeholder="市区町村・番地を入力" value="<%= inputUser != null ? inputUser.getAddress() : "" %>" required>
				</div>
				
				<div class="form-group">
					<label>建物名・部屋番号（任意）</label>
					<input type="text" name="building" size="70" placeholder="マンション名など" value="<%= inputUser != null ? inputUser.getBuilding() : "" %>">
				</div>
				
				<div class="form-group">
					<label>携帯電話番号</label>
					<input type="text" name="phoneNumber" placeholder="例：09012345678" value="<%= inputUser != null ? inputUser.getPhoneNumber() : "" %>" required>
				</div>
				
				<div class="form-group">
					<label>メールアドレス</label>
					<input type="mail" name="mail" placeholder="メールアドレス" value="<%= inputUser != null ? inputUser.getMail() : "" %>" required>
				</div>
				
				<div class="form-group">
					<label>パスワード</label>
					<input type="text" name="password" placeholder="パスワード（半角英数字・記号のみ" minlength="8" maxlength="100" required>
				</div>
				
				<div class="register-user-submit">
					<input type="submit" value="アカウントを作成する" class="black-btn">
				</div>
			</form>
			<a href="${pageContext.request.contextPath}/login">
				<button class="white-btn">
					戻る
				</button>
			</a>
		</div>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>