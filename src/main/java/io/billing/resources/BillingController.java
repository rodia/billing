package io.billing.resources;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/v1/billing")
@Produces(MediaType.APPLICATION_JSON)
public class BillingController {
    @GET
    public Response showBilling() {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response showBill(@PathParam("id") @NotBlank int id) {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/items")
    public Response showBillAndItems(@PathParam("id") @NotBlank int id) {
        return Response.ok().build();
    }

    @GET
    @Path("?nit")
    public Response findByQueryParam(@QueryParam("nit") String nit) {
        return Response.ok(nit).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBill(@PathParam("id") @NotEmpty int id) {
        return Response.ok().build();
    }
}
