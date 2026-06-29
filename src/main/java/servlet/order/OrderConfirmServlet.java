package servlet.order;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 注文情報確認画面を表示するサーブレットクラス
 */
@WebServlet("/order/confirm")
public class OrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 注文情報確認画面を表示する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		String paymentMethod = request.getParameter("paymentMethod");
		String cardToken = request.getParameter("card");
		
		// 支払方法をリクエストスコープに保存
		request.setAttribute("paymentMethod", paymentMethod);
		request.setAttribute("cardToken", cardToken);
		
		// 注文確認画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
