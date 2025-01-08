package si.um.feri.aiv.vao;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.jsf.mail.Observable;
import si.um.feri.aiv.jsf.mail.Observer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Skupnost implements Observable {
    public Skupnost(String naziv, Lastnik skrbnik, List<Lastnik> clani){
        this.naziv = naziv;
        this.skrbnik=skrbnik;
        this.clani=clani;
    }

    @JsonbTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String naziv;

    @ManyToOne
    private Lastnik skrbnik;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Lastnik> clani;

    @Transient
    private List<MailSender> observers = new ArrayList<>();

    public Skupnost(Skupnost selectedSkupnost) {}
    public Skupnost() {
        observers.add(new MailSender());
    }

    @Override
    public void notifyObservers() {
        for(Observer o : this.observers){
            o.update(this);
        }
    }
}
