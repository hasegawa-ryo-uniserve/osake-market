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
import model.logic.user.WithdrawUserLogic;

/**
 * 退会処理をするサーブレットクラス
 */
@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 退会処理をする
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// 退会処理
		WithdrawUserLogic logic = new WithdrawUserLogic();
		boolean result = logic.execute(userId);
		
		if(result) {
			// 退会処理成功時
		
			// セッションスコープを破棄
			session.removeAttribute("loginUser");
			
			// ホーム画面にリダイレクト
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// エラー画面にフォワード
			request.setAttribute("errorMsg", "退会処理に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
