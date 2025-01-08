package si.um.feri.aiv.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
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
import si.um.feri.aiv.dao.SkupnostDao;
import si.um.feri.aiv.dao.SkupnostMemoryDao;
import si.um.feri.aiv.jsf.mail.MailSender;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

@Named("skupnost")
@SessionScoped
//@Stateless
public class SkupnostJSFBean implements Serializable{

    //Deklaracije
    private static final long serialVersionUID = -8979220536758073133L;

    Logger log=Logger.getLogger(si.um.feri.aiv.jsf.SkupnostJSFBean.class.toString());

    @EJB
    private SkupnostDao dao;

    @EJB
    private LastnikDao lastnikDao;

    @Getter @Setter
    private List<Integer> izbraniSkrbnik;

    @Getter @Setter
    private List<Integer> izbraniClani;

    @Getter @Setter
    private int lastnikId;

    @Getter
    private Skupnost selectedSkupnost=new Skupnost();

    @Getter
    private int skupnostId;

    private String selectedNaziv;

    //Implementacije

    @PostConstruct
    public void init() {
        this.selectedSkupnost = new Skupnost();
        this.selectedSkupnost.setSkrbnik(new Lastnik());
    }

    public List<Skupnost> getAllSkupnosti() throws Exception {
        return dao.getAllSkupnosti();
    }

    public String saveSkupnost() throws Exception {
        log.info("Dao: saving skupnost");
        selectedSkupnost.setSkrbnik(lastnikDao.find(izbraniSkrbnik.get(0)));
        selectedSkupnost.setClani(lastnikDao.findMultiple(izbraniClani));
        dao.saveSkupnost(selectedSkupnost);
        selectedSkupnost = new Skupnost();
        return "prikazSkupnosti";
    }

    public void deleteSkupnost(Skupnost s) throws Exception {
        log.info("Deleting skupnost: "+s);
        dao.delete(s.getId());
    }

    public List<Lastnik> getAllClani(int id) throws Exception{
        log.info("Dao: getting all clani for skupnost: "+id);
        System.out.println(id);
        return dao.getAllClani(id);
    }

    public void setSelectedSkupnost(Skupnost selectedSkupnost) {
        this.selectedSkupnost = selectedSkupnost;
        if(this.selectedSkupnost == null){
            init();
        }
    }

    public void setSkupnostId(int id) throws Exception {
        skupnostId = id;
        selectedSkupnost = dao.findSkupnost(id);
        if(selectedSkupnost == null) selectedSkupnost = new Skupnost();
        System.out.println(selectedSkupnost);
    }

    public int skupnaMoc() {
        Skupnost selectedSkupnost = getSelectedSkupnost();
        return dao.vrniSkupnoMoc(selectedSkupnost);
    }
}
