package ca.esystem.bridges.sysio.impl;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ca.esystem.bridges.sysio.EmailSender;

@Service("EmailSender")
public class EmailSenderServiceImpl implements EmailSender {
    
	@Resource(name="JavaMailSender")
	private JavaMailSender javaMailSender;
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Resource(name="RegisterMailMessage")
	private SimpleMailMessage registerMailMessage;
	public SimpleMailMessage getRegisterMailMessage() {
		return registerMailMessage;
	}
	public void setRegisterMailMessage(SimpleMailMessage registerMailMessage) {
		this.registerMailMessage = registerMailMessage;
	}
	

	@Resource(name="ResetPassLetter")
	private SimpleMailMessage resetPassLetter;
	
	public SimpleMailMessage getResetPassLetter() {
		return resetPassLetter;
	}
	public void setResetPassLetter(SimpleMailMessage resetPassLetter) {
		this.resetPassLetter = resetPassLetter;
	}
	/**
	 * Send the register activate letter
	 * @param mailto
	 * @param address
	 * @param subject
	 * @param content
	 */
	public void sendRegisterMail(String mailto,String address, String subject, String content){
	    sendmail(registerMailMessage,mailto,address,subject,content);
	}
	
	public void sendRegisterMail(String[] mailto,String address, String subject, String content){
	    sendmail(registerMailMessage,mailto,address,subject,content);
	}
	
	
	public void sendResetPassLetter(String mailto,String address, String subject, String content){
	    sendmail(resetPassLetter,mailto,address,subject,content);
	}
	
	public void sendResetPassLetter(String[] mailto,String address, String subject, String content){
	    sendmail(resetPassLetter,mailto,address,subject,content);
	}		
	
	
   private void sendmail(SimpleMailMessage simpleMailMessage,String mailto,String address, String subject, String content) {
		// TODO Auto-generated method stub
		String fromEmail = simpleMailMessage.getFrom();
	    String emailBody = String.format(simpleMailMessage.getText(), address, content);
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    try {
	      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	      helper.setFrom(fromEmail);
	      helper.setTo(mailto);
	      helper.setSubject(subject);
	      helper.setText(emailBody);
	      /*
	        uncomment the following lines for attachment FileSystemResource
	        file = new FileSystemResource("attachment.jpg");
	        helper.addAttachment(file.getFilename(), file);
	       */
	      javaMailSender.send(mimeMessage);
	      System.out.println("Mail sent successfully.");
	    } catch (MessagingException e) {
	      e.printStackTrace();
	    }		
	}
	
	private void sendmail(SimpleMailMessage simpleMailMessage,String[] mailto,String address, String subject, String content) {
		// TODO Auto-generated method stub
		String fromEmail = simpleMailMessage.getFrom();
	    String emailBody = String.format(simpleMailMessage.getText(), address, content);
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    try {
	      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	      helper.setFrom(fromEmail);
	      helper.setTo(mailto);
	      helper.setSubject(subject);
	      helper.setText(emailBody);
	      /*
	        uncomment the following lines for attachment FileSystemResource
	        file = new FileSystemResource("attachment.jpg");
	        helper.addAttachment(file.getFilename(), file);
	       */
	      javaMailSender.send(mimeMessage);
	      System.out.println("Mail sent successfully.");
	    } catch (MessagingException e) {
	      e.printStackTrace();
	    }		
	}

}
