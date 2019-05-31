package com.nin.api;

import com.nin.model.Category;
import com.nin.model.Product;
import com.nin.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiProductController {
    private ProductService productService;
    public ApiProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<Map<String, Object>> listProduct(@RequestParam(value = "name") String name ) {
        List<Product> currentProduct = productService.findByName(name);
        List<Map<String, Object>> result = new ArrayList<>();
        for(Product product : currentProduct) {
            Map<String, Object> obj = new HashMap<>();
            obj.put("id", product.getProductId());
            obj.put("code", product.getCode());
            obj.put("name", product.getName());
            obj.put("image", product.getImage());
            obj.put("web_link", product.getWebLink());
            obj.put("description", product.getDescription());
            obj.put("has_new", product.getHasNew());

            result.add(obj);
        }
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("data", result);

        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
