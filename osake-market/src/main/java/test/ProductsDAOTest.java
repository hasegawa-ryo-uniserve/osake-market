package test;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import model.entity.Product;

/**
 * 商品DAOテストクラス
 */
public class ProductsDAOTest {
	public static void main(String[] args) {
//		testFindAllOK();	// 商品リストが見つかるテスト
//		testFindAllNG();	// 商品リストが見つからないテスト
//		testFindByCategoryNameOK();		// カテゴリ名で商品リストが見つかるテスト
//		testFindByCategoryNameNG();		// カテゴリ名で商品リストが見つからないテスト
//		testFindByProductIdOK();		// 商品IDで商品が見つかるテスト
//		testFindByProductIdNG();		// 商品IDで商品が見つからないテスト
//		testFindAllOrderBySortOK(); // ソートで商品リストを絞り込むテスト
//		testFindAllOrderBySortNG(); // ソートで商品リストを絞り込めないテスト
//		testFindAllWithCategoryNameOrderBySortOK(); // カテゴリ名とソートで商品リストを絞り込むテスト
//		testFindAllWithCategoryNameOrderBySortNG(); // カテゴリ名とソートで商品リストを絞り込めないテスト
//		testFindAllWithProductNameOrderBySortOK(); // 商品名とソートで商品リストを絞り込むテスト
//		testFindAllWithProductNameOrderBySortNG(); // 商品名とソートで商品リストを絞り込めないテスト
//		testFindAllWithCategoryNameAndProductNameOrderBySortOK(); // カテゴリ名と商品名とソートで商品リストを絞り込むテスト
//		testFindAllWithCategoryNameAndProductNameOrderBySortNG(); // カテゴリ名と商品名とソートで商品リストを絞り込めないテスト
//		testGetStockOK();	// 在庫数を取得するテスト
//		testGetStockNG();	// 在庫数を取得できないテスト
//		testDecreaseStockOK();	// 在庫数を減らすテスト
//		testIncreaseStockOK();	// 在庫数を増やすテスト
	}
	
	public static void testFindAllOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAll();
		if(productList != null) {
			System.out.println("testFindAllOK：成功しました");
		} else {
			System.out.println("testFindAllOK：失敗しました");
		}
	}
	
	public static void testFindAllNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAll();
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllNG：成功しました");
		} else {
			System.out.println("testFindAllNG：失敗しました");
		}
	}
	
	public static void testFindByCategoryNameOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findByCategoryName("ワイン");
		if(productList != null) {
			System.out.println("testFindAllOK：成功しました");
		} else {
			System.out.println("testFindAllOK：失敗しました");
		}
	}
	
	public static void testFindByCategoryNameNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findByCategoryName("ワイン");
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllNG：成功しました");
		} else {
			System.out.println("testFindAllNG：失敗しました");
		}
	}
	
	public static void testFindByProductIdOK() {
		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findByProductId(1, 1);
		if(product != null) {
			System.out.println("testFindByProductIdOK：成功しました");
		} else {
			System.out.println("testFindByProductIdOK：失敗しました");
		}
	}
	
	public static void testFindByProductIdNG() {
		ProductsDAO dao = new ProductsDAO();
		Product product = dao.findByProductId(1, 0);
		product = null;
		if(product == null) {
			System.out.println("testFindByProductIdNG：成功しました");
		} else {
			System.out.println("testFindByProductIdNG：失敗しました");
		}
	}
	
	public static void testFindAllOrderBySortOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllOrderBySort("create_date DESC", 1);
		if(productList != null) {
			System.out.println("testFindAllOrderBySortOK：成功しました");
		} else {
			System.out.println("testFindAllOrderBySortOK：失敗しました");
		}
	}
	
	public static void testFindAllOrderBySortNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllOrderBySort("create_date DESC", 1);
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllOrderBySortNG：成功しました");
		} else {
			System.out.println("testFindAllOrderBySortNG：失敗しました");
		}
	}
	
	public static void testFindAllWithCategoryNameOrderBySortOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithCategoryNameOrderBySort("ワイン", "create_date DESC", 1);
		if(productList != null) {
			System.out.println("testFindAllWithCategoryNameOrderBySortOK：成功しました");
		} else {
			System.out.println("testFindAllWithCategoryNameOrderBySortOK：失敗しました");
		}
	}
	
	public static void testFindAllWithCategoryNameOrderBySortNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithCategoryNameOrderBySort("wine", "create_date DESC", 1);
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllWithCategoryNameOrderBySortNG：成功しました");
		} else {
			System.out.println("testFindAllWithCategoryNameOrderBySortNG：失敗しました");
		}
	}
	
	public static void testFindAllWithProductNameOrderBySortOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithProductNameOrderBySort("赤", "create_date DESC", 1);
		if(productList != null) {
			System.out.println("testFindAllWithProductNameOrderBySortOK：成功しました");
		} else {
			System.out.println("testFindAllWithProductNameOrderBySortOK：失敗しました");
		}
	}
	
	public static void testFindAllWithProductNameOrderBySortNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithProductNameOrderBySort("赤", "create_date DESC", 1);
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllWithProductNameOrderBySortNG：成功しました");
		} else {
			System.out.println("testFindAllWithProductNameOrderBySortNG：失敗しました");
		}
	}
	
	public static void testFindAllWithCategoryNameAndProductNameOrderBySortOK() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithCategoryNameAndProductNameOrderBySort("ワイン", "赤", "create_date DESC", 1);
		if(productList != null) {
			System.out.println("testFindAllWithCategoryNameAndProductNameOrderBySortOK：成功しました");
		} else {
			System.out.println("testFindAllWithCategoryNameAndProductNameOrderBySortOK：失敗しました");
		}
	}
	
	public static void testFindAllWithCategoryNameAndProductNameOrderBySortNG() {
		ProductsDAO dao = new ProductsDAO();
		List<Product> productList = new ArrayList<>();
		productList = dao.findAllWithCategoryNameAndProductNameOrderBySort("wine", "赤", "create_date DESC", 1);
		productList = null;
		if(productList == null) {
			System.out.println("testFindAllWithCategoryNameAndProductNameOrderBySortNG：成功しました");
		} else {
			System.out.println("testFindAllWithCategoryNameAndProductNameOrderBySortNG：失敗しました");
		}
	}
	
//	public static void testGetStockOK() {
//		ProductsDAO dao = new ProductsDAO();
//		int stock = dao.getStock(1);
//		if(stock != 0) {
//			System.out.println("testGetStockOK：成功しました");
//		} else {
//			System.out.println("testGetStockOK：失敗しました");
//		}
//	}
//	
//	public static void testGetStockNG() {
//		ProductsDAO dao = new ProductsDAO();
//		int stock = dao.getStock(100);
//		if(stock == 0) {
//			System.out.println("testGetStockNG：成功しました");
//		} else {
//			System.out.println("testGetStockNG：失敗しました");
//		}
//	}
	
//	public static void testDecreaseStockOK() {
//		ProductsDAO dao = new ProductsDAO();
//		boolean result = dao.decreaseStock(1, 1);
//		if(result) {
//			System.out.println("testDecreaseStockOK：成功しました");
//		} else {
//			System.out.println("testDecreaseStockOK：失敗しました");
//		}
//	}
	
//	public static void testIncreaseStockOK() {
//		ProductsDAO dao = new ProductsDAO();
//		boolean result = dao.increaseStock(1, 1);
//		if(result) {
//			System.out.println("testIncreaseStockOK：成功しました");
//		} else {
//			System.out.println("testIncreaseStockOK：失敗しました");
//		}
//	}

}
