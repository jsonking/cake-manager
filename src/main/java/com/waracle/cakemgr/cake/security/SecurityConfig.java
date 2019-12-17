package com.waracle.cakemgr.cake.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * A Security Config that allows all requests
 */
@Configuration
@EnableWebSecurity
@Profile("!localtesting")
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        log.warn("SecurityConfig enabled. All are requests allowed");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
}
