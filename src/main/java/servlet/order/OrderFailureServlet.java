package servlet.order;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 注文失敗画面に遷移するサーブレットクラス
 */
@WebServlet("/order/failure")
public class OrderFailureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文失敗画面に遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderFailure.jsp");
		dispatcher.forward(request, response);
	}

}
