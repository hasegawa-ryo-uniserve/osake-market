package model;

/**
 * ログインエンティティ
 */
public class Login {
	/** メールアドレス */
	private String mail;
	
	/** パスワード */
	private String password;
	
	/**
	 * コンストラクタ
	 * 
	 * @param mail メールアドレス
	 * @param password パスワード
	 */
	public Login(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}
	
	/**
	 * メールアドレスを取得
	 * 
	 * @return メールアドレス
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * メールアドレスを設定
	 * 
	 * @param mail メールアドレス
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * パスワードを取得
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * パスワードを設定
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
