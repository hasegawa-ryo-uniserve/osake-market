package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;

/**
 * ソートによる商品一覧絞り込みロジッククラス
 */
public class ProductSerchedBySortLogic {
	/**
	 * 処理
	 * 
	 * @param categoryName カテゴリ名
	 * @param sort ソート
	 * @return 商品リスト
	 */
	public List<Product> execute(String sort) {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
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
		productList = dao.findAllOrderBySort(sort);
		if(productList == null) {
			return null;
		}
		return productList;
	}
}
