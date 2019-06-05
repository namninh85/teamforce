package com.nin.api;

import com.nin.model.LoyaltyProgram;
import com.nin.model.Voucher;
import com.nin.service.LoyaltyProgramService;
import com.nin.service.VoucherService;
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
public class ApiLoyaltyProgramController {

    private LoyaltyProgramService loyaltyProgramService;
    private VoucherService voucherService;

    public ApiLoyaltyProgramController(LoyaltyProgramService loyaltyProgramService, VoucherService voucherService) {
        this.loyaltyProgramService = loyaltyProgramService;
        this.voucherService = voucherService;
    }

    @GetMapping("/program")
    public ResponseEntity<Map<String, Object>> findLoyaltyProgramByDate() {
        try {
            List<LoyaltyProgram> currentLoyaltyProgram = loyaltyProgramService.findLoyaltyProgramByDate();
            List<Map<String, Object>> result = new ArrayList<>();
            for (LoyaltyProgram loyaltyProgram : currentLoyaltyProgram) {
                Voucher voucher =voucherService.findByVoucherId(loyaltyProgram.getVoucherId());
                Map<String, Object> obj = new HashMap<>();
                obj.put("voucherId", voucher.getVoucherId());
                obj.put("loyaltyProgramId", loyaltyProgram.getLoyaltyProgramId());
                obj.put("voucherName", voucher.getName());
                obj.put("image", voucher.getImage());
                obj.put("price", voucher.getValue());
                obj.put("currency", voucher.getCurrency());
                obj.put("releasesTotal", loyaltyProgram.getTotal_release());
                obj.put("point", loyaltyProgram.getPoint());
                obj.put("availables", loyaltyProgram.getAvailable());
                obj.put("uAvailables", 0);
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
