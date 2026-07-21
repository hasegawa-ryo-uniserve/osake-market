package model.logic.user;

import dao.UsersDAO;
import model.entity.User;

/**
 * 会員登録ロジッククラス
 */
public class RegisterUserLogic {
	/**
	 * 会員登録をする
	 * 
	 * @param user ユーザー
	 * @return 実行結果
	 */
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		
		// 既存ユーザーが登録されているか確認
		String mail = user.getMail();
		User existUser = dao.findByMail(mail);
		if (existUser != null) {
			return false;
		}
		
		// ユーザーをDBに登録する
		boolean isValid = dao.registerUser(user);
		if (!isValid) {
			return false;
		}
		return true;
	}
}
