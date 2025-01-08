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
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.verigaOdgovornosti.RedniPregled;
import si.um.feri.aiv.verigaOdgovornosti.ServisnoVzdrževanje;
import si.um.feri.aiv.verigaOdgovornosti.ZivDoba;
import si.um.feri.aiv.verigaOdgovornosti.verigaOdgovornosti;

@Named("demo2")
@SessionScoped
//@Stateless
public class MSEJSFBean implements Serializable{

    //Deklaracije
    private static final long serialVersionUID = -8979220536758073133L;

    Logger log=Logger.getLogger(si.um.feri.aiv.jsf.MSEJSFBean.class.toString());

    @EJB
    private MSEDao dao;
    @EJB
    private LastnikDao lastnikDao;

    @Getter @Setter
    private MSE selectedMSE=new MSE();

    @Getter
    private int lastnikId;

    @Getter @Setter
    private MSE viewMSE;

    //Implementacije

    public List<MSE> getAllMSE() throws Exception {
        return dao.getAll();
    }

    public String saveMSE(MSE m, Lastnik lastnik) throws Exception {
        log.info("Dao: saving MSE" + m + "for Lastnik: " + lastnik);
        dao.saveMSE(selectedMSE, lastnik.getId());
        return "details";
    }

    public void deleteMSE(MSE m) throws Exception {
        log.info("Dao: deleting MSE "+ m);
        dao.delete(m.getId());
    }

    public void setLastnikId(int id) throws Exception {
        lastnikId = id;
        selectedMSE = dao.find(lastnikId);
        if(selectedMSE == null){
            selectedMSE= new MSE();
        }
    }

    public void preveriServise(MSE mse) {
        verigaOdgovornosti verige = new ZivDoba(new ServisnoVzdrževanje(new RedniPregled()));
        verige.preveriDatum(mse);
    }
}
