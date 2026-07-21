package model.entity;

import java.time.LocalDateTime;

/**
 * お気に入りエンティティ
 */
public class Favorite {
	/** お気に入りID */
	private Integer favoriteId;
	
	/** 会員ID */
	private Integer userId;
	
	/** 商品ID */
	private Integer productId;
	
	/** お気に入り作成日時 */
	private LocalDateTime createDate;
	
	/** お気に入り更新日時 */
	private LocalDateTime modDate;
	
	/** お気に入り削除日時 */
	private LocalDateTime delDate;
	
	/** お気に入り削除フラグ（true:削除済み） */
	private Boolean delFlag;
	
	/**
	 * コンストラクタ(引数無し)
	 */
	public Favorite() {}
	
	/**
     * コンストラクタ
     *
     * @param favoriteId お気に入りID
     * @param userId 会員ID
     * @param productId 商品ID
     * @param createDate お気に入り作成日時
     * @param modDate お気に入り更新日時
     * @param delDate お気に入り削除日時
     * @param delFlag お気に入り削除フラグ
     */
	public Favorite(int favoriteId, int userId, int productId, LocalDateTime createDate,
						LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.favoriteId = favoriteId;
		this.userId = userId;
		this.productId = productId;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
	 * お気に入りIDの取得
	 * 
	 * @return お気に入りID
	 */
	public Integer getFavoriteId() {
		return favoriteId;
	}
	
	/**
	 * お気に入りIDの設定
	 * 
	 * @param favoriteId お気に入りIDの設定
	 */
	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	/**
	 * 会員IDの取得
	 * 
	 * @return 会員ID
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 会員IDの設定
	 * 
	 * @param userId 会員ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 商品IDの取得
	 * 
	 * @return 商品ID
	 */
	public Integer getProductId() {
		return productId;
	}
	
	/**
	 * 商品IDの設定
	 * 
	 * @param productId 商品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	/**
	 * お気に入り作成日時の取得
	 * 
	 * @return お気に入り作成日時
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
	 * お気に入り作成日時の設定
	 * 
	 * @param createDate お気に入り作成日時
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * お気に入り更新日時の取得
	 * 
	 * @return お気に入り更新日時
	 */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
	 * お気に入り更新日時の設定
	 * 
	 * @param modDate お気に入り更新日時
	 */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * お気に入り削除日時の取得
	 * 
	 * @return お気に入り削除日時
	 */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
	 * お気に入り削除日時の設定
	 * 
	 * @param delDate お気に入り削除日時
	 */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
	 * お気に入り削除フラグの取得
	 * 
	 * @return お気に入り削除フラグ
	 */
	public Boolean isDelFlag() {
		return delFlag;
	}
	
	/**
	 * お気に入り削除フラグの設定
	 * 
	 * @param delFlag お気に入り削除フラグ
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
