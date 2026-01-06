package co.istad.jdbc;

import co.istad.jdbc.config.DbConfig;
import co.istad.jdbc.model.Product;
import co.istad.jdbc.service.ProductService;
import co.istad.jdbc.service.ProductServiceImpl;
import co.istad.jdbc.util.InputUtil;
import co.istad.jdbc.util.ViewUtil;

import java.math.BigDecimal;
import java.util.List;

public class JdbcProgram {

    public static void main(String[] args) {

        DbConfig.init();

        ProductService productService = new ProductServiceImpl();

        do {
            ViewUtil.printMenu();
            int option = InputUtil.getInteger("Enter menu: ");

            switch (option) {
                case 0 -> System.exit(0);

                case 1 -> {
                    List<Product> productList = productService.findAll();
                    ViewUtil.printProductList(productList);
                }

                case 2 -> System.out.println("Search (not implemented)");

                case 3 -> {
                    String code = InputUtil.getText("Enter code: ");
                    String name = InputUtil.getText("Enter name: ");
                    BigDecimal price = InputUtil.getMoney("Enter price: ");
                    Integer qty = InputUtil.getInteger("Enter qty: ");

                    Product product = new Product(code, name, qty, price, false);

                    productService.save(product);
                    ViewUtil.printHeader("Product saved successfully");
                }

                case 5 -> {
                    Integer id = InputUtil.getInteger("Enter Product ID to delete: ");

                    Product product = new Product();
                    product.setId(id);

                    productService.delete(product);
                    ViewUtil.printHeader("Product deleted successfully");
                }

                default -> ViewUtil.printHeader("Invalid menu");
            }

        } while (true);
    }
}
