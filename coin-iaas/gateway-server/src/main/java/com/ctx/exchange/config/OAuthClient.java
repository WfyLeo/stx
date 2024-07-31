package com.ctx.exchange.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class OAuthClient {

    private static final String TOKEN_URL = "http://localhost:8080/oauth/token";
    private static final String CLIENT_ID = "coin-api";
    private static final String CLIENT_SECRET = "coin-secret";

    public String getAccessTokenByRefreshToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, request, Map.class);

        return (String) response.getBody().get("access_token");
    }
}
