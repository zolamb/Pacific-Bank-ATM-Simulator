import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailOptions {
    private final String from = "zachlamb0126@gmail.com";
    private final String password = "Aa83888888";
    private final String host = "smtp.gmail.com";
    private final Properties properties = System.getProperties();

    public MailOptions(){
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
    }

    public void sendRegistrationCode(String subject, String message, String to){
        Session session = Session.getDefaultInstance(properties, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        try{
            mimeMessage.setFrom(new InternetAddress("Pacific Bank<PacificBank@cyberservices.com>"));//This is the Alias
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            //mimeMessage.setText(message);
            String background = "http://bankingsp.weebly.com/uploads/9/9/4/5/99455446/atm-bank-simulator-background-image1_orig.jpg";
            mimeMessage.setContent("<html><table background=\""+background+"\" width=\"500\" height=\"400\"><font size=\"36\" face=\"Courier New\" ><caption><b>"+message+"</b></caption></font></table></html>", "text/html; charset=utf-8");
            //<html><body>" +message+ "</body></html>
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }catch (MessagingException me){
            me.printStackTrace();
            //System.out.println("Not a valid email address");
        }
    }
}