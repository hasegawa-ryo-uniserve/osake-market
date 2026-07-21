package servlet.others;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * プライバシーポリシーを表示するサーブレットクラス
 */
@WebServlet("/privacyPolicy")
public class PrivacyPolicyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * プライバシーポリシーを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/privacyPolicy.jsp");
		dispatcher.forward(request, response);
	}

}
