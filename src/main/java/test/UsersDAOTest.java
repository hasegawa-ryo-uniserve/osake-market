package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.UsersDAO;
import model.Gender;
import model.Login;
import model.entity.User;

public class UsersDAOTest {

	public static void main(String[] args) {
//		testFindByLoginOK();	//	ユーザーが見つかる場合のテスト
//		testFindByLoginNG();	//	ユーザーが見つからない場合のテスト
//		testResetPasswordBeforeMailOK(); //	リセット用パスワードが見つかる場合のテスト
//		testResetPasswordBeforeMailNG(); // リセット用パスワードが見つからない場合のテスト
//		testSaveTokenOK(); // パスワードリセットトークンと有効期限を保存できる場合のテスト
//		testSaveTokenNG(); // パスワードリセットトークンと有効期限を保存できない場合のテスト
//		testFindByTokenOK(); //	トークンが見つかる場合のテスト
//		testFindByTokenNG(); //	トークンが見つからない場合のテスト
//		testUpdatePasswordOK(); // 新しいパスワードを更新できた場合のテスト
//		testUpdatePasswordNG(); // 新しいパスワードを更新できなかった場合のテスト
//		testClearTokenOK(); // トークンと有効期限を削除するテスト
//		testClearTokenNG(); // トークンと有効期限を削除に失敗するテスト
//		testRegisterUserOK(); // 会員登録に成功するテスト
//		testRegisterUserNG(); // 会員登録に失敗するテスト
//		testUpdateUserOK();	// 会員情報編集に成功するテスト
//		testUpdateUserNG();	// 会員情報編集に失敗するテスト
		testDeleteUserOK();	// 会員情報を削除(論理削除)するテスト
		testDeleteUserNG();	// 会員情報を削除(論理削除)するテスト
	}

	public static void testFindByLoginOK() {
		Login login = new Login("tomatoakain@gmail.com", "1111");
		UsersDAO dao = new UsersDAO();
		User result = dao.findByLogin(login);
		if (result != null &&
				result.getMail().equals("tomatoakain@gmail.com") &&
				result.getPassword().equals("1111")) {
			System.out.println("testFindByLoginOK：成功しました");
		} else {
			System.out.println("testFindByLoginOK：失敗しました");
		}
	}

	public static void testFindByLoginNG() {
		Login login = new Login("tomatoakain@gmail.com", "1112");
		UsersDAO dao = new UsersDAO();
		User result = dao.findByLogin(login);
		if (result == null) {
			System.out.println("testFindByLoginNG：成功しました");
		} else {
			System.out.println("testFindByLoginNG：失敗しました");
		}
	}

	public static void testResetPasswordBeforeMailOK() {
		String mail = "tomatoakain@gmail.com";
		UsersDAO dao = new UsersDAO();
		User result = dao.findByMail(mail);
		if (result != null && result.getMail().equals("tomatoakain@gmail.com")) {
			System.out.println("testResetPasswordBeforeMailOK：成功しました");
		} else {
			System.out.println("testResetPasswordBeforeMailOK：失敗しました");
		}
	}
	
	public static void testResetPasswordBeforeMailNG() {
		String mail = "test@gmail.com";
		UsersDAO dao = new UsersDAO();
		User result = dao.findByMail(mail);
		if (result == null ) {
			System.out.println("testResetPasswordBeforeMailNG：成功しました");
		} else {
			System.out.println("testResetPasswordBeforeMailNG：失敗しました");
		}
	}
	
	public static void testsaveTokenOK() {
		int userId = 1;
		String token = "test-token-12345";
		LocalDateTime expire = LocalDateTime.of(2026, 5, 12, 0, 0, 0);
		UsersDAO dao = new UsersDAO();
		boolean result = dao.saveToken(userId, token, expire);
		if (result) {
			System.out.println("testsaveTokenOK：成功しました");
		} else {
			System.out.println("testsaveTokenOK：失敗しました");
		}
	}
	
	public static void testSaveTokenNG() {
		int userId = 2;
		String token = "test-token-12345";
		LocalDateTime expire = LocalDateTime.of(2026, 5, 12, 0, 0, 0);
		UsersDAO dao = new UsersDAO();
		boolean result = dao.saveToken(userId, token, expire);
		if (!result) {
			System.out.println("testSaveTokenNG：成功しました");
		} else {
			System.out.println("testSaveTokenNG：失敗しました");
		}
	}
	
	public static void testFindByTokenOK() {
		String token = "f2d5abf1-4766-4b6b-8f56-4fd55bf005ea";
		UsersDAO dao = new UsersDAO();
		User result = dao.findByToken(token);
		if (result != null && result.getToken().equals(token)) {
			System.out.println("testFindByTokenOK：成功しました");
		} else {
			System.out.println("testFindByTokenOK：失敗しました");
		}
	}
	
	public static void testFindByTokenNG() {
		String token = "totaoijoajdofjaofjaoifjoaijfo";
		UsersDAO dao = new UsersDAO();
		User result = dao.findByToken(token);
		if (result == null) {
			System.out.println("testFindByTokenNG：成功しました");
		} else {
			System.out.println("testFindByTokenNG：失敗しました");
		}
	}
	
	public static void testUpdatePasswordOK() {
		String newPassword = "1112";
		UsersDAO dao = new UsersDAO();
		boolean result = dao.updatePassword(1, newPassword);
		if (result) {
			System.out.println("testUpdatePasswordOK：成功しました");
		} else {
			System.out.println("testUpdatePasswordOK：失敗しました");
		}
	}
	
	public static void testUpdatePasswordNG() {
		String newPassword = null;
		UsersDAO dao = new UsersDAO();
		boolean result = dao.updatePassword(1, newPassword);
		if (!result) {
			System.out.println("testUpdatePasswordNG：成功しました");
		} else {
			System.out.println("testUpdatePasswordNG：失敗しました");
		}
	}
	
	public static void testClearTokenOK() {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.clearToken(1);
		if (result) {
			System.out.println("testClearTokenOK：成功しました");
		} else {
			System.out.println("testClearTokenOK：失敗しました");
		}
	}
	
	public static void testClearTokenNG() {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.clearToken(2);
		if (!result) {
			System.out.println("testClearTokenNG：成功しました");
		} else {
			System.out.println("testClearTokenNG：失敗しました");
		}
	}
	
	public static void testRegisterUserOK() {
		User user = new User();
		user.setSei("渡辺");
		user.setMei("花子");
		LocalDate birthday = LocalDate.parse("2000-01-01");
		user.setBirthday(birthday);
		user.setGender(Gender.MALE);
		user.setPostalCode("1111111");
		user.setPrefecture("北海道");
		user.setAddress("札幌市");
		user.setBuilding("花子アパート");
		user.setPhoneNumber("0123456789");
		user.setMail("hanako@gmail.com");
		user.setPassword("2222");
		UsersDAO dao = new UsersDAO();
		boolean result = dao.registerUser(user);
		if (result) {
			System.out.println("testRegisterUserOK：成功しました");
		} else {
			System.out.println("testRegisterUserOK：失敗しました");
		}
	}
	
	public static void testRegisterUserNG() {
		User user = new User();
	    user.setSei("a");
	    user.setMei("a");
	    user.setBirthday(LocalDate.now());
	    user.setGender(Gender.MALE);
	    user.setPostalCode("12345672222");
	    user.setPrefecture("東京");
	    user.setAddress("テスト");
	    user.setBuilding("テスト");
	    user.setPhoneNumber("09012345678");
	    user.setMail("test@test.com");
	    user.setPassword("pass");


		UsersDAO dao = new UsersDAO();
		boolean result = dao.registerUser(user);
		if (!result) {
			System.out.println("testRegisterUserNG：成功しました");
		} else {
			System.out.println("testRegisterUserNG：失敗しました");
		}
	}
	
	public static void testUpdateUserOK() {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByUserId(2);
		user.setSei("金城");
		boolean result = dao.updateUser(user);
		if (result) {
			System.out.println("testUpdateUserOK：成功しました");
		} else {
			System.out.println("testUpdateUserOK：失敗しました");
		}
	}
	
	public static void testUpdateUserNG() {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByUserId(2);
		user.setSei(null);
		boolean result = dao.updateUser(user);
		if (!result) {
			System.out.println("testUpdateUserNG：成功しました");
		} else {
			System.out.println("testUpdateUserNG：失敗しました");
		}
	}
	
	public static void testDeleteUserOK() {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser(2);
		if (result) {
			System.out.println("testDeleteUserOK：成功しました");
		} else {
			System.out.println("testDeleteUserOK：失敗しました");
		}
	}
	
	public static void testDeleteUserNG() {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser(0);
		if (!result) {
			System.out.println("testDeleteUserNG：成功しました");
		} else {
			System.out.println("testDeleteUserNG：失敗しました");
		}
	}
}
