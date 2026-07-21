package servlet.favorite;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.User;
import model.logic.favorite.FavoriteLogic;

/**
 * お気に入りを追加するサーブレットクラス
 */
@WebServlet("/favorite/add")
public class FavoriteAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * お気に入りを追加する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		int productId = Integer.parseInt(request.getParameter("productId"));
		String fromPage = request.getParameter("fromPage"); // "list" か "detail"
		
		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// お気に入り追加処理
		FavoriteLogic logic = new FavoriteLogic();
		boolean result = logic.addFavorite(userId, productId);
		
		// お気に入りに追加済みメッセージをセッションスコープにセット
		session.setAttribute("favoriteMessage", "お気に入りに追加しました");
		
		// リダイレクト
		session.setAttribute("addResult", result);
		if ("list".equals(fromPage)) {
		    // 一覧ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/list"); // 元のURLに戻る
		} else if ("detail".equals(fromPage)) {
		    // 詳細ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/detail?productId=" + productId);
		}
	}

}
