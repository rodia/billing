package io.billing.resources;

import io.billing.models.Bill;
import io.billing.models.Item;
import io.billing.models.Product;
import io.billing.services.Billing;
import io.billing.services.ItemInterface;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Path("/api/v1/billing")
@Produces(MediaType.APPLICATION_JSON)
public class BillingController {
    private static final String OPTIONAL_DEFAULT_VALUE = "none";

    @GET
    public Response showBilling(
            @QueryParam("nit") Optional<String> nitPrevious,
            @QueryParam("date") Optional<String> datePrevious,
            @QueryParam(value = "item")
            final List<String> items
    ) {
        String nit = nitPrevious.orElse(OPTIONAL_DEFAULT_VALUE);
        String date = datePrevious.orElse(OPTIONAL_DEFAULT_VALUE);
        Collection<Bill> billing = null;

        if (!nit.equals(OPTIONAL_DEFAULT_VALUE)) {
            billing = Billing.INSTANCE.getBillingService().getBillByNit(nit);
        } else if (!date.equals(OPTIONAL_DEFAULT_VALUE)) {
            billing = Billing.INSTANCE.getBillingService().getBillByDate(date);
        } else if (items.size() > 0) {
            billing = Billing.INSTANCE.getBillingService().getBillByItems(items);
        } else {
            billing = Billing.INSTANCE.getBillingService().getBilling();
        }

        if (billing == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(billing).build();
    }

    @GET
    @Path("/{id}")
    public Response showBill(@PathParam("id") int id) {
        Bill bill = Billing.INSTANCE.getBillingService().getBillById(id);

        if (bill == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.ok(bill).build();
    }

    @GET
    @Path("/{id}/items")
    public Response showBillAndItems(@PathParam("id") int id) {
        Bill bill = Billing.INSTANCE.getBillingService().getBillById(id);

        if (bill == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        ItemInterface ItemService = (ItemInterface) Billing.INSTANCE.getBillingService();
        Collection<Item> items = ItemService.getItems(bill);
        bill.setItems(items);

        return Response.ok(bill).build();
    }

    @POST
    public Response addBill(Bill bill) {
        if (bill != null && bill.getItems() != null) {
            ItemInterface itemService = (ItemInterface) Billing.INSTANCE.getBillingService();
            int insert = Billing.INSTANCE.getBillingService().addBill(bill);
            bill.setId(insert);

            if (insert <= 0) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            for (Item item: bill.getItems()) {
                Product product = io.billing.services.Product.INSTANCE.getProductService().getProductById(
                        item.getProduct().getId()
                );

                if (item.getQuantity() <= product.getStock()) {
                    itemService.addItem(bill, item);
                    product.setStock(product.getStock() - item.getQuantity());
                    io.billing.services.Product.INSTANCE.getProductService().updateProduct(product);
                }
            }

            return Response.status(Response.Status.CREATED).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBill(@PathParam("id") int id) {
        Bill bill = Billing.INSTANCE.getBillingService().getBillById(id);

        if (bill == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        ItemInterface itemService = (ItemInterface) Billing.INSTANCE.getBillingService();
        Collection<Item> items = itemService.getItems(bill);

        if (items != null) {
            for (Item item: items) {
                Product product = io.billing.services.Product.INSTANCE.getProductService().getProductById(
                        item.getProduct().getId()
                );
                product.setStock(product.getStock() + item.getQuantity());
                itemService.deleteItem(item.getId());
                io.billing.services.Product.INSTANCE.getProductService().updateProduct(product);
            }
        }

        Billing.INSTANCE.getBillingService().deleteBill(bill);

        return Response.ok().build();
    }
}
