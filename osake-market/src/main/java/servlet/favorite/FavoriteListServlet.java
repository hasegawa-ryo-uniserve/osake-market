package servlet.favorite;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Product;
import model.entity.User;
import model.logic.favorite.FavoriteLogic;

/**
 * お気に入り表示サーブレットクラス
 */
@WebServlet("/favorite/list")
public class FavoriteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * お気に入り一覧を表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログイン情報を取得する
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		// お気に入りを取得する
		FavoriteLogic logic = new FavoriteLogic();
		List<Product> favoriteList = logic.showFavorite(loginUser.getUserId());
		
		// リクエストスコープに保存して、フォワード
		request.setAttribute("favoriteList", favoriteList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/favorite/favoriteList.jsp");
		dispatcher.forward(request, response);
	}

}
