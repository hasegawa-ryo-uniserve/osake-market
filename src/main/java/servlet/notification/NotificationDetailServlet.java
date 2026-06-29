package servlet.notification;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.Notification;
import model.logic.notification.NotificationDetailLogic;

/**
 * 通知詳細を表示するサーブレットクラス
 */
@WebServlet("/notification/detail")
public class NotificationDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 通知詳細を表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得
		int notificationId = Integer.parseInt(request.getParameter("notificationId"));

		// 通知詳細を取得する処理
		NotificationDetailLogic logic = new NotificationDetailLogic();
		Notification notification = logic.execute(notificationId);

		// 取得結果によって遷移先を分岐
		if (notification != null) {
			// 取得時
			// リクエストスコープに保存して、通知詳細画面に遷移
			request.setAttribute("notification", notification);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notification/notificationDetail.jsp");
			dispatcher.forward(request, response);
		} else {
			// 取得できなかった場合
			// エラー画面に遷移
			request.setAttribute("errorMsg", "エラーが発生しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
