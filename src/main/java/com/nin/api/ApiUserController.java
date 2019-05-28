package com.nin.api;

import com.nin.model.Customer;
import com.nin.model.User;
import com.nin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class ApiUserController {

    private UserService userService;

    @Autowired
    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> userDetails() {

        User currentUser = userService.getCurrentUser();
        Customer aCustomer = userService.findCustomerByEmail(currentUser.getEmail());

        Map<String, Object> out = new HashMap<String, Object>() {{
            put("id", currentUser.getId());
            put("email", currentUser.getEmail());
            if(aCustomer != null){
                put("address", aCustomer.getAddress());
            }            
        }};
        
        
        

        return new ResponseEntity<>(out, HttpStatus.OK);
    }

}
