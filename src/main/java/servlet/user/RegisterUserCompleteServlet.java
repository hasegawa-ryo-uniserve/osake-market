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

/**
 * 会員登録完了サーブレット
 */
@WebServlet("/register/user/complete")
public class RegisterUserCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン済みならマイページにリダイレクト
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			response.sendRedirect(request.getContextPath() + "/mypage");
		}
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registerUserResult.jsp");
		dispatcher.forward(request, response);
	}
}
