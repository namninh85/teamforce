package com.nin.service;

import com.nin.model.Store;
import com.nin.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    @Qualifier("storeRepositoryImpl")
    private StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> findByProductId (String productId , String storeName, String street){
        return storeRepository.findByProductId(productId,storeName,street);
    }
}
