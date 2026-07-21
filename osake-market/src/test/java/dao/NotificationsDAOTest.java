package dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.entity.Notification;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotificationsDAOTest {
	// DAOを生成
	private final NotificationsDAO dao = new NotificationsDAO();
	
	@DisplayName("No.1 ページネーション用に件数取得 正常系")
	@Test
	@Order(1)
	void testCountAll() {
		int count = dao.countAll(1);
		assertEquals(11, count);
	}

	@DisplayName("No.2 通知一覧を取得する 正常系")
	@Test
	@Order(2)
	void testFindAllOK() {
		List<Notification> notificationList = dao.findAll(1, 0, 10);
		assertFalse(notificationList.isEmpty());
	}

	@DisplayName("No.3 通知IDにもとづいて通知を取得する 正常系")
	@Test
	@Order(3)
	void testFindByNotificationIdOK() {
		Notification notification = dao.findByNotificationId(1);
		assertNotNull(notification);
	}
	
	@DisplayName("No.4 通知IDにもとづいて通知を取得する 異常系")
	@Test
	@Order(4)
	void testFindByNotificationIdNG() {
		Notification notification = dao.findByNotificationId(0);
		assertNull(notification);
	}

}
