package Myfinal;

/*import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class JavaEmailSender {
 
    private String emailAddressTo = new String();
    private String msgSubject = new String();
    private String msgText = new String();

    final String USER_NAME = "10ameer5@gmail.com";   //User name of the Goole(gmail) account
    final String PASSSWORD = "ameer105";  //Password of the Goole(gmail) account
    final String FROM_ADDRESS = "10ameer5@gmail.com";  //From addresss
 
    public JavaEmailSender() {
    }

    public static void main(String[] args) {
      JavaEmailSender email = new JavaEmailSender();
     //Sending test email
      email.createAndSendEmail("zahraabbas394@gmail.com", "Test email subject",
      "Congratulations !!! \nThis is test email sent by java class.");
    }

    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage();
    }
 
    private void sendEmailMessage() {
     
     //Create email sending properties
     Properties props = new Properties();
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.port", "587");
  
    Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(USER_NAME, PASSSWORD);
   }
    });

  try {

     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("xxxxxxxx@gmail.com")); //Set from address of the email
     message.setContent(msgText,"text/html"); //set content type of the email
     
    //message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("xxxxxxxx@gmail.com")); //Set email recipient
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddressTo));

    
    message.setSubject("Hello World"); //Set email message subject
    Transport.send(message); //Send email message

   System.out.println("sent email successfully!");

  } catch (MessagingException e) {
       throw new RuntimeException(e);
  }
    }

    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
 
}
*/

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class JavaEmailSender {

    private String emailAddressTo;
    private String msgSubject;
    private String msgText;

    final String USER_NAME = "zahraabbas394@gmail.com";   // Your Gmail address
    final String PASSWORD = "apnxqjxmgcxflwup";           // Your Gmail password or app password
    final String FROM_ADDRESS = "zahraabbas394@gmail.com"; // Same Gmail address

    public JavaEmailSender() {
    }

    public static void main(String[] args) {
        JavaEmailSender email = new JavaEmailSender();
        // Sending test email
        email.createAndSendEmail(
            "zahraabbas394@gmail.com",    // <-- Email where you want to send
            "Test email subject",
            "Congratulations !!! <br>This is a test email sent by Java class."
        );
    }

    public void createAndSendEmail(String emailAddressTo, String msgSubject, String msgText) {
        this.emailAddressTo = emailAddressTo;
        this.msgSubject = msgSubject;
        this.msgText = msgText;
        sendEmailMessage();
    }

    private void sendEmailMessage() {

        // Create email sending properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER_NAME, PASSWORD);
                }
            }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_ADDRESS));           // Set from address
            message.setRecipients(Message.RecipientType.TO, 
                InternetAddress.parse(emailAddressTo));                   // Set recipient address
            message.setSubject(msgSubject);                                // Set email subject
            message.setContent(msgText, "text/html");                      // Set content type to HTML
            Transport.send(message);                                       // Send email

            System.out.println("Sent email successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Optional setters (if you want to use them later)
    public void setEmailAddressTo(String emailAddressTo) {
        this.emailAddressTo = emailAddressTo;
    }

    public void setSubject(String subject) {
        this.msgSubject = subject;
    }

    public void setMessageText(String msgText) {
        this.msgText = msgText;
    }
}


