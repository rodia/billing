package io.billing.services;

import io.billing.models.Client;

import java.util.Collection;

public interface ClientInterface {
    int addClient(Client client);

    int updateClient(Client client);

    int deleteClient(Client client);

    int deleteClient(int id);

    Client getClientByNit(String nit);

    Client getClientById(int id);

    Collection<Client> getClients();
}
