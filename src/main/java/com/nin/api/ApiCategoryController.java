package com.nin.api;

import com.nin.model.Category;
import com.nin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseEntity<Map<String, Object>> findAllCategory() {
        try {
            List<Category> currentCategory = categoryService.findAllCategory();
            List<Map<String, Object>> result = new ArrayList<>();
            for (Category category : currentCategory) {
                Map<String, Object> obj = new HashMap<>();
                obj.put("id", category.getProductCategoryId());
                obj.put("code", category.getCode());
                obj.put("name", category.getName());
                obj.put("image", category.getImage());
                obj.put("web_link", category.getWebLink());
                obj.put("description", category.getDescription());
                obj.put("parent_id", category.getParentId());

                result.add(obj);
            }
            Map<String, Object> out = new HashMap<String, Object>() {{
                put("data", result);
                put("error", 0);
            }};

            return new ResponseEntity<>(out, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> responseMap = new HashMap<String, Object>();
            responseMap.put("Message", e.getMessage());
            responseMap.put("data", responseMap);
            responseMap.put("error", -1);
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }
}
