package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordComplete.jsp");
		dispatcher.forward(request, response);
	}

}
