package com.nin.api;

import com.nin.model.AppDataConfig;
import com.nin.model.Category;
import com.nin.model.InterestedField;
import com.nin.service.AppDataConfigService;
import com.nin.service.InterestedFieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiAppDataConfigController {
    private AppDataConfigService appDataConfigService;
    private InterestedFieldService interestedFieldService;

    public ApiAppDataConfigController(AppDataConfigService appDataConfigService, InterestedFieldService interestedFieldService) {
        this.appDataConfigService = appDataConfigService;
        this.interestedFieldService = interestedFieldService;
    }

    @GetMapping("/app-data")
    public ResponseEntity<Map<String, Object>> findAppDataConfig() {
        try {
            AppDataConfig appDataConfig = appDataConfigService.findByIsActive();
            Map<String, Object> obj = new HashMap<>();
            obj.put("appDataConfigId", appDataConfig.getAppDataConfigId());
            obj.put("bannerHeaderImage", appDataConfig.getBannerHeaderImg());
            List<Map<String, Object>> listFields = new ArrayList<>();
            if (appDataConfig.getInterestedFields() != null) {
                List<Object[]> interestedFields = interestedFieldService.findByListId(appDataConfig.getInterestedFields());
                for (Object[] field : interestedFields) {
                    Map<String, Object> fieldObj = new HashMap<>();
                    fieldObj.put("value", field[0]);
                    fieldObj.put("name", field[1]);
                    listFields.add(fieldObj);
                }
            }

            obj.put("interestedFields", listFields);
            obj.put("logo", appDataConfig.getLogo());
            obj.put("privacyLink", appDataConfig.getPrivacyLink());
            obj.put("termConditionsLink", appDataConfig.getTermConditionsLink());
            Map<String, Object> out = new HashMap<String, Object>() {{
                put("data", obj);
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

//    @PostMapping("/app-data")
//    public ResponseEntity<Map<String, Object>> createOrUpdateAppDataConfig(@RequestBody Map<String, Object> appDataConfigDTO) {
//        try {
//            Map<String, Object> obj = new HashMap<>();
//            Map<String, Object> out = new HashMap<String, Object>();
//            AppDataConfig appDataConfig = new AppDataConfig();
//            Integer appDataConfigId = (Integer) appDataConfigDTO.get("app_data_config_id");
//            if (appDataConfig != null || appDataConfig > 0 ){
//
//            }
//            if (appDataConfigDTO.get("app_data_config_id") != null) {
//                appDataConfig.setAvartarImg(profileDTO.get("avatarImage").toString());
//            }
//            AppDataConfig appDataConfig = appDataConfigService.findByAppDataConfigId(Id);
//            if (appDataConfig == null) {
//                obj.put("Message", "App Data Config is not available !");
//                out.put("data", obj);
//                out.put("error", 0);
//                return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
//            }
//            if ()
//
//
//
//
//            return new ResponseEntity<>(out, HttpStatus.OK);
//        } catch (Exception e) {
//            Map<String, Object> responseMap = new HashMap<String, Object>();
//            responseMap.put("Message", e.getMessage());
//            responseMap.put("data", responseMap);
//            responseMap.put("error", -1);
//            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
//        }
//    }
}
