package com.nin.model;
// Generated May 28, 2019 6:18:43 AM by Hibernate Tools 5.4.2.Final

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "contact")
public class Customer implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8963700376471819016L;

	@Id
	private long customerId;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	private String email;
	@Column(name = "mobilephone")
	private String phone;
	private String lang;
	private Integer totalVoucher;
	private Integer totalPoint;
	private Integer totalOffer;
	private boolean isActive;
	private String interestedFields;
	private String address;
	@Column(name = "picture__c")
	private String avartarImg;
	private String bannerHeaderImg;
	private String qrcodeImg;
	@Column(name = "birthdate")
	private Long dob;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "customer_has_voucher", joinColumns = {
			@JoinColumn(name = "customer_id", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name = "voucher_code_id", nullable = false)}
	)
	private List<VoucherCode> voucherCodes;
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "customer_rewards_log", joinColumns = {
			@JoinColumn(name = "customer_id", nullable = false)},
			inverseJoinColumns = {@JoinColumn(name = "loyalty_program_id", nullable = false)}
	)
	private List<LoyaltyProgram> loyaltyPrograms;
	public Customer() {
	}

	public Customer(int customerId) {
		this.customerId = customerId;
	}

	public Customer(int customerId, String firstName, String lastName, String email, String phone, String lang,
			Integer totalVoucher, Boolean isActive, String interestedFields, String address,
			String avartarImg, String bannerHeaderImg, String qrcodeImg , List<VoucherCode> voucherCodes,List<LoyaltyProgram> loyaltyPrograms) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.lang = lang;
		this.totalVoucher = totalVoucher;
		this.isActive = isActive;
		
		this.interestedFields = interestedFields;
		this.address = address;
		this.avartarImg = avartarImg;
		this.bannerHeaderImg = bannerHeaderImg;
		this.qrcodeImg = qrcodeImg;

		this.voucherCodes = voucherCodes;
		this.loyaltyPrograms = loyaltyPrograms;
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
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
		if(this.totalVoucher == null) {
			return 0;
		}
		return this.totalVoucher;
	}

	public void setTotalVoucher(Integer totalVoucher) {
		this.totalVoucher = totalVoucher;
	}
	
	public Integer getTotalPoint() {
		if(this.totalPoint == null) {
			return 0;
		}
		return this.totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getTotalOffer() {
		if(this.totalOffer == null) {
			return 0;
		}
		return totalOffer;
	}

	public void setTotalOffer(Integer totalOffer) {
		this.totalOffer = totalOffer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public List<VoucherCode> getVoucherCodes() {
		return voucherCodes;
	}

	public void setVoucherCodes(List<VoucherCode> voucherCodes) {
		this.voucherCodes = voucherCodes;
	}

	public List<LoyaltyProgram> getLoyaltyPrograms() {
		return loyaltyPrograms;
	}

	public void setLoyaltyPrograms(List<LoyaltyProgram> loyaltyPrograms) {
		this.loyaltyPrograms = loyaltyPrograms;
	}

	public Long getDob() {
		return dob;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}
	
	
}
