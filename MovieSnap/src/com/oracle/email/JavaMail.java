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
	//����������� smtp��������ַ
	public static String myEmailSMTPHost="smtp.qq.com";
//	public static String receiveMailAccount="";
	
	public void sendemail(String  receiveMailAccount,String content) throws Exception {
		//������������
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
	  //�������ô����Ự���� ���ں��ʼ�����������
	  Session session=Session.getDefaultInstance(props);
	  session.setDebug(true);
	  //����һ���ʼ�
	  // 3. ����һ���ʼ�
      MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,content);

      // 4. ���� Session ��ȡ�ʼ��������
      Transport transport = session.getTransport();

      // 5. ʹ�� �����˺� �� ���� �����ʼ�������
      //    ������֤����������� message �еķ���������һ�£����򱨴�
      transport.connect("354635873@qq.com", "cvrucqxblktnbibi");

      // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
      transport.sendMessage(message, message.getAllRecipients());

      // 7. �ر�����
      transport.close();
	  
	  
	  
	  
		
	}
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String content ) throws Exception {
        // 1. ����һ���ʼ�
        MimeMessage message = new MimeMessage(session);

        // 2. From: ������
        message.setFrom(new InternetAddress(sendMail, "hzx", "UTF-8"));

        // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX�û�", "UTF-8"));

        // 4. Subject: �ʼ�����
        message.setSubject("��ϲ��,�����ɹ�!", "UTF-8");

        // 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. ���÷���ʱ��
        message.setSentDate(new Date());

        // 7. ��������
        message.saveChanges();

        return message;
    }

	

}
