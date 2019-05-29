package com.nin.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nin.dto.LoginDTO;
import com.nin.model.User;
import com.nin.service.UserService;

@RestController
@RequestMapping("/auth")
public class LoginController {
	int LOGIN_FAIL = -1;
	int LOGIN_SUCCESSFUL = 0;
	int LOGIN_NEW = 1;

	@Autowired
	private TokenStore tokenStore;

	private UserService userService;

	@PostMapping("/login")
	ResponseEntity<Map<String, Object>> clientLogin(HttpServletRequest httpServletRequest,
			@RequestBody LoginDTO loginDTO) {

		String credentials = "cHJvamVjdF9jbGllbnQ6cHJvamVjdF9wYXNz";

		HttpHeaders headers = new HttpHeaders();
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
		headers.add("Authorization", "Basic " + credentials);

		String link = httpServletRequest.getRequestURL().toString();
		String access_token_url = link.replaceAll("auth/login", "oauth/token");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "password");
		map.add("password", loginDTO.getPassword());
		map.add("username", loginDTO.getUsername());
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(access_token_url, request, String.class);

			// ResponseEntity<String> response = null;

			// response = restTemplate.exchange(access_token_url, HttpMethod.POST, request,
			// String.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(response.getBody());
				String token = node.path("access_token").asText();
				Map<String, Object> data = new HashMap<String, Object>() {
					{
						put("token", token);
					}
				};
				Map<String, Object> out = new HashMap<String, Object>() {
					{
						put("error", LOGIN_SUCCESSFUL);
						put("data", data);
					}
				};
				return new ResponseEntity<>(out, HttpStatus.OK);
			} else {
				Map<String, Object> data = new HashMap<String, Object>() {
					{
						put("message", "Login Fail");
					}
				};
				Map<String, Object> out = new HashMap<String, Object>() {
					{
						put("error", LOGIN_FAIL);
						put("data", data);
					}
				};
				return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			System.out.println("Access Token Response ---------" + e.getMessage());
		}
		Map<String, Object> data = new HashMap<String, Object>() {
			{
				put("message", "Login Fail");
			}
		};
		Map<String, Object> out = new HashMap<String, Object>() {
			{
				put("error", LOGIN_FAIL);
				put("data", data);
			}
		};
		return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED);

		// return null;
	}

	@PostMapping("/login-create")
	ResponseEntity<Map<String, Object>> clientLoginCreate(HttpServletRequest httpServletRequest,
			@RequestBody LoginDTO loginDTO) {

		User user = userService.findByEmail(loginDTO.getUsername());
		int isCreateNew = 0;
		if (user == null) {
			userService.createUser(loginDTO.getUsername(), loginDTO.getPassword());
			isCreateNew = 1;
		}

		String credentials = "cHJvamVjdF9jbGllbnQ6cHJvamVjdF9wYXNz";

		HttpHeaders headers = new HttpHeaders();
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
		headers.add("Authorization", "Basic " + credentials);

		String link = httpServletRequest.getRequestURL().toString();
		String access_token_url = link.replaceAll("auth/login-create", "oauth/token");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", "password");
		map.add("password", loginDTO.getPassword());
		map.add("username", loginDTO.getUsername());
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		try {
			ResponseEntity<String> response = restTemplate.postForEntity(access_token_url, request, String.class);

			// ResponseEntity<String> response = null;

			// response = restTemplate.exchange(access_token_url, HttpMethod.POST, request,
			// String.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
				ObjectMapper mapper = new ObjectMapper();
				JsonNode node = mapper.readTree(response.getBody());
				String token = node.path("access_token").asText();
				int error = (isCreateNew == 1) ? LOGIN_NEW : LOGIN_SUCCESSFUL;
				Map<String, Object> data = new HashMap<String, Object>() {
					{
						put("token", token);
					}
				};
				Map<String, Object> out = new HashMap<String, Object>() {
					{
						put("error", error);
						put("data", data);
					}
				};
				return new ResponseEntity<>(out, HttpStatus.OK);
			} else {
				Map<String, Object> data = new HashMap<String, Object>() {
					{
						put("message", "Login Fail");
					}
				};
				Map<String, Object> out = new HashMap<String, Object>() {
					{
						put("error", LOGIN_FAIL);
						put("data", data);
					}
				};
				return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			System.out.println("Access Token Response ---------" + e.getMessage());
		}
		Map<String, Object> data = new HashMap<String, Object>() {
			{
				put("message", "Login Fail");
			}
		};
		Map<String, Object> out = new HashMap<String, Object>() {
			{
				put("error", LOGIN_FAIL);
				put("data", data);
			}
		};
		return new ResponseEntity<>(out, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			String tokenValue = authHeader.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
		}
	}

}
