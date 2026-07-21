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

/**
 * 会員情報確認サーブレット
 */
@WebServlet("/register/user/confirm")
public class RegisterUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * URLから直接アクセスした場合→会員情報入力画面にリダイレクト
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン済みならマイページにリダイレクト
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser != null) {
			response.sendRedirect(request.getContextPath() + "/mypage");
			return;
		}
		
		response.sendRedirect(request.getContextPath() + "/register/user");
	}
	
	/**
	 * doPostメソッド
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

		// userインスタンスを作成
		User user = new User();
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

		// userインスタンスをリクエストスコープに格納して確認画面にフォワード
		request.setAttribute("registerUser", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registerUserConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
