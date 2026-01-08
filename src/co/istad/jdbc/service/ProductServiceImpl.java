package co.istad.jdbc.service;

import co.istad.jdbc.dao.ProductDao;
import co.istad.jdbc.dao.ProductDaoImpl;
import co.istad.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public void updateByCode(String code, Product product) {
        try {
            // Style 1 - validate product existing or not by code
            //if (!productDao.existsByCode(code))
            //    throw new RuntimeException("Product code doesn't exist..!");

            // Style 2 - validate product existing or not by code
            Product existingProduct = productDao.findByCode(code)
                    .orElseThrow(() -> new RuntimeException("Product code doesn't exist..!"));

            System.out.println("existing product: " + existingProduct.getCode());

            // name, price, qty (Partially Update)
            if (!product.getName().isBlank())
                existingProduct.setName(product.getName());

            if (product.getPrice() != null)
                existingProduct.setPrice(product.getPrice());

            if (product.getQty() != null)
                existingProduct.setQty(product.getQty());

            int affectedRow = productDao.updateByCode(code, existingProduct);

            if (affectedRow < 1)
                throw new RuntimeException("Update operation failed");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Product product) {
        try {
            int affectedRow = productDao.save(product);
            if (affectedRow < 1)
                throw new RuntimeException("Save operation failed");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteByCode(String code) {
        try {
            // Validate product code exist or not
            boolean isExisted = productDao.existsByCode(code);
            System.out.println("isExisted: " + isExisted);
            if (!isExisted)
                throw new RuntimeException("Product code doesn't exist..!");

            int affectedRow = productDao.deleteByCode(code);
            if (affectedRow < 1)
                throw new RuntimeException("Delete operation failed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            return productDao.finAll();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
