package model.logic.contact;

import model.MailSender;

/**
 * お問い合わせを受け付けるロジッククラス
 */
public class ContactLogic {
	/**
	 * お問い合わせを受け付ける
	 */
	public boolean sendContactMail(String name, String mail, String category, String content) {
		 // カテゴリを日本語に変換
		category = switch (category) {
	        case "product" -> "商品について";
	        case "order" -> "注文について";
	        case "delivery" -> "配送について";
	        case "payment" -> "お支払いについて";
	        case "return" -> "返品・交換について";
	        case "account" -> "会員登録・ログインについて";
	        case "request" -> "ご要望・ご意見";
	        case "other" -> "その他";
	        default -> "未分類";
		};
		
		// 注文完了メールを送信
        MailSender mailSender = new MailSender();
        boolean successSendMail = mailSender.sendContactMail(name, mail, category, content);
        
        if(!successSendMail) {
        	System.out.println("お問い合わせメールの送信に失敗しました。mail=" + mail);
        	return false;
        }
        
        return true;
	}
}
