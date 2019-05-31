package com.nin.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String code;
    private String name;
    private String description;
    private String image;
    private String webLink;
    private Boolean hasNew;
    private Boolean isActive;
    private Boolean isDeleted;


    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "product_in_pd_category", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_category_id", nullable = false, updatable = false)}
    )
    private List<Category> categories;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "product_in_store", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "store_id", nullable = false, updatable = false)}
    )
    private List<Store> stores;

    public Product() {
    }

    public Product(Long productId, String code, String name, String description, String image,
                   String webLink, Boolean hasNew, Boolean isActive, Boolean isDeleted,
                   List<Category> categories, List<Store> stores) {
        this.productId = productId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.webLink = webLink;
        this.hasNew = hasNew;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.categories = categories;
        this.stores = stores;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public Boolean getHasNew() {
        return hasNew;
    }

    public void setHasNew(Boolean hasNew) {
        this.hasNew = hasNew;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
