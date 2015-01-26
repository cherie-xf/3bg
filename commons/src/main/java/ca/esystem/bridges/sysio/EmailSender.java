package ca.esystem.bridges.sysio;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSender {
  public void sendRegisterMail(String mailto,String address,String subject, String content);
  public void sendRegisterMail(String[] mailto,String address,String subject, String content);
  public void sendResetPassLetter(String mailto,String address, String subject, String content);
  public void sendResetPassLetter(String[] mailto,String address, String subject, String content);  
  
}
