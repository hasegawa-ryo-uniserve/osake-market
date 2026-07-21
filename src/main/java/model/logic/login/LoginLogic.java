package model.logic.login;

import dao.UsersDAO;
import model.Login;
import model.entity.User;

/**
 * ログインの処理をするロジッククラス
 */
public class LoginLogic {
	/**
	 * ログイン実行
	 * 
	 * @param login ログイン情報
	 * @return 会員
	 */
	public User execute(Login login) {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByLogin(login);
		return user;
	}
}
