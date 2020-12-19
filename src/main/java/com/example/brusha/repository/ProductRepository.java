package com.example.brusha.repository;

import com.example.brusha.models.Customer;
import com.example.brusha.models.Product;
import com.example.brusha.util.DatabaseConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private Connection conn;
    public ProductRepository() {this.conn = DatabaseConnectionManager.getDatabaseConnection();}
/*
    int customerId;
    String name;
    String email;
    int phone;
    String address;
    String cpr;
  */

    public void create(Product product) {
        try {
            PreparedStatement createCustomer = conn.prepareStatement("INSERT INTO snus(name, brand, price, strength, country)" + "VALUES(?,?,?,?,?) ");
            createCustomer.setString(1, product.getName());
            createCustomer.setString(2, product.getProducer());
            createCustomer.setDouble(3, product.getPrice());
            createCustomer.setDate(4, (Date) product.getDate());
            createCustomer.setBoolean(5, product.isActive());
            createCustomer.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Product> readAll() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try{
            PreparedStatement readAllProducts = conn.prepareStatement("SELECT snusId, name, brand, price, strength, country, img FROM snus");
            ResultSet rs = readAllProducts.executeQuery();
            while(rs.next()){
                Product tempProduct = new Product();
                tempProduct.setId(rs.getInt(1));
                tempProduct.setName(rs.getString(2));
                tempProduct.setProducer(rs.getString(3));
                tempProduct.setPrice(rs.getInt(4));
                tempProduct.setDate(rs.getDate(5));
                tempProduct.setActive(rs.getBoolean(6));

                allProducts.add(tempProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allProducts;


    }

    public Product readSingleProduct(int productId) {
        Product tempProduct = new Product();
        try{
            PreparedStatement readtemp = conn.prepareStatement("SELECT * from snus WHERE snusId = ?");
            readtemp.setInt(1, productId);
            ResultSet rs = readtemp.executeQuery();
            while(rs.next()){
                tempProduct = new Product();
                tempProduct.setId(rs.getInt(1));
                tempProduct.setName(rs.getString(2));
                tempProduct.setProducer(rs.getString(3));
                tempProduct.setPrice(rs.getInt(4));
                tempProduct.setDate(rs.getDate(5));
                tempProduct.setActive(rs.getBoolean(6));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tempProduct;
    }



    public boolean update(Product product) {

        try{
            PreparedStatement updateproduct = conn.prepareStatement("UPDATE snus SET name=?, brand=?, price=?, strength=?, country=? WHERE snusId =?");
            updateproduct.setString(1, product.getName());
            updateproduct.setString(2, product.getProducer());
            updateproduct.setDouble(3, product.getPrice());
            updateproduct.setDate(4, (Date) product.getDate());
            updateproduct.setBoolean(5, product.isActive());
            updateproduct.setInt(6, product.getId());
            updateproduct.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



    public boolean delete(int productId) {
        try {
            PreparedStatement deleteProduct = conn.prepareStatement("DELETE FROM snus WHERE snusId = ?");
            deleteProduct.setInt(1, productId);
            deleteProduct.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }



}
