package model;

import java.time.LocalDateTime;

import dao.UsersDAO;

/**
 * パスワードリセットロジック 
 */
public class ResetPasswordUpdateLogic {
	/**
	 * リセットパスワードURLのトークンが正しいか確認する
	 * 
	 * @param token トークン
	 * @return ユーザー
	 */
	public User checkToken(String token) {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByToken(token);
		
		// トークンが存在するかチェック
		if (user == null) {
			return null;
		}
		
		// トークンの期限チェック
		LocalDateTime now = LocalDateTime.now();
		if (user.getExpire() == null) {
			return null;
		}
		if (user.getExpire().isBefore(now)) {
			return null;	// 期限切れ
		}
		
		return user;
	}
	
	/**
	 * パスワード更新
	 * 
	 * @param token トークン
	 * @param newPassword 新しいパスワード
	 * @return 実行結果
	 */
	public boolean updatePassword(String token, String newPassword) {
		UsersDAO dao = new UsersDAO();
		
		// トークンが一致するか確認
		User user = checkToken(token);
		
		// ユーザーが存在しない、あるいはトークンの有効期限が切れている場合は失敗
		if (user == null) {
			return false;
		}
		
		// パスワード更新
		boolean result = dao.updatePassword(user.getUserId(), newPassword);
		if (!result) {
			return false;
		}
		
		// トークンを無効化（使いまわし防止）
		dao.clearToken(user.getUserId());
		
		return true;
	}
}
