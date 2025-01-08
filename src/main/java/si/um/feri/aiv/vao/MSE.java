package si.um.feri.aiv.vao;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.state.PolnaMocMSE;
import si.um.feri.aiv.state.PolovicnaMSE;
import si.um.feri.aiv.state.StanjeMSE;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity
public class MSE {

    @JsonbTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String naziv;
    private int zmogljivostMSE;
    private double longitude;
    private double latitude;
    private Date datumVgradnje;
    private Date zadnjiPregled;
    private Date zadnjiServis;

    @ManyToOne(fetch = FetchType.EAGER)
    private Lastnik lastnik;

    @Transient
    private StanjeMSE stanje;

    public MSE(MSE mse) {
    }
    public MSE() {
        this("", 0,0,0 ,new Lastnik(), new Date(0), new Date(0), new Date(0));
    }
    public MSE(String naziv, int zmogljivostMSE,double longitude, double latitude, Lastnik l, Date datumVgradnje, Date zadnjiPregled, Date zadnjiServis){
        this.naziv = naziv;
        this.zmogljivostMSE = zmogljivostMSE;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastnik= l;
        this.stanje = new PolovicnaMSE();
        this.datumVgradnje= datumVgradnje;
        this.zadnjiPregled = zadnjiPregled;
        this.zadnjiServis = zadnjiServis;
    }
}
