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
    private String address;
    private Double latitude;
    private Double longitude;
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

    public Store(Long storeId, String name, String address, Double latitude, Double longitude, String phone, String utilities, Boolean isActive, Boolean isDeleted, List<Product> products) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
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
}
