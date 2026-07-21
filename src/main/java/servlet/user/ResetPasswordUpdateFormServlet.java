package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.User;
import model.logic.user.ResetPasswordUpdateLogic;

/**
 * パスワード変更サーブレット
 */
@WebServlet("/reset/password/update")
public class ResetPasswordUpdateFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン済みならマイページにリダイレクト
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			response.sendRedirect(request.getContextPath() + "/mypage");
			return;
		}
		
		// リクエストパラメータの取得
		String token = request.getParameter("token");

		// トークン確認処理
		ResetPasswordUpdateLogic logic = new ResetPasswordUpdateLogic();
		User user = logic.checkToken(token);

		// 処理の結果によって処理を分岐
		if (user != null) { // トークンの確認成功時
			// トークンをJSPに渡す
			request.setAttribute("token", token);

			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordForm.jsp");
			dispatcher.forward(request, response);
		} else { // トークンの確認失敗時
			// エラー画面にフォワード
			request.setAttribute("errorMsg", "このURLは無効です。もう一度パスワード再設定を行ってください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		String token = request.getParameter("token");

		// パスワードチェック処理
		if (newPassword == null || newPassword.isBlank()) {
			request.setAttribute("errorMsg", "新しいパスワードを入力して下さい");
			request.setAttribute("token", token);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordForm.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if (!(newPassword.equals(confirmPassword))) { // パスワードが確認用と一致しない
			// エラーメッセージをリクエストスコープに格納してフォワード
			request.setAttribute("errorMsg", "パスワードが一致しませんでした");
			request.setAttribute("token", token);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordForm.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// パスワードが一致したら、パスワード変更処理
		ResetPasswordUpdateLogic logic = new ResetPasswordUpdateLogic();
		boolean isValid = logic.updatePassword(token, newPassword);

		// 処理の結果によって処理を分岐
		if (isValid) { // パスワード更新成功時
			// 更新完了画面にリダイレクト
			response.sendRedirect(request.getContextPath() + "/reset/password/update/complete");
		} else { // パスワード更失敗時
			// エラー画面にフォワード
			request.setAttribute("errorMsg", "パスワード更新に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
