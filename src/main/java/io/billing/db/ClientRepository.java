package io.billing.db;

import io.billing.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ClientRepository extends Repository {
    public int addClient(Client client) throws SQLException, ClassNotFoundException  {
        String query = "INSERT INTO client (firstname, lastname, nit) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, client.getFirstname());
                stmt.setString(2, client.getLastname());
                stmt.setString(3, client.getNit());

                return stmt.executeUpdate();
            }
        }
    }

    public int updateClient(Client client) throws SQLException, ClassNotFoundException  {
        String query = "UPDATE client SET firstname = ?, lastname = ?, nit = ? WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, client.getFirstname());
                stmt.setString(2, client.getLastname());
                stmt.setString(3, client.getNit());
                stmt.setInt(4, client.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteClient(Client client) throws SQLException, ClassNotFoundException  {
        String query = "DELETE FROM client WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, client.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteClient(int id) throws SQLException, ClassNotFoundException  {
        String query = "DELETE FROM client WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);

                return stmt.executeUpdate();
            }
        }
    }

    public Client getClientByNit(String nit) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client WHERE nit = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, nit);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Client(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("nit")
                        );
                    }
                }
            }
        }

        return null;
    }

    public Client getClientById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Client(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("nit")
                        );
                    }
                }
            }
        }

        return null;
    }

    public Collection<Client> getClients() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM client ";
        ArrayList<Client> clients = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        clients.add(new Client(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("lastname"),
                                rs.getString("nit")
                        ));
                    }
                }
            }
        }

        return clients;
    }
}
