package model;

import java.time.LocalDateTime;

/**
 * カテゴリーエンティティ
 */
public class Category {
	/** カテゴリーID */
	private Integer categoryId;
	
	/** カテゴリー名 */
	private String categoryName;
	
	/** カテゴリー作成日時 */
	private LocalDateTime createDate;
	
	/** カテゴリー更新日時 */
	private LocalDateTime modDate;
	
	/** カテゴリー削除日時 */
	private LocalDateTime delDate;
	
	/** カテゴリー削除フラグ（true:削除済み） */
	private Boolean delFlag;
	
	/**
	 * コンストラクタ(引数無し)
	 */
	public Category() {}
	
	/**
     * コンストラクタ
     *
     * @param categoryId カテゴリーID
     * @param categoryName カテゴリー名
     * @param createDate 作成日時
     * @param modDate 更新日時
     * @param delDate 削除日時
     * @param delFlag 削除フラグ
     */
	public Category(Integer categoryId, String categoryName, LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
     * カテゴリーIDを取得する
     *
     * @return カテゴリーID
     */
	public Integer getcategoryId() {
		return categoryId;
	}
	
	/**
     * カテゴリーIDを設定する
     *
     * @param categoryId カテゴリーID
     */
	public void setcategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
     * カテゴリー名を取得する
     *
     * @return カテゴリー名
     */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
     * カテゴリー名を設定する
     *
     * @param categoryName カテゴリー名
     */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
     * カテゴリー作成日時を取得する
     *
     * @return カテゴリー作成日時
     */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
     * カテゴリー作成日時を設定する
     *
     * @param createDate カテゴリー作成日時
     */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
     * カテゴリー更新日時を取得する
     *
     * @return カテゴリー更新日時
     */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
     * カテゴリー更新日時を設定する
     *
     * @param modDate カテゴリー更新日時
     */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
     * カテゴリー削除日時を取得する
     *
     * @return カテゴリー削除日時
     */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
     * カテゴリー削除日時を設定する
     *
     * @param delDate カテゴリー削除日時
     */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
     * カテゴリー削除フラグを取得する
     *
     * @return カテゴリー削除フラグ
     */
	public Boolean getDelFlag() {
		return delFlag;
	}
	
	/**
     * カテゴリー削除フラグを設定する
     *
     * @param delFlag カテゴリー削除フラグ
     */
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
}
