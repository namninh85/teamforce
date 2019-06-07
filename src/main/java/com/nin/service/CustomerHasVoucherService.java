package com.nin.service;

import com.nin.model.CustomerHasVoucher;
import com.nin.repository.CustomerHasVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerHasVoucherService {
    @Autowired
    private CustomerHasVoucherRepository customerHasVoucherRepository;
    @Autowired
    public CustomerHasVoucherService(CustomerHasVoucherRepository customerHasVoucherRepository) {
        this.customerHasVoucherRepository = customerHasVoucherRepository;
    }

    public Integer countVoucherByCustomerId(Long Id){
        return customerHasVoucherRepository.countVoucherByCustomerId(Id);
    }
    public void createCustomerHasVoucher(CustomerHasVoucher customerHasVoucher){
        customerHasVoucherRepository.save(customerHasVoucher);
        return;
    }

}
