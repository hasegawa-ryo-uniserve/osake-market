package model.logic.order;

import java.util.List;

import dao.OrdersDAO;
import model.entity.Order;

/**
 * 注文履歴を取得するロジッククラス
 */
public class OrderListLogic {
	/**
	 * 注文履歴を取得する
	 * 
	 * @param userId 会員ID
	 * @param page 第何ページか
	 * @return 注文履歴
	 */
	public List<Order> findOrderList(int userId, int page) {
		// 1ページに表示する件数
		final int pageSize = 10;
		// 「何件スキップするか」を計算
		int offset = (page - 1) * pageSize;
		
		OrdersDAO dao = new OrdersDAO();
		List<Order> orderList = dao.findByUserId(userId, offset, pageSize);
		
		return orderList;
	}
	
	/**
	 * 注文履歴の総件数を取得
	 * 
	 * @param userId 会員ID
	 * @return 注文履歴総件数
	 */
	public int countAll(int userId) {
		OrdersDAO dao = new OrdersDAO();
		return dao.countAll(userId);
	}
}
