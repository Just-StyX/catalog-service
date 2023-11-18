package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    /**
     * A welcome message to users
     */
    private String greeting;

    public String getGreeting() { return this.greeting; }

    public void setGreeting(String greeting) {this.greeting = greeting; }
}
