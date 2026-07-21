package servlet.user;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Gender;
import model.entity.User;
import model.logic.user.UpdateUserLogic;

/**
 * 会員情報更新を実行するサーブレットクラス
 */
@WebServlet("/update/user/done")
public class UpdateUserDoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 会員情報更新を実行する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String sei = request.getParameter("sei");
		String mei = request.getParameter("mei");
		String birthdayStr = request.getParameter("birthday");
		LocalDate birthday = LocalDate.parse(birthdayStr);
		String genderStr = request.getParameter("gender");

		Gender gender = null;

		if ("1".equals(genderStr)) {
			gender = Gender.MALE;
		} else if ("2".equals(genderStr)) {
			gender = Gender.FEMALE;
		} else if ("3".equals(genderStr)) {
			gender = Gender.OTHER;
		}
		String postalCode = request.getParameter("postalCode");
		String prefecture = request.getParameter("prefecture");
		String address = request.getParameter("address");
		String building = request.getParameter("building");
		String phoneNumber = request.getParameter("phoneNumber");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();

		// userインスタンスを作成
		User user = new User();
		user.setUserId(userId);
		user.setSei(sei);
		user.setMei(mei);
		user.setBirthday(birthday);
		user.setGender(gender);
		user.setPostalCode(postalCode);
		user.setPrefecture(prefecture);
		user.setAddress(address);
		user.setBuilding(building);
		user.setPhoneNumber(phoneNumber);
		user.setMail(mail);
		user.setPassword(password);
		
		// セッション切れになったら、ログイン画面にリダイレクト
		if (loginUser == null) {
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}
		
		// 会員情報更新処理
		UpdateUserLogic logic = new UpdateUserLogic();
		boolean result = logic.execute(user);
		
		if(result) {
			// フォーム画面にリダイレクト
			session.setAttribute("msg", "会員情報を更新しました");
			response.sendRedirect(request.getContextPath() + "/update/user/form");
		} else {
			// エラー画面にフォワード
			request.setAttribute("errorMsg", "会員情報の更新に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
