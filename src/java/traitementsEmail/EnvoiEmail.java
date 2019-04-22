
package traitementsEmail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

public class EnvoiEmail implements Serializable {

    private String username;
    private String password;
    private String sender;
    private String receiver;
    private String subject;
    private String messageText;
    private List<String> attachedDocumentsPaths = new ArrayList();
    
    private String pieceJointe1;
    
    
    ///// Constructors
    public EnvoiEmail() {
    }
    
    public EnvoiEmail(String username, String password, String sender, String receiver, String subject, String messageText, List<String> attachedDocumentsPaths) {
    this.username = username;
    this.password = password;
    this.sender = sender;
    this.receiver = receiver;
    this.subject = subject;
    this.messageText = messageText;
    this.attachedDocumentsPaths = attachedDocumentsPaths;
    }

    ///// Getters and Setters
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

    public String getPieceJointe1() {
        return pieceJointe1;
    }

    public void setPieceJointe1(String pieceJointe1) {
        this.pieceJointe1 = pieceJointe1;
    }
   
    ///////////////
    public void envoyerEmail() throws MessagingException {
        
        Email email = new Email();
        
        
        if(this.pieceJointe1 != null) {
            this.attachedDocumentsPaths.add(this.pieceJointe1);
        }
        
        email.setAttachedDocumentsPaths(attachedDocumentsPaths);
        
            email.envoyerEmail();
        }  
}
