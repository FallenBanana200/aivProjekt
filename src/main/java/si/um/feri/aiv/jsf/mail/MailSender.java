package si.um.feri.aiv.jsf.mail;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mail.Address;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.Serializable;

@Named
@SessionScoped
public class MailSender implements Serializable, Observer{
    //Deklaracije
    private static final long serialVersionUID = 1544680932114626710L;
    private String from = "nik.tisler1@student.um.si";

    public void send(String to) {
        try {
            Context ctx = new InitialContext();
            Session mySession = (Session) ctx.lookup("java:jboss/mail/MojMail");

            Message message = new MimeMessage(mySession);
            message.setFrom(new InternetAddress(from));
            Address toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject("Nov član Skupnosti");
            message.setContent("V skupnost je bil dodan nov lastnik", "text/plain");

            Transport.send(message);
        } catch (Exception e) {
        }
        System.out.println("Pošiljam email: " + to);
    }

    //Implementacije

    @Override
    public void update(Observable o) {
        if(o instanceof Skupnost){
            Skupnost skupnost = (Skupnost) o;
            skupnost.getClani().forEach(clan -> {
                send(clan.getEmail());
            } );
        }
        if(o instanceof Lastnik){
            Lastnik lastnik = (Lastnik) o;
            send(lastnik.getEmail());
        }
    }
}