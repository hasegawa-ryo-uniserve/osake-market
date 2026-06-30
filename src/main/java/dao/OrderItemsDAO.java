package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.PaymentMethod;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.Product;

/**
 * 注文明細DAOクラス
 */
public class OrderItemsDAO {
	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/** 
	 * 注文情報をDBに登録する(注文トランザクション④)
	 * 
	 * @param conn データベース接続
	 * @param orderId 注文ID
	 * @param productId 商品ID
	 * @param quantity 注文数
	 * @param cart カート
	 * @return 実行結果
	 * @throws ClassNotFoundException 
	 *         ドライバクラスが見つからなかった場合 
	 * @throws SQLException 
	 *         DB接続に失敗した場合 
	 */
	public boolean registerOrderItem(Connection conn, int orderId, int productId, int quantity) {
		// データベースへ接続
		try {
			// SQLを準備
			String sql = "INSERT INTO order_items(order_item_id, order_id, product_id, quantity, del_flag) "
						+ "VALUES(seq_order_item.NEXTVAL, ?, ?, ?, 0)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);
			pStmt.setInt(2, productId);
			pStmt.setInt(3, quantity);

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
	 * 注文IDをもとに注文明細を取得
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
			String sql = "SELECT oi.order_item_id, oi.order_id, o.payment_method, o.total_amount,"
						+ "o.status, o.cancel, oi.product_id, p.category_id, "
						+ "p.product_name, p.price, p.description, p.image_file, oi.quantity, "
						+ "oi.create_date, oi.mod_date, oi.del_date, oi.del_flag "
						+ "FROM order_items oi "
						+ "INNER JOIN orders o "
						+ "ON oi.order_id = o.order_id "
						+ "INNER JOIN products p "
						+ "ON oi.product_id = p.product_id "
						+ "WHERE oi.order_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				if(order == null) {
					order = new Order();
					// 注文情報について
					Integer paymentMethodInt = rs.getInt("payment_method");
					PaymentMethod paymentMethod = switch (paymentMethodInt) {
						case 1 -> PaymentMethod.CREDIT_CARD;
						case 2 -> PaymentMethod.BANK;
						default -> PaymentMethod.CREDIT_CARD;
					};
					Integer totalAmount = rs.getInt("total_amount");
					Integer status = rs.getInt("status");
					int cancel = rs.getInt("cancel");
					
					order.setOrderId(orderId);
					order.setPaymentMethod(paymentMethod);
					order.setTotalAmount(totalAmount);
					order.setStatus(status);
					order.setCancel(cancel);
					
					order.setOrderItemList(new ArrayList<>());
				}
				
				
				// 注文明細について
				OrderItem orderItem = new OrderItem();
				Integer orderItemId = rs.getInt("order_item_id");
				Integer quantity = rs.getInt("quantity");
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
				orderItem.setOrderItemId(orderItemId);
				orderItem.setQuantity(quantity);
				orderItem.setCreateDate(createDate);
				orderItem.setModDate(modDate);
				orderItem.setDelDate(delDate);
				orderItem.setDelFlag(delFlag);
				
				// 商品について
				Product product = new Product();
				Integer productId = rs.getInt("product_id");
				String productName = rs.getString("product_name");
				Integer price = rs.getInt("price");
				String imageFile = rs.getString("image_file");
				
				product.setProductId(productId);
				product.setProductName(productName);
				product.setPrice(price);
				product.setImageFile(imageFile);
				
				orderItem.setProduct(product);
				
				order.getOrderItemList().add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}
	
	/** 
	 * 注文IDをもとに注文明細を取得(キャンセルトランザクション②)
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
			String sql = "SELECT oi.order_item_id, oi.order_id, o.payment_method, o.total_amount,"
						+ "o.status, o.cancel, oi.product_id, p.category_id, "
						+ "p.product_name, p.price, p.description, p.image_file, oi.quantity, "
						+ "oi.create_date, oi.mod_date, oi.del_date, oi.del_flag "
						+ "FROM order_items oi "
						+ "INNER JOIN orders o "
						+ "ON oi.order_id = o.order_id "
						+ "INNER JOIN products p "
						+ "ON oi.product_id = p.product_id "
						+ "WHERE oi.order_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, orderId);

			// SQLを実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				if(order == null) {
					order = new Order();
					// 注文情報について
					Integer paymentMethodInt = rs.getInt("payment_method");
					PaymentMethod paymentMethod = switch (paymentMethodInt) {
						case 1 -> PaymentMethod.CREDIT_CARD;
						case 2 -> PaymentMethod.BANK;
						default -> PaymentMethod.CREDIT_CARD;
					};
					Integer totalAmount = rs.getInt("total_amount");
					Integer status = rs.getInt("status");
					int cancel = rs.getInt("cancel");
					
					order.setOrderId(orderId);
					order.setPaymentMethod(paymentMethod);
					order.setTotalAmount(totalAmount);
					order.setStatus(status);
					order.setCancel(cancel);
					
					order.setOrderItemList(new ArrayList<>());
				}
				
				
				// 注文明細について
				OrderItem orderItem = new OrderItem();
				Integer orderItemId = rs.getInt("order_item_id");
				Integer productId = rs.getInt("product_id");
				Integer quantity = rs.getInt("quantity");
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
				orderItem.setOrderItemId(orderItemId);
				orderItem.setProductId(productId);
				orderItem.setQuantity(quantity);
				orderItem.setCreateDate(createDate);
				orderItem.setModDate(modDate);
				orderItem.setDelDate(delDate);
				orderItem.setDelFlag(delFlag);
				
				// 商品について
				Product product = new Product();
				String productName = rs.getString("product_name");
				Integer price = rs.getInt("price");
				String imageFile = rs.getString("image_file");
				
				product.setProductId(productId);
				product.setProductName(productName);
				product.setPrice(price);
				product.setImageFile(imageFile);
				
				orderItem.setProduct(product);
				
				order.getOrderItemList().add(orderItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return order;
	}
}
