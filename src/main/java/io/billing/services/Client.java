package io.billing.services;

public enum Client {
    INSTANCE;

    private final ClientInterface clientService;

    Client() {
        this.clientService = new ClientService();
    }

    public ClientInterface getClientService() {
        return this.clientService;
    }
}
