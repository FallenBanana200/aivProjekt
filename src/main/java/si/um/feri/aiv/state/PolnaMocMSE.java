package si.um.feri.aiv.state;


import si.um.feri.aiv.vao.MSE;

public class PolnaMocMSE implements StanjeMSE{
    @Override
    public int mocMSE(MSE mse) {
        return mse.getZmogljivostMSE();
    }
}
