package com.nin.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nin.model.Customer;
import com.nin.model.User;
import com.nin.service.UserService;
import com.nin.util.DateUtil;
import com.nin.util.InterestedField;

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
		Map<String, Object> out = new HashMap<String, Object>() {
			{
				put("id", currentUser.getId());
				put("email", currentUser.getEmail());

			}
		};
		return new ResponseEntity<>(out, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<Map<String, Object>> userProfile() {

		try {
			User currentUser = userService.getCurrentUser();
			Customer aCustomer = userService.findByCustomerIdAndEmail(currentUser.getId(), currentUser.getEmail());
			Map<String, Object> out = new HashMap<String, Object>() {
				{
					put("id", currentUser.getId());
					put("email", currentUser.getEmail());
					if (aCustomer != null) {
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
						if(aCustomer.getDob()!= null) {
							put("dateOfBirth", DateUtil.longDateToString(aCustomer.getDob()));
						}
						else {
							put("dateOfBirth", "");
						}
						

						String interestedFields = aCustomer.getInterestedFields();
						ArrayList<Map<String, Object>> interestedFieldsMap = new ArrayList<Map<String, Object>>();
						if (!StringUtils.isEmpty(interestedFields)) {
							String[] ary = interestedFields.split(",");
							for (int i = 0; i < ary.length; i++) {
								InterestedField field = null;
								try {
									field = InterestedField.valueOf("_" + ary[i]);
								}
								catch(Exception e) {
								}
								if (field != null) {
									Map<String, Object> interestedFieldObj = new HashMap<String, Object>();
									interestedFieldObj.put("value", Integer.parseInt(ary[i]));
									interestedFieldObj.put("name", field.getstringValue());
									interestedFieldsMap.add(interestedFieldObj);
								}
							}
						}
						put("interestedFields", interestedFieldsMap);

					}
				}
			};
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("data", out);
			responseMap.put("error", 0);
			return new ResponseEntity<>(responseMap, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("Message", e.getMessage());
			responseMap.put("data", responseMap);
			responseMap.put("error", -1);
			return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/profile")
	public ResponseEntity<Map<String, Object>> updateProfile(@RequestBody Map<String, Object> profileDTO) {

		try {
			User currentUser = userService.getCurrentUser();
			Customer customerDB = userService.findByCustomerIdAndEmail(currentUser.getId(), currentUser.getEmail());
			boolean isUpdate = customerDB != null;
			Customer aCustomer = new Customer();
			if (isUpdate) {
				aCustomer = customerDB;
			}
			aCustomer.setCustomerId(currentUser.getId());
			aCustomer.setEmail(currentUser.getEmail());

			if (profileDTO.get("firstName") != null) {
				aCustomer.setFirstName(profileDTO.get("firstName").toString());
			}
			
			if (profileDTO.get("lastName") != null) {
				aCustomer.setLastName(profileDTO.get("lastName").toString());
			}
			
			
			if (profileDTO.get("phone") != null) {
				aCustomer.setPhone(profileDTO.get("phone").toString());
			}
			
			if (profileDTO.get("phone") != null) {
				aCustomer.setPhone(profileDTO.get("phone").toString());
			}
			if (profileDTO.get("address") != null) {
				aCustomer.setAddress(profileDTO.get("address").toString());
			}

			if (profileDTO.get("lang") != null) {
				aCustomer.setLang(profileDTO.get("lang").toString());
			}

			if (profileDTO.get("avatarImage") != null) {
				aCustomer.setAvartarImg(profileDTO.get("avatarImage").toString());
			}

			if (profileDTO.get("bannerHeaderImage") != null) {
				aCustomer.setBannerHeaderImg(profileDTO.get("bannerHeaderImage").toString());
			}

			if (profileDTO.get("interestedFields") != null) {
				String myNum = profileDTO.get("interestedFields").toString() ;
				String joined = myNum.replace("[", "").replace("]", "").replace(" ", "");
				aCustomer.setInterestedFields(joined);
			}

			if (StringUtils.isEmpty(aCustomer.getQrcodeImg())) {
				aCustomer.setQrcodeImg(
						generateQRCode(aCustomer.getCustomerId(), aCustomer.getEmail(), aCustomer.getFirstName()));
			}

			Customer saved = userService.createOrUpdateCustomer(aCustomer);
			Map<String, Object> out = new HashMap<String, Object>();
			out.put("data", saved);
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

	private String generateQRCode(long customerId, String email, String firstName) {
		String q = "CustomerId: " + customerId + " ||| " + "email: " + email + " ||| " + "FirstName: " + firstName;
		String url = null;
		try {
			url = "http://chart.apis.google.com/chart?cht=qr&chs=300x300&chl=" + URLEncoder.encode(q, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}
