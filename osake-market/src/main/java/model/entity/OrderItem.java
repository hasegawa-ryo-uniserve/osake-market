package model.entity;

import java.time.LocalDateTime;

/**
 * 注文明細エンティティ
 */
public class OrderItem {
	/** 注文明細ID */
	private Integer orderItemId;
	
	/** 注文ID */
	private Integer orderId;
	
	/** 商品ID */
	private Integer productId;
	
	/** 注文数 */
	private Integer quantity;
	
	/** 注文明細作成日時 */
	private LocalDateTime createDate;
	
	/** 注文明細更新日時 */
	private LocalDateTime modDate;
	
	/** 注文明細削除日時 */
	private LocalDateTime delDate;
	
	/** 注文明細フラグ */
	private boolean delFlag;
	
	/** 商品情報 */
	private Product product;
	
	/**
	 * コンストラクタ（引数無し）
	 */
	public OrderItem() {}
	
	/**
	 * コンストラクタ（引数あり）
	 * 
	 * @param orderItemId 注文明細ID
	 * @param orderId 注文ID
	 * @param productId 商品ID
	 * @param quantity 注文数
	 * @param createDate 注文明細作成日時
	 * @param modDate 注文明細更新日時
	 * @param delDate 注文明細削除日時
	 * @param delFlag 注文明細削除フラグ
	 */
	public OrderItem(int orderItemId, int orderId, int productId, int quantity,
			LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
	 * 注文明細IDを取得
	 * 
	 * @return 注文明細ID
	 */
	public Integer getOrderItemId() {
		return orderItemId;
	}
	
	/**
	 * 注文明細IDを設定
	 * 
	 * @param orderItemId 注文明細ID
	 */
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	
	/**
	 * 注文IDを取得
	 * 
	 * @return 注文ID
	 */
	public Integer getOrderId() {
		return orderId;
	}
	
	/**
	 * 注文IDを設定
	 * 
	 * @param orderId 注文ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 商品IDを取得
	 * 
	 * @return 商品ID
	 */
	public Integer getProductId() {
		return productId;
	}
	
	/**
	 * 商品IDを設定
	 * 
	 * @param productId 商品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	/**
	 * 注文数を取得
	 * 
	 * @return 注文数
	 */
	public Integer getQuantity() {
		return quantity;
	}
	
	/**
	 * 注文数を設定
	 * 
	 * @param quantity 注文数
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 注文明細作成日時を取得
	 * 
	 * @return 注文明細作成日時
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
	 * 注文明細作成日時を設定
	 * 
	 * @param createDate 注文明細作成日時
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 注文明細更新日時を取得
	 * 
	 * @return 注文明細更新日時
	 */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
	 * 注文明細更新日時を設定
	 * 
	 * @param modDate 注文明細更新日時
	 */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * 注文明細削除日時を取得
	 * 
	 * @return 注文明細削除日時
	 */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
	 * 注文明細削除日時を設定
	 * 
	 * @param delDate 注文明細削除日時
	 */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
	 * 注文明細削除フラグを取得
	 * 
	 * @return 注文明細削除フラグ
	 */
	public boolean isDelFlag() {
		return delFlag;
	}
	
	/**
	 * 注文明細削除フラグを設定
	 * 
	 * @param delFlag 注文明細削除フラグ
	 */
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
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
	
	
}
