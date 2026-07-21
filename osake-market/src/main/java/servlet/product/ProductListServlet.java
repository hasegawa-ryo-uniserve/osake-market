package servlet.product;

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
import model.logic.product.ProductListLogic;
import model.logic.product.ProductSerchedByCategoryNameAndProductNameAndSortLogic;
import model.logic.product.ProductSerchedByCategoryNameAndSortLogic;
import model.logic.product.ProductSerchedByProductNameAndSortLogic;
import model.logic.product.ProductSerchedBySortLogic;

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
		// リクエストパラメータを取得
		String categoryName = request.getParameter("categoryName");
		String productName = request.getParameter("productName");
		String sort = request.getParameter("sort");
		
		// セッションからログイン情報を取得する
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		Integer userId = null;
	    if (loginUser != null) {
	        userId = loginUser.getUserId();
	    }
		
		
		ProductListLogic plLogic = new ProductListLogic();
		ProductSerchedBySortLogic sLogic = new ProductSerchedBySortLogic();
		ProductSerchedByCategoryNameAndSortLogic csLogic = new ProductSerchedByCategoryNameAndSortLogic();
		ProductSerchedByProductNameAndSortLogic psLogic = new ProductSerchedByProductNameAndSortLogic();
		
		ProductSerchedByCategoryNameAndProductNameAndSortLogic cpsLogic = new ProductSerchedByCategoryNameAndProductNameAndSortLogic();
		List<Product> productList;
		
			// リクエストパラメータが3つすべてある場合
		if ((categoryName != null && !categoryName.isEmpty()) && 
				(productName != null && !productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) {
			productList = cpsLogic.execute(categoryName, productName, sort, userId);			
		} else if ((categoryName != null && !categoryName.isEmpty()) && 
				(productName == null || productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) { 
			// リクエストパラメータがカテゴリ名とソートのとき
			productList = csLogic.execute(categoryName, sort, userId);
		} else if ((categoryName == null || categoryName.isEmpty()) && 
				(productName != null && !productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) {
			// リクエストパラメータが商品名とソートのとき
			productList = psLogic.execute(productName, sort, userId);
		} else if ((categoryName == null || categoryName.isEmpty()) &&
				(productName == null || productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) {
			// リクエストパラメータがソートのみのとき
			productList = sLogic.execute(sort, userId);
		} else {
			productList = plLogic.findAll(userId);
		}
		
		// --- DB取得後に空かどうかを判定 ---
//		if (productList != null && !productList.isEmpty()) {
			// リクエストスコープに格納してフォワード
			request.setAttribute("productList", productList);
//		} else { // 商品一覧が見つからなかった場合
			// エラーメッセージをリクエストスコープに格納してフォワード
//			request.setAttribute("noList", "商品一覧が見つかりませんでした");
//		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/product/productList.jsp");
		dispatcher.forward(request, response);
	}
}
