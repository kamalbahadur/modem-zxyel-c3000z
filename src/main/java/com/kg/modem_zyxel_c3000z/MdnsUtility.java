package com.kg.modem_zyxel_c3000z;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MdnsUtility {
    @Value("${server.port}")
    String serverPort;

    @Value("${spring.application.name}")
    String applicationName;

    private JmDNS jmdns;

    @PostConstruct
    public void init() throws IOException {
        // Create a JmDNS instance
        jmdns = JmDNS.create(InetAddress.getLocalHost());

        // Register a service
        ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", applicationName,
                Integer.parseInt(serverPort), "");
        Map<String, String> properties = new HashMap<>();
        properties.put("connect", "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + serverPort + "/connect");
        properties.put("disconnect", "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + serverPort + "/disconnect");
        serviceInfo.setText(properties);
        jmdns.registerService(serviceInfo);
    }

    @PreDestroy
    public void destroy() {
        jmdns.unregisterAllServices();
    }
}
