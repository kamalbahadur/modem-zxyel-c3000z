/*
 * Copyright (c) 2024 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kg.modem_zyxel_c3000z;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Class Description goes here.
 * Created by kbahadur on 9/22/24
 */
@Component
public class AuthHandler {

    @Autowired RestTemplate restTemplate;

    public String login(String host, String port, String username, String password) {
        String url = "http://" + host + ":" + port + "/login.cgi";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("admin_username", username);
        map.add("admin_password", password);

        ResponseEntity<String> response = restTemplate.postForEntity(url,  new HttpEntity<>(map, headers) , String.class);

        List<String> cookies = response.getHeaders().get(HttpHeaders.SET_COOKIE);

        return cookies.stream()
                .filter(cookie -> cookie.contains("SESSION="))
                .findFirst()
                .map(cookie -> cookie.split("=")[1])
                .map(cookie -> cookie.split(";")[0])
                .get();
    }

    public boolean logout(String host, String port) {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://" + host + ":" + port + "/logout.cgi",   String.class);
        return HttpStatus.OK == response.getStatusCode();
    }
}
