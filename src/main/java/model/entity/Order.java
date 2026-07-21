package model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.PaymentMethod;

/**
 * 注文エンティティ
 */
public class Order {
	/** 注文ID */
	private Integer orderId;
	
	/** 会員ID */
	private Integer userId;
	
	/** 支払方法 */
	private PaymentMethod paymentMethod;
	
	/** 合計金額 */
	private Integer totalAmount;
	
	/** 注文ステータス */
	private Integer status;
	
	/** キャンセル */
	private Integer cancel;
	
	/** 注文作成日時 */
	private LocalDateTime createDate;
	
	/** 注文更新日時 */
	private LocalDateTime modDate;
	
	/** 注文削除日時 */
	private LocalDateTime delDate;
	
	/** 注文削除フラグ */
	private boolean delFlag;
	
	/** 注文明細一覧 */
	private List<OrderItem> orderItemList = new ArrayList<>();
	
	/**
	 * コンストラクタ（引数無し）
	 */
	public Order() {}
	
	/**
	 * コンストラクタ（引数あり）
	 * 
	 * @param orderId 注文ID
	 * @param userId 会員ID
	 * @param paymentMethod 支払方法
	 * @param totalAmount 合計金額
	 * @param status 注文ステータス
	 * @param cancel キャンセル
	 * @param createDate 注文作成日時
	 * @param modDate 注文更新日時
	 * @param delDate 注文削除日時
	 * @param delFlag 注文削除フラグ
	 */
	public Order(int orderId, int userId, PaymentMethod paymentMethod, int totalAmount, int status, int cancel,
					LocalDateTime createDate, LocalDateTime modDate, LocalDateTime delDate, boolean delFlag) {
		this.orderId = orderId;
		this.userId = userId;
		this.paymentMethod = paymentMethod;
		this.totalAmount = totalAmount;
		this.status = status;
		this.cancel = cancel;
		this.createDate = createDate;
		this.modDate = modDate;
		this.delDate = delDate;
		this.delFlag = delFlag;
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
	 * 支払方法を取得
	 * 
	 * @return 支払方法
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * 支払方法を設定
	 * 
	 * @param paymentMethod 支払方法
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * 合計金額を取得
	 * 
	 * @return 合計金額
	 */
	public Integer getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * 合計金額を設定
	 * 
	 * @param totalAmount 合計金額
	 */
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * 注文ステータスを取得
	 * 
	 * @return 注文ステータス
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 注文ステータスを設定
	 * 
	 * @param status 注文ステータス
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * キャンセルを取得
	 * 
	 * @return キャンセル
	 */
	public Integer getCancel() {
		return cancel;
	}
	
	/**
	 * キャンセルを設定
	 * 
	 * @param cancel キャンセル
	 */
	public void setCancel(Integer cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * 注文作成日時を取得
	 * 
	 * @return 注文作成日時
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	
	/**
	 * 注文作成日時を設定
	 * 
	 * @param createDate 注文作成日時
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 注文更新日時を取得
	 * 
	 * @return 注文更新日時
	 */
	public LocalDateTime getModDate() {
		return modDate;
	}
	
	/**
	 * 注文更新日時を設定
	 * 
	 * @param modDate 注文更新日時
	 */
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	/**
	 * 注文削除日時を取得
	 * 
	 * @return 注文削除日時
	 */
	public LocalDateTime getDelDate() {
		return delDate;
	}
	
	/**
	 * 注文削除日時を設定
	 * 
	 * @param delDate 注文削除日時
	 */
	public void setDelDate(LocalDateTime delDate) {
		this.delDate = delDate;
	}
	
	/**
	 * 注文削除フラグを取得
	 * 
	 * @return 注文削除フラグ
	 */
	public boolean isDelFlag() {
		return delFlag;
	}
	
	/**
	 * 注文削除日時を取得
	 * 
	 * @return delFlag 注文削除日時
	 */
	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 注文明細リストを取得
	 * 
	 * @return 注文明細リスト
	 */
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	
	/**
	 * 注文明細リストを設定
	 * 
	 * @param orderItemList 注文明細リスト
	 */
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	
}
