package com.nin.api;

import com.nin.model.Customer;
import com.nin.model.User;
import com.nin.service.UserService;
import com.nin.util.DateUtil;
import com.nin.util.InterestedField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        Map<String, Object> out = new HashMap<String, Object>() {{
            put("id", currentUser.getId());
            put("email", currentUser.getEmail());
                        
        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }
    
    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> userProfile() {

    	User currentUser = userService.getCurrentUser();
        Customer aCustomer = userService.findByCustomerIdAndEmail(currentUser.getId(), currentUser.getEmail());

        Map<String, Object> out = new HashMap<String, Object>() {{
            put("id", currentUser.getId());
            put("email", currentUser.getEmail());
            if(aCustomer != null){
                put("name", aCustomer.getFirstName() + " " + aCustomer.getLastName());
                put("phone", aCustomer.getPhone());
                put("address", aCustomer.getAddress());
                put("language", aCustomer.getLang());
                put("avatarImage", aCustomer.getAvartarImg());
                put("registerDate", DateUtil.instantToString(currentUser.getCreated()));
                put("currentBalancePoints", aCustomer.getTotalPoint());
                put("vouchers", aCustomer.getTotalVoucher());
                put("offers", aCustomer.getTotalOffer());
                put("bannerHeaderImage", aCustomer.getBannerHeaderImg());
                put("qrcodeImage", aCustomer.getQrcodeImg());
                
                String interestedFields = aCustomer.getInterestedFields();
                ArrayList<Map<String, Object>> interestedFieldsMap = new ArrayList<Map<String,Object>>();
                if(!StringUtils.isEmpty(interestedFields)) {
                	String[] ary = interestedFields.split(",");
                	for(int i = 0; i < ary.length; i++) {
                		InterestedField field = InterestedField.valueOf("_"+ary[i]);
                		if(field != null) {
                			Map<String, Object> interestedFieldObj = new HashMap<String, Object>();
                			interestedFieldObj.put("value",Integer.parseInt(ary[i]));
                			interestedFieldObj.put("name",field.getstringValue());
                			interestedFieldsMap.add(interestedFieldObj);
                		}                		
                	}                	
                }
                put("interestedFields", interestedFieldsMap);
                
            }            
        }};
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

}
