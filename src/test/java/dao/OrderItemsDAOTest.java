package dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Order;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderItemsDAOTest {
	/** ドライバクラス名 */
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    /** 接続するDBのURL */
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

    /** DB接続するためのユーザ名 */
    private static final String DB_USER = "osake_market";

    /** DB接続するためのパスワード */
    private static final String DB_PASS = "systemsss";

    private final OrderItemsDAO dao = new OrderItemsDAO();
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
	void testRegisterOrderItemOK() {
		boolean result = dao.registerOrderItem(conn, 1, 5, 1);	// 商品ID5、注文数1がDBに登録される
		assertTrue(result);
	}
    
    @DisplayName("No.2 注文情報をDBに登録する 異常系")
	@Test
	@org.junit.jupiter.api.Order(2)
	void testRegisterOrderItemNG() {
		boolean result = dao.registerOrderItem(conn, 1, 0, 1);
		assertFalse(result);
	}

    @DisplayName("No.3 注文IDをもとに注文明細を取得 正常系")
	@Test
	@org.junit.jupiter.api.Order(3)
	void testFindByOrderIdOK() {
		Order order = dao.findByOrderId(1);
		assertNotNull(order);
	}
    
    @DisplayName("No.4 注文IDをもとに注文明細を取得 異常系")
	@Test
	@org.junit.jupiter.api.Order(4)
	void testFindByOrderIdNG() {
		Order order = dao.findByOrderId(0);
		assertNull(order);
	}
    
    @DisplayName("No.5 注文IDをもとに注文明細を取得(キャンセルトランザクション②) 正常系")
	@Test
	@org.junit.jupiter.api.Order(5)
	void testFindByOrderIdForCancelOK() {
		Order order = dao.findByOrderIdForCancel(conn, 1);
		assertNotNull(order);
	}
    
    @DisplayName("No.6 注文IDをもとに注文明細を取得(キャンセルトランザクション②) 異常系")
	@Test
	@org.junit.jupiter.api.Order(6)
	void testFindByOrderIdForCancelNG() {
		Order order = dao.findByOrderIdForCancel(conn, 0);
		assertNull(order);
	}

}
