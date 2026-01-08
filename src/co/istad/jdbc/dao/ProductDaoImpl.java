package co.istad.jdbc.dao;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private final Connection conn;

    public ProductDaoImpl() {
        conn = DbConfig.getInstance();
    }

    @Override
    public List<Product> finAll() throws SQLException {

        final String SQL = """
                SELECT *
                FROM products
                ORDER BY id;
                """;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        List<Product> productList = new ArrayList<>();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQty(rs.getInt("qty"));
            product.setDelete(rs.getBoolean("is_deleted"));
            productList.add(product);
        }


        return productList;
    }

    // SOFT DELETE
    @Override
    public int deleteByCode(String code) throws SQLException {
        final String SQL = """
                DELETE
                FROM products
                WHERE code = ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, code);
        return pstmt.executeUpdate();
    }

    @Override
    public Optional<Product> findByCode(String code) throws SQLException {

        // Define SQL
        final String SQL = """
                SELECT *
                FROM products
                WHERE code = ?
                """;

        // Create statement object
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, code);

        ResultSet rs = pstmt.executeQuery();
        Product product;
        if (rs.next()) {
            product = new Product();
            product.setId(rs.getInt("id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setQty(rs.getInt("qty"));
            product.setDelete(rs.getBoolean("is_deleted"));
            return Optional.of(product);
        }

        return Optional.empty();
    }

    @Override
    public boolean existsByCode(String code) throws SQLException {
        // Define SQL
        final String SQL = """
                SELECT EXISTS(
                    SELECT code FROM products WHERE code = ?
                );
                """;
        // Create statement object
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, code);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
            return rs.getBoolean("exists");
        else
            return false;
    }

    @Override
    public int updateByCode(String code, Product product) throws SQLException {
        // Define SQL
        final String SQL = """
                UPDATE products
                SET name = ?, price = ?, qty = ?
                WHERE code = ?
                """;

        // Create statement object
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, product.getName());
        pstmt.setBigDecimal(2, product.getPrice());
        pstmt.setInt(3, product.getQty());
        pstmt.setString(4, code);

        return pstmt.executeUpdate();
    }

    @Override
    public int save(Product product) throws SQLException {

        final String SQL = """
                INSERT INTO products(code, name, price, qty, is_deleted)
                VALUES (?, ?, ?, ?, ?);
                """;

        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, product.getCode());
        ps.setString(2, product.getName());
        ps.setBigDecimal(3, product.getPrice());
        ps.setInt(4, product.getQty());
        ps.setBoolean(5, product.getDelete());

        return ps.executeUpdate();
    }
}
