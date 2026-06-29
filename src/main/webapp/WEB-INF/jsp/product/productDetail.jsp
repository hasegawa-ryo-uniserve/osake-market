<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.entity.Product, java.text.NumberFormat, model.entity.Review, model.entity.User, model.Gender, java.time.LocalDate, java.time.Period" %>
<%
	Product product = (Product)request.getAttribute("product");
	NumberFormat nf = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品詳細 - Osake Market</title>
		<meta name="description"
				content="Osake Marketの商品詳細ページです。
						商品の特徴や価格、レビューをご確認いただき、
						お気に入り登録やカートへの追加を行えます。">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<!-- CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
		<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.png">
		
		<!-- JavaScript -->
		<script>
			function postReview() {
				const form = document.getElementById("review");
		
				if(form.style.display == "none") {
					form.style.display = "block";
				} else {
					form.style.display = "none";
				}
			}
		</script>
	</head>
	<body>
		<header class="page-header wrapper">
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
		</header>
		
		<%
			String cartMessage = (String) session.getAttribute("cartMessage");
			if (cartMessage != null) {
		%>
				<p style="color: green; font-weight: bold; margin-left: 5%;"><%= cartMessage %></p>
		<%
		    	session.removeAttribute("cartMessage"); // 一回だけ表示
			}
		%>
		
		<%
			Boolean addResult = (Boolean) session.getAttribute("addResult");
			Boolean deleteResult = (Boolean) session.getAttribute("deleteResult");
			String favoriteMessage = (String) session.getAttribute("favoriteMessage");
			if ((addResult != null && addResult == true && favoriteMessage != null) ||
				(deleteResult != null && deleteResult == true && favoriteMessage != null)) {
		%>
				<p style="color: green; font-weight: bold;  margin-left: 5%;"><%= favoriteMessage %></p>
		<%
		    	session.removeAttribute("favoriteMessage"); // 一回だけ表示
		    	session.removeAttribute("addResult"); // 一回だけ表示
		    	session.removeAttribute("deleteResult"); // 一回だけ表示
			}
		%>
		
		<div class="product-detail-container">
			<img src="${pageContext.request.contextPath}/images/<%= product.getImageFile() %>" class="product-detail-image">
			<div class="product-detail-wrapper">
				<h1><%= product.getProductName() %></h1>
				<p><%= product.getDescription() %></p>
				<p>￥<%= nf.format(product.getPrice()) %>(税込)</p>
				<form action="${pageContext.request.contextPath}/cart/add" method="post">
					<input type="hidden" name="productId" value="<%= product.getProductId() %>">
					<input type="hidden" name="fromPage" value="detail">
					<button type="submit" class="black-btn">
						カートに追加する
					</button>
				</form>
				<div class="product-detail-favorite-add">
					<% if(product.isFavoriteFlag()) { %>
						<form action="${pageContext.request.contextPath}/favorite/delete" method="post">
							<input type="hidden" name="productId" value="<%= product.getProductId() %>">
							<input type="hidden" name="fromPage" value="detail">
							<button type="submit" class="colored-btn">
								<%-- <img src="${pageContext.request.contextPath}/images/heart.svg" class="heart"> --%>
								<span class="product-detail-heart">♡</span>
								お気に入りから削除する
							</button>
						</form>
					<% } else { %>
						<form action="${pageContext.request.contextPath}/favorite/add" method="post">
							<input type="hidden" name="productId" value="<%= product.getProductId() %>">
							<input type="hidden" name="fromPage" value="detail">
							<button type="submit" class="white-btn">
								<%-- <img src="${pageContext.request.contextPath}/images/heart.svg" class="heart"> --%>
								<span class="product-detail-heart">♡</span>
								お気に入りに追加する
							</button>
						</form>
					<% } %>
				</div>
			</div>
		</div>
		<form action="${pageContext.request.contextPath}/review/create" method="post">
			<input type="hidden" name="productId" value="<%= product.getProductId() %>">
			<button type="button" onclick="postReview()" class="review-post-btn white-btn">レビューを投稿する</button>
			<div id="review" style="display:none;">  
			  <div class="input-stars">
				<span>
				    <input id="review05" type="radio" name="star" value="5" required><label for="review05">★</label>
				    <input id="review04" type="radio" name="star" value="4"><label for="review04">★</label>
				    <input id="review03" type="radio" name="star" value="3"><label for="review03">★</label>
				    <input id="review02" type="radio" name="star" value="2"><label for="review02">★</label>
				    <input id="review01" type="radio" name="star" value="1"><label for="review01">★</label>
		    	</span>
			  </div>
			  <textarea name="reviewText" class="review-textarea" placeholder="商品の感想を入力して下さい。"
			  			cols="100" rows="20" required></textarea><br>
			  <!-- <input type="submit" value="投稿する"> -->
			  <button type="submit" class="review-post black-btn">投稿する</button>
			</div>
		</form>
		<% if (product.getReviewList() != null && !product.getReviewList().isEmpty()) { %>
			<h2 class="review-for-product">この商品のレビュー</h2>
			<div class="review-item-and-reviewer-container">
		    <% for (Review review : product.getReviewList()) { %>
		        <div class="review-item">
		            <p class="stars">
		            <% 
		                int star = review.getStar();
		                String stars = "";
		                switch(star) {
		                    case 5: stars = "★★★★★"; break;
		                    case 4: stars = "★★★★☆"; break;
		                    case 3: stars = "★★★☆☆"; break;
		                    case 2: stars = "★★☆☆☆"; break;
		                    case 1: stars = "★☆☆☆☆"; break;
		                    default: stars = "☆☆☆☆☆"; // 不正値の場合
		                }
		                out.print(stars);
		            %>
		            </p>
		            <p class="review-text"><%= review.getReviewText() %></p>
		            <p class="reviewer">
		            <%
		                    User user = review.getUser();
		            		LocalDate today = LocalDate.now();
		                    int age = Period.between(user.getBirthday(), today).getYears();
		                    String ageGroup = (age / 10 * 10) + "代"; // 30代、40代
		                    String gender = "";
		                    switch(user.getGender()) {
		                        case MALE: 
		                        	gender = "男性";
		                        	break;
		                        case FEMALE:
		                        	gender = "女性";
		                        	break;
		                        case OTHER:
		                        	gender = "その他";
		                        	break;
		                    }
		                    out.print("(" + ageGroup + gender + ")");
		            %>
		            </p>
		        </div>
		    <% } %>
		    </div>
		<% } else { %>
			<h2 class="review-for-product">この商品のレビュー</h2>
			<hr style="width: 90%;">
			<p style="color: green; font-weight: bold; margin-left: 5%;">この商品はレビューがまだありません。</p>
		<% } %>
		
		<footer>
			<jsp:include page="/WEB-INF/jsp/footer.jsp" />
		</footer>
	</body>
</html>