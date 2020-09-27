package io.billing.services;

public enum Product {
    INSTANCE;

    private final ProductInterface productService;

    Product() {
        this.productService = new ProductService();
    }

    public ProductInterface getProductService() {
        return this.productService;
    }
}
