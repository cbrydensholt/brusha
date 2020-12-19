package com.example.brusha.repository;

import com.example.brusha.models.Customer;
import com.example.brusha.models.Subscription;
import com.example.brusha.util.DatabaseConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SubscriptionRepository {

    private Connection conn;
    public SubscriptionRepository() {this.conn = DatabaseConnectionManager.getDatabaseConnection();}
/*
    int customerId;
    String name;
    String email;
    int phone;
    String address;
    String cpr;
  */

    public void create(Subscription subscription) {
        try {
            PreparedStatement createCustomer = conn.prepareStatement("INSERT INTO snus(name, brand, price, strength, country)" + "VALUES(?,?,?,?,?) ");
            createCustomer.setDate(1, (Date) subscription.getStartDate());
            createCustomer.setDate(2, (Date) subscription.getEndDate());
            createCustomer.setTime(3, subscription.getTime());
            createCustomer.setBoolean(4, subscription.isActive());
            createCustomer.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Subscription> readAll() {
        ArrayList<Subscription> allSubscriptions = new ArrayList<>();
        try{
            PreparedStatement readAllSubs = conn.prepareStatement("SELECT snusId, name, brand, price, strength, country, img FROM snus");
            ResultSet rs = readAllSubs.executeQuery();
            while(rs.next()){
                Subscription subscription = new Subscription();
                subscription.setId(rs.getLong(1));
                subscription.setStartDate(rs.getDate(2));
                subscription.setEndDate(rs.getDate(3));
                subscription.setTime(rs.getTime(4));
                subscription.setActive(rs.getBoolean(5));

                allSubscriptions.add(subscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSubscriptions;


    }

    public Subscription readSingleSubscription(int subscriptionId) {
        Subscription tempSubscription = new Subscription();
        try{
            PreparedStatement readtemp = conn.prepareStatement("SELECT * from snus WHERE snusId = ?");
            readtemp.setInt(1, subscriptionId);
            ResultSet rs = readtemp.executeQuery();
            while(rs.next()){
                tempSubscription = new Subscription();
                tempSubscription.setId(rs.getLong(1));
                tempSubscription.setStartDate(rs.getDate(2));
                tempSubscription.setEndDate(rs.getDate(3));
                tempSubscription.setTime(rs.getTime(4));
                tempSubscription.setActive(rs.getBoolean(5));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tempSubscription;
    }



    public boolean update(Subscription subscription) {

        try{
            PreparedStatement updatesubscription = conn.prepareStatement("UPDATE snus SET name=?, brand=?, price=?, strength=?, country=? WHERE snusId =?");
            updatesubscription.setDate(1, (Date) subscription.getStartDate());
            updatesubscription.setDate(2, (Date) subscription.getEndDate());
            updatesubscription.setTime(3, subscription.getTime());
            updatesubscription.setBoolean(4, subscription.isActive());
            updatesubscription.setLong(5, subscription.getId());
            updatesubscription.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }



    public boolean delete(int subscriptionId) {
        try {
            PreparedStatement deleteSub = conn.prepareStatement("DELETE FROM snus WHERE snusId = ?");
            deleteSub.setLong(1, subscriptionId);
            deleteSub.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }





}
