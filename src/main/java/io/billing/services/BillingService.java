package io.billing.services;

import io.billing.db.BillingRepository;
import io.billing.db.ItemRepository;
import io.billing.models.Bill;
import io.billing.models.Item;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
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
    public Collection<Bill> getBillByNit(String nit) {
        try {
            return this.billingRepository.getBillingByNit(nit);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<Bill> getBillByDate(String date) {
        try {
            return this.billingRepository.getBillingByDate(this.formatDate(date));
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    /**
     * String defaultFormat = "dd-MM-yyyy";
     *
     * @param date
     * @return Formatted date as string.
     */
    private String formatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6);

        return day + "-" + month + "-" + year;
    }

    @Override
    public Collection<Bill> getBillByItems(List<String> items) {
        try {
            return this.billingRepository.getBillingByItems(items);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public int addItem(Bill bill, Item item) {
        try {
            return this.itemRepository.addItem(bill, item);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public Collection<Item> getItems(Bill bill) {
        try {
            return this.itemRepository.getItems(bill);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateItem(Item item) {
        try {
            return this.itemRepository.updateItem(item);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteItem(int id) {
        try {
            return this.itemRepository.deleteItem(id);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteItem(Item item) {
        try {
            return this.itemRepository.deleteItem(item);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return 0;
    }
}
