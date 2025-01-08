package si.um.feri.aiv.verigaOdgovornosti;

import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.jsf.mail.Observable;
import si.um.feri.aiv.vao.MSE;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ServisnoVzdrževanje implements verigaOdgovornosti{

    private verigaOdgovornosti veriga;
    public ServisnoVzdrževanje(){};

    public ServisnoVzdrževanje(verigaOdgovornosti veriga){
        this.veriga = veriga;
    };

    @Override
    public void preveriDatum(MSE mse) {
        Instant datumZadnjegaServisa = mse.getZadnjiServis().toInstant();
        Instant datumNaslednjegaServisa = datumZadnjegaServisa.plus(1500, ChronoUnit.DAYS);

        if(datumNaslednjegaServisa.minus(60, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Pošiljam mail zarad servisa");
            MailSender emailSender = new MailSender();
            emailSender.update((Observable) mse.getLastnik());
            return;
        }
        else if(datumNaslednjegaServisa.minus(180, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Datum servisa je kmalu");
        }

        if (veriga!=null) veriga.preveriDatum(mse);
    }
}
