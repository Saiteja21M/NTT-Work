package com.notifications.handlers;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



public class OTPMailer {
   public static void main(String[] args) {
      final String username = "your_email@example.com";
      final String password = "your_email_password";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("your_email@example.com"));
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("recipient_email@example.com"));
         message.setSubject("OTP for Login");
         int otp = generateOTP(); // your code to generate OTP
         message.setText("Your OTP for login is " + otp);

         Transport.send(message);

         System.out.println("OTP sent successfully");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }

   private static int generateOTP() {
      // your code to generate OTP
      return 123456;
   }
}
