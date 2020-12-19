package com.example.brusha.repository;

import com.example.brusha.models.Customer;
import com.example.brusha.util.DatabaseConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {


    private Connection conn;
    public CustomerRepository() {this.conn = DatabaseConnectionManager.getDatabaseConnection();}
/*
    int customerId;
    String name;
    String email;
    int phone;
    String address;
    String cpr;
  */

    public void create(Customer customer) {
        try {
            PreparedStatement createCustomer = conn.prepareStatement("INSERT INTO snus(name, brand, price, strength, country)" + "VALUES(?,?,?,?,?) ");
            createCustomer.setString(1, customer.getName());
            createCustomer.setString(2, customer.getEmail());
            createCustomer.setInt(3, customer.getPhone());
            createCustomer.setString(4, customer.getAddress());
            createCustomer.setString(5, customer.getCpr());
            createCustomer.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Customer> readAll() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        try{
            PreparedStatement readAllSnus = conn.prepareStatement("SELECT snusId, name, brand, price, strength, country, img FROM snus");
            ResultSet rs = readAllSnus.executeQuery();
            while(rs.next()){
                Customer tempCustomer = new Customer();
                tempCustomer.setCustomerId(rs.getInt(1));
                tempCustomer.setName(rs.getString(2));
                tempCustomer.setEmail(rs.getString(3));
                tempCustomer.setPhone(rs.getInt(4));
                tempCustomer.setAddress(rs.getString(5));
                tempCustomer.setCpr(rs.getString(6));

                allCustomers.add(tempCustomer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;


    }

    public Customer readSingleCustomer(int customerId) {
        Customer tempCustomer = new Customer();
        try{
            PreparedStatement readtemp = conn.prepareStatement("SELECT * from snus WHERE snusId = ?");
            readtemp.setInt(1, customerId);
            ResultSet rs = readtemp.executeQuery();
            while(rs.next()){
                tempCustomer = new Customer();
                tempCustomer.setCustomerId(rs.getInt(1));
                tempCustomer.setName(rs.getString(2));
                tempCustomer.setEmail(rs.getString(3));
                tempCustomer.setPhone(rs.getInt(4));
                tempCustomer.setAddress(rs.getString(5));
                tempCustomer.setCpr(rs.getString(6));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tempCustomer;
    }



    public boolean update(Customer customer) {

        try{
            PreparedStatement updatesnus = conn.prepareStatement("UPDATE snus SET name=?, brand=?, price=?, strength=?, country=? WHERE snusId =?");
            updatesnus.setString(1, customer.getName());
            updatesnus.setString(2, customer.getEmail());
            updatesnus.setInt(3, customer.getPhone());
            updatesnus.setString(4, customer.getAddress());
            updatesnus.setString(5, customer.getCpr());
            updatesnus.setInt(6, customer.getCustomerId());
            updatesnus.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



    public boolean delete(int customerId) {
        try {
            PreparedStatement deleteCustomer = conn.prepareStatement("DELETE FROM snus WHERE snusId = ?");
            deleteCustomer.setInt(1, customerId);
            deleteCustomer.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }




}
