package model;

import java.text.NumberFormat;
import java.util.List;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.entity.CartItem;

/**
 * メール送信クラス（Jakarta版）
 */
public class MailSender {
	
//	// ★ GASのURL（デプロイ後に貼る）
//    private static final String GAS_URL = "https://script.google.com/macros/s/AKfycbw3CFKq7uj-vjugye4cdNLYja8XpvmzUwxU1E46G0FsTrr3s1l5A_o47c7NZ1l_1QB8/exec";
//	
//	/**
//     * パスワードリセットメール（GAS経由）
//     */
//    public boolean send(String mail, String url) {
//
//        String subject = "パスワード再設定";
//        String body =
//            "以下のURLからパスワードを再設定してください:\n\n" + url;
//
//        return send(mail, subject, body);
//    }

    /**
     * メール送信(パスワードリセットメール)
     *
     * @param mail 送信先メールアドレス
     * @param url  本文に入れるURL
     */
    public boolean send(String mail, String url) {
    	
    	// 送信元メールアドレス
        final String from = "hasegawa.uniserve@gmail.com";
        // Gmailアプリパスワード
        final String password = "cfcm kcxw iakr aaap";
        
        // SMTP設定
        Properties props = new Properties();
        // SMTP認証有効
        props.put("mail.smtp.auth", "true");
        // TLS有効(通信を暗号化する)
        props.put("mail.smtp.starttls.enable", "true");
        // SMTPサーバーの設定
        props.put("mail.smtp.host", "smtp.gmail.com");
        // ポート番号指定
        props.put("mail.smtp.port", "587");
        
        // メール送信用セッションを作る
        Session session = Session.getInstance(props,
            new Authenticator() {
        		// メソッドオーバーライド
                protected PasswordAuthentication getPasswordAuthentication() {
                	// 認証情報返却
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
        	// メールオブジェクト作成
            Message message = new MimeMessage(session);
            // 差出人を設定
            message.setFrom(new InternetAddress(from));

            // 宛先設定
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail)
            );
            
            // 件名設定
            message.setSubject("パスワード再設定");
            
            // 本文設定
            message.setText(
                "以下のURLからパスワードを再設定してください:\n\n" + url
            );
            
            // メール送信
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * メール送信(注文完了メール)
     *
     * @param mail 送信先メールアドレス
     * @param orderId 注文ID
     * @param cart カート
     * @param totalAmount 合計金額
     * @param paymentMethod 支払方法
     * @return 実行結果
     */
    public boolean sendOrderCompleteMail(String mail, int orderId, List<CartItem> cart,
    										int totalAmount, String paymentMethod) {
    	// 送信元メールアドレス
        final String from = "hasegawa.uniserve@gmail.com";
        // Gmailアプリパスワード
        final String password = "cfcm kcxw iakr aaap";
        
        // SMTP設定
        Properties props = new Properties();
        // SMTP認証有効
        props.put("mail.smtp.auth", "true");
        // TLS有効(通信を暗号化する)
        props.put("mail.smtp.starttls.enable", "true");
        // SMTPサーバーの設定
        props.put("mail.smtp.host", "smtp.gmail.com");
        // ポート番号指定
        props.put("mail.smtp.port", "587");
        
        // メール送信用セッションを作る
        Session session = Session.getInstance(props,
            new Authenticator() {
        		// メソッドオーバーライド
                protected PasswordAuthentication getPasswordAuthentication() {
                	// 認証情報返却
                    return new PasswordAuthentication(from, password);
                }
            });
        
        try {
        	// メールオブジェクト作成
            Message message = new MimeMessage(session);
            // 差出人を設定
            message.setFrom(new InternetAddress(from));

            // 宛先設定
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail)
            );
            
            // 件名設定
            message.setSubject("ご注文ありがとうございました");
            
            // メール本文設定
            StringBuilder body = new StringBuilder();
            
            // 三桁区切り
            NumberFormat nf = NumberFormat.getInstance();

            body.append("ご注文を受け付けました。\n\n");
            body.append("注文番号：").append(orderId).append("\n");
            body.append("支払方法：").append(paymentMethod).append("\n");
            body.append("合計金額：").append(nf.format(totalAmount)).append("円\n\n");

            body.append("■ご注文商品\n");

            for(CartItem item : cart) {
                body.append(item.getProduct().getProductName())
                    .append(" × ")
                    .append(item.getQuantity())
                    .append("\n");
            }
            
            body.append("\n商品の発送までしばらくお待ちください。");
            
            // 本文設定
            message.setText(
                body.toString()
            );
            
            // メール送信
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * メール送信(お問い合わせ内容メール)
     *
     * @param mail 送信先メールアドレス
     * @param orderId 注文ID
     * @param cart カート
     * @param totalAmount 合計金額
     * @param paymentMethod 支払方法
     * @return 実行結果
     */
    public boolean sendContactMail(String name, String mail, String category, String content) {
    	// 送信元メールアドレス
        final String from = "hasegawa.uniserve@gmail.com";
        // Gmailアプリパスワード
        final String password = "cfcm kcxw iakr aaap";
        
        // SMTP設定
        Properties props = new Properties();
        // SMTP認証有効
        props.put("mail.smtp.auth", "true");
        // TLS有効(通信を暗号化する)
        props.put("mail.smtp.starttls.enable", "true");
        // SMTPサーバーの設定
        props.put("mail.smtp.host", "smtp.gmail.com");
        // ポート番号指定
        props.put("mail.smtp.port", "587");
        
        // メール送信用セッションを作る
        Session session = Session.getInstance(props,
            new Authenticator() {
        		// メソッドオーバーライド
                protected PasswordAuthentication getPasswordAuthentication() {
                	// 認証情報返却
                    return new PasswordAuthentication(from, password);
                }
            });
        
        try {
        	// メールオブジェクト作成
            Message message = new MimeMessage(session);
            // 差出人を設定
            try {
                message.setFrom(new InternetAddress(from, "Osake Market"));
            } catch (java.io.UnsupportedEncodingException e) {
                message.setFrom(new InternetAddress(from));
            }

            // 宛先設定
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(from) // ← 管理者宛て
            );
            
            // 件名設定
            message.setSubject("お問い合わせがありました");
            
            // メール本文設定
            StringBuilder body = new StringBuilder();

            body.append("■お問い合わせ内容\n\n");
            body.append("名前：").append(name).append("\n");
            body.append("メールアドレス：").append(mail).append("\n");
            body.append("カテゴリ：").append(category).append("\n\n");
            body.append("内容：\n").append(content).append("\n");
            
            // 本文設定
            message.setText(body.toString());
            
            // メール送信
            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
//    /**
//     * 共通送信処理（GAS）
//     */
//    private boolean send(String mail, String subject, String body) {
//
//        try {
//            String params =
//                "email=" + URLEncoder.encode(mail, StandardCharsets.UTF_8) +
//                "&subject=" + URLEncoder.encode(subject, StandardCharsets.UTF_8) +
//                "&body=" + URLEncoder.encode(body, StandardCharsets.UTF_8);
//
//            URL url = new URL(GAS_URL);
//            HttpURLConnection conn =
//                (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//
//            conn.setRequestProperty(
//                "Content-Type",
//                "application/x-www-form-urlencoded; charset=UTF-8"
//            );
//
//            try (OutputStream os = conn.getOutputStream()) {
//                os.write(params.getBytes(StandardCharsets.UTF_8));
//            }
//
//            return conn.getResponseCode() == 200;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}