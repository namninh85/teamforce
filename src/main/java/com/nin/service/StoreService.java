package com.nin.service;

import com.nin.model.Store;
import com.nin.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findByProductId (Long productId , String storeName, String address , String phone){
        return storeRepository.findByProductId(productId,storeName.toLowerCase(),address.toLowerCase(),phone.toLowerCase());
    }
}
