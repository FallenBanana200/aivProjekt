package si.um.feri.aiv.verigaOdgovornosti;

import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.jsf.mail.Observable;
import si.um.feri.aiv.vao.MSE;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RedniPregled implements verigaOdgovornosti{
    private verigaOdgovornosti veriga;

    public RedniPregled(){};

    public RedniPregled(verigaOdgovornosti veriga){
        this.veriga = veriga;
    };

    @Override
    public void preveriDatum(MSE mse) {
        Instant datumZadnjegaPregleda = mse.getZadnjiPregled().toInstant();
        Instant datumNaslednjegaPregleda = datumZadnjegaPregleda.plus(356, ChronoUnit.DAYS);

        if(datumNaslednjegaPregleda.minus(60, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Pošiljam mail zarad pregleda");
            MailSender emailSender = new MailSender();
            emailSender.update((Observable) mse.getLastnik());
            return;
        }
        else if(datumNaslednjegaPregleda.minus(180, ChronoUnit.DAYS).isBefore(Instant.now())) {
            System.out.println("Datum pregleda je kmalu");
        }

        if (veriga!=null) veriga.preveriDatum(mse);
    }
}
