package io.billing.services;

import io.billing.db.*;
import io.billing.models.*;
import io.billing.models.Client;
import io.billing.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StatsService implements StatsInterface {
    private final StatsRepository statsRepository;
    private final ProductRepository productRepository;
    private final BillingRepository billingRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    public StatsService() {
        this.statsRepository = new StatsRepository();
        this.productRepository = new ProductRepository();
        this.billingRepository = new BillingRepository();
        this.clientRepository = new ClientRepository();
        this.itemRepository = new ItemRepository();
    }

    public Collection<ProductSold> getProductsAndSales() {
        try {
            Collection<Product> products = this.productRepository.getProducts();
            Collection<Item> items = this.statsRepository.getItemsSold();
            Collection<ProductSold> sold = new ArrayList<>();

            for (Product product: products) {
                double total = 0;

                for (Item item: items) {
                    if (item.getProduct().getId() == product.getId()) {
                        total += item.getQuantity() * item.getProduct().getUnitPrice();
                    }
                }

                sold.add(new ProductSold(
                        product.getId(),
                        product.getDescription(),
                        product.getUnitPrice(),
                        product.getStock(),
                        total
                ));
            }

            return sold;
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public Collection<ProductItem> getProductsAndItems() {
        try {
            Collection<Product> products = this.productRepository.getProducts();
            Collection<Item> items = this.statsRepository.getItemsSold();
            Collection<ProductItem> sold = new ArrayList<>();

            for (Product product: products) {
                int total = 0;

                for (Item item: items) {
                    if (item.getProduct().getId() == product.getId()) {
                        total += item.getQuantity();
                    }
                }

                sold.add(new ProductItem(
                        product.getId(),
                        product.getDescription(),
                        product.getUnitPrice(),
                        product.getStock(),
                        total
                ));
            }

            return sold;
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<ClientBuy> getClientsBuy() {
        try {
            Collection<Bill> billing = this.billingRepository.getBilling();
            Collection<Client> clients = this.clientRepository.getClients();
            Collection<ClientBuy> clientsBuyers = new ArrayList<>();

            for (Client client: clients) {
                double total = 0;
                for (Bill bill: billing) {
                    if (bill.getClient().getId() == client.getId()) {
                        Collection<Item> items = this.itemRepository.getItems(bill);
                        if (items == null) {
                            continue;
                        }

                        for (Item item: items) {
                            total += item.getQuantity() * item.getProduct().getUnitPrice();
                        }
                    }
                }

                clientsBuyers.add(new ClientBuy(
                        client.getId(),
                        client.getLastname(),
                        client.getFirstname(),
                        client.getNit(),
                        total
                ));
            }

            return clientsBuyers;
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
