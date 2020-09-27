package io.billing.services;

import io.billing.models.Bill;
import io.billing.models.Item;

import java.util.Collection;

public interface ItemInterface {
    int addItem(Bill bill, Item item);

    Collection<Item> getItems(Bill bill);

    int updateItem(Item item);

    int deleteItem(int id);

    int deleteItem(Item item);
}
