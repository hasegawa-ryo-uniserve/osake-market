package test;

import java.util.List;

import dao.NotificationsDAO;
import model.entity.Notification;

public class NotificationsDAOTest {
	public static void main(String[] args) {
//		testFindAllOK();	// 通知一覧を取得するテスト
//		testFindAllNG();	// 通知一覧を取得失敗するテスト
		testFindByNotificationIdOK();	// 通知IDをもとに通知を取得するテスト
		testFindByNotificationIdNG();	// 通知IDをもとに通知を取得失敗するテスト
	}
	
	public static void testFindAllOK() {
		NotificationsDAO dao = new NotificationsDAO();
		List<Notification> notificationList = dao.findAll(1, 10, 2);
		if(notificationList != null && !notificationList.isEmpty()) {
			System.out.println("testFindAllOK：成功しました");
		} else {
			System.out.println("testFindAllOK：失敗しました");
		}
	}
	
	public static void testFindAllNG() {
		NotificationsDAO dao = new NotificationsDAO();
		Integer i = null;
		List<Notification> notificationList = dao.findAll(i, 10, 2);
		notificationList = null;
		if(notificationList == null || notificationList.isEmpty()) {
			System.out.println("testFindAllNG：成功しました");
		} else {
			System.out.println("testFindAllNG：失敗しました");
		}
	}
	
	public static void testFindByNotificationIdOK() {
		NotificationsDAO dao = new NotificationsDAO();
		Notification notification = dao.findByNotificationId(1);
		if(notification != null) {
			System.out.println("testFindByNotificationIdOK：成功しました");
		} else {
			System.out.println("testFindByNotificationIdOK：失敗しました");
		}
	}
	
	public static void testFindByNotificationIdNG() {
		NotificationsDAO dao = new NotificationsDAO();
		Notification notification = dao.findByNotificationId(0);
		if(notification == null) {
			System.out.println("testFindByNotificationIdNG：成功しました");
		} else {
			System.out.println("testFindByNotificationIdNG：失敗しました");
		}
	}
}
