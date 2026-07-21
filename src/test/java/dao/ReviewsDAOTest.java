package dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Review;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewsDAOTest {
	// DAOを生成
	private final ReviewsDAO dao = new ReviewsDAO();
	
	@DisplayName("No.1 商品IDをもとにレビューを取得する 正常系")
	@Test
	@Order(1)
	void testFindByProductIdOK() {
		List<Review> reviewList = dao.findByProductId(1);
		assertFalse(reviewList.isEmpty());
	}
	
	@DisplayName("No.2 商品IDをもとにレビューを取得する 異常系")
	@Test
	@Order(2)
	void testFindByProductIdNG() {
		List<Review> reviewList = dao.findByProductId(2);	// レビューがまだない商品ID
		assertTrue(reviewList.isEmpty());
	}
	
	@DisplayName("No.3 レビューを登録する 正常系")
	@Test
	@Order(3)
	void testRegistReviewOK() {
		Review review = new Review(null, 1, 1, 3, "まずまず", null, null, null, false);
		boolean result = dao.registReview(review);
		assertTrue(result);
	}
	
	@DisplayName("No.4 レビューを登録する 異常系")
	@Test
	@Order(4)
	void testRegistReviewNG() {
		Review review = new Review(null, 0, 1, 3, "まずまず", null, null, null, false);
		boolean result = dao.registReview(review);
		assertFalse(result);
	}

}
