package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.entity.Notification;

/**
 * 通知DAOクラス
 */
public class NotificationsDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";
	
	/**
	 * ページネーション用に件数取得
	 * @param userId 会員ID
	 * @return 件数
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public int countAll(int userId) {
		int count = 0;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT COUNT(*) "
			          	+ "FROM notifications "
			         	+ "WHERE user_id = ? OR user_id IS NULL";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {
				// 1列目の値を取得
	            count = rs.getInt(1);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	/** 
	 * 通知一覧を取得する
	 * 
	 * @param userId 会員ID
	 * @param offset そのページより前の件数をスキップ
	 * @param pageSize 1ページにおける件数
	 * @return 通知一覧
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Notification> findAll(int userId, int offset, int pageSize) {
		List<Notification> notificationList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT notification_id, user_id, title, message, create_date, "
						+ "mod_date, del_date, del_flag "
						+ "FROM notifications "
						+ "WHERE user_id = ? OR user_id IS NULL "
						+ "ORDER BY create_date DESC "
						+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, offset);
			pStmt.setInt(3, pageSize);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// 通知が存在したらデータを取得
				// その通知を表すnotificationインスタンスを生成
				Integer notificationId = rs.getInt("notification_id");

				String title = rs.getString("title");
				Integer targetUserId = (Integer)rs.getInt("user_id");
				String message = rs.getString("message");
				Timestamp tsCreate = rs.getTimestamp("create_date");
				LocalDateTime createDate = (tsCreate != null) ? tsCreate.toLocalDateTime() : null;
				Timestamp tsMod = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (tsMod != null) ? tsMod.toLocalDateTime() : null;
				Timestamp tsDel = rs.getTimestamp("del_date");
				LocalDateTime delDate = (tsDel != null) ? tsDel.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				Notification notification = new Notification(notificationId, targetUserId, title, message, 
															createDate, modDate, delDate, delFlag);
				notificationList.add(notification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return notificationList;
	}
	
	/** 
	 * 通知IDにもとづいて通知を取得する
	 * 
	 * @param notificationId 通知ID
	 * @return 通知
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public Notification findByNotificationId(int notificationId) {
		Notification notification = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT notification_id, user_id, title, message, create_date, "
						+ "mod_date, del_date, del_flag "
						+ "FROM notifications "
						+ "WHERE notification_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, notificationId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// 通知が存在したらデータを取得
				// その通知を表すNotificationインスタンスを生成
				Integer userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String message = rs.getString("message");
				Timestamp tsCreate = rs.getTimestamp("create_date");
				LocalDateTime createDate = (tsCreate != null) ? tsCreate.toLocalDateTime() : null;
				Timestamp tsMod = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (tsMod != null) ? tsMod.toLocalDateTime() : null;
				Timestamp tsDel = rs.getTimestamp("del_date");
				LocalDateTime delDate = (tsDel != null) ? tsDel.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				notification = new Notification(notificationId, userId, title, message, 
												createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return notification;
	}
}
