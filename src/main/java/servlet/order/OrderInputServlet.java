package servlet.order;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 注文情報入力画面を表示するサーブレットクラス
 */
@WebServlet("/order/input")
public class OrderInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * 注文情報入力画面を表示
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderInput.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * 注文確認画面から注文詳細画面に戻る処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		String paymentMethod = request.getParameter("paymentMethod");
		
		// リクエストパラメータに支払方法を保存
		request.setAttribute("paymentMethod", paymentMethod);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderInput.jsp");
		dispatcher.forward(request, response);
	}
}
