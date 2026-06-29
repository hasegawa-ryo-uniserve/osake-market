package servlet.order;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.logic.order.OrderCancelLogic;

/**
 * 注文をキャンセルするサーブレットクラス
 */
@WebServlet("/order/cancel")
public class OrderCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文をキャンセルする
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		// キャンセル処理
		OrderCancelLogic logic = new OrderCancelLogic();
		boolean result = logic.cancel(orderId);
		
		HttpSession session = request.getSession();
		// 実行結果によって処理を分岐
		if(result) {
			// 成功時
			// 成功結果をセッションスコープに保存
			session.setAttribute("msg", "注文の取り消しを実行しました");
		} else {
			// 失敗時
			// 失敗結果をセッションスコープに保存
			session.setAttribute("msg", "配送手続き中のため注文の取り消しは不可です");
		}
		response.sendRedirect(request.getContextPath() + "/order/detail?orderId=" + orderId);
	}

}
