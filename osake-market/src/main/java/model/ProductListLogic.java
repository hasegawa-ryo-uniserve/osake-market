package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;

/**
 * 商品リストロジッククラス
 */
public class ProductListLogic {
	/**
	 * 商品一覧を返す
	 * 
	 * @productList 商品リスト
	 */
	public List<Product> findAll() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAll();
		if(productList == null) {
			return null;
		}
		return productList;
	}
}
