package dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersDAOTest {
	/** ドライバクラス名 */
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    /** 接続するDBのURL */
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

    /** DB接続するためのユーザ名 */
    private static final String DB_USER = "osake_market";

    /** DB接続するためのパスワード */
    private static final String DB_PASS = "systemsss";
    
    private final OrdersDAO dao = new OrdersDAO();
    private Connection conn;
    
    @BeforeEach
    void setUp() throws Exception {
    	Class.forName(DRIVER);
    	conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	if (conn != null) {
            conn.close();
        }
    }
	
	@DisplayName("No.1 注文情報をDBに登録する 正常系")
	@Test
	@org.junit.jupiter.api.Order(1)
	void testRegisterOrderOK() {
		int orderId = dao.registerOrder(conn, 1, 1, 5000, 0);
		assertEquals(12, orderId);
	}
	
	@DisplayName("No.2 注文情報をDBに登録する 異常系")
	@Test
	@org.junit.jupiter.api.Order(2)
	void testRegisterOrderNG() {
		int orderId = dao.registerOrder(conn, 0, 1, 5000, 0);
		assertEquals(-1, orderId);
	}
	
	@DisplayName("No.3 注文一覧を取得 正常系")
	@Test
	@org.junit.jupiter.api.Order(3)
	void testFindByUserIdOK() {
		List<Order> orderList = dao.findByUserId(1, 10, 2);
		assertFalse(orderList.isEmpty());
	}
	
	@DisplayName("No.4 注文一覧を取得 異常系")
	@Test
	@org.junit.jupiter.api.Order(4)
	void testFindByUserIdNG() {
		List<Order> orderList = dao.findByUserId(0, 10, 2);
		assertTrue(orderList.isEmpty());
	}
	
	@DisplayName("No.5 注文IDをもとに商品を取得 正常系")
	@Test
	@org.junit.jupiter.api.Order(5)
	void testFindByOrderIdOK() {
		Order order = dao.findByOrderId(1);
		assertNotNull(order);
	}
	
	@DisplayName("No.6 注文IDをもとに商品を取得 異常系")
	@Test
	@org.junit.jupiter.api.Order(6)
	void testFindByOrderIdNG() {
		Order order = dao.findByOrderId(0);
		assertNull(order);
	}
	
	@DisplayName("No.7 注文IDをもとに商品を取得(キャンセルトランザクション①) 正常系")
	@Test
	@org.junit.jupiter.api.Order(7)
	void testFindByOrderIdForCancelOK() {
		Order order = dao.findByOrderIdForCancel(conn, 1);
		assertNotNull(order);
	}
	
	@DisplayName("No.8 注文IDをもとに商品を取得(キャンセルトランザクション①) 異常系")
	@Test
	@org.junit.jupiter.api.Order(8)
	void testFindByOrderIdForCancelNG() {
		Order order = dao.findByOrderIdForCancel(conn, 0);
		assertNull(order);
	}
	
	@DisplayName("No.9 キャンセルする 正常系")
	@Test
	@org.junit.jupiter.api.Order(9)
	void testUpdateCancelOK() {
		boolean result = dao.updateCancel(conn, 10);	// 注文ID10のcancelが1に更新される
		assertTrue(result);
	}
	
	@DisplayName("No.10 キャンセルする 異常系")
	@Test
	@org.junit.jupiter.api.Order(10)
	void testUpdateCancelNG() {
		boolean result = dao.updateCancel(conn, 0);
		assertFalse(result);
	}
	
	@DisplayName("No.11 ページネーション用に件数取得 正常系")
	@Test
	@org.junit.jupiter.api.Order(11)
	void testCountAllOK() {
		int count = dao.countAll(1);
		assertEquals(12, count);
	}
	
	@DisplayName("No.12 ページネーション用に件数取得 異常系")
	@Test
	@org.junit.jupiter.api.Order(12)
	void testCountAllNG() {
		int count = dao.countAll(0);
		assertEquals(0, count);
	}

}
