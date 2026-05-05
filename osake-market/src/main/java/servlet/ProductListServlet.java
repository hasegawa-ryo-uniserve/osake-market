package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductListLogic;

/**
 * 商品リスト一覧サーブレット
 */
@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * dpGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductListLogic logic = new ProductListLogic();
		List<Product> productList = new ArrayList<>();
		productList = logic.findAll();
		if(productList != null) {	// 商品リスト一覧が見つかった場合
			// リクエストスコープに格納してフォワード
			request.setAttribute("productList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
			dispatcher.forward(request, response);
		} else { // 商品一覧が見つからなかった場合
			// エラーメッセージをリクエストスコープに格納してフォワード
			request.setAttribute("errorMsg", "商品一覧が見つかりませんでした");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
