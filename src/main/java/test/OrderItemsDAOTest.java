package test;

import dao.OrderItemsDAO;
import model.entity.Order;

/**
 * 注文明細DAOテストクラス
 */
public class OrderItemsDAOTest {
	public static void main(String[] args) {
//		testRegisterItemOK();	// 注文明細情報を登録するテスト
//		testRegisterItemNG();	// 注文明細情報を登録失敗するテスト
		testFindByOrderIdOK();	// 注文IDをもとに注文明細情報を取得するテスト
		testFindByOrderIdNG();	// 注文IDをもとに注文明細情報を取得失敗するテスト
	}
	
//	public static void testRegisterItemOK() {
//		OrderItemsDAO dao = new OrderItemsDAO();
//		boolean result = dao.registerOrderItem(1, 1, 4);
//		if(result) {
//			System.out.println("testRegisterItemOK：成功しました");
//		} else {
//			System.out.println("testRegisterItemOK：失敗しました");
//		}
//	}
//	
//	public static void testRegisterItemNG() {
//		OrderItemsDAO dao = new OrderItemsDAO();
//		boolean result = dao.registerOrderItem(1, 1, -1);
//		if(!result) {
//			System.out.println("testRegisterItemNG：成功しました");
//		} else {
//			System.out.println("testRegisterItemNG：失敗しました");
//		}
//	}
	
	public static void testFindByOrderIdOK() {
		OrderItemsDAO dao = new OrderItemsDAO();
		Order order = dao.findByOrderId(1);
		if(order != null) {
			System.out.println("testFindByOrderIdOK：成功しました");
		} else {
			System.out.println("testFindByOrderIdOK：失敗しました");
		}
	}
	
	public static void testFindByOrderIdNG() {
		OrderItemsDAO dao = new OrderItemsDAO();
		Order order = dao.findByOrderId(0);
		if(order == null) {
			System.out.println("testFindByOrderIdNG：成功しました");
		} else {
			System.out.println("testFindByOrderIdNG：失敗しました");
		}
	}
}
