package model.logic.review;

import dao.ReviewsDAO;
import model.entity.Review;

/**
 * 商品レビュー投稿ロジッククラス
 */
public class ReviewLogic {
	/**
	 * 商品レビューを投稿
	 * 
	 * @param review レビュー
	 * @return 実行結果
	 */
	public boolean createReview(Review review) {
		ReviewsDAO dao = new ReviewsDAO();
		boolean result = dao.registReview(review);
		
		if(result) {
			return true;
		} else {
			return false;
		}
	}
}
