package com.example.brusha.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Statistic {

    private Long id;

    Date date;



    // prøv at lave many to many relation på customer / commercial. husk ret på customer også
// tester lige om dette virker istedet for en "many to one relation
//@ManyToMany(cascade = CascadeType.ALL)
////specificer join tabellen
//@JoinTable ( name = "Statistic_customer",
//        joinColumns = @JoinColumn(name = "statistic_id"),
//        inverseJoinColumns = @JoinColumn(name = "customer_id")
//)
//private Set<Customer> customers = new HashSet<>();


// ------- // many to one customer relation
//
}
