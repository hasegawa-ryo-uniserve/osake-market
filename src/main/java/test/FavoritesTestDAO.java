package test;

import java.util.List;

import dao.FavoritesDAO;
import model.entity.Favorite;
import model.entity.Product;

/**
 * お気に入りDAOテストクラス
 */
public class FavoritesTestDAO {
	public static void main(String[] args) {
//		testFindByUserIdOK();	// 会員IDをもとにお気に入り商品一覧を取得するテスト
//		testFindByUserIdNG();	// 会員IDをもとにお気に入り商品一覧を取得に失敗するテスト
//		testRegisterFavoriteOK();	// お気に入りを追加するテスト
//		testRegisterFavoriteNG();	// お気に入りを追加失敗するテスト
		testDeleteFavoriteOK();		// お気に入りを削除するテスト
		testDeleteFavoriteNG();		// お気に入りを削除失敗するテスト
	}
	
	public static void testFindByUserIdOK() {
		FavoritesDAO dao = new FavoritesDAO();
		List<Product> favoritesList = dao.findByUserId(1);
		if(favoritesList != null && !favoritesList.isEmpty()) {
			System.out.println("testFindByUserIdOK：成功しました");
		} else {
			System.out.println("testFindByUserIdOK：失敗しました");
		}
	}
	
	public static void testFindByUserIdNG() {
		FavoritesDAO dao = new FavoritesDAO();
		List<Product> favoritesList = dao.findByUserId(0);
		if(favoritesList == null || favoritesList.isEmpty()) {
			System.out.println("testFindByUserIdNG：成功しました");
		} else {
			System.out.println("testFindByUserIdNG：失敗しました");
		}
	}
	
	public static void testRegisterFavoriteOK() {
		FavoritesDAO dao = new FavoritesDAO();
		Favorite favorite = new Favorite();
		favorite.setUserId(1);
		favorite.setProductId(1);
		boolean result = dao.registerFavorite(favorite);
		if(result) {
			System.out.println("testRegisterFavoriteOK：成功しました");
		} else {
			System.out.println("testRegisterFavoriteOK：失敗しました");
		}
	}
	
	public static void testRegisterFavoriteNG() {
		FavoritesDAO dao = new FavoritesDAO();
		Favorite favorite = new Favorite();
		favorite.setUserId(0);
		favorite.setProductId(1);
		boolean result = dao.registerFavorite(favorite);
		if(!result) {
			System.out.println("testRegisterFavoriteNG：成功しました");
		} else {
			System.out.println("testRegisterFavoriteNG：失敗しました");
		}
	}
	
	public static void testDeleteFavoriteOK() {
		FavoritesDAO dao = new FavoritesDAO();
		Favorite favorite = new Favorite();
		favorite.setUserId(1);
		favorite.setProductId(4);
		boolean result = dao.deleteFavorite(favorite);
		if(result) {
			System.out.println("testDeleteFavoriteOK：成功しました");
		} else {
			System.out.println("testDeleteFavoriteOK：失敗しました");
		}
	}
	
	public static void testDeleteFavoriteNG() {
		FavoritesDAO dao = new FavoritesDAO();
		Favorite favorite = new Favorite();
		favorite.setUserId(0);
		favorite.setProductId(8);
		boolean result = dao.deleteFavorite(favorite);
		if(!result) {
			System.out.println("testDeleteFavoriteNG：成功しました");
		} else {
			System.out.println("testDeleteFavoriteNG：失敗しました");
		}
	}
}
