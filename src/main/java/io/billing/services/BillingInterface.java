package io.billing.services;

import io.billing.models.Bill;
import java.util.Collection;
import java.util.List;

public interface BillingInterface {
    Collection<Bill> getBilling();

    Bill getBillById(int id);

    int updateBill(Bill bill);

    int deleteBill(Bill bill);

    int deleteBill(int id);

    int addBill(Bill bill);

    Collection<Bill> getBillByNit(String nit);

    Collection<Bill> getBillByDate(String date);

    Collection<Bill> getBillByItems(List<String> items);
}
