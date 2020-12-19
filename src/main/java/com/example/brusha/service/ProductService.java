package com.example.brusha.service;

import com.example.brusha.models.Customer;
import com.example.brusha.models.Product;
import com.example.brusha.repository.CustomerRepository;
import com.example.brusha.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void create(Product product){

        productRepository.create(product);
    }

    public Boolean update(Product productFromPost){
        productRepository.update(productFromPost);

        return false;

    }

    public List readall(){

        ArrayList<Product> customerArrayList = new ArrayList<>();
        for(Product product : productRepository.readAll()){
            customerArrayList.add(product);
        }
        return customerArrayList;
    }



    public void delete(int productId){


        productRepository.delete(productId);
    }

    public Product read(int productId){


        Product productToReturn = productRepository.readSingleProduct(productId);

        return productToReturn;

    }



}
