package dao;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import model.Gender;
import model.Login;
import model.entity.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersDAOTest {
	// DAOを生成
	private final UsersDAO dao = new UsersDAO();
	
	@DisplayName("No.1 ログイン 正常系")
	@Test
	@Order(1)
	void testFindByLoginOK() {
		Login login = new Login("tomatoakain@gmail.com", "Tanaka11#Pass");
		User user = dao.findByLogin(login);
		assertNotNull(user);
	}
	
	@DisplayName("No.2 ログイン 異常系")
	@Test
	@Order(2)
	void testFindByLoginNG2() {
		Login login = new Login("example@gmail.com", "Tanaka11#Pass");
		User user = dao.findByLogin(login);
		assertNull(user);
	}
	
	@DisplayName("No.3 ログイン 異常系")
	@Test
	@Order(3)
	void testFindByLoginNG3() {
		Login login = new Login("tomatoakain@gmail.com", "9999");
		User user = dao.findByLogin(login);
		assertNull(user);
	}
	
	@DisplayName("No.4 ログイン 異常系")
	@Test
	@Order(4)
	void testFindByLoginNG4() {
		Login login = new Login("suzuki@gmail.com", "Suzuki22#Pass");
		User user = dao.findByLogin(login);
		assertNull(user);
	}
	
	@DisplayName("No.5 メール検索 正常系")
	@Test
	@Order(5)
	void testFindByMailOK() {
		User user = dao.findByMail("tomatoakain@gmail.com");
		assertNotNull(user);
	}
	
	@DisplayName("No.6 メール検索 異常系")
	@Test
	@Order(6)
	void testFindByMailNG() {
		User user = dao.findByMail("example@gmail.com");
		assertNull(user);
	}
	
	@DisplayName("No.7 トークン保存 正常系")
	@Test
	@Order(7)
	void testSaveTokenOK() {
		boolean result = dao.saveToken(1, "aaaa", 
									LocalDateTime.now().plusMinutes(30));
		assertTrue(result);
	}
	
	@DisplayName("No.8 トークン保存 異常系")
	@Test
	@Order(8)
	void testSaveTokenNG() {
		boolean result = dao.saveToken(0, "aaaa", 
									LocalDateTime.now().plusMinutes(30));
		assertFalse(result);
	}

	@DisplayName("No.9 トークン検索 正常系")
	@Test
	@Order(9)
	void testFindByTokenOK() {
		User user = dao.findByToken("bbbb");
		assertNotNull(user);
	}
	
	@DisplayName("No.10 トークン検索 異常系")
	@Test
	@Order(10)
	void testFindByTokenNG() {
		User user = dao.findByToken("zzzz");
		assertNull(user);
	}
	
	@DisplayName("No.11 パスワード更新 正常系")
	@Test
	@Order(11)
	void testUpdatePasswordOK() {
		boolean result = dao.updatePassword(3, "0000");
		assertTrue(result);
	}
	
	@DisplayName("No.12 パスワード更新 異常系")
	@Test
	@Order(12)
	void testUpdatePasswordNG() {
		boolean result = dao.updatePassword(0, "0000");
		assertFalse(result);
	}
	
	@DisplayName("No.13 トークン削除 正常系")
	@Test
	@Order(13)
	void testClearTokenOK() {
		boolean result = dao.clearToken(1);
		assertTrue(result);
	}
	
	@DisplayName("No.14 トークン削除 異常系")
	@Test
	@Order(14)
	void testClearTokenNG() {
		boolean result = dao.clearToken(0);
		assertFalse(result);
	}
	
	@DisplayName("No.15 会員登録 正常系")
	@Test
	@Order(15)
	void testRegisterUserOK() {
		User user = new User(
			    null,	// userId（DBで採番）
			    "渡辺",	// 姓
			    "花子",	// 名
			    LocalDate.of(2000, 1, 1),	// 生年月日
			    Gender.FEMALE,	// 性別
			    "1234567",	// 郵便番号
			    "東京都",	// 都道府県
			    "八王子市1-1-1",	// 住所
			    "八王子ハイツ102",	// 建物
			    "09012345678",		// 電話番号
			    "hanako@gmail.com",	// メール
			    "Watanabe77#Pass",	// パスワード
			    null,	// token
			    null,	// expire
			    null,	// createDate
			    null,	// modDate
			    null,	// delDate
			    false	// delFlag
			);
		boolean result = dao.registerUser(user);
		assertTrue(result);
	}
	
	@DisplayName("No.16 会員登録 異常系")
	@Test
	@Order(16)
	void testRegisterUserNG() {
		User user = new User(
			    null,
			    "渡辺",
			    "花子",
			    LocalDate.of(2000, 1, 1),
			    Gender.FEMALE,
			    "1234567",
			    "東京都",
			    "八王子市1-1-1",
			    "八王子ハイツ102",
			    "09012345678",
			    "tomatoakain@gmail.com",	// すでにDBに登録されているメールアドレス
			    "Watanabe77#Pass",
			    null,
			    null,
			    null,
			    null,
			    null,
			    false
			);
		boolean result = dao.registerUser(user);
		assertFalse(result);
	}
	
	@DisplayName("No.17 会員検索 正常系")
	@Test
	@Order(17)
	void testFindByUserIdOK() {
		User user = dao.findByUserId(1);
		assertNotNull(user);
	}
	
	@DisplayName("No.18 会員検索 異常系")
	@Test
	@Order(18)
	void testFindByUserIdNG() {
		User user = dao.findByUserId(0);
		assertNull(user);
	}
	
	@DisplayName("No.19 会員更新 正常系")
	@Test
	@Order(19)
	void testUpdateUserOK() {
		User user = dao.findByUserId(5);
		user.setSei("鮫島");
		boolean result = dao.updateUser(user);
		assertTrue(result);
	}
	
	@DisplayName("No.20 会員更新 異常系")
	@Test
	@Order(20)
	void testUpdateUserNG() {
		User user = dao.findByUserId(5);
		user.setUserId(999999);
		boolean result = dao.updateUser(user);
		assertFalse(result);
	}
	
	@DisplayName("No.21 退会 正常系")
	@Test
	@Order(21)
	void testDeleteUserOK() {
		boolean result = dao.deleteUser(6);
		assertTrue(result);
	}
	
	@DisplayName("No.22 退会 異常系")
	@Test
	@Order(22)
	void testDeleteUserNG() {
		boolean result = dao.deleteUser(0);
		assertFalse(result);
	}

}
