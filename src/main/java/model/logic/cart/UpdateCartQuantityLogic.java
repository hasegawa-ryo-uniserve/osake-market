package model.logic.cart;

import java.util.Iterator;
import java.util.List;

import model.entity.CartItem;

/**
 * カート内の商品数を増減させるロジッククラス
 */
public class UpdateCartQuantityLogic {
	/**
	 * カート内の商品数を増減させる
	 * 
	 * @param cart カート
	 * @param productId 商品ID
	 * @param delta 増減量
	 * @return カート
	 */
	public List<CartItem> execute(List<CartItem> cart, int productId, int delta) {
		// イテレータを使って対象の商品IDの商品を検索する
		Iterator<CartItem> it = cart.iterator();
		while(it.hasNext()) {
			CartItem cartItem = it.next();
			// 対象の商品IDの商品の数量をdelta分増やす（減らす）
			if(cartItem.getProduct().getProductId() == productId) {
				int quantity = cartItem.getQuantity() + delta;
				// 計算の結果商品数が0以下になったら、
				// 対象の商品を削除する
				if(quantity <= 0) {
					it.remove();
				} else {
					// 商品数が正の数の場合、商品数を再登録する
					cartItem.setQuantity(quantity);
				}
			}
		}
		return cart;
	}
}
