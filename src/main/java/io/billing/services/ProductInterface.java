package io.billing.services;

import io.billing.models.Product;
import java.util.Collection;

public interface ProductInterface {
    int addProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(int id);

    int deleteProduct(Product product);

    Collection<Product> getProducts();

    Product getProductById(int id);
}
