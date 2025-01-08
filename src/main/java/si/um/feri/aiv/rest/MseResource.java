package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.dao.MSEDao;
import si.um.feri.aiv.vao.MSE;

import java.util.ArrayList;
import java.util.List;

@Path("/mse")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MseResource {
    @EJB
    MSEDao dao;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/")
    public List<MSE> allMse(){
        List<MSE> seznamMse = new ArrayList<>();
        for (si.um.feri.aiv.vao.MSE mse : dao.getAll())
            seznamMse.add(new MSE(mse));
        return seznamMse;
    }

    @POST
    @Path("/")
    public void addMse(MSE mse) throws Exception{
        dao.saveMSE(mse,1);
    }
}
