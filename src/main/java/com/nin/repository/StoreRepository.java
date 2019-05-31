package com.nin.repository;

import com.nin.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByProductId(Long productId , String storeName , String street);
}
