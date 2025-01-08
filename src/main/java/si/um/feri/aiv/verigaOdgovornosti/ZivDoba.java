package si.um.feri.aiv.verigaOdgovornosti;

import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.jsf.mail.Observable;
import si.um.feri.aiv.vao.MSE;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ZivDoba implements verigaOdgovornosti{
    private verigaOdgovornosti veriga;
    public ZivDoba(){};

    public ZivDoba(verigaOdgovornosti veriga){
        this.veriga = veriga;
    };

    @Override
    public void preveriDatum(MSE mse) {
        Instant datumZivljenskeDobe = mse.getDatumVgradnje().toInstant();
        Instant datumZivDobe = datumZivljenskeDobe.plus(7304, ChronoUnit.DAYS);

        if(datumZivDobe.minus(60, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Pošiljam mail zarad ziv dobe");
            MailSender emailSender = new MailSender();
            emailSender.update(mse.getLastnik());
        }
        else if(datumZivDobe.minus(180, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Zivljenska doba še teče");
        }

        if (veriga!=null) veriga.preveriDatum(mse);
    }
}
