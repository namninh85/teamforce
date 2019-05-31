package com.nin.api;

import com.nin.model.Store;
import com.nin.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Map<String, Object>> listStore(@RequestParam(value = "productId") String productId,
                                                         @RequestParam(value = "storeName") String storeName, @RequestParam(value = "street") String street) {
        List<Store> currentStore = storeService.findByProductId(productId, storeName, street);
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("data", currentStore);
        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
