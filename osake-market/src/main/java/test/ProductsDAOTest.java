package test;

import java.util.ArrayList;
import java.util.List;

import dao.ProductsDAO;
import model.Product;

/**
 * 商品DAOテストクラス
 */
public class ProductsDAOTest {
	public static void main(String[] args) {
		testFindAllOK();	// 商品リストが見つかるテスト
		testFindAllNG();	// 商品リストが見つからないテスト
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
}
