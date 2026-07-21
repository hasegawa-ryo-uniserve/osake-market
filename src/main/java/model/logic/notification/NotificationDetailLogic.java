package model.logic.notification;

import dao.NotificationsDAO;
import model.entity.Notification;

/**
 * 通知詳細を表示するロジッククラス
 */
public class NotificationDetailLogic {
	/**
	 * 通知詳細を表示する
	 * 
	 * @param notificationId 通知ID
	 * @return 通知
	 */
	public Notification execute(int notificationId) {
		NotificationsDAO dao = new NotificationsDAO();
		Notification notification = dao.findByNotificationId(notificationId);
		return notification;
	}
}
