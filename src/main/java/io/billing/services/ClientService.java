package io.billing.services;

import io.billing.db.ClientRepository;
import io.billing.models.Client;

import java.sql.SQLException;
import java.util.Collection;

public class ClientService implements ClientInterface {
    private final ClientRepository repository;

    public ClientService() {
        this.repository = new ClientRepository();
    }

    @Override
    public int addClient(Client client) {
        try {
            return this.repository.addClient(client);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int updateClient(Client client) {
        try {
            return this.repository.updateClient(client);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteClient(Client client) {
        try {
            return this.repository.deleteClient(client);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public int deleteClient(int id) {
        try {
            return this.repository.deleteClient(id);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return 0;
    }

    @Override
    public Client getClientByNit(String nit) {
        try {
            return this.repository.getClientByNit(nit);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Client getClientById(int id) {
        try {
            return this.repository.getClientById(id);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public Collection<Client> getClients() {
        try {
            return this.repository.getClients();
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }
}
