package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;

/**
 * カテゴリ名とソートによる商品一覧絞り込みロジッククラス
 */
public class ProductSerchedByCategoryNameAndSortLogic {
	/**
	 * 処理
	 * 
	 * @param categoryName カテゴリ名
	 * @param sort ソート
	 * @return 商品リスト
	 */
	public List<Product> execute(String categoryName, String sort) {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		// カテゴリ名の変換処理
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
		// ソート名の変換処理
		switch(sort) {
			case "new":
				sort = "p.create_date ASC";
				break;
			case "old":
				sort = "p.create_date DESC";
				break;
			case "name":
				sort = "p.product_name ASC";
				break;
			default:
				sort = "p.create_date ASC";
				break;
		}
		productList = dao.findAllWithCategoryNameOrderBySort(categoryName, sort);
		if(productList == null) {
			return null;
		}
		return productList;
	}
}
