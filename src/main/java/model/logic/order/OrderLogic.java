package model.logic.order;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import dao.OrderItemsDAO;
import dao.OrdersDAO;
import dao.ProductsDAO;
import jp.pay.Payjp;
import jp.pay.model.Charge;
import model.MailSender;
import model.PaymentMethod;
import model.entity.CartItem;

/**
 * 注文ロジッククラス
 */
public class OrderLogic {

	/** ドライバクラス名 */
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	/** 接続するDBのURL */
	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

	/** DB接続するためのユーザ名 */
	private static final String DB_USER = "osake_market";

	/** DB接続するためのパスワード */
	private static final String DB_PASS = "systemsss";

	/**
	 * 注文処理
	 * 
	 * @param cart カート
	 * @param userId ユーザーID
	 * @param paymentMethod 支払方法
	 * @param cardToken カードトークン
	 * @return 実行結果
	 */
	public boolean createOrder(List<CartItem> cart, int userId, String paymentMethod, String cardToken,
								String mail) {
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
			
			// 各種DAOを生成する
			ProductsDAO productsDao = new ProductsDAO();
	        OrdersDAO ordersDao = new OrdersDAO();
	        OrderItemsDAO orderItemsDao = new OrderItemsDAO();
	        
	        // カートに入っている各商品の在庫数を取得する
	        int totalAmount = 0;
	        for(CartItem cartItem : cart) {
	        	int stock = productsDao.getStock(conn, cartItem.getProduct().getProductId());
	        	
	        	// 在庫不足の場合falseを返す
	        	if(stock < cartItem.getQuantity()) {
	        		conn.rollback();
	        		return false;
	        	}
	        	
	        	// 合計金額を求める
	        	int subTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
	        	totalAmount += subTotal;
	        }
	        
	        // 支払情報をenum→intに直す
	        PaymentMethod method = PaymentMethod.fromString(paymentMethod);
	        int paymentMethodCode = method.getValue();
	        String paymentLabel = method.getLabel();
	        
	        // pay.jpを利用したクレジットカード決済
	        int status = 0;
	        if(cardToken != null && method == PaymentMethod.CREDIT_CARD) {
	        	
	        	Properties properties = new Properties();
	        	try (InputStream in = getClass().getClassLoader()
	        	        .getResourceAsStream("config.properties")) {
	        	    if (in == null) {
	        	        throw new IllegalStateException("config.properties が見つかりません。");
	        	    }
	        	    properties.load(in);
	        	} catch (IOException e) {
	        	    throw new IllegalStateException("config.properties の読み込みに失敗しました。", e);
	        	}

	        	Payjp.apiKey = properties.getProperty("Payjp.apiKey");
	        	
	        	Map<String, Object> chargeMap = new HashMap<String, Object>();
	        	chargeMap.put("amount", totalAmount);//支払金額をセット
	            chargeMap.put("currency", "jpy");//日本円をセット
	            chargeMap.put("card", cardToken);//カード情報をセット
	            
	            try {
	            	Charge charge = Charge.create(chargeMap);
	            	
	            	// ステータスを「決済完了済み」にする
	            	status = 1;
	            } catch (Exception e) {
	            	e.printStackTrace();
	            	conn.rollback();
	            	return false;
	            }
	        }
	        
	        // 注文情報を登録する
	        int orderId = ordersDao.registerOrder(conn, userId, paymentMethodCode, totalAmount, status);
	        
	        // 注文明細を登録する
	        for(CartItem cartItem : cart) {
	        	int productId = cartItem.getProduct().getProductId();
	        	int quantity = cartItem.getQuantity();
	        	boolean result = orderItemsDao.registerOrderItem(conn, orderId, productId, quantity);
	        	if(!result) {
	        		conn.rollback();
	        		return false;
	        	}
	        }
	        
	        // 在庫数を減らす
	        for(CartItem cartItem : cart) {
	        	int productId = cartItem.getProduct().getProductId();
	        	int quantity = cartItem.getQuantity();
	        	
	        	boolean result = productsDao.decreaseStock(conn, productId, quantity);
	        	if(!result) {
	        		conn.rollback();
	        		return false;
	        	}
	        }
	        
	        conn.commit();
	        
	        // 注文完了メールを送信
	        MailSender mailSender = new MailSender();
	        boolean successSendMail = mailSender.sendOrderCompleteMail(mail, orderId, cart,
	        		totalAmount, paymentLabel);
	        
	        if(!successSendMail) {
	        	System.out.println("注文完了メールの送信に失敗しました。orderId=" + orderId);
	        }
	        
	        return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
