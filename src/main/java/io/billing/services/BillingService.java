package io.billing.services;

import io.billing.db.BillingRepository;
import io.billing.db.ItemRepository;
import io.billing.models.Bill;
import io.billing.models.Item;
import io.billing.models.Product;

import java.sql.SQLException;
import java.util.Collection;

public class BillingService implements BillingInterface, ItemInterface {
    private final BillingRepository billingRepository;
    private final ItemRepository itemRepository;

    public BillingService() {
        this.billingRepository = new BillingRepository();
        this.itemRepository = new ItemRepository();
    }

    @Override
    public Collection<Bill> getBilling() {
        try {
            return this.billingRepository.getBilling();
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Bill getBillById(int id) {
        try {
            return this.billingRepository.getBillById(id);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateBill(Bill bill) {
        try {
            return this.billingRepository.updateBill(bill);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteBill(Bill bill) {
        try {
            return this.billingRepository.deleteBill(bill);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteBill(int id) {
        try {
            return this.billingRepository.deleteBill(id);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int addBill(Bill bill) {
        try {
            return this.billingRepository.addBill(bill);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int addItem(Bill bill, Item item, Product product) {
        return 0;
    }

    @Override
    public Collection<Item> getItems(Bill bill) {
        return null;
    }

    @Override
    public int updateItem(Item item, Product product) {
        return 0;
    }

    @Override
    public int deleteItem(int id) {
        return 0;
    }

    @Override
    public int deleteItem(Item item) {
        return 0;
    }
}