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

                case 4 -> {
                    ViewUtil.printHeader("Update product by code");
                    String code = InputUtil.getText("Enter code: ");
                    String name = InputUtil.getText("Enter name: ");
                    BigDecimal price = InputUtil.getMoney("Enter price: ");
                    Integer qty = InputUtil.getInteger("Enter qty: ");

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);
                    product.setQty(qty);
                    try {
                        productService.updateByCode(code, product);
                        ViewUtil.printHeader("Product updated successfully..!");
                    } catch (RuntimeException e) {
                        ViewUtil.printHeader(e.getMessage());
                    }
                }

                case 5 -> {
                    ViewUtil.printHeader("Delete product by code");
                    String code = InputUtil.getText("Enter code: ");
                    ViewUtil.print("Are you sure to delete?", true);
                    String confirm = InputUtil.getText("[Y/n]");
                    if (confirm.equalsIgnoreCase("y")) {
                        // Call logic from service
                        try {
                            productService.deleteByCode(code);
                            ViewUtil.printHeader("Product deleted successfully");
                        } catch (RuntimeException e) {
                            ViewUtil.printHeader(e.getMessage());
                        }
                    } else {
                        ViewUtil.print("Delete cancelled", true);
                    }
                }

                default -> ViewUtil.printHeader("Invalid menu");
            }

        } while (true);
    }
}
