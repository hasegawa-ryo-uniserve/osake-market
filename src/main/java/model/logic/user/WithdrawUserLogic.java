package model.logic.user;

import dao.UsersDAO;

/**
 * 退会処理用のロジッククラス
 */
public class WithdrawUserLogic {
	/**
	 * 退会処理
	 * 
	 * @param userId 会員ID
	 * @return 実行結果
	 */
	public boolean execute(int userId) {
		UsersDAO dao = new UsersDAO();
		boolean result = dao.deleteUser(userId);
		
		return result;
	}
}
