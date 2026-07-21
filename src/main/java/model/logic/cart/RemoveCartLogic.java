package model.logic.cart;

import java.util.Iterator;
import java.util.List;

import model.entity.CartItem;

/**
 * カート内の商品を削除するロジッククラス
 */
public class RemoveCartLogic {
	/**
	 * カート内の商品を削除する
	 * 
	 * @param cart カート
	 * @param productId 商品ID
	 * @return カート
	 */
	public List<CartItem> execute(List<CartItem> cart, int productId) {
		// イテレータを使って対象の商品IDの商品を削除する
		Iterator<CartItem> it = cart.iterator();
		while(it.hasNext()) {
			CartItem cartItem = it.next();
			if(cartItem.getProduct().getProductId() == productId) {
				it.remove();
			}
		}
		return cart;
	}
}
