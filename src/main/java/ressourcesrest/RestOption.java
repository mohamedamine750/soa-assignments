package ressourcesrest;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/options")
public class RestOption {

   public static OptionBusiness opB = new OptionBusiness();

   // ============ A2 : GET /options (ou ?domaine=...) ============
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOptions(@QueryParam("domaine") String d) {
      List<Option> l;
      if (d == null) {
         l = opB.getListeOptions();
      } else {
         l = opB.getOptionsByDomaine(d);
      }

      if (l == null || l.isEmpty()) {
         return Response.status(Response.Status.NO_CONTENT).build();
      }
      return Response.status(200).entity(l).build();
   }

   // ============ A6 : GET /options/{code} ============
   @GET
   @Path("{code}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getOptionById(@PathParam("code") int code) {
      Option o = opB.getOptionByCode(code);
      if (o == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.status(Response.Status.OK).entity(o).build();
   }

   // ============ A1 : POST /options ============
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addNewOption(Option op) {
      if (!opB.addOption(op)) {
         return Response.status(Response.Status.BAD_REQUEST).build();
      }
      return Response.status(200).build();
   }

   // ============ A5 : PUT /options/{code} ============
   @PUT
   @Path("{code}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateOption(@PathParam("code") int code, Option op) {
      if (!opB.updateOption(code, op)) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.status(200).build();
   }

   // ============ A4 : DELETE /options/{code} ============
   @DELETE
   @Path("{code}")
   public Response deleteOption(@PathParam("code") int id) {
      if (opB.deleteOption(id)) {
         return Response.status(Response.Status.NO_CONTENT).build();
      }
      return Response.status(Response.Status.NOT_FOUND).build();
   }
}