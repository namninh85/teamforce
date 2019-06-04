package com.nin.service;

import com.nin.model.Voucher;
import com.nin.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public Voucher findByVoucherId (Long Id){
        return  voucherRepository.findByVoucherId(Id);
    }
}
