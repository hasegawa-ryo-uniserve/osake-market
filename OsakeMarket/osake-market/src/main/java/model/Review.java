package model;

import java.time.LocalDateTime;

/**
 * レビューエンティティ
 */
public class Review {
	/** レビューID */
	private Integer reviewId;
	
	/** ユーザーID */
	private Integer userId;
	
	/** 商品ID */
	private Integer productId;
	
	/** 星評価 */
	private Integer star;
	
	/** レビューコメント */
	private String reviewText;
	
	/** ユーザー */
	private User user;
	
	/** レビュー作成日時 */
	private LocalDateTime createDate;
	
	/** レビュー更新日時 */
	private LocalDateTime modDate;
	
	/** レビュー削除日時 */
	private LocalDateTime delDate;
	
	/** レビュー削除フラグ（true:削除済み） */
	private Boolean delFlag;
	
	/**
	 * コンストラクタ(引数無し)
	 */
	public Review() {}
	
	/**
     * コンストラクタ
     * 
     * @param reviewId レビューID
     * @param userID ユーザーID
     * @param productId 商品ID
     * @param star 星評価
     * @param reviewText レビューコメント
     * @param createDate 作成日時
     * @param modDate 更新日時
     * @param delDate 削除日時
     * @param delFlag 削除フラグ
     */
	public Review(int reviewId, int userId, int productId, int star, String reviewText,
			LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.productId = productId;
		this.star = star;
		this.reviewText = reviewText;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
	 * レビューIDを取得
	 * 
	 * @return レビューID
	 */
	public Integer getReviewId() {
		return reviewId;
	}
	
	/**
	 * レビューIDを設定
	 * 
	 * @param reviewId レビューID
	 */
	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}
	
	/**
	 * ユーザーIDを取得
	 * 
	 * @return ユーザーID
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * ユーザーIDを設定
	 * 
	 * @param userId ユーザーID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * 商品Iを設定
	 * 
	 * @param productId 商品I
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	/**
	 * 星評価を取得
	 * 
	 * @return 星評価
	 */
	public Integer getStar() {
		return star;
	}
	
	/**
	 * 星評価を設定
	 * 
	 * @param star 星評価
	 */
	public void setStar(Integer star) {
		this.star = star;
	}
	
	/**
	 * レビューコメントを取得
	 * 
	 * @return レビューコメント
	 */
	public String getReviewText() {
		return reviewText;
	}
	
	/**
	 * レビューコメントを設定
	 * 
	 * @param reviewText レビューコメント
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	/**
	 * ユーザーを取得
	 * 
	 * @return ユーザー
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * ユーザーを設定
	 * 
	 * @param user ユーザー
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * 作成日時を取得
	 * 
	 * @return 作成日時
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
	 * 作成日時を設定
	 * 
	 * @param createDate 作成日時
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 更新日時を取得
	 * 
	 * @return 更新日時
	 */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
	 * 更新日時を設定
	 * 
	 * @param modDate 更新日時
	 */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * 削除日時を取得
	 * 
	 * @return 削除日時
	 */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
	 * 削除日時を設定
	 * 
	 * @param delDate 削除日時
	 */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
	 * 削除フラグを取得
	 * 
	 * @return 削除フラグ
	 */
	public Boolean getDelFlag() {
		return delFlag;
	}
	
	/**
	 * 削除フラグを設定
	 * 
	 * @param delFlag 削除フラグ
	 */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
}
