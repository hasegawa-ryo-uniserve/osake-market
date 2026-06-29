package model.logic.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import dao.OrderItemsDAO;
import dao.OrdersDAO;
import dao.ProductsDAO;
import model.entity.Order;
import model.entity.OrderItem;

/**
 * 注文キャンセルロジッククラス
 */
public class OrderCancelLogic {

	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/**
	 * 注文をキャンセルする
	 * 
	 * @param orderId 注文ID
	 * @return 実行結果
	 */
	public boolean cancel(int orderId) {
		// JDBCドライバを読み込む
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		// データベースへ接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			
			// オートコミット解除
			conn.setAutoCommit(false);
			
			// DAOクラスの生成
			OrdersDAO ordersDao = new OrdersDAO();
			OrderItemsDAO orderItemsDao = new OrderItemsDAO();
			ProductsDAO productsDao = new ProductsDAO();
			
			// 注文情報が存在するか確認
			Order order = ordersDao.findByOrderIdForCancel(conn, orderId);
			if(order == null) {
				conn.rollback();
				return false;
			}
			
			// 注文ステータスを確認(発送依頼済み、または発送済みの場合はキャンセル不可)
			if(order.getStatus() == 2 || order.getStatus() == 3) {
				conn.rollback();
				return false;
			}
			
			// 注文明細を取得
			order = orderItemsDao.findByOrderIdForCancel(conn, orderId);
			
			// 在庫数を増やす
			List<OrderItem> orderItemList = order.getOrderItemList();
			for(OrderItem orderItem : orderItemList) {
				// 商品IDと注文数を取得
				int productId = orderItem.getProductId();
				int quantity = orderItem.getQuantity();
				
				// 在庫数増加処理
				boolean result = productsDao.increaseStock(conn, productId, quantity);
				
				if(!result) {
					conn.rollback();
					return false;
				}
			}
			
			// orderテーブルのキャンセルステータスを更新する
			boolean result = ordersDao.updateCancel(conn, orderId);
			
			if(!result) {
				conn.rollback();
				return false;
			}
			
			conn.commit();
			
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
