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
 * パスワード更新完了サーブレット
 */
@WebServlet("/reset/password/update/complete")
public class ResetPasswordUpdateCompleteServlet extends HttpServlet {
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
			return;
		}
		
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordComplete.jsp");
		dispatcher.forward(request, response);
	}

}
