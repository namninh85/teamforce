package com.nin.api;

import com.nin.model.*;
import com.nin.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiVoucherController {
    private UserService userService;
    private LoyaltyProgramService loyaltyProgramService;
    private VoucherCodeService voucherCodeService;
    private CustomerRewardsLogService customerRewardsLogService;
    private VoucherService voucherService;
    private CustomerHasVoucherService customerHasVoucherService;
    private CustomerService customerService;

    public ApiVoucherController(UserService userService, LoyaltyProgramService loyaltyProgramService, VoucherCodeService voucherCodeService, CustomerRewardsLogService customerRewardsLogService, VoucherService voucherService, CustomerHasVoucherService customerHasVoucherService, CustomerService customerService) {
        this.userService = userService;
        this.loyaltyProgramService = loyaltyProgramService;
        this.voucherCodeService = voucherCodeService;
        this.customerRewardsLogService = customerRewardsLogService;
        this.voucherService = voucherService;
        this.customerHasVoucherService = customerHasVoucherService;
        this.customerService = customerService;
    }

    @PostMapping("/redeem")
    public ResponseEntity<Map<String, Object>> redeemVoucher(@RequestBody Map<String, Object> redeemDTO) {
        try {
            Long date = new Date().getTime() / 1000;
            User currentUser = userService.getCurrentUser();
            Customer customer = customerService.findByCustomerId(currentUser.getId());
            if (redeemDTO.get("loyaltyProgramId") != null) {
                LoyaltyProgram loyaltyProgram = loyaltyProgramService.finByLoyaltyProgramId(Long.parseLong(redeemDTO.get("loyaltyProgramId").toString()));
                Voucher voucher = voucherService.findByVoucherId(loyaltyProgram.getVoucherId());
                Long voucherCode = voucherCodeService.findVoucherCodeByVoucherId(loyaltyProgram.getVoucherId());
                //Insert CustomerRewardsLog
                CustomerRewardsLog customerRewardsLog = new CustomerRewardsLog();
                customerRewardsLog.setCustomerId(customer.getCustomerId());
                customerRewardsLog.setPointBurnEarn(-1*loyaltyProgram.getPoint());
                customerRewardsLog.setLoyaltyProgramId(loyaltyProgram.getLoyaltyProgramId());
                customerRewardsLog.setVoucherCodeId(voucherCode.longValue());
                customerRewardsLog.setRewardDate(BigInteger.valueOf(date.longValue()));
                customerRewardsLog.setActive(true);
                customerRewardsLog.setDeleted(false);
                customerRewardsLogService.createCustomerRewardsLog(customerRewardsLog);
                //Insert CustomerHasVoucher
                CustomerHasVoucher customerHasVoucher = new CustomerHasVoucher();
                customerHasVoucher.setCustomerId(customer.getCustomerId());
                customerHasVoucher.setVoucherCodeId(voucherCode);
                customerHasVoucher.setReceivedDate(BigInteger.valueOf(date.longValue()));
                customerHasVoucher.setExpiredDate(BigInteger.valueOf(date + 86400*voucher.getNumberDateUse()));
                customerHasVoucher.setActive(true);
                customerHasVoucher.setDeleted(false);
                customerHasVoucherService.createCustomerHasVoucher(customerHasVoucher);
                //Update table Customer
                Integer totalVoucher = customerHasVoucherService.countVoucherByCustomerId(currentUser.getId());
                customer.setTotalVoucher(totalVoucher.intValue());
                customer.setTotalPoint(customer.getTotalPoint()+loyaltyProgram.getPoint());
                customerService.updateVoucherAndPointCustomer(customer);
            }

            Map<String, Object> message = new HashMap<>();
            message.put("Message","You’ve just earned "+ redeemDTO.get("availableVoucher") +" gift vouchers");
            Map<String, Object> out = new HashMap<>();
            out.put("data", message);
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
