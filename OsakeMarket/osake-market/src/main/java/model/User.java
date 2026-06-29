package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ユーザーエンティティ
 */
public class User {
	/** ユーザーID */
	private Integer userId;
	
	/** 姓 */
	private String sei;
	
	/** 名 */
	private String mei;
	
	/** 生年月日 */
	private LocalDate birthday;
	
	/** 性別 */
	private Gender gender;
	
	/** 郵便番号 */
	private String postalCode;
	
	/** 都道府県 */
	private String prefecture;
	
	/** 住所 */
	private String address;
	
	/** 建物名・部屋番号 */
	private String building;
	
	/** 電話番号 */
	String phoneNumber;
	
	/** メールアドレス */
	private String mail;
	
	/** パスワード */
	private String password;
	
	/** パスワードリセットトークン */
	private String token;
	
	/** パスワードリセットトークンの有効期限 */
	private LocalDateTime expire;
	
	/** ユーザー作成日時 */
	private LocalDateTime createDate;
	
	/** ユーザー更新日時 */
	private LocalDateTime modDate;
	
	/** ユーザー削除日時 */
	private LocalDateTime delDate;
	
	/** ユーザー削除フラグ（true:削除済み） */
	private boolean delFlag;
	
	/**
	 * コンストラクタ(引数無し)
	 */
	public User() {}
	
	/**
     * コンストラクタ
     *
     * @param userId ユーザーID
     * @param sei 姓
     * @param mei 名
     * @param birthday 生年月日
     * @param gender 性別
     * @param postalCode 郵便番号
     * @param prefecture 都道府県
     * @param address 住所
     * @param building 建物名・部屋番号
     * @param phoneNumber 電話番号
     * @param mail メールアドレス
     * @param password パスワード
     * @param token パスワードリセットトークン
     * @param expire トークン有効期限
     * @param createDate 作成日時
     * @param modDate 更新日時
     * @param delDate 削除日時
     * @param delFlag 削除フラグ
     */
	public User(Integer userId, String sei, String mei, LocalDate birthday, Gender gender, String postalCode,
			String prefecture, String address, String building, String phoneNumber, String mail, String password, String token, LocalDateTime expire,
			LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.userId = userId;
		this.sei = sei;
		this.mei = mei;
		this.birthday = birthday;
		this.gender = gender;
		this.postalCode = postalCode;
		this.prefecture = prefecture;
		this.address = address;
		this.building = building;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.password = password;
		this.token = token;
		this.expire = expire;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
     * ユーザーIDを取得する
     *
     * @return ユーザーID
     */
	public Integer getUserId() {
		return userId;
	}
	
	/**
     * ユーザーIDを設定する
     *
     * @param userId ユーザーID
     */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
     * 姓を取得する
     *
     * @return 姓
     */
	public String getSei() {
		return sei;
	}
	
	/**
     * 姓を設定する
     *
     * @param sei 姓
     */
	public void setSei(String sei) {
		this.sei = sei;
	}
	
	/**
     * 名を取得する
     *
     * @return 名
     */
	public String getMei() {
		return mei;
	}
	
	/**
     * 名を設定する
     *
     * @param mei 名
     */
	public void setMei(String mei) {
		this.mei = mei;
	}
	
	/**
     * 生年月日を取得する
     *
     * @return 生年月日
     */
	public LocalDate getBirthday() {
		return birthday;
	}
	
	/**
     * 生年月日を設定する
     *
     * @param birthday 生年月日
     */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	/**
     * 性別を取得する
     *
     * @return 性別
     */
	public Gender getGender() {
		return gender;
	}
	
	/**
     * 性別を設定する
     *
     * @param gender 性別
     */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	/**
     * 郵便番号を取得する
     *
     * @return 郵便番号
     */
	public String getPostalCode() {
		return postalCode;
	}
	
	/**
     * 郵便番号を設定する
     *
     * @param postalCode 郵便番号
     */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/**
     * 都道府県を取得する
     *
     * @return 都道府県
     */
	public String getPrefecture() {
		return prefecture;
	}
	
	/**
     * 都道府県を設定する
     *
     * @param prefecture 都道府県
     */
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	
	/**
     * 住所を取得する
     *
     * @return 住所
     */
	public String getAddress() {
		return address;
	}
	
	/**
     * 住所を設定する
     *
     * @param address 住所
     */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
     * 建物名・部屋番号を取得する
     *
     * @return 建物名・部屋番号
     */
	public String getBuilding() {
		return building;
	}
	
	/**
     * 建物名・部屋番号を設定する
     *
     * @param building 建物名・部屋番号
     */
	public void setBuilding(String building) {
		this.building = building;
	}
	
	/**
     * 電話番号を取得する
     *
     * @return 電話番号
     */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
     * 電話番号を設定する
     *
     * @param phoneNumber 電話番号
     */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
     * メールアドレスを取得する
     *
     * @return メールアドレス
     */
	public String getMail() {
		return mail;
	}
	
	/**
     * メールアドレスを設定する
     *
     * @param mail メールアドレス
     */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
     * パスワードを取得する
     *
     * @returnパスワード
     */
	public String getPassword() {
		return password;
	}
	
	/**
     * パスワードを設定する
     *
     * @param password パスワード
     */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
     *パスワードリセットトークンを取得する
     *
     * @return パスワードリセットトークン
     */
	public String getToken() {
		return token;
	}
	
	/**
     * パスワードリセットトークンを設定する
     *
     * @param token パスワードリセットトークン
     */
	public void setToken(String token) {
		this.token = token;
	}
	
	/**
     * パスワードリセットトークンの有効期限を取得する
     *
     * @return パスワードリセットトークンの有効期限
     */
	public LocalDateTime getExpire() {
		return expire;
	}
	
	/**
     * パスワードリセットトークンの有効期限を設定する
     *
     * @param expire パスワードリセットトークンの有効期限
     */
	public void setExpire(LocalDateTime expire) {
		this.expire = expire;
	}
	
	/**
     * ユーザー作成日時を取得する
     *
     * @return ユーザー作成日時
     */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
     * ユーザー作成日時を設定する
     *
     * @param createDate ユーザー作成日時
     */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
     * ユーザー更新日時を取得する
     *
     * @return ユーザー更新日時
     */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
     * ユーザー更新日時を設定する
     *
     * @param modDate ユーザー更新日時
     */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
     * ユーザー削除日時を取得する
     *
     * @return ユーザー削除日時
     */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
     * ユーザー削除日時を設定する
     *
     * @param delDate ユーザー削除日時
     */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
     * ユーザー削除フラグを取得する
     *
     * @return ユーザー削除フラグ
     */
	public boolean getDelFlag() {
		return delFlag;
	}
	
	/**
     * ユーザー削除フラグを設定する
     *
     * @param delFlag ユーザー削除フラグ
     */
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
}
