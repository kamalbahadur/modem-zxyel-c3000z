/*
 * Copyright (c) 2024 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.kg.modem_zyxel_c3000z;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Class Description goes here.
 * Created by kbahadur on 9/22/24
 */
@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
       return new RestTemplate();
    }
}
