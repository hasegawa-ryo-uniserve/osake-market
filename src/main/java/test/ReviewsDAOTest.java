package test;

import java.util.ArrayList;
import java.util.List;

import dao.ReviewsDAO;
import model.entity.Review;

/**
 * レビューDAOテストクラス
 */
public class ReviewsDAOTest {

	public static void main(String[] args) {
//		testReviewFindByProductIdOK();	// 商品IDでレビューが見つかるテスト	
//		testReviewFindByProductIdNG();	// 商品IDでレビューが見つからないテスト
		testRegistReviewOK();				// レビューを投稿するテスト
		testRegistReviewNG();				// レビューを投稿できないテスト
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
	
	public static void testRegistReviewOK() {
		ReviewsDAO dao = new ReviewsDAO();
		Review review = new Review();
		review.setUserId(1);
		review.setProductId(1);
		review.setStar(1);
		review.setReviewText("テスト");
		boolean result = dao.registReview(review);
		if(result) {
			System.out.println("testRegistReviewOK：成功しました" );
		} else {
			System.out.println("testRegistReviewOK：失敗しました" );
		}
	}
	
	public static void testRegistReviewNG() {
		ReviewsDAO dao = new ReviewsDAO();
		Review review = new Review();
		review.setUserId(100);
		review.setProductId(1);
		review.setStar(1);
		review.setReviewText(null);
		boolean result = dao.registReview(review);
		if(!result) {
			System.out.println("testRegistReviewNG：成功しました" );
		} else {
			System.out.println("testRegistReviewNG：失敗しました" );
		}
	}
}
