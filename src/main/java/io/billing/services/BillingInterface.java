package io.billing.services;

import io.billing.models.Bill;
import java.util.Collection;

public interface BillingInterface {
    Collection<Bill> getBilling();

    Bill getBillById(int id);

    int updateBill(Bill bill);

    int deleteBill(Bill bill);

    int deleteBill(int id);

    int addBill(Bill bill);
}
