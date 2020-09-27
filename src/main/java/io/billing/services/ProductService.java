package io.billing.services;

import io.billing.db.ProductRepository;
import io.billing.models.Product;

import java.sql.SQLException;
import java.util.Collection;

public class ProductService implements ProductInterface {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public int addProduct(Product product) {
        try {
            return this.productRepository.addProduct(product);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateProduct(Product product) {
        try {
            return this.productRepository.updateProduct(product);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteProduct(int id) {
        try {
            return this.productRepository.deleteProduct(id);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteProduct(Product product) {
        try {
            return this.productRepository.deleteProduct(product);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public Collection<Product> getProducts() {
        try {
            return this.productRepository.getProducts();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public Product getProductById(int id) {
        try {
            return this.productRepository.getProductById(id);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
