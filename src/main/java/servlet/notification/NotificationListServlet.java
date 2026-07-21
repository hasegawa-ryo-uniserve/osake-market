package servlet.notification;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.Notification;
import model.entity.User;
import model.logic.notification.NotificationListLogic;

/**
 * 通知一覧を表示するサーブレットクラス
 */
@WebServlet("/notification/list")
public class NotificationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 通知一覧を表示する
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションスコープからログイン情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		int userId = loginUser.getUserId();
		
		// 通知のページ数を取得
		int page = 1;
		
		String pageParam = request.getParameter("page");
		if(pageParam != null) {
		    page = Integer.parseInt(pageParam);
		}
		
		// 通知一覧を取得する
		NotificationListLogic logic = new NotificationListLogic();
		List<Notification> notificationList = logic.findAll(userId, page);
		
		// 通知総件数を取得する
		int totalCount = logic.countAll(loginUser.getUserId());
		int totalPage = (int)Math.ceil((double)totalCount / 10);
		
		// 取得結果によって遷移先を分岐
		if(notificationList != null) {
			// 取得時
			// リクエストスコープに保存して、通知一覧画面に遷移
			request.setAttribute("notificationList", notificationList);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/notification/notificationList.jsp");
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
