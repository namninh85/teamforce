package com.nin.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_category")
@Where(clause = "is_active = 'true'")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;
    private String code;
    private String name;
    private String description;
    private Long parentId;
    private boolean isActive;
    @Column(nullable = false)
    private boolean isDeleted;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_in_pd_category", joinColumns = {
            @JoinColumn(name = "product_category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_id", nullable = false, updatable = false)}
    )


    private List<Product> products;

    public Category() {
    }

    public Category(String code, String name, String description, Long parentId, boolean isActive, boolean isDeleted, List<Product> products) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.parentId = parentId;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.products = products;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
