package servlet.login;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Login;
import model.entity.User;
import model.logic.login.LoginLogic;

/**
 * ログインサーブレットクラス
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ログイン画面に遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン済みならホーム画面に遷移
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		if (loginUser != null) {
			response.sendRedirect(request.getContextPath() + "/");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ログイン処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		// ログイン処理の実行
		Login login = new Login(mail, password);
		LoginLogic logic = new LoginLogic();
		User user = logic.execute(login);
		
		// ログイン処理の結果によって処理を分岐
		if (user != null) { // ログイン成功時
			// セッションスコープにユーザーを保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			// リダイレクト
			response.sendRedirect(request.getContextPath() + "/");
		} else { // ログイン失敗時
			// ログイン画面にフォワード
			request.setAttribute("errorMsg", "ログインに失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
