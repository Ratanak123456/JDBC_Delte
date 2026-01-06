package co.istad.jdbc.model;

import java.math.BigDecimal;

public class Product {

    private Integer id;
    private String code;
    private String name;
    private BigDecimal price;
    private Integer qty;
    private Boolean isDelete;

    public Product() {
    }

    public Product(String code, String name, Integer qty, BigDecimal price, Boolean isDelete) {
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
