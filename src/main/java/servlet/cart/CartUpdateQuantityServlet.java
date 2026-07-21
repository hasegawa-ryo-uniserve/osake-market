package servlet.cart;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.CartItem;
import model.logic.cart.UpdateCartQuantityLogic;

/**
 * カート内の商品数を増減させるサーブレットクラス
 */
@WebServlet("/cart/update/quantity")
public class CartUpdateQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * カート内の商品数を増減させる
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String srtProductId = request.getParameter("productId");
		int productId = Integer.parseInt(srtProductId);
		String strDelta = request.getParameter("delta");
		int delta = Integer.parseInt(strDelta);

		// セッションスコープからカート情報を取得
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

		// 商品数増減処理
		UpdateCartQuantityLogic logic = new UpdateCartQuantityLogic();
		cart = logic.execute(cart, productId, delta);

		// カート情報をセッションスコープに保存
		session.setAttribute("cart", cart);

		// カート表示機能にリダイレクト
		response.sendRedirect(request.getContextPath() + "/cart");
	}

}
