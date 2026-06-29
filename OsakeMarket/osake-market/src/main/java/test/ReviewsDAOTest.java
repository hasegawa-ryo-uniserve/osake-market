package test;

import java.util.ArrayList;
import java.util.List;

import dao.ReviewsDAO;
import model.Review;

/**
 * レビューDAOテストクラス
 */
public class ReviewsDAOTest {

	public static void main(String[] args) {
		testReviewFindByProductIdOK();	// 商品IDでレビューが見つかるテスト	
		testReviewFindByProductIdNG();	// 商品IDでレビューが見つからないテスト
	}
	public static void testReviewFindByProductIdOK() {
		ReviewsDAO dao = new ReviewsDAO();
		List<Review> reviewList = new ArrayList<>();
		reviewList = dao.findByProductId(1);
		if (reviewList != null) {
			System.out.println("testReviewFindByProductIdOK：成功しました");
		} else {
			System.out.println("testReviewFindByProductIdOK：失敗しました");
		}
	}
	
	public static void testReviewFindByProductIdNG() {
		ReviewsDAO dao = new ReviewsDAO();
		List<Review> reviewList = new ArrayList<>();
		reviewList = dao.findByProductId(1);
		reviewList = null;
		if (reviewList == null) {
			System.out.println("testReviewFindByProductIdNG：成功しました");
		} else {
			System.out.println("testReviewFindByProductIdNG：失敗しました");
		}
	}
}
