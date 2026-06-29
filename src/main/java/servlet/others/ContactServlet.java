package servlet.others;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.logic.contact.ContactLogic;

/**
 * お問い合わせのページに関するサーブレットクラス
 */
@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * お問い合わせのページを表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp");
		dispatcher.forward(request, response);
	}
	
	/**
	 * お問い合わせフォームの内容を処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		
		// お問い合わせフォーム用のメール送信処理の実行
		ContactLogic logic = new ContactLogic();
		boolean isValid = logic.sendContactMail(name, mail, category, content);
		
		if(isValid) {
			request.setAttribute("msg", "お問い合わせ内容が送信されました。");
		} else {
			// エラーメッセージをリクエストスコープに格納して、メールアドレス入力画面にフォワード
			request.setAttribute("msg", "メール送信に失敗しました。");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp");
		dispatcher.forward(request, response);
	}
}