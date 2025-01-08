package si.um.feri.aiv.dao;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.Lastnik;
import si.um.feri.aiv.vao.MSE;
import si.um.feri.aiv.vao.Skupnost;

import java.util.List;

@Local
public interface SkupnostDao {

    List<Skupnost> getAllSkupnosti();
    void saveSkupnost(Skupnost s);
    Skupnost findSkupnost(int id);
    void delete(int id);

    List<Lastnik> getAllClani(int id);

    void dodajClana(Lastnik lastnik, Skupnost skupnost);
    void dodajSkrbnika(Lastnik skrbnik, Skupnost skupnost);
    int vrniSkupnoMoc(Skupnost skupnost);
}
