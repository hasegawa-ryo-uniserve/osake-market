package servlet.order;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Order;
import model.logic.order.OrderDetailLogic;

/**
 * 注文明細情報を表示するサーブレットクラス
 */
@WebServlet("/order/detail")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文明細情報を表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		// 注文明細を取得処理
		OrderDetailLogic logic = new OrderDetailLogic();
		Order order = logic.findByOrderId(orderId);
		
		// 取得結果によって処理を分岐
		if(order != null) {
			// 成功時
			// 注文明細リストをリクエストスコープに保存して注文明細画面に遷移
			request.setAttribute("order", order);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderDetail.jsp");
			dispatcher.forward(request, response);
		} else {
			// 失敗時
			// エラー画面にフォワード
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
