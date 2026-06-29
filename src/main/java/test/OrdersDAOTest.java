package test;

import java.util.List;

import dao.OrdersDAO;
import model.entity.Order;

/**
 * 注文DAOテストクラス
 */
public class OrdersDAOTest {
	public static void main(String[] args) {
//		testFindByUserIdOK();	// 会員IDをもとに注文一覧を取得するテスト
//		testFindByUserIdNG();	// 会員IDをもとに注文一覧を取得するテスト
//		testFindByOrderIdOK();	// 商品IDをもとに注文明細情報を取得するテスト
//		testFindByOrderIdNG();	// 商品IDをもとに注文明細情報を取得失敗するテスト
//		testUpdateStatusOK();	// 注文ステータスを更新するテスト
//		testUpdateStatusNG();	// 注文ステータスを更新失敗するテスト
	}
	
	public static void testFindByUserIdOK() {
		OrdersDAO dao = new OrdersDAO();
		List<Order> orderList = dao.findByUserId(5, 10, 2);
		if(orderList != null && !orderList.isEmpty()) {
			System.out.println("testFindByUserIdOK：成功しました");
		} else {
			System.out.println("testFindByUserIdOK：失敗しました");
		}
	}
	
	public static void testFindByUserIdNG() {
		OrdersDAO dao = new OrdersDAO();
		List<Order> orderList = dao.findByUserId(0, 10, 2);
		if(orderList == null || orderList.isEmpty()) {
			System.out.println("testFindByUserIdNG：成功しました");
		} else {
			System.out.println("testFindByUserIdNG：失敗しました");
		}
	}
	
	public static void testFindByOrderIdOK() {
		OrdersDAO dao = new OrdersDAO();
		Order order = dao.findByOrderId(1);
		if(order != null) {
			System.out.println("testFindByOrderIdOK：成功しました");
		} else {
			System.out.println("testFindByOrderIdNG：失敗しました");
		}
	}
	
	public static void testFindByOrderIdNG() {
		OrdersDAO dao = new OrdersDAO();
		Order order = dao.findByOrderId(0);
		if(order == null) {
			System.out.println("testFindByOrderIdNG：成功しました");
		} else {
			System.out.println("testFindByOrderIdNG：失敗しました");
		}
	}
	
//	public static void testUpdateStatusOK() {
//		OrdersDAO dao = new OrdersDAO();
//		boolean result = dao.updateCancel(1);
//		if(result) {
//			System.out.println("testUpdateStatusOK：成功しました");
//		} else {
//			System.out.println("testUpdateStatusOK：失敗しました");
//		}
//	}
//	
//	public static void testUpdateStatusNG() {
//		OrdersDAO dao = new OrdersDAO();
//		boolean result = dao.updateCancel(0);
//		if(!result) {
//			System.out.println("testUpdateStatusNG：成功しました");
//		} else {
//			System.out.println("testUpdateStatusNG：失敗しました");
//		}
//	}
	
}
