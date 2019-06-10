package com.nin.api;

import com.nin.dto.RedeemDTO;
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
import java.util.Objects;

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
            Map<String, Object> out = new HashMap<>();
            Map<String, Object> message = new HashMap<>();
            out.put("error", -1);
            Long date = new Date().getTime() / 1000;
            User currentUser = userService.getCurrentUser();
            Customer customer = customerService.findByCustomerId(currentUser.getId());
            //Check null
            Integer loyaltyProgramId = (Integer) redeemDTO.get("loyaltyProgramId");
            Integer availableVoucher = (Integer) redeemDTO.get("availableVoucher");
            if ( loyaltyProgramId == null ||  loyaltyProgramId <= 0 ) {
                message.put("Message", "Loyalty Program ID is null !");
                out.put("data", message);
                return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
            }
            if (availableVoucher == null || availableVoucher <= 0) {
                message.put("Message", "Available Voucher is null or smaller than 0 !");
                out.put("data", message);
                return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);

            }
            //Find loyalty program by ID and expire date
            LoyaltyProgram loyaltyProgram = loyaltyProgramService.findLoyaltyProgramByIdAndDate(date, loyaltyProgramId.longValue());
            if (loyaltyProgram == null) {
                message.put("Message", "Loyalty Program  is not available !");
                out.put("data", message);
                return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);

            }
            Integer totalPoint = loyaltyProgram.getPoint() * availableVoucher;
            if (customer.getTotalPoint() < totalPoint) {
                message.put("Message", "Point is not enough !");
                out.put("data", message);
                return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
            }

            Voucher voucher = voucherService.findByVoucherId(loyaltyProgram.getVoucherId());
            Long voucherCode = voucherCodeService.findVoucherCodeByVoucherId(loyaltyProgram.getVoucherId());
            for (Integer i=0;i<availableVoucher;i++){
                //Insert CustomerRewardsLog
                CustomerRewardsLog customerRewardsLog = new CustomerRewardsLog();
                customerRewardsLog.setCustomerId(customer.getCustomerId());
                customerRewardsLog.setPointBurnEarn(-1 * loyaltyProgram.getPoint());
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
                customerHasVoucher.setExpiredDate(BigInteger.valueOf(date + 86400 * voucher.getNumberDateUse()));
                customerHasVoucher.setActive(true);
                customerHasVoucher.setDeleted(false);
                customerHasVoucherService.createCustomerHasVoucher(customerHasVoucher);
            }
            //Update table Customer
            Integer totalVoucher = customerHasVoucherService.countVoucherByCustomerId(currentUser.getId());
            customer.setTotalVoucher(totalVoucher.intValue());
            customer.setTotalPoint(customer.getTotalPoint() - totalPoint);
            customerService.updateVoucherAndPointCustomer(customer);

            message.put("Message", "You’ve just earned " + redeemDTO.get("availableVoucher") + " gift vouchers");
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
