package io.billing.db;

import io.billing.models.Bill;
import io.billing.models.Item;
import io.billing.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ItemRepository extends Repository {
    public int addItem(Bill bill, Item item) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO item (quantity, productId, billId) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, item.getQuantity());
                stmt.setInt(2, item.getProduct().getId());
                stmt.setInt(3, bill.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public Collection<Item> getItems(Bill bill) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM item " +
                "INNER JOIN bill ON item.billId = bill.id " +
                "INNER JOIN product ON item.productId = product.id " +
                "WHERE bill.id = ? ";
        ArrayList<Item> items = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, bill.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        items.add(new Item(
                                rs.getInt("item.id"),
                                new Product(
                                        rs.getInt("product.id"),
                                        rs.getString("product.description"),
                                        rs.getDouble("product.unitPrice"),
                                        rs.getInt("product.stock")
                                ),
                                rs.getInt("quantity")
                        ));
                    }
                }
            }
        }

        return items;
    }

    public int updateItem(Item item) throws SQLException, ClassNotFoundException {
        String query = "UPDATE item SET productId = ?, quantity = ? WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, item.getProduct().getId());
                stmt.setInt(2, item.getQuantity());
                stmt.setInt(3, item.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteItem(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM item WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteItem(Item item) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM item WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, item.getId());

                return stmt.executeUpdate();
            }
        }
    }
}
