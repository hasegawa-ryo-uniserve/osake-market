package model.logic.user;

import dao.UsersDAO;
import model.entity.User;

/**
 * 会員情報を編集するロジッククラス
 */
public class UpdateUserLogic {
	/**
	 * 会員情報を取得する
	 * 
	 * @param userId 会員ID
	 * @return 会員情報
	 */
	public User findUser(int userId) {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByUserId(userId);
		
		return user;
	}
	
	/**
	 * 会員情報を更新する
	 * 
	 * @param user 会員情報
	 * @return 実行結果
	 */
	public boolean execute(User user) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.updateUser(user);
		
		return result;
	}
}
