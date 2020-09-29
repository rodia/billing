package io.billing.db;

import io.billing.models.Item;
import io.billing.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class StatsRepository extends Repository {
    public Collection<Item> getItemsSold() throws SQLException, ClassNotFoundException {
        String query = "SELECT item.id AS item_id, quantity, product.id AS product_id, description, unitPrice, stock " +
                "FROM item " +
                "INNER JOIN bill ON item.billId = bill.id " +
                "INNER JOIN product ON item.productId = product.id " +
                "ORDER BY product.id ASC";
        ArrayList<Item> items = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        items.add(new Item(
                                rs.getInt("item_id"),
                                new Product(
                                        rs.getInt("product_id"),
                                        rs.getString("description"),
                                        rs.getDouble("unitPrice"),
                                        rs.getInt("stock")
                                ),
                                rs.getInt("quantity")
                        ));
                    }
                }
            }
        }

        return items;
    }
}
