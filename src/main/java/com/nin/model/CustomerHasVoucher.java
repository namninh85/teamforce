package com.nin.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "customer_has_voucher")
public class CustomerHasVoucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerHasVoucherId;
    @Column(name = "customer_id",nullable = false)
    private Long customerId;
    @Column(name = "voucher_code_id",nullable = false)
    private Long voucherCodeId;
    private BigInteger receivedDate;
    private BigInteger expiredDate;
    private Boolean isActive;
    private Boolean isDeleted;

    public CustomerHasVoucher(){}

    public CustomerHasVoucher(Long customerHasVoucherId, Long customerId, Long voucherCodeId, BigInteger receivedDate, BigInteger expiredDate, Boolean isActive, Boolean isDeleted) {
        this.customerHasVoucherId = customerHasVoucherId;
        this.customerId = customerId;
        this.voucherCodeId = voucherCodeId;
        this.receivedDate = receivedDate;
        this.expiredDate = expiredDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Long getCustomerHasVoucherId() {
        return customerHasVoucherId;
    }

    public void setCustomerHasVoucherId(Long customerHasVoucherId) {
        this.customerHasVoucherId = customerHasVoucherId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVoucherCodeId() {
        return voucherCodeId;
    }

    public void setVoucherCodeId(Long voucherCodeId) {
        this.voucherCodeId = voucherCodeId;
    }

    public BigInteger getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(BigInteger receivedDate) {
        this.receivedDate = receivedDate;
    }

    public BigInteger getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(BigInteger expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
