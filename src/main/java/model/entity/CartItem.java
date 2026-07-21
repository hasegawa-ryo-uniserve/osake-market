package model.entity;

import model.entity.Product;

/**
 * カート内商品クラス
 */
public class CartItem {
	/** 商品情報 */
	private Product product;
	
	/** カート内の商品数 */
	private int quantity;
	
	/**
	 * コンストラクタ（引数無し）
	 * 
	 */
	public CartItem() {}
	
	/**
	 * コンストラクタ（引数あり）
	 * 
	 * @param product 商品情報
	 * @param quantity カート内の商品数
	 */
	public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
	
	/**
	 * 商品情報を取得
	 * 
	 * @return 商品情報
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * 商品情報を設定
	 * 
	 * @param product 商品情報
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/**
	 * カート内の商品数を取得
	 * 
	 * @return カート内の商品数
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * カート内の商品数を設定
	 * 
	 * @param quantity カート内の商品数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
