package si.um.feri.aiv.dao;

import java.util.*;
import java.util.logging.Logger;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

@Stateless
public class SkupnostMemoryDao implements SkupnostDao {
    Logger log=Logger.getLogger(SkupnostMemoryDao.class.toString());
    @PersistenceContext
    EntityManager em;

    @Getter
    private static List<Skupnost> skupnosti= Collections.synchronizedList(new ArrayList<Skupnost>());
    public SkupnostMemoryDao(){}

    //Implementacije
    @Override
    public List<Skupnost> getAllSkupnosti() {
        log.info("DAO: get all" + skupnosti);
        return em.createQuery("select s from Skupnost  s").getResultList();
    }

    @Override
    public void saveSkupnost(Skupnost s) {
        s.notifyObservers();
        if(findSkupnost(s.getId()) != null){
            em.merge(s);
        }else{
        em.persist(s);}
    }

    @Override
    public Skupnost findSkupnost(int id) {
        log.info("DAO: finding "+id);
        return em.find(Skupnost.class, id);
    }

    @Override
    public void delete(int id) {
        log.info("DAO: deleting "+id);
        em.remove(findSkupnost(id));
        }


    @Override
    public List<Lastnik> getAllClani(int id) {
        log.info("Dao: getting all clani for "+id);
        return findSkupnost(id).getClani();
    }

    @Override
    public void dodajClana(Lastnik lastnik, Skupnost skupnost) {
        log.info("Dao: dodajam clana: "+ lastnik + "skupnosti: " + skupnost);
        List<Lastnik> clani = getAllClani(skupnost.getId());
        clani.add(lastnik);
        skupnost.setClani(clani);
        em.persist(skupnost);
    }

    @Override
    public void dodajSkrbnika(Lastnik skrbnik, Skupnost skupnost) {
        log.info("Dao: dodajam skrbnika: "+ skrbnik+ "skupnosti: "+skupnost);
        skupnost.setSkrbnik(skrbnik);
        em.merge(skupnost);
    }

    @Override
    public int vrniSkupnoMoc(Skupnost skupnost) {
        int skupnaMoc=0;
        List<Lastnik> lastniki = skupnost.getClani();

        for (Lastnik lastnik : lastniki) {
            List<MSE> mseList = em.createQuery("select m from MSE m where m.lastnik.id = :id")
                    .setParameter("id", lastnik.getId())
                    .getResultList();

            for (MSE mse : mseList) {
                skupnaMoc += mse.getStanje().mocMSE(mse);
            }
        }
        return skupnaMoc;
    }
}
