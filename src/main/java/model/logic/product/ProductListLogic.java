package model.logic.product;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import model.entity.Product;

/**
 * 商品リストロジッククラス
 */
public class ProductListLogic {
	/**
	 * カテゴリ名で検索して該当する商品一覧を返す
	 * 
	 * @param categoryName カテゴリ名
	 * @return 商品リスト
	 */
	public List<Product> findByCategoryName(String categoryName) {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		switch(categoryName) {
		case "wine":
			categoryName = "ワイン";
			break;
		case "sparklingWine":
			categoryName = "スパークリングワイン";
			break;
		case "whisky":
			categoryName = "ウイスキー";
			break;
		case "brandy":
			categoryName = "ブランデー";
			break;
		case "shochu":
			categoryName = "焼酎";
			break;
		case "japaneseSake":
			categoryName = "日本酒";
			break;
		case "liqueur":
			categoryName = "リキュール";
			break;
		case "beer":
			categoryName = "ビール";
			break;
		case "food":
			categoryName = "おつまみ";
			break;
		default:
			break;
		}
		productList = dao.findByCategoryName(categoryName);
		if(productList == null) {
			return null;
		}
		return productList;
	}
	
	/**
	 * 商品一覧を返す
	 * 
	 * @param userId 会員一覧
	 * @productList 商品リスト
	 */
	public List<Product> findAll(Integer userId) {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		
		if(userId != null) {
			productList = dao.findAllWithFavorite(userId);
		} else {
			productList = dao.findAll();
		}
		
		return productList;
	}
}
