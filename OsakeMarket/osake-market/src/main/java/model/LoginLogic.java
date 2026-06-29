package model;

import dao.UsersDAO;

/**
 * ログインロジッククラス
 */
public class LoginLogic {
	/**
	 * ログイン実行
	 * 
	 * @param login ログイン情報
	 * @return ユーザー
	 */
	public User execute(Login login) {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByLogin(login);
		return user;
	}
}
