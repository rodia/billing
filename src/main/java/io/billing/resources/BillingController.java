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

@Path("/api/v1/billing")
@Produces(MediaType.APPLICATION_JSON)
public class BillingController {
    @GET
    public Response showBilling() {
        Collection<Bill> billing = Billing.INSTANCE.getBillingService().getBilling();

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

    @GET
    @Path("?nit")
    public Response findByQueryParam(@QueryParam("nit") String nit) {
        return Response.ok(nit).build();
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
