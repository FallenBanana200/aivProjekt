package si.um.feri.aiv.ejb;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.aiv.dao.SkupnostDao;
import si.um.feri.aiv.vao.Skupnost;

import java.util.concurrent.atomic.AtomicInteger;

@Stateless
public class CalcBean implements ICalc{
    @EJB
    SkupnostDao skupnostDao;

    public CalcBean(){}

    @Override
    public int izracunajMoc(int id) {
//        Skupnost najdenaSkupnost = skupnostDao.findSkupnost(id);
//        AtomicInteger zmogljivost = new AtomicInteger(0);
//        najdenaSkupnost.getClani().stream()
//                .flatMap(clan -> clan.getMojeMSE().stream())
//                .forEach(mse -> zmogljivost.addAndGet(mse.getZmogljivostMSE()));
        return 1;
    }
}
