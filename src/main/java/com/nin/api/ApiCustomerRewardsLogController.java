package com.nin.api;

import com.nin.model.CustomerRewardsLog;
import com.nin.model.LoyaltyProgram;
import com.nin.model.User;
import com.nin.model.Voucher;
import com.nin.service.CustomerRewardsLogService;
import com.nin.service.LoyaltyProgramService;
import com.nin.service.UserService;
import com.nin.service.VoucherService;
import com.nin.util.DateUtil;

import org.apache.commons.lang3.ArrayUtils;
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
public class ApiCustomerRewardsLogController {
    private CustomerRewardsLogService customerRewardsLogService;
    private UserService userService;
    private LoyaltyProgramService loyaltyProgramService;
    private VoucherService voucherService;

    @Autowired
    public ApiCustomerRewardsLogController(CustomerRewardsLogService customerRewardsLogService, UserService userService, LoyaltyProgramService loyaltyProgramService, VoucherService voucherService) {
        this.customerRewardsLogService = customerRewardsLogService;
        this.userService = userService;
        this.loyaltyProgramService = loyaltyProgramService;
        this.voucherService = voucherService;
    }




    @GetMapping("/my-rewards")
    public ResponseEntity<Map<String,Object>> findCustomerRewardsLogByCustomerId(){
        try {
            User currentUser = userService.getCurrentUser();
            List<Object[]> listCustomerRewardsLog = customerRewardsLogService.findCustomerRewardsLogByCustomerId(currentUser.getId());
            List<Map<String, Object>> result = new ArrayList<>();
            for (Object[] customerRewardsLog : listCustomerRewardsLog) {
                LoyaltyProgram loyaltyProgram = loyaltyProgramService.finByLoyaltyProgramId(Long.parseLong(customerRewardsLog[0].toString()));
                Voucher voucher = voucherService.findByVoucherId(loyaltyProgram.getVoucherId());
                Map<String, Object> obj = new HashMap<>();
                obj.put("voucherId", voucher.getVoucherId());
                obj.put("loyaltyProgramId", loyaltyProgram.getLoyaltyProgramId());
                obj.put("voucherName", voucher.getName());
                obj.put("image", voucher.getImage());
                obj.put("price", voucher.getValue());
                obj.put("currency", voucher.getCurrency());
                obj.put("releasesTotal", loyaltyProgram.getTotal_release());
                obj.put("point", loyaltyProgram.getPoint());
                obj.put("uAvailables", customerRewardsLog[1]);

                result.add(obj);
            }
            Map<String, Object> out = new HashMap<String, Object>() {{
                put("data", result);
                put("error", 0);
            }};
            return new ResponseEntity<>(out, HttpStatus.OK);
        }catch (Exception e) {
            Map<String, Object> responseMap = new HashMap<String, Object>();
            responseMap.put("Message", e.getMessage());
            responseMap.put("data", responseMap);
            responseMap.put("error", -1);
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/redeem-histories")
	public ResponseEntity<Map<String, Object>> redeemHistories() {
		try {
			User currentUser = userService.getCurrentUser();
			List<CustomerRewardsLog> listCustomerRewardsLog = customerRewardsLogService
					.findAllCustomerRewardsLogByCustomerIdActive(currentUser.getId());
			List<Map<String, Object>> result = new ArrayList<>();
			Map<Long, LoyaltyProgram> mapLoyaltyProgram = new HashMap<Long, LoyaltyProgram>();
			if (listCustomerRewardsLog != null) {
				Map<String, List<CustomerRewardsLog>> mapCountCustomerRewardsLog = new HashMap<String, List<CustomerRewardsLog>>();
				for (CustomerRewardsLog customerRewardsLog : listCustomerRewardsLog) {
					if (customerRewardsLog.getLoyaltyProgramId() != null) {
						LoyaltyProgram loyaltyProgram = loyaltyProgramService
								.finByLoyaltyProgramId(customerRewardsLog.getLoyaltyProgramId());
						if (loyaltyProgram != null) {
							mapLoyaltyProgram.put(loyaltyProgram.getLoyaltyProgramId(), loyaltyProgram);
							String keyMap = customerRewardsLog.getLoyaltyProgramId() + "_"
									+ customerRewardsLog.getRewardDate();
							if (mapCountCustomerRewardsLog.get(keyMap) == null) {
								List<CustomerRewardsLog> listObjects = new ArrayList<CustomerRewardsLog>();
								listObjects.add(customerRewardsLog);
								mapCountCustomerRewardsLog.put(keyMap, listObjects);
							} else {
								mapCountCustomerRewardsLog.get(keyMap).add(customerRewardsLog);
							}

						}

					}

				}

				for (String key : mapCountCustomerRewardsLog.keySet()) {
					List<CustomerRewardsLog> customerRewardsLogs = mapCountCustomerRewardsLog.get(key);
					if (customerRewardsLogs != null && customerRewardsLogs.size() > 0) {
						CustomerRewardsLog customerRewardsLog = customerRewardsLogs.get(0);
						LoyaltyProgram loyaltyProgram = mapLoyaltyProgram.get(customerRewardsLog.getLoyaltyProgramId());
						if (loyaltyProgram != null) {
							Voucher voucher = voucherService.findByVoucherId(loyaltyProgram.getVoucherId());
							Map<String, Object> obj = new HashMap<>();

							if (voucher != null) {
								obj.put("voucherId", voucher.getVoucherId());
								obj.put("voucherName", voucher.getName());
								obj.put("image", voucher.getImage());
								obj.put("price", voucher.getValue());
								obj.put("currency", voucher.getCurrency());
								obj.put("uAvailables", customerRewardsLogs.size());
								obj.put("loyaltyProgramId", loyaltyProgram.getLoyaltyProgramId());
								if (customerRewardsLog.getPointBurnEarn() != null) {
									obj.put("point",
											customerRewardsLog.getPointBurnEarn() * -1);
								}
								if (customerRewardsLog.getRewardDate() != null) {
									obj.put("rewardDate",
											DateUtil.longDateToString(customerRewardsLog.getRewardDate().longValue()));
								} else {
									obj.put("rewardDate", "");
								}
								result.add(obj);

							}
						}

					}

				}

			}

			Map<String, Object> out = new HashMap<String, Object>() {
				{
					put("data", result);
					put("error", 0);
				}
			};
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
