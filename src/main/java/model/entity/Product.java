package model.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品エンティティ
 */
public class Product {
	/** 商品ID */
	private Integer productId;
	
	/** カテゴリーID */
	private Integer categoryId;
	
	/** 商品名 */
	private String productName;
	
	/** 価格 */
	private Integer price;
	
	/** 商品説明 */
	private String description;
	
	/** 在庫数 */
	private Integer stock;
	
	/** 商品画像 */
	private String imageFile;
	
	/** 商品作成日時 */
	private LocalDateTime createDate;
	
	/** 商品更新日時 */
	private LocalDateTime modDate;
	
	/** 商品削除日時 */
	private LocalDateTime delDate;
	
	/** 商品削除フラグ（true:削除済み） */
	private Boolean delFlag;
	
	// 画面用オプション
	/** カテゴリ―名 */
	private String categoryName;
	
	/** レビューリスト */
	private List<Review> reviewList;
	
	/** お気に入りに登録されているかどうかのフラグ */
	private boolean favoriteFlag;
	
	/**
	 * コンストラクタ(引数無し)
	 */
	public Product() {}
	
	/**
     * コンストラクタ
     *
     * @param productId 商品ID
     * @param categoryId カテゴリーID
     * @param productName 商品名
     * @param price 価格
     * @param description 商品説明
     * @param stock 在庫数
     * @param imageFile 商品画像
     * @param createDate 作成日時
     * @param modDate 更新日時
     * @param delDate 削除日時
     * @param delFlag 削除フラグ
     */
	public Product(Integer productId, Integer categoryId, String productName, Integer price, String description,
			Integer stock, String imageFile, LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.productId = productId;
		this.categoryId = categoryId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.stock = stock;
		this.imageFile = imageFile;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
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
	 * カテゴリーIDを取得
	 * 
	 * @return カテゴリーID
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
	
	/**
	 * カテゴリーIDを設定
	 * 
	 * @param categoryId カテゴリーID
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 商品名を取得
	 * 
	 * @return 商品名
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * 商品名を設定
	 * 
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 価格を取得
	 * 
	 * @return 価格
	 */
	public Integer getPrice() {
		return price;
	}
	
	/**
	 * 価格を設定
	 * 
	 * @param price 価格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	/**
	 * 商品説明を取得
	 * 
	 * @return 商品説明
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 商品説明を設定
	 * 
	 * @param description 商品説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 在庫数を取得
	 * 
	 * @return 在庫数
	 */
	public Integer getStock() {
		return stock;
	}
	
	/**
	 * 在庫数を設定
	 * 
	 * @param quantity 在庫数
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	/**
	 * 商品画像を取得
	 * 
	 * @return 商品画像
	 */
	public String getImageFile() {
		return imageFile;
	}
	
	/**
	 * 商品画像を設定
	 * 
	 * @param imageFile 商品画像
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	/**
	 * レビューリストを取得
	 * 
	 * @return レビューリスト
	 */
	public List<Review> getReviewList() {
		return reviewList;
	}
	
	/**
	 * レビューリストを設定
	 * 
	 * @param reviewList レビューリスト
	 */
	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
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
	public Boolean isDelFlag() {
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
	
	/**
	 * カテゴリー名を取得
	 * 
	 * @return カテゴリー名
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * カテゴリー名を設定
	 * 
	 * @param categoryName カテゴリー名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * お気に入りフラグの取得
	 * 
	 * @return お気に入りフラグ
	 */
	public boolean isFavoriteFlag() {
		return favoriteFlag;
	}
	
	/**
	 * お気に入りフラグの設定
	 * 
	 * @param favoriteFlag お気に入りフラグ
	 */
	public void setFavoriteFlag(boolean favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
}
