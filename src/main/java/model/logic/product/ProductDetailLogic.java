package model.logic.product;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import model.entity.Product;
import model.entity.Review;
import model.entity.User;

/**
 * 商品詳細ロジッククラス
 */
public class ProductDetailLogic {
	/**
	 * 商品IDをもとに商品を取得する
	 * 
	 * @param productId 商品ID
	 * @return 商品
	 */
	public Product execute(int productId, Integer userId) {
		ProductsDAO productDao = new ProductsDAO();
		Product product = productDao.findByProductId(productId, userId);
		if (product == null) {
			return null;
		}
		List<Review> reviewList = new ArrayList<>();
		ReviewsDAO reviewsDao = new ReviewsDAO();
		reviewList = reviewsDao.findByProductId(product.getProductId());
		if (reviewList == null) {
			reviewList = new ArrayList<>();
		} else {
			UsersDAO userDao = new UsersDAO();
			for (Review review : reviewList) {
			    User user = userDao.findByUserId(review.getUserId());  // usersテーブルから取得
			    review.setUser(user);  // Review クラスに User フィールドを追加
			}
		}
		product.setReviewList(reviewList);
		return product;
	}
}
