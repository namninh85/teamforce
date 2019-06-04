package com.nin.model;

import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
@Where(clause = "is_deleted='false'")
public class Store {
    @Id
    @GeneratedValue
    private Long storeId;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private String phone;
    private String utilities;
    private Boolean isActive;
    private Boolean isDeleted;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_in_store", joinColumns = {
            @JoinColumn(name = "store_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)}
    )
    private List<Product> products;
    public Store(){}
    public Store(Long storeId, String name, String street, String ward, String district, String city,
                 String country, String phone, Boolean isActive, Boolean isDeleted,String utilities,
                 List<Product> products) {
        this.storeId = storeId;
        this.name = name;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.utilities = utilities;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.products = products;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

}
