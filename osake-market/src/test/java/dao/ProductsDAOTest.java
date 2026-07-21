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
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Product;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductsDAOTest {
	/** ドライバクラス名 */
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    /** 接続するDBのURL */
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521/xepdb1";

    /** DB接続するためのユーザ名 */
    private static final String DB_USER = "osake_market";

    /** DB接続するためのパスワード */
    private static final String DB_PASS = "systemsss";

    private final ProductsDAO dao = new ProductsDAO();
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
	
	@DisplayName("No.1 カテゴリをもとに商品を検索 正常系")
	@Test
	@Order(1)
	void testFindByCategoryNameOK() {
		List<Product> productList = dao.findByCategoryName("ワイン");
		assertFalse(productList.isEmpty());
	}
	
	@DisplayName("No.2 カテゴリをもとに商品を検索 異常系")
	@Test
	@Order(2)
	void testFindByCategoryNameNG() {
		List<Product> productList = dao.findByCategoryName("NG");
		assertTrue(productList.isEmpty());
	}
	
	@DisplayName("No.3 商品一覧を取得 正常系")
	@Test
	@Order(3)
	void testFindAll() {
		List<Product> productList = dao.findAll();
		assertFalse(productList.isEmpty());
	}
	
	@DisplayName("No.4 商品IDをもとに商品を取得 正常系")
	@Test
	@Order(4)
	void testFindByProductIdOK() {
		Product product = dao.findByProductId(1, 1);
		assertNotNull(product);
	}
	
	@DisplayName("No.5 商品IDをもとに商品を取得 異常系")
	@Test
	@Order(5)
	void testFindByProductIdNG() {
		Product product = dao.findByProductId(0, 1);
		assertNull(product);
	}
	
	@DisplayName("No.6 追加の新しい順で商品一覧を絞込検索 正常系")
	@Test
	@Order(6)
	void testFindAllOrderBySortNew() {
	    List<Product> productList = dao.findAllOrderBySort("p.create_date DESC", null);

	    // 結果が取得できていること
	    assertNotNull(productList);
	    assertFalse(productList.isEmpty());

	    // 新しい順になっていること
	    for (int i = 0; i < productList.size() - 1; i++) {
	        assertTrue(productList.get(i).getCreateDate().compareTo(productList.get(i + 1).getCreateDate()) >= 0);
	    }
	}
	
	@DisplayName("No.7 追加の古い順で商品一覧を絞込検索 正常系")
	@Test
	@Order(7)
	void testFindAllOrderBySortOld() {
	    List<Product> productList = dao.findAllOrderBySort("p.create_date ASC", null);

	    // 結果が取得できていること
	    assertNotNull(productList);
	    assertFalse(productList.isEmpty());

	    // 古い順になっていること
	    for (int i = 0; i < productList.size() - 1; i++) {
	        assertTrue(productList.get(i).getCreateDate().compareTo(productList.get(i + 1).getCreateDate()) <= 0);
	    }
	}
	
	@DisplayName("No.8 価格の安い順で商品一覧を絞込検索 正常系")
	@Test
	@Order(8)
	void testFindAllOrderBySortPriceAsc() {
	    List<Product> productList = dao.findAllOrderBySort("p.price ASC", null);

	    // 結果が取得できていること
	    assertNotNull(productList);
	    assertFalse(productList.isEmpty());

	    // 価格の安い順になっていること
	    for (int i = 0; i < productList.size() - 1; i++) {
	        assertTrue(productList.get(i).getPrice() <= productList.get(i + 1).getPrice());
	    }
	}
	
	@DisplayName("No.9 価格の高い順で商品一覧を絞込検索 正常系")
	@Test
	@Order(9)
	void testFindAllOrderBySortPriceDesc() {
	    List<Product> productList = dao.findAllOrderBySort("p.price DESC", null);

	    // 結果が取得できていること
	    assertNotNull(productList);
	    assertFalse(productList.isEmpty());

	    // 価格の高い順になっていること
	    for (int i = 0; i < productList.size() - 1; i++) {
	        assertTrue(productList.get(i).getPrice() >= productList.get(i + 1).getPrice());
	    }
	}
	
	@DisplayName("No.10 ソートで商品一覧を絞込検索 異常系")
	@Test
	@Order(10)
	void testFindAllOrderBySort() {
	    List<Product> productList = dao.findAllOrderBySort("aaa", null);

	    // 結果が取得できていること
	    assertNull(productList);
//	    assertTrue(productList.isEmpty());
	}
	
	@DisplayName("No.11 カテゴリ名とソートで商品一覧を絞込検索 正常系")
	@Test
	@Order(11)
	void testFindAllWithCategoryNameOrderBySort() {
		List<Product> productList = dao.findAllWithCategoryNameOrderBySort("ワイン", "p.create_date ASC", null);
		assertFalse(productList.isEmpty());
	}
	
	@DisplayName("No.12 商品名とソートで商品一覧を絞込検索 正常系")
	@Test
	@Order(12)
	void testFindAllWithProductNameOrderBySort() {
		List<Product> productList = dao.findAllWithProductNameOrderBySort("ワイン", "p.create_date ASC", null);
		assertFalse(productList.isEmpty());
	}
	
	@DisplayName("No.13 カテゴリ名と商品名とソートで商品一覧を絞込検索 正常系")
	@Test
	@Order(13)
	void testFindAllWithCategoryNameAndProductNameOrderBySort() {
		List<Product> productList = dao.findAllWithCategoryNameAndProductNameOrderBySort("ワイン", "赤", "p.create_date ASC", null);
		assertFalse(productList.isEmpty());
	}
	
	@DisplayName("No.14 在庫チェック 正常系")
	@Test
	@Order(14)
	void testGetStockOK() {
		int stock = dao.getStock(conn, 1);
		assertEquals(10, stock);
	}
	
	@DisplayName("No.15 在庫チェック 異常系")
	@Test
	@Order(15)
	void testGetStockNG() {
		int stock = dao.getStock(conn, 0);
		assertEquals(0, stock);
	}
	
	@DisplayName("No.16 在庫数を減らす 正常系")
	@Test
	@Order(16)
	void testDecreaseStockOK() {
		boolean result = dao.decreaseStock(conn, 2, 1); // 商品IDが2の在庫が9になっている
		assertTrue(result);
	}
	
	@DisplayName("No.17 在庫数を減らす 異常系")
	@Test
	@Order(17)
	void testDecreaseStockNG() {
		boolean result = dao.decreaseStock(conn, 0, 1);
		assertFalse(result);
	}
	
	@DisplayName("No.18 商品一覧（お気に入り情報付き）を取得する 正常系")
	@Test
	@Order(18)
	void testFindAllWithFavoriteOK() {
		List<Product> favoriteList = dao.findAllWithFavorite(1);
		assertFalse(favoriteList.isEmpty());
	}
	
	@DisplayName("No.19 商品一覧（お気に入り情報付き）を取得する 異常系")
	@Test
	@Order(19)
	void testFindAllWithFavoriteNG() {
		List<Product> favoriteList = dao.findAllWithFavorite(0);
		assertFalse(favoriteList.isEmpty());
	}
	
	@DisplayName("No.20 在庫数を増やす 正常系")
	@Test
	@Order(20)
	void testIncreaseStockOK() {
		boolean result = dao.increaseStock(conn, 3, 1);	// 商品IDが3の在庫が11になっている
		assertTrue(result);
	}
	
	@DisplayName("No.21 在庫数を増やす 異常系")
	@Test
	@Order(21)
	void testIncreaseStockNG() {
		boolean result = dao.increaseStock(conn, 0, 1);
		assertFalse(result);
	}

}
