package io.billing.services;

import io.billing.db.ProductRepository;
import io.billing.db.StatsRepository;
import io.billing.models.*;
import io.billing.models.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StatsService implements StatsInterface {
    private final StatsRepository statsRepository;
    private final ProductRepository productRepository;

    public StatsService() {
        this.statsRepository = new StatsRepository();
        this.productRepository = new ProductRepository();
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
        return null;
    }
}
