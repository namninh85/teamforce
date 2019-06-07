package com.nin.repository;

import com.nin.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByCustomerIdAndEmail(long customerId, String email);
	Customer findByCustomerId(Long Id);
}
