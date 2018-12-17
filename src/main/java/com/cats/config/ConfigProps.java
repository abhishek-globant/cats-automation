package com.cats.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProps {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${chromeDriverPath}")
    private String chromeDriverPath;

    @Value("${name:unknown}")
    private String name;


    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getMessage() {
        return getMessage(name);
    }

    public String getMessage(String name) {
        return name;
    }
}
