package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    int save(Product product) throws SQLException;

    List<Product> finAll() throws SQLException;

    int delete(Product product) throws SQLException;
}
