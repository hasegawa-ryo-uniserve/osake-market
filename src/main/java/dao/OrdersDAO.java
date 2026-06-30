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

import model.PaymentMethod;
import model.entity.Order;

/**
 * 注文DAOクラス
 */
public class OrdersDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/** 
	 * 注文情報をDBに登録する(注文トランザクション②)
	 * 
	 * @param conn データベース接続
	 * @param userId 会員ID
	 * @param paymentMethod 支払方法
	 * @param totalAmount 合計金額
	 * @return 注文ID
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public int registerOrder(Connection conn, int userId, int paymentMethod, int totalAmount, int status) {
		int orderId = 0;
		// データベースへ接続
		try {
			// シーケンス取得
			String seqSql = "SELECT seq_order.NEXTVAL FROM dual";

			PreparedStatement seqStmt = conn.prepareStatement(seqSql);
			ResultSet rs = seqStmt.executeQuery();

			if (rs.next()) {
				orderId = rs.getInt(1);
			} else {
				return -1;
			}

			// SQLを準備
			String sql = "INSERT INTO orders(order_id, user_id, payment_method, total_amount, "
					+ "status, cancel, del_flag) "
					+ "VALUES(?, ?, ?, ?, ?, 0, 0)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);
			pStmt.setInt(2, userId);
			pStmt.setInt(3, paymentMethod);
			pStmt.setInt(4, totalAmount);
			pStmt.setInt(5, status);

			// SQLを実行し、結果を取得
			int result = pStmt.executeUpdate();

			if (result != 1) {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return orderId;
	}

	/** 
	 * 注文一覧を取得
	 * 
	 * @param userId 会員ID
	 * @return 注文リスト
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public List<Order> findByUserId(int userId, int offset, int pageSize) {
		List<Order> orderList = new ArrayList<>();
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT order_id, user_id, payment_method, total_amount, status, "
						+ "cancel, create_date, mod_date, del_date, del_flag "
						+ "FROM orders "
						+ "WHERE user_id = ? "
						+ "ORDER BY create_date DESC "
						+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, offset);
			pStmt.setInt(3, pageSize);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// 注文が存在したらデータを取得
				// その注文を表すorderインスタンスを生成
				Integer orderId = rs.getInt("order_id");
				Integer paymentMethodInt = rs.getInt("payment_method");
				PaymentMethod paymentMethod = switch (paymentMethodInt) {
					case 1 -> PaymentMethod.CREDIT_CARD;
					case 2 -> PaymentMethod.BANK;
					default -> PaymentMethod.CREDIT_CARD;
				};
				Integer totalAmount = rs.getInt("total_amount");
				Integer status = rs.getInt("status");
				int cancel = rs.getInt("cancel");
				Timestamp ts = rs.getTimestamp("create_date");
				LocalDateTime createDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("del_date");
				LocalDateTime delDate = (ts != null) ? ts.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				Order order = new Order(orderId, userId, paymentMethod, totalAmount, status,
						cancel, createDate, modDate, delDate, delFlag);

				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return orderList;
	}

	/** 
	 * 注文IDをもとに商品を取得
	 * 
	 * @param orderId 注文ID
	 * @return 注文情報
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public Order findByOrderId(int orderId) {
		Order order = null;
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SQLを準備
			String sql = "SELECT order_id, user_id, payment_method, total_amount, status, "
						+ "cancel, create_date, mod_date, del_date, del_flag "
						+ "FROM orders "
						+ "WHERE order_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// ユーザーが存在したらデータを取得
				// そのユーザーを表すUserインスタンスを生成
				Integer userId = rs.getInt("user_id");
				Integer paymentMethodInt = rs.getInt("payment_method");
				PaymentMethod paymentMethod = switch (paymentMethodInt) {
					case 1 -> PaymentMethod.CREDIT_CARD;
					case 2 -> PaymentMethod.BANK;
					default -> PaymentMethod.CREDIT_CARD;
				};
				Integer totalAmount = rs.getInt("total_amount");
				Integer status = rs.getInt("status");
				int cancel = rs.getInt("cancel");
				Timestamp ts = rs.getTimestamp("create_date");
				LocalDateTime createDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("del_date");
				LocalDateTime delDate = (ts != null) ? ts.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				order = new Order(orderId, userId, paymentMethod, totalAmount, status,
						cancel, createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}

	/** 
	 * 注文IDをもとに商品を取得(キャンセルトランザクション①)
	 * 
	 * @param conn データベース接続
	 * @param orderId 注文ID
	 * @return 注文情報
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public Order findByOrderIdForCancel(Connection conn, int orderId) {
		Order order = null;
		// データベースへ接続
		try {

			// SQLを準備
			String sql = "SELECT order_id, user_id, payment_method, total_amount, status, "
						+ "cancel, create_date, mod_date, del_date, del_flag "
						+ "FROM orders "
						+ "WHERE order_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				// ユーザーが存在したらデータを取得
				// そのユーザーを表すUserインスタンスを生成
				Integer userId = rs.getInt("user_id");
				Integer paymentMethodInt = rs.getInt("payment_method");
				PaymentMethod paymentMethod = switch (paymentMethodInt) {
					case 1 -> PaymentMethod.CREDIT_CARD;
					case 2 -> PaymentMethod.BANK;
					default -> PaymentMethod.CREDIT_CARD;
				};
				Integer totalAmount = rs.getInt("total_amount");
				Integer status = rs.getInt("status");
				int cancel = rs.getInt("cancel");
				Timestamp ts = rs.getTimestamp("create_date");
				LocalDateTime createDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("mod_date");
				LocalDateTime modDate = (ts != null) ? ts.toLocalDateTime() : null;
				ts = rs.getTimestamp("del_date");
				LocalDateTime delDate = (ts != null) ? ts.toLocalDateTime() : null;
				int delFlagInt = rs.getInt("del_flag");
				boolean delFlag = false;
				if (delFlagInt == 0) {
					delFlag = false;
				} else if (delFlagInt == 1) {
					delFlag = true;
				}
				order = new Order(orderId, userId, paymentMethod, totalAmount, status,
						cancel, createDate, modDate, delDate, delFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}

	/** 
	 * キャンセルする(キャンセルトランザクション④)
	 * 
	 * @param conn データベース接続
	 * @param orderId 注文ID
	 * @return 注文情報
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean updateCancel(Connection conn, int orderId) {
		// データベースへ接続
		try {

			// SQLを準備
			String sql = "UPDATE orders "
						+ "SET cancel = 1, mod_date = CURRENT_TIMESTAMP "
						+ "WHERE order_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);

			// SQLを実行し、結果を取得
			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ページネーション用に件数取得
	 * @param userId 会員ID
	 * @return 注文件数
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
			          	+ "FROM orders "
			         	+ "WHERE user_id = ?";
			
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
	
	
}
