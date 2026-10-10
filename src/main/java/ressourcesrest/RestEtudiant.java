package ressourcesrest;

import entities.Etudiant;
import entities.EtudiantList;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class RestEtudiant {

    private static EtudiantBusiness etuB = new EtudiantBusiness();
    private static OptionBusiness optB = new OptionBusiness();

    // ============ B1 : POST /etudiants ============
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant e) {
        if (e == null || e.getOption() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Option o = optB.getOptionByCode(e.getOption().getCodeOption());
        if (o == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (!etuB.addEtudiant(e)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(e).build();
    }

    // ============ B2 : GET /etudiants ============
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {
        List<Etudiant> liste = etuB.getAllEtudiants();
        if (liste == null || liste.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    // ============ B3 : GET /etudiants/{identifiant} ============
    @GET
    @Path("{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("identifiant") String identifiant) {
        Etudiant e = etuB.getEtudiantByIdentifiant(identifiant);
        if (e == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(e).build();
    }

    // ============ B4 : DELETE /etudiants/{identifiant} ============
    @DELETE
    @Path("{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant) {
        if (etuB.deleteEtudiant(identifiant)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // ============ B5 : PUT /etudiants/{identifiant} ============
    @PUT
    @Path("{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(@PathParam("identifiant") String identifiant, Etudiant e) {
        if (etuB.updateEtudiant(identifiant, e)) {
            return Response.status(Response.Status.OK).entity(e).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // ============ B6 : GET /etudiants/option?codeOption=1 (XML) ============
    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(@QueryParam("codeOption") int codeOption) {
        Option o = optB.getOptionByCode(codeOption);
        if (o == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Etudiant> liste = etuB.getEtudiantsByOption(codeOption);
        EtudiantList etudiantList = new EtudiantList(liste);
        return Response.status(Response.Status.OK).entity(etudiantList).build();
    }
}