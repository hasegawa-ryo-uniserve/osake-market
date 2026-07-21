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
import model.entity.CartItem;

/**
 * 注文情報確認画面を表示するサーブレットクラス
 */
@WebServlet("/order/confirm")
public class OrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * URLから直接アクセスした場合
	 * カートに商品がない場合→カート画面にリダイレクト
	 * カートに商品はあるが支払情報が入力されていない場合→注文情報入力画面にリダイレクト
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// カートがセッションにあるかどうか確認
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
		if(cart == null || cart.isEmpty()) {
			// カートが空であればカート画面にリダイレクト
			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}
		
		// 支払情報をパラメータとして取得
		String paymentMethod = request.getParameter("paymentMethod");
		if (paymentMethod == null || paymentMethod.isBlank()) {
			// 支払情報が空の場合は注文情報入力画面にリダイレクト
		    response.sendRedirect(request.getContextPath() + "/order/input");
		    return;
		}
	}
	
	/**
	 * 注文情報確認画面を表示する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		String paymentMethod = request.getParameter("paymentMethod");
		String cardToken = request.getParameter("card");
		
		if ("CREDIT_CARD".equals(paymentMethod)
		        && (cardToken == null || cardToken.isEmpty())) {

		    request.setAttribute("error", "クレジットカード情報を入力してください");
		    request.setAttribute("paymentMethod", paymentMethod);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderInput.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		
		// 支払方法をリクエストスコープに保存
		request.setAttribute("paymentMethod", paymentMethod);
		request.setAttribute("cardToken", cardToken);
		
		// 注文確認画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order/orderConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
