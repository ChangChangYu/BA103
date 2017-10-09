package com.admin.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminMailService {
	
	// �]�w�ǰe�l��:�ܦ��H�H��Email�H�c,Email�D��,Email���e
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // �]�w�ϥ�SSL�s�u�� Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ���]�w gmail ���b�� & �K�X (�N�ǥѧA��Gmail�ӶǰeEmail)
       // �����NmyGmail���i�w���ʸ��C�����ε{���s���v�j���}
	     final String myGmail = "chumeetservice@gmail.com";
	     final String myGmail_password = "ChuMeet123";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //�]�w�H�����D��  
		   message.setSubject(subject);
		   //�]�w�H�������e 
		   message.setContent(messageText, "text/html; charset=utf-8");

		   Transport.send(message);
		   System.out.println("�ǰe���\!");
     }catch (MessagingException e){
	     System.out.println("�ǰe����!");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      String to = "chumeetservice@gmail.com";
      
      String subject = "���}ChuMeet E-mail ���U�T�{�H";
      
      String ch_name = "ChuMeetAdmin003";
      String passRandom = "ChuMeetWeb0083";
      String messageText = "<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>�˷R�� "+ "���H��" +" �z�n:</h2><br><p>�w��z���U�������}ChuMeet�|���C</p><p>���U������AChuMeet�N�|���ѱz��h���A�ȸ�T�P���e�C </p><br><h5>ChuMeet�w��z���[�J! ChuMeet�A�ȹζ�</h5><br><h4>�p��������D�w��ӫHChuMeet�ȪA�H�c: service@chumeet.com</h4>"; 
      
      
      AdminMailService mailService = new AdminMailService();
      mailService.sendMail(to, subject, messageText);
      

   }


}