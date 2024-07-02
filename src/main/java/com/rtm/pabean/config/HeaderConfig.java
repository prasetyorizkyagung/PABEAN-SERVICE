package com.rtm.pabean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class HeaderConfig {

    @Value("${rtm.pabean.ceisa.api-key}")
    private String beacukaiApiKey;

    public HttpHeaders headerCeisa(String token) {
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, String.format("%s %s", "Bearer", token));
        header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        header.set("Beacukai-Api-Key", beacukaiApiKey);
        return header;
    }
}
