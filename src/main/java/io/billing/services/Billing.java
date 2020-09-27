package io.billing.services;

public enum Billing {
    INSTANCE;

    private final BillingInterface billingService;

    Billing() {
        this.billingService = new BillingService();
    }

    public BillingInterface getBillingService() {
        return this.billingService;
    }
}
