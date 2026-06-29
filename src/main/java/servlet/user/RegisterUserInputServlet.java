package servlet.user;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gender;
import model.entity.User;

/**
 * 会員登録サーブレット
 */
@WebServlet("/register/user")
public class RegisterUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * doGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registerUserInput.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
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
		
		// userインスタンスをリクエストスコープに格納して入力画面にフォワード
		request.setAttribute("inputUser", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registerUserInput.jsp");
		dispatcher.forward(request, response);
	}

}
