package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.logic.user.ResetPasswordMailLogic;

/**
 * パスワード再設定 メール送信前サーブレット
 */
@WebServlet("/reset/password/mail")
public class ResetPasswordMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * パスワード再設定フォーム画面に遷移
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordBeforeMail.jsp");
		dispatcher.forward(request, response);
	}

	/**
     * パスワード再設定メールを送信する
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		String mail = request.getParameter("mail");
		
		// リセットパスワード用のメール送信処理の実行
		ResetPasswordMailLogic logic = new ResetPasswordMailLogic();
		boolean isValid = logic.sendMail(mail);
		
		// メール送信の結果によって処理を分岐
		if (isValid) { // メール送信成功時
			// フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordAfterMail.jsp");
			dispatcher.forward(request, response);
		} else { // メール送信失敗時
			// エラーメッセージをリクエストスコープに格納して、メールアドレス入力画面にフォワード
			request.setAttribute("errorMsg", "メール送信に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resetPasswordBeforeMail.jsp");
			dispatcher.forward(request, response);
		}
	}

}
