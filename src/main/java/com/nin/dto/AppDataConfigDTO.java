package com.nin.dto;


public class AppDataConfigDTO {
    private Long appDataConfigId;
    private String bannerHeaderImg;
    private String interestedField;
    private String logo;
    private String privacyLink;
    private String termConditionsLink;

    public Long getAppDataConfigId() {
        return appDataConfigId;
    }

    public void setAppDataConfigId(Long appDataConfigId) {
        this.appDataConfigId = appDataConfigId;
    }

    public String getBannerHeaderImg() {
        return bannerHeaderImg;
    }

    public void setBannerHeaderImg(String bannerHeaderImg) {
        this.bannerHeaderImg = bannerHeaderImg;
    }

    public String getInterestedField() {
        return interestedField;
    }

    public void setInterestedField(String interestedField) {
        this.interestedField = interestedField;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(String privacyLink) {
        this.privacyLink = privacyLink;
    }

    public String getTermConditionsLink() {
        return termConditionsLink;
    }

    public void setTermConditionsLink(String termConditionsLink) {
        this.termConditionsLink = termConditionsLink;
    }
}
