package servlet.review;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Review;
import model.entity.User;
import model.logic.review.ReviewLogic;

/**
 * 商品レビューサーブレットクラス
 */
@WebServlet("/review/create")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品レビューを投稿する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		int productId = Integer.parseInt(request.getParameter("productId"));
		String starString = request.getParameter("star");
		String reviewText = request.getParameter("reviewText");
		
		// 未入力チェック
		if(starString == null) {
			request.setAttribute("errorMsg", "星を選択してください");
			RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		if(reviewText == null || reviewText.isEmpty()) {
			request.setAttribute("errorMsg", "コメントを入力してください");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		int star = Integer.parseInt(starString);
		
		// ログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		// レビューインスタンスを作成
		Review review = new Review();
		review.setUserId(loginUser.getUserId());
		review.setProductId(productId);
		review.setStar(star);
		review.setReviewText(reviewText);
		
		// 実行処理
		ReviewLogic logic = new ReviewLogic();
		boolean result = logic.createReview(review);
		
		// 実行結果によって遷移先を分岐
		if(result) {
			response.sendRedirect(request.getContextPath() + "/product/detail?productId=" + productId);
		} else {
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
