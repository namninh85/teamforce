package com.nin.service;

import com.nin.model.VoucherCode;
import com.nin.repository.VoucherCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherCodeService {
    @Autowired
    private VoucherCodeRepository voucherCodeRepository;
    @Autowired
    public VoucherCodeService(VoucherCodeRepository voucherCodeRepository) {
        this.voucherCodeRepository = voucherCodeRepository;
    }

    public Long findVoucherCodeByVoucherId(Long voucherId){
        return voucherCodeRepository.findVoucherCodeByVoucherId(voucherId);
    }
}
