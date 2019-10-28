package classes;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email
{
    public static void sendMail(String recipient) throws Exception
    {
        System.out.println("skal sende email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "cohesionhotell@gmail.com\n";
        String password = "Itgruppa15";

        Session session = Session.getInstance(properties, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("Message er sendt");
    }
        private static Message prepareMessage(Session session, String myAccountEmail, String recipient)
        {
            try
            {
                Message message =  new MimeMessage(session);
                message.setFrom(new InternetAddress(myAccountEmail));
                message.setRecipient(Message.RecipientType.TO , new InternetAddress(recipient));
                message.setSubject("Du har nå registerert en bruker");
                message.setText("Registrert bruker");
                return message;
            }
            catch (Exception ex)
            {

                Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            }
                return null;

        }

}


