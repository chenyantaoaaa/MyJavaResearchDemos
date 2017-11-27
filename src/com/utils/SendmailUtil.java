package com.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class SendmailUtil {
    // 设置服务器
    private static String KEY_SMTP = "mail.smtp.host";
    private static String VALUE_SMTP = "smtp.mxhichina.com";
//    private static String VALUE_SMTP = "smtp.taoshaome.com";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static boolean VALUE_PROPS = true;
    // 发件人用户名、密码
    private static String SEND_USER = "postmaster@taoshaome.cn";
    private static String SEND_UNAME = "postmaster@taoshaome.cn";
    private static String SEND_PWD = "Aa86358201";
    // 建立会话
    private MimeMessage message;
    private Session s;

    public static String sslSend(String headName, String sendText,String emailAd)
            throws AddressException, MessagingException, IOException {
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", emailAccount.getPlace());
        props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port.enable", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        final String username = SEND_USER;
        final String password = SEND_PWD;
        Session session = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }});
        Message msg = new MimeMessage(session);

        // 设置发件人和收件人
        msg.setFrom(new InternetAddress(SEND_USER));
//        yyyvoyage@hotmail.com
        InternetAddress to = new InternetAddress(emailAd);
        // 多个收件人地址
        msg.setRecipient(Message.RecipientType.TO, to);
        msg.setSubject(headName); // 标题
        msg.setText(sendText);// 内容
        msg.setSentDate(new Date());
        Transport.send(msg);
        System.out.println("EmailUtil ssl协议邮件发送打印" +msg.toString());
        return "succ";
    }



    public static void main(String[] args) {
        SendmailUtil se = new SendmailUtil();
//        se.doSendTextEmail("邮件头文件名", "邮件内容");
        try {
            se.sslSend("邮件头文件名", "邮件内容","");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}