package si.um.feri.aiv.ejb;

import jakarta.ejb.Remote;

@Remote
public interface ICalc {
    int izracunajMoc(int id);
}
