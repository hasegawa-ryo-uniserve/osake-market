package dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Favorite;
import model.entity.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FavoritesDAOTest {
	// DAOを生成
	private final FavoritesDAO dao = new FavoritesDAO();
	
	@DisplayName("No.1 お気に入り一覧を取得する 正常系")
	@Test
	@Order(1)
	void testFindByUserIdOK() {
		List<Product> favoriteList = dao.findByUserId(1);
		assertFalse(favoriteList.isEmpty());
	}
	
	@DisplayName("No.2 お気に入り一覧を取得する 異常系")
	@Test
	@Order(2)
	void testFindByUserIdNG() {
		List<Product> favoriteList = dao.findByUserId(0);
		assertTrue(favoriteList.isEmpty());
	}

	@DisplayName("No.3 お気に入りを追加する 正常系")
	@Test
	@Order(3)
	void testRegisterFavoriteOK() {
		Favorite favorite = new Favorite(null, 1, 5, null, null, null, false);	// 会員ID1,商品ID5の商品がDBに追加される
		boolean result = dao.registerFavorite(favorite);
		assertTrue(result);
	}
	
	@DisplayName("No.4 お気に入りを追加する 異常系")
	@Test
	@Order(4)
	void testRegisterFavoriteNG() {
		Favorite favorite = new Favorite(null, 0, 8, null, null, null, false);
		boolean result = dao.registerFavorite(favorite);
		assertFalse(result);
	}

	@DisplayName("No.5 お気に入りを削除する 正常系")
	@Test
	@Order(5)
	void testDeleteFavoritOK() {
		Favorite favorite = new Favorite(1, 1, 1, null, null, null, false);
		boolean result = dao.deleteFavorite(favorite);	// お気に入りID1が削除されDBに反映
		assertTrue(result);
	}
	
	@DisplayName("No.6 お気に入りを削除する 異常系")
	@Test
	@Order(6)
	void testDeleteFavoritNG() {
		Favorite favorite = new Favorite(0, 1, 1, null, null, null, false);
		boolean result = dao.deleteFavorite(favorite);
		assertFalse(result);
	}

}
