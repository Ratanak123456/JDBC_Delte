package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    // 1. Operation: Check a product by code
    // 2. Expected return value: boolean
    // 3. Parameters: code
    boolean existsByCode(String code) throws SQLException;


    // 1. Operation: Select a product by code
    // 2. Expected return value: Single Object of Product
    // 3. Parameters: code
    Optional<Product> findByCode(String code) throws SQLException;


    // 1. Operation: Update an existing record by code in database
    // 2. Expected return value -> affected row number
    // 3. Parameters: code, product
    int updateByCode(String code, Product product) throws SQLException;

    int save(Product product) throws SQLException;

    List<Product> finAll() throws SQLException;

    int delete(Product product) throws SQLException;
}
