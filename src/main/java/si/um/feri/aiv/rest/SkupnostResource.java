package si.um.feri.aiv.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.aiv.dao.SkupnostDao;
import si.um.feri.aiv.vao.Skupnost;

import java.util.ArrayList;
import java.util.List;

@Path("/skupnosti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SkupnostResource {

    @EJB SkupnostDao dao;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    @Path("/")
    public List<Skupnost> allSkupnosti(){
        List<Skupnost> seznamSk = new ArrayList<>();
        for (si.um.feri.aiv.vao.Skupnost sk : dao.getAllSkupnosti())
            seznamSk.add(new Skupnost(sk));
        return seznamSk;
    }

    @POST
    @Path("/")
    public void addSkupnost(Skupnost skupnost) throws Exception{
        dao.saveSkupnost(skupnost);
    }
}
