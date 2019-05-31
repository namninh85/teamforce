package com.nin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue
    @Column(nullable = true)
    private Long storeId;
    private String name;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private String phone;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_in_store", joinColumns = {
            @JoinColumn(name = "store_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)}
    )
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "store")
    private Set<StoreUtility> storeUtilities = new HashSet<>();
    public Store() {
    }

    public Store(Long storeId, String name, String ward, String district, String city,
                 String street, String country, String phone,
                  Set<StoreUtility> storeUtilities,List<Product> products) {
        this.storeId = storeId;
        this.name = name;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.products = products;
        this.storeUtilities = storeUtilities;
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Set<StoreUtility> getStoreUtilities() {
        return storeUtilities;
    }

    public void setStoreUtilities(Set<StoreUtility> storeUtilities) {
        this.storeUtilities = storeUtilities;
    }
}
