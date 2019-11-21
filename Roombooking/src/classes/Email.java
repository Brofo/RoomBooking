package classes;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email
{
    public static void sendMail(String recipient, String subject, String text) throws Exception
    {
        System.out.println("Trying to send mail");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "cohesionhotell@gmail.com";
        String password = "Itgruppa15";

        System.out.println("22");
        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        System.out.println("23");
        Message message = prepareMessage(session, myAccountEmail, recipient, subject, text);

        Transport.send(message);
        System.out.println("Message is sent");
    }
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String subject, String text)
    {
        try
        {
            Message message =  new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO , new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(text);
            return message;
        }
        catch (Exception ex)
        {

            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}



