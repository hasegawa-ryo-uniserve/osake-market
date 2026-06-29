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
import model.logic.cart.RemoveCartLogic;

/**
 * カート内の商品を削除するサーブレットクラス
 */
@WebServlet("/cart/remove")
public class CartRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * カート内の商品を削除する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String strProductId = request.getParameter("productId");
		int productId = Integer.parseInt(strProductId);
		
		// セッションスコープからカート情報を取得
		HttpSession session = request.getSession();
		List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
		
		// 削除処理
		RemoveCartLogic logic = new RemoveCartLogic();
		cart = logic.execute(cart, productId);
		
		// カート情報をセッションスコープに保存
		session.setAttribute("cart", cart);
		
		// カート表示機能にリダイレクト
		response.sendRedirect(request.getContextPath() + "/cart");
	}

}
