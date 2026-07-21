package model.logic.favorite;

import java.util.List;

import dao.FavoritesDAO;
import model.entity.Favorite;
import model.entity.Product;

/**
 * お気に入り表示ロジッククラス
 */
public class FavoriteLogic {
	/**
	 * お気に入りを表示する
	 * 
	 * @param userId 会員ID
	 * @return favoriteList お気に入りリスト
	 */
	public List<Product> showFavorite(int userId) {
		FavoritesDAO dao = new FavoritesDAO();
		List<Product> favoriteList = dao.findByUserId(userId);
		
		if(favoriteList == null || favoriteList.isEmpty()) {
			return null;
		}
		return favoriteList;
	}
	
	/**
	 * お気に入りを追加する
	 * 
	 * @param userId 会員ID
	 * @param productId 商品ID
	 * @return 実行結果
	 */
	public boolean addFavorite(int userId, int productId) {
		FavoritesDAO dao = new FavoritesDAO();
		
		Favorite favorite = new Favorite();
		favorite.setUserId(userId);
		favorite.setProductId(productId);
		
		boolean result = dao.registerFavorite(favorite);
		
		return result;
	}
	
	/**
	 * お気に入りを削除する
	 * 
	 * @param userId 会員ID
	 * @param productId 商品ID
	 * @return 実行結果
	 */
	public boolean deleteFavorite(int userId, int productId) {
		FavoritesDAO dao = new FavoritesDAO();
		
		Favorite favorite = new Favorite();
		favorite.setUserId(userId);
		favorite.setProductId(productId);
		
		boolean result = dao.deleteFavorite(favorite);
		
		return result;
	}
}
