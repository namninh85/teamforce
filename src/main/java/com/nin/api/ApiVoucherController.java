package com.nin.api;

import com.nin.model.User;
import com.nin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class ApiVoucherController {
    private UserService userService;

    @PostMapping("/redeem")
    public ResponseEntity<Map<String, Object>> redeemVoucher() {
        try {
            User currentUser = userService.getCurrentUser();


            Map<String, Object> out = new HashMap<>();
            out.put("data", null);
            out.put("error", 0);
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
