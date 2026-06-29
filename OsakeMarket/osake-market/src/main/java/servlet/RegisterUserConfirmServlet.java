package servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gender;
import model.User;

/**
 * 会員情報確認サーブレット
 */
@WebServlet("/register/user/confirm")
public class RegisterUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerUserConfirm.jsp");
		dispatcher.forward(request, response);
	}

}
