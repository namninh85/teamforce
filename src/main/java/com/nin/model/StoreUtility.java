package com.nin.model;

import javax.persistence.*;

@Entity
@Table(name = "store_utility")
public class StoreUtility {
    @Id
    @GeneratedValue
    @Column(nullable = true)
    private Long store_utility_id ;
    private String name;
    private String image;
    private String webLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    private Store store;

    public StoreUtility() {}

    public StoreUtility(Long store_utility_id, String name, String image, String webLink, Store store) {
        this.store_utility_id = store_utility_id;
        this.name = name;
        this.image = image;
        this.webLink = webLink;
        this.store = store;
    }

    public Long getStore_utility_id() {
        return store_utility_id;
    }

    public void setStore_utility_id(Long store_utility_id) {
        this.store_utility_id = store_utility_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
