package io.billing.services;

import io.billing.models.ClientBuy;
import io.billing.models.ProductItem;
import io.billing.models.ProductSold;

import java.util.Collection;

public interface StatsInterface {
    Collection<ProductSold> getProductsAndSales();

    Collection<ProductItem> getProductsAndItems();

    Collection<ClientBuy> getClientsBuy();
}
