package io.billing.services;

import io.billing.models.Bill;
import io.billing.models.Item;
import io.billing.models.Product;

import java.util.Collection;

public interface ItemInterface {
    int addItem(Bill bill, Item item, Product product);

    Collection<Item> getItems(Bill bill);

    int updateItem(Item item, Product product);

    int deleteItem(int id);

    int deleteItem(Item item);
}
