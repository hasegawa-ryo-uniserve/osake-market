package servlet.order;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Order;
import model.entity.User;
import model.logic.order.OrderListLogic;

/**
 * 注文履歴を表示するサーブレットクラス
 */
@WebServlet("/order/list")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文履歴を表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログイン情報を取得する
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// 注文履歴のページ数を取得
		int page = 1;
		String pageParam = request.getParameter("page");
		if(pageParam != null) {
		    page = Integer.parseInt(pageParam);
		}
		
		// 注文履歴の取得処理
		OrderListLogic logic = new OrderListLogic();
		List<Order> orderList = logic.findOrderList(userId, page);
		
		// 注文履歴総件数を取得する
		int totalCount = logic.countAll(loginUser.getUserId());
		int totalPage = (int)Math.ceil((double)totalCount / 10);
		
		// 注文一覧画面に遷移
		if(orderList != null && !orderList.isEmpty()) {
			request.setAttribute("orderList", orderList);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderList.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}



}
