package test;

import model.Login;
import model.entity.User;
import model.logic.login.LoginLogic;

/**
 * ログインロジックテストクラス
 */
public class LoginLogicTest {

	public static void main(String[] args) {
		testExecuteOK(); // ログイン成功のテスト
		testExecuteNG(); // ログイン失敗のテスト
	}
	public static void testExecuteOK() {
		Login login = new Login("tomatoakain@gmail.com", "1111");
		LoginLogic logic = new LoginLogic();
		User user = logic.execute(login);
		if (user != null) {
			System.out.println("testExecuteOK：成功しました");
		} else {
			System.out.println("testExecuteOK：失敗しました");
		}
	}
	public static void testExecuteNG() {
		Login login = new Login("tomatoakain@gmail.com", "1112");
		LoginLogic logic = new LoginLogic();
		User user = logic.execute(login);
		if (user == null) {
			System.out.println("testExecuteNG：成功しました");
		} else {
			System.out.println("testExecuteNG：失敗しました");
		}
	}
}
