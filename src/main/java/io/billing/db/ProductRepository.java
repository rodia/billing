package io.billing.db;

import io.billing.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductRepository extends Repository {
    public int addProduct(Product product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO product (description, unitPrice, stock) VALUES (?, ?, ?)";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, product.getDescription());
                stmt.setDouble(2, product.getUnitPrice());
                stmt.setInt(3, product.getStock());

                return stmt.executeUpdate();
            }
        }
    }

    public int updateProduct(Product product) throws SQLException, ClassNotFoundException {
        String query = "UPDATE product SET description = ?, unitPrice = ?, stock = ? WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, product.getDescription());
                stmt.setDouble(2, product.getUnitPrice());
                stmt.setInt(3, product.getStock());
                stmt.setInt(4, product.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteProduct(int id) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM product WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(4, id);

                return stmt.executeUpdate();
            }
        }
    }

    public int deleteProduct(Product product) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM product WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(4, product.getId());

                return stmt.executeUpdate();
            }
        }
    }

    public Collection<Product> getProducts() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM product ";
        ArrayList<Product> products = new ArrayList<>();

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        products.add(new Product(
                                rs.getInt("id"),
                                rs.getString("description"),
                                rs.getDouble("unitPrice"),
                                rs.getInt("stock")
                        ));
                    }
                }
            }
        }

        return products;
    }

    public Product getProductById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM product WHERE id = ?";

        try (Connection connection = getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Product(
                                rs.getInt("id"),
                                rs.getString("description"),
                                rs.getDouble("unitPrice"),
                                rs.getInt("stock")
                        );
                    }
                }
            }
        }

        return null;
    }
}
