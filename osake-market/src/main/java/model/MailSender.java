package model;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * メール送信クラス（Jakarta版）
 */
public class MailSender {

    /**
     * メール送信
     *
     * @param mail 送信先メールアドレス
     * @param url  本文に入れるURL
     */
    public boolean send(String mail, String url) {

        final String from = "hasegawa.uniserve@gmail.com";
        final String password = "cfcm kcxw iakr aaap";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            // ★ここ修正
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mail)
            );

            message.setSubject("パスワード再設定");

            message.setText(
                "以下のURLからパスワードを再設定してください:\n\n" + url
            );

            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}