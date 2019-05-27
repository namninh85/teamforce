package com.nin.model;
// Generated May 28, 2019 6:18:43 AM by Hibernate Tools 5.4.2.Final

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer implements java.io.Serializable {
	
	@Id
	private int customerId;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String lang;
	private Integer totalVoucher;
	private Boolean isActive;
	private Boolean isDeleted;
	private String interestedFields;
	private String address;
	private String avartarImg;
	private String bannerHeaderImg;
	private String qrcodeImg;

	public Customer() {
	}

	public Customer(int customerId) {
		this.customerId = customerId;
	}

	public Customer(int customerId, String firstName, String lastName, String email, String phone, String lang,
			Integer totalVoucher, Boolean isActive, Boolean isDeleted, String interestedFields, String address,
			String avartarImg, String bannerHeaderImg, String qrcodeImg) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.lang = lang;
		this.totalVoucher = totalVoucher;
		this.isActive = isActive;
		this.isDeleted = isDeleted;
		this.interestedFields = interestedFields;
		this.address = address;
		this.avartarImg = avartarImg;
		this.bannerHeaderImg = bannerHeaderImg;
		this.qrcodeImg = qrcodeImg;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getTotalVoucher() {
		return this.totalVoucher;
	}

	public void setTotalVoucher(Integer totalVoucher) {
		this.totalVoucher = totalVoucher;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getInterestedFields() {
		return this.interestedFields;
	}

	public void setInterestedFields(String interestedFields) {
		this.interestedFields = interestedFields;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvartarImg() {
		return this.avartarImg;
	}

	public void setAvartarImg(String avartarImg) {
		this.avartarImg = avartarImg;
	}

	public String getBannerHeaderImg() {
		return this.bannerHeaderImg;
	}

	public void setBannerHeaderImg(String bannerHeaderImg) {
		this.bannerHeaderImg = bannerHeaderImg;
	}

	public String getQrcodeImg() {
		return this.qrcodeImg;
	}

	public void setQrcodeImg(String qrcodeImg) {
		this.qrcodeImg = qrcodeImg;
	}

}
