package model.entity;

import java.time.LocalDateTime;

/**
 * 通知エンティティ
 */
public class Notification {
	/** 通知ID */
	private Integer notificationId;
	
	/** 会員ID */
	private Integer userId;
	
	/** 通知タイトル */
	private String title;
	
	/** 本文 */
	private String message;
	
	/** 通知作成日時 */
	private LocalDateTime createDate;
	
	/** 通知更新日時 */
	private LocalDateTime modDate;
	
	/** 通知削除日時j */
	private LocalDateTime delDate;
	
	/** 削除フラグ */
	private boolean delFlag;
	
	/**
	 * コンストラクタ（引数無し）
	 */
	public Notification() {}
	
	/**
	 * コンストラクタ（引数あり）
	 * 
	 * @param notificationId 通知ID
	 * @param userId 会員ID
	 * @param title 通知タイトル
	 * @param message 本文
	 * @param createDate 通知作成日時
	 * @param modDate 通知更新日時
	 * @param delDate 通知削除日時
	 * @param delFlag 削除フラグ
	 */
	public Notification(int notificationId, int userId, String title, String message,
						LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate,
						boolean delFlag) {
		this.notificationId = notificationId;
		this.userId = userId;
		this.title = title;
		this.message = message;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
	}
	
	/**
	 * 通知IDを取得
	 * 
	 * @return 通知ID
	 */
	public Integer getNotificationId() {
		return notificationId;
	}
	
	/**
	 * 通知IDを設定
	 * 
	 * @param notificationId 通知ID
	 */
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	
	/**
	 * 会員IDを取得
	 * 
	 * @return 会員ID
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 会員IDを設定
	 * 
	 * @param userId 会員ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 通知タイトルを取得
	 * 
	 * @return 通知タイトル
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 通知タイトルを設定
	 * 
	 * @param title 通知タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 本文を取得
	 * 
	 * @return 本文
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 本文を設定
	 * 
	 * @param message 本文
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 通知作成日時を取得
	 * 
	 * @return 通知作成日時
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
	 * 通知作成日時を設定
	 * 
	 * @param createDate 通知作成日時
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 通知更新日時を取得
	 * 
	 * @return 通知更新日時
	 */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
	 * 通知更新日時を設定
	 * 
	 * @param modDate 通知更新日時
	 */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * 通知削除日時を取得
	 * 
	 * @return 更新作成日時
	 */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
	 * 通知削除日時を設定
	 * 
	 * @param delDate 通知削除日時
	 */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
	 * 削除フラグを取得
	 * 
	 * @return 削除フラグ
	 */
	public boolean isDelFlag() {
		return delFlag;
	}
	
	/**
	 * 削除フラグを設定
	 * 
	 * @param delFlag 削除フラグ
	 */
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
