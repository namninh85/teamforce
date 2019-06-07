package com.nin.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voucher_code")
public class VoucherCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherCodeId;
    private Long voucherId;
    private String voucherCode;
    private Boolean isActive;
    private Boolean isDeleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_has_voucher", joinColumns = {
            @JoinColumn(name = "voucher_code_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "customer_id", nullable = false)}
    )
    private List<Customer> customers;
    public VoucherCode(){}

    public VoucherCode(Long voucherCodeId, Long voucherId, String voucherCode, Boolean isActive, Boolean isDeleted, List<Customer> customers) {
        this.voucherCodeId = voucherCodeId;
        this.voucherId = voucherId;
        this.voucherCode = voucherCode;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.customers = customers;
    }

    public Long getVoucherCodeId() {
        return voucherCodeId;
    }

    public void setVoucherCodeId(Long voucherCodeId) {
        this.voucherCodeId = voucherCodeId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
