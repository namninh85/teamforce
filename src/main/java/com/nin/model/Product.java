package com.nin.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private boolean is_active;

    @Column(name = "is_deleted")
    private boolean is_deleted;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_in_pd_category", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "product_category_id", nullable = false, updatable = false)}
    )

    private List<Category> categories;

    public Product(String code, String name, String description, boolean is_active, boolean is_deleted, List<Category> categories) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.is_active = is_active;
        this.is_deleted = is_deleted;
        this.categories = categories;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
