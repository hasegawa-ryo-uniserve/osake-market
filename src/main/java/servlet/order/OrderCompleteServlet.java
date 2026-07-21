package servlet.order;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 注文完了画面に遷移するサーブレットクラス
 */
@WebServlet("/order/complete")
public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文完了画面に遷移
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderComplete.jsp");
		dispatcher.forward(request, response);
	}


}
