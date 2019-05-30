package com.nin.api;

import com.nin.model.Product;
import com.nin.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("data", currentProduct);
        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
