package servlet.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.CartItem;
import model.entity.User;
import model.logic.cart.AddCartLogic;

/**
 * カートに商品を追加するサーブレット
 */
@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * カートに商品を追加する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		String strProductId = request.getParameter("productId");
		int productId = Integer.parseInt(strProductId);
		String fromPage = request.getParameter("fromPage"); // "list" か "detail"
		
		// セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		Integer userId = loginUser.getUserId();
		
		// セッションスコープからカート情報を取得する
		session.setMaxInactiveInterval(300);
		List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
		
		// セッションスコープにカート情報がない時
		if (cart == null) {
			cart = new ArrayList<>();
		}
		
		// カートに商品を追加する
		AddCartLogic logic = new AddCartLogic();
		cart = logic.execute(cart, productId, userId);
		
		// セッションスコープにカートを保存する
		session.setAttribute("cart", cart);
		
		// カートに追加済みメッセージをセッションスコープにセット
		session.setAttribute("cartMessage", "カートに追加しました");
		
		// 商品詳細ページにリダイレクト
		if ("list".equals(fromPage)) {
		    // 一覧ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/list"); // 元のURLに戻る
		} else if ("detail".equals(fromPage)) {
		    // 詳細ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/detail?productId=" + productId);
		}
	}

}
