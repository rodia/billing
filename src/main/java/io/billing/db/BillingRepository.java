package io.billing.db;

import io.billing.models.Bill;
import io.billing.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class BillingRepository extends Repository {
    public Collection<Bill> getBilling() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM bill INNER JOIN client ON bill.clientId = client.id";
        Collection<Bill> elements = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        elements.add(new Bill(
                                rs.getInt("id"),
                                rs.getString("number"),
                                new Date(rs.getString("date")),
                                new Client(
                                        rs.getInt("client.id"),
                                        rs.getString("lastname"),
                                        rs.getString("firstname"),
                                        rs.getString("nit")
                                )
                        ));
                    }
                }
            }
        }

        return elements;
    }

    public int addBill(Bill bill) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO bill (number, clientId, date) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, bill.getBillNumber());
                stmt.setInt(2, bill.getClient().getId());
                stmt.setString(3, bill.getDate().toString());

                return stmt.executeUpdate();
            }
        }
    }

    public Bill getBillById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM bill INNER JOIN client ON bill.clientId = client.id WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Bill(
                                rs.getString("number"),
                                new Date(rs.getString("date")),
                                new Client(
                                        rs.getInt("client.id"),
                                        rs.getString("lastname"),
                                        rs.getString("firstname"),
                                        rs.getString("nit")
                                )
                        );
                    }
                }
            }
        }

        return null;
    }

    public int updateBill(Bill bill) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bill SET number = ?, clientId = ?, date = ? WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, bill.getBillNumber());
                stmt.setInt(2, bill.getClient().getId());
                stmt.setString(3, bill.getDate().toString());
                stmt.setInt(4, bill.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteBill(Bill bill) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM bill WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, bill.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteBill(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM bill WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);

                return stmt.executeUpdate();
            }
        }
    }
}
