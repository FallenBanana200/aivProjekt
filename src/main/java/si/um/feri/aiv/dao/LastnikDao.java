package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;

import java.util.List;

@Local
public interface LastnikDao {
	List<Lastnik> getAll();
	void save(Lastnik l);
	Lastnik find(int id);

	List<Lastnik> findMultiple(List<Integer> ids);
	void delete(int id);
	List<MSE> getAllmojeMSE(int id);
	void dodajMse(MSE mse, Lastnik lastnik);
}
