package com.edu.csc.kt1_buiminhthien.model;


import java.io.Serializable;
import java.util.ArrayList;

//lưu trư dữ liệu vào
public class DataSale implements Serializable {
    private ArrayList<Product> Products;

    public DataSale(){
        Products = new ArrayList<>();
    }
    public ArrayList<Product> getProducts(){
        return Products;
    }
    public void setProducts(ArrayList<Product> products){
        Products = products;
    }
    public void generateProducts(){
        Products.clear();
        Products.add(new Product("Socola Kitkat","Gói",4200));
        Products.add(new Product("Socola Kitkat","Gói",4200));
        Products.add(new Product("Socola Kitkat","Gói",4200));
        Products.add(new Product("Socola Kitkat","Gói",4200));
        Products.add(new Product("Socola Kitkat","Gói",4200));
    }

    private static DataSale dataSale;

    public static DataSale get(){
        if(dataSale==null)
            dataSale=new DataSale();
        return dataSale;
    }
}
