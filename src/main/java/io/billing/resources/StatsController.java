package io.billing.resources;

import io.billing.models.ProductSold;
import io.billing.services.Stats;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/api/v1/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatsController {
    @GET
    @Path("/products")
    public Response getProducts() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/sales")
    public Response getSales() {
        Collection<ProductSold> sold = Stats.INSTANCE.getStatsService().getProductsAndSales();

        if (sold == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Collection<ProductSold> result = sold.stream()
                .sorted((a, b) -> (int) (b.getTotal() - a.getTotal()))
                .collect(Collectors.toList());

        return Response.ok(result).build();
    }

    @GET
    @Path("/clients")
    public Response getClients() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
