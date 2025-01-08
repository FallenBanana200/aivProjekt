package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.MSE;

import java.util.List;

@Local
public interface MSEDao {
    List<MSE> getAll();
    void saveMSE(MSE m, int lastnikId);
    MSE find(int id);
    void delete(int id);
}
