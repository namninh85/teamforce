package com.nin.api;

import com.nin.model.Product;
import com.nin.model.Store;
import com.nin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiStoreController {
    private StoreService storeService;

    @Autowired
    public ApiStoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @GetMapping("/store")
    public ResponseEntity<Map<String, Object>> listStore(@RequestParam(value = "productId") Long productId, @RequestParam(value = "storeName") String storeName, @RequestParam(value = "street") String street) {
        List<Store> currentStore = storeService.findByProductId(productId, storeName, street);
        List<Map<String, Object>> result = new ArrayList<>();
        for(Store store : currentStore) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", store.getStoreId());
            obj.put("name", store.getName());
            obj.put("street", store.getStreet());
            obj.put("ward", store.getWard());
            obj.put("district", store.getDistrict());
            obj.put("city", store.getCity());
            obj.put("country", store.getCountry());
            obj.put("phone", store.getPhone());
            obj.put("utility", store.getStoreUtilities());

            result.add(obj);
        }
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("data", result);

        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
