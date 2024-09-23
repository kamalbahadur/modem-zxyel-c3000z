/*
 * Copyright (c) 2024 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kg.modem_zyxel_c3000z;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConnectionHandler {
    @Autowired RestTemplate restTemplate;

    @Value("${modem.host}")
    String host;

    @Value("${modem.port:80}")
    String port;

    public boolean connect(String sessionId) {
        String url = "http://" + host + ":" + port + "/SetpppUp.cgi";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "SESSION=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity response = restTemplate.postForEntity(url, requestEntity, String.class);
        return HttpStatus.OK == response.getStatusCode();
    }

    public boolean disconnect(String sessionId) {
        String url = "http://" + host + ":" + port + "/SetpppDown.cgi";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "SESSION=" + sessionId);
        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
        ResponseEntity response = restTemplate.postForEntity(url, requestEntity, String.class);
        return HttpStatus.OK == response.getStatusCode();
    }
}
