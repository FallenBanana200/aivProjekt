package si.um.feri.aiv.verigaOdgovornosti;

import jakarta.ejb.Local;
import si.um.feri.aiv.vao.MSE;

@Local
public interface verigaOdgovornosti {

    void preveriDatum(MSE mse);

}
