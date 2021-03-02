package org.acme.archws.json;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces("application/json")
@Consumes("application/json")
@Path("/visit")
public class VisitService {

    @GET
    public List<Visit> getVisits() {
        return Visit.findAll().list();
    }

    @POST
    @Transactional
    public Response addVisit(Visit visit) {
        try {
            Visit.persist(visit);
            return Response.ok(visit.id).build();
        }
        catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getVisit(@PathParam("id") long id) {
        Visit v = Visit.findById(id);
        Response.ResponseBuilder builder;
        if (v != null) {
            builder = Response.ok(v);
        } else {
            builder = Response.status(404);
        }
        return builder.build();
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public boolean deleteVisit(@PathParam("id") long id) {
        return Visit.deleteById(id);
    }
}
