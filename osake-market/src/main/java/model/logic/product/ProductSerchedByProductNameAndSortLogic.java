package model.logic.product;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import model.entity.Product;

/**
 * 商品名とソートによる商品一覧絞り込みロジッククラス
 */
public class ProductSerchedByProductNameAndSortLogic {
	/**
	 * 処理
	 * 
	 * @param productName 商品名
	 * @param sort ソート
	 * @return 商品リスト
	 */
	public List<Product> execute(String productName, String sort, Integer userId) {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		// ソート名の変換処理
		switch(sort) {
			case "new":
				sort = "create_date ASC";
				break;
			case "old":
				sort = "create_date DESC";
				break;
			case "name":
				sort = "product_name ASC";
				break;
			default:
				break;
		}
		productList = dao.findAllWithProductNameOrderBySort(productName, sort, userId);
		if(productList == null) {
			return null;
		}
		return productList;
	}
}
