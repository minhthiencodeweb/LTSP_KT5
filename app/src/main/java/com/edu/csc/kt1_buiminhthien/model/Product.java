package com.edu.csc.kt1_buiminhthien.model;

import java.io.Serializable;

//giá trị thay đổi trên item
public class Product implements Serializable {
    private String ProductName;
    private String ProductUnit;
    private double ProductPrice;

    public Product() {
        ProductName = "";
        ProductUnit = "";
        ProductPrice=0;
    }

    public Product(String productName, String productUnit, double productPrice) {
        ProductName = productName;
        ProductUnit = productUnit;
        ProductPrice = productPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }
}
