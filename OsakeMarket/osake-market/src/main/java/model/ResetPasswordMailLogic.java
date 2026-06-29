package model;

import java.time.LocalDateTime;
import java.util.UUID;

import dao.UsersDAO;

/**
 * パスワードリセットロジック
 */
public class ResetPasswordMailLogic {
	/**
	 * メールを送る
	 * 
	 * @param mail メールアドレス
	 * @return ユーザー
	 */
	public boolean sendMail(String mail) {
		// パスワードリセット用のメールをDBから探す
		UsersDAO dao = new UsersDAO();
		User user = dao.findByMail(mail);
		if (user == null) {
			return false;
		}
		// パスワードリセットトークンと有効期限をDBに設定
		String token = UUID.randomUUID().toString();
		LocalDateTime expire = LocalDateTime.now().plusMinutes(30);
		boolean successSavetToken = dao.saveToken(user.getUserId(), token, expire);
		if (!successSavetToken) {
			return false;
		}
		
		// URL作成
        String url = "http://localhost:8080/osake-market/reset/password/update?token=" + token;
        
        // メール送信
        MailSender mailSender = new MailSender();
        boolean successSendMail = mailSender.send(mail, url);
        return successSendMail;
	}
}
