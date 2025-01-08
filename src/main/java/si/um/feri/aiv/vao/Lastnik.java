package si.um.feri.aiv.vao;


import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.jsf.mail.Observable;
import si.um.feri.aiv.jsf.mail.Observer;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Lastnik implements Observable {
public Lastnik(String ime, String priimek, String tip, String naslov, String email)
	{
		this.ime = ime;
		this.priimek = priimek;
		this.naslov = naslov;
		this.tip = tip;
		this.email = email;

	}

	@JsonbTransient
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String ime;
	private String priimek;
	private String tip;
	private String naslov;
	private String email;

	public Lastnik(Lastnik selectedLastnik) {
	}
	public Lastnik() {}

	@Override
	public void notifyObservers() {
		Observer obj = (Observer) this;
		obj.update(this);
	}
}
