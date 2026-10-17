package webservices;
import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestApi {
   static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUE(UniteEnseignement ue) {
        if (helper.addUniteEnseignement(ue)) {
            return Response.status(Response.Status.CREATED).entity("created").build();   // 201
        }
        return Response.status(Response.Status.CONFLICT).entity("Code deja existant").build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUE(@QueryParam("semestre") Integer semestre,
                          @QueryParam("code") Integer code) {

        if (code != null) {                              // GET /UE?code=123
            UniteEnseignement ue = helper.getUEByCode(code);
            if (ue == null) {
                return Response.status(Response.Status.NOT_FOUND).build();   // 404
            }
            return Response.ok(ue).build();                                  // 200
        }
        if (semestre != null) {                          // GET /UE?semestre=2
            return Response.ok(helper.getUEBySemestre(semestre)).build();
        }
        return Response.ok(helper.getListeUE()).build(); // GET /UE
    }
    @DELETE
    @Path("/{code}")
    public Response deleteUE(@PathParam("code") int code) {
        if (helper.deleteUniteEnseignement(code)) {
            return Response.ok().build();                             // 200
        }
        return Response.status(Response.Status.NOT_FOUND).build();    // 404
    }
    @PUT
    @Path("/{code}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue) {
        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.ok().build();                             // 200
        }
        return Response.status(Response.Status.NOT_FOUND).build();    // 404
    }
}