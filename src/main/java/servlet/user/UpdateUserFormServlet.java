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
import model.logic.user.UpdateUserLogic;

/**
 * 会員情報編集ページに遷移するサーブレットクラス
 */
@WebServlet("/update/user/form")
public class UpdateUserFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 会員情報編集ページに遷移する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン情報を取得する
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// 会員情報を取得する処理
		UpdateUserLogic logic = new UpdateUserLogic();
		User user = logic.findUser(userId);
		
		// 取得した会員情報をリクエストスコープに保存してフォワード
		if(user != null) {
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/updateUserForm.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		
	}


}
