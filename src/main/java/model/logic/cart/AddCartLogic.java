package model.logic.cart;

import java.util.List;

import dao.ProductsDAO;
import model.entity.CartItem;
import model.entity.Product;

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
	public List<CartItem> execute(List<CartItem> cart, int productId, Integer userId) {
		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findByProductId(productId, userId);
		if(product == null) {
			return cart;
		}
		
		// すでにカートにあるかチェック
		for(CartItem item : cart) {
			if(item.getProduct().getProductId() == productId) {
				// 既存なら数量+1
				item.setQuantity(item.getQuantity() + 1);
				return cart;
			}
		}
		
		// なければ新規追加
        cart.add(new CartItem(product, 1));
        
        return cart;
		
	}
}
