package servlet.others;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 会社概要のページを表示するサーブレットクラス
 */
@WebServlet("/company")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 会社概要のページを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/company.jsp");
		dispatcher.forward(request, response);
	}

}