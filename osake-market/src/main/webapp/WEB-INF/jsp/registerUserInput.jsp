<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<div class="register-user-form">
<h1>新規会員登録</h1>
<form action="${pageContext.request.contextPath}/register/user/confirm" method="post">

<div class="sei-and-mei">
<div class="form-group">
<label>姓</label>
<input type="text" name="sei" size="35" placeholder="姓">
</div>

<div class="form-group">
<label>名</label>
<input type="text" name="mei" size="35" placeholder="名">
</div>
</div>

<div class="form-group">
<label>生年月日</label><input type="date" name="birthday">
</div>

<div class="form-group">
<label>性別</label>
<div class="radio-group">
<input type="radio" name="gender" value="1" checked>男性
<input type="radio" name="gender" value="2">女性
<input type="radio" name="gender" value="3">その他
</div>
</div>

<div class="postalCode-and-prefecture">
<div class="form-group">
<label>郵便番号</label><input type="text" name="postalCode" placeholder="例：1011001">
</div>

<div class="form-group">
<label>都道府県</label>
<select name="prefecture">
<option value="北海道">北海道</option>
<option value="青森県">青森県</option>
<option value="岩手県">岩手県</option>
<option value="宮城県">宮城県</option>
<option value="秋田県">秋田県</option>
<option value="山形県">山形県</option>
<option value="福島県">福島県</option>
<option value="茨城県">茨城県</option>
<option value="栃木県">栃木県</option>
<option value="群馬県">群馬県</option>
<option value="埼玉県">埼玉県</option>
<option value="千葉県">千葉県</option>
<option value="東京都" selected>東京都</option>
<option value="神奈川県">神奈川県</option>
<option value="新潟県">新潟県</option>
<option value="富山県">富山県</option>
<option value="石川県">石川県</option>
<option value="福井県">福井県</option>
<option value="山梨県">山梨県</option>
<option value="長野県">長野県</option>
<option value="岐阜県">岐阜県</option>
<option value="静岡県">静岡県</option>
<option value="愛知県">愛知県</option>
<option value="三重県">三重県</option>
<option value="滋賀県">滋賀県</option>
<option value="京都府">京都府</option>
<option value="大阪府">大阪府</option>
<option value="兵庫県">兵庫県</option>
<option value="奈良県">奈良県</option>
<option value="和歌山県">和歌山県</option>
<option value="鳥取県">鳥取県</option>
<option value="島根県">島根県</option>
<option value="岡山県">岡山県</option>
<option value="広島県">広島県</option>
<option value="山口県">山口県</option>
<option value="徳島県">徳島県</option>
<option value="香川県">香川県</option>
<option value="愛媛県">愛媛県</option>
<option value="高知県">高知県</option>
<option value="福岡県">福岡県</option>
<option value="佐賀県">佐賀県</option>
<option value="長崎県">長崎県</option>
<option value="熊本県">熊本県</option>
<option value="大分県">大分県</option>
<option value="宮崎県">宮崎県</option>
<option value="鹿児島県">鹿児島県</option>
<option value="沖縄県">沖縄県</option>
</select>
</div>
</div>

<div class="form-group">
<label>住所</label><input type="text" name="address" size="70" placeholder="市区町村・番地を入力">
</div>

<div class="form-group">
<label>建物名・部屋番号（任意）</label><input type="text" name="building" size="70" placeholder="マンション名など">
</div>

<div class="form-group">
<label>電話番号</label><input type="text" name="phoneNumber" placeholder="例：09012345678">
</div>

<div class="form-group">
<label>メールアドレス</label><input type="mail" name="mail" placeholder="メールアドレス">
</div>

<div class="form-group">
<label>パスワード</label><input type="text" name="password" placeholder="パスワード（半角英数字・記号のみ8~100文字">
</div>

<div class="register-user-submit">
<input type="submit" value="アカウントを作成する">
</div>
</form>
</div>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>
</body>
</html>