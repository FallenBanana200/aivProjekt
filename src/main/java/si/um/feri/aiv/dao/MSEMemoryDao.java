package si.um.feri.aiv.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class MSEMemoryDao implements MSEDao{
    //Deklaracije
    Logger log=Logger.getLogger(LastnikMemoryDao.class.toString());

    private static List<MSE> vseMse= Collections.synchronizedList(new ArrayList<MSE>());

    //private Map<Integer, List<MSE>> lastnikiMseMap = new HashMap<>();

    public MSEMemoryDao(){}

    @PersistenceContext
    EntityManager em;

    @Override
    public List<MSE> getAll() {
        log.info("DAO: get all");
        return vseMse;
    }

    @Override
    public void saveMSE(MSE m, int lastnikId) {
        log.info("DAO: saving " + m + " for lastnik with ID: " + lastnikId);
        if (find(m.getId()) != null){
            em.merge(m);
        }else{
        log.info("Dao: update-am MSE" + m);
        Lastnik lastnik = em.find(Lastnik.class, lastnikId);
        m.setLastnik(lastnik);
        em.persist(m);}
    }

    @Override
    public MSE find(int id) {
        log.info("DAO: finding "+id);

        return em.find(MSE.class, id);
    }

    @Override
    public void delete(int id) {
        log.info("DAO: deleting "+id);
        em.remove(find(id));
    }
}
