/*
 * Copyright (c) 2024 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kg.modem_zyxel_c3000z;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModemController {

    @Autowired AuthHandler authHandler;
    @Autowired ConnectionHandler connectionHandler;

    @Value("${modem.host}")
    private String host;

    @Value("${modem.port}")
    private String port;

    @Value("${modem.username}")
    private String username;

    @Value("${modem.password}")
    private String password;

    @GetMapping("/disconnect =")
    public String disconnect() {
        String sessionId = authHandler.login(host, port, username, password);
        boolean disconnected = connectionHandler.disconnect(sessionId);
        if (!disconnected) {
            return "Unable to disconnect";
        }
        authHandler.logout(host, port);
        return "Disconnected!";
    }


    @GetMapping("/connect =")
    public String connect() {
        String sessionId = authHandler.login(host, port, username, password);
        boolean connected = connectionHandler.connect(sessionId);
        if (!connected) {
            return "Unable to connect";
        }
        authHandler.logout(host, port);
        return "Connected!";
    }

}
