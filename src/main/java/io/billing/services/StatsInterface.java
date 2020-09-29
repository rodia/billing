package io.billing.services;

import io.billing.models.ProductSold;

import java.util.Collection;

public interface StatsInterface {
    Collection<ProductSold> getProductsAndSales();
}
