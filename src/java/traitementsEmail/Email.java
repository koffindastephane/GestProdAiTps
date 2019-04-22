
package traitementsEmail;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Email {
    private String username;
    private String password;
    private String sender;
    private String receiver;
    private String subject;
    private String messageText;
    private List<String> attachedDocumentsPaths;

    public Email() {
    }

    public Email(String username, String password, String sender, String receiver, String subject, String messageText, List<String> attachedDocumentsPaths) {
        this.username = username;
        this.password = password;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.messageText = messageText;
        this.attachedDocumentsPaths = attachedDocumentsPaths;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public List<String> getAttachedDocumentsPaths() {
        return attachedDocumentsPaths;
    }

    public void setAttachedDocumentsPaths(List<String> attachedDocumentsPaths) {
        this.attachedDocumentsPaths = attachedDocumentsPaths;
    }
    
    //-------------------------------------------------------------------
    
    public void envoyerEmail() throws MessagingException {
      Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.sender));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(this.receiver));
			message.setSubject(this.subject);
     
         if(!attachedDocumentsPaths.isEmpty()) {
			// Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText(this.messageText);

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         for(int i=0; i< this.attachedDocumentsPaths.size(); i++) {
         messageBodyPart = new MimeBodyPart();
         String filename = this.attachedDocumentsPaths.get(i);
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
         }
         
         // Send the complete message parts
         message.setContent(multipart);
         }
         else{
             message.setText(this.messageText);
         }
         
         // Send message
         Transport.send(message);

         System.out.println("Message envoyé avec succès (avec " + this.attachedDocumentsPaths.size() + " pièce(s) jointe(s)" );
  
 
		
	}
}