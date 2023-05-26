package com.test.dog.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class InitConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(requestFactory);
        var restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofMillis(5000))
                .setConnectTimeout(Duration.ofMillis(5000))
                .build();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateOther(RestTemplateBuilder restTemplateBuilder) {
        var requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(requestFactory);
        var restTemplate = restTemplateBuilder
                .setReadTimeout(Duration.ofMillis(2000))
                .setConnectTimeout(Duration.ofMillis(2000))
                .build();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }
}
