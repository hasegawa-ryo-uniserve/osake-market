package servlet.others;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 特定商取引法のページを表示するサーブレットクラス
 */
@WebServlet("/legal")
public class LegalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 特定商取引法のページを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/legal.jsp");
		dispatcher.forward(request, response);
	}

}
