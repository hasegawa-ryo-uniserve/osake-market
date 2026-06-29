package model;

import java.util.List;

import dao.ProductsDAO;

/**
 * カートに商品を追加するロジッククラス
 */
public class AddCartLogic {
	/**
	 * カートに商品を追加する
	 * 
	 * @param cart カート
	 * @param productId 商品ID
	 * @return カート
	 */
	public List<Product> execute(List<Product> cart, int productId) {
		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findByProductId(productId);
		if (product != null) {
			cart.add(product);			
		}
		return cart;
		
	}
}
