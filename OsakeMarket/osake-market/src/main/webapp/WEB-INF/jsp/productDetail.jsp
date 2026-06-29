<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Product, java.text.NumberFormat, model.Review, model.User, model.Gender, java.time.LocalDate, java.time.Period" %>
<%
	Product product = (Product)request.getAttribute("product");
	NumberFormat nf = NumberFormat.getInstance();
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

<%
String cartMessage = (String) session.getAttribute("cartMessage");
if (cartMessage != null) {
%>
<p style="color: green; font-weight: bold;"><%= cartMessage %></p>
<%
    session.removeAttribute("cartMessage"); // 一回だけ表示
}
%>

<div class="product-detail-container">
<img src="${pageContext.request.contextPath}/images/<%= product.getImageFile() %>" class="product-detail-image">
<div class="product-detail-wrapper">
<h1><%= product.getProductName() %></h1>
<span><%= product.getDescription() %></span>
<span>￥<%= nf.format(product.getPrice()) %>(税込)</span>
<form action="${pageContext.request.contextPath}/cart/add" method="post">
<input type="hidden" name="productId" value="<%= product.getProductId() %>">
<input type="hidden" name="fromPage" value="detail">
<button type="submit">
カートに追加する
</button>
</form>
<div class="product-detail-favorite-add">
<button>
お気に入りに追加する
<img src="${pageContext.request.contextPath}/images/heart.svg" class="heart">
</button>
</div>
</div>
</div>
<% if (product.getReviewList() != null) { %>
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
<% } %>
<footer>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</footer>

</body>
</html>