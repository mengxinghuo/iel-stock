package com.truck.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class StockInventory {
    private Integer id;

    private Integer quantity;

    private Integer pandlan;

    public StockInventory(Integer id, Integer quantity, Integer pandlan) {
        this.id = id;
        this.quantity = quantity;
        this.pandlan = pandlan;
    }

    public StockInventory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPandlan() {
        return pandlan;
    }

    public void setPandlan(Integer pandlan) {
        this.pandlan = pandlan;
    }
}