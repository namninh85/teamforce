package com.nin.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaign")
@Where(clause = "is_deleted ='false'")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loyaltyProgramId ;
    private Long voucherId;
    @Column(name = "startdate")
    private BigInteger startDate;
    @Column(name = "enddate")
    private BigInteger endDate;
    @Column(name = "point__c")
    private Integer point;
    @Column(name = "total_release__c")
    private Integer total_release;
     @Column(name = "available__c")
    private Integer available;
    private Boolean isActive;
    private Boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_program_id")
    private List<CustomerRewardsLog> customerRewardsLogs =new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_rewards_log", joinColumns = {
            @JoinColumn(name = "loyalty_program_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "customer_id", nullable = false)}
    )
    private List<Customer> customers;
    public LoyaltyProgram(){}

    public LoyaltyProgram(Long loyaltyProgramId, Long voucherId, BigInteger startDate, BigInteger endDate, Integer point, Integer total_release, Integer available, Boolean isActive, Boolean isDeleted, List<CustomerRewardsLog> customerRewardsLogs, List<Customer> customers) {
        this.loyaltyProgramId = loyaltyProgramId;
        this.voucherId = voucherId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.point = point;
        this.total_release = total_release;
        this.available = available;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.customerRewardsLogs = customerRewardsLogs;
        this.customers = customers;
    }

    public Long getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(Long loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public BigInteger getStartDate() {
        return startDate;
    }

    public void setStartDate(BigInteger startDate) {
        this.startDate = startDate;
    }

    public BigInteger getEndDate() {
        return endDate;
    }

    public void setEndDate(BigInteger endDate) {
        this.endDate = endDate;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getTotal_release() {
        return total_release;
    }

    public void setTotal_release(Integer total_release) {
        this.total_release = total_release;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
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

    public List<CustomerRewardsLog> getCustomerRewardsLogs() {
        return customerRewardsLogs;
    }

    public void setCustomerRewardsLogs(List<CustomerRewardsLog> customerRewardsLogs) {
        this.customerRewardsLogs = customerRewardsLogs;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
