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
 * お気に入りから削除するサーブレットクラス
 */
@WebServlet("/favorite/delete")
public class FavoriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * お気に入りから削除する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		int productId = Integer.parseInt(request.getParameter("productId"));
		String fromPage = request.getParameter("fromPage"); // "list" か "detail" か "favoriteList"
		
		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// お気に入り追加処理
		FavoriteLogic logic = new FavoriteLogic();
		boolean result = logic.deleteFavorite(userId, productId);
		
		// お気に入り削除メッセージをセッションスコープにセット
		session.setAttribute("favoriteMessage", "お気に入りから削除しました");
		
		// リダイレクト
		session.setAttribute("deleteResult", result);
		if ("list".equals(fromPage)) {
		    // 一覧ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/list");
		} else if ("detail".equals(fromPage)) {
		    // 詳細ページに戻す
		    response.sendRedirect(request.getContextPath() + "/product/detail?productId=" + productId);
		} else if ("favoriteList".equals(fromPage)) {
			// お気に入り一覧ページに戻す
			response.sendRedirect(request.getContextPath() + "/favorite/list");
		}
	}

}
