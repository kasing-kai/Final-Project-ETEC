package com.example.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.dto.SaleRequest;
import com.example.product.model.Customer;
import com.example.product.repository.CustomerRepository;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer AddCustomer(SaleRequest saleRequest){
        System.out.println(saleRequest.getCustomer());
        return customerRepository.save(saleRequest.getCustomer());
    }


}
