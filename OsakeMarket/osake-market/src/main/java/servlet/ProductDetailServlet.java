package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductDetailLogic;

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
		request.setCharacterEncoding("UTF-8");
		String productIdStr = request.getParameter("productId");
		int productId = Integer.parseInt(productIdStr);
		
		// 商品詳細取得処理
		ProductDetailLogic logic = new ProductDetailLogic();
		Product product = logic.execute(productId);
		
		// 処理結果によってフォワード先を変える
		if (product != null) { // 商品詳細がある場合
			request.setAttribute("product", product);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productDetail.jsp");
			dispatcher.forward(request, response);
		} else {	// 商品詳細が見つからなかった場合
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
