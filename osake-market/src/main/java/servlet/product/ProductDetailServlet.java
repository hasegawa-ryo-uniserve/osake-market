package servlet.product;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Product;
import model.entity.User;
import model.logic.product.ProductDetailLogic;

/**
 * 商品詳細サーブレット
 */
@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		int productId = Integer.parseInt(request.getParameter("productId"));
		
		// ログイン情報を取得(ログインしているときはカートとお気に入りボタンが光る)
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		Integer userId = null;
		if(loginUser != null) {
			userId = loginUser.getUserId();
		}
		
		// 商品詳細取得処理
		ProductDetailLogic logic = new ProductDetailLogic();
		Product product = logic.execute(productId, userId);
		
		// 処理結果によってフォワード先を変える
		if (product != null) { // 商品詳細がある場合
			// お気に入り
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/product/productDetail.jsp");
			dispatcher.forward(request, response);
		} else {	// 商品詳細が見つからなかった場合
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
