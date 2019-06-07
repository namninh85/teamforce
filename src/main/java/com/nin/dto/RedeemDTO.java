package com.nin.dto;

public class RedeemDTO {
    String LoyaltyProgramId;
    Integer availableVoucher;

    public String getLoyaltyProgramId() {
        return LoyaltyProgramId;
    }

    public void setLoyaltyProgramId(String loyaltyProgramId) {
        LoyaltyProgramId = loyaltyProgramId;
    }

    public Integer getAvailableVoucher() {
        return availableVoucher;
    }

    public void setAvailableVoucher(Integer availableVoucher) {
        this.availableVoucher = availableVoucher;
    }
}

