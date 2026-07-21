package model.logic.order;

import dao.OrderItemsDAO;
import model.entity.Order;

/**
 * 注文明細を取得するロジッククラス
 */
public class OrderDetailLogic {
	/**
	 * 注文明細を取得する
	 * 
	 * @param orderId 注文ID
	 * @return order 注文情報
	 */
	public Order findByOrderId(int orderId) {
		OrderItemsDAO orderItemsDAO = new OrderItemsDAO();
		Order order = orderItemsDAO.findByOrderId(orderId);

		return order;
	}
}
