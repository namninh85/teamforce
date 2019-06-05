package com.nin.model;

import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "customer_rewards_log")
public class CustomerRewardsLog {
    @Id
    @GeneratedValue
    private Long customerRewardsLogId;
    private Long customerId;
    private Long loyaltyProgramId;
    private Long voucherCodeId;
    private Integer pointBurnEarn;
    private BigInteger rewardDate;
    private Boolean isActive;
    private Boolean isDeleted;

    public CustomerRewardsLog() {
    }

    public CustomerRewardsLog(Long customerRewardsLogId, Long customerId, Long loyaltyProgramId, Long voucherCodeId, Integer pointBurnEarn, BigInteger rewardDate,  Boolean isActive, Boolean isDeleted) {
        this.customerRewardsLogId = customerRewardsLogId;
        this.customerId = customerId;
        this.loyaltyProgramId = loyaltyProgramId;
        this.voucherCodeId = voucherCodeId;
        this.pointBurnEarn = pointBurnEarn;
        this.rewardDate = rewardDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Long getCustomerRewardsLogId() {
        return customerRewardsLogId;
    }

    public void setCustomerRewardsLogId(Long customerRewardsLogId) {
        this.customerRewardsLogId = customerRewardsLogId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLoyaltyProgramId() {
        return loyaltyProgramId;
    }

    public void setLoyaltyProgramId(Long loyaltyProgramId) {
        this.loyaltyProgramId = loyaltyProgramId;
    }

    public Long getVoucherCodeId() {
        return voucherCodeId;
    }

    public void setVoucherCodeId(Long voucherCodeId) {
        this.voucherCodeId = voucherCodeId;
    }

    public Integer getPointBurnEarn() {
        return pointBurnEarn;
    }

    public void setPointBurnEarn(Integer pointBurnEarn) {
        this.pointBurnEarn = pointBurnEarn;
    }

    public BigInteger getRewardDate() {
        return rewardDate;
    }

    public void setRewardDate(BigInteger rewardDate) {
        this.rewardDate = rewardDate;
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
