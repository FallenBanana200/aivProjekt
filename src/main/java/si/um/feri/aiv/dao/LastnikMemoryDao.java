package si.um.feri.aiv.dao;

import java.util.*;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;

@Stateless
public class LastnikMemoryDao implements LastnikDao {
	//Deklaracije
	Logger log=Logger.getLogger(LastnikMemoryDao.class.toString());

	@Getter
	private static List<Lastnik> lastniki=Collections.synchronizedList(new ArrayList<Lastnik>());

	public LastnikMemoryDao(){}

	@PersistenceContext
	EntityManager em;

	//Implementacije
	@Override
	public List<Lastnik> getAll() {
		log.info("DAO: get all" + lastniki);
		return em.createQuery("select l from Lastnik l").getResultList();
	}

	@Override
	public void save(Lastnik l)  {
		log.info("DAO: saving "+l);
		if(find(l.getId()) != null){
			em.merge(l);
		}
		else{
		em.persist(l);}
	}
	@Override
	public Lastnik find(int id) {
		log.info("DAO: finding "+id);
		return em.find(Lastnik.class, id);
	}

	@Override
	public List<Lastnik> findMultiple(List<Integer> ids) {
		log.info("Dao: finding multiple");
		List<Lastnik> lastniki = new ArrayList<>();
		for (Integer id : ids){
			lastniki.add(this.find(id));
		} return lastniki;
	}

	@Override
	public void delete(int id) {
		log.info("DAO: deleting "+id);
		em.remove(find(id));
	}

	@Override
	public List<MSE> getAllmojeMSE(int id) {
		log.info("Dao: getting MSE for "+id);
		return em.createQuery("select m from MSE m where m.lastnik.id = :id")
				.setParameter("id", id)
				.getResultList();
	}

	public void dodajMse(MSE mse, Lastnik lastnik) {
		log.info("Dao: dodajam MSE lastniku "+ lastnik.getId());
		mse.setLastnik(lastnik);
		em.persist(mse);
		}
	}
