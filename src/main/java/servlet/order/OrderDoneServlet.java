package servlet.order;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.CartItem;
import model.entity.User;
import model.logic.order.OrderLogic;

/**
 * 注文を確定するサーブレットクラス
 */
@WebServlet("/order/done")
public class OrderDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 注文を確定する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		String paymentMethod = request.getParameter("paymentMethod");
		String cardToken = request.getParameter("cardToken");
		
		// セッションスコープから、カート情報とユーザー情報を取得
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		String mail = loginUser.getMail();
		
		// 注文処理
		OrderLogic logic = new OrderLogic();
		boolean result = logic.createOrder(cart, userId, paymentMethod, cardToken, mail);
		
		// 注文処理結果によって遷移先を分岐
		if(result) {			
			response.sendRedirect(request.getContextPath() + "/order/complete");
		} else {
			response.sendRedirect(request.getContextPath() + "/order/failure");
		}
	}

}
