package com.oracle.email;

//import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class JavaMail {
	
	public static String myEmailAccount="354635873@qq.com";
	public static String myEmailPassword="cvrucqxblktnbibi";
	//发件人邮箱的 smtp服务器地址
	public static String myEmailSMTPHost="smtp.qq.com";
//	public static String receiveMailAccount="";
	
	public void sendemail(String  receiveMailAccount,String content) throws Exception {
		//创建参数配置
	  Properties props=new Properties();
	  MailSSLSocketFactory sf = new MailSSLSocketFactory();
	  sf.setTrustAllHosts(true);
	  props.put("mail.smtp.ssl.enable", "true");
	  props.put("mail.smtp.ssl.socketFactory", sf);
	  props.put("mail.smtp.socketFactory.port", 465);
	  props.put("mail.smtp.starttls.enable","true");
	  props.put("mail.smtp.port", 465);
	  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  props.setProperty("mail.transport.protocol", "smtp");
	  props.setProperty("mail.smtp.host", myEmailSMTPHost);
	  props.setProperty("mail.smtp.auth", "true");
	  //根据配置创建会话对象 用于和邮件服务器交互
	  Session session=Session.getDefaultInstance(props);
	  session.setDebug(true);
	  //创建一封邮件
	  // 3. 创建一封邮件
      MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,content);

      // 4. 根据 Session 获取邮件传输对象
      Transport transport = session.getTransport();

      // 5. 使用 邮箱账号 和 密码 连接邮件服务器
      //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
      transport.connect("354635873@qq.com", "cvrucqxblktnbibi");

      // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
      transport.sendMessage(message, message.getAllRecipients());

      // 7. 关闭连接
      transport.close();
	  
	  
	  
	  
		
	}
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String content ) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "hzx", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("恭喜您,抢购成功!", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

	

}
