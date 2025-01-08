package si.um.feri.aiv.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.Setter;
import si.um.feri.aiv.dao.LastnikDao;
import si.um.feri.aiv.dao.LastnikMemoryDao;
import si.um.feri.aiv.dao.MSEDao;
import si.um.feri.aiv.dao.MSEMemoryDao;
import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;

@Named("demo")
@SessionScoped

public class LastnikJSFBean implements Serializable{

	//Deklaracije
	private static final long serialVersionUID = -8979220536758073133L;

	Logger log=Logger.getLogger(si.um.feri.aiv.jsf.LastnikJSFBean.class.toString());

	@EJB
	private LastnikDao dao;

	@Getter
	private Lastnik selectedLastnik=new Lastnik();

	@Getter
	private int lastnikId;

	//Implementacije

	public List<Lastnik> getAllLastniki() throws Exception {
		log.info("Dao: getting all lastniki");
		return dao.getAll();
	}

	public String saveLastnik() throws Exception {
		log.info("Dao: saving lastnik ");
		dao.save(selectedLastnik);
		this.selectedLastnik = new Lastnik();
		return "all";
	}

	public void deleteLastnik(Lastnik l) throws Exception {
		log.info("Deleting lastnik: "+l);
		dao.delete(l.getId());
	}

	public List<MSE> getAllMSE(int id) throws Exception{
		log.info("Dao: getting all MSE for Lastnik "+ id);
		return dao.getAllmojeMSE(id);
	}

	public void setSelectedLastnik(Lastnik selectedLastnik) {
		this.selectedLastnik = selectedLastnik;
	}

	public void setLastnikId(int id) throws Exception {
		lastnikId = id;
		selectedLastnik = dao.find(id);
		if(selectedLastnik == null) selectedLastnik = new Lastnik();
		System.out.println(selectedLastnik);
	}
}