package com.nin.api;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nin.dto.LoginDTO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    ResponseEntity<Map<String, Object>> clientLogin(HttpServletRequest httpServletRequest, @RequestBody LoginDTO loginDTO){
    
        String credentials = "cHJvamVjdF9jbGllbnQ6cHJvamVjdF9wYXNz";		

		HttpHeaders headers = new HttpHeaders();
		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
		headers.add("Authorization", "Basic " + credentials);

		String link = httpServletRequest.getRequestURL().toString();
		String access_token_url = link.replaceAll("auth/login", "oauth/token");
       	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "password");
		map.add("password", loginDTO.getPassword());
		map.add("username", loginDTO.getUsername());
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity( access_token_url, request , String.class );

			// ResponseEntity<String> response = null;
			
			// response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
			if( response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null){
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(response.getBody());
				String token = node.path("access_token").asText();
				Map<String, Object> data = new HashMap<String, Object>() {{
					put("token", token);				
				}};
				Map<String, Object> out = new HashMap<String, Object>() {{
					put("error", 0);
					put("data", data);
				}};
				return new ResponseEntity<>(out, HttpStatus.OK); 
			}
			else{
				Map<String, Object> data = new HashMap<String, Object>() {{
					put("message", "Login Fail");				
				}};
				Map<String, Object> out = new HashMap<String, Object>() {{
					put("error", 1);
					put("data", data);
				}};
				return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED); 
			}
			

		} catch (Exception e) {
			System.out.println("Access Token Response ---------" + e.getMessage());
		}
		Map<String, Object> data = new HashMap<String, Object>() {{
			put("message", "Login Fail");				
		}};
		Map<String, Object> out = new HashMap<String, Object>() {{
			put("error", 1);
			put("data", data);
		}};
		return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED); 

		//return null;
	}	

}
