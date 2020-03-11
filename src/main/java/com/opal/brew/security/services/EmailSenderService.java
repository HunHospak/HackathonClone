package com.opal.brew.security.services;

import com.opal.brew.users.MyUser;
import com.opal.brew.security.models.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailSenderService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Async
  public void sendEmail(SimpleMailMessage email) {
    javaMailSender.send(email);
  }

  public SimpleMailMessage createEmail(ConfirmationToken confirmationToken, MyUser newUser) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(newUser.getEmail());
    mailMessage.setSubject("Complete Registration!");
    mailMessage.setFrom(System.getenv("MAIL_ADDRESS"));
    mailMessage.setText("Dear College!\n" +
            "Your user details are the following:\n" +
            "Username: " + newUser.getUsername() + "\n" +
            "Password: " + newUser.getPassword() + "\n" +
            "Role: " + newUser.getRole() + "\n" +
            "To confirm your account, please click here : "
            + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
    return mailMessage;
  }
}
