package com.nin.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductInStore  implements Serializable {
    @Id
    @Column(name = "store_id",nullable = true)
    private Long storeId;
    @Id
    @Column(name = "product_id",nullable = true)
    private Long productId;
    private Integer stockAmount;
    private boolean isActive;
    private boolean isDeleted;

    public ProductInStore(Long storeId, Long productId, Integer stockAmount, boolean isActive, boolean isDeleted) {
        this.storeId = storeId;
        this.productId = productId;
        this.stockAmount = stockAmount;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(Integer stockAmount) {
        this.stockAmount = stockAmount;
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
}
