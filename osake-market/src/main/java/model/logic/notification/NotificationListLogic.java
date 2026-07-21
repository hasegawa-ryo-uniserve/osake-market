package model.logic.notification;

import java.util.List;

import dao.NotificationsDAO;
import model.entity.Notification;

/**
 * 通知一覧を取得するロジッククラス
 */
public class NotificationListLogic {
	/**
	 * 通知一覧を取得する
	 * 
	 * @param userId 会員ID
	 * @param page 第何ページか
	 * @return 通知一覧
	 */
	public List<Notification> findAll(int userId, int page) {
		// 1ページに表示する件数
		final int pageSize = 10;
		// 「何件スキップするか」を計算
		int offset = (page - 1) * pageSize;
		
		NotificationsDAO dao = new NotificationsDAO();
		List<Notification> notificationList = dao.findAll(userId, offset, pageSize);
		
		return notificationList;
	}
	
	/**
	 * 通知の総件数を取得
	 * 
	 * @param userId 会員ID
	 * @return 通知総件数
	 */
	public int countAll(int userId) {
		NotificationsDAO dao = new NotificationsDAO();
		return dao.countAll(userId);
	}
}
