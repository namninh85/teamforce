package com.nin.service;

import com.nin.model.Customer;
import com.nin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByCustomerId(Long Id) {
        return customerRepository.findByCustomerId(Id);
    }

    public void updateVoucherAndPointCustomer(Customer customer) {
        customerRepository.save(customer);
        return;
    }
}
