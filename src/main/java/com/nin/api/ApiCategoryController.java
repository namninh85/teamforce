package com.nin.api;

import com.nin.model.Category;
import com.nin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiCategoryController {
    private CategoryService categoryService;

    @Autowired
    public ApiCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/product-categories")
    public ResponseEntity<Map<String, Object>> listCategory() {
        List<Category> currentCategory = categoryService.findAllCategory();
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("data", currentCategory);
        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
}
