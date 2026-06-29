package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductListLogic;
import model.ProductSerchedByCategoryNameAndProductNameAndSortLogic;
import model.ProductSerchedByCategoryNameAndSortLogic;
import model.ProductSerchedByProductNameAndSortLogic;
import model.ProductSerchedBySortLogic;

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
		request.setCharacterEncoding("UTF-8");
		String categoryName = request.getParameter("categoryName");
		String productName = request.getParameter("productName");
		String sort = request.getParameter("sort");
		
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
			productList = cpsLogic.execute(categoryName, productName, sort);			
		} else if ((categoryName != null && !categoryName.isEmpty()) && 
				(productName == null || productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) { 
			// リクエストパラメータがカテゴリ名とソートのとき
			productList = csLogic.execute(categoryName, sort);
		} else if ((categoryName == null || categoryName.isEmpty()) && 
				(productName != null && !productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) {
			// リクエストパラメータが商品名とソートのとき
			productList = psLogic.execute(productName, sort);
		} else if ((categoryName == null || categoryName.isEmpty()) &&
				(productName == null || productName.isEmpty()) &&
				(sort != null && !sort.isEmpty())) {
			// リクエストパラメータがソートのみのとき
			productList = sLogic.execute(sort);
		} else {
			productList = plLogic.findAll();
		}
		
		// --- DB取得後に空かどうかを判定 ---
//		if (productList != null && !productList.isEmpty()) {
			// リクエストスコープに格納してフォワード
			request.setAttribute("productList", productList);		
//		} else { // 商品一覧が見つからなかった場合
			// エラーメッセージをリクエストスコープに格納してフォワード
//			request.setAttribute("noList", "商品一覧が見つかりませんでした");
//		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
		dispatcher.forward(request, response);
	}
}
